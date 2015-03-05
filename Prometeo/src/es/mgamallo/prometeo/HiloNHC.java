package es.mgamallo.prometeo;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class HiloNHC extends Thread{

	final String introduceNhc1;
	final String nombreIanus;
	final String nhc;
	int retardo;
	ActiveXComponent ianus;
	
	HiloNHC(ActiveXComponent ianus,String introduceNhc1, String nombreIanus, int retardo, String nhc){
		this.ianus = ianus;
		this.introduceNhc1 = introduceNhc1;
		this.nombreIanus = nombreIanus;
		this.retardo = retardo;
		this.nhc = nhc;
	}
	
	public void run(){
		try {
			Thread.sleep(retardo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				 try {
					Dispatch.call(ianus, "navigate",introduceNhc1);
					Thread.sleep(Retardos.retardoIntroducirNHC);
					Dispatch.call(ianus, "navigate",CadenasJavascript.introducirNHC2(nhc));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					System.out.println("Jacob break al introducir un nhc. Restaurando.");
					
					GestionJacob.rescateJacob(nombreIanus);
				}
			}
		});
	}
}
