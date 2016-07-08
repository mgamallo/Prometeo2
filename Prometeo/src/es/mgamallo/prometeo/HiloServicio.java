package es.mgamallo.prometeo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class HiloServicio extends Thread{

	final int RET = 200;
	
	final String servicio;
	String tipoNodo;
	int retardo;
	boolean inicializar;
	boolean buscaOtroNodo;
	boolean subirAuto;
	ActiveXComponent ianus;
	
	HiloServicio(ActiveXComponent ianus,String servicio, String tipoNodo, int retardo, boolean inicializar,boolean buscaOtroNodo, boolean subirAuto){
		this.ianus = ianus;
		this.servicio = servicio;
		this.retardo = retardo;
		this.tipoNodo = tipoNodo;
		this.inicializar = inicializar;
		this.buscaOtroNodo = buscaOtroNodo;
		this.subirAuto = subirAuto;
		System.out.println("HiloServicio. Incialización.");
	}
	
	public void run(){
		System.out.println("HiloServicio. Metodo run.");
		if(!buscaOtroNodo){
			
		//	getReadyState(3, retardo);
			
		
			try {
				Thread.sleep(retardo);
				System.out.println("HiloServicio. Retado run.");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println("HiloServicio. Inicio run. Antes de buscarNodo. Tipo Nodo: " + tipoNodo);
		GestionJacob.buscaNodo(ianus, servicio, tipoNodo, inicializar);
		
		if(subirAuto){
			int ret = Retardos.retPulsarVauto;;
			
			if(!buscaOtroNodo){
				ret = RET;
			}
			
			try {
				Thread.sleep(ret);
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				
				System.out.println("Pulsamos v automat.");
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		/*
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				// GestionJacob.getDatosPaciente(ianus);

				try {
					// Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			//	System.out.println("HiloServicio. Inicio run. Antes de buscarNodo. Tipo Nodo: " + tipoNodo);
			//	GestionJacob.buscaNodo(ianus, servicio, tipoNodo, inicializar);
			}
		});
		*/
	}
	
	
	public void getReadyState(int estado, int retardo) {
		
		
		
		/*
		int ciclos = 0;
		while (true && ciclos < 100) {

			System.out.println(ianus.getProperty("LocationURL").getString());
			String cadena = Dispatch.call(ianus, "readyState").toString();
			try {
				Thread.sleep(200);
				System.out.println(ciclos +  "  " + cadena);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(cadena.toString());
			if (Integer.valueOf(cadena.toString()) == estado) {
				System.out.println("CArgado");
				break;
			}
			ciclos++;
		}
		*/
		
		System.out.println(ianus.getProperty("LocationURL").getString());
		
		try {
			Thread.sleep(9500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		Dispatch window = Dispatch.call(ianus,"principal").getDispatch();
		System.out.println(Dispatch.call(window, "length").toString());
	    
		System.out.println(ianus.getProperty("LocationURL").getString());
		
	      Dispatch documento = Dispatch.call(ianus,"document").getDispatch();
	      Dispatch frames = Dispatch.call(documento,"getElementsByTagName", "frame").getDispatch();

	      Dispatch framePrincipal = Dispatch.call(frames,String.valueOf(1)).getDispatch();
	      System.out.println(Dispatch.call(framePrincipal,"name").toString());
	      int ciclos = 0;
      
		  while (true && ciclos < 50) {

				String cadena = Dispatch.call(framePrincipal, "readyState").toString();
				try {
					Thread.sleep(200);
					System.out.println(ciclos +  "  " + cadena);
				} catch (InterruptedException ev) {
					// TODO Auto-generated catch block
					ev.printStackTrace();
				}
				// System.out.println(cadena.toString());
				if (cadena.toString().equals("complete") || Integer.valueOf(cadena.toString()) == estado) {
					System.out.println("CArgado");
					break;
				}
		}
		
	}
	
	
	private void gestionRetardo(int retardo){
		
		
		
		try {
			
			Thread.sleep(7500);
			
			
			for(int i = 3500; i< retardo; i+= 350){
				System.out.println(i);
				
			      Dispatch documento = Dispatch.call(ianus,"document").getDispatch();
			      Dispatch frames = Dispatch.call(documento,"getElementsByTagName", "frame").getDispatch();
      
			      Dispatch framePrincipal = Dispatch.call(frames,String.valueOf(1)).getDispatch();
			      
			      Dispatch frameDatos = Dispatch.call(framePrincipal,"datos").getDispatch();
			      Dispatch frameArbol = Dispatch.call(frameDatos,"arbol").getDispatch();
			      Dispatch frameDespliegue = Dispatch.call(frameArbol,"despliegue").getDispatch();
			      Dispatch cargado =Dispatch.call(frameDespliegue,"getElementById", "divNodoDocExterna").getDispatch();
			      Variant texto = Dispatch.get(cargado, "innerHTML");
			      System.out.println(texto.toString());
			      
			      if(texto != null){
			    	  break;
			      }
			      
			      System.out.println("HiloServicio. Retado run.");
			      Thread.sleep(350);
			      
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
	}
}
