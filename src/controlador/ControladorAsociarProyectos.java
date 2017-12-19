package controlador;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modelo.ConsultasTablas;
import modelo.ModeloTabla;
import vista.AsociarProyectos;

public class ControladorAsociarProyectos extends ControladorAbstracto{

	private AsociarProyectos ventanaAsociar;
	private ConsultasTablas consultas = new ConsultasTablas();
	private JComboBox listaEmpleados;
	private JComboBox listaProyectos;
	private JComboBox desplFiltrado;
	private String filtro;
	
	public ControladorAsociarProyectos(JDialog ventanaPadre){
		ventanaAsociar = (AsociarProyectos) ventanaPadre;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		
		switch(comando){
			
			case("OK"):
			
				String titulo = listaProyectos.getSelectedItem().toString();
				String nif = listaEmpleados.getSelectedItem().toString();
				
				boolean lanzaConsulta = consultas.insertarProyEmpleado(nif, titulo);
				
				if(lanzaConsulta){
					JOptionPane.showMessageDialog(ventanaAsociar, "Proyecto asociado");
					//ResultSet rs = consultas.listarTabla("*",consultas.TABLA_PROY_EMPLEADOS);
					ResultSet rs = consultas.listarProyectosEmpleados();
					recargaTabla(rs);
					
				}else{
					JOptionPane.showMessageDialog(ventanaAsociar, "Error al asociar proyecto");
				}
				break;
				
			case("Filtro"):
				filtro = ventanaAsociar.getDesplSelectFiltro().getSelectedItem().toString();
				desplFiltrado.removeAllItems();
				
				if(filtro.equals("empleados")){
					ResultSet rs = consultas.listarTabla("nif",consultas.TABLA_EMPLEADOS);
					
					try {
						while(rs.next()){
							desplFiltrado.addItem(rs.getString(1));
							
						}
						ventanaAsociar.setDesplListFiltrada(desplFiltrado);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else if(filtro.equals("proyectos")){
					ResultSet rs = consultas.listarTabla("titulo",consultas.TABLA_PROYECTOS);
					try {
						while(rs.next()){
							desplFiltrado.addItem(rs.getString(1));
						}
						ventanaAsociar.setDesplListFiltrada(desplFiltrado);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				break;//fin filtro
			
			case("Aplicar Filtro"):
				String eleccion = desplFiltrado.getSelectedItem().toString();
				ResultSet rs = null;
				if(filtro != null){
					if(filtro.equals("empleados")){
						
						rs = consultas.listProyEmplFilt("nif", eleccion);
					}
					else if(filtro.equals("proyectos")){
						
						rs = consultas.listProyEmplFilt("titulo", eleccion);
					}
					recargaTabla(rs);
				}
				
				break;
			case("Cancelar"):
				ventanaAsociar.dispose();
				break;
		}
	}
	
	@Override
	public void windowOpened(WindowEvent e){
		
		listaEmpleados = ventanaAsociar.getDesplegEmpleados();
		listaProyectos = ventanaAsociar.getDesplegProyectos();
		desplFiltrado = ventanaAsociar.getDesplListFiltrada();
		ResultSet rs = consultas.listarProyectosEmpleados();
		ResultSet rs2 = consultas.listarTabla("nif",consultas.TABLA_EMPLEADOS);
		ResultSet rs3 = consultas.listarTabla("titulo",consultas.TABLA_PROYECTOS);
		
		try {
			
			while(rs2.next()){
				listaEmpleados.addItem(rs2.getString(1));
			}
			
			while(rs3.next()){
				listaProyectos.addItem(rs3.getString(1));
			}
			while(rs.next()){
				desplFiltrado.addItem(rs.getString(1));
			}
			
			recargaTabla(rs);
			
			consultas.cerrarConexion();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void recargaTabla(ResultSet rs){
		ventanaAsociar.panelTabla.removeAll();
		ventanaAsociar.panelTabla.setLayout(new BorderLayout(0,0));
		{
			JTable table = new JTable(new ModeloTabla(rs));
			//table.setFillsViewportHeight(true); 
			ventanaAsociar.panelTabla.add(new JScrollPane(table));
		}
		ventanaAsociar.setDesplegEmpleados(listaEmpleados);
		ventanaAsociar.setDesplegProyectos(listaProyectos);
		
		ventanaAsociar.validate();
	}
}