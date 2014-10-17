package es.mgamallo.prometeo;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class Carpetas {
	
	static String path = "K:/Desarrollo Zona Pruebas/03 Firmado";
	
	private Directorio[] conjuntoCarpetas;
	
	ArrayList<Directorio> arrayCarpetas = new ArrayList<Directorio>();
	
	Carpetas(boolean carpetaFirmados){
		if(!carpetaFirmados){
			path = "H:/02 Area Pruebas/03 Firmado";
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
		
		System.out.println();
		
		conjuntoCarpetas = new Directorio[carpetas.length];
		for(int i=0;i<carpetas.length;i++){
			conjuntoCarpetas[i] = new Directorio(carpetas[i]);
			System.out.println(conjuntoCarpetas[i].directorio.getName());
			System.out.println(conjuntoCarpetas[i].numeroPdfs);
		}
		
		System.out.println();
		
		ordenaCarpetas();
		
	}
	
	
	private void ordenaCarpetas(){
		
		ArrayList<Directorio> arrayCarpetasXL = new ArrayList<Directorio>();
		ArrayList<Directorio> arrayCarpetasL = new ArrayList<Directorio>();
		ArrayList<Directorio> arrayCarpetasM = new ArrayList<Directorio>();
		ArrayList<Directorio> arrayCarpetasS = new ArrayList<Directorio>();
		
		for(int i=0;i<conjuntoCarpetas.length;i++){
			if(conjuntoCarpetas[i].numeroPdfs > 89 ){
				arrayCarpetasXL.add(conjuntoCarpetas[i]);
			}
			else if(conjuntoCarpetas[i].numeroPdfs > 49){
				arrayCarpetasL.add(conjuntoCarpetas[i]);
			}
			else if(conjuntoCarpetas[i].numeroPdfs > 19){
				arrayCarpetasM.add(conjuntoCarpetas[i]);
			}
			else{
				arrayCarpetasS.add(conjuntoCarpetas[i]);
			}
		}
		
		arrayCarpetas.addAll(arrayCarpetasXL);
		while(arrayCarpetasM.size() > 1){
			for(int i=0;i<2;i++){
				arrayCarpetas.add(arrayCarpetasM.get(i));
			}
			
			for(int i=0;i<2;i++){
				arrayCarpetasM.remove(0);
			}
		}
		
		for(int i=0;i<arrayCarpetas.size();i++){
			System.out.println(arrayCarpetas.get(i).numeroPdfs);
		}
		
		arrayCarpetas.addAll(arrayCarpetasL);
		arrayCarpetas.addAll(arrayCarpetasM);
		arrayCarpetas.addAll(arrayCarpetasS);
		
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
