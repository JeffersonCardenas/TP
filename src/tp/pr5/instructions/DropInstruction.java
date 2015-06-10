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
 * Esta instruccion suelta un item en el lugar actual y lo pone en el inventario del robot,
 * la instruccion funciona si el usuario escribe DROP o SOLTAR
 */
public class DropInstruction implements Instruction{
	
	private ItemContainer theInventory;
	private NavigationModule theNavigation;
	private RobotEngine theEngine;
	private String id;
	
	/**
	 * Constructora por defecto
	 */
	public DropInstruction(){}
	
	/**
	 * Constructora con parametro
	 * @param i identifiacion del item
	 */
	public DropInstruction(String i){
		this.id = i;
	}
	
	/**
	 * Parsea el string devolviendo una instancia de DropInstruction o lanzando WrongInstructionFormatException
	 */
	public Instruction parse (String cad) throws WrongInstructionFormatException{
		String[] word = cad.split(" ");
		if (word.length == 2 && (word[0].equalsIgnoreCase("DROP") || word[0].equalsIgnoreCase("SOLTAR") ) )
			return new DropInstruction(word[1]);
		else throw new WrongInstructionFormatException();
	}
	
	/**
	 * La ayuda de la instruccion
	 * @Return la sintaxis de DROP <id>
	 */
	public String getHelp(){
		return ("    DROP| SOLTAR <id>");		
	}
	
	/**
	 * Configura el contexto actual, es decir, el robot engine y navigation module
	 * @param engine: robot engine
	 * @param navigation: la informacion sobre el juego
	 * @param robotContainer: el inventario del robot
	 * 
	 */
	public void configureContext(RobotEngine engine,NavigationModule navigation,ItemContainer robotContainer){		
		this.theInventory = robotContainer;
		this.theNavigation = navigation;
		this.theEngine = engine;
	}
	
	/**
	 * El robot suelta un item de su inventario en el lugar actual (si existe), lanza una excepcion si el
	 * inventario del robot no contiene el item con esa id o cuando en ese lugar ya existe ese item
	 */
	public void execute() throws InstructionExecutionException{
		if (this.theInventory.numberOfItems() > 0){
			Item aux = this.theInventory.pickItem(this.id);
			if (aux != null){
				if (this.theNavigation.getCurrentPlace().dropItem(aux)){
					this.theEngine.saySomething("Great! I have dropped "+this.id);
				}
				else {
					this.theInventory.addItem(aux);
					throw new InstructionExecutionException();					
				}
			}
			else {
				this.theEngine.requestError("You do not have any "+this.id);
				throw new InstructionExecutionException();
			}
		}
		else {
			this.theEngine.requestError("WALL·E says: my inventory is empty!");
			throw new InstructionExecutionException();
		}
	}

}
