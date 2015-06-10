package tp.pr5;

/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * Interfaz de los observadores que quieren ser notificados sobre eventos relacionados con
 * el modulo de navegación, las clases que implementen esta interfaz seran informadas
 * cuando el robot cambie su direccion, cuando llegue al lugar, cuando el lugar es modificado
 * (porque el robot ha cogido o soltado un item) o cuando el usuario solicita usar el radar
 *
 */
public interface NavigationObserver {
	
	/**
	 * Notifica que la direccion del robot ha cambiado
	 * @param newHeading nueva direccion del robot
	 */
	public abstract void headingChanged(Direction newHeading);
	
	/**
	 * Notifica que el modulo de navegacin ha sido inicializado
	 * @param initialPlace el lugar donde el robot comienza la simulacion
	 * @param heading direccion inicial del robot
	 */
	public abstract void initNavigationModule(PlaceInfo initialPlace,Direction heading);
	
	/**
	 * Notifica que el lugar donde esta el robot ha cambiado(porque el robot
	 * ha cogido o soltado un item)
	 * @param placeDescription
	 */
	public abstract void placeHasChanged(PlaceInfo placeDescription);
	
	/**
	 * Notifica que el usuario solicita realizar la instruccion de tipo RADAR
	 * @param placeDescription
	 */
	public abstract void placeScanned(PlaceInfo placeDescription);
	
	/**
	 * Notifica que el robot ha llegado al lugar
	 * @param heading direccion de movimiento del robot
	 * @param place el lugar donde el robot llega
	 */
	public abstract void robotArrivesAtPlace(Direction heading,PlaceInfo place);

}
