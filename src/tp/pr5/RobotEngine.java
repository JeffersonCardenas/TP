package tp.pr5;

import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.ItemContainer;
import tp.pr5.items.InventoryObserver;
import java.util.Iterator;

/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * Esta clase controla los movimientos del robot procesando las instrucciones introducidas por teclado,
 * termina la ejecucion cuando el robot llega a su nave, se queda sin combustible o recibe una instruccion quit
 * Esta clase tambien es la responsable de actualizar los niveles de combustible y el material reciclado de
 * acuerdo a las acciones que el robot realiza en la ciudad
 * Tambien contiene un inventario donde el robot guarda los items que va recogiendo por la ciudad
 */
public class RobotEngine extends Observable<RobotEngineObserver>{	
	//Atributos
	private int energy;
	private int recycled_material;
	private ItemContainer inventory;
	private NavigationModule controlRobot;
	private boolean finJuego;
	private boolean quit;
	
	/**
	 * Crea el motor del robot en un lugar inicial, mirando a una direccion inicial y con un mapa de la ciudad
	 * inicialmente el robot no tiene ningun item ni material reciclado pero tiene una cantidad inicial
	 * de energia de 50
	 * @param cityM la ciudad por la que el robot se mueve
	 * @param initialPlace el lugar donde el robot empieza
	 * @param dir la direccion inicial a la que el robot mira
	 */
	public RobotEngine(City cityM, Place initialPlace, Direction dir){			
		this.energy = 100;
		this.recycled_material = 0;
		this.inventory = new ItemContainer();		
		this.controlRobot = new NavigationModule(cityM,initialPlace);
		this.controlRobot.initHeading(dir);
		this.finJuego = false;
		this.quit = false;
	}
	
	/**
	 * Ejecuta una instruccion, la instruccion debe ser configurada en su contexto antes
	 * de ejecutarla, controla el fin de la simulacion
	 * @param c Instruccion que va a ser ejecutada
	 */
	public void communicateRobot(Instruction c){
		if (c!=null){
			try{
				c.configureContext(this,this.controlRobot,this.inventory);
				c.execute();
				if (isOver()) requestQuit();
			}
			catch (InstructionExecutionException e){
				if (e.getMessage()!=null) this.requestError(e.getMessage());
			}
		}
		else this.requestError("WALL·E says: I dont understand");
	}
	
	/**
	 * Añade una cantidad de energia al robot (puede ser negativa) poniendola a cero en ese caso
	 * @param fuel cantidad de energia añadida al robot
	 */
	public void addFuel(int fuel){
		this.energy+=fuel;
		if (this.energy<=0){
			this.energy = 0;
		}
	}
	
	/**
	 * Incrementa la cantidad de material reciclado del robot
	 * @param weight cantidad de material reciclado
	 */
	public void addRecycledMaterial(int weight){
		this.recycled_material+=weight;
	}
	
	/**
	 * Accesora
	 * @return Devuelve la cantidad de energia del robot
	 */
	public int getFuel(){
		return this.energy;
	}
	
	/**
	 * Accesora
	 * @return Devuelve la cantidad de material reciclado del robot
	 */
	public int getRecycledMaterial(){
		return this.recycled_material;
	}
	
	/**
	 * Solicita el fin de la simulacion
	 */
	public void requestQuit(){
		this.finJuego = true;
		boolean conFuel = (getFuel()>0);
		if (!this.quit){
			Iterator<RobotEngineObserver> it = observadores.iterator();			
			if (conFuel && !this.controlRobot.atSpaceship()){
				while (it.hasNext()){
					it.next().communicationCompleted();
				}
			}
			else{			
				while (it.hasNext()){
					it.next().engineOff(conFuel);
				}
			}
		}
		this.quit = true;
	}
	
	/**
	 * Muestra toda la informacion sobre todas las posibles instrucciones
	 */
	public void requestHelp(){
		Iterator<RobotEngineObserver> it = observadores.iterator();
		while (it.hasNext()){
			it.next().communicationHelp(Interpreter.interpreterHelp());
		}
	}
	
	//Metodos Practica 5
	/**
	* Añade un EngineObserver al modelo
	* @param observer El observador que quiere ser registrado
	*/
	public void addEngineObserver(RobotEngineObserver observer){
		super.agregarObservador(observer);
	}
		
	/**
	* Añade un ItemContainerObserver al modelo
	* @param observer El observador que quiere ser registrado
	*/
	public void addItemContainerObserver(InventoryObserver observer){
		this.inventory.agregarObservador(observer);
	}
		
	/**
	* Añade un NavigationObserver al modelo
	* @param robotObserver El observador que quiere ser registrado
	*/
	public void addNavigationObserver(NavigationObserver robotObserver){
		this.controlRobot.agregarObservador(robotObserver);
	}
		
	/**
	* Comprueba si la simulacion ha terminado
	* @return true si el juego ha finalizado
	*/
	public boolean isOver(){
		return (this.energy <= 0 || this.controlRobot.atSpaceship() || this.finJuego);
	}
		
	/**
	* Solicita al motor que informe que un error se ha producido
	* @param msg Mensaje de error
	*/
	public void requestError(String msg){
		Iterator<RobotEngineObserver> it = observadores.iterator();
		while (it.hasNext()){
			it.next().raiseError(msg);
		}
	}
		
	/**
	* Solicita al motor que informe a los observadores que la simulacion ha comenzado
	*/
	public void requestStart(){
		this.controlRobot.informaObservadoresNavigation();
		this.informaObservadorRobot();
	}
		
	/**
	 * Solicita al motor decir algo
	 * @param message
	 */
	public void saySomething(String message){
		Iterator<RobotEngineObserver> it = observadores.iterator();
		while (it.hasNext()){
			it.next().robotSays(message);
		}
	}
	
	/**
	 * Informa a los observadores de un cambio en los atributos del robot
	 */
	public void informaObservadorRobot(){
		Iterator<RobotEngineObserver> it = this.observadores.iterator();
		while (it.hasNext()){
			it.next().robotUpdate(energy, recycled_material);
		}
	}
	
	/**
	 * Actualiza el inventario de wall-e
	 */
	public void actualizaInventario(){
		this.inventory.informaObservadorInventory();
	}
	
}
