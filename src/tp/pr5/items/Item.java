package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * 
 * @author Jefferson Cárdenas
 * Superclase de cada tipo de Item, contiene la informacion comun para todos los items
 * y define la interfaz que debe coincidir con la de los items
 */
public abstract class Item implements Comparable<Item>{
	
	protected java.lang.String identificacion;
	protected java.lang.String descripcion;
	
	/**
	 * Constructora por defecto
	 */
	public Item(){
		this.descripcion = "";
		this.identificacion = "";
	}
	
	/**
	 * Construye un item con una id y una descripcion dadas
	 * @param id
	 * @param description
	 */
	public Item(java.lang.String id, java.lang.String description){
		this.descripcion = description;
		this.identificacion = id;
	}
	
	/**
	 * Comprueba si un item puede ser utilizado, las subclases deben implementar este metodo
	 * @return true si el item puede ser usado
	 */
	public abstract boolean canBeUsed();
	
	/**
	 * Intenta usar el item con un robot en un lugar dado, las subclases deben implementar este metodo
	 * @param r el robot que usa el metodo
	 * @param nav el lugar donde se usa el item
	 * @return true si la accion se realiza en otro caso false
	 */
	public abstract boolean use(RobotEngine r,NavigationModule nav);
	
	/**
	 * Devuelve la id del item
	 * @return id
	 */
	public java.lang.String getId(){
		return this.identificacion;
	}
	
	/**
	 * Devuelve la descripcion del item
	 * @return descripcion
	 */
	public String getDescription(){
		return this.descripcion;
	}
	
	/**
	 * Genera un string con la descripcion del item
	 */
	public java.lang.String toString(){
		return ("WALL·E says: "+this.identificacion+": "+this.descripcion);
	}
	
	/**
	 * Sobreescritura del metodo equals
	 */
	public boolean equals(Object o){
		boolean value = true;
		if (o == null && !(o instanceof Item)) value=false;
		if (o == this) value=true;
		Item it = (Item) o;
		if (identificacion == null || !(identificacion.equalsIgnoreCase(it.identificacion))) value=false;
		return value;
	}
	
	/**
	 * Sobreescritura del metodo hashCode
	 */
	public int hashCode(){
		int result = 17;
		result = 31 * result + (identificacion != null ? identificacion.hashCode() : 0);
		return result;
	}
	
	/**
	 * Implementacion del metodo compareTo para los items
	 */
	public int compareTo(Item it){		
		int result = 1;
		if (it != null)	result = this.identificacion.compareToIgnoreCase(it.identificacion);
		return result;
	}

}
