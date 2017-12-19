package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlador.ControladorAbstracto;
import modelo.Proyecto;
import controlador.ControladorAltaProyecto;
import controlador.ControladorModificaProyectos;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTextField;

public class AltaProyecto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tituloText;
	private JTextField descripcionText;
	private JTextField fechaInicioText;
	private JTextField fechaFinText;	
	
	public AltaProyecto(JFrame ventana,boolean modal,boolean nuevo,Proyecto proyecto) {

		super(ventana,modal);
		
		ControladorAbstracto controlador;
		
		if(nuevo){//si nuevo proyecto
			controlador = (ControladorAltaProyecto) new ControladorAltaProyecto(this);
			setTitle("Alta Proyecto");
		}else{//sino modifica proyecto
			controlador = (ControladorModificaProyectos) new ControladorModificaProyectos(this,proyecto);
			addWindowListener(controlador);
			setTitle("Modifica Proyecto");
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
				JLabel labelTitulo = new JLabel("Titulo:");
				panel.add(labelTitulo);
				Component horizontalStrut = Box.createHorizontalStrut(20);
				horizontalStrut.setMaximumSize(new Dimension(95, 32767));
				panel.add(horizontalStrut);
			}
			{
				tituloText = new JTextField();
				tituloText.setMaximumSize(new Dimension(200, 20));
				panel.add(tituloText);
				tituloText.setColumns(5);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setMaximumSize(new Dimension(400, 50));
			contentPanel.add(panel);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			{
				JLabel labelDescrip = new JLabel("Descripci\u00F3n:");
				panel.add(labelDescrip);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				horizontalStrut.setMaximumSize(new Dimension(60, 32767));
				panel.add(horizontalStrut);
			}
			{
				descripcionText = new JTextField();
				descripcionText.setMaximumSize(new Dimension(200, 20));
				descripcionText.setColumns(5);
				panel.add(descripcionText);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setMaximumSize(new Dimension(400, 50));
			contentPanel.add(panel);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			{
				JLabel labelFechaInicio = new JLabel("Fecha Inicio:");
				panel.add(labelFechaInicio);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				horizontalStrut.setMaximumSize(new Dimension(55, 32767));
				panel.add(horizontalStrut);
			}
			{
				fechaInicioText = new JTextField();
				fechaInicioText.setMaximumSize(new Dimension(200, 20));
				fechaInicioText.setColumns(5);
				panel.add(fechaInicioText);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setMaximumSize(new Dimension(400, 50));
			contentPanel.add(panel);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			{
				JLabel labelFechaFin = new JLabel("Fecha Fin:");
				panel.add(labelFechaFin);
			}
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				horizontalStrut.setMaximumSize(new Dimension(70, 32767));
				panel.add(horizontalStrut);
			}
			{
				fechaFinText = new JTextField();
				fechaFinText.setMaximumSize(new Dimension(200, 20));
				fechaFinText.setColumns(5);
				panel.add(fechaFinText);
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
	
	public JTextField getTituloText() {
		return tituloText;
	}


	public void setTituloText(JTextField tituloText) {
		this.tituloText = tituloText;
	}


	public JTextField getDescripcionText() {
		return descripcionText;
	}


	public void setDescripcionText(JTextField descripcionText) {
		this.descripcionText = descripcionText;
	}


	public JTextField getFechaInicioText() {
		return fechaInicioText;
	}


	public void setFechaInicioText(JTextField fechaInicioText) {
		this.fechaInicioText = fechaInicioText;
	}


	public JTextField getFechaFinText() {
		return fechaFinText;
	}


	public void setFechaFinText(JTextField fechaFinText) {
		this.fechaFinText = fechaFinText;
	}

}
