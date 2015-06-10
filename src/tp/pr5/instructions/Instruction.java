package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Jefferson Cárdenas
 * Este interfaz representa una instruccion soportada por la aplicacion, cada instruccion que el robot
 * puede realizar implementa esta instruccion, esto fuerza a que cada instruccion implemente
 * cuatro metodos diferentes
 *
 */
public interface Instruction {
	
	/**
	 * Parsea el string devolviendo una instancia de cada correspondiente subclase
	 * @param cad string
	 * @return instancia de una subclase si se corresponde con el string de entrada
	 * @throws WrongInstructionFormatException cuando el string no coincide con ninguna instruccion
	 */
	public abstract Instruction parse (String cad) throws WrongInstructionFormatException;
	
	/**
	 * Devuelve una descripcion de la sintaxis de la instruccion
	 * @return la sintaxis de la instruccion
	 */
	public abstract String getHelp();
	
	/**
	 * Ajusta el contexto de la ejecucion
	 * @param engine el robot engine
	 * @param navigation la informacion sobre el juego
	 * @param robotContainer el inventario del robot
	 */
	public abstract void configureContext(RobotEngine engine,NavigationModule navigation,ItemContainer robotContainer);
	
	/**
	 * Ejecuta la instruccion, debe ser implementada en cada subclase no abstracta
	 * @throws InstructionExecutionException si existe algun error de ejecucion
	 */
	public abstract void execute() throws InstructionExecutionException;

}
