package es.mgamallo.prometeo;

import java.io.File;

public class Estadistica {

	Calendario calendario;
	
	public Estadistica() {
		// TODO Auto-generated constructor stub
		
		calendario = new Calendario(); 
		
	}
	
	private int getDocumentosAsociados(){
		
		String ruta = calendario.getCarpetaFinal(true); 
		
		File rutaHoy = new File(ruta);
		File rutaMes = rutaHoy.getParentFile();
		
		int ultimoDiaHabil = calendario.getUltimoDiaHabil();
		
		String rutaFinal = rutaMes.getAbsolutePath() + "\\" + ultimoDiaHabil;
		
		File directorioRutaFinal = new File(rutaFinal);
		
		
		return 0;
	}
	
	private int getDocumentosAsociadosUrgencias(){
		
		String ruta = calendario.getCarpetaFinal(true); 
		
		return 0;
	}
	
	private int getDocumentosXedoc(){
		
		String ruta = calendario.getCarpetaFinal(false); 
		
		return 0;
	}

}
