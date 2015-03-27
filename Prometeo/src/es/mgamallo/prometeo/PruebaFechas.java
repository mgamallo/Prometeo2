package es.mgamallo.prometeo;

import java.util.Calendar;

public class PruebaFechas {

	public PruebaFechas() {
		// TODO Auto-generated constructor stub
		
		Calendar calendario = Calendar.getInstance();
		int dia = calendario.get(Calendar.DAY_OF_MONTH);
		int mes = calendario.get(Calendar.MONTH) + 1;
		int año = calendario.get(Calendar.YEAR);
		
		System.out.println(dia + "/" + mes + "/" + año);

		calendario.add(Calendar.DAY_OF_MONTH,-45);
		
		dia = calendario.get(Calendar.DAY_OF_MONTH);
		mes = calendario.get(Calendar.MONTH) + 1;
		año = calendario.get(Calendar.YEAR);
		
		System.out.println(dia + "/" + mes + "/" + año);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PruebaFechas();
	}

}
