package tp.pr5;
/**
 * 
 * @author Jefferson Cárdenas Carrillo
 * Esta clase representa la ciudad por la que el robot se mueve, contiene información
 * sobre las calles y los lugares de la ciudad
 */
public class City {
	private Street[] cityMap;
	private int size;
	private int numElems;
	
	/**
	 * Constructora por defecto, inicializa el array de streets
	 */
	public City(){
		this.size = 1;
		this.numElems = 0;
		this.cityMap = new Street[this.size];
	}
	
	/**
	 * Constructora con argumento, inicializa el array de streets
	 * @param cityM array de streets
	 */
	public City(Street[] cityM){
		this.cityMap = cityM;
		this.numElems = cityM.length;
	}
	
	/**
	 * Metodo privado para duplicar el tamaño del array
	 */
	private void redimensiona(){
		Street[] aux = new Street[this.size*2];
		for (int i=0;i<this.numElems;i++){
			aux[i] = this.cityMap[i];
		}
		this.cityMap = aux;
		this.size = this.size *2;
	};
	
	/**
	 * Busca la calle que comienza por un lugar y una direccion dada
	 * @param currentPlace: Place donde buscar la calle
	 * @param currentHeading: Direction donde buscar la calle
	 * @return Devuelve la calle que comienza con una direccion y un lugar dados
	 * devuelve null si no existe ninguna calle en esa direccion ni lugar dados
	 */
	public Street lookForStreet(Place currentPlace, Direction currentHeading){
		boolean enc = false;
		int i = 0;
		Street aux = null;
		while(!enc && i < this.numElems){
			if (this.cityMap[i].comeOutFrom(currentPlace, currentHeading)) enc = true;
			else i++;
		}
		if (enc) aux = this.cityMap[i];
		return aux;
	}
	
	/**
	 * Añade una calle a la ciudad (array)
	 * @param street : La calle que va a ser adherida
	 */
	public void addStreet (Street street){		
		if (this.numElems < this.size)	this.cityMap[this.numElems] = street;		
		else {
			this.redimensiona();
			this.cityMap[this.numElems] = street;
		}
		this.numElems++;
	}
	
	/**
	 * Accesora
	 * @return El numero de calles de la ciudad
	 */
	public int numElems(){
		return this.numElems;
	}

}