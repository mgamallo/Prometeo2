package es.mgamallo.prometeo;

import java.util.ArrayList;

public class Norma {
	String fecha = "";
	String texto = "";
	String textoSinFormato = "";
	ArrayList<String> servicios = new ArrayList<String>();
	String rutaImagen = "No";
	String rutaNormaTxt = "";
	
	public String setTextoSinFormato(){
		
		String cadenaFinal = "";
		boolean ignorarMenorQue = false;
		boolean ignorarCodigo = false;
		
		for(int i=0;i<texto.length();i++){
			
			char caracter = texto.charAt(i);
			
			if(caracter == '<'){
				ignorarMenorQue = true;
			}
			else if(caracter == '&'){
				ignorarCodigo = true;
			}
			else if(caracter == '>' && ignorarMenorQue){
				ignorarMenorQue = false;
				continue;
			}
			else if(caracter == ';' && ignorarCodigo ){
				ignorarCodigo = false;
				continue;
			}
			
			if(!ignorarCodigo && !ignorarMenorQue){
				cadenaFinal += String.valueOf(caracter);
			}
		}
		
		return cadenaFinal;
	}
	
}
