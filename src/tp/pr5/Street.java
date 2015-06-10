package tp.pr5;

import tp.pr5.items.CodeCard;

/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * Una calle une dos lugares A y B en una direccion, las calles son de doble sentido, es decir,
 * si B es al NORTH de A entonces A es al SOUTH de B
 * Algunas calles estan cerradas y necesitan un codigo para abrirlas
 */
public class Street {	
	//Atributos
	private Place source;
	private Place target;
	private Direction direction;
	private boolean isOpen;
	private java.lang.String code;

	/**
	 * Constructora por defecto
	 */
	public Street(){
		this.source = new Place();
		this.direction = Direction.NORTH;
		this.target = new Place();		
	}
	
	/**
	 * Crea una calle abierta y no necesita codigo para abrirla o cerrarla
	 * @param origen lugar de origen
	 * @param direccion representa como esta colocado el origen del destino
	 * @param destino lugar de destino
	 */
	public Street(Place origen, Direction direccion, Place destino){
		this.source = origen;
		this.direction = direccion;
		this.target = destino;
		this.isOpen = true;
		this.code = "";
	}
	
	/**
	 * Crea una calle que tiene un codigo para abrirla o cerrarla
	 * @param origen lugar de origen
	 * @param direccion representa como esta colocado el origen del destino
	 * @param destino lugar de destino
	 * @param esAbierto determina si la calle esta abierta o cerrada
	 * @param cod codigo que abre o cierra la calle
	 */
	public Street(Place origen, Direction direccion, Place destino, boolean esAbierto, java.lang.String cod){
		this.source = origen;
		this.direction = direccion;
		this.target = destino;
		this.isOpen = esAbierto;
		this.code = cod;
	}
	
	/**
	 * comprueba si una calle sale de lugar y una direccion, las calles son de doble sentido
	 * @param place el lugar para comprobar
	 * @param whichDirection la direccion usada
	 * @return true si la calle viene del lugar de entrada
	 */
	public boolean comeOutFrom(Place place,Direction whichDirection){
		return (place.equals(source) && whichDirection.equals(direction)) || 
				(place.equals(target) && this.direction.equals(Direction.SOUTH) && whichDirection.equals(Direction.NORTH) ) ||
				(place.equals(target) && this.direction.equals(Direction.NORTH) && whichDirection.equals(Direction.SOUTH) ) ||
				(place.equals(target) && this.direction.equals(Direction.EAST) && whichDirection.equals(Direction.WEST) ) ||
				(place.equals(target) && this.direction.equals(Direction.WEST) && whichDirection.equals(Direction.EAST) );
	}
		
	/**
	 * Devuelve el lugar al otro lado de whereAmI, este metodo no tiene en cuenta si la calle esta
	 * abierta o cerrada
	 * @param whereAmI el lugar donde estoy
	 * @return devuelve el Place al otro lado de la calle(incluso si la calle esta cerrada),
	 * devuelve null si whereAmI no pertenece a la calle
	 */
	public Place nextPlace(Place whereAmI){
		Place aux = null;
		if (whereAmI == this.source)	aux = this.target;
		else if (whereAmI == this.target)	aux = this.source;
		return aux;
	}
	
	/**
	 * Comprueba si la calle esta abierta o cerrada
	 * @return true si la calle esta abierta y falso cuando esta cerrada
	 */
	public boolean isOpen(){
		return this.isOpen;
	}
	
	/**
	 * Intenta abrir una calle usando una code card, el codigo debe coincidir para completar la accion
	 * @param card una codecard para abrir la calle
	 * @return true si la accion se ha realizado
	 */
	public boolean open(CodeCard card){
		if (this.code.compareTo(card.getCode()) == 0) {
			this.isOpen = true;
			return true;
		}
		else return false;
	}
	
	/**
	 * Intenta cerrar una calle usando una code card, el código debe coincidir para completar la accion
	 * @param card una codecard para cerrar la calle
	 * @return true si la accion se ha realizado
	 */
	public boolean close(CodeCard card){
		if (this.code.compareTo(card.getCode()) == 0) {
			this.isOpen = false;
			return true;
		}
		else return false;
	}

}
