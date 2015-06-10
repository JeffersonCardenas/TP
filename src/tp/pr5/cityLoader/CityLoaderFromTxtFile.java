package tp.pr5.cityLoader;

import java.util.Scanner;
import tp.pr5.City;
import tp.pr5.Direction;
import tp.pr5.Place;
import tp.pr5.Street;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.items.*;
import java.util.ArrayList;

/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * Esta clase carga desde un fichero de texto la ciudad
 */
public class CityLoaderFromTxtFile {
	
	//atributos
	private int numItems;
	private ArrayList<Place> _places;
	private City _streets;
	
	/**
	 * Constructora por defecto que inicializa los distintos arrays
	 */
	public CityLoaderFromTxtFile(){
		this.numItems = 0;
		this._places = new ArrayList<Place>();
		this._streets = new City();
	}
	
	/**
	 * Elimina de la descripcion de un lugar todos los guiones bajos
	 * @param desc descripcion del lugar
	 * @return string con espacios en lugar de guiones bajos
	 */
	private String setDescripcion(String desc){
		String word[] = desc.split("_");
		String cadena = new String();
		for (int i=0;i<word.length;i++){
			cadena = cadena + word[i] + " ";
		}
		return cadena;
	}
	
	/**
	 * Comprueba si un char es un digito
	 * @param ent string
	 * @return true si el string es de tipo numerico
	 */
	private boolean checkInt(String ent){
		boolean esDigito = false;
		if (ent != null && ent.length() > 0){
			char[] aux = ent.toCharArray();
			int i = 0;
			esDigito = true;
			while (i<aux.length && esDigito){
				if (aux[i] == '-') i++;
				if(!Character.isDigit(aux[i])) esDigito = false;
				else i++;
			}
		}	
		return esDigito;
	}	
	
	/**
	 * Comprueba si la entrada es un lugar
	 * @param place nombre del lugar
	 * @param num posicion del lugar
	 * @param nave indica si el lugar es nave
	 * @return true si y solo si el formato de entrada corresponde a un lugar
	 */
	private boolean checkPlace (String place,String num,String nave){
		boolean esPlace = false;
		if (this.checkInt(num)){
			int ent = Integer.parseInt(num);
			esPlace =  ((nave.equals("spaceShip") || nave.equals("noSpaceShip")) && place.equals("place") && this._places.size() == ent && ent >= 0);
		}
		return esPlace;
	}
	
	/**
	 * Indica si el string representa una nave
	 * @param nave ¿es nave?
	 * @return true si y solo si el string de entrada indica que se trata de una nave
	 */
	private boolean esNave(String nave){
		return nave.equals("spaceShip");
	}
	
	/**
	 * Añade un lugar al array
	 * @param place identificador de place
	 * @param num representa el numero de calles
	 * @param id nombre del lugar
	 * @param desc descripcion del lugar
	 * @param nave indica si el lugar representa una nave
	 * @throws WrongCityFormatException si no cumple el formato de un lugar
	 */
	private void loadPlaces(String place,String num,String id,String desc,String nave) throws WrongCityFormatException{		
		if (this.checkPlace(place, num, nave)){
			this._places.add(new Place(id,this.esNave(nave),this.setDescripcion(desc)));
		}
		else throw new WrongCityFormatException();
	}
	
	/**
	 * Comprueba si el formato de entrada es de una calle
	 * @param street identificador de calle
	 * @param num representa el numero de calles
	 * @param place1 identificador de lugares
	 * @param origen lugar de comienzo de la calle
	 * @param direction direccion entre los lugares
	 * @param place2 identificador de lugares
	 * @param destino final de la calle
	 * @return true si cumple con el formato de calles
	 */
	private boolean checkStreets(String street,String num,String place1,String origen,
			String direction,String place2,String destino){
		boolean esCalle = false;
		if (this.checkInt(num) && this.checkInt(origen) && this.checkInt(destino) ){
			int ent = Integer.parseInt(num);
			int ori = Integer.parseInt(origen);
			int des = Integer.parseInt(destino);
			esCalle = (street.equals("street") && this._streets.numElems() == ent && place1.equals("place") &&
				place2.equals("place") && ori <= this._places.size() && ori >= 0 && des <= this._places.size() && des >= 0 &&
				Direction.setDirection(direction) != Direction.UNKNOWN );
			}
		return esCalle;
	}
	
	/**
	 * Añade una calle abierta al array
	 * @param street identificador de calle
	 * @param num numeros de calles
	 * @param place1 identificador de lugares
	 * @param origen lugar de comienzo de la calle
	 * @param direction direccion
	 * @param place2 identificador de lugares
	 * @param destino lugar de final de la calle
	 * @throws WrongCityFormatException si no cumple el formato de una calle
	 */
	private void loadStreetOpen(String street,String num,String place1,String origen,
			String direction,String place2,String destino) throws WrongCityFormatException{
		if (this.checkStreets(street, num, place1, origen, direction, place2, destino) && this.checkInt(origen) && this.checkInt(destino) ){
			int ori = Integer.parseInt(origen);
			int des = Integer.parseInt(destino);							
			this._streets.addStreet(new Street(this._places.get(ori),Direction.setDirection(direction),this._places.get(des)));
		}
		else throw new WrongCityFormatException();
	}
	
	/**
	 * Añade una calle cerrada al array
	 * @param street identificador de calle
	 * @param num numeros de calles
	 * @param place1 identificador de lugares
	 * @param origen lugar de comienzo de la calle
	 * @param direction direccion
	 * @param place2 identificador de lugares
	 * @param destino lugar de final de la calle
	 * @param clave codigo para abrir o cerrar la calle
	 * @throws WrongCityFormatException si no cumple el formato de una calle
	 */
	private void loadStreetClose(String street,String num,String place1,String origen,
			String direction,String place2,String destino,String clave) throws WrongCityFormatException{				
		if (this.checkStreets(street, num, place1, origen, direction, place2, destino) && this.checkInt(origen) && this.checkInt(destino)){
			int ori = Integer.parseInt(origen);
			int des = Integer.parseInt(destino);
			this._streets.addStreet(new Street(this._places.get(ori),Direction.setDirection(direction),this._places.get(des),false,clave));						
		}
		else throw new WrongCityFormatException();		
	}
	
	/**
	 * Comprueba si un item es de tipo fuel
	 * @param item identificador del item
	 * @return true si y solo si el string de entrada es fuel
	 */
	private boolean esFuel(String item){
		return (item.equals("fuel"));
	}
	
	/**
	 * Comprueba si un item es de tipo garbage
	 * @param item identificador del item
	 * @return true si y solo si el string de entrada es garbage
	 */
	private boolean esGarbage(String item){
		return (item.equals("garbage"));
	}
	
	/**
	 * Comprueba si un item es de tipo codecard
	 * @param item identificador del item
	 * @return true si y solo si el string de entrada es codecard
	 */
	private boolean esCodeCard(String item){
		return (item.equals("codecard"));
	}
	
	/**
	 * Comprueba si el formato de entrada es de un item
	 */
	private boolean checkItem(String num,String place,String num2){
		boolean esItem = false;
		if (this.checkInt(num) && this.checkInt(num2)){
			int it = Integer.parseInt(num);
			int pla = Integer.parseInt(num2);
			esItem =  (this.numItems == it && place.equals("place") && pla <= this._places.size() && pla >=0);
		}
		return esItem;
	}
	
	/**
	 * Añade un item de tipo fuel al array
	 * @param num numero de items
	 * @param id nombre del item
	 * @param desc descripcion del item
	 * @param energia cantidad de combustible para el robot
	 * @param tiempo numero de veces que se puede usar el item
	 * @param place identificador de lugar
	 * @param num2 lugar donde se encuentra el item
	 * @throws WrongCityFormatException si no coincide con el formato de un item
	 */
	private void loadItemFuel(String num,String id,String desc,String energia,String tiempo,String place,String num2) throws WrongCityFormatException{		
		if (this.checkItem(num, place, num2) && this.checkInt(num2) && this.checkInt(energia) && this.checkInt(tiempo)) {
			int lugar = Integer.parseInt(num2);
			int energy = Integer.parseInt(energia);
			int time = Integer.parseInt(tiempo);
			this._places.get(lugar).addItem(new Fuel(id,this.setDescripcion(desc),energy,time));
			this.numItems++;
		}
		else throw new WrongCityFormatException();
	}
	
	/**
	 * Añade un item de tipo garbage al array
	 * @param num numero de items
	 * @param id nombre del item
	 * @param desc descripcion del item
	 * @param reciclada cantidad de material reciclado que proporciona al robot
	 * @param place identificador de lugar
	 * @param num2 lugar donde se encuentra el item
	 * @throws WrongCityFormatException si no coincide con el formato de un item
	 */
	private void loadItemGarbage(String num,String id,String desc,String reciclada,String place,String num2) throws WrongCityFormatException{		
		if (this.checkItem(num, place, num2) && this.checkInt(num2) && this.checkInt(reciclada)){
			int lugar = Integer.parseInt(num2);
			int recycle = Integer.parseInt(reciclada);
			this._places.get(lugar).addItem(new Garbage(id,this.setDescripcion(desc),recycle));
			this.numItems++;
		}
		else throw new WrongCityFormatException();
	}
	
	/**
	 * Añade un item de tipo codecard al array
	 * @param num numero de items
	 * @param id nombre del item
	 * @param desc descripcion del item
	 * @param cod codigo de la tarjeta
	 * @param place identificador de lugar
	 * @param num2 lugar donde se encuentra el item
	 * @throws WrongCityFormatException si no coincide con el formato de un item
	 */
	private void loadItemCodeCard(String num,String id,String desc,String cod,String place,String num2) throws WrongCityFormatException{		
		if (this.checkItem(num, place, num2) && this.checkInt(num2)){
			int lugar = Integer.parseInt(num2);
			this._places.get(lugar).addItem(new CodeCard(id,this.setDescripcion(desc),cod));
			this.numItems++;
		}
		else throw new WrongCityFormatException();
	}
	
	/**
	 * Comprueba que la parte de lugares del archivo empieza y termina correctamente
	 * @param sc Scanner
	 * @throws WrongCityFormatException si no cumple con el formato de la ciudad
	 */
	private void checkInputPlace(Scanner sc) throws WrongCityFormatException{	
		if (sc.hasNext()){
			boolean endPlaces = false;
			String line = sc.nextLine();
			if (line.equals("BeginPlaces")){
				while(sc.hasNext() && !endPlaces){
					line = sc.nextLine();
					if (line.equals("EndPlaces")) endPlaces = true;
					else{
						String[] word = line.split(" ");
						if (word.length == 5)	this.loadPlaces(word[0],word[1],word[2],word[3],word[4]);
						else throw new WrongCityFormatException();
					}
				}
			}
			else throw new WrongCityFormatException();
		}
		else throw new WrongCityFormatException();
	}
	
	/**
	 * Comprueba que la parte de calles del archivo empieza y termina correctamente
	 * @param sc Scanner
	 * @throws WrongCityFormatException si no cumple con el formato de la ciudad
	 */
	private void checkInputStreet(Scanner sc) throws WrongCityFormatException{		
		if (sc.hasNext()){
			boolean endStreets = false;		
			String line = sc.nextLine();
			if (line.equals("BeginStreets")){
				while(sc.hasNext() && !endStreets){					
					line = sc.nextLine();
					if (line.equals("EndStreets")) endStreets = true;
					else{				 
						String[] word = line.split(" ");
						if (word.length == 8 || word.length == 9){
							if (word[7].equals("open"))	this.loadStreetOpen(word[0],word[1],word[2],word[3],word[4],word[5],word[6]);
							else if (word[7].equals("closed"))	this.loadStreetClose(word[0],word[1],word[2],word[3],word[4],word[5],word[6],word[8]);
							else throw new WrongCityFormatException();
						}
						else throw new WrongCityFormatException();
					}
				}
			}
			else throw new WrongCityFormatException();
		}
		else throw new WrongCityFormatException();
	}
	
	/**
	 * Comprueba que la parte de items del archivo empieza y termina correctamente
	 * @param sc Scanner
	 * @throws WrongCityFormatException si no cumple con el formato de la ciudad
	 */
	private void checkInputItem(Scanner sc) throws WrongCityFormatException{		
		if (sc.hasNext()){
			boolean endItems = false;
			String line = sc.nextLine();
			if (line.equals("BeginItems")){
				while(sc.hasNext() && !endItems){
					line = sc.nextLine();
					if (line.equals("EndItems")) endItems = true;
					else{
						String[] word = line.split(" ");
						if (word.length == 7 || word.length == 8){
							if (this.esFuel(word[0]))	this.loadItemFuel(word[1],word[2],word[3],word[4],word[5],word[6],word[7]);
							else if (this.esGarbage(word[0])) this.loadItemGarbage(word[1],word[2],word[3],word[4],word[5],word[6]);
							else if (this.esCodeCard(word[0])) this.loadItemCodeCard(word[1],word[2],word[3],word[4],word[5],word[6]);
							else throw new WrongCityFormatException();
						}
						else throw new WrongCityFormatException();
					}
				}
			}
			else throw new WrongCityFormatException();
		}
		else throw new WrongCityFormatException();
	}
	
	/**
	 * 
	 * @param file el archivo de entrada dond esta guardada la ciudad
	 * @return la ciudad
	 * @throws java.io.IOException cuando hay algun formato erroneo en el archivo o errores de entrada/salida
	 */
	public City loadCity(java.io.InputStream file) throws java.io.IOException{		
		Scanner sc = null;		
		try{			
			sc = new Scanner(file);			
			String line = sc.nextLine();
			if (line.equals("BeginCity") && sc.hasNext()){
				//Comprobamos que la entrada de los lugares es correcta		
				this.checkInputPlace(sc);				
				//Comprobamos que la entrada de las calles es correcta
				this.checkInputStreet(sc);				
				//Comprobamos que la entrada de los items es correcta		
				this.checkInputItem(sc);
				
				line = sc.nextLine();
				if (!line.equals("EndCity")) throw new WrongCityFormatException();
									
			}
			else throw new WrongCityFormatException();			
		}			
		catch (WrongCityFormatException e){			
			throw e;
		}		
		finally{
			sc.close();
		}
		
		return this._streets;
		
	}
	
	/**
	 * Devuelve el lugar donde el robot empieza la simulacion
	 * @return el lugar inicial
	 */
	public Place getInitialPlace(){
		return this._places.get(0);
	}

}
