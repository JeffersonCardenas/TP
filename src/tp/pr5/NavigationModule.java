package tp.pr5;

import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.items.Item;
import java.util.Iterator;

/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * Esta clase se encarga del movimiento del robot, contiene la ciudad donde el robot busca la basura,
 * el lugar actual donde se encuentra el robot y la direccion actual de robot, contiene metodos para 
 * los diferentes moviemientos del robot y para recoger y soltar items en el lugar actual
 */
public class NavigationModule extends Observable<NavigationObserver>{
	private City cityMap;
	private Place initialPlace;
	private Direction direction;
	
	/**
	 * Constructora, necesita el mapa de la ciudad y el lugar inicial
	 * @param ciudad mapa de la ciudad
	 * @param lugarInicial Place inicial para el robot
	 */
	public NavigationModule(City ciudad,Place lugarInicial){
		this.cityMap = ciudad;
		this.initialPlace = lugarInicial;
	}	
	
	/**
	 * Comprueba si el robot ha llegado a la nave
	 * @return true si y solo si el lugar actual es la nave
	 */
	public boolean atSpaceship(){
		return this.initialPlace.isSpaceship();
	}
	
	/**
	 * Actualiza la direccion actual del robot de acuerdo a la rotacion
	 * @param rotation izquierda (left) o derecha (right)
	 */
	public void rotate(Rotation rotation){
		if (rotation == Rotation.RIGHT) this.direction = Direction.turnRight(this.direction);
		else this.direction = Direction.turnLeft(this.direction);
		Iterator<NavigationObserver> it = observadores.iterator();
		while(it.hasNext()){
			it.next().headingChanged(direction);
		}
	}
	
	/**
	 * Este metodo intenta mover al robot siguiendo la direccion actual, si el moviemiento no es
	 * posible porque no existe una calle o la calle esta cerrada entonces lanza una excepcion,
	 * en otro caso el lugar actual se actualiza de acuerdo al movimiento
	 * @throws InstructionExecutionException
	 */
	public void move() throws InstructionExecutionException {
		Street aux = this.getHeadingStreet();
		if (aux != null){
			if (aux.isOpen()){
				this.initialPlace = aux.nextPlace(this.getCurrentPlace());
				Iterator<NavigationObserver> it = observadores.iterator();
				while (it.hasNext()){
					it.next().robotArrivesAtPlace(direction, initialPlace);
				}
			}
			else throw new InstructionExecutionException("WALL·E says: Arrggg, there is a street but it is closed!");
		}
		else throw new InstructionExecutionException("WALL·E says: There is no street in direction " + this.direction);
	}
	
	/**
	 * Intenta recoger un item del lugar actual caracterizado por un identificador,
	 * si se completa la accion el item es eliminado del lugar actaul
	 * @param id el identificador del item
	 * @return el item si se encuentra en el lugar en otro caso devuele null
	 */
	public Item pickItemFromCurrentPlace(java.lang.String id) {
		return this.initialPlace.pickItem(id);
	}
	
	/**
	 * Suelta un item en el lugar actual se asume que no existe ningun otro item con el
	 * mismo nombre, en otro caso la conducta no esta definida
	 * @param it identificador del item que va a ser soltado
	 */
	public void dropItemAtCurrentPlace(Item it) {
		this.initialPlace.dropItem(it);
	}
	
	/**
	 * Comprueba si hay un item con la identificacion dada en el lugar actual
	 * @param id identificador del item que estamos buscando
	 * @return true si y solo si un item con esta id se encuentra en lugar actual
	 */
	public boolean findItemAtCurrentPlace(java.lang.String id) {
		return this.initialPlace.existItem(id);
	}
	
	/**
	 * Inicializa la direccion actual de acuerdo al parametro
	 * @param heading nueva direccion para el robot
	 */
	public void initHeading(Direction heading) {
		this.direction = heading;
	}
	
	/**
	 * Proporciona el obsevador con la informacion(descripcion + inventario) del lugar actual
	 */
	public void scanCurrentPlace(){
		Iterator<NavigationObserver> it = observadores.iterator();
		while(it.hasNext()){
			it.next().placeScanned(initialPlace);
		}
	}
	
	/**
	 * Devuelve la calle de enfrente del robot
	 * @return la calle a la que el robot esta mirando o null si no hay calle en esa direccion
	 */
	public Street getHeadingStreet(){
		return this.cityMap.lookForStreet(this.initialPlace, this.direction);
	}
	
	/**
	 * Devuelve la direccion del robot
	 * @return la direccion a la que el robot esta mirando
	 */
	public Direction getCurrentHeading() {
		return this.direction;
	}
	
	/**
	 * Devuelve el lugar en el que se encuentra el robot
	 * @return el lugar actual
	 */
	public Place getCurrentPlace() {
		return this.initialPlace;
	}
	
	//Metodos Utiles
	/**
	 * Informa a los observadores de navigation que la ejecucion ha empezado
	 */
	public void informaObservadoresNavigation(){
		Iterator<NavigationObserver> it = observadores.iterator();
		while (it.hasNext()){
			it.next().initNavigationModule(initialPlace, direction);
		}
	}

}
