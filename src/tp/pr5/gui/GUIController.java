package tp.pr5.gui;

import tp.pr5.Controller;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.*;

/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * Controlador utilizado cuando la aplicacion ha sido configurada en modo swing.
 * Es responsable por solicitar que el robot engine empiece y redirige las acciones
 * realizadas por el usuario en la ventana del robot engine
 *
 */
public class GUIController extends Controller{
	
	//Constructora
	public GUIController(RobotEngine robot){
		super(robot);
	}
	
	/**
	 * Inicia la ejecucion del modo swing
	 */
	public void startController(){
		this.engine.requestStart();
	}
	
	/**
	 * Solicita al robot engine realizar la instruccion
	 * @param instruccion
	 */
	public void ejecutaInstruccion(Instruction instruccion){
		this.engine.communicateRobot(instruccion);
	}
	
	/**
	 * Solicita al robot engine finalizar la simulacion
	 */
	public void requestEnd(){
		this.engine.requestQuit();
	}
	
	/**
	 * Agrega los diferentes tipos de observadores que hay en la interfaz grafica
	 * @param ventana
	 * @param robotPanel
	 * @param navigationPanel
	 * @param infoPanel
	 */
	public void agregaObservadores(MainWindow ventana,RobotPanel robotPanel,NavigationPanel navigationPanel,InfoPanel infoPanel){
		//Ventana Principal
		super.registerEngineObserver(ventana);
		
		//Robot Panel
		super.registerEngineObserver(robotPanel);
		super.registerItemContainerObserver(robotPanel);
		
		//Navigation Panel
		super.registerRobotObserver(navigationPanel);
		
		//Info Panel
		super.registerEngineObserver(infoPanel);
		super.registerRobotObserver(infoPanel);
		super.registerItemContainerObserver(infoPanel);
	}
	
	/**
	 * Solicita actualizar el inventario de wall-e para mostrar en la tabla los elementos del
	 * inventario
	 */
	public void requestUpdateInventory(){
		this.engine.actualizaInventario();
	}

}
