package controlador;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelo.ConsultasTablas;
import modelo.Empleado;
import modelo.ModeloTabla;
import modelo.Proyecto;
import vista.AltaEmpleado;
import vista.AltaProyecto;
import vista.ListadoRegistros;

public class ControladorListarRegistros extends ControladorAbstracto implements MouseListener{

	private ListadoRegistros ventanaListadoRegistros;
	private ConsultasTablas consultas = new ConsultasTablas();
	private JComboBox desplegableTablas;
	private int row = -1;
	private int col = -1;
	private int totalColumnas = 0;
	private ResultSet rs = null;
	private boolean actualiza = false;
	
	public ControladorListarRegistros(JDialog ventanaListadoRegistros){
		this.ventanaListadoRegistros = (ListadoRegistros) ventanaListadoRegistros;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		
		switch(comando){
			
			case("Listar"):
				String eleccion = desplegableTablas.getSelectedItem().toString();
				consultas = new ConsultasTablas();
				desplegableTablas = ventanaListadoRegistros.getDesplegTablas();
				rs = consultas.listarTabla("*", eleccion);
				recargaTabla(rs);
				
				if(!eleccion.equals("proyectos_empleados")){
					actualiza = true;
					ventanaListadoRegistros.btnModificar.setEnabled(true);
				}
				
				break;
			case("Cancelar"):
				ventanaListadoRegistros.dispose();
				break;
			case("Modificar"):
				
				if(actualiza){
					String[] filaSelect = new String[totalColumnas];
					String tablaSelect = desplegableTablas.getSelectedItem().toString();
					
					for(int i = 0;i<totalColumnas;i++){
			        	filaSelect[i] = ventanaListadoRegistros.getTable().getValueAt(row, i).toString();
			        }
					
					switch(tablaSelect){
						case ("empleados"):
							AltaEmpleado altaEmpleado = new AltaEmpleado(ventanaListadoRegistros.ventana,
									true,false,new Empleado(filaSelect[0],filaSelect[1],filaSelect[2],filaSelect[3]));
						
							break;
						case ("proyectos"):
							
							AltaProyecto altaProyecto = new AltaProyecto(ventanaListadoRegistros.ventana,true,false,
									new Proyecto(filaSelect[0],filaSelect[1],filaSelect[2],filaSelect[3]));
						
						break;
					}//fin switch anidado
					
				}
			
				break;//fin modificar
				
			case("Desplegable"):
				ventanaListadoRegistros.btnModificar.setEnabled(false);
				actualiza = false;
				break;
		}
	}
	
	@Override
	public void windowOpened(WindowEvent e){
		desplegableTablas = ventanaListadoRegistros.getDesplegTablas();
		
		ResultSet rs = consultas.listarTablas();
		
		try {
			while(rs.next()){
				desplegableTablas.addItem(rs.getString(1));
			}
			
			ventanaListadoRegistros.setDesplegTablas(desplegableTablas);
			consultas.cerrarConexion();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
	}
	
	public void recargaTabla(ResultSet rs){
		
		ventanaListadoRegistros.panelTable.removeAll();
		
		{
			
			JTable table = new JTable(new ModeloTabla(rs));
			table.addMouseListener(this);
			//table.setFillsViewportHeight(true); 
			ventanaListadoRegistros.panelTable.add(new JScrollPane(table));
			ventanaListadoRegistros.setTable(table);
			
		}
		ventanaListadoRegistros.setDesplegTablas(desplegableTablas);
		
		ventanaListadoRegistros.validate();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
    public void mouseReleased(MouseEvent evt) 
    {
    	if(!estaVacio())
    	{
	        row = ventanaListadoRegistros.getTable().rowAtPoint(evt.getPoint());
	        col = ventanaListadoRegistros.getTable().columnAtPoint(evt.getPoint());
	        
	        totalColumnas = ventanaListadoRegistros.getTable().getColumnCount();
	        
    	}
    	else
    	{
    		
    		row = -1;
    		col = -1;
    		//System.out.println("Contador filas si esta vacio" + table.getRowCount());
    	}
    }
	
	private boolean estaVacio()
	{
		if(ventanaListadoRegistros.getTable().getRowCount() > 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}
