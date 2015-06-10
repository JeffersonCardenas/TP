package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Street;

/**
 * 
 * @author Jefferson Cárdenas
 * Una code card puede abrir o cerrar una calle, la tarjeta contiene un codigo que debe coincidir
 * con el codigo de la calle para realizar la accion
 */
public class CodeCard extends Item {
	
	private java.lang.String code;
	
	/**
	 * Constructor de code card
	 * @param id nombre de la tarjeta
	 * @param description descripcion de la tarjeta
	 * @param codigo secreto guardado en la tarjeta
	 */
	public CodeCard(java.lang.String id, java.lang.String description, java.lang.String codigo){
		super(id,description);
		this.code = codigo;
	}
	
	/**
	 * Una code card siempre se puede usar, desde que el robot tiene un code card nunca la pierde
	 */
	public boolean canBeUsed(){
		return true;
	}
	
	/**
	 * Si el robot se encuentra en el lugar que contiene una calle en la direccion en la que esta mirando
	 * entonces la calle se abre o se cierra si el codigo de la tarjeta y el de la calle coinciden
	 */
	public boolean use(RobotEngine r, NavigationModule nav){
		Street aux = nav.getHeadingStreet();
		boolean usado = false;
		if (aux != null){
			if (aux.isOpen()) usado = aux.close(this);
			else usado = aux.open(this);
		}
		return usado;
	}
	
	/**
	 * obtiene el codigo guardado en la tarjeta
	 * @return String que representa el codigo
	 */
	public java.lang.String getCode(){
		return this.code;
	}

}
