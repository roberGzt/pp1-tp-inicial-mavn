package datos.reporte;

import java.sql.Date;
import java.util.Calendar;

import dto.DomicilioDTO;
import dto.PersonaDTO;
import dto.TipoDeContactoDTO;

public class PersonaDTOJasper extends PersonaDTO{

	String nombre;
	String telefono;
	String mail;
	Date fechaCumpleaños;
	TipoDeContactoDTO tipoDeContacto;
	DomicilioDTO domicilio;
	SignoZodiaco signo;
	
	public PersonaDTOJasper(String nombre, String telefono, String mail, Date fechaCumpleaños,
			TipoDeContactoDTO tipoDeContacto, DomicilioDTO domicilio, SignoZodiaco SignoZodiaco) {
		super(nombre, telefono, mail, fechaCumpleaños, tipoDeContacto, domicilio);
		// TODO Auto-generated constructor stub
		
		nombre = this.nombre;
		telefono = this.telefono;
		mail = this.mail;
		fechaCumpleaños = this.fechaCumpleaños;
		tipoDeContacto = this.tipoDeContacto;
		domicilio = this.domicilio;
		SignoZodiaco = this.signo;
	}

	
	public SignoZodiaco getSigno() {
			return signo;
	}

	public void setSigno(SignoZodiaco signo) {
			this.signo = signo;
	}

	public SignoZodiaco calcularYAsignarSigno(Date fechaCumpleaños){
		
		Calendar calFecha = Calendar.getInstance();
	    calFecha.setTime(fechaCumpleaños);
	    int añoFecha = calFecha.get(Calendar.YEAR);
	    int mesFecha = calFecha.get(Calendar.MONTH)+1; //+1 porque si es mes 8, trae 7
	    int diaFecha = calFecha.get(Calendar.DATE);
	    
	    System.out.println("El dia de mes es: "+ diaFecha);
	    System.out.println("El mes es: "+ mesFecha);
		
		if (diaFecha >= 21 && mesFecha == 3 || diaFecha <= 20 && mesFecha == 4)
			return SignoZodiaco.ARIES;
		if (diaFecha >= 21 && mesFecha == 4 || diaFecha <= 21 && mesFecha == 5)
			return  SignoZodiaco.TAURO;
		if (diaFecha >= 22 && mesFecha == 5 || diaFecha <= 21 && mesFecha == 6)
			return SignoZodiaco.Geminis;
		if (diaFecha >= 22 && mesFecha == 6 || diaFecha <= 22 && mesFecha == 7)
			return SignoZodiaco.Cancer;
		if (diaFecha >= 23 && mesFecha == 7 || diaFecha <= 23 && mesFecha == 8)
			return SignoZodiaco.LEO;
		if (diaFecha >= 24 && mesFecha == 8 || diaFecha <= 23 && mesFecha == 9)
			return SignoZodiaco.VIRGO;
		if (diaFecha >= 24 && mesFecha == 9 || diaFecha <= 23 && mesFecha == 10)
			return SignoZodiaco.LIBRA;
		if (diaFecha >= 24 && mesFecha == 10 || diaFecha <= 22 && mesFecha == 11)
			return SignoZodiaco.Escorpio;
		if (diaFecha >= 23 && mesFecha == 11 || diaFecha <= 21 && mesFecha == 12)
			return SignoZodiaco.SAGITARIO;
		if (diaFecha >= 22 && mesFecha == 12 || diaFecha <= 20 && mesFecha == 1)
			return SignoZodiaco.CAPRICORNIO;
		if (diaFecha >= 21 && mesFecha == 1 || diaFecha <= 18 && mesFecha == 2)
			return SignoZodiaco.ACUARIO;
		//if (diaFecha >= 19 && mesFecha == 2 || diaFecha <= 20 && mesFecha == 3)
		else
			return SignoZodiaco.PISCIS;			
			
			
			
	}

}
