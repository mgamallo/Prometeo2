package es.mgamallo.prometeo;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class HiloClipboard extends Thread{

	int retardo;
	
	HiloClipboard(int retardo){

		this.retardo = retardo;
	}
	
	public void run(){
		try {
			Thread.sleep(retardo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Portapapeles clipBoard = new Portapapeles();
		String head = clipBoard.getDatosPortapapelesTemporal();
		System.out.println(head);
	}
}
