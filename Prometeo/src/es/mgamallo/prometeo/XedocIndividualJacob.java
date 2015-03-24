package es.mgamallo.prometeo;

import java.util.Iterator;
import java.util.TreeMap;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class XedocIndividualJacob {

	private final String CONTINXENCIA = "Documento sen tipo (continxencia)";
	
	public String nhc = "";
	public String servicio = "";
	
	private String aliasServicio = "";
	private String nombreServicio = "";
	
	public String tipoDocumento = "";
	public String titulo = "";
	private TreeMap<String, String> nombreDocumentos = new TreeMap<String, String>();
	private boolean tieneTitulo = false;
	
	public String fecha = "";
	
	public String carpeta = "";
	public String nombreFichero = "";
	
	public String tipoSubida = "";       // x q i f e
	
	public Dispatch documento;
	public ActiveXComponent xedocDocumento;
	
	public XedocIndividualJacob(String nombreFichero, Dispatch documento, ActiveXComponent xedocDocumento){
		
		this.nombreFichero = nombreFichero;
		this.documento = documento;
		this.xedocDocumento = xedocDocumento;
		
		nombreDocumentos = Inicio.leerExcel.nombreDocumentos;
		
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
				tieneTitulo = true;
			}
			else{
				tipoDocumento = campos[3];
			}
		}
		
		if(!nombreDocumentos.containsKey(tipoDocumento)){
			titulo = tipoDocumento;
			tipoDocumento = CONTINXENCIA;
			tieneTitulo = true;
		}
		
		tipoSubida = detectaTipoNodo(servicio, campos[3]);
		System.out.println("El tipo de subida es... " + tipoSubida);
	}


	
	public void buscaNodo(){
		
		String id = "";
	
		if(servicio.equals("HOSP")){
			id = "HOS";
			if(tipoSubida.equals("q")){
				id = "QUI";
			}
		}
		else if(servicio.equals("URG")){
			id = "URG";
		}
		else if(servicio.equals("CIA")){
			id = "QUI";
			if(!tipoSubida.equals("q")){
				id = "HOS";
			}
		}
		else{
			id = servicio;
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
			Dispatch.call(nodoAncla, "click");
			
			if(id.equals("HOS") || id.equals("URG")){
				
				String cadena = Dispatch.get(nodoAncla,"innerHTML").getString();
				System.out.println(cadena);
				fecha = cadena.substring(cadena.length()-11);
				
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
				
				String cadena = Dispatch.get(nodoAncla,"innerHTML").getString();
				System.out.println(cadena);
				int index = cadena.lastIndexOf("/");
				fecha = cadena.substring(index-5,index+5);
				index = cadena.lastIndexOf(">") + 1;
				aliasServicio = cadena.substring(index,index + 4);
			}
			
			System.out.println(fecha);
			
		}

		else{
			// Es una consulta y hay que saber primero si va a ir al nodo general o no
			// Programar
			
			aliasServicio = servicio;
			
			if(tipoSubida.equals("f") || tipoSubida.equals("e") ){
				id = servicio + "noSeleccionable-rama";

				Dispatch nodo = Dispatch.call(documento, "getElementById",id).getDispatch();
				Dispatch nodoLis = Dispatch.call(nodo, "getElementsByTagName","li").getDispatch();
				System.out.println("Numero de lis...." + Dispatch.get(nodoLis,"length").toString());
				Dispatch nodoLi = Dispatch.get(nodoLis, "0").getDispatch();
				Dispatch nodoA = Dispatch.call(nodoLi, "getElementsByTagName","a").getDispatch();
				System.out.println("Numero de as...." + Dispatch.get(nodoA,"length").toString());
				
				Dispatch nodoAncla = Dispatch.get(nodoA,"0").getDispatch();
				Dispatch.call(nodoAncla,"setAttribute","id","nodoSeleccionado");
				Dispatch.call(nodoAncla, "click");
			}
			else{
				id = "360340-1-2-" + servicio;
				
				Dispatch nodo = Dispatch.call(documento, "getElementById",id).getDispatch();
				Dispatch nodoA = Dispatch.call(nodo, "getElementsByTagName","a").getDispatch();
				Dispatch nodoAncla = Dispatch.get(nodoA,"0").getDispatch();
				Dispatch.call(nodoAncla,"setAttribute","id","nodoSeleccionado");
				Dispatch.call(nodoAncla, "click");
				
			}

		}

	}
	
	
	public void putFecha(Dispatch fechaDispatch){
		
		Dispatch.put(fechaDispatch,"value",fecha);
		
	}
	
	public void seleccionarServicio(){
		
		// A partir del alias del servicio, obtenemos un rango de indices (7) donde buscarlo en el select
				
		Dispatch caja = Dispatch.call(documento, "getElementById","cajaColoreada2").getDispatch();
		
		String numOpcion = (String) InicioXedoc.nombreServicios.get(aliasServicio);
		int numeroServicios = InicioXedoc.nombreServicios.size();
		
		System.out.println("Empieza a seleccionar servicio.");
		System.out.println("Alias del servicio... " + aliasServicio);
		System.out.println("Numero de seleccion " + numOpcion);
		
		System.out.println("Numero de servicios " + numeroServicios);
		
		Dispatch selectServicio = Dispatch.call(documento, "getElementById","{hc}servicioEspecialidad-{hc}docExt").getDispatch();
		Dispatch opciones = Dispatch.call(selectServicio, "options").getDispatch();
		
		int min = 0;
		int max = 0;
		if(Integer.valueOf(numOpcion)-3 < 0 ){
			min = 0;
		}
		else{
			min = Integer.valueOf(numOpcion)-3;
		}
		
		if(Integer.valueOf(numOpcion)+4 > numeroServicios ){
			max = numeroServicios;
		}
		else{
			max = Integer.valueOf(numOpcion) + 4;
		}
		
		for(int i= min;i< max;i++){
			Dispatch opcion = Dispatch.get(opciones, String.valueOf(i)).getDispatch();
			String nombreServicio = Dispatch.get(opcion,"text").toString();
			if(nombreServicio.substring(0,4).equals(aliasServicio)){
				Dispatch.put(opcion,"selected","true");
				Dispatch.put(caja,"value",nombreServicio);
			}
		}

	}
	
	public void seleccionarDocumento(){
		
		// A partir del alias del servicio, obtenemos un rango de indices (7) donde buscarlo en el select
		
		Dispatch caja = Dispatch.call(documento, "getElementById","cajaColoreada1").getDispatch();
		
		String numOpcion = (String) InicioXedoc.nombreDocumentos.get(tipoDocumento);
		System.out.println("El tipo de documento es... " + tipoDocumento);
		System.out.println("El numero de opcion es... " + numOpcion);
		
		Dispatch selectTipoDocumento = Dispatch.call(documento, "getElementById","{hc}codDocEx-{hc}docExt").getDispatch();
		Dispatch opciones = Dispatch.call(selectTipoDocumento, "options").getDispatch();
		
		for(int i= Integer.valueOf(numOpcion) - 3;i< Integer.valueOf(numOpcion) +4;i++){
			Dispatch opcion = Dispatch.get(opciones, String.valueOf(i)).getDispatch();
			String nombreDocumento = Dispatch.get(opcion,"text").toString();
			
			if(tipoDocumento.equals(nombreDocumento)){
				System.out.println(nombreDocumento);
				Dispatch.put(opcion,"selected","true");
				Dispatch.put(caja,"value",nombreDocumento);
			}
		}
		
		if(tieneTitulo){
			caja = Dispatch.call(documento, "getElementById","{hc}titulo-{hc}docExt").getDispatch();
			Dispatch.put(caja,"value",titulo);
		}
	}
	
	public void ocultaNodos(){
		
		System.out.println("Empieza a ocultar nodos.");
		
		Dispatch consultas = Dispatch.call(documento,"getElementById","CEX-noSeleccionable-rama").getDispatch();
		Dispatch listaConsultas = Dispatch.call(documento, "getElementsByTagName","li").getDispatch();
		
		int numListas = Integer.valueOf(Dispatch.get(listaConsultas,"length").toString());
		
		for(int i=0;i<numListas;i++){
			Dispatch lista = Dispatch.get(listaConsultas,String.valueOf(i)).getDispatch();
			String nombreId = Dispatch.get(lista,"id").toString();
			if(nombreId.contains("-noSeleccionable-rama")){
				System.out.println(nombreId);
			}
		}
		
		System.out.println("Acabo de ocultar nodos.");

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
