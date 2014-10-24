package es.mgamallo.prometeo;

import java.io.IOException;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
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
		
		Thread.sleep(900);	
			
		Dispatch.call(Inicio.ianus2, "Navigate","http://ianuschop.sergas.local/ianus_chp_pro/inicio.jsp");

		
		if(Inicio.contraseña){
			
			// introduceUsuarioJacob(Inicio.ianus1, Inicio.usuario);
			
			Dispatch.call(Inicio.ianus1,"Navigate","javascript:" + CadenasJavascript.introducirUsuario(Inicio.usuario));
			
			Thread.sleep(1500);
			
			Dispatch.put(Inicio.ianus1,"Visible",true);
			Dispatch.put(Inicio.ianus1,"menubar",false);
			Dispatch.put(Inicio.ianus1,"toolbar",false);
			
		    Dispatch.put(Inicio.ianus1,"height",1079);
		    Dispatch.put(Inicio.ianus1,"top",200);
		    Dispatch.put(Inicio.ianus1,"left",1024);
			
//		    introduceUsuarioJacob(Inicio.ianus2, Inicio.usuario);
			Dispatch.call(Inicio.ianus2,"Navigate","javascript:" + CadenasJavascript.introducirUsuario(Inicio.usuario));

		
			Thread.sleep(900);
			
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
				System.out.println("El servicio en GEstionJacob.buscaNodo es: " + servicio);
				if(servicio.equals(InicioIanus.HOSP_JACOB)){
					

					try {
						
						Thread.sleep(1500);
						
						Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.buscarNodoHosp(servicio));
						
						Thread.sleep(2500);
						System.out.println("Abrimos ficha paciente");
						Dispatch.call(ianus,"Navigate","javascript:principal.datos.ficha.hosp_main.cambiar('pFicha');");
				
						Thread.sleep(1000);
						
						Portapapeles cbTemporal = new Portapapeles();
					//	cbTemporal.getDatosPortapapelesTemporal();
					//	System.out.println("Los datos temporales del portapapeles son: " + cbTemporal.valorTemporal);
						
						System.out.println("Obtenemos datos de la ficha");
						Dispatch.call(ianus, "navigate","javascript:" + CadenasJavascript.obtieneDatosFichaHosp(1));
						
						Thread.sleep(400);
						String cadena = cbTemporal.getDatosPaciente();
	
						
						if(Inicio.ianus1onTop){
							Inicio.datosPaciente1.setDatosFicha( cadena);
						}
						else{
							Inicio.datosPaciente2.setDatosFicha( cadena);
						}
						
					//	cbTemporal.setDatosOriginales();
						
						/*		
						Thread.sleep(1500);
						Dispatch documento = Dispatch.call(ianus,"document").toDispatch();
						
						Dispatch frames = Dispatch.call(documento, "getElementsByTagName","frame").toDispatch();
						
						int numFrames = Dispatch.get(frames,"length").getInt();
						
						System.out.println(numFrames);
						
						for(int i= 0;i<numFrames;i++){
							String numero = String.valueOf(i);
							Dispatch frame = Dispatch.get(frames,numero).toDispatch();
							System.out.println("El frame " + i+ ", se llama " + Dispatch.get(frame,"name"));
						}
						
					//	Dispatch all = Dispatch.get(tablas,"0").toDispatch();
					//	System.out.println(Dispatch.get(all,"innerHTML"));
					
  				        
					      Dispatch framePrincipal = Dispatch.get(frames,"1").toDispatch();
					      System.out.println("El framePrincipal, se llama " + Dispatch.get(framePrincipal,"name"));
					      Dispatch documentPrincipal = Dispatch.call(framePrincipal,"document").toDispatch();
					      Dispatch framesPrincipal = Dispatch.call(documentPrincipal,"getElementsByTagName","frame").getDispatch();
					      
					      numFrames = Dispatch.get(framesPrincipal,"length").getInt();
					      System.out.println("hola " + numFrames);
							for(int i= 0;i<numFrames;i++){
								String numero = String.valueOf(i);
								Dispatch frame = Dispatch.get(framesPrincipal,numero).toDispatch();
								System.out.println("El frame " + i+ ", dentro de principal se llama " + Dispatch.get(frame,"name"));
							}
					      
					     
					      
					      Dispatch frameFicha = Dispatch.get(frameDatos,"ficha").getDispatch();
					      Dispatch frameHosp_main = Dispatch.get(frameFicha,"hosp_main").getDispatch();
					      Dispatch frameEpisodio = Dispatch.get(frameHosp_main,"episodio").toDispatch();					     
					      Dispatch document = Dispatch.get(frameEpisodio,"document").toDispatch();
					      Dispatch tablas = Dispatch.call(document,"getElementsByTagName","table").toDispatch();
					      Dispatch tabla6 = Dispatch.get(tablas,"6").toDispatch();
					      System.out.println(Dispatch.get(tabla6,"innerHTML"));
					      
					      
				*/
						
					//	javascript:var tabla = principal.datos.ficha.hosp_main.episodio.document.getElementsByTagName('TABLE');

						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(!servicio.equals(InicioIanus.DESCONOCIDO)){
					Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.buscarNodoConsultas(servicio));
				}


		//	}
	//	});
	}
	
	
	public static void buscaNodoYpulsaBotonAsociar(final ActiveXComponent ianus, final String servicio){
	//	SwingUtilities.invokeLater(new Runnable() {
	//		public void run() {
				
				//	Busca nodo
				Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.buscarNodoConsultas(servicio));
				
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
	
	
	public static void introduceUsuarioJacob(final ActiveXComponent ianus, final Usuario user){
		
		user.usuario = "mgamgul1";
		user.password = "archivo0";

		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			//	ComThread.InitSTA();
				
			      Dispatch documento = Dispatch.call(ianus,"document").getDispatch();
	      
			      Dispatch frames = Dispatch.get(documento, "frames").toDispatch();
			      Dispatch framePrincipal = Dispatch.get(frames,"principal").toDispatch();
			      Dispatch frameMain = Dispatch.get(framePrincipal,"main").toDispatch();
			      Dispatch frameLogin = Dispatch.get(frameMain,"document").toDispatch();
			      Dispatch login = Dispatch.call(frameLogin,"getElementById", "login").toDispatch();
			      Dispatch.put(login,"value",user.usuario);
			      Dispatch password = Dispatch.call(frameLogin,"getElementById", "password").toDispatch();
			      Dispatch.put(password,"value",user.password);
			      Dispatch.call(frameMain, "eval","aceptar()");
			}
		});		
	}
}
