package tp.pr5.items;

import java.util.List;

/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * Interfaz de los observadores que quieren ser notificados sobre eventos ocurridos en el
 * inventario del robot. El contenedor notificará a su observador de cada cambio en el contenedor
 * (cuando el robot realiza alguna instruccion de tipo PICK o DROP) y cuando un item es borrado
 * del contenedor porque no hay mas unidades. El contenedor tambien notificará cuando el usuario
 * solicite escanear un item o el contenedor entero
 *
 */
public interface InventoryObserver {
	
	/**
	 * Notifica cuando el contenedor ha cambiado
	 * @param inventory Nuevo inventario
	 */
	public abstract void inventoryChange(List<Item> inventory);
	
	/**
	 * Notifica cuando el usuario solicita utilizas la instruccion SCAN sobre el inventario
	 * @param inventoryDescription Informacion sobre el inventario
	 */
	public abstract void inventoryScanned(String inventoryDescription);
	
	/**
	 * Notifica cuando el usuario quiere escanear un item del inventario
	 * @param description Descripcion del item
	 */
	public abstract void itemScanned(String description);
	
	/**
	 * Notifica cuando el item se ha agotado y será removido del inventario. Una invocacion
	 * al metodo inventoryChanged seguirá
	 * @param itemName nombre del item
	 */
	public abstract void itemEmpty(String itemName);

}
