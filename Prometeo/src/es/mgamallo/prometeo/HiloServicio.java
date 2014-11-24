package es.mgamallo.prometeo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class HiloServicio extends Thread{

	final int RET = 500;
	
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
			int ret = Inicio.inicioIanus.retardo.retPulsarVauto;;
			
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
}
