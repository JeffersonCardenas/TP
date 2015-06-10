package tp.pr5.gui;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.util.List;

/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * Panel al final de la ventana que muestra mensajes sobre los eventos ocurridos durante la
 * simulacion. Este panel implementa los diferentes observadores
 *
 */
public class InfoPanel extends javax.swing.JPanel implements RobotEngineObserver,
															NavigationObserver,InventoryObserver{
	
	public static final String LINE_SEPARATOR =	System.getProperty("line.separator");
	private static final long serialVersionUID = 1L;
	private JLabel lbText;
	
	public InfoPanel(){
		this.lbText = new JLabel("",JLabel.CENTER);
		this.setLayout(new BorderLayout());
		this.add(lbText,BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	/**
	 * El RobotEngine informa de que la comunicacion se ha terminado
	 */
	public void communicationCompleted(){
		this.lbText.setText("WALL·E says: I have communication problems. Bye bye");
	}

	/**
	 * El RobotEngine informa de que la ayuda ha sido solicitada
	 * @param help String con la informacion de ayuda
	 */
	public void communicationHelp(String help){
		this.lbText.setText(help);
	}

	/**
	 * El RobotEngine informa de que el robot se desconecta
	 * (porque ha llegado a su nave o se ha quedado sin combustible)
	 * @param atShip true si el robot ha llegado a su nave o false si se ha quedado
	 * sin combustible
	 */
	public void engineOff(boolean atShip){
		if (atShip) {
			this.lbText.setText("WALL·E says: I am at my space ship. Bye bye");
			JOptionPane.showMessageDialog(this,"WALL·E says: I am at my space ship. Bye bye");
		}
		else {
			this.lbText.setText("WALL·E says: I run out of fuel. I cannot move. Shutting down...");
			JOptionPane.showMessageDialog(this,"WALL·E says: I run out of fuel. I cannot move. Shutting down...");
		}
		System.exit(0);
	}

	/**
	 * El Robot Engine informa de un error
	 * @param msg mensaje de error
	 */
	public void raiseError(String msg){
		this.lbText.setText(msg);
	}

	/**
	 * El RobotEngine informa de que el robot quiere decir algo
	 * @param message mensaje del robot
	 */
	public void robotSays(String message){
		this.lbText.setText(message);
	}

	/**
	 * El RobotEngine informa que el fuel y/o la cantidad de material reciclado ha cambiado
	 * @param fuel cantidad actual de fuel
	 * @param recycledMaterial cantidad actual de material reciclado
	 */
	public void robotUpdate(int fuel, int recycledMaterial){
		String mensaje = new String("Robot attributes has been updated " + "(" + fuel + "," + recycledMaterial + ")");
		this.lbText.setText(mensaje);		
	}

	/**
	 * Notifica que la direccion del robot ha cambiado
	 * @param newHeading nueva direccion del robot
	 */
	public void headingChanged(Direction newHeading){
	}

	/**
	 * Notifica que el modulo de navegacin ha sido inicializado
	 * @param initialPlace el lugar donde el robot comienza la simulacion
	 * @param heading direccion inicial del robot
	 */
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading){
	}
	
	/**
	 * Notifica que el lugar donde esta el robot ha cambiado(porque el robot
	 * ha cogido o soltado un item)
	 * @param placeDescription
	 */
	public void placeHasChanged(PlaceInfo placeDescription){}

	/**
	 * Notifica que el usuario solicita realizar la instruccion de tipo RADAR
	 * @param placeDescription
	 */
	public void placeScanned(PlaceInfo placeDescription){}

	/**
	 * Notifica que el robot ha llegado al lugar
	 * @param heading direccion de movimiento del robot
	 * @param place el lugar donde el robot llega
	 */
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place){}

	/**
	 * Notifica cuando el contenedor ha cambiado
	 * @param inventory Nuevo inventario
	 */
	public void inventoryChange(List<Item> inventory){
		
	}

	/**
	 * Notifica cuando el usuario solicita utilizas la instruccion SCAN sobre el inventario
	 * @param inventoryDescription Informacion sobre el inventario
	 */
	public void inventoryScanned(String inventoryDescription){
		this.lbText.setText(inventoryDescription);
	}

	/**
	 * Notifica cuando el usuario quiere escanear un item del inventario
	 * @param description Descripcion del item
	 */
	public void itemScanned(String description){
		this.lbText.setText(description);
	}

	/**
	 * Notifica cuando el item se ha agotado y será removido del inventario. Una invocacion
	 * al metodo inventoryChanged seguirá
	 * @param itemName nombre del item
	 */
	public void itemEmpty(String itemName){
		this.lbText.setText(itemName);
	}

}
