package tp.pr5.gui;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tp.pr5.Direction;
import tp.pr5.PlaceInfo;
import tp.pr5.NavigationObserver;
import java.awt.BorderLayout;
import java.awt.GridLayout;

/**
 * 
 * @author Jefferson Cárdenas
 * Esta clase es la encargada de mostrar la ciudad que el robot atraviesa en la interfaz GUI,
 * Contiene las celdas que representan la ciudad,un area de texto que muestra la informacion del
 * lugar actual y una imagen que representa la direccion en la que mira el robot
 *
 */
public class NavigationPanel extends JPanel implements NavigationObserver{
	
	private static final long serialVersionUID = 1L;
	private int row;
	private int col;
	private PlaceCell[][] cells;
	private JTextArea info;
	private JLabel lbRobotHeading;
	//private java.util.EnumMap<K extends java.lang.Enum<K>,V> headingIcons;
	private ImageIcon iconoNorte;
	private ImageIcon iconoOeste;
	private ImageIcon iconoSur;
	private ImageIcon iconoEste;
	private Direction direccion;	
	private JScrollPane scrollpane;
	private int filaActual;
	private int colActual;
	
	/**
	 * Constructora del NavigationPanel
	 */
	public NavigationPanel(){
		this.initNavigationPanel();
	}
	
	/**
	 * Metodo privado que inicializa las celdas,el area de texto y la imagen de walle
	 */
	private void initNavigationPanel(){
		this.row = 11;
		this.col = 11;
		
		this.filaActual = 4;
		this.colActual = 4;
		
		JPanel matriz = new JPanel();
		matriz.setLayout(new GridLayout(this.row,this.col));
		matriz.setBorder(new TitledBorder("City Map"));
		
		this.iniciaCeldas(matriz);
		
		matriz.setVisible(true);
		this.setLayout(new BorderLayout());
		
		//log inferior
		this.info = new JTextArea(5,5);
		scrollpane = new JScrollPane(this.info);
		scrollpane.setBorder(new TitledBorder("Log"));
		scrollpane.setBounds(10,50,400,300);
		this.info.setEditable(false);
		
		//Imagen de wall-e
		this.lbRobotHeading = new JLabel();
		this.iniciaImagen();
		this.lbRobotHeading.setIcon(iconoNorte);
		this.lbRobotHeading.setBorder(new TitledBorder(""));
		this.lbRobotHeading.setVisible(true);
		
		//Colocacion de los elementos en el JPanel
		this.add(scrollpane,BorderLayout.SOUTH);
		this.add(lbRobotHeading,BorderLayout.WEST);
		this.add(matriz,BorderLayout.CENTER);
	}
	
	/**
	 * Metodo privadoq ue inicia las celdas que representan la ciudad
	 * @param matri JPanel que representa la ciudad
	 */
	private void iniciaCeldas(JPanel matri){
		this.cells = new PlaceCell[this.row][this.col];
		for (int i = 0;i<this.row;i++){
			for (int j = 0;j<this.col;j++){
				this.cells[i][j] = new PlaceCell();
				anyadeAction(this.cells[i][j]);
				matri.add(this.cells[i][j]);				
			}
		}
	}
	
	/**
	 * Inicializa las imagenes
	 */
	private void iniciaImagen(){
		iconoNorte = new ImageIcon(NavigationPanel.class.getResource("/tp/pr5/gui/images/walleNorth.png"));
		iconoOeste = new ImageIcon(NavigationPanel.class.getResource("/tp/pr5/gui/images/walleWest.png"));
		iconoEste = new ImageIcon(NavigationPanel.class.getResource("/tp/pr5/gui/images/walleEast.png"));
		iconoSur = new ImageIcon(NavigationPanel.class.getResource("/tp/pr5/gui/images/walleSouth.png"));
	}
	
	/**
	 * Ajusta la imagen de Wall-e
	 */
	private void fijaImagen(){
		switch(this.direccion){
		case NORTH:	this.lbRobotHeading.setIcon(iconoNorte);
		break;
		case EAST:	this.lbRobotHeading.setIcon(iconoEste);
		break;
		case SOUTH:	this.lbRobotHeading.setIcon(iconoSur);
		break;
		case WEST:	this.lbRobotHeading.setIcon(iconoOeste);
		break;
		}
	}
	
	/**
	 * Actualiza la fila y columna actual en funcion de la direccion
	 * @param dir direccion del robot
	 */
	private void actualizaPos(Direction dir){
		if (this.filaActual>0 && this.colActual>0 && this.filaActual<this.row && this.colActual<this.col){
			switch(dir){
				case NORTH:	this.filaActual--;
				break;
				case EAST: this.colActual++;
				break;
				case SOUTH: this.filaActual++;
				break;
				case WEST:	this.colActual--;
			}
		}
	}
	
	/**
	 * Muestra la informacion del lugar en el que se pulsa el boton
	 * @param info String con la informacion del PlaceCell
	 */
	private void showCellInfo(String info){
		this.info.setText("");
		this.info.setText(info);
	}
	
	/**
	 * Informacion a mostrar por el area de texto
	 * @param mensaje
	 */
	public void actualizaInfoLog(){
		this.info.setText("");
		this.info.setText(this.cells[this.filaActual][this.colActual].muestraInfoPlace());
	}
	
	/**
	 * Notifica que la direccion del robot ha cambiado
	 * @param newHeading nueva direccion del robot
	 */
	public void headingChanged(Direction newHeading){
		this.direccion = newHeading;
		this.fijaImagen();
	}

	/**
	 * Notifica que el modulo de navegacion ha sido inicializado
	 * @param initialPlace el lugar donde el robot comienza la simulacion
	 * @param heading direccion inicial del robot
	 */
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading){
		this.direccion = heading;
		this.cells[this.filaActual][this.colActual].setPlace(initialPlace);
		this.cells[this.filaActual][this.colActual].setName(initialPlace.getName());
		this.cells[this.filaActual][this.colActual].muestraInfoPlace();
		this.actualizaInfoLog();
	}
	
	/**
	 * Notifica que el lugar donde esta el robot ha cambiado(porque el robot
	 * ha cogido o soltado un item)
	 * @param placeDescription
	 */
	public void placeHasChanged(PlaceInfo placeDescription){
		this.cells[this.filaActual][this.colActual].setPlace(placeDescription);
		this.cells[this.filaActual][this.colActual].setName(placeDescription.getName());
		this.cells[this.filaActual][this.colActual].muestraInfoPlace();
	}

	/**
	 * Notifica que el usuario solicita realizar la instruccion de tipo RADAR
	 * @param placeDescription
	 */
	public void placeScanned(PlaceInfo placeDescription){
		this.actualizaInfoLog();
	}

	/**
	 * Notifica que el robot ha llegado al lugar
	 * @param heading direccion de movimiento del robot
	 * @param place el lugar donde el robot llega
	 */
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place){
		this.direccion = heading;
		this.cells[this.filaActual][this.colActual].visitada(place.getName());
		actualizaPos(this.direccion);
		this.cells[this.filaActual][this.colActual].setPlace(place);		
	}
	
	/**
	 * Agrega el evento a mostrar por la celda pulsada
	 * @param celda placecell pulsada
	 */
	public void anyadeAction(final PlaceCell celda){
		celda.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent e){
				if (!celda.vacia() && celda.visitada(celda.getPlaceName()))
					showCellInfo(celda.muestraInfoPlace());
			}
			
		});
	}

}
