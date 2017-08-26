package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Color;

public class Principal {
	private JFrame frame;
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private JButton btnReporte;
	private DefaultTableModel modelPersonas;
	private String[] nombreColumnas = { "Nombre y apellido", "Teléfono", "Calle", "Altura", "Piso", "Depto",
			"Localidad", "Mail", "FechaNac", "TipoContacto" };
	private JButton btnGestionDeTipos;
	private JButton btnGestionLocalidades;

	public Principal() {
		super();
		initialize();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		frame = new JFrame();
		frame.setBounds(100, 100, 913, 355);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 897, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 11, 877, 182);
		panel.add(spPersonas);

		modelPersonas = new DefaultTableModel(null, nombreColumnas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaPersonas = new JTable(modelPersonas);

		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);

		spPersonas.setViewportView(tablaPersonas);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, 228, 89, 23);
		panel.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 228, 89, 23);
		panel.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, 228, 89, 23);
		panel.add(btnBorrar);

		btnReporte = new JButton("Reporte");
		btnReporte.setBounds(307, 228, 89, 23);
		panel.add(btnReporte);

		btnGestionLocalidades = new JButton("Gestion de Localidades");
		btnGestionLocalidades.setBounds(406, 228, 151, 23);
		panel.add(btnGestionLocalidades);

		btnGestionDeTipos = new JButton("Gestion de Tipos de Contacto");
		btnGestionDeTipos.setBounds(567, 228, 192, 23);
		panel.add(btnGestionDeTipos);
	}

	public void show() {
		this.frame.setVisible(true);
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnReporte() {
		return btnReporte;
	}

	public JButton getBtnGestionDeTipos() {
		return btnGestionDeTipos;
	}

	public JButton getBtnGestionLocalidades() {
		return btnGestionLocalidades;
	}

	public DefaultTableModel getModelPersonas() {
		return modelPersonas;
	}

	public JTable getTablaPersonas() {
		return tablaPersonas;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
}
