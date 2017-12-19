package modelo;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

public class ModeloTabla extends AbstractTableModel{
	
	 private ResultSet resultado;
	 private ResultSetMetaData metaResultado;
	    
	 public ModeloTabla(ResultSet resultado){
	        
		 try {
			 this.resultado = resultado;
	         this.metaResultado = this.resultado.getMetaData();
	         
	     } catch (SQLException ex) {
	         System.out.println(ex.getMessage());
	        
	     }
	 }
	    
	 @Override
	 public int getRowCount() {
	     try {
	         resultado.last();
	         return resultado.getRow();
	     } catch (SQLException ex) {
	    	 System.out.println(ex.getMessage());
	    	 System.out.println("Aqui");
	     }
	        //return metaResultado.getRow();
	     return 0;
	 }

	 @Override
	 public int getColumnCount() {
	     
		 try {
	         return metaResultado.getColumnCount();
	     } catch (SQLException ex) {
	        System.out.println(ex.getMessage());
	     }
	        
	     return 0;
	 }

	 @Override
	 public Object getValueAt(int rowIndex, int columnIndex) {
	        
	     try {
	         resultado.absolute(rowIndex + 1);
	         return resultado.getObject(columnIndex + 1);
	     } catch (SQLException ex) {
	        System.out.println(ex.getMessage());
	     }
	     return null;
	        
	 }
	    
	 public String getColumnName(int c){
	     try {
	    	 
	         return metaResultado.getColumnName(c+1);
	     } catch (SQLException ex) {
	        System.out.println(ex.getMessage());
	     }
	        
	     return "";
	 }
}
