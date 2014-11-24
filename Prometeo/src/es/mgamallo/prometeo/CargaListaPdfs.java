package es.mgamallo.prometeo;
import java.io.File;


public class CargaListaPdfs {

	String[] nombrePdfs;	//Sólo el nombre
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
			
			int tamañoDir = ficheros.length;
			nombrePdfs = new String[tamañoDir];
			rutaPdfs = new String[tamañoDir];
			rutaCarpeta = carpeta.nombreCarpeta;
			rutaCompletaCarpeta = carpeta.rutaCompletaCarpeta;
		
			for(int i=0;i<tamañoDir;i++){
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
		
		int tamañoDir = ficheros.length;
		nombrePdfs = new String[tamañoDir];
		rutaPdfs = new String[tamañoDir];
		rutaCarpeta = carpeta.nombreCarpeta;
		rutaCompletaCarpeta = carpeta.rutaCompletaCarpeta;
	
		for(int i=0;i<tamañoDir;i++){
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
