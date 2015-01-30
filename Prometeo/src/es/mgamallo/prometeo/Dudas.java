package es.mgamallo.prometeo;

import java.io.IOException;

/*
 * 		Accede a las carpetas de dudas, apartado firmado, apartado revisado.
 * 
 * 
 */

public class Dudas {

	static public void cargarDudas(){
		
	}
	
	static public void abrirCarpetaApartadoFirmado(){
		
		String rutaCarpetaFirmadosApartados = Inicio.rutaFirmados + "\\Apartado por " + Inicio.usuario.alias + ". Pendiente";
		if(Inicio.usuario.urgencias){
			rutaCarpetaFirmadosApartados = Inicio.unidadHDDvirtual + Inicio.rutaFirmadosUrgencias + "\\01 " + Inicio.usuario.alias + "\\03 Firmado" ;
		}
		
		// Borrar esta asignacion
	//	rutaCarpetaFirmados = "j:\\digitalización\\00 documentacion\\03 Firmado";
		
    	String cadena = "explorer.exe " + rutaCarpetaFirmadosApartados;
		try {
			Runtime.getRuntime().exec(cadena);
		} catch (IOException ev) {
			// TODO Auto-generated catch block
			ev.printStackTrace();
		}
	}
	
	static public void abrirCarpetaApartadoRevisado(){
		String rutaCarpetaRevisadoApartados = Inicio.rutaRevisados + "\\Apartado por " + Inicio.usuario.alias;
		if(Inicio.usuario.urgencias){
			rutaCarpetaRevisadoApartados = Inicio.unidadHDDvirtual + Inicio.rutaFirmadosUrgencias + "\\01 " + Inicio.usuario.alias + "\\03 Firmado" ;
		}
		
		// Borrar esta asignacion
	//	rutaCarpetaFirmados = "j:\\digitalización\\00 documentacion\\03 Firmado";
		
    	String cadena = "explorer.exe " + rutaCarpetaRevisadoApartados;
		try {
			Runtime.getRuntime().exec(cadena);
		} catch (IOException ev) {
			// TODO Auto-generated catch block
			ev.printStackTrace();
		}
	}
}
