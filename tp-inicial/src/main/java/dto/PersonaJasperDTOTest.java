package dto;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.junit.Test;

public class PersonaJasperDTOTest {

	@Test
	public void testCompareTo() {
		PersonaJasperDTO persona1 = new PersonaJasperDTO("dasda", "dsadas", "dasdas", Date.valueOf("1993-07-04"),null, null);
		PersonaJasperDTO persona2 = new PersonaJasperDTO("dasda", "dsadas", "dasdas", Date.valueOf("1995-01-03"),null, null);
		PersonaJasperDTO persona3 = new PersonaJasperDTO("dasda", "dsadas", "dasdas", Date.valueOf("1998-06-07"),null, null);
		PersonaJasperDTO persona4 = new PersonaJasperDTO("dasda", "dsadas", "dasdas", Date.valueOf("1991-07-05"),null, null);
		PersonaJasperDTO persona5 = new PersonaJasperDTO("dasda", "dsadas", "dasdas", Date.valueOf("1993-07-05"),null, null);
		
		assertEquals(-1, persona2.compareTo(persona1));
		assertEquals(-1, persona2.compareTo(persona3));
		assertEquals(-1, persona2.compareTo(persona4));
		assertEquals(-1, persona2.compareTo(persona5));
		
		assertEquals(1, persona3.compareTo(persona1));
		assertEquals(1, persona3.compareTo(persona2));
		assertEquals(1, persona3.compareTo(persona4));
		assertEquals(1, persona3.compareTo(persona5));
		
		assertEquals(0, persona4.compareTo(persona5));
		
		
	}
	
	@Test
	public void compareTo() {
		PersonaDTO persona1 = new PersonaDTO("dasda", "dsadas", "dasdas", Date.valueOf("1993-07-04"),null, null);
		PersonaDTO persona2 = new PersonaDTO("dasda", "dsadas", "dasdas", Date.valueOf("1995-01-03"),null, null);
		PersonaDTO persona3 = new PersonaDTO("dasda", "dsadas", "dasdas", Date.valueOf("1998-06-07"),null, null);
		PersonaDTO persona4 = new PersonaDTO("dasda", "dsadas", "dasdas", Date.valueOf("1991-07-05"),null, null);
		PersonaDTO persona5 = new PersonaDTO("dasda", "dsadas", "dasdas", Date.valueOf("1993-07-05"),null, null);
		
		ArrayList<PersonaDTO>lista =new  ArrayList<PersonaDTO>();
		lista.add(persona5);
		lista.add(persona3);
		lista.add(persona2);
		lista.add(persona4);
		lista.add(persona1);
		
		ArrayList<PersonaJasperDTO>jasperizados=(ArrayList<PersonaJasperDTO>) PersonaJasperDTO.jasperizarPersonas(lista);
		
		
		assertEquals(persona2.getFechaCumpleaños(), jasperizados.get(0).getFechaCumpleaños());
		assertEquals(persona3.getFechaCumpleaños(), jasperizados.get(4).getFechaCumpleaños());
		
	}

}
