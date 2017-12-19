package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import modelo.ConsultasTablas;
import modelo.Empleado;
import modelo.Proyecto;
import vista.AltaProyecto;

public class ControladorModificaProyectos extends ControladorAltaProyecto{

	private Proyecto proyecto;
	private String anteriorTitulo;
	
	public ControladorModificaProyectos(JDialog ventanaProyecto,Proyecto proyecto){
		this.ventanaProyecto = (AltaProyecto) ventanaProyecto;
		this.proyecto = proyecto;
	}
	
	@Override
	public void windowOpened(WindowEvent e){
		titulo = this.ventanaProyecto.getTituloText();
		descripcion = this.ventanaProyecto.getDescripcionText();
		anteriorTitulo = proyecto.getTitulo();
		fechaInicio = this.ventanaProyecto.getFechaInicioText();
		fechaFin = this.ventanaProyecto.getFechaFinText();
		
		titulo.setText(proyecto.getTitulo());
		descripcion.setText(proyecto.getDescripcion());
		fechaInicio.setText(proyecto.getFechaInicio());
		fechaFin.setText(proyecto.getFechaFin());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		
		switch (comando){
			
		case ("OK"):
			String resultado = validacion();
			
			if(resultado.equals("")){
				
				consultas = new ConsultasTablas();
				//System.out.println(consultas.prueba());
				Proyecto proyecto = new Proyecto(titulo.getText(),descripcion.getText(),
						fechaInicio.getText(),fechaFin.getText());
				
				boolean lanzaConsulta = consultas.modificarProyecto(proyecto,anteriorTitulo);
				
				if(lanzaConsulta){
					JOptionPane.showMessageDialog(ventanaProyecto, "Empleado modificado");
				}else{
					JOptionPane.showMessageDialog(ventanaProyecto, "Error al modificar Empleado");
				}
				
				ventanaProyecto.dispose();
			}else{
				JOptionPane.showMessageDialog(ventanaProyecto, "Los siguientes campos son incorrectos: " + resultado);
			}
				
			break;
		case ("Cancelar"):
			ventanaProyecto.dispose();
			break;
		}
	}
}
