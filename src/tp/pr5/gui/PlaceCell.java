package tp.pr5.gui;

import tp.pr5.PlaceInfo;
import javax.swing.JButton;

/**
 * 
 * @author Jefferson Cárdenas
 * Representa un lugar en la interfaz swing, es un boton con el nombre del lugar,
 * cuando el usuario pulsa el boton la interfaz muestra la descripcion del lugar
 *
 */
public class PlaceCell extends JButton{
	
	private static final long serialVersionUID = 1L;	
	//private NavigationPanel owner;
	private boolean active;
	//private Place thePlace;
	private PlaceInfo thePlace;
	
	/**
	 * Contructora de PlaceCell
	 */
	public PlaceCell(){
		super("");
		this.active = false;
		this.thePlace = null;
		this.setVisible(true);
	}
	
	/**
	 * Actualiza el lugar de la ejecución
	 */
	public void setPlace(PlaceInfo place){
		this.thePlace = place;
		this.active = true;
		this.setText(thePlace.getName());
		setBackground(java.awt.Color.green);
	}
	
	/**
	 * Indica si una celda ya ha sido visitada
	 * @return true si y solo si la celda que representa a un lugar ya ha sido visitado
	 */
	public boolean visitada(String nombre){
		if (this.thePlace.getName()!=nombre )	setBackground(java.awt.Color.gray);
		return active;
	}
	
	/**
	 * Muestra la descripción del lugar
	 */
	public String muestraInfoPlace(){
		return this.thePlace.toString();
	}
	
	/**
	 * Indica si una celda no tiene asignado ningun lugar
	 * @return true si y solo si la celda tiene un place
	 */
	public boolean vacia(){
		return (this.thePlace == null);
	}
	
	/**
	 * Metodo que informa de si la casilla es la nave espacial
	 * @return booleano true si la casilla es nava espacial false en otro caso
	 */
	public boolean esNave(){
		return this.thePlace.isSpaceship();
	}
	
	/**
	 * Devuelve el nombre del lugar
	 * @return string que representa el nombre del lugar
	 */
	public String getPlaceName(){
		return this.thePlace.getName();
	}

}
