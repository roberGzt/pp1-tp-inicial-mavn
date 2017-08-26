package persistencia.dao.mysql;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


import dto.TipoDeContactoDTO;
import persistencia.dao.interfaz.TipoDeContactoDAO;

public class TipoDeContactoDAOImplTest {
	
	TipoDeContactoDAO dao = new TipoDeContactoDAOImpl();
	
	//
	@Test
	public void printAll() {
		ArrayList<TipoDeContactoDTO> tiposDeContacto = (ArrayList<TipoDeContactoDTO>) dao.readAll();
		tiposDeContacto.forEach(t -> System.out.println(t.getNombre()));
	}
	
	@Test
	public void insert() {
		assertNotEquals(-1, dao.insert(new TipoDeContactoDTO("NuevoTipodasda")));
		//si lo ejecutan mas de 1 vez es normal que tire error porque el nombre es Unique
	}
	
	@Test 
	public void update() {
		assertTrue(dao.update(new TipoDeContactoDTO(1, "Nuevo tipo")));
	}

	
	

}
