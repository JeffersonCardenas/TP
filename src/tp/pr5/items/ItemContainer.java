package tp.pr5.items;

import java.util.Collection;
import java.util.List;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.ArrayList;
import tp.pr5.Observable;

/**
 * 
 * @author Jefferson Cárdenas
 * Implementacion del ItemContainer mediante un TreeMap, esta clase representa un contenedor de items
 */
public class ItemContainer extends Observable<InventoryObserver>{
	
	public static final String LINE_SEPARATOR =	System.getProperty("line.separator");
	private TreeMap<String,Item> contenedor;
	private List<Item> itemsTabla;
	
	/**
	 * Crea un contenedor vacio
	 */
	public ItemContainer(){
		this.contenedor = new TreeMap<String,Item>();
		itemsTabla = new ArrayList<Item>(numberOfItems());
	}
	
	/**
	 * Comprueba si el item existe en el contenedor
	 * @param id nombre del item
	 * @return true si el inventario contiene un item con ese nombre
	 */
	public boolean containsItem (java.lang.String id){
		return this.contenedor.containsKey(id);
	}
	
	/**
	 * Devuelve el tamaño del contenedor
	 * @return el numero de items en el contenedor
	 */
	public int numberOfItems(){
		return this.contenedor.size();
	}
	
	/**
	 * Añade un item al contenedor, la operacion puede fallar devolviendo false
	 * @param item que va a ser añadido
	 * @return true si y solo si el item es añadido, dos items con el mismo identificador
	 * no existen en el contenedor 
	 */
	public boolean addItem(Item item){
		boolean added = false;
		if (!this.containsItem(item.getId()))	{
			this.contenedor.put(item.getId(), item);
			added = true;
			//this.informaObservadorInventory();//para avisar de que el inventario ha cambiado
		}
		return added;
	}
	
	/**
	 * Devuelve el item del contenedor de acuerdo a su nombre
	 * @param id nombre de item
	 * @return item con la id o null si no existe en el container
	 */
	public Item getItem(java.lang.String id){		
		return this.contenedor.get(id);
	}
	
	/**
	 * Devuelve y elimina un item del inventario, esta operacion puede fallar devolviendo null
	 * @param id nombre del item
	 * @return un item si y solo si el item con el id existe en el inventario en otro caso devuelve null
	 */
	public Item pickItem(java.lang.String id){		
		Item aux = this.getItem(id);
		this.contenedor.remove(id);
		//this.informaObservadorInventory();//para avisar de que el inventario ha cambiado
		return aux;
	}
	
	/**
	 * Sobreescritura del metodo toString,
	 * Devuelve un string con la informacion sobre los items contenidos en el inventario,
	 * los items deben aparecer ordenados por nombre
	 */
	public java.lang.String toString(){		
		String cadena = new String();
		Collection<Item> lista = this.contenedor.values();
		Iterator<Item> it = lista.iterator();
		while (it.hasNext()){
			cadena = cadena + LINE_SEPARATOR + "   " + it.next().identificacion;
		}
		return cadena;
	}
	
	/**
	 * Metodo llamado por OperateInstruction cuando in item guardado en el inventario es utilizado
	 * con exito. Despues se comprueba si el item puede volver a ser utilizado nuevamente en el
	 * futuro, si no es posible por que no hay mas elementos del item entonces es eliminado
	 * del inventario (y el metodo notifica a los observadores)
	 * @param item
	 */
	public void useItem(Item item){
		if (this.containsItem(item.getId()) && !item.canBeUsed())	this.contenedor.remove(item);
	}
	
	//Metodos practica 5
	/**
	 * Solicita escanear el contenido del inventario
	 */
	public void requestScanCollection(){
		Iterator<InventoryObserver> it = observadores.iterator();
		while (it.hasNext()){
			it.next().inventoryScanned(toString());
		}
	}
	
	/**
	 * Solicita mostrar la info de un determinado item, como precondicion el item debe existir
	 * @param id nombre del item
	 */
	public void requestScanItem(String id){
		if (this.containsItem(id)) this.itemScannedObserver(id);
		else this.itemEmptyObserver(id);
	}
	
	//Metodos Utiles
	/**
	 * Informa a los observadores de que el inventario ha cambiado
	 */
	public void informaObservadorInventory(){
		this.iniciaItemTabla();
		Iterator<InventoryObserver> it = observadores.iterator();
		while (it.hasNext()){
				it.next().inventoryChange(itemsTabla);
		}
	}
	
	/**
	 * Informa a los observadores que un item va a ser escaneado
	 * @param id Identificador del item
	 */
	private void itemScannedObserver(String id){
		Iterator<InventoryObserver> it = observadores.iterator();
		while (it.hasNext()){
			it.next().itemScanned(this.getItem(id).toString());
		}
	}
	
	/**
	 * Informa a los observadores que se ha terminado un item
	 * @param id Identificador del elemento
	 */
	private void itemEmptyObserver(String id){
		Iterator<InventoryObserver> it = observadores.iterator();
		while (it.hasNext()){
			it.next().itemEmpty(id);
		}
	}
	
	
	private void iniciaItemTabla(){
		itemsTabla.clear();
		Collection<Item> lista = contenedor.values();
		Iterator<Item> it = lista.iterator();
		while (it.hasNext()){
			itemsTabla.add(it.next());
		}
	}
}
