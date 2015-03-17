package es.mgamallo.prometeo;

import java.util.Iterator;

public class XedocIndividualJacob {

	public String nhc = "";
	public String servicio = "";
	private String nombreServicio = "";
	public String tipoDocumento = "";
	public String titulo = "";
	public String fecha = "";
	public String carpeta = "";
	public String nombreFichero = "";
	
	public XedocIndividualJacob(String nombreFichero){
		
		this.nombreFichero = nombreFichero;
		
		String campos[] = nombreFichero.split(" @");
		if(campos.length == 4){
			nhc = campos[1];
			servicio = campos[2];
			
			int index = campos[0].indexOf("$");
			if(index != -1){
				carpeta = campos[0].substring(index+1);
			}

			int indexf = campos[3].lastIndexOf(" r _f");
			campos[3] = campos[3].substring(0,indexf);
			
			index = campos[3].indexOf("(");
			if(index != -1){
				tipoDocumento = campos[3].substring(0,index-1);
				titulo = campos[3].substring(index + 1,campos[3].length()-1);
			}
			else{
				tipoDocumento = campos[3];
			}
		}
	}
	
	public String obtieneCodigoJavascript(){
		
		String cadena = insertaIds();
		
		cadena += buscaNodo();
		
		cadena += putFecha();

		// cadena += putServicio();
		
		return cadena;
	}
	
	private String insertaIds(){
		String cadena = ""
				+ "var buscando = document.querySelectorAll('.custom-combobox-input');"
				+ "buscando[1].setAttribute('id','tipoDocXedoc');"
				+ "buscando[2].setAttribute('id','servicioXedoc');"
				+ "";
		
		return cadena;
	}
	
	private String buscaNodo(){
		
		String id = "";
		String cadena = "var fecha;";
		if(servicio.equals("HOSP")){
			id = "HOS";
			cadena = id;
		}
		else if(servicio.equals("URG")){
			id = "URG";
		}
		else if(servicio.equals("QUI")){
			id = "QUI";
		}
		else{
			id = servicio;
		}

		if(id.equals("HOS") || id.equals("URG") || id.equals("QUI")){
			cadena = "var nodo = document.getElementById('" + id + "-noSeleccionable-rama');"
					+ "var anclaNodo = nodo.getElementsByTagName('li')[0].getElementsByTagName('a')[0];"
					+ "anclaNodo.setAttribute('id','nodoseleccionado');"
					
					// + "anclaNodo.click();"
					+ "";
			
			if(id.equals("HOS") || id.equals("URG")){
				cadena += ""
						+ "fecha = anclaNodo.innerHTML.slice(-11);"
						+ "";
			}
			else{
				cadena += ""
						+ "var index = anclaNodo.innerHTML.lastIndexOf('/');"
						+ "fecha = anclaNodo.innerHTML.slice(index-5,index+5);"
						+ "";
			}
			
			cadena += "alert(fecha);";
		}

		else{
			// Es una consulta y hay que saber primero si va a ir al nodo general o no
			// Programar
			
			cadena = "var nodo = document.getElementById('360340-1-2-" + id + "');"
					+ "var anclaNodo = nodo.getElementsByTagName('a')[0];"
					+ "anclaNodo.setAttribute('id','nodoseleccionado');"
					// + "anclaNodo.click();"
					+ "";
		}
		
		// nombreServicio = (String) InicioXedoc.nombreServicios.get(servicio);
		
		
		return cadena;
	}
	
	
	private String putFecha(){
		
		String cadena = ""
				+ "var cajaFecha = document.getElementById('{hc}dataVersion-{hc}docExt');"
				+ "cajaFecha.value = fecha;"
		//		+ "alert(anclaNodo.id);"
				+ "anclaNodo.click();";
		
		return cadena;
	}
	
	public String putServicio(String servicioFinal){
		
		/*
		String cadena = ""
				+ "var mapServicio = {};"
				+ "";
		
		for(Iterator it = InicioXedoc.nombreServicios.keySet().iterator();it.hasNext();){
			String clave = (String) it.next();
			String valor = (String) InicioXedoc.nombreServicios.get(clave);
			cadena += "mapServicio." + clave + "= '" + valor + "';";
		}
		
		cadena += "alert(mapServicio['CARC']);";
		*/
		
		String nombreCompleto = servicioFinal + "-" + InicioXedoc.nombreServicios.get(servicioFinal);
		
		System.out.println(nombreCompleto);
		
		String cadena = ""
				
				+ "buscando[2].focus();"
				+ "buscando[2].value = '" + nombreCompleto + "';"
				+ "buscando[1].focus();"
				+ "buscando[1].value = 'Nota ingreso';"				
				+ "alert('" + nombreCompleto + "');"
				+ "";
		
		return cadena;
	}
	
	private String ocultaNodos(){
		String cadena = ""
				+ "var consultas = document.getElementById('CEX-noSeleccionable-rama');"
				+ "var listaconsultas = consultas.getElementsByTagName('li');"
				+ "for(var i=0;i<listaconsultas.length;i++){"
					+ "if(listaconsultas[i].id.indexOf('-noSeleccionable-rama') != -1){"
						+ ""
				;
		
		return cadena;
	}
	
	public void imprimeDatos(){
		System.out.println("nhc " + nhc);
		System.out.println("Servicio " + servicio);
		System.out.println("Tipo de documento " + tipoDocumento);
		System.out.println("Titulo " + titulo);
		System.out.println("Carpeta " + carpeta);
		
	}
	
	public static void main(String args[]){
		XedocIndividualJacob xedoc = new XedocIndividualJacob("025_D_20150227_171616 $430 @118194 @HOSP @Ordes (médicas) r_f.pdf");
		xedoc.imprimeDatos();
	}
}
