package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Jefferson Cardenas
 * Muestra la ayuda del juego con todas las instrucciones que el robot puede ejecutar,
 * la instruccion funciona si el usuario escribe HELP o AYUDA
 *
 */
public class HelpInstruction implements Instruction{
	//atributos
	private RobotEngine theEngine;
	private ItemContainer theInventory;
	private NavigationModule theNavigation;	
	
	/**
	 * Constructora por defecto
	 */
	public HelpInstruction(){}
	
	/**
	 * Parsea el string devolviendo una instancia de HelpInstruction o lanzando una WrongInstructionFormatException 
	 */
	public Instruction parse (String cad) throws WrongInstructionFormatException{
		if (cad.equalsIgnoreCase("HELP") || cad.equalsIgnoreCase("AYUDA"))
			return new HelpInstruction();
		else throw new WrongInstructionFormatException();
	}
	
	/**
	 * Sintaxis de ayuda
	 */
	public String getHelp(){
		return ("    HELP | AYUDA");
	}
	
	/**
	 * Configuracion del contexto para esta instruccion
	 * @Param engine robot  engine
	 * @param navigation la informacion de juego
	 * @Param robotContainer inventario del robot
	 */
	public void configureContext(RobotEngine engine,NavigationModule navigation,ItemContainer robotContainer){
		this.theEngine = engine;
		this.theInventory = robotContainer;
		this.theNavigation = navigation;
	}
	
	/**
	 * Muestra el string de ayuda de cada instruccion, lo delega a la clase interpreter
	 */
	public void execute() throws InstructionExecutionException{
		this.theEngine.requestHelp();
	}

}
