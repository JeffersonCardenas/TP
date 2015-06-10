package tp.pr5.console;

import java.util.Scanner;
import tp.pr5.Controller;
import tp.pr5.Interpreter;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;

/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * El controlador empleado cuando la aplicacion esta configurada para funcionar en modo consola.
 * Contiene el bucle de simulacion que ejecuta las instrucciones escritas por el usuario en
 * consola
 *
 */
public class ConsoleController extends Controller{
	
	private Console vista;
	private Scanner keyboard;
	
	//Constructora
	public ConsoleController(RobotEngine game){
		super(game);
		this.vista = new Console();
		super.registerEngineObserver(vista);
		super.registerItemContainerObserver(vista);
		super.registerRobotObserver(vista);
	}
	
	/**
	 * Pone el juego en funcionamiento en modo consola
	 */
	public void startController(){
		Instruction ins=null;
		this.keyboard = new Scanner(System.in);
		new Interpreter();
		this.engine.requestStart();
		try{
			while (!this.engine.isOver()) {
				this.vista.showReader();
				ins=Interpreter.generateInstruction(keyboard.nextLine());			
				this.engine.communicateRobot(ins);
			}
		}
		catch (WrongInstructionFormatException e){
			
		}
		if (this.engine.isOver())	this.engine.requestQuit();
		this.keyboard.close();
	}

}
