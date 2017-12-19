package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import modelo.ConsultasTablas;
import modelo.Empleado;
import vista.AltaEmpleado;

public class ControladorModificaEmpleados extends ControladorAltaEmpleado{
	private Empleado empleado;
	private String anteriorNIF;
	
	public ControladorModificaEmpleados(JDialog ventanaEmpleado,Empleado empleado){
		this.ventanaEmpleado = (AltaEmpleado) ventanaEmpleado;
		this.empleado = empleado;
	}
	
	@Override
	public void windowOpened(WindowEvent e){
		nombre = this.ventanaEmpleado.getNombreText();
		apellido = this.ventanaEmpleado.getApellidoTexto();
		anteriorNIF = empleado.getNif();
		nif = this.ventanaEmpleado.getNifText();
		fechaNacimiento = this.ventanaEmpleado.getFechaNacTexto();
		
		System.out.println("vacio" + anteriorNIF);
		nif.setText(empleado.getNif());
		nombre.setText(empleado.getNombre());
		apellido.setText(empleado.getApellido());
		fechaNacimiento.setText(empleado.getFechaNacimiento());
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
				Empleado empleado = new Empleado(nif.getText(),nombre.getText(),
						apellido.getText(),fechaNacimiento.getText());
				
				boolean lanzaConsulta = consultas.modificarEmpleado(empleado,anteriorNIF);
				
				if(lanzaConsulta){
					JOptionPane.showMessageDialog(ventanaEmpleado, "Empleado modificado");
				}else{
					JOptionPane.showMessageDialog(ventanaEmpleado, "Error al modificar Empleado");
				}
				
				ventanaEmpleado.dispose();
			}else{
				JOptionPane.showMessageDialog(ventanaEmpleado, "Los siguientes campos son incorrectos: " + resultado);
			}
				
			break;
		case ("Cancelar"):
			ventanaEmpleado.dispose();
			break;
		}
	}

}
