package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import dto.LocalidadDTO;
import dto.TipoDeContactoDTO;
import modelo.Agenda;
import modelo.Validador;
import patrones.observer.Observador;
import presentacion.vista.VistaLocalidades;
import presentacion.vista.VistaTiposDeContacto;

public class ControladorGestionDeTiposContacto implements ActionListener, Observador {

	private static ControladorGestionDeTiposContacto controlador = null;
	private Agenda agenda;
	private VistaTiposDeContacto vistaTipoDeContacto;
	private List<TipoDeContactoDTO> tiposDeContactoEnTabla;

	private ControladorGestionDeTiposContacto() {
		this.agenda = Agenda.getInstance();
		agenda.registrarObservador(this);
		this.vistaTipoDeContacto = new VistaTiposDeContacto();
		this.vistaTipoDeContacto.getBtnAgregar().addActionListener(this);
		this.vistaTipoDeContacto.getBtnEditar().addActionListener(this);
		this.vistaTipoDeContacto.getBtnEliminar().addActionListener(this);
	}

	public static ControladorGestionDeTiposContacto getInstance() {
		if (controlador == null)
			controlador = new ControladorGestionDeTiposContacto();
		return controlador;
	}

	public void abrirVentana() {
		vistaTipoDeContacto.mostrar();
		inicializar();
	}

	public void inicializar() {
		updateTabla();
	}

	private void updateTabla() {
		this.vistaTipoDeContacto.getModelTipoDeContacto().setRowCount(0); // Para vaciar la tabla
		this.vistaTipoDeContacto.getModelTipoDeContacto().setColumnCount(0);
		this.vistaTipoDeContacto.getModelTipoDeContacto()
				.setColumnIdentifiers(this.vistaTipoDeContacto.getNombreColumnas());

		this.tiposDeContactoEnTabla = agenda.getTiposDeContacto();
		tiposDeContactoEnTabla.forEach(this::añadirTipoDeContacto);

	}

	private void añadirTipoDeContacto(TipoDeContactoDTO tipoDeContactoDTO) {
		Object[] fila = { tipoDeContactoDTO.getNombre() };
		vistaTipoDeContacto.getModelTipoDeContacto().addRow(fila);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vistaTipoDeContacto.getBtnAgregar()) {
			agregar();
		} else if (e.getSource() == vistaTipoDeContacto.getBtnEditar()) {
			int indexFilaSeleccionada = vistaTipoDeContacto.getTablaTipoDeContacto().getSelectedRow();
			if (indexFilaSeleccionada != -1) {
				editar(indexFilaSeleccionada);
			} else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar una etiqueta");
			}

		} else if (e.getSource() == vistaTipoDeContacto.getBtnEliminar()) {
			int indexFilaSeleccionada = vistaTipoDeContacto.getTablaTipoDeContacto().getSelectedRow();
			if (indexFilaSeleccionada != -1) {
				borrar(indexFilaSeleccionada);
			} else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar una etiqueta");
			}
		}

	}

	private boolean esValido(String nombre) {
		return Validador.esLongitudValida(nombre, 1, 30);
	}

	private void agregar() {
		String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la etiqueta que desea ingresar ");
		if (esValido(nombre)) {
			try {
				this.agenda.agregarTipoDeContacto(new TipoDeContactoDTO(nombre));
				updateTabla();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error, verifique que la etiqueta que desea ingresa no exista");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Error, el nombre ingresado no es valido");
		}

	}

	private void editar(int indexFilaSeleccionada) {
		String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre ");
		if (esValido(nombre)) {
			try {
				this.agenda.modificarTipoDeContacto(
						new TipoDeContactoDTO(tiposDeContactoEnTabla.get(indexFilaSeleccionada).getId(), nombre));
				updateTabla();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error, verifique que la etiqueta que desea ingresa no exista");
			}

		} else {
			JOptionPane.showMessageDialog(null, "Error, el nombre ingresado no es valido");
		}
	}

	private void borrar(int indexFilaSeleccionada) {
		try {
			boolean resultado = this.agenda.borrarTipoDeContacto(
					new TipoDeContactoDTO(tiposDeContactoEnTabla.get(indexFilaSeleccionada).getId()));
			if (resultado == false) 
				JOptionPane.showMessageDialog(null,
						"Error, el tipo de contacto esta siendo utilizado.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error, verifique que la etiqueta no este siendo utilizada en algun contacto");
		}

	}

	@Override
	public void update() {
		updateTabla();
	}

}
