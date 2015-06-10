package tp.pr5.instructions.exceptions;

/**
 * 
 * @author Jefferson Cárdenas
 * Excepcion lanzada cuando un string no puede ser parseado para crear un comando
 */
public class WrongInstructionFormatException extends java.lang.Exception{
	
	private static final long serialVersionUID = 5827964258591386202L;
	
	public WrongInstructionFormatException(){}
	
	public WrongInstructionFormatException(java.lang.String arg0){
		super(arg0);
	}
	
	public WrongInstructionFormatException(java.lang.Throwable arg0){
		super(arg0);
	}
	
	public WrongInstructionFormatException(java.lang.String arg0,java.lang.Throwable arg1){
		super(arg0,arg1);
	}

}
