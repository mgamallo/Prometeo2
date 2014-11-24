package es.mgamallo.prometeo;
import java.io.File;


public class CargaListaPdfs {

	String[] nombrePdfs;	//S�lo el nombre
	String[] rutaPdfs;		//path + nombre
	String rutaCarpeta;
	String rutaCompletaCarpeta;
	
	boolean cargado = false;
	boolean cancelado = true;
	
	

	
	CargaListaPdfs(boolean renombrar) {
		
		AbrirCarpeta carpeta = new AbrirCarpeta(renombrar);

				
		if(carpeta.directorioSeleccionado == true){
			cargado = true;
			cancelado = false;
			File[] ficheros = carpeta.getPdfs(renombrar);
			
			int tama�oDir = ficheros.length;
			nombrePdfs = new String[tama�oDir];
			rutaPdfs = new String[tama�oDir];
			rutaCarpeta = carpeta.nombreCarpeta;
			rutaCompletaCarpeta = carpeta.rutaCompletaCarpeta;
		
			for(int i=0;i<tama�oDir;i++){
				nombrePdfs[i] = ficheros[i].getName();
				rutaPdfs[i] = ficheros[i].getAbsolutePath();
				System.out.println("nombresPdfs " + nombrePdfs[i]);
			}
		}
		else{
			nombrePdfs = null;
			rutaPdfs = null;
			rutaCarpeta = null;
			cancelado = true;
		}
	}
	
	CargaListaPdfs(boolean renombrar, File directorio){
		AbrirCarpeta carpeta = new AbrirCarpeta(renombrar,true);
		
		File[] ficheros = carpeta.getPdfsMetro(renombrar,directorio);
		
		int tama�oDir = ficheros.length;
		nombrePdfs = new String[tama�oDir];
		rutaPdfs = new String[tama�oDir];
		rutaCarpeta = carpeta.nombreCarpeta;
		rutaCompletaCarpeta = carpeta.rutaCompletaCarpeta;
	
		for(int i=0;i<tama�oDir;i++){
			nombrePdfs[i] = ficheros[i].getName();
			rutaPdfs[i] = ficheros[i].getAbsolutePath();
			System.out.println("nombresPdfs " + nombrePdfs[i]);
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
