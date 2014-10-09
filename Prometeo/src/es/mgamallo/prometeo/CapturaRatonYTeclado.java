package es.mgamallo.prometeo;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseListener;

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

	// Métodos de ratón
	// **********************************************************

	@Override
	public void nativeMouseClicked(NativeMouseEvent e) {
		// TODO Auto-generated method stub

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

	// Métodos de teclado
	// *************************************************************

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("NativeKeyPressed " + e.getKeyCode());
		System.out.println("Tecla ... " + e.getKeyCode());

		teclaAnterior = teclaActual;
		teclaActual = e.getKeyCode();
		
		if (e.getKeyCode() == 27) {
			System.out.println("Pulsado escape");
		}

		switch (e.getKeyCode()) {
		case 112:
			System.out.println("Pulsado f1. Inicialización de los dos ianus");
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Inicio.gestion = new Gestion2Ianus();
					Inicio.gestion.impresionInicial();
				}
			});
			

			break;
		case 113:
			
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
		case 115:
			System.out.println("Pulsado f4");
			break;

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
		
	case 66: // b
		System.out.println("Pulsado b");
		Inicio.gestion.gestion();
		break;
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
		default:
			//  V, cuando no utilizamos ctrl + v
			if(teclaAnterior != 17 && teclaActual == 86){
				Inicio.vExplorador.asociaDocumento();
			}
	}		

	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		 System.out.println("NativeKeyReleased " + arg0.getKeyCode());
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		 System.out.println("NativeKeyTyped " + arg0.getKeyCode());
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
