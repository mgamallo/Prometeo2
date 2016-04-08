package es.mgamallo.prometeo;

public class Usuario {
	String alias = null;
	String usuario=null;
	String password = null;
	
	String imagen = null;
	String fotoFinal = null;
	
	CarpetaXedoc avisosXedoc = null;  // Almacena el nº de doc pendientes, y si alguno está corrupto
	
	int tipoDocumentacion = 1;  // 0 urgencias, 1 document, 2 salnes
	boolean xedoc = false;
	
	boolean tieneAvisos = false;
}
