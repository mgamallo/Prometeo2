package es.mgamallo.prometeo;

import java.io.IOException;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class GestionJacobXedoc {
	
	public static void capturaWebXedoc(){
	    InicioXedoc.oShell = new ActiveXComponent("Shell.Application"); 
	    InicioXedoc.oWindows = InicioXedoc.oShell.invokeGetComponent("Windows");
	    
        try {
			Runtime.getRuntime().exec("C:/Archivos de programa/Internet Explorer/iexplore.exe");
			Thread.sleep(1000);
			/*
			Runtime.getRuntime().exec("C:/Archivos de programa/Internet Explorer/iexplore.exe");
			Thread.sleep(1000);
			*/
        } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        int iCount = InicioXedoc.oWindows.getProperty("Count").getInt();
        System.out.println("iCount: " + iCount);        
		
        for (int i=iCount-1,j= 1; i >iCount-3 ; i--,j++) {
            ActiveXComponent oWindow = InicioXedoc.oWindows.invokeGetComponent("Item", new Variant(i));     
            String sLocName = oWindow.getProperty("LocationName").getString();
            String sFullName = oWindow.getProperty("FullName").getString();
            boolean isIE = sFullName.toLowerCase().endsWith("iexplore.exe");
            boolean bVisible = oWindow.getProperty("Visible").getBoolean();
            System.out.println("i: " + i + ", loc: " + sLocName + ", name: " + sFullName + ", isIE: " + isIE + ", vis: " + bVisible);
            /*
            if ((isIE)&&(sLocName.startsWith("about:blank"))) {
                oIE = oWindow;
            }
            */
            if(j==1){
            	Inicio.paciente1.xedoc = oWindow;
            }
            /*
            if(j==2){
            	Inicio.paciente2.xedoc = oWindow;
            }
            */
        }
        
		Dispatch.call(Inicio.paciente1.xedoc, "Navigate","http://xedocidx.sergas.local/xedoc_idx/login");
		
		Variant estado = Dispatch.call(Inicio.paciente1.xedoc,"readyState");
		
		int i=0;
		while(true && i < 15000){
			estado = Dispatch.call(Inicio.paciente1.xedoc, "readyState");
			System.out.println(i++);
			System.out.println(estado.toString());
			if(Integer.valueOf(estado.toString()) == 4){
				System.out.println("El estado está en 4");
				break;
			}
		}
		
		/*
		try {
		
		Thread.sleep(900);	
			
		 Dispatch.call(Inicio.paciente2.xedoc, "Navigate","http://xedocidx.sergas.local/xedoc_idx/login");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		if(Inicio.contraseña){
			Dispatch.call(Inicio.paciente1.xedoc, "Navigate","javascript:" + CadenasJavascriptXedoc.introUsuario(Inicio.usuario));
		
			Dispatch.put(Inicio.paciente1.xedoc,"menubar",false);
			Dispatch.put(Inicio.paciente1.xedoc,"toolbar",false);
			
		    Dispatch.put(Inicio.paciente1.xedoc,"height",1049);
		    Dispatch.put(Inicio.paciente1.xedoc,"width",1070);
		    Dispatch.put(Inicio.paciente1.xedoc,"top",0);  
		    Dispatch.put(Inicio.paciente1.xedoc,"left",850);
		}
	}
}
