package tp.pr5;
/**
 * 
 * @author Jefferson Cárdenas
 *Un tipo enumerado que representa las direcciones mas un valor que representa una direccion
 *desconocida
 */

public enum Direction {
	NORTH,EAST,SOUTH,WEST,UNKNOWN;

/**
 * Método que convierte un string en un enumerado de tipo Direction
 * @param direction String que indica una direccion
 * @return una direccion valida en funcion del string de entrada
 */
public static Direction setDirection(String direction){
	Direction aux;
	switch (direction){
		case "north": aux = NORTH;
		break;
		case "south": aux = SOUTH;
		break;
		case "west": aux = WEST;
		break;
		case "east": aux = EAST;
		break;
		default : aux = UNKNOWN;
		break;
	}
	return aux;		
}

/**
 * metodo que gira la direccion hacia la derecha
 * @param dir Direction inicial
 * @return Direction despues del giro
 */
public static Direction turnRight(Direction dir){
	Direction aux;
	switch (dir){
		case NORTH : aux = Direction.EAST;
		break;
		case EAST : aux = Direction.SOUTH;
		break;
		case SOUTH : aux = Direction.WEST;
		break;
		case WEST : aux = Direction.NORTH;
		break;
		default: aux = UNKNOWN;
		break;
	}
	return aux;
}

/**
 * metodo que gira la direccion hacia la izquiera
 * @param dir Direction inicial
 * @return Direction despues del giro
 */
public static Direction turnLeft(Direction dir){
	Direction aux;
	switch (dir){
		case NORTH : aux = WEST;
		break;
		case EAST : aux = NORTH;
		break;
		case SOUTH : aux = EAST;
		break;
		case WEST : aux = SOUTH;
		break;
		default: aux = UNKNOWN;
		break;
	}
	return aux;
}
	
}
