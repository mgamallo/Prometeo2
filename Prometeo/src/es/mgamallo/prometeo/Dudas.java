package es.mgamallo.prometeo;

import java.io.File;
import java.io.IOException;

/*
 * 		Accede a las carpetas de dudas, apartado firmado, apartado revisado.
 * 
 * 
 */

public class Dudas {
	
	static public void abrirCarpetaApartadoFirmado(){
		
		String rutaCarpetaFirmadosApartados = Inicio.rutaFirmados + "\\Apartado por " + Inicio.usuario.alias + ". Pendiente";
		if(Inicio.usuario.urgencias){
			rutaCarpetaFirmadosApartados = Inicio.rutaFirmadosUrgencias + "\\01 " + Inicio.usuario.alias 
					+ "\\03 Firmado\\Apartado por " + Inicio.usuario.alias + ". Pendiente";
						;
		}
		
		// Borrar esta asignacion
	//	rutaCarpetaFirmados = "j:\\digitalización\\00 documentacion\\03 Firmado";
		
		File f = new File(rutaCarpetaFirmadosApartados);
		if(f.exists()){
	    	String cadena = "explorer.exe " + rutaCarpetaFirmadosApartados;
	    	System.out.println(rutaCarpetaFirmadosApartados);
			try {
				Runtime.getRuntime().exec(cadena);
			} catch (IOException ev) {
				// TODO Auto-generated catch block
				ev.printStackTrace();
			}
		}

	}
	
	static public void abrirCarpetaApartadoRevisado(){
		String rutaCarpetaRevisadoApartados = Inicio.rutaRevisados + "\\Apartado por " + Inicio.usuario.alias;
		if(Inicio.usuario.urgencias){
			rutaCarpetaRevisadoApartados = Inicio.rutaFirmadosUrgencias + "\\01 " + Inicio.usuario.alias 
					+ "\\02 Revisado\\Apartado por " + Inicio.usuario.alias + ". Pendiente" ;
			System.out.println(rutaCarpetaRevisadoApartados);
		}
		
		File f = new File(rutaCarpetaRevisadoApartados);
		if(f.exists()){
	    	String cadena = "explorer.exe " + rutaCarpetaRevisadoApartados;
			try {
				Runtime.getRuntime().exec(cadena);
			} catch (IOException ev) {
				// TODO Auto-generated catch block
				ev.printStackTrace();
			}
		}
	}
	
	static public void abrirCarpetaXedocOriginales(){
		
		Calendario calendario = new Calendario();
		String rutaCarpetaXedocOriginales = calendario.getCarpetaFinal(false);
		
		File f = new File(rutaCarpetaXedocOriginales);
		if(f.exists()){
	    	String cadena = "explorer.exe " + rutaCarpetaXedocOriginales;
			try {
				Runtime.getRuntime().exec(cadena);
			} catch (IOException ev) {
				// TODO Auto-generated catch block
				ev.printStackTrace();
			}
		}
	}
	
}
