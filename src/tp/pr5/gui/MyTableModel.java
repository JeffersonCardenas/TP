package tp.pr5.gui;

import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.table.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import tp.pr5.items.Item;

/**
 * Clase que implementa la interfaz Table Model, utilizada en la tabla de items de la interfaz GUI
 * @author Jefferson Cárdenas
 *
 */
class MyTableModel extends AbstractTableModel {
	static final long serialVersionUID = 1L;
	private String[] columnNames = {"Id", "Description"};
	private LinkedList<Item> data = new LinkedList<Item>();
	private LinkedList listeners = new LinkedList();
	
	/**
     * Pasa a los suscriptores el evento.
     */
    private void avisaSuscriptores (TableModelEvent evento){
    	
        // Bucle para todos los suscriptores en la lista, se llama al metodo
        // tableChanged() de los mismos, pasándole el evento.
        
        Iterator it = listeners.iterator();
        
        while (it.hasNext()){
        	((TableModelListener)it.next()).tableChanged(evento);
        }
        
    }
    
    /**
     * Elimina todos los elementos de la lista
     */
    public void removeTable(){
    	data.clear();
    	this.fireTableDataChanged();
    }
	
    /**
     * Devuelve el numero de columnas
     */
	public int getColumnCount() {
		//return columnNames.length;
		return 2;
	}
	
	/**
	 * devuelve el numero de filas
	 */
	public int getRowCount() {
		//return data.length;
		return data.size();
	}
	
	/**
	 * Devuelve el nombre de las columnas
	 */
	public String getColumnName(int col) {
		//return columnNames[col];
		String aux = "";
		switch (col){
			case 0: aux = "Id";
			case 1: aux = "Description";
		}
		return aux;
	}
	
	/**
	 * Devuelve el valor de una posicion en la tabla
	 */
	public Object getValueAt(int row, int col) {
		Item aux = data.get(row);
		
		switch(col){
			case 0: return aux.getId();
			
			case 1: return aux.getDescription();
			
			default: return null;
		}
	}
	
	/**
	 * Devuelve la clase de la columna
	 */
	public Class getColumnClass(int c) {
		//return getValueAt(0, c).getClass();
		switch(c){
			case 0: return String.class;
			
			case 1: return String.class;
			
			default: return Object.class;
		}
	}

  /**
   * Devuelve un booleano indicando si la celda es editable
   */
   public boolean isCellEditable(int row, int col) {
      //Note that the data/cell address is constant,
      //no matter where the cell appears onscreen.
      return (col<2);
   }

  /**
   * Asigna un valor en la posicion de la tabla
   */
  public void setValueAt(Object value, int row, int col) {
	  data.add((Item) value);
	  fireTableCellUpdated(row, col);
  }
  
  /**
   * Borra del modelo el item en la fila indicada 
   */
  public void borraItem (int fila)
  {
      // Se borra la fila 
      data.remove(fila);
      
      // Y se avisa a los suscriptores, creando un TableModelEvent
      TableModelEvent evento = new TableModelEvent (this,fila,fila,TableModelEvent.ALL_COLUMNS,TableModelEvent.DELETE);
      
      // se pasa a los suscriptores el TableModelEvent
      avisaSuscriptores (evento);
  }
  
  /**
   * Añade un Item al final de la tabla
   */
  public void anyadeItem (Item nuevoItem)
  {
      // Añade la persona al modelo 
      data.add(nuevoItem);
      
      // Avisa a los suscriptores creando un TableModelEvent
      TableModelEvent evento;
      evento = new TableModelEvent (this, this.getRowCount()-1,
          this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
          TableModelEvent.INSERT);

      // avisa a los suscriptores
      avisaSuscriptores (evento);
  }
  
  /** 
   * Añade el suscriptor a la lista de suscriptores que es notificada cada vez que ocurre un cambio en
   * el modelo de la tabla
   * @param	l el TableModelListener
   *
   */
  public void addTableModelListener(TableModelListener l) {
      listeners.add(l);
  }
  
  /** 
   * Elimina el suscriptor de la lista
   * @param	l el TableModelListener
   *
   */
  public void removeTableModelListener(TableModelListener l) {
      listeners.remove(l);
  }
 
}
