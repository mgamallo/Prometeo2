package es.mgamallo.prometeo;
import java.io.File;


public class CargaListaPdfs {

	String[] nombrePdfs;	//Sólo el nombre
	String[] rutaPdfs;		//path + nombre
	String rutaCarpeta;
	boolean cargado = false;
	
	

	
	CargaListaPdfs(boolean renombrar) {
		
		AbrirCarpeta carpeta = new AbrirCarpeta(renombrar);
				
		if(carpeta.directorioSeleccionado == true){
			cargado = true;
			File[] ficheros = carpeta.getPdfs(renombrar);
			
			int tamañoDir = ficheros.length;
			nombrePdfs = new String[tamañoDir];
			rutaPdfs = new String[tamañoDir];
			rutaCarpeta = carpeta.nombreCarpeta;
		
			for(int i=0;i<tamañoDir;i++){
				nombrePdfs[i] = ficheros[i].getName();
				rutaPdfs[i] = ficheros[i].getAbsolutePath();
			}
		}
	}
	
	
	
	String[] getNombresPdfs(){
		return nombrePdfs;
	}
	
	String[] getRutaPdfs(){
		return rutaPdfs;
	}
	
	String getRutaCarpeta(){
		return rutaCarpeta;
	}
	
}
