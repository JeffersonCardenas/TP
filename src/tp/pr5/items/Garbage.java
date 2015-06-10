package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * 
 * @author Jefferson Cárdenas
 * La basura es un tipo de item que genera material reciclado despues de usarlo, la basura
 * solo se usa una vez, despues debe ser eliminada del inventario
 */
public class Garbage extends Item {
	
	private int recycledMaterial;	
	private boolean isUsed;
	
	/**
	 * Garbage Constructor
	 * @param id
	 * @param description
	 * @param recycledMat cantidad de material reciclado que el item genera
	 */
	public Garbage(java.lang.String id, java.lang.String description, int recycledMat){
		super(id,description);
		this.recycledMaterial = recycledMat;
		this.isUsed = false;
	}
	
	/**
	 * La basura solo se puede utilizar una vez
	 */
	public boolean canBeUsed(){
		return !(this.isUsed);
	}
	
	/**
	 * La basura genera material reciclado para el robot	
	 */
	public boolean use(RobotEngine r, NavigationModule nav){
		boolean usado = false;
		if(this.canBeUsed()){
			r.addRecycledMaterial(this.recycledMaterial);
			this.isUsed = true;
			r.saySomething("   * My power is "+r.getFuel());
			r.saySomething("   * My recycled material is: "+r.getRecycledMaterial());
			usado = true;
		}
		return usado;
	}
	
	/**
	 * Genera un string con la descripcion del item
	 */
	public java.lang.String toString(){
		return (super.toString()+this.recycledMaterial);
	}

}
