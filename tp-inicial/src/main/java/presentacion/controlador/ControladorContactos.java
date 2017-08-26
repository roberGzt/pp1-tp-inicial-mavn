package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JOptionPane;

import dto.PersonaDTO;
import modelo.Agenda;
import modelo.Validador;
import patrones.observer.Observador;
import presentacion.vista.FormularioContactos;

public class ControladorContactos implements ActionListener, Observador {
	private static ControladorContactos instancia;
	private static FormularioContactos formularioContactos;
	private static Agenda agenda;

	private ControladorContactos() {
		agenda = Agenda.getInstance();
		agenda.registrarObservador(this);

	}

	public static ControladorContactos getInstance() {
		if (instancia == null)
			instancia = new ControladorContactos();
		return instancia;
	}

	public void abrirVentana(PersonaDTO persona) {
		formularioContactos = new FormularioContactos(this);
		formularioContactos.setLocalidades(agenda.getLocalidades());
		formularioContactos.setTiposDeContacto(agenda.getTiposDeContacto());
		formularioContactos.cargarCombos();
		formularioContactos.setPersona(persona);
		if (persona == null) {
			limpiarCampos();
		} else
			cargarCampos(persona);

		formularioContactos.setVisible(true);
	}

	private void limpiarCampos() {

		formularioContactos.getTfAltura().setText(null);
		formularioContactos.getTfCalle().setText(null);
		formularioContactos.getTfDepto().setText(null);
		formularioContactos.getTfEmail().setText(null);
		formularioContactos.getTfNombre().setText(null);
		formularioContactos.getTfPiso().setText(null);
		formularioContactos.getTfTelefono().setText(null);
		formularioContactos.resetCombos();
	}

	private void cargarCampos(PersonaDTO persona) {
		formularioContactos.setPersona(persona);
		formularioContactos.getTfAltura().setText(Integer.toString(persona.getDomicilio().getAltura()));
		formularioContactos.getTfCalle().setText(persona.getDomicilio().getCalle());
		formularioContactos.getTfDepto().setText(persona.getDomicilio().getDepartamento());
		formularioContactos.getTfEmail().setText(persona.getMail());
		formularioContactos.getTfNombre().setText(persona.getNombre());
		formularioContactos.getTfPiso().setText(Integer.toString(persona.getDomicilio().getPiso()));
		formularioContactos.getTfTelefono().setText(persona.getTelefono());
		formularioContactos.setCombos();
		formularioContactos.getDatePicker().setDate(Date.valueOf(persona.getFechaCumpleaños().toString()));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == formularioContactos.getBtnGuardarPersona()) {
			guardarPersona();
		}

	}

	private void guardarPersona() {
		if (camposSonValidos()) {
			formularioContactos.cargarDatosEnDTO();
			PersonaDTO persona = formularioContactos.getPersona();
			int idDomicilio = agenda.agregarDomicilio(persona.getDomicilio());
			if (idDomicilio != -1) {
				persona.getDomicilio().setIdDomicilio(idDomicilio);
				if (persona.getIdPersona() == 0)
					agenda.agregarPersona(persona);
				else
					agenda.modificarPersona(persona);
				formularioContactos.dispose();
			} else {
				JOptionPane.showMessageDialog(formularioContactos,
						"No se pudo guardar el contacto. No se pudo guardar el domicilio", "Error de alta de contacto",
						JOptionPane.ERROR_MESSAGE);

			}
		}
	}

	private boolean camposSonValidos() {
		boolean sonValidos = false;
		if (algunCampoEstaVacio()) {
			JOptionPane.showMessageDialog(formularioContactos,
					"No se pudo editar/agregar el contacto. Alg�n campo esta vacio", "Error de contacto",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			String nombre = formularioContactos.getTfNombre().getText();
			String telefono = formularioContactos.getTfTelefono().getText();
			String calle = formularioContactos.getTfCalle().getText();
			String altura = formularioContactos.getTfAltura().getText();
			String piso = formularioContactos.getTfPiso().getText();
			String depto = formularioContactos.getTfDepto().getText();
			String email = formularioContactos.getTfEmail().getText();
			java.util.Date fechaNac = formularioContactos.getDatePicker().getDate();

			sonValidos = Validador.esStringNoEmpezadoEnEspacios(nombre, 45)
					&& Validador.esStringNoEmpezadoEnEspacios(telefono, 20)
					&& Validador.esStringNoEmpezadoEnEspacios(calle, 45) && Validador.esIntValido(altura)
					&& Validador.esIntValido(piso) && Validador.esStringNoEmpezadoEnEspacios(depto, 15)
					&& Validador.esMailValido(email, 45)
					&& Validador.esFechaNacimientoValida(fechaNac);

			if (!sonValidos) {
				JOptionPane.showMessageDialog(formularioContactos,
						"Error de validaci�n, verifica que los campos esten ingresados correctamente.",
						"Error de contacto", JOptionPane.ERROR_MESSAGE);
			}
		}

		return sonValidos;
	}

	private boolean algunCampoEstaVacio() {
		return formularioContactos.getTfAltura().getText().isEmpty()
				|| formularioContactos.getTfCalle().getText().isEmpty()
				|| formularioContactos.getTfDepto().getText().isEmpty()
				|| formularioContactos.getTfEmail().getText().isEmpty()
				|| formularioContactos.getTfTelefono().getText().isEmpty()
				|| formularioContactos.getTfNombre().getText().isEmpty()
				|| formularioContactos.getTfPiso().getText().isEmpty();
	}

	@Override
	public void update() {
		if (formularioContactos != null) {
			formularioContactos.setLocalidades(agenda.getLocalidades());
			formularioContactos.setTiposDeContacto(agenda.getTiposDeContacto());
			formularioContactos.cargarCombos();
		}
	}

}
