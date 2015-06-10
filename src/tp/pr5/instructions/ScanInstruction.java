package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Jefferson Cárdenas
 * La ejecucion de esta instruccion muestra la informacion del inventario del robot
 * o la completa descripcion de un item con el identificador id, la instruccion funciona si el
 * usuario escribe SCAN o ESCANEAR
 */
public class ScanInstruction implements Instruction{	
	
	private ItemContainer theInventory;
	private NavigationModule theNavigation;
	private RobotEngine theEngine;
	private String id;
	
	/**
	 * Constructora por defecto
	 */
	public ScanInstruction(){
		this.id = "";
	}
	
	/**
	 * Constructora con parametro
	 * @param i identificador del item
	 */
	public ScanInstruction(String i){
		this.id = i;
	}
	
	/**
	 * Parsea la instruccion devolviendo una instancia de ScanInstruction o lanzando una
	 * WrongInstructionFormatException
	 */
	public Instruction parse (String cad) throws WrongInstructionFormatException{
		String[] word = cad.split(" ");
		if (word.length == 1 && (word[0].equalsIgnoreCase("SCAN") || word[0].equalsIgnoreCase("ESCANEAR")) ) 			
				return new ScanInstruction();
		else if (word.length == 2 && (word[0].equalsIgnoreCase("SCAN") || word[0].equalsIgnoreCase("ESCANEAR")) )
				return new ScanInstruction(word[1]);
		else throw new WrongInstructionFormatException();
	}
	
	/**
	 * Devuelve una descripcion de la sintaxis de la instruccion
	 */
	public String getHelp(){
		return ("    SCAN | ESCANEAR [id]");
	}
	
	/**
	 * Configura el contexto actual, es decir, el robot engine y navigation module
	 * @param engine: robot engine
	 * @param navigation: la informacion sobre el juego
	 * @param robotContainer: el inventario del robot
	 */
	public void configureContext(RobotEngine engine,NavigationModule navigation,ItemContainer robotContainer){		
		this.theInventory = robotContainer;
		this.theEngine = engine;
		this.theNavigation = navigation;
	}
	
	/**
	 * Muestra la informacion de un item concreto o del inventario completo del robot
	 */
	public void execute() throws InstructionExecutionException{		
		if (this.id ==""){
			if (this.theInventory.numberOfItems()==0)	this.theEngine.requestError("WALL·E says: My inventory is empty");
			else {
				this.theEngine.saySomething("WALL·E says: I am carrying the following items"+this.theInventory.toString());
			}
		}
		else {
			Item aux = this.theInventory.getItem(this.id);
			if (aux != null) this.theEngine.saySomething(aux.toString());
			else	throw new InstructionExecutionException("WALL·E says: I have not such object");
		}
	}

}
