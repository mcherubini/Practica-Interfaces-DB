package controlador;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import modelo.ConsultasTablas;
import modelo.Proyecto;
import vista.AltaProyecto;


public class ControladorAltaProyecto extends ControladorAbstracto{

	protected JTextField titulo;
	protected JTextField descripcion;
	protected JTextField fechaInicio;
	protected JTextField fechaFin;
	protected ConsultasTablas consultas;
	protected AltaProyecto ventanaProyecto;
	
	public ControladorAltaProyecto(){}
	
	public ControladorAltaProyecto(JDialog ventanaProyecto){
		this.ventanaProyecto = (AltaProyecto) ventanaProyecto;
		
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
				
				Proyecto proyecto = new Proyecto(titulo.getText(),descripcion.getText(),fechaInicio.getText(),
						fechaFin.getText());
				boolean lanzaConsulta = consultas.insertarProyecto(proyecto);
				
				if(lanzaConsulta){
					JOptionPane.showMessageDialog(ventanaProyecto, "Proyecto creado");
					ventanaProyecto.dispose();
				}else{
					JOptionPane.showMessageDialog(ventanaProyecto, "Error al insertar Proyecto");
				}
			}else{
				JOptionPane.showMessageDialog(ventanaProyecto, "Los siguientes campos son incorrectos: " + resultado);
			}
			
			break;
		
		case ("Cancelar"):
			
			ventanaProyecto.dispose();
			break;
		}
	}
	
	protected String validacion(){
        /* modificar para que devuelva string con los campos que deben ser rellenados
        con un StringBuilder o lo mas eficiente para concatenar String*/
        
		titulo = ventanaProyecto.getTituloText();
		descripcion = ventanaProyecto.getDescripcionText();
		fechaInicio = ventanaProyecto.getFechaInicioText();
		fechaFin = ventanaProyecto.getFechaFinText();
		
		StringBuilder textoError = new StringBuilder("");
		
        Border borderRojo = BorderFactory.createLineBorder(Color.RED);
       
        if(!Pattern.matches("[a-zA-Z]+[0-9]+", titulo.getText())){
        	
            textoError.append("titulo, ");
            titulo.setBorder(borderRojo);
            
        }
        
        if(!Pattern.matches("^[a-zA-Z][a-zA-Z-\\s]+", descripcion.getText())){
            
            textoError.append("descripcion, ");
            descripcion.setBorder(borderRojo);
        }
        
        if(!Pattern.matches("\\d{4}-\\d{1,2}-\\d{1,2}", fechaInicio.getText())){
            
            textoError.append("fecha inicio, ");
            fechaInicio.setBorder(borderRojo);
        }
        
        if(!Pattern.matches("\\d{4}-\\d{1,2}-\\d{1,2}", fechaFin.getText())){
            
            textoError.append("fecha fin");
            fechaFin.setBorder(borderRojo);
        }
        
        return textoError.toString();
    }

}
