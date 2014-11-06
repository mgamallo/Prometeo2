package es.mgamallo.prometeo;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class HiloNHCyNodo extends Thread{

	final String introduceNHC;
	final String servicio;
	int retardo;
	boolean primerDocumento;
	ActiveXComponent ianus;
	
	protected static final String LS = System.getProperty("line.separator");

	HiloNHCyNodo(ActiveXComponent ianus, String cadenaJavascript, String servicio, int retardo, boolean primerDocumento) {
		this.ianus = ianus;
		introduceNHC = "javascript:" + cadenaJavascript;
		this.retardo = retardo;
		this.servicio = servicio;
		this.primerDocumento = primerDocumento;
	}
	
	public void run() {
		try {
			Thread.sleep(retardo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				 Dispatch.call(ianus, "navigate",introduceNHC);
				
				try {
					Thread.sleep(5500);
				/*	if(primerDocumento)
						GestionJacob.buscaNodoYpulsaBotonAsociar(ianus,servicio);
					else  */
						System.out.println("Buscando nodo en hilo...");
						
				//		Thread.sleep(1500);
						GestionJacob.getDatosPaciente(ianus);
	///////////////////////////////////////////////////////////////////////	
						
						System.out.println("Deberíamos Actualizar los botones");
						

		/////////////////////////////////////////////////////////////////				
						
						Thread.sleep(200);
						GestionJacob.buscaNodo(ianus, servicio);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}
			
	
		
}
