package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import modelo.ConsultasTablas;
import modelo.Empleado;
import vista.AltaEmpleado;


public class ControladorAltaEmpleado extends ControladorAbstracto{

	protected AltaEmpleado ventanaEmpleado;
	protected ConsultasTablas consultas;
	protected JTextField nombre;
	protected JTextField apellido;
	protected JTextField nif;
	protected JTextField fechaNacimiento;
	
	public ControladorAltaEmpleado(){}
	
	public ControladorAltaEmpleado(JDialog ventanaEmpleado){
		this.ventanaEmpleado = (AltaEmpleado) ventanaEmpleado;
		
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
				
				boolean lanzaConsulta = consultas.insertarEmpleado(empleado);
				
				if(lanzaConsulta){
					JOptionPane.showMessageDialog(ventanaEmpleado, "Empleado creado");
				}else{
					JOptionPane.showMessageDialog(ventanaEmpleado, "Error al insertar Empleado");
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
	
	protected String validacion(){
        /* modificar para que devuelva string con los campos que deben ser rellenados
        con un StringBuilder o lo mas eficiente para concatenar String*/
        
		nombre = ventanaEmpleado.getNombreText();
		apellido = ventanaEmpleado.getApellidoTexto();
		nif = ventanaEmpleado.getNifText();
		fechaNacimiento = ventanaEmpleado.getFechaNacTexto();
		StringBuilder textoError = new StringBuilder("");
		
        Border borderRojo = BorderFactory.createLineBorder(Color.RED);
        
        if(!Pattern.matches("[a-zA-Z]+", nombre.getText())){
        	
            textoError.append("nombre, ");
            nombre.setBorder(borderRojo);
            
        }
        
        if(!Pattern.matches("[a-zA-Z]+", apellido.getText())){
            
            textoError.append("apellido, ");
            apellido.setBorder(borderRojo);
        }
        
        if(!Pattern.matches("\\d{8}[A-HJ-NP-TV-Z]", nif.getText())){
            
            textoError.append("dni, ");
            nif.setBorder(borderRojo);
        }
        
        if(!Pattern.matches("\\d{4}-\\d{1,2}-\\d{1,2}", fechaNacimiento.getText())){
            
            textoError.append("fecha Nacimiento");
            fechaNacimiento.setBorder(borderRojo);
        }
        
        return textoError.toString();
    }

}
