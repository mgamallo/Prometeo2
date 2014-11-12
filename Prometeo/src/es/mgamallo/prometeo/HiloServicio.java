package es.mgamallo.prometeo;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class HiloServicio extends Thread{

	final String servicio;
	String tipoNodo;
	int retardo;
	boolean inicializar;
	ActiveXComponent ianus;
	
	HiloServicio(ActiveXComponent ianus,String servicio, String tipoNodo, int retardo, boolean inicializar){
		this.ianus = ianus;
		this.servicio = servicio;
		this.retardo = retardo;
		this.tipoNodo = tipoNodo;
		this.inicializar = inicializar;
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

				// GestionJacob.getDatosPaciente(ianus);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				GestionJacob.buscaNodo(ianus, servicio, tipoNodo, inicializar);
			}
		});
	}
}
