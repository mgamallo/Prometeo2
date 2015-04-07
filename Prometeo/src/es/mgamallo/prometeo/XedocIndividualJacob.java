package es.mgamallo.prometeo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class XedocIndividualJacob {

	private final String CONTINXENCIA = "Documento sen tipo (continxencia)";
	private final String COLORFONDOCAJAS = "RGB(253,247,133)";
	
	public String nhc = "";
	public String servicio = "";
	
	private String aliasServicio = "";
	private String nombreServicio = "";
	
	public String nombreDocumento = "";
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
			
			nombreDocumento = campos[3];
			
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
			System.out.println(nombreId);
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
		if(fecha.length() != 0){
			Dispatch.put(fechaDispatch,"value",fecha);
			Dispatch.call(fechaDispatch,"focus");
		}
		else
			Dispatch.call(fechaDispatch,"focus");
		
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
		
		
		System.out.println("numOpcion vale... " + numOpcion);
		
		if(numOpcion != null){
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


	}
	
	public void seleccionarDocumento(){
		
		// A partir del alias del servicio, obtenemos un rango de indices (7) donde buscarlo en el select
		
		Dispatch caja = Dispatch.call(documento, "getElementById","cajaColoreada1").getDispatch();
		
		String numOpcion = (String) InicioXedoc.nombreDocumentos.get(tipoDocumento);
		int numeroDocumentos = InicioXedoc.nombreDocumentos.size();
		System.out.println("El tipo de documento es... " + tipoDocumento);
		System.out.println("El numero de opcion es... " + numOpcion);
		
		Dispatch selectTipoDocumento = Dispatch.call(documento, "getElementById","{hc}codDocEx-{hc}docExt").getDispatch();
		Dispatch opciones = Dispatch.call(selectTipoDocumento, "options").getDispatch();
		
		int min = 0;
		int max = 0;
		if(Integer.valueOf(numOpcion)-3 < 0 ){
			min = 0;
		}
		else{
			min = Integer.valueOf(numOpcion)-3;
		}
		
		if(Integer.valueOf(numOpcion)+4 > numeroDocumentos ){
			max = numeroDocumentos;
		}
		else{
			max = Integer.valueOf(numOpcion) + 4;
		}
		
		
		for(int i= min;i< max;i++){
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
			Dispatch estiloCaja = Dispatch.call(caja, "style").getDispatch();
			Dispatch.put(caja,"value",titulo);
			Dispatch.put(estiloCaja,"backgroundColor",COLORFONDOCAJAS);
			Dispatch.put(estiloCaja,"font","bold 18px arial, sans-serif");
		}
	}
	
	
	public void ocultaNodos(){
		
		//  Cierra ramas no necesarias
		
		boolean ocultaNodosCEX = false;
		
		if(servicio.equals("HOSP") || servicio.equals("CIA")){
			Dispatch cex = Dispatch.call(documento, "getElementById","CEX-noSeleccionable-rama").getDispatch();
			Dispatch urg = Dispatch.call(documento, "getElementById","URG-noSeleccionable-rama").getDispatch();
			Dispatch.call(cex,"setattribute","class","jstree-unchecked jstree-closed");
			Dispatch.call(urg,"setattribute","class","jstree-unchecked jstree-closed");
		}
		else if(servicio.equals("URG")){
			Dispatch cex = Dispatch.call(documento, "getElementById","CEX-noSeleccionable-rama").getDispatch();
			Dispatch.call(cex,"setattribute","class","jstree-unchecked jstree-closed");
		}
		else{
			Dispatch hosp = Dispatch.call(documento, "getElementById","HOS-noSeleccionable-rama").getDispatch();
			Dispatch urg = Dispatch.call(documento, "getElementById","URG-noSeleccionable-rama").getDispatch();
			Dispatch qui = Dispatch.call(documento, "getElementById","QUI-noSeleccionable-rama").getDispatch();

			Dispatch.call(hosp,"setAttribute","class","jstree-unchecked jstree-closed");
			Dispatch.call(urg,"setAttribute","class","jstree-unchecked jstree-closed");
			Dispatch.call(qui,"setAttribute","class","jstree-unchecked jstree-closed");
			
			ocultaNodosCEX = true;
		}

		/*
		// ocultando nodo urgencias
		System.out.println("Ocultando nodo urgencias");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Dispatch urg = Dispatch.call(documento, "getElementById","URG-noSeleccionable-rama").getDispatch();
		Dispatch.call(urg,"setAttribute","class","jstree-unchecked jstree-closed");
		
		Dispatch.call(Inicio.documento1.xedoc,"navigate","javascript:var hola = document.getElementById('URG-noSeleccionable-rama');"
				+ "hola.setAttribute('class','jstree-unchecked jstree-closed');alert('hola');");
		*/
		
		//  Oculta nodos no necesarios de consultas
		if(ocultaNodosCEX){
			System.out.println("Empieza a ocultar nodos.");
			
			Dispatch consultas = Dispatch.call(documento,"getElementById","CEX-noSeleccionable-rama").getDispatch();
			Dispatch listaConsultas = Dispatch.call(consultas, "getElementsByTagName","li").getDispatch();
			
			int numListas = Integer.valueOf(Dispatch.get(listaConsultas,"length").toString());
			
			ArrayList<String> serviciosPaciente = new ArrayList<String>();
			
			for(int i=0;i<numListas;i++){
				Dispatch lista = Dispatch.get(listaConsultas,String.valueOf(i)).getDispatch();
				String nombreId = Dispatch.get(lista,"id").toString();
				if(nombreId.contains("-noSeleccionable-rama")){
					int index = nombreId.indexOf("-");
					String serv = nombreId.substring(0,index);
					if(!serv.equals("CEX") && !serv.equals("OTROS")){
						serviciosPaciente.add(serv);
					}
					if(serv.equals("OTROS")){
						break;
					}
				}
			}
			
			for(int i=0;i<serviciosPaciente.size();i++){
				System.out.println(serviciosPaciente.get(i));
			}
			
			consultas = Dispatch.call(documento,"getElementById","OTROS-noSeleccionable-rama").getDispatch();
			listaConsultas = Dispatch.call(consultas, "getElementsByTagName","li").getDispatch();
			
			numListas = Integer.valueOf(Dispatch.get(listaConsultas,"length").toString());
/*			
			for(int i=0;i<numListas;i++){
				Dispatch lista = Dispatch.get(listaConsultas,String.valueOf(i)).getDispatch();
				String nombreId = Dispatch.get(lista,"id").toString();
				Dispatch estiloLista = Dispatch.get(lista,"style").getDispatch();
				Dispatch.put(estiloLista,"display","none");
			}
			
			for(int i=0;i<serviciosPaciente.size();i++){
				String id = "360340-1-2-" + serviciosPaciente.get(i);
				Dispatch lista = Dispatch.get(documento,"getElementById").getDispatch();
				Dispatch estiloLista = Dispatch.get(lista,"style").getDispatch();
				Dispatch.put(estiloLista,"display","inline");
			}
			String id = "360340-1-2-" + aliasServicio;
			Dispatch lista = Dispatch.get(documento,"getElementById").getDispatch();
			Dispatch estiloLista = Dispatch.get(lista,"style").getDispatch();
			Dispatch.put(estiloLista,"display","inline");
*/			
			for(int i=0,j=0;i<numListas;i++){
				Dispatch lista = Dispatch.get(listaConsultas,String.valueOf(i)).getDispatch();
				String nombreId = Dispatch.get(lista,"id").toString();
				
				System.out.println("Lista numero " + i + ". " + nombreId);
				
				if(j< serviciosPaciente.size()){
					if(!nombreId.contains(serviciosPaciente.get(j))){
					//	if(nombreId.contains("360340-1-2-")){
						
						if(!nombreId.contains(aliasServicio)){
							Dispatch estiloLista = Dispatch.get(lista,"style").getDispatch();
							Dispatch.put(estiloLista,"display","none");
							System.out.println(nombreId + " ocultado.");
						}
					//	}
					}
					else{
						j++;
					}
				}
				else if(!nombreId.contains(aliasServicio)){
					Dispatch estiloLista = Dispatch.get(lista,"style").getDispatch();
					Dispatch.put(estiloLista,"display","none");
					System.out.println(nombreId + " ocultado.");
				}
			}
		
			System.out.println("Acabo de ocultar nodos.");

		}

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

	public void getFocus(){
		Dispatch enviar = Dispatch.call(documento, "getElementById","submitFormFirmar").getDispatch();
		Dispatch.get(enviar,"focus");
	}
}
