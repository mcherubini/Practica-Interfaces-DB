package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	protected Connection conexion = null;
	private static String db = "jdbc:mysql://localhost/practica4_interfaces" ;
	private static String user ="root";
	private static String p = "***";
	
	public Connection conectar(){
		
		try {
			
		Class.forName("com.mysql.jdbc.Driver").newInstance();//carga el driver
		conexion =DriverManager.getConnection(db,user,p);//establece la conexion
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conexion;
	}

}
