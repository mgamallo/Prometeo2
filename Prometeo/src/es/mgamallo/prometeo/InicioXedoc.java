package es.mgamallo.prometeo;

import java.util.Iterator;
import java.util.TreeMap;

import com.jacob.activeX.ActiveXComponent;

public class InicioXedoc {
	
    static ActiveXComponent oShell;  
    static ActiveXComponent oWindows; 
    
    public static TreeMap<String, String> nombreServicios = new TreeMap<String, String>();
    
    public InicioXedoc(){
    	
    	nombreServicios = Inicio.leerExcel.nombreServicios;
    	
        for( Iterator it = nombreServicios.keySet().iterator(); it.hasNext();) {
        	String clave = (String)it.next();
        	String valor = (String)nombreServicios.get(clave);
        	System.out.println(clave + " : " + valor);
        }
    	
    	
    	 // GestionJacobXedoc.capturaWebXedoc();
    	
    	
    	// GestionJacobXedoc.pruebaCapturaWebXedoc();
    	
    	
    	  GestionJacobXedoc.CapturaWebXedoc_nuevoproyecto();
    }
	
	
}
