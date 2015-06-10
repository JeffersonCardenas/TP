package tp.pr5;

import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * Representa un lugar de la ciudad, los lugares estan conectados por las cuatro direcciones
 * (NORTH,SOUTH,EAST,WEST) cada lugar tiene un nombre y una descripcion sobre si misma, esta descripcion se muestra
 * cuando el robot llega al lugar.
 * Un lugar puede representar la nave donde el robot esta a salvo, cuando el robot llega a este sitio
 * la aplicacion termina
 */
public class Place implements PlaceInfo{
	
	//Atributos
	public static final String LINE_SEPARATOR =	System.getProperty("line.separator");
	private String name;
	private boolean isSpaceShip;
	private String description;
	private ItemContainer list_items;
	
	/**
	 * Constructora por defecto
	 */
	public Place(){
		this.name = "Colón";
		this.isSpaceShip = true;
		this.description = "Sometime ago, Spanish people concentrates here to watch football in a big screen.";		
		this.list_items = new ItemContainer();		
	}
	
	/**
	 * Crea el lugar
	 * @param nombre del lugar
	 * @param esNave ¿este lugar es la nave?
	 * @param descripcion del lugar
	 */
	public Place(java.lang.String nombre, boolean esNave, java.lang.String descripcion){
		this.name = nombre;
		this.isSpaceShip = esNave;
		this.description = descripcion;
		this.list_items = new ItemContainer();
	}
	
	/**
	 * ¿Este lugar es la nave?
	 * @return true si el lugar representa la nave
	 */
	public boolean isSpaceship(){
		return this.isSpaceShip;
	}
	
	/**
	 * Sobreescribe el metodo toString, devuelve el nombre del lugar, su descripcion y la lista de items
	 * contenidos en ese lugar
	 */
	public java.lang.String toString(){		
		String cadena = new String(this.name+LINE_SEPARATOR+this.description);		
		
		if (this.list_items.numberOfItems()>0) {			
			cadena = cadena + LINE_SEPARATOR + "The place contains these objects:" + this.list_items.toString();
		}
		else {	
			cadena = cadena + LINE_SEPARATOR + "The place is empty. There are no objects to pick"+LINE_SEPARATOR;
		}
		return cadena;
	}
		
	/**
	 * Intenta coger un item caracterizado por un identificador, si la accion se
	 * realiza el item debe ser eliminado del lugar
	 * @param id identificador del item
	 * @return el item si es que existe, en otro caso el metodo devuelve null
	 */
	public Item pickItem(java.lang.String id){
		return this.list_items.pickItem(id);
	}
	
	/**
	 * Intenta añadir un item al lugar, la operacion puede fallar si el lugar ya contiene el item
	 * @param it el item que va a ser añadido
	 * @return true si y solo si el item puede ser añadido es decir el lugar no contiene un item con el mismo
	 * nombre
	 */
	public boolean addItem(Item it){
		return this.list_items.addItem(it);
	}
	
	/**
	 * Suelta un item en este lugar, la operacion puede fallar devolviendo false
	 * @param it el item que va a ser soltado
	 * @return true si y solo si el item se suelta en el lugar, es decir, un item con
	 * el mismo id no existe en el lugar
	 */
	public boolean dropItem(Item it){
		return this.list_items.addItem(it);
	}
	
	/**
	 * Comprueba si un item se encuentra en el lugar
	 * @param id identificador del item
	 * @return true si y solo si el lugar contiene el item identificado por id
	 */
	public boolean existItem(String id){
		return this.list_items.containsItem(id);
	}
	
	/**
	 * Devuelve el nombre del lugar
	 * @return nombre del lugar
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Devuelve la descripcion del lugar
	 */
	public String getDescription(){
		return this.description;
	}

}
