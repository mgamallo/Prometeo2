package es.mgamallo.prometeo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import com.jacob.activeX.ActiveXComponent;

public class InicioXedoc {
		
    static ActiveXComponent oShell;  
    static ActiveXComponent oWindows; 
    
    public static TreeMap<String, String> nombreServicios = new TreeMap<String, String>();
    public static TreeMap<String, String> nombreDocumentos = new TreeMap<String, String>();
    
    static boolean antiguo = true;   //  Variable para borrar
    
    /* variables para las excepciones de los documentos */
    
    String[] listaServicios;
    String[] listaNombresDocumentos;
    String[][] tablaDocumentos;
    

	
    public InicioXedoc(){
    	
    	setDefaultsModels();
    	setExcepciones();
    	
    	
    	nombreServicios = Inicio.leerExcel.nombreServicios;
    	nombreDocumentos = Inicio.leerExcel.nombreDocumentos;
    	    	
    	GestionJacobXedoc.inicializa2Xedocs();
    	GestionJacobXedoc.xedoc1visible = true;
    	Inicio.ventanaControlXedoc.jBxedoc1.setBackground(Color.green);
    	Inicio.ventanaControlXedoc.jBxedoc2.setBackground(Color.green);
    	Inicio.xedoc1activo = true;
    	Inicio.xedoc2activo = true;
    	
    	antiguo = false;
    }
	
	
	private void setDefaultsModels() {

		// listaNodos = Inicio.leerExcel.getNodos();
				
		listaServicios = Inicio.leerExcel.getServicios();
		listaNombresDocumentos = Inicio.leerExcel.getNombres();
		tablaDocumentos = Inicio.leerExcel.getTabla();

	//	System.out.println("Tamaño listaServicios " + listaServicios.length);
	//	System.out.println("Tamaño listaNombresDocumentos " + listaNombresDocumentos.length);
	}
    
    
    private void setExcepciones(){
		
		boolean servicioConExcepcion = false;
		ExcepcionesServicio excpServicio = new ExcepcionesServicio();
		
		for(int i=0;i<listaServicios.length;i++){
			servicioConExcepcion = false;
			for(int j=0;j<listaNombresDocumentos.length;j++){
				if(		tablaDocumentos[j][i].toLowerCase().equals("q") ||
						tablaDocumentos[j][i].toLowerCase().equals("f") ||
						tablaDocumentos[j][i].toLowerCase().equals("e") ||
						tablaDocumentos[j][i].toLowerCase().equals("i")     ){
					if(!servicioConExcepcion){
						servicioConExcepcion = true;
						excpServicio = new ExcepcionesServicio();
						excpServicio.servicio = listaServicios[i];
						excpServicio.excepciones = new ArrayList<Excepcion>();
					}
					Excepcion excepcion = new Excepcion(listaNombresDocumentos[j], tablaDocumentos[j][i]);
					excpServicio.excepciones.add(excepcion);
				}
			}
			if(servicioConExcepcion){
				Inicio.tablaExcepcionesXedoc.add(excpServicio);
			}
		}
		
		
		for(int i=0;i<Inicio.tablaExcepcionesXedoc.size();i++){
			
			System.out.println("Excepciones del servicio " + Inicio.tablaExcepcionesXedoc.get(i).servicio);
			for(int j=0;j<Inicio.tablaExcepcionesXedoc.get(i).excepciones.size();j++){
				System.out.println(Inicio.tablaExcepcionesXedoc.get(i).excepciones.get(j).nombreDocumento 
						+ ", " + Inicio.tablaExcepcionesXedoc.get(i).excepciones.get(j).tipoExcepcion	);
			}
			System.out.println();
		}
		
	}
}
