package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.AltaEmpleado;
import vista.AltaProyecto;
import vista.AsociarProyectos;
import vista.ListadoRegistros;
import vista.VentanaPrincipal;

public class ControladorVentanaPrincipal implements ActionListener{

	private VentanaPrincipal ventanaPrincipal;
	
	public ControladorVentanaPrincipal(VentanaPrincipal ventanaPrincipal){
		this.ventanaPrincipal =  ventanaPrincipal;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String comando = arg0.getActionCommand();
		
		switch(comando){
			
		case ("Alta Empleado"):
			new AltaEmpleado(ventanaPrincipal,true,true,null);
			break;
			
		case("Alta Proyecto"):
			new AltaProyecto(ventanaPrincipal,true,true,null);
			break;
		
		case("Asociar Proyecto"):
			new AsociarProyectos(ventanaPrincipal,true);
			break;
		case("Listar Registro"):
			new ListadoRegistros(ventanaPrincipal,true);
			break;
		
		}
		
	}

	

}
