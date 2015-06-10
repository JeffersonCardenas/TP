package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.Item;
import tp.pr5.items.InventoryObserver;

/**
 * 
 * @author Jefferson Cárdenas
 * El robot panel muestra informacion del robot, así como de su inventario, muestra los niveles
 * de energía y cantidad de material reciclado del robot
 *
 */
public class RobotPanel extends JPanel implements RobotEngineObserver, InventoryObserver{
	
	private static final long serialVersionUID = 1L;
	private JLabel titulo;
	private JLabel fuelLevel;
	private JLabel recycledMat;
	private JTable table;
	private MyTableModel tabla;
	
	private int fuelLev;
	private int recycle;
	
	/**
	 * Constructora de RobotPanel
	 */
	public RobotPanel(){
		super();
		initRobotPanel();
	}
	
	/**
	 * Inicializa el RobotPanel añadiendo los JLabel y el panel principal
	 */
	private void initRobotPanel(){		
		this.titulo = new JLabel();
		this.titulo.setName("Robot info");
		this.fuelLev = 100;
		this.recycle = 0;
		this.fuelLevel = new JLabel("Fuel: "+ fuelLev, JLabel.CENTER);
        this.recycledMat = new JLabel("Recycled: "+ recycle, JLabel.CENTER);
        
		this.tabla = new MyTableModel();
        
        this.table = new JTable(tabla);
		this.table.setPreferredScrollableViewportSize(new Dimension(200, 150));
        this.table.setFillsViewportHeight(true);
        this.table.setBorder(new TitledBorder(""));
        
        this.setLayout(new BorderLayout());
        
        JPanel casilla = new JPanel();
		casilla.setLayout(new GridLayout(1,1,2,2));
        
        casilla.add(fuelLevel);
        casilla.add(recycledMat);
        
        this.add(casilla,BorderLayout.NORTH);
        this.add(this.table,BorderLayout.CENTER);
        this.setBorder(new TitledBorder(this.titulo.getName()));
        this.setVisible(true);
	}
	
	/**
	 * El RobotEngine informa de que la comunicacion se ha terminado
	 */
	public void communicationCompleted(){
	}

	/**
	 * El RobotEngine informa de que la ayuda ha sido solicitada
	 * @param help String con la informacion de ayuda
	 */
	public void communicationHelp(String help){
		
	}

	/**
	 * El RobotEngine informa de que el robot se desconecta
	 * (porque ha llegado a su nave o se ha quedado sin combustible)
	 * @param atShip true si el robot ha llegado a su nave o false si se ha quedado
	 * sin combustible
	 */
	public void engineOff(boolean atShip){
	}

	/**
	 * El Robot Engine informa de un error
	 * @param msg mensaje de error
	 */
	public void raiseError(String msg){
		
	}

	/**
	 * El RobotEngine informa de que el robot quiere decir algo
	 * @param message mensaje del robot
	 */
	public void robotSays(String message){
		
	}

	/**
	 * El RobotEngine informa que el fuel y/o la cantidad de material reciclado ha cambiado
	 * @param fuel cantidad actual de fuel
	 * @param recycledMaterial cantidad actual de material reciclado
	 */
	public void robotUpdate(int fuel, int recycledMaterial){
		fuelLev = fuel;
		recycle = recycledMaterial;
		this.fuelLevel.setText("Fuel: "+ fuelLev);
		this.recycledMat.setText("Recycled: "+ recycle);
	}
	
	/**
	 * Notifica cuando el contenedor ha cambiado
	 * @param inventory Nuevo inventario
	 */
	public void inventoryChange(List<Item> inventory){
		if (!inventory.isEmpty()){
			Iterator<Item> it = inventory.iterator();
			while (it.hasNext()){
				this.addItem(it.next());
			}
		}
		else this.borraTabla();
	}

	/**
	 * Notifica cuando el usuario solicita utilizar la instruccion SCAN sobre el inventario
	 * @param inventoryDescription Informacion sobre el inventario
	 */
	public void inventoryScanned(String inventoryDescription){
		
	}

	/**
	 * Notifica cuando el usuario quiere escanear un item del inventario
	 * @param description Descripcion del item
	 */
	public void itemScanned(String description){
		
	}

	/**
	 * Notifica cuando el item se ha agotado y será removido del inventario. Una invocacion
	 * al metodo inventoryChanged seguirá
	 * @param itemName nombre del item
	 */
	public void itemEmpty(String itemName){
		
	}
	
	/**
	 * Actualiza los datos de la tabla
	 */
	private void actualizaTabla(){
		this.tabla.removeTable();
	}
	
	/**
	 * Elimina la tabla
	 */
	public void borraTabla(){
		this.table.repaint();
	}
	
	/**
	 * Añade un item nuevo a la tabla
	 */
	public void addItem(Item it){
		this.tabla.anyadeItem(it);
	}
	
	/**
	 * Actualiza los datos de la tabla
	 */
	public void actualizaDatos(){
		this.actualizaTabla();
		this.robotUpdate(fuelLev, recycle);
	}
	
	
	

}
