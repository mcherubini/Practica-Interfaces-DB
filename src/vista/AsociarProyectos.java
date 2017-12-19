package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import controlador.ControladorAsociarProyectos;
import controlador.ControladorAbstracto;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

public class AsociarProyectos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JComboBox desplegEmpleados;
	private JComboBox desplegProyectos;
	public JPanel panelTabla;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblFiltrarPor;
	private JComboBox desplSelectFiltro;
	private JLabel lblSeleccionar;
	private JComboBox desplListFiltrada;
	private JButton btnFiltrar;
	
	public AsociarProyectos(JFrame ventana,boolean modal) {
		
		super(ventana,modal);
		setTitle("Asociar Proyectos");
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		ControladorAsociarProyectos controlador = new ControladorAsociarProyectos(this);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panel_1 = new JPanel();
			getContentPane().add(panel_1, BorderLayout.NORTH);
			panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
			{
				JPanel panel = new JPanel();
				panel_1.add(panel);
				panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					lblNewLabel = new JLabel("NIF: ");
					panel.add(lblNewLabel);
				}
				{
					desplegEmpleados = new JComboBox();
					panel.add(desplegEmpleados);
				}
				{
					lblNewLabel_1 = new JLabel("Proyecto: ");
					panel.add(lblNewLabel_1);
				}
				{
					desplegProyectos = new JComboBox();
					panel.add(desplegProyectos);
				}
			}
			{
				panel_2 = new JPanel();
				panel_1.add(panel_2);
				{
					lblFiltrarPor = new JLabel("Filtrar por:");
					panel_2.add(lblFiltrarPor);
				}
				{
					desplSelectFiltro = new JComboBox();
					desplSelectFiltro.addItem("empleados");
					desplSelectFiltro.addItem("proyectos");
					desplSelectFiltro.addActionListener(controlador);
					desplSelectFiltro.setActionCommand("Filtro");
					panel_2.add(desplSelectFiltro);
				}
				{
					lblSeleccionar = new JLabel("Seleccionar: ");
					panel_2.add(lblSeleccionar);
				}
				{
					desplListFiltrada = new JComboBox();
					panel_2.add(desplListFiltrada);
				}
				{
					btnFiltrar = new JButton("Filtrar");
					btnFiltrar.setActionCommand("Aplicar Filtro");
					btnFiltrar.addActionListener(controlador);
					panel_2.add(btnFiltrar);
				}
			}
		}
		{
			panelTabla = new JPanel();
			getContentPane().add(panelTabla, BorderLayout.WEST);
			panelTabla.setLayout(new BorderLayout(0, 0));
			{
				table = new JTable();
				
				panelTabla.add(table);
				//panelTabla.add(table.getTableHeader());
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(controlador);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancelar");
				cancelButton.addActionListener(controlador);
				buttonPane.add(cancelButton);
			}
		}
		
		addWindowListener(controlador);
		setVisible(true);
	}

	public JTable getTable() {
		return table;
	}
	
	public JComboBox getDesplSelectFiltro() {
		return desplSelectFiltro;
	}

	public void setDesplSelectFiltro(JComboBox desplSelectFiltro) {
		this.desplSelectFiltro = desplSelectFiltro;
	}

	public JComboBox getDesplListFiltrada() {
		return desplListFiltrada;
	}

	public void setDesplListFiltrada(JComboBox desplListFiltrada) {
		this.desplListFiltrada = desplListFiltrada;
	}

	public JComboBox getDesplegEmpleados() {
		return desplegEmpleados;
	}

	public void setDesplegEmpleados(JComboBox desplegEmpleados) {
		this.desplegEmpleados = desplegEmpleados;
	}

	public JComboBox getDesplegProyectos() {
		return desplegProyectos;
	}

	public void setDesplegProyectos(JComboBox desplegProyectos) {
		this.desplegProyectos = desplegProyectos;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

}
