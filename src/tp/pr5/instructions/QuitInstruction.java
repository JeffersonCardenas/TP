package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Jefferson Cárdenas
 * Esta instruccion finaliza la ejecucion, la instruccion funciona cuando el usuario escribe
 * QUIT o SALIR
 */
public class QuitInstruction implements Instruction{
	
	private ItemContainer theInventory;
	private NavigationModule theNavigation;
	private RobotEngine theEngine;
	
	/**
	 * Constructora por defecto
	 */
	public QuitInstruction(){}
	
	/**
	 * Parsea la instruccion devolviendo una instancia de QuitInstruction o lanzando una
	 * WrongInstructionFormatException
	 */
	public Instruction parse (String cad) throws WrongInstructionFormatException{
		if (cad.equalsIgnoreCase("QUIT") || cad.equalsIgnoreCase("SALIR"))
			return new QuitInstruction();
		else throw new WrongInstructionFormatException();	
	}
	
	/**
	 * Devuelve una descripcion de la sintaxis de la instruccion
	 */
	public String getHelp(){
		return ("    QUIT|SALIR");
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
		this.theInventory = robotContainer;
		this.theNavigation = navigation;
	}
	
	/**
	 * Solicita al robot finalizar la ejecucion
	 */
	public void execute() throws InstructionExecutionException{
		this.theEngine.requestQuit();
	}

}
