package tp.pr5.instructions.exceptions;

/**
 * 
 * @author Jefferson Cárdenas
 * Excepcion lanzada cuando la ejecucion de una instruccion falla
 */
public class InstructionExecutionException extends java.lang.Exception{
	
	private static final long serialVersionUID = 944317795401965279L;
	
	public InstructionExecutionException(){}
	
	public InstructionExecutionException(java.lang.String arg0){
		super(arg0);
	}
	
	public InstructionExecutionException(java.lang.Throwable arg0){
		super(arg0);
	}
	
	public InstructionExecutionException(java.lang.String arg0,java.lang.Throwable arg1){
		super(arg0,arg1);
	}

}
