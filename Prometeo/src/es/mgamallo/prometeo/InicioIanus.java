package es.mgamallo.prometeo;

import java.io.File;

public class InicioIanus {

	static final String RUTA ="j:/digitalización/00 documentacion/03 Firmado"; 
	static final String RUTAB ="H:/DIGITALIZACIÓN/00 DOCUMENTACION/03 Firmado";
	static final String RUTAURG ="j:/DIGITALIZACIÓN/01 INFORMES URG (Colectiva)"; 
	static final String RUTAURGB ="H:/DIGITALIZACIÓN/01 INFORMES URG (Colectiva)";	
	static final String RUTAPC = "c:/ianus/ianus.txt"; 
		
	static int documentacion= 0;  // 0	Urgencias
								  // 1 	Documentacion	
	
	static String[] rutaCompletaPdfs;
	static File[] tandaDePdfs;
	static public int pdfSeleccionado = 0;


}
