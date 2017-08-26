package dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import datos.reporte.SignoZodiaco;

public class PersonaJasperDTO extends PersonaDTO implements Comparable<PersonaJasperDTO>{
	
	Signo signo=null;
	
	//Se puede hacer con herencia o tambien utilizando composicion (creando este objeto con variables
	//persona y signo
	public PersonaJasperDTO(String nombre, String telefono, String mail, Date fechaCumpleaños,
			TipoDeContactoDTO tipoDeContacto, DomicilioDTO domicilio) {
		///usamos el constructor de PersonaDTO
		super(nombre, telefono, mail, fechaCumpleaños, tipoDeContacto, domicilio);
		//Usamos el metodo para calcular su signo
		signo = calcularSigno(this.getFechaCumpleaños());
	}
	
	public PersonaJasperDTO(PersonaDTO persona) {
		super(persona.getIdPersona(), persona.getNombre(), persona.getTelefono(), persona.getMail(), persona.getFechaCumpleaños(), null, null);
		signo = calcularSigno(this.getFechaCumpleaños());
	}
	
	public static Signo calcularSigno(Date fechaNacimiento) {
		Calendar calFecha = Calendar.getInstance();
	    calFecha.setTime(fechaNacimiento);
	    int añoFecha = calFecha.get(Calendar.YEAR);
	    int mesFecha = calFecha.get(Calendar.MONTH)+1; //+1 porque si es mes 8, trae 7
	    int diaFecha = calFecha.get(Calendar.DAY_OF_MONTH);
	    Signo signo= null;
	    System.out.println("El dia de mes es: "+ diaFecha);
	    System.out.println("El mes es: "+ mesFecha);
		
		if (diaFecha >= 21 && mesFecha == 3 || diaFecha <= 20 && mesFecha == 4)
			signo= Signo.Aries;
		if (diaFecha >= 21 && mesFecha == 4 || diaFecha <= 21 && mesFecha == 5)
			signo= Signo.Tauro;
		if (diaFecha >= 22 && mesFecha == 5 || diaFecha <= 21 && mesFecha == 6)
			signo= Signo.Geminis;
		if (diaFecha >= 22 && mesFecha == 6 || diaFecha <= 22 && mesFecha == 7)
			signo= Signo.Cancer;
		if (diaFecha >= 23 && mesFecha == 7 || diaFecha <= 23 && mesFecha == 8)
			signo= Signo.Leo;
		if (diaFecha >= 24 && mesFecha == 8 || diaFecha <= 23 && mesFecha == 9)
			signo= Signo.Virgo;
		if (diaFecha >= 24 && mesFecha == 9 || diaFecha <= 23 && mesFecha == 10)
			signo= Signo.Libra;
		if (diaFecha >= 24 && mesFecha == 10 || diaFecha <= 22 && mesFecha == 11)
			signo= Signo.Escorpio;
		if (diaFecha >= 23 && mesFecha == 11 || diaFecha <= 21 && mesFecha == 12)
			signo= Signo.Sagitario;
		if (diaFecha >= 22 && mesFecha == 12 || diaFecha <= 20 && mesFecha == 1)
			signo= Signo.Capricornio;
		if (diaFecha >= 21 && mesFecha == 1 || diaFecha <= 18 && mesFecha == 2)
			signo= Signo.Acuario;
		if (diaFecha >= 19 && mesFecha == 2 || diaFecha <= 20 && mesFecha == 3)
			signo= Signo.Piscis;
		return signo;
	}

	@Override
	public int compareTo(PersonaJasperDTO o) {
		Calendar fecha1 = Calendar.getInstance();
		fecha1.setTime(this.getFechaCumpleaños());
		fecha1.set(Calendar.YEAR, 1000);
		
		Calendar fecha2 = Calendar.getInstance();
		fecha2.setTime(o.getFechaCumpleaños());
		fecha2.set(Calendar.YEAR, 1000);
		
		
		if(fecha1.get(Calendar.DAY_OF_MONTH) > fecha2.get(Calendar.DAY_OF_MONTH))
			return 1;
		else if(fecha1.get(Calendar.DAY_OF_MONTH) < fecha2.get(Calendar.DAY_OF_MONTH))
			return -1;
		else if(fecha1.get(Calendar.MONTH) > fecha2.get(Calendar.MONTH))
			return 1;
		else if(fecha1.get(Calendar.MONTH) < fecha2.get(Calendar.MONTH))
			return -1;
		else 
			return 0;
	}
	
	public static List<PersonaJasperDTO> jasperizarPersonas(List<PersonaDTO>personas) {
		List<PersonaJasperDTO>personasJasper=new ArrayList<>();
		personas.forEach(p -> personasJasper.add(new PersonaJasperDTO(p)));
		Collections.sort(personasJasper);
		return personasJasper;
	}
	
	
	
	

	
	
	
}
