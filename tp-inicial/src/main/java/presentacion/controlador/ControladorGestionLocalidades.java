package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import dto.LocalidadDTO;
import modelo.Agenda;
import modelo.Validador;
import patrones.observer.Observador;
import presentacion.vista.VistaLocalidades;

public class ControladorGestionLocalidades implements ActionListener, Observador {
	
	private static ControladorGestionLocalidades controlador=null;
	private Agenda agenda;
	private VistaLocalidades vistaLocalidades;
	private List<LocalidadDTO>localidadesEnTabla;

	private ControladorGestionLocalidades() {// TODO hacer singleton de agenda/modelo.
		this.agenda =Agenda.getInstance();
		agenda.registrarObservador(this);
		
		this.vistaLocalidades= new VistaLocalidades();
		this.vistaLocalidades.getBtnAgregar().addActionListener(this);
		this.vistaLocalidades.getBtnEditar().addActionListener(this);
		this.vistaLocalidades.getBtnEliminar().addActionListener(this);
	}

	public static ControladorGestionLocalidades getInstance() {
		if(controlador==null)
			controlador=new ControladorGestionLocalidades();
		return controlador;
	}

	public void abrirVentana() {
		vistaLocalidades.mostrar();
		inicializar();
	}
	
	public void inicializar() {
		updateTabla();
	}
	
	private void updateTabla() {
		this.vistaLocalidades.getModelLocalidades().setRowCount(0); // Para vaciar la tabla
		this.vistaLocalidades.getModelLocalidades().setColumnCount(0);
		this.vistaLocalidades.getModelLocalidades().setColumnIdentifiers(this.vistaLocalidades.getNombreColumnas());

		this.localidadesEnTabla = agenda.getLocalidades();
		localidadesEnTabla.forEach(this::añadirLocalidadATabla);

	}
	
	private void añadirLocalidadATabla(LocalidadDTO localidad) {
		Object[] fila = { localidad.getNombre()};
		vistaLocalidades.getModelLocalidades().addRow(fila);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vistaLocalidades.getBtnAgregar()) {
			agregar();
		} else if(e.getSource() == vistaLocalidades.getBtnEditar()) {
			int indexFilaSeleccionada = vistaLocalidades.getTablaLocalidades().getSelectedRow();
			if(indexFilaSeleccionada != -1) {
				editar(indexFilaSeleccionada);
			}else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar una localidad");
			}
			
		} else if(e.getSource() == vistaLocalidades.getBtnEliminar()) {
			int indexFilaSeleccionada = vistaLocalidades.getTablaLocalidades().getSelectedRow();
			if(indexFilaSeleccionada != -1) {
				borrar(indexFilaSeleccionada);
			}else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar una localidad");
			}
		}
		
	}

	private boolean esValido(String nombre) {
		return Validador.esLongitudValida(nombre, 1, 45);
	}

	private void agregar() {
		String nombre=JOptionPane.showInputDialog("Ingrese el nombre de la localidad que desea ingresar ");
		if(esValido(nombre)) {
			this.agenda.agregarLocalidad(new LocalidadDTO(nombre));
			updateTabla();
		} else{
			JOptionPane.showMessageDialog(null, "El valor ingresado es invalido");
		}
	}
	
	private void editar(int indexFilaSeleccionada) {
		String nombre=JOptionPane.showInputDialog("Ingrese el nuevo nombre ");
		if(esValido(nombre)) {
			try {
				this.agenda.modificarLocalidad(new LocalidadDTO(localidadesEnTabla.get(indexFilaSeleccionada).getId(), nombre));
				updateTabla();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error, verifique haber seleccionado una fila");
			}
		
		} else {
			JOptionPane.showMessageDialog(null, "El valor ingresado es invalido");
		}
	}
	
	private void borrar(int indexFilaSeleccionada) {
		try {
				boolean resultado = this.agenda.borrarLocalidad(new LocalidadDTO(localidadesEnTabla.get(indexFilaSeleccionada).getId()));
				if (resultado == false) 
					JOptionPane.showMessageDialog(null,
							"Error, La localidad esta siendo utilizada.");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, verifique que la localidad no este siendo usada en algun contacto");
		}
		
	}

	@Override
	public void update() {
		updateTabla();
	}
	

	
	

}