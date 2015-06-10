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
 * La instruccion para utilizar el item, esta instruccion funciona cuando el usuario escribe
 * OPERATE id u OPERAR id
 */
public class OperateInstruction implements Instruction{
	
	private RobotEngine theEngine;
	private NavigationModule theNavigation;
	private ItemContainer theInventory;
	private String id;
	
	/**
	 * Constructora por defecto
	 */
	public OperateInstruction(){}
	
	/**
	 * Constructora con argumento
	 * @param i id del item
	 */
	public OperateInstruction(String i){
		this.id = i;
	}
	
	/**
	 * Parsea la instruccion devolviendo una instancia de OperateInstruction o lanzando una
	 * WrongInstructionFormatException
	 */
	public Instruction parse (String cad) throws WrongInstructionFormatException{
		String[] word = cad.split(" ");
		if (word.length == 2 && (word[0].equalsIgnoreCase("OPERATE") || word[0].equalsIgnoreCase("OPERAR")) )
			return new OperateInstruction(word[1]);
		else throw new WrongInstructionFormatException();
	}
	
	/**
	 * Devuelve una descripcion de la sintaxis de la instruccion
	 */
	public String getHelp(){
		return ("    OPERATE|OPERAR <ID>");		
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
	 * El robot utiliza el item requerido, lanza excepcion cuando en el inventario no se encuentra
	 * el item o cuando el item no puede ser utilizado
	 */
	public void execute() throws InstructionExecutionException{
		if (this.theInventory.numberOfItems()>0){
			Item aux = this.theInventory.getItem(this.id);
			if (aux!=null)	{
				if (!aux.use(this.theEngine, this.theNavigation))	throw new InstructionExecutionException();
				//else this.theEngine.muestraMensaje("");
				if (!aux.canBeUsed()) {
					this.theEngine.requestError("WALL·E says: What a pity! I have no more " + aux.getId() + " in my inventory");
					aux = this.theInventory.pickItem(this.id);					
				}
			this.theEngine.informaObservadorRobot();	
			}			
			else {
				this.theEngine.requestError("WALL·E says: I have problems using the object " + this.id);
				throw new InstructionExecutionException();
			}
		}
		else {
			this.theEngine.requestError("WALL·E says: I have problems using the object " + this.id);
			throw new InstructionExecutionException();
		}		
	}

}
