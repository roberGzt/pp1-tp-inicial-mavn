package persistencia.dao.mysql;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import persistencia.dao.interfaz.DomicilioDAO;

public class DomicilioDAOImplTest {
	DomicilioDAO dao = new DomicilioDAOImpl();
	
	
	@Test
	public void printAll() {
		ArrayList<DomicilioDTO>domicilios = (ArrayList<DomicilioDTO>) dao.readAll();
		
		domicilios.forEach(d -> System.out.println(d.getCalle()+" "+d.getIdDomicilio()));
	}
	
	@Test
	public void update() {
		assertTrue(dao.update(new DomicilioDTO(1, "sarasa", 123, 132, "Sarasa2312", new LocalidadDTO(1))));
	}
	
	@Test
	public void insert() {
		assertNotEquals(-1,dao.insert(new DomicilioDTO("ultima", 1000, 4, "1a", new LocalidadDTO(1))));
	}

}
