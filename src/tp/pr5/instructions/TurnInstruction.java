package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Jefferson Cárdenas & Marco Antonio Palacios
 * Gira el robot, la instruccion funciona si el usuario escribe TURN LEFT o RIGHT o GIRAR LEFT o RIGHT
 *
 */
public class TurnInstruction implements Instruction{
	
	private ItemContainer theInventory;
	private NavigationModule theNavigation;
	private RobotEngine theEngine;
	private Rotation rotation;
	
	/**
	 * Constructora por defecto
	 */
	public TurnInstruction(){}
	
	/**
	 * Constructora con argumento
	 * @param r rotacion izquierda o derecha
	 */
	public TurnInstruction(Rotation r){
		this.rotation = r;
	}
	
	/**
	 * Parsea la instruccion devolviendo una instancia de TurnInstruction o lanzando una
	 * WrongInstructionFormatException
	 */
	public Instruction parse (String cad) throws WrongInstructionFormatException{
		String[] word = cad.split(" ");
		if (word.length == 2){
			if ((word[0].equalsIgnoreCase("TURN") || word[0].equalsIgnoreCase("GIRAR")) && word[1].equalsIgnoreCase("LEFT")) return new TurnInstruction(Rotation.LEFT);
			if ((word[0].equalsIgnoreCase("TURN") || word[0].equalsIgnoreCase("GIRAR")) && word[1].equalsIgnoreCase("RIGHT")) return new TurnInstruction(Rotation.RIGHT);
			else throw new WrongInstructionFormatException();
		}
		else throw new WrongInstructionFormatException();
	}
	
	/**
	 * Devuelve una descripcion de la sintaxis de la instruccion
	 */
	public String getHelp(){
		return ("    TURN | GIRAR < LEFT|RIGHT >");
	}
	
	/**
	 * Configura el contexto actual, es decir, el robot engine y navigation module
	 * @param engine: robot engine
	 * @param navigation: la informacion sobre el juego
	 * @param robotContainer: el inventario del robot
	 */
	public void configureContext(RobotEngine engine,NavigationModule navigation,ItemContainer robotContainer){		
		this.theEngine = engine;
		this.theNavigation = navigation;
		this.theInventory = robotContainer;
	}
	
	/**
	 * Gira al robot a izquierda o derecha
	 */
	public void execute() throws InstructionExecutionException{
		this.theNavigation.rotate(rotation);
		this.theEngine.addFuel(-5);
		this.theEngine.informaObservadorRobot();
	}

}
