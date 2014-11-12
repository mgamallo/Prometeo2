package es.mgamallo.prometeo;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class HiloNHC extends Thread{

	final String introduceNhc;
	final String nombreIanus;
	int retardo;
	ActiveXComponent ianus;
	
	HiloNHC(ActiveXComponent ianus,String introduceNhc, String nombreIanus, int retardo){
		this.ianus = ianus;
		this.introduceNhc = introduceNhc;
		this.nombreIanus = nombreIanus;
		this.retardo = retardo;
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
					Dispatch.call(ianus, "navigate",introduceNhc);
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
