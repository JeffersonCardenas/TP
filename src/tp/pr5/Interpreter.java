package tp.pr5;

import java.util.ArrayList;
import java.util.Iterator;
import tp.pr5.Interpreter;
import tp.pr5.instructions.*;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
/**
 * 
 * @author Jefferson Cárdenas Carrillo
 *El interpreter se encarga de convertir la entrada del usuario en una instruction
 *para el robot, las instrucciones validas son MOVE,DROP,HELP,OPERATE,PICK,QUIT,RADAR
 *SCAN y TURN
 */
public class Interpreter {
	
	public static final String LINE_SEPARATOR =	System.getProperty("line.separator");
	
	private static ArrayList<Instruction> inst = new ArrayList<Instruction>();	
	
	/**
	 * constructora por defecto que inicializa el arraylist de las instrucciones
	 */
	public Interpreter(){
		inst.add(new DropInstruction());
		inst.add(new HelpInstruction());
		inst.add(new MoveInstruction());
		inst.add(new OperateInstruction());
		inst.add(new PickInstruction());
		inst.add(new QuitInstruction());
		inst.add(new RadarInstruction());
		inst.add(new ScanInstruction());
		inst.add(new TurnInstruction());
	}
	
	/**
	 * Genera una nueva instruccion en funcion de la entrada del usuario
	 * @param line String con la instruccion del usuario
	 * @return la instruccion leida por line dado, si la instruccio no es correcta lanza una excepcion
	 * @throws WrongInstructionFormatException
	 */
	public static Instruction generateInstruction (java.lang.String line) throws WrongInstructionFormatException{		
		boolean enc = false;
		Iterator<Instruction> it = inst.iterator();
		
		while(it.hasNext() && !enc){
			try{
				Instruction inst = it.next().parse(line);
				enc = true;
				return inst;
			}		
			catch(WrongInstructionFormatException e){
			
			}
		}		
		return null;
	}		
				
	/**
	 * Devuelve informacion sobre todas las instrucciones que el robot entiende
	 * @return String con la informacion sobre todas las instrucciones que el robot entiende
	 */
	public static java.lang.String interpreterHelp(){
		String cadena = new String("The valid instructions for WALL-E are:"+LINE_SEPARATOR);
		Iterator<Instruction> it = inst.iterator();
		while(it.hasNext()){
			cadena += it.next().getHelp()+LINE_SEPARATOR;
		}
		return cadena;
	}

}
