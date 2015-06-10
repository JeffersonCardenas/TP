package tp.pr5;

/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * PlaceInfo define una interfaz no modificable sobre Place, es empleada por las clases que
 * necesitan acceder a la informacion contenida en Place pero que no pueden modificarla
 * por si mismas
 */
public interface PlaceInfo {
	
	/**
	 * Devuelve la descripcion del lugar
	 * @return String que representa la descripcion del lugar
	 */
	public abstract String getDescription();
	
	/**
	 * Devuelve el nombre del lugar
	 * @return String que representa el nombre del lugar
	 */
	public abstract String getName();
	
	/**
	 * Este metodo informa si es el Place representa la nave del robot
	 * @return true si el lugar representa su nave
	 */
	public abstract boolean isSpaceship();

}
