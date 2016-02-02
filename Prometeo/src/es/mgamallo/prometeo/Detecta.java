package es.mgamallo.prometeo;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class Detecta {
	
	static public ArrayList<String> detectaDudasJavier(){
		ArrayList<String> usuariosConDudas = new ArrayList<String>();
		
		for(int i=0;i< Inicio.usuarios.length;i++){
			 System.out.println(Inicio.usuarios[i].alias);
			
			String rutaDudas = Inicio.rutaDudas + "\\" + Inicio.usuarios[i].alias;
			File rutaUsuario = new File(rutaDudas);
			if(rutaUsuario.exists()){
				File[] carpetasConDudas = rutaUsuario.listFiles(new FilenameFilter() {
					
					@Override
					public boolean accept(File dir, String name) {
						// TODO Auto-generated method stub
						return new File(dir, name).isDirectory();
					}
				});
				
				for(int j=0;j<carpetasConDudas.length;j++){
					if(!carpetasConDudas[j].getName().contains("@")){
						usuariosConDudas.add(Inicio.usuarios[i].alias);
						break;
					}
				}
			}
		}
		
		for(int i=0;i<usuariosConDudas.size();i++){
			System.out.println(usuariosConDudas.get(i) + " tiene dudas. ");
		}
		
		return usuariosConDudas;
	}

	static public int dudasResueltas(){
		
		int numeroCarpetasDudas = 0;
		
		File file = new File(Inicio.rutaDudas + "\\" + Inicio.usuario.alias);
		
		if(file.exists()){
			File[] directorios = file.listFiles(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					// TODO Auto-generated method stub
					return new File(dir, name).isDirectory();
				}
			});
			
			for(int i=0;i<directorios.length;i++){
				String nombreFichero = directorios[i].getName();
				if(nombreFichero.indexOf("@") != -1){
					if(!nombreFichero.contains("@" + Inicio.usuario.usuario)){
						numeroCarpetasDudas++;
					}
				}
				System.out.println();
			}
			
		}
		
		return numeroCarpetasDudas;
	}
	
	static public int apartadoPendiente(boolean firmado){
		int numeroApartadoPendientes = 0;
		
		String ruta = "";
		
	//	System.out.println("Ruta firmadosUrgencias: " + Inicio.rutaFirmadosUrgencias);
		
		if(firmado){  // Carpeta firmado
			if(Inicio.usuario.tipoDocumentacion == 0){
				ruta = Inicio.rutaFirmadosUrgencias + "\\01 " + Inicio.usuario.alias + "\\03 Firmado\\";
			}
			else if(Inicio.usuario.tipoDocumentacion == 1){
				ruta = Inicio.rutaFirmados + "\\";
			}
			else if(Inicio.usuario.tipoDocumentacion == 2){
				ruta = Inicio.rutaFirmadosSalnes + "\\";
			}
			ruta += ("Apartado por " + Inicio.usuario.alias + ". Pendiente");
		}
		else{  // Carpeta revisado
			if(Inicio.usuario.tipoDocumentacion == 0){
				ruta = Inicio.rutaFirmadosUrgencias + "\\01 " + Inicio.usuario.alias + "\\02 Revisado\\";
			}
			else if(Inicio.usuario.tipoDocumentacion == 1){
				ruta = Inicio.rutaRevisados + "\\";
			}
			else if(Inicio.usuario.tipoDocumentacion == 2){
				ruta = Inicio.rutaRevisados + "\\";
			}
			ruta += ("Apartado por " + Inicio.usuario.alias);
		}

		

		
		System.out.println(ruta);
		
		File file = new File(ruta);
		
		if(file.exists()){

			
			File[] directorios = file.listFiles();
			for(int i=0;i<directorios.length;i++){
				if(directorios[i].getName().endsWith("pdf") || directorios[i].isDirectory()){
					numeroApartadoPendientes++;
				}
			}

			
			System.out.println("Tienes apartados..." + numeroApartadoPendientes + " pdfs o carpetas.");
			
		}
		else{
			System.out.println("No encontrado nada en apartado");
		}
		
		
		return numeroApartadoPendientes;
	}
	

}
