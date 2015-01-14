package es.mgamallo.prometeo;


import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Random;

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
		
		if(Inicio.usuario.urgencias){
			path = pathUrgA + "01 " + Inicio.usuario.alias + "\\03 Firmado";
		}
		
		File caminito = new File(path);
		if(!caminito.exists()){
			if(Inicio.usuario.urgencias){
				path = pathUrgB + "01 " + Inicio.usuario.alias + "\\03 Firmado";
			}
			else{
				path = "h:\\DIGITALIZACIÓN\\00 DOCUMENTACION\\03 Firmado";
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
			System.out.println(conjuntoCarpetas[i].directorio.getName());
			System.out.println(conjuntoCarpetas[i].numeroPdfs);
		}
		
		System.out.println();

		ordenaCarpetas();
		
		getCodigoJavascript();
		
	//	System.out.println("Html creado...");
		
	//	CadenasJavascript.getCarpetas(true, false, arrayCarpetas);
		
	}
	
	public String getCodigoJavascript(){
		return CadenasJavascript.getCarpetas(true, false, arrayCarpetas);
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

class Directorio{
	File directorio;
	int numeroPdfs = 0;
	boolean urgente = false;
	boolean asociado = false;
	
	String usuario = "";
	String servicio = "";
	String color = "";
	String dia = "";
	String numCarpeta = "";
	
	Directorio(){
		
	}
	
	Directorio(File carpeta){
		directorio = carpeta;

		
		numeroPdfs = directorio.listFiles(new FilenameFilter(){
			public boolean accept(File directorio, String name){
				return name.toLowerCase().endsWith(".pdf");
			}
		}).length;
		
		getDatos(carpeta.getName());
	}
	
	Directorio(File carpeta, boolean subidos){
		directorio = carpeta;

		
		numeroPdfs = directorio.listFiles(new FilenameFilter(){
			public boolean accept(File directorio, String name){
				return name.toLowerCase().endsWith(".pdf");
			}
		}).length;

		servicio = directorio.getName();
		
		int index = servicio.lastIndexOf("@");
		if(index != -1){
			usuario = Inicio.usuario.alias;
			servicio = servicio.substring(0,index);
		}
	}
	
	
	private void getDatos(String nombreCarpeta){
		
		
		String arrayColores[] = { "yellow","brown", "lime", "green", 
								  "emerald", "mauve", "teal", "cyan", "cobalt", "indigo",
								  "lightBlue", "violet", "lightTeal", "pink", "lightOlive",
								  "magenta", "crimson", "lightPink", "orange", 
								  "lightGreen", "amber", "darkCobalt"
								  };
		
		int numColores = arrayColores.length;
		
		String cadena = nombreCarpeta;
		System.out.println("Número de colores " + arrayColores.length);
		
		int index = nombreCarpeta.lastIndexOf("@");
		if (index != -1){
			usuario = nombreCarpeta.substring(index+1);
			//System.out.println(usuario);
			cadena= cadena.substring(0,index);
		}
		
		
		int indexDia = nombreCarpeta.indexOf("#");
		if(indexDia != -1){
			cadena = cadena.substring(indexDia + 1);
			int primerEspacioBlanco = cadena.indexOf(" ");
			numCarpeta = cadena.substring(0,primerEspacioBlanco);
			System.out.println(numCarpeta);
			
			cadena = cadena.substring(primerEspacioBlanco + 1);
			if(cadena.length() > 2 && cadena.charAt(2) == ' '){
					dia = cadena.substring(0,2);
					cadena = cadena.substring(3);
					//System.out.println(dia);
			}
		}

		
		servicio = cadena;
		
		cadena = cadena.toLowerCase();
		
		int claveColor = (int) (Math.random()*((numColores-1) - 0)) + 0;
		
		String colorDia[] = {"bg-yellow", "bg-teal","bg-amber","bg-lightBlue","bg-emerald"};
		String colorFinal = "";

		dia = dia.toLowerCase();
		if(dia.equals("lu")){
			colorFinal = colorDia[0];
		}else if(dia.equals("ma")){
			colorFinal = colorDia[1];
		}else if(dia.equals("mi")){
			colorFinal = colorDia[2];
		}else if(dia.equals("ju")){
			colorFinal = colorDia[3];
		}else if(dia.equals("vi")){
			colorFinal = colorDia[4];
		}else{
			colorFinal = "bg-" + arrayColores[claveColor];
		}
		
		if(cadena.contains("anr")){
			// servicio = "ANRC";
			color = "bg-lightRed";
		}
		else if(cadena.contains("car")){
			// servicio = "CARC";
			color = "bg-red";
		}
		else if(cadena.contains("cons")){
			// servicio = "Consentimientos";
			color = "bg-crimson";
		}
		else if(cadena.contains("ingr")){
			// servicio = "Ingresos";
			color = colorFinal;
		}
		else{
			// servicio = cadena.toUpperCase();
			color = colorFinal;
		}
		if(cadena.toLowerCase().contains("urg")){
			// servicio = " urg.";
			color = "bg-lightRed";
		}
		if(!usuario.equals("")){
			color = "bg-steel";
		}
		
		System.out.println("Usuario: " + usuario);
		System.out.println("Servicio: " + servicio);
		System.out.println("Color: " + color);
		System.out.println("Dia: " + dia);
		System.out.println("Pdfs: " + numeroPdfs);
	}
}
