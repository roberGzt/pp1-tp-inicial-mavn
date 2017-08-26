package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JButton;

public class VistaLocalidades extends JFrame {

	private JPanel panel;
	private JTable tablaLocalidades;
	private String[] nombreColumnas = { "Localidad" };
	private DefaultTableModel modelLocalidades;
	JButton btnAgregar = new JButton("Agregar");
	JButton btnEditar = new JButton("Editar");
	JButton btnEliminar = new JButton("Eliminar");

	public VistaLocalidades() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.setBounds(100, 100, 548, 455);

		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);

		JScrollPane spLocalidades = new JScrollPane();
		spLocalidades.setBounds(49, 11, 446, 180);
		panel.add(spLocalidades);

		//Para hacer la tabla no editable.
		modelLocalidades = new DefaultTableModel(null, nombreColumnas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaLocalidades = new JTable(modelLocalidades);
		tablaLocalidades.setBounds(503, 312, -376, -257);

		spLocalidades.setViewportView(tablaLocalidades);

		btnAgregar.setBounds(49, 244, 91, 23);
		panel.add(btnAgregar);

		btnEditar.setBounds(211, 244, 91, 23);
		panel.add(btnEditar);

		btnEliminar.setBounds(372, 244, 91, 23);
		panel.add(btnEliminar);

		// panel.add(table);
	}

	public JPanel getPanel() {
		return panel;
	}

	public JTable getTablaLocalidades() {
		return tablaLocalidades;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getModelLocalidades() {
		return modelLocalidades;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void mostrar() {
		this.setVisible(true);
	}

}
