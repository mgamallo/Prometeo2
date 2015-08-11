package es.mgamallo.prometeo;

import java.util.Calendar;
import java.util.Locale;

public class Fecha {
	
	int dia;
	private int diaSemana;
	public int mes;
	int año;
	String nombreMes = "";
	String nombreMesYnumero = "";
	String nombreDia = "";

	
	public Fecha() {
		// TODO Auto-generated constructor stub
		getFecha();
	}
	
	
	private void getFecha(){
		
		// JUEVES, 18 DE SEPTIEMBRE DE 2014

		Calendar hoy = Calendar.getInstance();
		
		dia = hoy.get(Calendar.DAY_OF_MONTH);
		diaSemana = hoy.get(Calendar.DAY_OF_WEEK);
		mes = hoy.get(Calendar.MONTH);
		año = hoy.get(Calendar.YEAR);
		
		nombreDia = getDiaSemana(diaSemana);
		nombreMes = getNombreMes(mes);
		nombreMesYnumero = getNombreMesYNumero(mes);
	}
	
	private String getDiaSemana(int d){
		
		String cadena = "";
		switch (d) {
		case 1:
			cadena = "Domingo";
			break;
		case 2:
			cadena = "Lunes";
			break;
		case 3:
			cadena = "Martes";
			break;
		case 4:
			cadena = "Miércoles";
			break;
		case 5:
			cadena = "Jueves";
			break;
		case 6:
			cadena = "Viernes";
			break;
		case 7:
			cadena = "Sábado";
			break;

		default:
			break;
		}
		
		return cadena;
	}
	
	private String getNombreMes(int mes){
		
		String nombre = "";
		
		switch(mes){
		case 0:	nombre = "Enero";break;
		case 1:	nombre = "Febrero";break;
		case 2:	nombre = "Marzo";break;
		case 3:	nombre = "Abril";break;
		case 4:	nombre = "Mayo";break;
		case 5:	nombre = "Junio";break;
		case 6:	nombre = "Julio";break;
		case 7:	nombre = "Agosto";break;
		case 8:	nombre = "Septiembre";break;
		case 9:	nombre = "Octubre";break;
		case 10:	nombre = "Noviembre";break;
		case 11:	nombre = "Diciembre";break;
		}
		
		return nombre;
	}
	
	
	private String getNombreMesYNumero(int mes){
		
		String nombre = "";
		
		switch(mes){
		case 0:	nombre = "01 Enero";break;
		case 1:	nombre = "02 Febrero";break;
		case 2:	nombre = "03 Marzo";break;
		case 3:	nombre = "04 Abril";break;
		case 4:	nombre = "05 Mayo";break;
		case 5:	nombre = "06 Junio";break;
		case 6:	nombre = "07 Julio";break;
		case 7:	nombre = "08 Agosto";break;
		case 8:	nombre = "09 Septiembre";break;
		case 9:	nombre = "10 Octubre";break;
		case 10:	nombre = "11 Noviembre";break;
		case 11:	nombre = "12 Diciembre";break;
		}
		
		return nombre;
	}
	
	static public void main(String args[]){
		Fecha fecha = new Fecha();
		System.out.println(fecha.nombreDia);
		System.out.println(fecha.nombreMes);
	}
}
