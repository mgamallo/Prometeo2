package es.mgamallo.prometeo;

import java.util.Calendar;
import java.util.Locale;

public class Fecha {
	static String getFecha(){
		
		// JUEVES, 18 DE SEPTIEMBRE DE 2014
		
		Locale loc = Locale.GERMAN;
		Calendar hoy = Calendar.getInstance(loc);
		
		int diaSemana = hoy.get(Calendar.DAY_OF_WEEK);
		System.out.println("Dia de la semana... " + diaSemana);
		
		int dia = hoy.get(Calendar.DAY_OF_MONTH);
		System.out.println(hoy.get(Calendar.DATE));
		
		int mes = hoy.get(Calendar.MONTH);
		int año = hoy.get(Calendar.YEAR);
		
		System.out.println("Hoy es... " + hoy.getTime());
		
		// System.out.println(" Hola " + getDiaSemana(diaSemana));
		
		//String cadena = hoy.get()
		
		return null;
	}
	
	private static String getDiaSemana(int d){
		
		String cadena = "";
		switch (d) {
		case 0:
			cadena = "Domingo";
			break;
		case 1:
			cadena = "Lunes";
			break;
		case 2:
			cadena = "Martes";
			break;
		case 3:
			cadena = "Miércoles";
			break;
		case 4:
			cadena = "Jueves";
			break;
		case 5:
			cadena = "Viernes";
			break;
		case 6:
			cadena = "Sábado";
			break;

		default:
			break;
		}
		
		return cadena;
	}
	
	static public void main(String args[]){
		Fecha.getFecha();
	}
}
