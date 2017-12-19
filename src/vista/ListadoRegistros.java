package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ControladorAbstracto;
import controlador.ControladorListarRegistros;

import javax.swing.JLabel;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JTable;

public class ListadoRegistros extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JComboBox desplegTablas;
	public JPanel panelTable;
	public JFrame ventana;
	public JButton btnModificar;

	public ListadoRegistros(JFrame ventana,boolean modal) {
		
		super(ventana,modal);
		this.ventana = ventana;
		
		ControladorListarRegistros controlador = new ControladorListarRegistros(this);
		
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			{
				Component horizontalStrut = Box.createHorizontalStrut(20);
				horizontalStrut.setMaximumSize(new Dimension(60, 32767));
				panel.add(horizontalStrut);
				JLabel lblMostrar = new JLabel("Mostrar:");
				lblMostrar.setAlignmentX(Component.CENTER_ALIGNMENT);
				panel.add(lblMostrar);
				panel.add(Box.createHorizontalStrut(20));
			}
			{
				desplegTablas = new JComboBox();
				desplegTablas.setActionCommand("Desplegable");
				desplegTablas.addActionListener(controlador);
				desplegTablas.setMaximumSize(new Dimension(200, 32767));
				panel.add(desplegTablas);
			}
		}
		{
			panelTable = new JPanel();
			contentPanel.add(panelTable, BorderLayout.CENTER);
			panelTable.setLayout(new BorderLayout(0, 0));
			{
				table = new JTable();
				panelTable.add(table);
				table.addMouseListener(controlador);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Listar/Actualizar");
				okButton.setActionCommand("Listar");
				buttonPane.add(okButton);
				okButton.addActionListener(controlador);
				getRootPane().setDefaultButton(okButton);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.setActionCommand("Modificar");
				btnModificar.addActionListener(controlador);
				buttonPane.add(btnModificar);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancelar");
				cancelButton.addActionListener(controlador);
				buttonPane.add(cancelButton);
			}
		}
		
		addWindowFocusListener(controlador);
		addWindowListener(controlador);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}//fin constructor
	
	public JTable getTable() {
		return table;
	}


	public void setTable(JTable table) {
		this.table = table;
	}


	public JComboBox getDesplegTablas() {
		return desplegTablas;
	}


	public void setDesplegTablas(JComboBox desplegTablas) {
		this.desplegTablas = desplegTablas;
	}

}
