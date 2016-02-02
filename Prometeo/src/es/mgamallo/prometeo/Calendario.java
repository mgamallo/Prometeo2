package es.mgamallo.prometeo;

import java.util.Calendar;

public class Calendario {

	int dia;
	int mes;
	String nombreMes = "";
	String nombreDiaHabil = "";
	String fechaHabil = "";
	
	int año;
	
	
	
	public Calendario() {
		// TODO Auto-generated constructor stub
		
		Calendar calendario = Calendar.getInstance();
		dia = calendario.get(Calendar.DAY_OF_MONTH);
		mes = calendario.get(Calendar.MONTH);
		año = calendario.get(Calendar.YEAR);
		
		nombreMes = getNombreMes(mes);
	}

	public String getCarpetaFinal(boolean ianus, int tipoDocumentacion){
		
		String ruta = "";
		
		if(ianus){
			if(tipoDocumentacion == 0){
				 ruta = Inicio.rutaAsociadosUrgencias;
			}
			else if(tipoDocumentacion == 1){
				ruta = Inicio.rutaAsociados;
			}
			else if(tipoDocumentacion == 2){
				ruta = Inicio.rutaAsociadosSalnes;
			}
		}
		else{
			ruta = Inicio.rutaXedocOriginales;
		}

		ruta += ("\\" + año + "\\" + nombreMes + "\\" + dia);
		
		return ruta;
	}
	
	public int getUltimoDiaHabil(){
		Calendar cal = Calendar.getInstance();
		
		int diaSemana = -1;
		boolean noEncontrado = true;
		do{
			cal.add(Calendar.DAY_OF_MONTH, -1);
			diaSemana = cal.get(Calendar.DAY_OF_WEEK);
			if(diaSemana != 1 && diaSemana != 7 ){
				noEncontrado = false;
			}
		}
		while(noEncontrado );
		
		int diaHabil = cal.get(Calendar.DAY_OF_MONTH);
		int diaHabilSemana = cal.get(Calendar.DAY_OF_WEEK);
		mes = cal.get(Calendar.MONTH);
		año = cal.get(Calendar.YEAR);
		
		nombreMes = getNombreMes(mes);

		
		String diaString = "";
		if(diaHabil <10){
			diaString = "0";
		}
		diaString += diaHabil;
		
		String mesString = "";
		int mess = mes + 1;
		if(mess < 10){
			mesString = "0";
		}
		mesString += mess;
		
		fechaHabil = diaString + "/" + mesString + "/" + año;
		
	//	System.out.println("Dia habil " + diaHabilSemana);
		nombreDiaHabil = getDiaSemana(diaHabilSemana);
		
		System.out.println("Ultimo día hábil " + diaHabil);
		return diaHabil;
	}
	
	private String getNombreMes(int mes){
		
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
		
		System.out.println("El dia es " + cadena);
		
		return cadena;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Calendario calendario = new Calendario();
		System.out.println(calendario.getCarpetaFinal(false,1));
		calendario.getUltimoDiaHabil();
	}

}
