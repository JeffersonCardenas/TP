package tp.pr5;

import tp.pr5.items.InventoryObserver;

/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * Controlador del juego, Clase de la que herederan los diferentes controladores del juego
 */
public abstract class Controller {
	
	//Modelo
	protected RobotEngine engine;
	
	/**
	 * Constructor que usa el modelo
	 * @param game referencia al modelo
	 */
	public Controller(RobotEngine game){
		this.engine = game;
	}
	
	/**
	 * Metodo abstracto que pone en funcionamiento el juego, este metodo es diferente
	 * en funcion si el juego se inicia por consola o en una ventana de swing
	 */
	public abstract void startController();
	
	/**
	 * Registra un observador del juego al modelo
	 * @param gameObserver observador que quiere ser registrado
	 */
	public void registerEngineObserver(RobotEngineObserver gameObserver){
		this.engine.addEngineObserver(gameObserver);
	}
	
	/**
	 * Registra un observador del mapa al modelo
	 * @param containerObserver observador que quiere ser registrado
	 */
	public void registerItemContainerObserver(InventoryObserver containerObserver){
		this.engine.addItemContainerObserver(containerObserver);
	}
	
	/**
	 * Registra un observador del jugador al modelo
	 * @param playerObserver observador que quiere ser registrado
	 */
	public void registerRobotObserver(NavigationObserver playerObserver){
		this.engine.addNavigationObserver(playerObserver);
	}

}
