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
			Thread.sleep(1000);
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
            	Inicio.paciente1.ianus = oWindow;
            }
            if(j==2){
            	Inicio.paciente2.ianus = oWindow;
            }
        }
        

		Dispatch.call(Inicio.paciente1.ianus, "Navigate","http://ianuschop.sergas.local/ianus_chp_pro/inicio.jsp");

		
		try {
		
		Thread.sleep(900);	
			
		Dispatch.call(Inicio.paciente2.ianus, "Navigate","http://ianuschop.sergas.local/ianus_chp_pro/inicio.jsp");

		
		if(Inicio.contraseña){
			
			// introduceUsuarioJacob(Inicio.ianus1, Inicio.usuario);
			
			Dispatch.call(Inicio.paciente1.ianus,"Navigate","javascript:" + CadenasJavascript.introducirUsuario(Inicio.usuario));
			
			Thread.sleep(1500);
			
			Dispatch.put(Inicio.paciente1.ianus,"Visible",true);
			Dispatch.put(Inicio.paciente1.ianus,"menubar",false);
			Dispatch.put(Inicio.paciente1.ianus,"toolbar",false);
			
		    Dispatch.put(Inicio.paciente1.ianus,"height",1099);
		    Dispatch.put(Inicio.paciente1.ianus,"top",180);
		    Dispatch.put(Inicio.paciente1.ianus,"left",1024);
			
//		    introduceUsuarioJacob(Inicio.ianus2, Inicio.usuario);
			Dispatch.call(Inicio.paciente2.ianus,"Navigate","javascript:" + CadenasJavascript.introducirUsuario(Inicio.usuario));

		
			Thread.sleep(900);
			
			Dispatch.put(Inicio.paciente2.ianus,"Visible",true);	
			Dispatch.put(Inicio.paciente2.ianus,"menubar",false);
			Dispatch.put(Inicio.paciente2.ianus,"toolbar",false);
			
		    Dispatch.put(Inicio.paciente2.ianus,"height",1099);
		    Dispatch.put(Inicio.paciente2.ianus,"top",180);
		    Dispatch.put(Inicio.paciente2.ianus,"left",1024);
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
	
	public static void getDatosPaciente(final ActiveXComponent ianus){
		
	//	Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.seleccionaFichaPaciente());
		try {
			Portapapeles cbTemporal = new Portapapeles();
			Thread.sleep(50);
			
			Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.inicializacionPacienteIntegral01());
			
			Thread.sleep(300);
			String cadena = cbTemporal.getDatosPaciente();
		
			if(ianus.equals(Inicio.paciente1.ianus)){
				Inicio.paciente1.ficha.setDatosFicha( cadena);
				if(Inicio.ianus1onTop){
					Inicio.vControlIanus.labelNombreExtraido.setText(Inicio.paciente1.ficha.nombreCompleto);
					Inicio.vControlIanus.labelCIPextraido.setText(Inicio.paciente1.ficha.cip);
				}
			}
			else{
				Inicio.paciente2.ficha.setDatosFicha( cadena);
				if(!Inicio.ianus1onTop){
					Inicio.vControlIanus.labelNombreExtraido.setText(Inicio.paciente2.ficha.nombreCompleto);
					Inicio.vControlIanus.labelCIPextraido.setText(Inicio.paciente2.ficha.cip);
				}
			}

			
			/*
			if(Inicio.ianus1onTop){
				Inicio.paciente1.ficha.setDatosFicha( cadena);
			}
			else{
				Inicio.paciente2.ficha.setDatosFicha( cadena);
			}
			*/
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void buscaNodo(final ActiveXComponent ianus, final String servicio){
	//	SwingUtilities.invokeLater(new Runnable() {
	//		public void run() {
				
				//	Busca nodo
				System.out.println("El servicio en GEstionJacob.buscaNodo es: " + servicio);
				if(servicio.equals(InicioIanus.HOSP_JACOB)){
					

					try {

						Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.inicializacionPacienteNodoHosp(servicio));
						Thread.sleep(2000);
						Dispatch.call(ianus, "Navigate","javascript:" + CadenasJavascript.inicializacionPacienteSetFichaHosp());
						Thread.sleep(300);	
						Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.obtieneDatosFichaHosp());

						Thread.sleep(400);
						Portapapeles cbTemporal = new Portapapeles();
						String cadena = cbTemporal.getDatosPaciente();
						
						if(Inicio.ianus1onTop){
							Inicio.paciente1.ficha.setDatosFicha( cadena);
						}
						else{
							Inicio.paciente2.ficha.setDatosFicha( cadena);
						}
				
						Thread.sleep(100);
						
						if(ianus.equals(Inicio.paciente1.ianus)){
							if(Inicio.ianus1onTop){
								Inicio.vControlIanus.botonFecha.setText(Inicio.paciente1.ficha.fechaIngreso);
								Inicio.vControlIanus.botonHora.setText(Inicio.paciente1.ficha.horaIngreso);
								System.out.println(Inicio.paciente1.ficha.fechaIngreso);
							}
						}
						else{
							if(!Inicio.ianus1onTop){
								Inicio.vControlIanus.botonFecha.setText(Inicio.paciente2.ficha.fechaIngreso);
								Inicio.vControlIanus.botonHora.setText(Inicio.paciente2.ficha.horaIngreso);
							}
						}
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(servicio.equals(InicioIanus.URG_JACOB)){
					try {

						
						Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.buscarNodoHosp(servicio));
						
						Thread.sleep(1000);
						
						Portapapeles cbTemporal = new Portapapeles();
									
						System.out.println("Obtenemos datos de la ficha");
						Dispatch.call(ianus, "navigate","javascript:" + CadenasJavascript.obtieneDatosFichaURG());
						
						Thread.sleep(400);
						String cadena = cbTemporal.getDatosPaciente();
	
						
						if(Inicio.ianus1onTop){
							Inicio.paciente1.ficha.setDatosFicha( cadena);
						}
						else{
							Inicio.paciente2.ficha.setDatosFicha( cadena);
						}
						
						Thread.sleep(100);
						
						if(ianus.equals(Inicio.paciente1.ianus)){
							if(Inicio.ianus1onTop){
								Inicio.vControlIanus.botonFecha.setText(Inicio.paciente1.ficha.fechaIngreso);
								Inicio.vControlIanus.botonHora.setText(Inicio.paciente1.ficha.horaIngreso);
								System.out.println(Inicio.paciente1.ficha.fechaIngreso);
							}
						}
						else{
							if(!Inicio.ianus1onTop){
								Inicio.vControlIanus.botonFecha.setText(Inicio.paciente2.ficha.fechaIngreso);
								Inicio.vControlIanus.botonHora.setText(Inicio.paciente2.ficha.horaIngreso);
							}
						}
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(servicio.equals(InicioIanus.CIA_JACOB)){
						try {
							
							Thread.sleep(10);
							
							Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.buscarNodoHosp(servicio));
							
		
							
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
						ianusActivo = Inicio.paciente1.ianus;
						Dispatch.call(ianus, "Navigate",CadenasJavascript.buscarBotonAsociar());
					}
					else{
						ianusActivo = Inicio.paciente2.ianus;
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
					ianusActivo = Inicio.paciente1.ianus;
					Dispatch.call(ianusActivo, "Navigate",CadenasJavascript.buscarBotonAsociar());
				}
				else{
					ianusActivo = Inicio.paciente2.ianus;
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
