package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * 
 * @author Jefferson Cárdenas
 * Un item que representa combustible, este item puede usarse al menos una vez y provee energia al robot
 * cuando el item se usa un determinado numero de veces debe ser eliminado del inventario
 */
public class Fuel extends Item {
	
	private int power;
	private int times;
	
	/**
	 * 	Constructor del item fuel
	 * @param id
	 * @param description
	 * @param poder cantidad de energia que el item proporciona al robot
	 * @param tiempo numerod de veces que el robot puede usar el item
	 */
	public Fuel(java.lang.String id, java.lang.String description, int poder, int tiempo){
		super(id,description);
		this.power = poder;
		this.times = tiempo;
	}
	
	/**
	 * Puede usarse la cantidad de veces que este configurada para ello
	 */
	public boolean canBeUsed(){
		boolean usado = false;
		if (this.times > 0) usado = true;
		return usado;
	}
	
	/**
	 * Usar fuel da energia al robot (si puede ser usado)
	 */
	public boolean use(RobotEngine r, NavigationModule nav){
		boolean usado = false;
		if (this.canBeUsed()){
			r.addFuel(this.power);
			this.times--;
			r.saySomething("   * My power is "+r.getFuel());
			r.saySomething("   * My recycled material is: "+r.getRecycledMaterial());
			usado = true;
		}
		return usado;
	}
	
	/**
	 * genera un string con la descripcion del item
	 */
	public java.lang.String toString(){
		return (super.toString()+"// power = "+this.power+", times = "+this.times);
	}

}
