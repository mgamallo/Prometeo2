package es.mgamallo.prometeo;

import java.io.File;

public class Estadistica {

	Calendario calendario;
	EstadisticaDiaHabil estadisticaDiaria;
	
	
	public Estadistica() {
		// TODO Auto-generated constructor stub
		
		calendario = new Calendario(); 
		estadisticaDiaria = new EstadisticaDiaHabil(calendario);
		
	}
	

	
	
	


	
	public static void main(String args[]){
		new Estadistica();
	}
	
}

class EstadisticaDiaHabil{
	
	int numeroArchivosSubidosIanus;
	int numeroArchivosSubidosIanusUrg;
	int numeroArchivosSubidosXedoc;
	
	int diaHabil;
	int mesHabil;
	int añoHabil;
	
	Calendario calendario;
	
	public EstadisticaDiaHabil(Calendario calendario) {
		// TODO Auto-generated constructor stub
		
		this.calendario = calendario;
		diaHabil = this.calendario.getUltimoDiaHabil();
		
		numeroArchivosSubidosIanus = getNumeroDeDocumentosSubidosDia(true, false);
		numeroArchivosSubidosIanusUrg = getNumeroDeDocumentosSubidosDia(true, true);
		numeroArchivosSubidosXedoc = getNumeroDeDocumentosSubidosDia(false, true);
	
	} 
	
	private int getNumeroDeDocumentosSubidosDia(boolean ianus, boolean urgencias){
		
		int numeroArchivos = 0;
		
		String ruta = calendario.getCarpetaFinal(ianus, urgencias); 
		
		File rutaHoy = new File(ruta);
		File rutaMes = rutaHoy.getParentFile();
		
		int ultimoDiaHabil = calendario.getUltimoDiaHabil();
		
		String rutaFinal = rutaMes.getAbsolutePath() + "\\" + ultimoDiaHabil;
		
	//	System.out.println("Ruta final del ultimo dia habil: ");
	//	System.out.println(rutaFinal);
		
		File ficheroRutaFinal = new File(rutaFinal);
		if(ficheroRutaFinal.exists())
			numeroArchivos = getNumeroFicherosDirectorio( ficheroRutaFinal);
		
		return numeroArchivos;
	}
	
	private int getNumeroFicherosDirectorio(File directorio){
		
		int numeroFicheros = 0;
		
		File fichero[] = directorio.listFiles();
		for(int i=0;i<fichero.length;i++){
			if(fichero[i].isDirectory()){
				numeroFicheros = numeroFicheros + getNumeroFicherosDirectorio(fichero[i]);
			}
			else{
				numeroFicheros++;
			}
		}
		
		return numeroFicheros;
	}
}