package tp.pr5;

import tp.pr5.cityLoader.CityLoaderFromTxtFile;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.gui.*;
import tp.pr5.console.ConsoleController;
import java.io.*;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * Punto de entrada de la aplicacion, si arg no esta especificada (o se da mas de un archivo),
 * se muestra un mensaje de error (System.err) y la aplicacion termina con un codigo de error -1,
 * si el mapa no puede ser leido o no existe la aplicacion termina con un distinto codigo de error -2,
 * en cualquier otro caso el juego se inicia normalmente (devuelve código 0)
 * 
 */
public class Main {

	public static final String LINE_SEPARATOR =	System.getProperty("line.separator");
	
	/**
	 * Creates the city, the engine and finally
	 * starts the simulation
	 * @param args
	 */

	public static void main(String[] args){
		Options opciones = optionsList();
		CommandLineParser cmdParser = new GnuParser();
		CommandLine line = null;

		try {
			line = cmdParser.parse(opciones, args);
			if (line.hasOption("h") || line.hasOption("help"))	showHelp();
			else {
				if (!line.hasOption("m") && !line.hasOption("map")) {
					System.err.println("Map file not specified");
					System.exit(1);
				}
				if (!line.hasOption("i") && !line.hasOption("interface")) {
					System.err.println("Interface not specified");
					System.exit(1);
				}
				else {
					if (line.getOptionValue("m").equalsIgnoreCase("madrid")) {
						System.err.println("Wrong type of interface");
						System.exit(3);
					}
					
					InputStream input = new FileInputStream(line.getOptionValue("m"));
					
					CityLoaderFromTxtFile loader = new CityLoaderFromTxtFile();
					
					RobotEngine engine = new RobotEngine(loader.loadCity(input),loader.getInitialPlace(),Direction.NORTH);
					
					if (line.getOptionValue("i").equalsIgnoreCase("console")) {
						ConsoleController controladorConsola = new ConsoleController(engine);
						controladorConsola.startController();
						System.exit(0);
					}
					else {
						if (line.getOptionValue("i").equalsIgnoreCase("swing")) {
							GUIController controladorSwing = new GUIController(engine);
							
							MainWindow vista = new MainWindow(controladorSwing);

							controladorSwing.startController();
						}
						else {
							if (line.getOptionValue("i").equalsIgnoreCase("both")){
								//Iniciamos los controladores
								ConsoleController controlConsola = new ConsoleController(engine);
								GUIController controlSwing = new GUIController(engine);
								
								MainWindow vistaGUI = new MainWindow(controlSwing);
								controlSwing.startController();
							}
							else {
								System.err.println("Wrong type of interface");
								System.exit(3);
							}
						}
					}
				}
			}
		}
		catch (WrongCityFormatException e) {
			System.err.println("El mapa es incorrecto");
			System.err.println(e);
			System.exit(2);
		}
		catch (IOException e) {
			System.err.println("Error reading the map file:"
			+ line.getOptionValue("m")
			+ "(No existe el fichero o el directorio)");
			System.exit(2);
		}
		catch (ParseException e) {
			System.exit(1);
		}
		
	}

	/**
	 * Inicializa las opciones del juego:
	 * mostrando una ayuda con las posibles opciones y dando a elegir la posibilidad
	 * de iniciar el juego en swing o en consola
	 */
	private static Options optionsList() {
		Options options = new Options();
		options.addOption("h", "help", false, "Shows this help message");
		options.addOption("m", "map", true, "The type of interface: console or swing");
		options.addOption("i", "interface", true, "File with the description of the city");
		return options;
	}
	
	/**
	 * Muestra el mensaje de ayuda que la aplicacion mostrará si el usuario lo solicita
	 */
	private static void showHelp() {
		String cadena = new String("Execute this assignment with these parameters:" + LINE_SEPARATOR
									+"usage: tp.pr4.Main [-h] [-i <type>] [-m <mapfile>]" + LINE_SEPARATOR
									+ " -h,--help               Shows this help message" + LINE_SEPARATOR
									+ " -i,--interface <type>   The type of interface: console, swing or both" + LINE_SEPARATOR
									+ " -m,--map <mapfile>      File with the description of the city");

		System.out.println(cadena);
	}
}
