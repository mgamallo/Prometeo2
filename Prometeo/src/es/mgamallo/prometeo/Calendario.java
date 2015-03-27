package es.mgamallo.prometeo;

import java.util.Calendar;

public class Calendario {

	int dia;
	int mes;
	String nombreMes = "";
	int año;
	
	public Calendario() {
		// TODO Auto-generated constructor stub
		
		Calendar calendario = Calendar.getInstance();
		dia = calendario.get(Calendar.DAY_OF_MONTH);
		mes = calendario.get(Calendar.MONTH);
		año = calendario.get(Calendar.YEAR);
		
		switch(mes){
		case 0:	nombreMes = "01 Enero";break;
		case 1:	nombreMes = "02 Febrero";break;
		case 2:	nombreMes = "03 Marzo";break;
		case 3:	nombreMes = "04 Abril";break;
		case 4:	nombreMes = "05 Mayo";break;
		case 5:	nombreMes = "06 Junio";break;
		case 6:	nombreMes = "07 Julio";break;
		case 7:	nombreMes = "08 Agosto";break;
		case 8:	nombreMes = "09 Septiembre";break;
		case 9:	nombreMes = "10 Octubre";break;
		case 10:	nombreMes = "11 Noviembre";break;
		case 11:	nombreMes = "12 Diciembre";break;
		}
	}

	public String getCarpetaFinal(boolean ianus){
		
		String ruta = "";
		
		if(ianus){
			if(Inicio.usuario.urgencias){
				 ruta = Inicio.rutaAsociadosUrgencias;
			}
			else{
				ruta = Inicio.rutaAsociados;
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
			System.out.println(diaSemana);
			if(diaSemana != 1 && diaSemana != 7 ){
				noEncontrado = false;
			}
		}
		while(noEncontrado );
		
		int diaHabil = cal.get(Calendar.DAY_OF_MONTH);
		
		/*
		for(int i=1;i<8;i++){
			System.out.print("Dia ... ");
			dia = cal.get(Calendar.DAY_OF_MONTH);
			System.out.println(dia);
			System.out.println(cal.get(Calendar.DAY_OF_WEEK));
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		 */
		System.out.println(diaHabil);
		return diaHabil;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Calendario calendario = new Calendario();
		System.out.println(calendario.getCarpetaFinal(false));
		calendario.getUltimoDiaHabil();
	}

}
