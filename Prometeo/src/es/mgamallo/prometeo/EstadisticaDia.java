package es.mgamallo.prometeo;

public class EstadisticaDia {

	String fecha;
	String numeroFicheros;
	String diaSemana;
	
	public EstadisticaDia(String fecha, String numeroFicheros, String diaSemana) {
		// TODO Auto-generated constructor stub
		
		this.fecha = fecha;
		this.numeroFicheros = numeroFicheros;
		this.diaSemana = diaSemana;
		
	}
	
	
	public EstadisticaDia(String cadena) {
		// TODO Auto-generated constructor stub
		
		System.out.println(cadena);
		
		String[] cadenaArray = cadena.split(";");
		
	//	if(cadenaArray.length>2){
			this.fecha = cadenaArray[0];
			this.numeroFicheros = cadenaArray[1];
			this.diaSemana = cadenaArray[2];
	//	}
		

		
	}
	
	public void imprimeDatos(){
		System.out.println(fecha + ";" + numeroFicheros + ";" + diaSemana);
	}
	
}
