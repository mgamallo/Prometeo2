package es.mgamallo.prometeo;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class HiloNHCyNodo extends Thread{

	final String cadenaJavascript;
	final String servicio;
	int retardo;
	boolean primerDocumento;
	ActiveXComponent ianus;
	
	protected static final String LS = System.getProperty("line.separator");

	HiloNHCyNodo(ActiveXComponent ianus, String cadenaJavascript, String servicio, int retardo, boolean primerDocumento) {
		this.ianus = ianus;
		this.cadenaJavascript = "javascript:" + cadenaJavascript;
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

				Dispatch.call(ianus, "navigate",cadenaJavascript);
				
				try {
					Thread.sleep(4000);
					if(primerDocumento)
						GestionJacob.buscaNodoYpulsaBotonAsociar(ianus,servicio);
					else
						GestionJacob.buscaNodo(ianus, servicio);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}
			
	
		
}
