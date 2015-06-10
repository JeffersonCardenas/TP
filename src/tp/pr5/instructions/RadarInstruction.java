package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Jefferson Cárdenas
 * Esta instruccion muestra la descripcion del lugar actual y los items que hay en el,
 * la instruccion funciona si el usuario escribe RADAR
 */
public class RadarInstruction implements Instruction{	
	
	private ItemContainer theInventory;
	private NavigationModule theNavigation;
	private RobotEngine theEngine;
	
	/**
	 * Constructora publica
	 */
	public RadarInstruction(){}
	
	/**
	 * Parsea la instruccion devolviendo una instancia de RadarInstruction o lanzando una
	 * WrongInstructionFormatException
	 */
	public Instruction parse (String cad) throws WrongInstructionFormatException{		
		if (cad.equalsIgnoreCase("RADAR"))
			return new RadarInstruction();
		else throw new WrongInstructionFormatException();
	}
	
	/**
	 * Devuelve una descripcion de la sintaxis de la instruccion
	 */
	public String getHelp(){
		return ("    RADAR");
	}
	
	/**
	 * Configura el contexto actual, es decir, el robot engine y navigation module
	 * @param engine: robot engine
	 * @param navigation: la informacion sobre el juego
	 * @param robotContainer: el inventario del robot
	 * 
	 */
	public void configureContext(RobotEngine engine,NavigationModule navigation,ItemContainer robotContainer){
		this.theNavigation = navigation;
		this.theEngine = engine;
		this.theInventory = robotContainer;
	}
	
	/**
	 * Muestra la descipcion del lugar actual
	 */
	public void execute() throws InstructionExecutionException{		
		System.out.println(this.theNavigation.getCurrentPlace().toString());
	}

}
