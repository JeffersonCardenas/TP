package tp.pr5.console;

import java.util.Iterator;
import java.util.List;
import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * La vista que despliega los System.out de la aplicacion. Implementa todas las interfaces
 * de tipo observer que seran notificadas sobre cada evento que ocurra cuando el robot
 * se esta moviendo
 *
 */
public class Console implements InventoryObserver,NavigationObserver,RobotEngineObserver{
	
	public static final String LINE_SEPARATOR =	System.getProperty("line.separator");
	
	//Constructora
	public Console(){}

	/**
	 * El RobotEngine informa de que la comunicacion se ha terminado
	 */
	public void communicationCompleted(){
		System.out.println("WALL·E says: I have communication problems. Bye bye");
	}

	/**
	 * El RobotEngine informa de que la ayuda ha sido solicitada
	 * @param help String con la informacion de ayuda
	 */
	public void communicationHelp(String help){
		System.out.println(help);
	}

	/**
	 * El RobotEngine informa de que el robot se desconecta
	 * (porque ha llegado a su nave o se ha quedado sin combustible)
	 * @param atShip true si el robot ha llegado a su nave o false si se ha quedado
	 * sin combustible
	 */
	public void engineOff(boolean atShip){
		if (atShip) System.out.println("WALL·E says: I am at my space ship. Bye bye");
		else System.out.println("WALL·E says: I run out of fuel. I cannot move. Shutting down...");
	}

	/**
	 * El Robot Engine informa de un error
	 * @param msg mensaje de error
	 */
	public void raiseError(String msg){
		System.out.println(msg);
	}

	/**
	 * El RobotEngine informa de que el robot quiere decir algo
	 * @param message mensaje del robot
	 */
	public void robotSays(String message){
		System.out.println(message);
	}

	/**
	 * El RobotEngine informa que el fuel y/o la cantidad de material reciclado ha cambiado
	 * @param fuel cantidad actual de fuel
	 * @param recycledMaterial cantidad actual de material reciclado
	 */
	public void robotUpdate(int fuel, int recycledMaterial){
		System.out.println("      * My power is " + fuel);
		System.out.println("      * My recycled material is " + recycledMaterial);
	}

	/**
	 * Notifica que la direccion del robot ha cambiado
	 * @param newHeading nueva direccion del robot
	 */
	public void headingChanged(Direction newHeading){
		System.out.println("WALL·E is looking at direction " + newHeading);
	}

	/**
	 * Notifica que el modulo de navegacion ha sido inicializado
	 * @param initialPlace el lugar donde el robot comienza la simulacion
	 * @param heading direccion inicial del robot
	 */
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading){
		System.out.println(initialPlace.toString());
		System.out.println("WALL·E is looking at direction " + heading);
	}
	
	/**
	 * Notifica que el lugar donde esta el robot ha cambiado(porque el robot
	 * ha cogido o soltado un item)
	 * @param placeDescription
	 */
	public void placeHasChanged(PlaceInfo placeDescription){
		System.out.println(placeDescription.toString());
	}

	/**
	 * Notifica que el usuario solicita realizar la instruccion de tipo RADAR
	 * @param placeDescription
	 */
	public void placeScanned(PlaceInfo placeDescription){
		System.out.println(placeDescription.toString());
	}

	/**
	 * Notifica que el robot ha llegado al lugar
	 * @param heading direccion de movimiento del robot
	 * @param place el lugar donde el robot llega
	 */
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place){
		System.out.println("WALL·E says: Moving in direction " + heading);
		System.out.println(place.toString());
	}

	/**
	 * Notifica cuando el contenedor ha cambiado
	 * @param inventory Nuevo inventario
	 */
	public void inventoryChange(List<Item> inventory){
		String cadena = new String("");
		Iterator<Item> it = inventory.iterator();
		while (it.hasNext()){
			cadena = cadena + LINE_SEPARATOR + "   " + it.next().getId();
		}		
		System.out.println(cadena);
	}

	/**
	 * Notifica cuando el usuario solicita utilizas la instruccion SCAN sobre el inventario
	 * @param inventoryDescription Informacion sobre el inventario
	 */
	public void inventoryScanned(String collectionDescription){
		System.out.println("WALL·E says: I am carrying the following items" + collectionDescription);
	}

	/**
	 * Notifica cuando el usuario quiere escanear un item del inventario
	 * @param description Descripcion del item
	 */
	public void itemScanned(String description){
		System.out.println(description);
	}

	/**
	 * Notifica cuando el item se ha agotado y será removido del inventario. Una invocacion
	 * al metodo inventoryChanged seguirá
	 * @param itemName nombre del item
	 */
	public void itemEmpty(String itemName){
		System.out.println("WALL·E says: What a pity! I have no more " + itemName + " in my inventory");
	}
	
	/**
	 * Muestra el prompt de lectura de comandos
	 */
	public void showReader(){
		System.out.print("WALL·E> ");
	}

}
