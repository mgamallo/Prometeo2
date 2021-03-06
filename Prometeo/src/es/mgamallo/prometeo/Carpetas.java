package es.mgamallo.prometeo;


import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class Carpetas {
	
	 // static String path = "d:/02 Area Pruebas/03 Firmado";
	 static String path = "j:\\DIGITALIZACIÓN\\00 DOCUMENTACION\\03 Firmado";
	 static String pathUrgA = "j:\\DIGITALIZACIÓN\\01 INFORMES URG (Colectiva)\\";
	 static String pathUrgB = "h:\\DIGITALIZACIÓN\\01 INFORMES URG (Colectiva)\\";
	 
	private Directorio[] conjuntoCarpetas;
	
	ArrayList<Directorio> arrayCarpetas = new ArrayList<Directorio>();
	
	public int numeroPdfsTotales = 0;
	public int numeroPdfsPendientes = 0;
	
	Carpetas(boolean carpetaFirmados){
		
		path = Inicio.rutaFirmados;
		
		if(Inicio.usuario.tipoDocumentacion == 0){
			path = Inicio.rutaFirmadosUrgencias + "\\01 " + Inicio.usuario.alias + "\\03 Firmado";
		}
		else if(Inicio.usuario.tipoDocumentacion == 2){
			path = Inicio.rutaFirmadosSalnes;
		}

		
		System.out.println(path);
		
		File caminito = new File(path);
		
		if(Inicio.carpetaDudas){
			
			String nuevaRuta = (Inicio.rutaDudas + "\\" + Inicio.usuario.alias);
			System.out.println("Nueva Ruta: Dudas. " + nuevaRuta);
			File fi = new File(nuevaRuta);
			if(fi.exists()){
				path = nuevaRuta;
			}
			else{
				JOptionPane.showMessageDialog(null, "No tienes dudas.");
				Inicio.carpetaDudas = false;
			}
		}
		else if(Inicio.carpetaXedocFirmado){
			path = Inicio.rutaFirmadosXedoc;
			if(Inicio.usuario.tipoDocumentacion == 0){
				path = Inicio.rutaFirmadosUrgencias + "\\01 " + Inicio.usuario.alias + "\\03 Firmado Xedoc";
			}
			else if(Inicio.usuario.tipoDocumentacion == 2){
				path = Inicio.rutaFirmadosXedocSalnes;
			}
		}
			
		
		
		if(!carpetaFirmados){
			path = "d:/02 Area Pruebas/01 Escaneado";
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
		
		System.out.println("Numero de carpetas " + carpetas.length);
		
		ArrayList<File> carpetasUtiles = new ArrayList<File>();
		
		for(int i=0;i<carpetas.length;i++){
			if(!carpetas[i].getName().contains("Apartado")){
				carpetasUtiles.add(carpetas[i]);
			}
		}

		File carpetasFinales[] = new File[carpetasUtiles.size()];
		
		for(int i=0;i<carpetasFinales.length;i++){
			carpetasFinales[i] = carpetasUtiles.get(i);
		}
		
		
		conjuntoCarpetas = new Directorio[carpetasFinales.length];
		for(int i=0;i<carpetasFinales.length;i++){
			conjuntoCarpetas[i] = new Directorio(carpetasFinales[i]);
			numeroPdfsTotales += conjuntoCarpetas[i].numeroPdfs;
			if(conjuntoCarpetas[i].usuario.length() == 0){
				numeroPdfsPendientes += conjuntoCarpetas[i].numeroPdfs;
			}
			System.out.println("A      " + conjuntoCarpetas[i].directorio.getName());
			System.out.println("B      " + conjuntoCarpetas[i].numeroPdfs);
		}
		
		System.out.println();

		if(!Inicio.carpetaXedocFirmado){
			ordenaCarpetas();
			getCodigoJavascript();
		}
		else{
			ordenaCarpetas();
			getCodigoJavascriptXedoc();
		}
		
		
		
		
	//	System.out.println("Html creado...");
		
	//	CadenasJavascript.getCarpetas(true, false, arrayCarpetas);
		
	}
	
	public String getCodigoJavascript(){
		return CadenasJavascript.getCarpetas(true, false, arrayCarpetas);
	}
	
	public String getCodigoJavascriptXedoc(){
		System.out.println("Entramos en el metodo de javascript");
		
		// return CadenasJavascript.getCarpetasXedoc(true, false, arrayCarpetas, numeroPdfsTotales);
		return CadenasJavascript.editCarpetasXedoc(true, false, arrayCarpetas, numeroPdfsTotales);
	}
	
	
	private void ordenaCarpetas(){
		
		ArrayList<Directorio> arrayCarpetasXL = new ArrayList<Directorio>();
		ArrayList<Directorio> arrayCarpetasL = new ArrayList<Directorio>();
		ArrayList<Directorio> arrayCarpetasM = new ArrayList<Directorio>();
		ArrayList<Directorio> arrayCarpetasS = new ArrayList<Directorio>();
		
		boolean terminar = false;
		
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
		
		// Se añaden primero las carpetas grandes
		arrayCarpetas.addAll(arrayCarpetasXL);
		
		
		Directorio ultimoM = new Directorio();
		boolean arrayMimpar = false;
		
		System.out.println("Tamaño del array M: " + arrayCarpetasM.size());
		
		if(arrayCarpetasM.size() % 2 == 1){
			ultimoM = arrayCarpetasM.get(arrayCarpetasM.size()-1);
			arrayCarpetasM.remove(arrayCarpetasM.size()-1);
			arrayMimpar = true;
		}
		
		
		if(arrayCarpetasL.size() > 0){
			arrayCarpetas.add(arrayCarpetasL.get(0));
			arrayCarpetasL.remove(0);
		}
		
		
		if(arrayCarpetasM.size() > 1){
			
			for(int i=0;i<2;i++){
				arrayCarpetas.add(arrayCarpetasM.get(0));
				arrayCarpetasM.remove(0);
			}
			
			if(arrayCarpetasM.size() > 1){    		// o 0 o 2
				if(arrayCarpetasL.size() > 0){
					arrayCarpetas.add(arrayCarpetasM.get(0));
					arrayCarpetasM.remove(0);
					arrayCarpetas.add(arrayCarpetasL.get(0));
					arrayCarpetasL.remove(0);
					arrayCarpetas.add(arrayCarpetasM.get(0));
					arrayCarpetasM.remove(0);
				}
				else{
					arrayCarpetas.addAll(arrayCarpetasM);
					arrayCarpetasM.clear();
					if(arrayMimpar){
						arrayCarpetas.add(ultimoM);
						arrayMimpar = false;
					}					
					arrayCarpetas.addAll(arrayCarpetasS);
					arrayCarpetasS.clear();
					terminar = true;
				}
			}
		}
		
		
		
		if(arrayCarpetasL.size() > 0 && !terminar){
				arrayCarpetas.add(arrayCarpetasL.get(0));
				arrayCarpetasL.remove(0);
		}
		else if(!terminar){
			arrayCarpetas.addAll(arrayCarpetasM);
			arrayCarpetasM.clear();
			if(arrayMimpar){
				arrayCarpetas.add(ultimoM);
				arrayMimpar = false;
			}					
			arrayCarpetas.addAll(arrayCarpetasS);
			arrayCarpetasS.clear();
			terminar = true;
		}
		
		
			
		if(arrayCarpetasM.size() > 1 && !terminar){
				arrayCarpetas.add(arrayCarpetasM.get(0));
				arrayCarpetasM.remove(0);
				arrayCarpetas.add(arrayCarpetasM.get(0));
				arrayCarpetasM.remove(0);				
		}
		else if(!terminar){
			arrayCarpetas.addAll(arrayCarpetasL);
			if(arrayMimpar){
				arrayCarpetas.add(ultimoM);
				arrayMimpar = false;
			}
			arrayCarpetas.addAll(arrayCarpetasS);
			terminar = true;
		}
		
		int claveAnterior = 0;  //  numero fuera de rango
			
		while(arrayCarpetasM.size() > 1 && arrayCarpetasL.size() > 0 && !terminar){
				int clave = (int) (Math.random()*(4 - 1)) + 1;
				
				if(clave != claveAnterior){
					claveAnterior = clave;
					switch (clave) {
					case 1:
						
						arrayCarpetas.add(arrayCarpetasM.get(0));
						arrayCarpetasM.remove(0);
						arrayCarpetas.add(arrayCarpetasM.get(0));
						arrayCarpetasM.remove(0);
						arrayCarpetas.add(arrayCarpetasL.get(0));
						arrayCarpetasL.remove(0);
						
						break;
						
					case 2:
						
						arrayCarpetas.add(arrayCarpetasM.get(0));
						arrayCarpetasM.remove(0);
						arrayCarpetas.add(arrayCarpetasL.get(0));
						arrayCarpetasL.remove(0);
						arrayCarpetas.add(arrayCarpetasM.get(0));
						arrayCarpetasM.remove(0);
						
						break;	
						
					case 3:
						
						arrayCarpetas.add(arrayCarpetasL.get(0));
						arrayCarpetasL.remove(0);
						arrayCarpetas.add(arrayCarpetasM.get(0));
						arrayCarpetasM.remove(0);
						arrayCarpetas.add(arrayCarpetasM.get(0));
						arrayCarpetasM.remove(0);
						
						break;	

					default:
						break;
					}
			}

		}
		
		if(!terminar){
			arrayCarpetas.addAll(arrayCarpetasL);
			arrayCarpetasL.clear();
			arrayCarpetas.addAll(arrayCarpetasM);
			arrayCarpetasM.clear();
			if(arrayMimpar){
				arrayCarpetas.add(ultimoM);
			}					
			arrayCarpetas.addAll(arrayCarpetasS);
			arrayCarpetasS.clear();
		}

		
		System.out.println("El numero de carpetas añadido es " + arrayCarpetas.size());
		
		for(int i=0;i<arrayCarpetas.size();i++){
			System.out.println(arrayCarpetas.get(i).numeroPdfs);
		}
				
	}
	
	
	static public void main(String[] args){
		new Carpetas(true);
	}
	
}

