package es.mgamallo.prometeo;

import java.util.ArrayList;

public class ExcepcionesServicio {

	public String servicio = "";
	public ArrayList<Excepcion> excepciones = new ArrayList<Excepcion>();
}


class Excepcion{
	public String nombreDocumento;
	public String tipoExcepcion;
	
	Excepcion(String nombreDocumento, String tipoExcepcion){
		this.nombreDocumento = nombreDocumento;
		this.tipoExcepcion = tipoExcepcion;				
	}
}