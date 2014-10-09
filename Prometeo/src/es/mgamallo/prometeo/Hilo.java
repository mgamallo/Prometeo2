package es.mgamallo.prometeo;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class Hilo extends Thread{

	final String cadenaJavascript;
	int retardo;
	ActiveXComponent ianus;
	
	protected static final String LS = System.getProperty("line.separator");

	Hilo(ActiveXComponent ianus, String cadenaJavascript, int retardo) {
		this.ianus = ianus;
		this.cadenaJavascript = "javascript:" + cadenaJavascript;
		this.retardo = retardo;
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
			}
		});
	}
	
}
