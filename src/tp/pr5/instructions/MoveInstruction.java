package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Jefferson Cárdenas & Marco Antonio Palacios
 * Su ejecucion mueve el robot desde un lugar al siguiente en la direccion actual, esta instruccion
 * funciona si el usuario escribe MOVE o MOVER
 *
 */
public class MoveInstruction implements Instruction{
	
	private ItemContainer theInventory;
	private NavigationModule theNavigation;
	private RobotEngine theEngine;
	
	/**
	 * Constructora por defecto
	 */
	public MoveInstruction(){}
	
	/**
	 * Parsea el string devolviendo una instancia de MoveInstruction o lanzando WrongInstructionFormatException
	 */
	public Instruction parse (String cad) throws WrongInstructionFormatException{
		if (cad.equalsIgnoreCase("MOVE") || cad.equalsIgnoreCase("MOVER"))
			return new MoveInstruction();
		else throw new WrongInstructionFormatException();
	}
	
	/**
	 * Devuelve una descripcion de la sintaxis de la instruccion
	 */
	public String getHelp(){
		return ("    MOVE|MOVER");
	}
	
	/**
	 * Configura el contexto actual, es decir, el robot engine y navigation module
	 * @param engine: robot engine
	 * @param navigation: la informacion sobre el juego
	 * @param robotContainer: el inventario del robot
	 * 
	 */
	public void configureContext(RobotEngine engine,NavigationModule navigation,ItemContainer robotContainer){
		this.theEngine = engine;
		this.theNavigation = navigation;
		this.theInventory = robotContainer;
	}
	
	/**
	 * Mueve del lugar actual al siguiente en la direccion actual, la calle debe estar abierta para
	 * poder realizar el movimiento
	 */
	public void execute() throws InstructionExecutionException{
		this.theNavigation.move();
		this.theEngine.addFuel(-5);
		this.theEngine.informaObservadorRobot();
	}

}
