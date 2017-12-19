package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.ControladorAbstracto;
import modelo.Empleado;
import controlador.ControladorAltaEmpleado;
import controlador.ControladorModificaEmpleados;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTextField;

public class AltaEmpleado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nifText;
	private JTextField nombreText;
	private JTextField apellidoTexto;
	private JTextField fechaNacTexto;
	
	public AltaEmpleado(JFrame ventana,boolean modal,boolean nuevo,Empleado empleado) {

		super(ventana,modal);
		
		ControladorAbstracto controlador;
		
		if(nuevo){//si se va a dar de alta un usuario
			controlador = (ControladorAltaEmpleado) new ControladorAltaEmpleado(this);
			setTitle("Alta Empleado");
		}else{//si se va a modificar un usuario
			controlador = (ControladorModificaEmpleados) new ControladorModificaEmpleados(this,empleado);
			addWindowListener(controlador);
			setTitle("Modifica Empleado");
		}
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JPanel panel = new JPanel();
			panel.setMaximumSize(new Dimension(400, 50));
			contentPanel.add(panel);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			{
				JLabel labelNIF = new JLabel("NIF:");
				panel.add(labelNIF);
				Component horizontalStrut = Box.createHorizontalStrut(20);
				horizontalStrut.setMaximumSize(new Dimension(95, 32767));
				panel.add(horizontalStrut);
			}
			{
				nifText = new JTextField();
				nifText.setMaximumSize(new Dimension(200, 20));
				panel.add(nifText);
				nifText.setColumns(5);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setMaximumSize(new Dimension(400, 50));
			contentPanel.add(panel);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			{
				JLabel lblNombre = new JLabel("Nombre:");
				panel.add(lblNombre);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				horizontalStrut.setMaximumSize(new Dimension(70, 32767));
				panel.add(horizontalStrut);
			}
			{
				nombreText = new JTextField();
				nombreText.setMaximumSize(new Dimension(200, 20));
				nombreText.setColumns(5);
				panel.add(nombreText);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setMaximumSize(new Dimension(400, 50));
			contentPanel.add(panel);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			{
				JLabel lblApellido = new JLabel("Apellido:");
				panel.add(lblApellido);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				horizontalStrut.setMaximumSize(new Dimension(70, 32767));
				panel.add(horizontalStrut);
			}
			{
				apellidoTexto = new JTextField();
				apellidoTexto.setMaximumSize(new Dimension(200, 20));
				apellidoTexto.setColumns(5);
				panel.add(apellidoTexto);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setMaximumSize(new Dimension(400, 50));
			contentPanel.add(panel);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			{
				JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
				panel.add(lblFechaNacimiento);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				horizontalStrut.setMaximumSize(new Dimension(15, 32767));
				panel.add(horizontalStrut);
			}
			{
				fechaNacTexto = new JTextField();
				fechaNacTexto.setMaximumSize(new Dimension(200, 20));
				fechaNacTexto.setColumns(5);
				panel.add(fechaNacTexto);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(controlador);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancelar");
				cancelButton.addActionListener(controlador);
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
	}//fin constructor
	
	public JTextField getNifText() {
		return nifText;
	}

	public void setNifText(JTextField nifText) {
		this.nifText = nifText;
	}

	public JTextField getNombreText() {
		return nombreText;
	}

	public void setNombreText(JTextField nombreText) {
		this.nombreText = nombreText;
	}

	public JTextField getApellidoTexto() {
		return apellidoTexto;
	}

	public void setApellidoTexto(JTextField apellidoTexto) {
		this.apellidoTexto = apellidoTexto;
	}

	public JTextField getFechaNacTexto() {
		return fechaNacTexto;
	}

	public void setFechaNacTexto(JTextField fechaNacTexto) {
		this.fechaNacTexto = fechaNacTexto;
	}

}
