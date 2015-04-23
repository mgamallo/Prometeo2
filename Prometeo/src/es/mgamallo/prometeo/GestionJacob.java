package es.mgamallo.prometeo;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class GestionJacob {
	
	public static String direccionIanus = "http://ianuschop.sergas.local/ianus_chp_pro/inicio.jsp";

	public static void capturaWebs(){
		
		//	ComThread.InitSTA();
		
		// String direccionIanus = "http://assccihspre/ianus_hosp2_pre/inicio.do";
		

		
	    InicioIanus.oShell = new ActiveXComponent("Shell.Application"); 
	    InicioIanus.oWindows = InicioIanus.oShell.invokeGetComponent("Windows");

        
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
        
        int iCount = InicioIanus.oWindows.getProperty("Count").getInt();
        System.out.println("iCount: " + iCount);        
		
        for (int i=iCount-1,j= 1; i >iCount-3 ; i--,j++) {
            ActiveXComponent oWindow = InicioIanus.oWindows.invokeGetComponent("Item", new Variant(i));     
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
        

	//	Dispatch.call(Inicio.paciente1.ianus, "Navigate","http://ianuschop.sergas.local/ianus_chp_pro/inicio.jsp");
    	Dispatch.call(Inicio.paciente1.ianus, "Navigate",direccionIanus);

		
		try {
		
		Thread.sleep(900);	
			
	//	Dispatch.call(Inicio.paciente2.ianus, "Navigate","http://ianuschop.sergas.local/ianus_chp_pro/inicio.jsp");
		Dispatch.call(Inicio.paciente2.ianus, "Navigate",direccionIanus);
		
		if(Inicio.contraseña){
			
			// introduceUsuarioJacob(Inicio.ianus1, Inicio.usuario);
			
			Dispatch.call(Inicio.paciente1.ianus,"Navigate","javascript:" + CadenasJavascript.introducirUsuario(Inicio.usuario));
			
			Thread.sleep(1500);
			
			Dispatch.put(Inicio.paciente1.ianus,"Visible",true);
			Dispatch.put(Inicio.paciente1.ianus,"menubar",false);
			Dispatch.put(Inicio.paciente1.ianus,"toolbar",false);
			
		    Dispatch.put(Inicio.paciente1.ianus,"height",1149);  // 1099
		    Dispatch.put(Inicio.paciente1.ianus,"top",130);  // 180
		    Dispatch.put(Inicio.paciente1.ianus,"left",1024);
		    if(Inicio.numeroPantallas == 1){
		    	Dispatch.put(Inicio.paciente1.ianus,"height",1172-130);  // 1099
		    	Dispatch.put(Inicio.paciente1.ianus,"width",895);
		    }
			
//		    introduceUsuarioJacob(Inicio.ianus2, Inicio.usuario);
			Dispatch.call(Inicio.paciente2.ianus,"Navigate","javascript:" + CadenasJavascript.introducirUsuario(Inicio.usuario));

		
			Thread.sleep(900);
			
			Dispatch.put(Inicio.paciente2.ianus,"Visible",true);	
			Dispatch.put(Inicio.paciente2.ianus,"menubar",false);
			Dispatch.put(Inicio.paciente2.ianus,"toolbar",false);
			
		    Dispatch.put(Inicio.paciente2.ianus,"height",1149);
		    Dispatch.put(Inicio.paciente2.ianus,"top",130);
		    Dispatch.put(Inicio.paciente2.ianus,"left",1024);
		    
		    if(Inicio.numeroPantallas == 1){
		    	Dispatch.put(Inicio.paciente2.ianus,"height",1052-130);  // 1099
		    	Dispatch.put(Inicio.paciente2.ianus,"width",895);
		    }
		    
		}
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setVisible(final ActiveXComponent ianus, final boolean verDosIanus){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if(!verDosIanus){
					Dispatch.put(ianus, "Visible", !Dispatch.get(ianus,"visible").getBoolean());
				}
				Dispatch.put(ianus, "Visible", true);
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

	public static void buscaNodo(final ActiveXComponent ianus, String servicio, final String tipoNodo, boolean inicializar){
	//	SwingUtilities.invokeLater(new Runnable() {
	//		public void run() {
				if(servicio == null){
					servicio = InicioIanus.DESCONOCIDO;
				}
				//	Busca nodo
				System.out.println("El servicio en GEstionJacob.buscaNodo es: " + servicio);
				if(servicio.equals(InicioIanus.HOSP_JACOB)){
					
					if(inicializar){
						try {

							Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.inicializacionPacienteNodoHosp(servicio));
							Thread.sleep(2000);
					//		Dispatch.call(ianus, "Navigate","javascript:" + CadenasJavascript.inicializacionPacienteSetFichaHosp());
					//		Thread.sleep(300);	
							
							/*
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
							
							*/
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("Tipo nodo en ingresos inicial es: " + tipoNodo + " en ianus1: " + Inicio.ianus1onTop);
						if(!tipoNodo.equals("x")){
							try {

								String clave = "";
								if(tipoNodo.equals("q")){
									Thread.sleep(1200);
									clave = "QUI:";
								}
								System.out.println("Buscando quirofano....");
								Dispatch.call(ianus, "Navigate","javascript:" + CadenasJavascript.buscarNodoHospHijo(clave));
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					else{
						if(!tipoNodo.equals("x")){
							String clave = "";
							if(tipoNodo.equals("q")){
								clave = "QUI:";
							}
				
							System.out.println("GestionJacob.consulta. Debería elegir nodo fecha. No iniciado.");
							Dispatch.call(ianus, "Navigate","javascript:" + CadenasJavascript.buscarNodoHospHijo(clave));
						}
						else{
							Dispatch.call(ianus, "Navigate","javascript:" + CadenasJavascript.pulsaNodoPadre());
						}
					}

				}
				else if(servicio.equals(InicioIanus.URG_JACOB)){
					
					if(inicializar){
						Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.buscarNodoUrgInicial("01/01/14"));
						
						try {
							Thread.sleep(2500);
							Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.buscarNodoUrgIngreso("01/01/14"));

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else{
						Dispatch.call(ianus, "Navigate","javascript:" + CadenasJavascript.pulsaNodoPadre());
					}

				}
				else if(servicio.equals(InicioIanus.CIA_JACOB)){
					
					if(inicializar){
						System.out.println("Imprimiendo en GestionJacob. Inicializar. CIA. " + servicio);
						
						Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.buscarNodoCiaInicial(servicio));
						
						if(!tipoNodo.equals("x")){
							try {
								Thread.sleep(1200);
								Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.buscarNodoCiaIngreso("QUI:"));

							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
		
					}else{
						if(!tipoNodo.equals("x")){
							Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.buscarNodoCiaIngreso("QUI:"));
						}
						else{
							Dispatch.call(ianus, "Navigate","javascript:" + CadenasJavascript.pulsaNodoPadre());

						}
					}

				}
				else if(!servicio.equals(InicioIanus.DESCONOCIDO)){
					
					if(inicializar){
						System.out.println("Imprimiendo en GestionJacob. Inicializar. Consulta. " + servicio);
	
						Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.buscarNodoConsultasInicial(servicio));

						try {
							Thread.sleep(1200);
							Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.creaNodoCEX(servicio));

						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						
						if(!tipoNodo.equals("x")){
							try {
								Thread.sleep(1200);
								String clave = "";
								if(tipoNodo.equals("f")){
									clave = "ltima";
								}
								else if(tipoNodo.equals("e")){
									clave = "nfermer";
								}
								Dispatch.call(ianus, "Navigate","javascript:" + CadenasJavascript.buscarNodoConsHijo(clave));
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}else{
						if(!tipoNodo.equals("x")){
							String clave = "";
							if(tipoNodo.equals("f")){
								clave = "ltima";
							}
							else if(tipoNodo.equals("e")){
								clave = "nfermer";
							}
				
							System.out.println("GestionJacob.consulta. Debería elegir nodo fecha. No iniciado.");
							Dispatch.call(ianus, "Navigate","javascript:" + CadenasJavascript.buscarNodoConsHijo(clave));
						}
						else{
							Dispatch.call(ianus, "Navigate","javascript:" + CadenasJavascript.pulsaNodoPadre());
						}
					}
				
		
				}


		//	}
	//	});
	}
	
/*	
	public static void buscaNodoYpulsaBotonAsociar(final ActiveXComponent ianus, final String servicio, final String tipoNodo){
	//	SwingUtilities.invokeLater(new Runnable() {
	//		public void run() {
				
				//	Busca nodo
				Dispatch.call(ianus,"Navigate","javascript:" + CadenasJavascript.buscarNodoConsultas(servicio,tipoNodo));
				
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
*/	
	
	public static void introduceNHC(final ActiveXComponent ianus, final String nomIanus, final String nhc){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				try {
					Dispatch.call(ianus, "Navigate","javascript:" + CadenasJavascript.introducirNHC1(nhc));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Jacob break al introducir un nhc. Restaurando.");
					
					rescateJacob(nomIanus);
				}
				
			}
		});
	}
	
	public static void cambiaPaciente(final ActiveXComponent ianus,final String nomIanus){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Dispatch.call(ianus, "navigate","javascript:window.frames.principal.botonera.inicio()");

					if(nomIanus.equals(Gestion2Ianus.nombreIanus1)){
						Inicio.ianus1onTop = false;

					}
					else{
						Inicio.ianus1onTop = true;
					}
					
					if(Inicio.vControlIanus.botonServicio.getText().equals(InicioIanus.HOSP) || 
							Inicio.vControlIanus.botonServicio.getText().equals(InicioIanus.CIA)){

						
						if(nomIanus.equals(Gestion2Ianus.nombreIanus1)){
							Dispatch.call(Inicio.paciente2.ianus, "Navigate","javascript:" + CadenasJavascript.inicializacionPacienteSetFichaHosp());
							Thread.sleep(200);
							System.out.println("Agranda fechas");
							Dispatch.call(Inicio.paciente2.ianus,"Navigate","javascript:" + CadenasJavascript.resaltarFechaIngreso01());
							Thread.sleep(200);
							Dispatch.call(Inicio.paciente2.ianus,"Navigate","javascript:" + CadenasJavascript.resaltarFechaIngreso02());
							

						}
						else{
							Dispatch.call(Inicio.paciente1.ianus, "Navigate","javascript:" + CadenasJavascript.inicializacionPacienteSetFichaHosp());
							Thread.sleep(200);
							System.out.println("Agranda fechas");
							Dispatch.call(Inicio.paciente1.ianus,"Navigate","javascript:" + CadenasJavascript.resaltarFechaIngreso01());
							Thread.sleep(200);
							Dispatch.call(Inicio.paciente1.ianus,"Navigate","javascript:" + CadenasJavascript.resaltarFechaIngreso02());

						}
	
					}
					
					if(Inicio.vControlIanus.botonServicio.getText().equals(InicioIanus.URG)){

						
						if(nomIanus.equals(Gestion2Ianus.nombreIanus1)){
							Dispatch.call(Inicio.paciente2.ianus, "Navigate","javascript:" + CadenasJavascript.inicializacionPacienteSetFichaUrg());
							Thread.sleep(300);
							System.out.println("Agranda fechas");
							Dispatch.call(Inicio.paciente2.ianus,"Navigate","javascript:" + CadenasJavascript.resaltarFechaUrg01());
							Thread.sleep(300);
							Dispatch.call(Inicio.paciente2.ianus,"Navigate","javascript:" + CadenasJavascript.resaltarFechaUrg02());
							

						}
						else{
							Dispatch.call(Inicio.paciente1.ianus, "Navigate","javascript:" + CadenasJavascript.inicializacionPacienteSetFichaUrg());
							Thread.sleep(300);
							System.out.println("Agranda fechas");
							Dispatch.call(Inicio.paciente1.ianus,"Navigate","javascript:" + CadenasJavascript.resaltarFechaUrg01());
							Thread.sleep(300);
							Dispatch.call(Inicio.paciente1.ianus,"Navigate","javascript:" + CadenasJavascript.resaltarFechaUrg02());

						}
	
					}
					/*
					else if(!Inicio.vControlIanus.botonServicio.getText().equals(InicioIanus.HOSP) && 
							!Inicio.vControlIanus.botonServicio.getText().equals(InicioIanus.CIA) &&
							!Inicio.vControlIanus.botonServicio.getText().equals(InicioIanus.URG)){
								
						if(nomIanus.equals(Gestion2Ianus.nombreIanus1)){

							System.out.println("Cambia colores");
							Dispatch.call(Inicio.paciente2.ianus,"Navigate","javascript:" + CadenasJavascript.cambiaFondoConstultas01());
							Thread.sleep(200);
							Dispatch.call(Inicio.paciente2.ianus,"Navigate","javascript:" + CadenasJavascript.cambiaFondoConstultas02());
						}
						else{

							System.out.println("Cambia colores");
							Dispatch.call(Inicio.paciente1.ianus,"Navigate","javascript:" + CadenasJavascript.cambiaFondoConstultas01());
							Thread.sleep(200);
							Dispatch.call(Inicio.paciente1.ianus,"Navigate","javascript:" + CadenasJavascript.cambiaFondoConstultas02());
						}		
					}
					
					*/
				} catch (Exception e) {
					// TODO Auto-generated catch blockNota ingreso
					e.printStackTrace();
					rescateJacob(nomIanus);
				}

			}																	
			
		});
	}
	
	public static void getIdeEpisodio(){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				ActiveXComponent ianusActivo = new ActiveXComponent("InternetExplorer.Application");
				String aux = "";
				
				
				try {
					if(Inicio.ianus1onTop){
						aux = "Ianus 1";
						ianusActivo = Inicio.paciente1.ianus;
						Dispatch.call(ianusActivo, "Navigate",CadenasJavascript.buscarIdentificacionEpisodio());
						System.out.println("Enviado a ianus 1");

					}
					else{
						aux = "Ianus 2";
						ianusActivo = Inicio.paciente2.ianus;
						Dispatch.call(ianusActivo, "Navigate",CadenasJavascript.buscarIdentificacionEpisodio());
						System.out.println("Enviado a ianus 2");
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Falló el ianus a la hora de asociar. " + aux);
					
					rescateJacob(aux);
				}
			}
		});
	}
	
	public static void putAnclaPadre(final String codigoJavascript){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				ActiveXComponent ianusActivo = new ActiveXComponent("InternetExplorer.Application");
				String aux = "";
				
				
				try {
					if(Inicio.ianus1onTop){
						aux = "Ianus 1";
						ianusActivo = Inicio.paciente1.ianus;
						Dispatch.call(ianusActivo, "Navigate",codigoJavascript);
						System.out.println("Enviado a ianus 1");

					}
					else{
						aux = "Ianus 2";
						ianusActivo = Inicio.paciente2.ianus;
						Dispatch.call(ianusActivo, "Navigate",codigoJavascript);
						System.out.println("Enviado a ianus 2");
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Falló el ianus a la hora de asociar. " + aux);
					
					rescateJacob(aux);
				}
			}
		});
	}
	
	
	public static void pulsaBotonAsociar(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				
				ActiveXComponent ianusActivo = new ActiveXComponent("InternetExplorer.Application");
				String aux = "";
				
				
				try {
					if(Inicio.ianus1onTop){
						aux = "Ianus 1";
						ianusActivo = Inicio.paciente1.ianus;
						Dispatch.call(ianusActivo, "Navigate",CadenasJavascript.buscarBotonAsociar());
						

					}
					else{
						aux = "Ianus 2";
						ianusActivo = Inicio.paciente2.ianus;
						Dispatch.call(ianusActivo, "Navigate",CadenasJavascript.buscarBotonAsociar());
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Falló el ianus a la hora de asociar. " + aux);
					
					rescateJacob(aux);
					
				}
			}
		});
	}
	
	
	public static void introduceUsuarioJacob(final ActiveXComponent ianus, final Usuario user){
		
		user.usuario = "mgamg1";
		user.password = "xxx";

		System.out.println(user.password);
		
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
	
	public static void rescateJacob(String ianus){
		int iCount = InicioIanus.oWindows.getProperty("Count").getInt();
		
		int i = 1;
		if(!ianus.equals("Ianus 1")){
			i = 2;
		}
		InicioIanus.oWindows.invokeGetComponent("Item", new Variant(iCount-i)); 
	}
	
	public static void reseteaIanus(){
		
		String ianus = Inicio.vControlIanus.labelNumeroIanus.getText();
		System.out.println("Reseteando el ianus: " + ianus);
		if(ianus.toLowerCase().contains(("Ianus 1").toLowerCase())){
			Dispatch.call(Inicio.paciente1.ianus, "Navigate","http://ianuschop.sergas.local/ianus_chp_pro/inicio.jsp");
			try {
				Thread.sleep(900);
				Dispatch.call(Inicio.paciente1.ianus,"Navigate","javascript:" + CadenasJavascript.introducirUsuario(Inicio.usuario));
			    Thread.sleep(300);
				Dispatch.put(Inicio.paciente1.ianus,"height",1099);
			    Dispatch.put(Inicio.paciente1.ianus,"top",180);
			    Dispatch.put(Inicio.paciente1.ianus,"left",1024);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		}
		else{
			Dispatch.call(Inicio.paciente2.ianus, "Navigate","http://ianuschop.sergas.local/ianus_chp_pro/inicio.jsp");
			try {
				Thread.sleep(900);
				Dispatch.call(Inicio.paciente2.ianus,"Navigate","javascript:" + CadenasJavascript.introducirUsuario(Inicio.usuario));
			    Thread.sleep(300);
				Dispatch.put(Inicio.paciente2.ianus,"height",1099);
			    Dispatch.put(Inicio.paciente2.ianus,"top",180);
			    Dispatch.put(Inicio.paciente2.ianus,"left",1024);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void reIntroduceNHC(){
		
		String ianus = Inicio.vControlIanus.labelNumeroIanus.getText();

		if(ianus.toLowerCase().contains(("Ianus 1").toLowerCase())){
			
			try {
				Dispatch.call(Inicio.paciente1.ianus,"navigate","javascript:" +CadenasJavascript.reloadNhc01());
				Thread.sleep(300);
				Dispatch.call(Inicio.paciente1.ianus,"navigate","javascript:" +CadenasJavascript.reloadNhc02());
				Thread.sleep(300);
				Dispatch.call(Inicio.paciente1.ianus,"navigate","javascript:" +CadenasJavascript.reloadNhc03());
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Gestion2Ianus.introduceNHC(Inicio.paciente1.ianus,Gestion2Ianus.nombreIanus1,Inicio.nhcDelIanus1,100);
			Gestion2Ianus.buscaNodo(Inicio.paciente1.ianus,Inicio.documento[Inicio.indiceArchivoSelecc].servicio,Inicio.documento[Inicio.indiceArchivoSelecc].nombreNormalizado,true,false,Retardos.retardoCargarPaciente,false);
					
		}
		else{
//			Dispatch.call(Inicio.paciente2.ianus,"GoBack");
			
			try {
				Dispatch.call(Inicio.paciente2.ianus,"navigate","javascript:" +CadenasJavascript.reloadNhc01());
				Thread.sleep(300);
				Dispatch.call(Inicio.paciente2.ianus,"navigate","javascript:" +CadenasJavascript.reloadNhc02());
				Thread.sleep(300);
				Dispatch.call(Inicio.paciente2.ianus,"navigate","javascript:" +CadenasJavascript.reloadNhc03());
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Gestion2Ianus.introduceNHC(Inicio.paciente2.ianus,Gestion2Ianus.nombreIanus2,Inicio.nhcDelIanus2,100);
			Gestion2Ianus.buscaNodo(Inicio.paciente2.ianus,Inicio.documento[Inicio.indiceArchivoSelecc].servicio,Inicio.documento[Inicio.indiceArchivoSelecc].nombreNormalizado,true,false,Retardos.retardoCargarPaciente,false);

		}
	}
	
	public static void pulsaVersionar(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				
				ActiveXComponent ianusActivo = new ActiveXComponent("InternetExplorer.Application");
				String aux = "";
				
				
				try {
					if(Inicio.ianus1onTop){
						aux = "Ianus 1";
						ianusActivo = Inicio.paciente1.ianus;
						Dispatch.call(ianusActivo, "Navigate",CadenasJavascript.pulsarVersionar());
						

					}
					else{
						aux = "Ianus 2";
						ianusActivo = Inicio.paciente2.ianus;
						Dispatch.call(ianusActivo, "Navigate",CadenasJavascript.pulsarVersionar());
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Falló el ianus a la hora de versionar. " + aux);
					
					rescateJacob(aux);
					
				}
			}
		});
	}
}
