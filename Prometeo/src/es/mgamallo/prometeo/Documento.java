package es.mgamallo.prometeo;

public class Documento {
	String nhc = "";
	String servicio = "";
	String nombreNormalizado = "";
	String rutaArchivo;
	boolean ocr;
	
	Documento(String ruta){
		rutaArchivo = ruta;
		ocr = getPropiedades();
	}
	
	boolean getPropiedades(){
		
		int aux = rutaArchivo.indexOf("@");
		if(aux == -1){
			return resetPropiedades();
		}
		int auxNHC = rutaArchivo.indexOf("@",aux+1);
		if(auxNHC == -1){
			return resetPropiedades();
		}
		nhc = rutaArchivo.substring(aux + 1, auxNHC -1);

		int auxServ = rutaArchivo.indexOf("@",auxNHC+1);
		if(auxServ == -1){
			return resetPropiedades();
		}
		servicio = rutaArchivo.substring(auxNHC+1,auxServ-1);

		int auxNombre = rutaArchivo.lastIndexOf("r_");
		if(auxNombre == -1){
			return resetPropiedades();
		}
		nombreNormalizado = rutaArchivo.substring(auxServ+1,auxNombre-1);
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