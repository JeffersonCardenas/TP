package tp.pr5.cityLoader.cityLoaderExceptions;

/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * Excepcion lanzada por el cargador del mapa cuando el archivo no coincide con el formato requerido
 */
public class WrongCityFormatException extends java.io.IOException{
		
		private static final long serialVersionUID = 1L;
		
		public WrongCityFormatException(){}
		
		public WrongCityFormatException(java.lang.String msg){
			super(msg);
		}
		
		public WrongCityFormatException(java.lang.String msg,java.lang.Throwable arg){
			super(msg,arg);
		}
		
		public WrongCityFormatException(java.lang.Throwable arg){
			super(arg);
		}

}
