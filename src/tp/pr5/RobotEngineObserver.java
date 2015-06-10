package tp.pr5;

/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * Interfaz de los observadores que quieren ser notificados sobre eventos que ocurren en el
 * RobotEngine. El RobotEngine notificará los cambios en el robot (fuel y material reciclado),
 * informará sobre los problemas de comunicación, errores y cuando el robot quiere decir algo.
 * Finalmente notificará cuando los usuarios soliciten ayuda y cuando el robot se desconecte
 * (porque se ha quedado sin fuel o ha llegado a su nave)
 *
 */
public interface RobotEngineObserver {
	
	/**
	 * El RobotEngine informa de que la comunicacion se ha terminado
	 */
	public abstract void communicationCompleted();
	
	/**
	 * El RobotEngine informa de que la ayuda ha sido solicitada
	 * @param help String con la informacion de ayuda
	 */
	public abstract void communicationHelp(String help);
	
	/**
	 * El RobotEngine informa de que el robot se desconecta
	 * (porque ha llegado a su nave o se ha quedado sin combustible)
	 * @param atShip true si el robot ha llegado a su nave o false
	 * si se ha quedado sin combustible
	 */
	public abstract void engineOff(boolean atShip);
	
	/**
	 * El Robot Engine informa de un error
	 * @param msg mensaje de error
	 */
	public abstract void raiseError(String msg);
	
	/**
	 * El RobotEngine informa de que el robot quiere decir algo
	 * @param message mensaje del robot
	 */
	public abstract void robotSays(String message);
	
	/**
	 * El RobotEngine informa que el fuel y/o la cantidad de material reciclado ha cambiado
	 * @param fuel cantidad actual de fuel
	 * @param recycledMaterial cantidad actual de material reciclado
	 */
	public abstract void robotUpdate(int fuel, int recycledMaterial);

}
