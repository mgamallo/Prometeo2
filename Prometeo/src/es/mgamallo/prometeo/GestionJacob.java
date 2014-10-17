package es.mgamallo.prometeo;

import java.io.IOException;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class GestionJacob {
	

	public static void capturaWebs(){
		
		//	ComThread.InitSTA();
		
        ActiveXComponent oShell = new ActiveXComponent("Shell.Application"); 
        ActiveXComponent oWindows = oShell.invokeGetComponent("Windows");

        
        try {
			Runtime.getRuntime().exec("C:/Archivos de programa/Internet Explorer/iexplore.exe");
			Runtime.getRuntime().exec("C:/Archivos de programa/Internet Explorer/iexplore.exe");
			Thread.sleep(1000);
        } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        int iCount = oWindows.getProperty("Count").getInt();
        System.out.println("iCount: " + iCount);        
		
        for (int i=iCount-1,j= 1; i >iCount-3 ; i--,j++) {
            ActiveXComponent oWindow = oWindows.invokeGetComponent("Item", new Variant(i));     
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
            	Inicio.ianus1 = oWindow;
            }
            if(j==2){
            	Inicio.ianus2 = oWindow;
            }
        }
        
		Dispatch.call(Inicio.ianus1, "Navigate","http://ianuschop.sergas.local/ianus_chp_pro/inicio.jsp");

		
		try {
		
		Thread.sleep(500);	
			
		Dispatch.call(Inicio.ianus2, "Navigate","http://ianuschop.sergas.local/ianus_chp_pro/inicio.jsp");

		
		if(Inicio.contraseña){
			Dispatch.call(Inicio.ianus1, "Navigate","javascript:" + CadenasJavascript.introducirUsuario(Inicio.usuario));
			Thread.sleep(1000);
			
			Dispatch.put(Inicio.ianus1,"Visible",true);
			Dispatch.put(Inicio.ianus1,"menubar",false);
			Dispatch.put(Inicio.ianus1,"toolbar",false);
			
		    Dispatch.put(Inicio.ianus1,"height",1079);
		    Dispatch.put(Inicio.ianus1,"top",200);
		    Dispatch.put(Inicio.ianus1,"left",1024);
			
			Dispatch.call(Inicio.ianus2, "Navigate","javascript:" + CadenasJavascript.introducirUsuario(Inicio.usuario));
		
			Thread.sleep(400);
			
			Dispatch.put(Inicio.ianus2,"Visible",true);	
			Dispatch.put(Inicio.ianus2,"menubar",false);
			Dispatch.put(Inicio.ianus2,"toolbar",false);
			
		    Dispatch.put(Inicio.ianus2,"height",1079);
		    Dispatch.put(Inicio.ianus2,"top",200);
		    Dispatch.put(Inicio.ianus2,"left",1024);
		}
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setVisible(final ActiveXComponent ianus){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				Dispatch.put(ianus, "Visible", !Dispatch.get(ianus,"visible").getBoolean());
			}
		});
	}
	

	public static void buscaNodo(final ActiveXComponent ianus, final String servicio){
	//	SwingUtilities.invokeLater(new Runnable() {
	//		public void run() {
				
				//	Busca nodo
				System.out.println("El servicio es: " + servicio);
		
				Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.buscarNodo01(servicio));
				

		//	}
	//	});
	}
	
	
	public static void buscaNodoYpulsaBotonAsociar(final ActiveXComponent ianus, final String servicio){
	//	SwingUtilities.invokeLater(new Runnable() {
	//		public void run() {
				
				//	Busca nodo
				Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.buscarNodo01(servicio));
				
				//	Pulsa boton asociar
				try {
					Thread.sleep(1000);
					
					ActiveXComponent ianusActivo = new ActiveXComponent("InternetExplorer.Application");
					
					if(Inicio.ianus1onTop){
						ianusActivo = Inicio.ianus1;
						Dispatch.call(ianus, "Navigate",CadenasJavascript.buscarBotonAsociar());
					}
					else{
						ianusActivo = Inicio.ianus2;
						Dispatch.call(ianus, "Navigate",CadenasJavascript.buscarBotonAsociar());
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		//	}
	//	});
	}
	
	
	public static void introduceNHC(final ActiveXComponent ianus, final String nhc){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				Dispatch.call(ianus, "Navigate","javascript:" + CadenasJavascript.introducirNHC(nhc));
				
				/*
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.buscarNodo01());
				*/
			}
		});
	}
	
	
	public static void pulsaBotonAsociar(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				
				ActiveXComponent ianusActivo = new ActiveXComponent("InternetExplorer.Application");
				
				if(Inicio.ianus1onTop){
					ianusActivo = Inicio.ianus1;
					Dispatch.call(ianusActivo, "Navigate",CadenasJavascript.buscarBotonAsociar());
				}
				else{
					ianusActivo = Inicio.ianus2;
					Dispatch.call(ianusActivo, "Navigate",CadenasJavascript.buscarBotonAsociar());
				}
			}
		});
	}
	
}
