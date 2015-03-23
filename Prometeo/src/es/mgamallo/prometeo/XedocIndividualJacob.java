package es.mgamallo.prometeo;

import java.util.Iterator;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class XedocIndividualJacob {

	public String nhc = "";
	public String servicio = "";
	private String aliasServicio = "";
	private String nombreServicio = "";
	public String tipoDocumento = "";
	public String titulo = "";
	public String fecha = "";
	public String carpeta = "";
	public String nombreFichero = "";
	
	public String tipoSubida = "";       // x q i f
	
	public Dispatch documento;
	public ActiveXComponent xedocDocumento;
	
	public XedocIndividualJacob(String nombreFichero, Dispatch documento, ActiveXComponent xedocDocumento){
		
		this.nombreFichero = nombreFichero;
		this.documento = documento;
		this.xedocDocumento = xedocDocumento;
		
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
		
		tipoSubida = detectaTipoNodo(servicio, campos[3]);
		System.out.println("El tipo de subida es... " + tipoSubida);
	}


	
	public void buscaNodo(){
		
		String id = "";
	
		if(servicio.equals("HOSP")){
			id = "HOS";
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

		if(tipoSubida.toLowerCase().equals("q")){
			id = "QUI";
		}
		
		if(id.equals("HOS") || id.equals("URG") || id.equals("QUI")){
			String nombreId = id + "-noSeleccionable-rama";
			Dispatch nodo = Dispatch.call(documento, "getElementById",nombreId).getDispatch();
			Dispatch nodoLis = Dispatch.call(nodo, "getElementsByTagName","li").getDispatch();
			System.out.println("Numero de uls...." + Dispatch.get(nodoLis,"length").toString());
			Dispatch nodoLi = Dispatch.get(nodoLis, "0").getDispatch();
			Dispatch nodoA = Dispatch.call(nodoLi, "getElementsByTagName","a").getDispatch();
			System.out.println("Numero de as...." + Dispatch.get(nodoA,"length").toString());
			
			Dispatch nodoAncla = Dispatch.get(nodoA,"0").getDispatch();
			Dispatch.call(nodoAncla,"setAttribute","id","nodoSeleccionado");
	//		Dispatch.call(xedocDocumento, "navigate","javascript: var hola = document.getElementById('nodoSeleccionado');alert(hola.innerHTML);");
			Dispatch.call(nodoAncla, "click");

			
			/*	
			cadena = "var nodo = document.getElementById('" + id + "-noSeleccionable-rama');"
					+ "var anclaNodo = nodo.getElementsByTagName('li')[0].getElementsByTagName('a')[0];"
					+ "anclaNodo.setAttribute('id','nodoseleccionado');"
					
					// + "anclaNodo.click();"
					+ "";
			*/		
			
			if(id.equals("HOS") || id.equals("URG")){
				
				String cadena = Dispatch.get(nodoAncla,"innerHTML").getString();
				System.out.println(cadena);
				fecha = cadena.substring(cadena.length()-11);
				/*
				cadena += ""
						+ "fecha = anclaNodo.innerHTML.slice(-11);"
						+ "";
				*/
				
				int index = cadena.lastIndexOf(">") + 1;
				
				if(id.equals("HOS")){
					aliasServicio = cadena.substring(index+4, index + 8);
					
				}
				else{
					aliasServicio = cadena.substring(index,index + 4);
				}
				
				System.out.println(aliasServicio);
				
			}
			else{
				
				fecha = Dispatch.get(nodoAncla,"innerHTML").getString();
				int index = fecha.lastIndexOf("/");
				fecha = fecha.substring(index-5,index+5);
				/*
				cadena += ""
						+ "var index = anclaNodo.innerHTML.lastIndexOf('/');"
						+ "fecha = anclaNodo.innerHTML.slice(index-5,index+5);"
						+ "";
				*/
			}
			
			System.out.println(fecha);
			
		}

		else{
			// Es una consulta y hay que saber primero si va a ir al nodo general o no
			// Programar
		/*	
			cadena = "var nodo = document.getElementById('360340-1-2-" + id + "');"
					+ "var anclaNodo = nodo.getElementsByTagName('a')[0];"
					+ "anclaNodo.setAttribute('id','nodoseleccionado');"
					// + "anclaNodo.click();"
					+ "";
					*/
		}
		
		// nombreServicio = (String) InicioXedoc.nombreServicios.get(servicio);

	}
	
	
	public void putFecha(Dispatch fechaDispatch){
		
		Dispatch.put(fechaDispatch,"value",fecha);
		
	}
	
	public void seleccionarServicio(){
		Dispatch caja = Dispatch.call(documento, "getElementById","cajaColoreada2").getDispatch();
		String nombreCompleto = aliasServicio + "-" + (String) InicioXedoc.nombreServicios.get(aliasServicio);
		Dispatch.put(caja,"value",nombreCompleto);
		
		Dispatch selectServicio = Dispatch.call(documento, "getElementById","{hc}servicioEspecialidad-{hc}docExt").getDispatch();
		Dispatch opciones = Dispatch.call(selectServicio, "options").getDispatch();
		
		Variant variant = Dispatch.get(opciones, "innerHTML");
		
		System.out.println(variant.toString());
		
		/*
		Dispatch opcion = Dispatch.get(opciones,String.valueOf(index)).getDispatch();
		Dispatch.put(opcion,"selected","true");
		*/
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
	
	private static String detectaTipoNodo(String servicio, String nombreDocumento){
		
		String tipoNodo = "x";    // Nodo padre, sin excepciones
		
		/*
		for(int i=0;i<Inicio.tablaExcepcionesXedoc.size();i++){
			System.out.println(Inicio.tablaExcepcionesXedoc.get(i).servicio);
			for(int j=0;j<Inicio.tablaExcepcionesXedoc.get(i).excepciones.size();j++){
				System.out.println(Inicio.tablaExcepcionesXedoc.get(i).excepciones.get(j).nombreDocumento + 
						" Tipo de excepción: " + Inicio.tablaExcepcionesXedoc.get(i).excepciones.get(j).tipoExcepcion);
			}
		}
		*/
		
		
		System.out.println("Tabla de excepciones .... ");
		for(int i=0;i<Inicio.tablaExcepcionesXedoc.size();i++){
			System.out.println("Servicio: " + InicioIanus.tablaExcepciones);
			if(servicio.equals(Inicio.tablaExcepcionesXedoc.get(i).servicio)){
				for(int j=0;j<Inicio.tablaExcepcionesXedoc.get(i).excepciones.size();j++){
					if(nombreDocumento.equals(Inicio.tablaExcepcionesXedoc.get(i).excepciones.get(j).nombreDocumento)){
						tipoNodo = Inicio.tablaExcepcionesXedoc.get(i).excepciones.get(j).tipoExcepcion;
					}
				}
			}
		}
		return tipoNodo;
	}

}
