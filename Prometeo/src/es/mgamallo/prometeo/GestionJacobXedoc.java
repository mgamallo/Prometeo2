package es.mgamallo.prometeo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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
		
		System.out.println("Primer readyState");
		
		int i=0;
		while(true /* && i < 25000 */){
			estado = Dispatch.call(Inicio.paciente1.xedoc, "readyState");
		//	System.out.println(i++);
		//	System.out.println(estado.toString());
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
		    
			System.out.println("Segundo readyState");
			
			i=0;
			while(true /* && i < 25000 */){
				estado = Dispatch.call(Inicio.paciente1.xedoc, "readyState");
			//	System.out.println(i++);
			//	System.out.println(estado.toString());
				if(Integer.valueOf(estado.toString()) == 4){
					System.out.println("El estado está en 4");
					break;
				}
			}
		    
			Dispatch.call(Inicio.paciente1.xedoc, "Navigate","javascript:" + CadenasJavascriptXedoc.selectMiBandeja());

			System.out.println("Tercer readyState");
			
			i=0;
			while(true /* && i < 25000 */){
				estado = Dispatch.call(Inicio.paciente1.xedoc, "readyState");
			//	System.out.println(i++);
			//	System.out.println(estado.toString());
				if(Integer.valueOf(estado.toString()) == 4){
					System.out.println("El estado está en 4");
					break;
				}
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Dispatch.call(Inicio.paciente1.xedoc, "Navigate","javascript:" + CadenasJavascriptXedoc.inicio1Xedoc());

			
			System.out.println("Cuarto readyState");
			
			i=0;
			while(true /* && i < 25000 */){
				estado = Dispatch.call(Inicio.paciente1.xedoc, "readyState");
			//	System.out.println(i++);
			//	System.out.println(estado.toString());
				if(Integer.valueOf(estado.toString()) == 4){
					System.out.println("El estado está en 4");
					break;
				}
			}
			
			/*
			try {
				Thread.sleep(5000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			
			Dispatch documento = Dispatch.call(Inicio.paciente1.xedoc, "Document").getDispatch();
			Dispatch branding = Dispatch.call(documento, "getElementById","branding").getDispatch();
			Dispatch brandingStilo = Dispatch.get(branding, "style").getDispatch();
			Dispatch.put(brandingStilo, "display","none");
			
			Dispatch entornoLogin = Dispatch.call(documento, "getElementById","entornoLogin").getDispatch();
			Dispatch entornoLoginStilo = Dispatch.get(entornoLogin, "style").getDispatch();
			Dispatch.put(entornoLoginStilo, "display","none");
			
			Dispatch header = Dispatch.call(documento, "getElementById","header").getDispatch();
			Dispatch headerStilo = Dispatch.get(header, "style").getDispatch();
			Dispatch.put(headerStilo, "height","0px");
			
			Dispatch columnaIzquierdaEdicion = Dispatch.call(documento, "getElementById","columnaIzquierdaEdicion").getDispatch();
			Dispatch columnaIzquierdaEdicionStilo = Dispatch.get(columnaIzquierdaEdicion, "style").getDispatch();
			Dispatch.put(columnaIzquierdaEdicionStilo, "width","750px");
			Dispatch.put(columnaIzquierdaEdicionStilo, "height","1100px");
		//	Dispatch.put(columnaIzquierdaEdicionStilo, "float","left");
			
			
			Dispatch completePreview = Dispatch.call(documento, "getElementById","completePreview").getDispatch();
			Dispatch completePreviewStilo = Dispatch.get(completePreview, "style").getDispatch();
			Dispatch.put(completePreviewStilo, "width","750px");
			Dispatch.put(completePreviewStilo, "height","1100px");
			
			Dispatch previewer = Dispatch.call(documento, "getElementById","previewer").getDispatch();
			Dispatch previewerStilo = Dispatch.get(previewer, "style").getDispatch();
			Dispatch.put(previewerStilo, "width","750px");
			Dispatch.put(previewerStilo, "height","1100px");
			
			Dispatch columnaDerechaEdicion = Dispatch.call(documento, "getElementById","columnaDerechaEdicion").getDispatch();
			Dispatch columnaDerechaEdicionStilo = Dispatch.get(columnaDerechaEdicion, "style").getDispatch();
			Dispatch.put(columnaDerechaEdicionStilo, "margin","0px");
	//		Dispatch.put(columnaDerechaEdicionStilo, "float","left");
			
			Dispatch edicionForm = Dispatch.call(documento, "getElementById","edicionForm").getDispatch();
			Dispatch edicionFormStilo = Dispatch.get(edicionForm, "style").getDispatch();
			Dispatch.put(edicionFormStilo, "width","1025px");
		//	Dispatch.put(edicionFormStilo, "float","left");
			
			Dispatch tablaElementosAjax = Dispatch.call(documento, "getElementById","tablaElementosAjax").getDispatch();
			Dispatch tablaElementosAjaxStilo = Dispatch.get(tablaElementosAjax, "style").getDispatch();
			Dispatch.put(tablaElementosAjaxStilo, "width","450px");
			Dispatch.put(tablaElementosAjaxStilo, "height","1000px");
		//	Dispatch.put(tablaElementosAjaxStilo, "float","left");
		
			
			
			//	Dispatch.call(Inicio.paciente1.xedoc, "Navigate","javascript:" + CadenasJavascriptXedoc.maquetado());

			 
		}
	}
}
