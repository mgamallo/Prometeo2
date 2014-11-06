package es.mgamallo.prometeo;

import java.io.File;

public class Documento {
	String nhc = "";
	String servicio = "";
	String nombreNormalizado = "";
	String rutaArchivo;
	
	private String nombreArchivo;
	
	boolean ocr;
	
	Documento(String ruta){
		File fichero = new File(ruta);
		rutaArchivo = ruta;
		nombreArchivo = fichero.getName();
		ocr = getPropiedades();
	}
	
	boolean getPropiedades(){
		
		int aux = nombreArchivo.indexOf("@");
		if(aux == -1){
			return resetPropiedades();
		}
		int auxNHC = nombreArchivo.indexOf("@",aux+1);
		if(auxNHC == -1){
			return resetPropiedades();
		}
		nhc = nombreArchivo.substring(aux + 1, auxNHC -1);

		int auxServ = nombreArchivo.indexOf("@",auxNHC+1);
		if(auxServ == -1){
			return resetPropiedades();
		}
		servicio = nombreArchivo.substring(auxNHC+1,auxServ-1);

		int auxNombre = nombreArchivo.lastIndexOf("r_");
		if(auxNombre == -1){
			return resetPropiedades();
		}
		nombreNormalizado = nombreArchivo.substring(auxServ+1,auxNombre-1);
		return true;
	}
	
	void imprimePropiedades(){
		System.out.println(rutaArchivo);
		System.out.println("NHC: " + nhc);
		System.out.println("Servicio: " + servicio);
		System.out.println("Nombre: " + nombreNormalizado);
	}
	
	boolean resetPropiedades(){
		nhc = "";
		servicio = "";
		nombreNormalizado = "";
		
		return false;
	}
}