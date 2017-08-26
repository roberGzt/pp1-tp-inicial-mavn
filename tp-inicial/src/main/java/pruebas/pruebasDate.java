package pruebas;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class pruebasDate {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
//		Date date1  = Date.valueOf("2017-02-06");
//		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");// FOrmat in This Format or you change Change as well 
//		
//		String formatString= format.format(date1);
//		//Si hacemos print de formatString obtenemos un string con la fecha ordenada como seteamos arriba
//		System.out.println("Nuevo formato "+formatString);
		
		//Ahora creamos un date con ese formato y lo imprimimos
		//Date nuevo = Date.valueOf(formatString);
		//System.out.println(nuevo.toString());
		
//		java.util.Date dateJava =new  java.util.Date(date.getTime());
//		format = new SimpleDateFormat("dd-MM-yyyy");// FOrmat in This Format or you change Change as well 
//		
//		formatString= format.format(date);
//		
//		date = Date.valueOf(dateJava.toString());
//		System.out.println(date.toString());
		
		Date date1  = Date.valueOf("2017-02-06");
		Date date2  = Date.valueOf("2019-02-05");
		Date date3  = Date.valueOf("2019-02-06");
		
		Calendar fecha1 = Calendar.getInstance();
		fecha1.setTime(date1);
		fecha1.set(Calendar.YEAR, 1000);
		
		Calendar fecha2 = Calendar.getInstance();
		fecha2.setTime(date2);
		fecha2.set(Calendar.YEAR, 1000);
		
		Calendar fecha3 = Calendar.getInstance();
		fecha3.setTime(date3);
		fecha3.set(Calendar.YEAR, 1000);
		
		System.out.println("Comparamos los dates : "+date1.compareTo(date2));
		System.out.println("Comparamos los calendars: "+fecha1.compareTo(fecha2));
		System.out.println("Comparamos 2 calendar iguales: "+fecha3.compareTo(fecha1)+" "+fecha1.compareTo(fecha3));
		System.out.println("DayOfMouth "+fecha1.get(Calendar.DAY_OF_MONTH));
		
		
		
	}

}
