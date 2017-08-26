package persistencia.dao.mysql;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dto.DomicilioDTO;
import dto.PersonaDTO;
import dto.TipoDeContactoDTO;
import persistencia.dao.interfaz.PersonaDAO;

public class PersonaDAOImplTest {
	PersonaDAOImpl dao = new PersonaDAOImpl();
	
		@Test
		public void pruebaJoin() {
			ArrayList<PersonaDTO>resultado = (ArrayList<PersonaDTO>) dao.readAll();
			for(PersonaDTO p : resultado) {
				System.out.println(p.getNombre()+" de "+p.getDomicilio().getLocalidad().getNombre()+" tipo de contacto: "+p.getTipoDeContacto().getNombre()
						+ " Fecha cumpleaños "+p.getFechaCumpleaños().toString());
			}
		}
		
		@Test
		public void update() {
			assertTrue(dao.update(new PersonaDTO(1, "carlitos", "123132", "ddasdas@dasd", Date.valueOf("1993-05-06"), new TipoDeContactoDTO(3), new DomicilioDTO(2))));
		}
		
		@Test
		public void insert() {
			assertTrue(dao.insert(new PersonaDTO("Profe daniel", "12346", "pd@dasdsa", Date.valueOf("1969-12-31"), new TipoDeContactoDTO(1), new DomicilioDTO(1))));
		}
			
		@Test
		public void delete() {
			assertTrue(dao.delete(new PersonaDTO(2)));
		}

}
