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
 * La instruccion intenta recoger un item del lugar actual e insertarlo en el inventario del robot,
 * la instruccion funciona cuando el usuario escribe PICK id o COGER id
 */
public class PickInstruction implements Instruction{
	
	private ItemContainer theInventory;
	private NavigationModule theNavigation;
	private RobotEngine theEngine;
	private String id;
	
	/**
	 * Constructora por defecto
	 */
	public PickInstruction(){}
	
	/**
	 * Constructora con argumento
	 * @param i id del item
	 */
	public PickInstruction(String i){
		this.id = i;
	}
	
	/**
	 * Parsea la instruccion devolviendo una instancia de PickInstruction o lanzando una
	 * WrongInstructionFormatException
	 */
	public Instruction parse (String cad) throws WrongInstructionFormatException{
		String[] word = cad.split(" ");
		if (word.length == 2 && (word[0].equalsIgnoreCase("PICK") || word[0].equalsIgnoreCase("COGER")) )
			return new PickInstruction(word[1]);
		else throw new WrongInstructionFormatException();
	}
	
	/**
	 * Devuelve una descripcion de la sintaxis de la instruccion
	 */
	public String getHelp(){
		return ("    PICK|COGER <id>");		
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
		this.theInventory = robotContainer;
		this.theEngine = engine;
	}
	
	/**
	 * El robot añade un item del lugar actual al inventario, lanza excepcion cuando el lugar no
	 * contiene ningun item con esa id o cuando el item ya se encuentra en el inventario
	 */
	public void execute() throws InstructionExecutionException{
		Item aux = this.theNavigation.getCurrentPlace().pickItem(this.id);
		if (aux == null) {
			this.theEngine.requestError("WALL·E says: Ooops, this place has not the object " + this.id);
			throw new InstructionExecutionException();
		}
		else {
			if (this.theInventory.addItem(aux)) this.theEngine.saySomething("WALL·E says: I am happy! Now I have " + this.id);			
			else {
				this.theNavigation.getCurrentPlace().addItem(aux);
				this.theEngine.requestError("WALL·E says: I am stupid! I had already the object " + this.id);
				throw new InstructionExecutionException();
			}
		}
	}

}
