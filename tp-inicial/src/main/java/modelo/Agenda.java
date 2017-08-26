package modelo;

import java.util.ArrayList;
import java.util.List;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoDeContactoDTO;
import persistencia.dao.interfaz.DomicilioDAO;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoDeContactoDAO;
import persistencia.dao.mysql.DomicilioDAOImpl;
import persistencia.dao.mysql.LocalidadDAOImpl;
import persistencia.dao.mysql.PersonaDAOImpl;
import persistencia.dao.mysql.TipoDeContactoDAOImpl;
import patrones.observer.*;

public class Agenda implements Observable {
	private ArrayList<Observador> observadores;
	private PersonaDAO persona;
	private LocalidadDAO localidad;
	private TipoDeContactoDAO tipoDeContacto;
	private DomicilioDAO domicilio;
	private static Agenda instancia;

	private Agenda() {
		observadores = new ArrayList<>();
		persona = new PersonaDAOImpl();
		localidad = new LocalidadDAOImpl();
		tipoDeContacto = new TipoDeContactoDAOImpl();
		domicilio = new DomicilioDAOImpl();
	}
	
	public static Agenda getInstance() {
		if (instancia == null) 
			instancia = new Agenda();
		return instancia;
	}

	public void agregarPersona(PersonaDTO nuevaPersona) {
		persona.insert(nuevaPersona);
		notificarObservadores();
	}

	public void borrarPersona(PersonaDTO personaAEliminar) {
		persona.delete(personaAEliminar);
		notificarObservadores();
	}

	public List<PersonaDTO> obtenerPersonas() {
		return persona.readAll();
	}

	public void modificarPersona(PersonaDTO personaAModificar) {
		persona.update(personaAModificar);
		notificarObservadores();
	}

	public void agregarLocalidad(LocalidadDTO nuevaLocalidad) {
		localidad.insert(nuevaLocalidad);
		notificarObservadores();
	}

	public int agregarDomicilio(DomicilioDTO nuevoDomicilio) {
		int ret = domicilio.insert(nuevoDomicilio);
		if (ret != -1)
			notificarObservadores();
		return ret;
	}

	public List<LocalidadDTO> getLocalidades() {
		return localidad.readAll();
	}

	public List<TipoDeContactoDTO> getTiposDeContacto() {
		return tipoDeContacto.readAll();
	}

	public void modificarLocalidad(LocalidadDTO localidadNueva) {
		localidad.update(localidadNueva);
		notificarObservadores();
	}

	public boolean borrarLocalidad(LocalidadDTO localidadABorrar) {
		boolean ret = localidad.delete(localidadABorrar);
		if (ret)
		notificarObservadores();
		return ret;
	}

	public void agregarTipoDeContacto(TipoDeContactoDTO tipoDeContactoDTO) {
		tipoDeContacto.insert(tipoDeContactoDTO);
		notificarObservadores();
	}

	public void modificarTipoDeContacto(TipoDeContactoDTO tipoDeContactoDTO) {
		tipoDeContacto.update(tipoDeContactoDTO);
		notificarObservadores();
	}

	public boolean borrarTipoDeContacto(TipoDeContactoDTO tipoDeContactoDTO) {
		boolean ret = tipoDeContacto.delete(tipoDeContactoDTO);
		if (ret)
		notificarObservadores();
		return ret;
	}

	@Override
	public void registrarObservador(Observador o) {
		observadores.add(o);

	}

	@Override
	public void notificarObservadores() {
		observadores.forEach(o -> o.update());
	}

}
