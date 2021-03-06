package es.mgamallo.prometeo;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseListener;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class CapturaRatonYTeclado implements NativeKeyListener,
		NativeMouseInputListener {

	protected static final String LS = System.getProperty("line.separator");
	
	public static boolean barraEspaciadoraOn = true;
	
	int teclaActual = 0;
	int teclaAnterior = 0;
	
	public CapturaRatonYTeclado() {
		// TODO Auto-generated constructor stub

		 GlobalScreen.getInstance().addNativeKeyListener(this);
         GlobalScreen.getInstance().addNativeMouseListener(this);
         GlobalScreen.getInstance().addNativeMouseMotionListener(this);
         
         System.out.println("Hola captura");
         
         try {
			GlobalScreen.registerNativeHook();
			
			System.out.println("Capturando");
			
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * try { GlobalScreen.registerNativeHook(); } catch (NativeHookException
		 * e) { // TODO Auto-generated catch block
		 * System.err.println("There was a problem registering the native hook."
		 * ); System.err.println(e.getMessage()); //e.printStackTrace(); }
		 */
	}

	// M�todos de rat�n
	// **********************************************************

	@Override
	public void nativeMouseClicked(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == 3){
			try {
				Robot robot  = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_2);
				robot.keyRelease(KeyEvent.VK_2);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mousepressed");
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mousereleased");
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mousedragged");
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println("mousemoved");
	}

	// M�todos de teclado
	// *************************************************************

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		 System.out.println("NativeKeyPressed " + e.getKeyCode());
		 System.out.println("Tecla ... " + ((char) e.getKeyCode()));

		
		if(Inicio.xedoc){
			// Tecla * asterisco
			if(e.getKeyCode() == 106){
				
						if(InicioXedoc.antiguo)
							Dispatch.call(GestionJacobXedoc.bandejaXedoc1, "Navigate","javascript:" + CadenasJavascriptXedoc.zoomPdf()  /* CadenasJavascriptXedoc.maquetado2() */ );
						else{
							if(Inicio.xedoc1onTop){

								Dispatch.put(Inicio.documento1.xedoc,"visible","false");
								if(Inicio.xedoc2activo){
									Inicio.ventanaControlXedoc.jBxedoc1.setBackground(Color.DARK_GRAY);
									try {
										Thread.sleep(200);
									} catch (InterruptedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									Dispatch.put(Inicio.documento2.xedoc,"visible","true");
									Inicio.ventanaControlXedoc.jBxedoc2.setBackground(Color.green);
									Inicio.xedoc1onTop = false;
									
									Robot robot;
									try {
										robot = new Robot();
										
										Point p = MouseInfo.getPointerInfo().getLocation();
																				
										robot.delay(300);
										robot.mouseMove(40, 15);
										robot.mousePress(InputEvent.BUTTON1_MASK);
										robot.mouseRelease(InputEvent.BUTTON1_MASK);
										robot.delay(200);
										robot.mouseMove(p.x, p.y);
										
									} catch (AWTException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

									
									GestionJacobXedoc.cargaNuevoPdf(Inicio.documento1.xedoc);
								}
								else{
									GestionJacobXedoc.finXedoc(Inicio.documento1.xedoc,"Xedoc1");
								}
			
							}
							else if(!Inicio.xedoc1onTop && Inicio.xedoc1activo){

								Dispatch.put(Inicio.documento2.xedoc,"visible","false");
								if(Inicio.xedoc2activo){
									Inicio.ventanaControlXedoc.jBxedoc2.setBackground(Color.DARK_GRAY);
									try {
										Thread.sleep(200);
									} catch (InterruptedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									Dispatch.put(Inicio.documento1.xedoc,"visible","true");
									Inicio.ventanaControlXedoc.jBxedoc1.setBackground(Color.green);
									Inicio.xedoc1onTop = true;
									
									Robot robot;
									try {
										robot = new Robot();
										robot.delay(300);
										robot.mousePress(InputEvent.BUTTON1_MASK);
										robot.mouseRelease(InputEvent.BUTTON1_MASK);
									} catch (AWTException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									
									GestionJacobXedoc.cargaNuevoPdf(Inicio.documento2.xedoc);
								}
								else{
									GestionJacobXedoc.finXedoc(Inicio.documento2.xedoc,"Xedoc2");
								}
							}
						}
			}
			
			/*
			
			//	Tecla �
			if(e.getKeyCode() == 92){
	//			Dispatch.call(Inicio.documento1.xedoc, "Navigate","javascript:" +  CadenasJavascriptXedoc.zoomPdf2() );
				GestionJacobXedoc.captura2XedocIndividuales();
			
			}
			
			//	Tecla fin
			if(e.getKeyCode() == 35){
	//			Dispatch.call(Inicio.documento1.xedoc, "Navigate","javascript:" +  CadenasJavascriptXedoc.zoomPdf2() );
				GestionJacobXedoc.aceptar();
			
			}
			
			*/
			/*
			//	Tecla Avzar pagina			if(e.getKeyCode() == 34){
			if(e.getKeyCode() == 34){
		//		Dispatch.call(Inicio.documento1.xedoc, "Navigate","javascript:" +  CadenasJavascriptXedoc.zoomPdf3() );
				
				try {
					Inicio.documento1.xedoc = GestionJacobXedoc.capturaUltimoExplorer();
					
					 Thread.sleep(200);
				
					 MaquetadoXedoc maquetado = new MaquetadoXedoc(Inicio.documento1.xedoc, "Xedoc 1");
				
				}catch(InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			*/
			/*
			
			//  Flecha arriba
			if(e.getKeyCode() == 38){
				Dispatch.call(GestionJacobXedoc.bandejaXedoc1, "Navigate","javascript:" +  CadenasJavascriptXedoc.pruebaTabla() );
				
			}
			
			*/
			//  Flecha derecha
			if(e.getKeyCode() == 39){
			}
			
			//  Flecha abajo
			if(e.getKeyCode() == 40){
			}
			
			//  Flecha izquierda
			if(e.getKeyCode() == 37){
			}
			
		}
		
		if(Inicio.teclasHabilitadas){
			teclaAnterior = teclaActual;
			teclaActual = e.getKeyCode();
			
			if (e.getKeyCode() == 27) {
				System.out.println("Pulsado escape");
			}


			switch (e.getKeyCode()) {
			case 112:
	
				break;
			case 113:
				System.out.println("Pulsado f2. Capturando nodo");
				

	
							Portapapeles clipBoard = new Portapapeles();
			//				String temporal = clipBoard.getDatosPortapapelesTemporal();
							GestionJacob.getIdeEpisodio();
							HiloClipboard hilo = new HiloClipboard(500);
							hilo.run();
							
							
							
							try {
								Robot robot = new Robot();
								robot.delay(500);
								robot.keyPress(KeyEvent.VK_ENTER);
								robot.keyRelease(KeyEvent.VK_ENTER);
								robot.delay(200);
								robot.keyPress(KeyEvent.VK_V);
								robot.keyRelease(KeyEvent.VK_V);				
								
							} catch (AWTException ex) {
								// TODO Auto-generated catch block
								ex.printStackTrace();
							}
							
							
							
							
							

				

				/***************************************************************
				System.out.println("Pulsado f2. Salir de ianus1");
				
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						final String salirPaciente = 
								"var framePrincipal = window.frames;" + LS +
								"var frameBotonera = framePrincipal['principal'].frames['botonera'];" + LS +

								"frameBotonera.inicio();"
								;
						
						String nhc = "559287";
						
						final String introducirNHCPulido = 
								"var framePrincipal = window.frames;" + LS +
								"var frameNHC = framePrincipal['principal'].frames['mainFrame'];" + LS +
								"var NHC = frameNHC.document.buscarPacienteForm.ID_NHC;" + LS +
						
								"NHC.value = " + nhc + ";" + LS +
								"frameNHC.buscar();"
								;
						

						
						Inicio.ianus1.webBrowser.executeJavascript(salirPaciente);
						
						Hilo introNHC = new Hilo(Inicio.ianus1.webBrowser ,introducirNHCPulido,1000);
					    introNHC.start();
					    
					    Inicio.ianus1.frame.setAlwaysOnTop(false);
					    Inicio.ianus2.frame.setAlwaysOnTop(true);
					    
						
					}
				});
		*************************************		  */
				break;
			case 114:
				
				/**************************************************************
				System.out.println("Pulsado f3. Salir de ianus2");

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						final String salirPaciente = 
								"var framePrincipal = window.frames;" + LS +
								"var frameBotonera = framePrincipal['principal'].frames['botonera'];" + LS +

								"frameBotonera.inicio();"
								;
							
						String nhc = "486712";
						
						final String introducirNHCPulido = 
								"var framePrincipal = window.frames;" + LS +
								"var frameNHC = framePrincipal['principal'].frames['mainFrame'];" + LS +
								"var NHC = frameNHC.document.buscarPacienteForm.ID_NHC;" + LS +
						
								"NHC.value = " + nhc + ";" + LS +
								"frameNHC.buscar();"
								;
						

						
						Inicio.ianus2.webBrowser.executeJavascript(salirPaciente);
						
						Hilo introNHC = new Hilo(Inicio.ianus2.webBrowser ,introducirNHCPulido,1000);
					    introNHC.start();
					    
					    Inicio.ianus2.frame.setAlwaysOnTop(false);
					    Inicio.ianus1.frame.setAlwaysOnTop(true);
					}
				});
		********************************************************/		
				break;
	
	 		case 116:

				System.out.println("Pulsado f5");
				versiona("HOS");
				break;
				
			case 117:

				System.out.println("Pulsado f6");
				versiona("CEX");
				break;

	 		case 118:

				System.out.println("Pulsado f7");
				versiona("CIA");
				break;
				
			case 119:

				System.out.println("Pulsado f8");
				versiona("QUI");
				break;
/*
			case 81: // q
				System.out.println("Pulsado q");
				break;
			case 87: // w
				System.out.println("Pulsado w");
				break;
			case 69: // e
				System.out.println("Pulsado e");
				break;
			case 82: // r
				System.out.println("Pulsado r");
				break;
	*/
	/*		
		case 66: // b
			System.out.println("Pulsado b");
			Inicio.gestion.gestion();
			break;
	*/
	/*	case 86: // v
			System.out.println("Pulsado v");
		//	if(barraEspaciadoraOn){
		//		barraEspaciadoraOn = false;
		//		Inicio.vExplorador.asociaDocumento();
		//	}
		
			break;
	*/		
	/*
		case 32: // space
			System.out.println("Pulsado space");
		//	if(barraEspaciadoraOn){
		//		barraEspaciadoraOn = false;
		//		Inicio.gestion.gestion();
		//	}
			
			break;
	*/
				
			case 67: // c
				System.out.println("Pulsado c");
				try {
					Robot robot = new Robot();
					
					robot.mouseMove(300, 400);
					robot.delay(50);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					robot.delay(100);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_0);
					robot.delay(50);
					robot.keyRelease(KeyEvent.VK_0);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.delay(50);

				} catch (AWTException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				break;	
			

			default:
				//  V, cuando no utilizamos ctrl + v
				if(teclaAnterior != 17 && teclaActual == 86){
					// Inicio.vExplorador.asociaDocumento();
					
					if(!InicioIanus.versionar){
						
						GestionJacob.pulsaBotonAsociar();
						
						
						if(true){
							try {
								
								// Thread.sleep(Retardos.retardoDibujarVentana);
								Thread.sleep(100);

							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						
						
						Portapapeles cbTemporal = new Portapapeles();
						String tipoSubida = cbTemporal.getTipoDeSubida();
						InicioIanus.tipoSubida = tipoSubida;
						
						System.out.println("El tipo de subida es..... " + tipoSubida);

					}
					Inicio.vExplorador.asociaDocumento(Inicio.documento[Inicio.indiceArchivoSelecc].nombreNormalizado);
				}
				//	space, cuando no utilizamos tab - space
				else if(teclaAnterior != 9 && teclaActual == 32){
					
					
					// System.out.println("Comparamos si es un separador...");
					// Comparamos si es un separador. Si lo es visualizamos el siguiente pdf
					
					if(Inicio.vControlIanus.botonNHC.getText().equals(InicioIanus.SEPARADOR)){
						
						System.out.println("Es un separador");
						
				        int tama�oLista = Inicio.vExplorador.listaPdfs.getModel().getSize();

			        	if(Inicio.indiceArchivoSelecc + 1< tama�oLista){
			        		
			        		Inicio.indiceArchivoSelecc++;
			        		Inicio.documentoActivo = Inicio.documento[Inicio.indiceArchivoSelecc];
			        		

			        		
			        		Inicio.vExplorador.listaPdfs.setSelectedIndex(Inicio.indiceArchivoSelecc);
			        	//	jScrollPane1.getVerticalScrollBar().setValue(Inicio.indiceArchivoSelecc*15 -15);
			          		

			        		try{
			        			
			        			Inicio.panelPrincipal.webBrowserOperaciones.setVisible(true);
			         			
			        			if(Inicio.esWin64){
			        				// new VisualizaPdfWeb(Inicio.documento[Inicio.indiceArchivoSelecc].rutaArchivo);
			        				new VisualizaPdfReader(Inicio.documento[Inicio.indiceArchivoSelecc].rutaArchivo);

			        			}
			        			else{
			        				SwingUtilities.invokeLater(new Runnable() {
			            				public void run() {  
			            					Inicio.panelPrincipal.webBrowserOperaciones.navigate(Inicio.documento[Inicio.indiceArchivoSelecc].rutaArchivo);
			            				}
			            			});  
			        			}
			        			
			        	
			        			
			        			Inicio.vControlIanus.panelBotones.setBackground(new Color(80,200,120));
			 
			        			
			            		if(!Inicio.vNombres.comboServicio.getSelectedItem().toString().equals(Inicio.documento[Inicio.indiceArchivoSelecc])){
			            			Inicio.vControlIanus.botonServicio.setBackground(Color.red);
			            			Inicio.vControlIanus.panelBotones.setBackground(Color.yellow);
			            			Inicio.vControlIanus.botonServicio.setText(Inicio.documento[Inicio.indiceArchivoSelecc].servicio);
			            		}
			            		else{
			            			Inicio.vControlIanus.botonServicio.setBackground(Color.green);
			            			Inicio.vControlIanus.panelBotones.setBackground(new Color(143, 188, 143));
			            		}
			            		if(!Inicio.documento[Inicio.indiceArchivoSelecc].nhc.equals(Inicio.documento[Inicio.indiceArchivoSelecc-1].nhc)){
			            			Inicio.vControlIanus.panelBotones.setBackground(Color.red);
			            			Inicio.vControlIanus.botonNHC.setText(Inicio.documento[Inicio.indiceArchivoSelecc].nhc);

			            		}
			            		else{
			            			Inicio.vControlIanus.panelBotones.setBackground(new Color(143, 188, 143));
			            			Inicio.vControlIanus.botonNHC.setText(Inicio.documento[Inicio.indiceArchivoSelecc].nhc);
			            		}
			        			
			            		Inicio.vControlIanus.botonNombreDocumento.setText(Inicio.documento[Inicio.indiceArchivoSelecc].nombreNormalizado);
			        			
			         			Inicio.vNombres.comboServicio.setSelectedItem(Inicio.documento[Inicio.indiceArchivoSelecc].servicio);
			         			
			       			    
			        		}catch (Exception ev) {
			        			System.out.println(ev);
			        		}
						
						
			        	}
					}
					
					
					
					
					
					Inicio.gestion.gestion();
					


				}
		}
		}
		
		

	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
	//	 System.out.println("NativeKeyReleased " + arg0.getKeyCode());
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
	//	 System.out.println("NativeKeyTyped " + arg0.getKeyCode());
	}

	
	private void versiona(String tipoVersion){

		
		GestionJacob.pulsaVersionar();
		try {
			Robot robot = new Robot();
			robot.delay(600);
			robot.mouseMove(1525, 645);
			robot.delay(50);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.delay(300);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.delay(50);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.delay(50);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
						
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		InicioIanus.versionar = true;
		InicioIanus.tipoSubida = tipoVersion;
	}
	
	
	static public void main(String arg[]){
		CapturaRatonYTeclado capt = new CapturaRatonYTeclado();
		/*
		 GlobalScreen.getInstance().addNativeKeyListener(capt);
         GlobalScreen.getInstance().addNativeMouseListener(capt);
         GlobalScreen.getInstance().addNativeMouseMotionListener(capt);
         
         System.out.println("Hola");
         
         try {
			GlobalScreen.registerNativeHook();
			
			System.out.println("Capturando");
			
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
}
