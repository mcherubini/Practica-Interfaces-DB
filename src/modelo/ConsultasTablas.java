package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsultasTablas extends Conexion {

	public static final String TABLA_EMPLEADOS = "empleados";
	public static final String TABLA_PROYECTOS = "proyectos";
	public static final String TABLA_PROY_EMPLEADOS = "proyectos_empleados";
	private Connection conex;
	
	public boolean insertarEmpleado(Empleado empleado){
		
		conex = conectar();
		
		String nif = empleado.getNif();
		String nombre = empleado.getNombre();
		String apellido = empleado.getApellido();
		String fechaNac = empleado.getFechaNacimiento();
		
		try {
			Statement statement = conex.createStatement();
			
			statement.executeUpdate("INSERT INTO " + TABLA_EMPLEADOS + " VALUES('" +
					
					nif + "','" + nombre + "','"  + apellido +  "','" + fechaNac +  "');");
			
			conex.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean modificarEmpleado(Empleado empleado,String oldNIF){//modifica empleado segun campo nif pasado
		
		conex = conectar();
		
		String nif = empleado.getNif();
		String nombre = empleado.getNombre();
		String apellido = empleado.getApellido();
		String fechaNac = empleado.getFechaNacimiento();
		
		try {
			Statement statement = conex.createStatement();
			
			statement.executeUpdate("UPDATE "+ TABLA_EMPLEADOS +  " SET nif ='" + nif  + "', nombre ='"+
			nombre + "', apellido ='"+ apellido + "',fecha_nacimiento ='" + fechaNac + "' WHERE nif='" + oldNIF +
			"';");
			
			conex.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
public boolean modificarProyecto(Proyecto proyecto,String oldTitulo){//modifica proyecto segun campo titulo pasado
		
		conex = conectar();
		
		String titulo = proyecto.getTitulo();
		String descripcion = proyecto.getDescripcion();
		String fechaIni = proyecto.getFechaInicio();
		String fechaFin = proyecto.getFechaFin();
		
		try {
			Statement statement = conex.createStatement();
			
			statement.executeUpdate("UPDATE "+ TABLA_PROYECTOS +  " SET titulo ='" + titulo  + "', descripcion ='"+
			descripcion + "', fecha_inicio ='"+ fechaIni + "',fecha_fin ='" + fechaFin + "' WHERE titulo='" + oldTitulo +
			"';");
			
			conex.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean insertarProyecto(Proyecto proyecto){
		
		conex = conectar();
		
		String titulo = proyecto.getTitulo();
		String descripcion = proyecto.getDescripcion();
		String fechaInicio = proyecto.getFechaInicio();
		String fechaFin = proyecto.getFechaFin();
		
		try {
			Statement statement = conex.createStatement();
			
			statement.executeUpdate("INSERT INTO " + TABLA_PROYECTOS + " VALUES('" +
					titulo + "','" + descripcion + "','"  + fechaInicio +  "','" + fechaFin +  "');");
			
			conex.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}//fin insertar proyecto
	
	public boolean insertarProyEmpleado(String nif,String titulo){//relaciona proyecto con empleado
		conex = conectar();
		
		try {
			Statement statement = conex.createStatement();
			
			statement.executeUpdate("INSERT INTO " + TABLA_PROY_EMPLEADOS + " VALUES('" +
					nif + "','" + titulo + "');");
			
			conex.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	
	public ResultSet listarTablas(){
		conex = conectar();
		
		try {
			Statement statement = conex.createStatement();
			
			ResultSet rs =  statement.executeQuery("SHOW TABLES");
			
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ResultSet listarProyectosEmpleados(){//muestra proyectos asociados a los empleados con todos los datos de los empleados
		
		conex = conectar();
		
		try {
			Statement statement = conex.createStatement();
			
			ResultSet rs =  statement.executeQuery("SELECT empleados.* , proyectos_empleados.titulo "+
			"FROM proyectos_empleados INNER JOIN empleados on empleados.nif = proyectos_empleados.nif;");
			
			//conex.close();
			
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet listProyEmplFilt(String campo, String filtro){//lista proyecto asociado a empleados filtrando segun diferentes campos
conex = conectar();
		
		try {
			Statement statement = conex.createStatement();
			
			ResultSet rs =  statement.executeQuery("SELECT empleados.* , proyectos_empleados.titulo " + 
					"FROM proyectos_empleados INNER JOIN empleados on empleados.nif = proyectos_empleados.nif " +
					"WHERE proyectos_empleados." + campo + "='"+ filtro +  "';");
			
			//conex.close();
			
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet listarTabla(String columnas,String tabla){
		
		conex = conectar();
		
		try {
			Statement statement = conex.createStatement();
			
			ResultSet rs =  statement.executeQuery("SELECT " + columnas + " FROM " + tabla);
			
			//conex.close();
			
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void cerrarConexion(){
		try {
			conex.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
