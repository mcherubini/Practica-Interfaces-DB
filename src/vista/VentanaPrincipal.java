package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.ControladorVentanaPrincipal;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JSeparator;
import java.awt.Dimension;
import java.awt.Rectangle;

public class VentanaPrincipal extends JFrame {

	private JPanel PanelPrincipal;

	public VentanaPrincipal() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		PanelPrincipal = new JPanel();
		PanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelPrincipal);
		PanelPrincipal.setLayout(new BoxLayout(PanelPrincipal, BoxLayout.Y_AXIS));
		
		JButton btnAltaEmpleado = new JButton("Alta Empleado");
		btnAltaEmpleado.setAlignmentX(Component.CENTER_ALIGNMENT);
		PanelPrincipal.add(btnAltaEmpleado);
		
		PanelPrincipal.add(Box.createVerticalStrut(20));
		
		JButton btnAltaProyecto = new JButton("Alta Proyecto");
		btnAltaProyecto.setAlignmentX(Component.CENTER_ALIGNMENT);
		PanelPrincipal.add(btnAltaProyecto);
		
		PanelPrincipal.add(Box.createVerticalStrut(20));
		
		JButton btnListarTodo = new JButton("Listar Todo");
		btnListarTodo.setAlignmentX(Component.CENTER_ALIGNMENT);
		PanelPrincipal.add(btnListarTodo);
		
		PanelPrincipal.add(Box.createVerticalStrut(20));
		
		JButton btnAsociarProy = new JButton("Asociar Proyecto");
		btnAsociarProy.setAlignmentX(Component.CENTER_ALIGNMENT);
		PanelPrincipal.add(btnAsociarProy);
		
		PanelPrincipal.add(Box.createVerticalStrut(20));
		
		/*				LISTENER CONTROLADOR 						*/
		
		ControladorVentanaPrincipal controlador = new ControladorVentanaPrincipal(this);
		
		btnAltaEmpleado.setActionCommand("Alta Empleado");
		btnAltaEmpleado.addActionListener(controlador);
		
		btnAltaProyecto.setActionCommand("Alta Proyecto");
		btnAltaProyecto.addActionListener(controlador);
		
		btnAsociarProy.setActionCommand("Asociar Proyecto");
		btnAsociarProy.addActionListener(controlador);
		
		btnListarTodo.setActionCommand("Listar Registro");
		btnListarTodo.addActionListener(controlador);
	}
	
}
