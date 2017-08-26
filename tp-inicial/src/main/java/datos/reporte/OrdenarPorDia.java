package datos.reporte;

import java.sql.Date;
import java.util.Calendar;
import java.util.Comparator;

import dto.PersonaDTO;


public class OrdenarPorDia implements Comparator <PersonaDTOJasper> {
	
	@Override
	public int compare(PersonaDTOJasper persona1, PersonaDTOJasper persona2) {

		 Calendar calFecha = Calendar.getInstance();
	      calFecha.setTime(persona1.getFechaCumpleaños());
	      int añoFecha = calFecha.get(Calendar.YEAR);
	      int mesFecha = calFecha.get(Calendar.MONTH);
	      int diaFecha = calFecha.get(Calendar.DATE);
	      
	     Calendar calFecha2 = Calendar.getInstance();
	      calFecha2.setTime(persona2.getFechaCumpleaños());
	      int añoFecha2 = calFecha2.get(Calendar.YEAR);
	      int mesFecha2 = calFecha2.get(Calendar.MONTH);
	      int diaFecha2 = calFecha2.get(Calendar.DATE);
	
	      if(diaFecha < diaFecha2){//ordenamos de menor a mayor
	         return -1;
	      }else if(diaFecha > diaFecha2){
	         return 1;
	      }else{
	         return 0;
	      } 
	     
	}

	
}
