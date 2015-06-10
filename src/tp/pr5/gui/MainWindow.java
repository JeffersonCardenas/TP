package tp.pr5.gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import tp.pr5.Rotation;
import tp.pr5.instructions.*;
import tp.pr5.RobotEngineObserver;

/**
 * 
 * @author Jefferson Cárdenas
 * Esta clase crea la ventana para la interfaz de Swing
 */
public class MainWindow extends JFrame implements RobotEngineObserver{
	
	//Atributos
	private static final long serialVersionUID = 1L;
	private JPanel instrucciones;		//Cuadro de instrucciones
	private NavigationPanel theCity;	//Representa la ciudad(Casillas)
	private RobotPanel robotInfo;		//Panel informacion de la derecha(tabla)
	private InfoPanel etiqueta;			//muestra los mensajes de simulacion
	private JMenuBar menu;	
	private GUIController controlador;
	
	/**
	 * Crea la ventana y los paneles usando componentes de swing
	 * @param gameController El RobotEngine que recibe las instrucciones realizadas
	 * por el panel de acciones
	 */
	public MainWindow(GUIController control){
		super("WALL-E The Garbage Collector");
		this.initVentana();
		this.controlador = control;
		this.controlador.agregaObservadores(this, robotInfo, theCity, etiqueta);
	}
	
	/**
	 * Inicializa los distintos elementos de la ventana de Swing
	 */
	private void initVentana(){
		setName("WALL-E The Garbage Collector");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200,150,800,600);		
		setVisible(true);
		
		this.robotInfo = new RobotPanel();
		this.theCity = new NavigationPanel();
		this.etiqueta = new InfoPanel();
		
		//Parte de arriba (panel de instrucciones y tabla de elementos)
		JPanel info = new JPanel();
		info.setLayout(new GridLayout(1,1));
		
		//Inicializa el panel de instrucciones
		initInstrucciones();
		
		info.add(instrucciones);
		info.add(robotInfo);
		
		//Menu
		this.menu = new JMenuBar();
		JMenu m1 = new JMenu("Quit");		
		this.menu.add(m1);
		setJMenuBar(this.menu);
		
		//Contenedor Principal (panel de cells y ventana de info)
		JPanel contenedor = new JPanel();
		contenedor.setLayout(new BorderLayout());
		
		contenedor.add(theCity,BorderLayout.CENTER);
		contenedor.add(info,BorderLayout.NORTH);
		contenedor.add(etiqueta,BorderLayout.SOUTH);
		
		contenedor.setVisible(true);
		
		this.add(contenedor);
		
		pack();
		setSize(800,600);
	}
	
	/**
	 * Inicializa el panel de instrucciones y añade los eventos
	 */
	private void initInstrucciones(){
		this.instrucciones = new JPanel();
		this.instrucciones.setLayout(new GridLayout(4,2,5,5));
		this.instrucciones.setBorder(new TitledBorder("Instructions"));
		this.instrucciones.setVisible(true);
		
		final JButton bOperate = new JButton("OPERATE");
		final JButton bDrop = new JButton("DROP");
		final JButton bPick = new JButton("PICK");
		final JButton bMove = new JButton("MOVE");
		final JButton bQuit = new JButton("QUIT");
		final JButton bTurn = new JButton("TURN");
		final JComboBox<Rotation> cRotation = new JComboBox<Rotation>(Rotation.values());
		final JTextField tItem = new JTextField(1);
		
		//Añadimos funcionalidad a los botones
		this.instruccionMove(bMove);
		this.instruccionQuit(bQuit);
		this.instruccionOperate(bOperate,tItem);
		this.instruccionDrop(bDrop,tItem);
		this.instruccionPick(bPick,tItem);
		this.instruccionTurn(bTurn,cRotation);
		
		//Añadimos los botones al panel
		this.instrucciones.add(bMove);
		this.instrucciones.add(bQuit);
		this.instrucciones.add(bTurn);
		this.instrucciones.add(cRotation);
		this.instrucciones.add(bPick);
		this.instrucciones.add(tItem);
		this.instrucciones.add(bDrop);
		this.instrucciones.add(bOperate);	
	}
	
	/**
	 * Metodo privado que añade la funcionalidad de la instruccion move al boton
	 * @param botonMover boton del panel que realiza la instruccion move
	 */
	private void instruccionMove(JButton botonMover){
		botonMover.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent e){
				Instruction i = new MoveInstruction();
				controlador.ejecutaInstruccion(i);
				robotInfo.actualizaDatos();
				theCity.actualizaInfoLog();
			}
		});
	}
	
	/**
	 * Metodo privado que añade la funcionalidad de la instruccion quit al boton
	 * @param botonQuit boton del panel que realiza la instruccion quit
	 */
	private void instruccionQuit(final JButton botonQuit){
		botonQuit.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent e){
				int salir = JOptionPane.showConfirmDialog(botonQuit,"¿Deseas salir?");
				if (JOptionPane.OK_OPTION == salir)	{
					controlador.requestEnd();
					JOptionPane.showMessageDialog(botonQuit, "WALL·E says: I have communication problems. Bye bye");
					System.exit(0);
				}
			}
		});
	}
	
	/**
	 * Metodo privado que añade funcionalidad de la instruccion operate al boton
	 * @param botonOperate boton del panel que realiza la instruccion Operate
	 * @param texto campo de texto del panel de instrucciones
	 */
	private void instruccionOperate(final JButton botonOperate,final JTextField texto){
		botonOperate.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent e){
				String id = texto.getText();
				if (!id.isEmpty()){
					Instruction i = new OperateInstruction(id);
					controlador.ejecutaInstruccion(i);
					robotInfo.borraTabla();
					robotInfo.actualizaDatos();
					controlador.requestUpdateInventory();
					theCity.actualizaInfoLog();
				}
			}
		});
	}
	
	/**
	 * Metodo privado que añade funcionalidad de la instruccion Drop al boton
	 * @param botonDrop boton del panel que realiza la instruccion Drop
	 * @param texto campo de texto del panel de instrucciones
	 */
	private void instruccionDrop(final JButton botonDrop,final JTextField texto){
		botonDrop.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent e){
				String id = texto.getText();
				if (!id.isEmpty()){
					Instruction i = new DropInstruction(id);
					controlador.ejecutaInstruccion(i);
					theCity.actualizaInfoLog();
					robotInfo.borraTabla();
					robotInfo.actualizaDatos();
					controlador.requestUpdateInventory();
				}
			}
		});
	}
	
	/**
	 * Metodo privado que añade funcionalidad de la instruccion Pick al boton
	 * @param botonPick boton del panel que realiza la instruccion Pick
	 * @param texto campo de texto del panel de instrucciones
	 */
	private void instruccionPick(final JButton botonPick,final JTextField texto){
		botonPick.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent e){
				String id = texto.getText();
				if (!id.isEmpty()){
					Instruction i = new PickInstruction(id);
					controlador.ejecutaInstruccion(i);
					theCity.actualizaInfoLog();
					robotInfo.actualizaDatos();
					controlador.requestUpdateInventory();
				}
			}
		});
	}
	
	/**
	 * Metodo privado que añade funcionalidad de la instruccion Turn al boton
	 * @param botonTurn boton del panel que realiza la instruccion Turn
	 * @param rotacion ComboBox con las diferentes rotacion (right y left)
	 */
	private void instruccionTurn(final JButton botonTurn,final JComboBox<Rotation> rotacion){
		botonTurn.addActionListener(new ActionListener(){			
			public void actionPerformed(ActionEvent e){
				Rotation rot = (Rotation)rotacion.getSelectedItem();
				Instruction i = new TurnInstruction(rot);
				controlador.ejecutaInstruccion(i);
				robotInfo.actualizaDatos();
			}
		});
	}
	
	/**
	 * El RobotEngine informa de que la comunicacion se ha terminado
	 */
	public void communicationCompleted(){
		this.etiqueta.communicationCompleted();
	}

	/**
	 * El RobotEngine informa de que la ayuda ha sido solicitada
	 * @param help String con la informacion de ayuda
	 */
	public void communicationHelp(String help){
		this.etiqueta.communicationHelp(help);
	}

	/**
	 * El RobotEngine informa de que el robot se desconecta
	 * (porque ha llegado a su nave o se ha quedado sin combustible)
	 * @param atShip true si el robot ha llegado a su nave o false si se ha quedado
	 * sin combustible
	 */
	public void engineOff(boolean atShip){
		this.etiqueta.engineOff(atShip);
	}

	/**
	 * El Robot Engine informa de un error
	 * @param msg mensaje de error
	 */
	public void raiseError(String msg){
		this.etiqueta.raiseError(msg);
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
		this.etiqueta.robotUpdate(fuel, recycledMaterial);
		this.robotInfo.robotUpdate(fuel, recycledMaterial);
	}

}
