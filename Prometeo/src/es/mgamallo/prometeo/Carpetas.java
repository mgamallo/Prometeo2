package es.mgamallo.prometeo;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class Carpetas {
	
	static String path = "K:/Desarrollo Zona Pruebas/03 Firmado";
	
	
	
	Carpetas(boolean carpetaFirmados){
		if(!carpetaFirmados){
			path = "K:/Desarrollo Zona Pruebas/01 Escaneado";
		}
		
		File ruta = new File(path);
		
		FileFilter filtro = new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				return pathname.isDirectory();
			}
		};
		
		File carpetas[] = ruta.listFiles(filtro);
		for(int i=0;i<carpetas.length;i++){
			System.out.println(carpetas[i].getName());
			
		}
		
		Directorio[] conjuntoCarpetas = new Directorio[carpetas.length];
		for(int i=0;i<carpetas.length;i++){
			conjuntoCarpetas[i] = new Directorio(carpetas[i]);
			System.out.println(conjuntoCarpetas[i].directorio.getName());
			System.out.println(conjuntoCarpetas[i].numeroPdfs);
		}
		
	}
	
	static public void main(String[] args){
		new Carpetas(false);
	}
	
}

class Directorio{
	File directorio;
	int numeroPdfs = 0;
	boolean urgente = false;
	
	Directorio(File carpeta){
		directorio = carpeta;
		
		numeroPdfs = directorio.listFiles(new FilenameFilter(){
			public boolean accept(File directorio, String name){
				return name.toLowerCase().endsWith(".pdf");
			}
		}).length;

	}
}
