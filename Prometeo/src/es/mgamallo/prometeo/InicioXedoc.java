package es.mgamallo.prometeo;

import com.jacob.activeX.ActiveXComponent;

public class InicioXedoc {
	
    static ActiveXComponent oShell;  
    static ActiveXComponent oWindows; 
    
    public InicioXedoc(){
    	GestionJacobXedoc.capturaWebXedoc();
    }
	
	
}
