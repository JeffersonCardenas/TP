package tp.pr5;

import java.util.Iterator;
import java.util.ArrayList;

/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * Clase que contienen los 3 observadores distintos, proporcionados por el modelo.
 * Implementa la clase iterable para poder acceder a los diferentes observadores
 * de las clases del modelo. Clase parametrizada al haber 3 tipos distintos
 * de observador
 *
 * @param <T> Tipo parametrico de observador (NavigationObserver,RobotEngineObserver
 * InventoryObserver)
 */
public class Observable<T> implements Iterable<T>{

	protected ArrayList<T> observadores;
	
	/**
	 * Constructora que inicializa el arraylist de observadores
	 */
	public Observable(){
		this.observadores = new ArrayList<T>();
	}
	
	/**
	 * Devuelve un iterador de observadores xra recorrerlos, La interfaz
	 * Iterable obliga a implementar este metodo
	 */
	@Override
	public Iterator<T> iterator() {
		return this.observadores.iterator();
	}
	
	/**
	 * Añade un observador a la lista de observadores
	 * @param elemento observador
	 */
	protected void agregarObservador(T elemento){
		this.observadores.add(elemento);
	}
	
	/**
	 * Elimina un observador de la lista de observadores
	 * @param elemento observador
	 */
	protected void eliminaObservador(T elemento){
		this.observadores.remove(elemento);
	}

}
