package es.mgamallo.prometeo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class HiloClipboard extends Thread{

	int retardo;
	String cadena = "";
	String idEpi = "";
	
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
		cadena = clipBoard.getDatosPortapapelesTemporal();
		getIdEpi(cadena);
		
		
	}
	
	
	private void getIdEpi(String cadena){

		int index = cadena.indexOf("function crearCursoClinico()");
		System.out.println(index);
		if(index != -1){
			cadena = cadena.substring(index, index+300);
			int idEpi1 = cadena.indexOf("idEpi=");
			cadena = cadena.substring(idEpi1 + 7);
			int idEpi2 = cadena.indexOf(";") -1;
			idEpi = "Epi_" + cadena.substring(0,idEpi2);

			int centro1 = cadena.indexOf("centro=");
			cadena = cadena.substring(centro1 + 8);
			int centro2 = cadena.indexOf(";")-1;
			idEpi = idEpi + "_" + cadena.substring(0,centro2);
			
			int app1 = cadena.indexOf("app=");
			cadena = cadena.substring(app1 + 5);
			int app2 = cadena.indexOf(";") - 1;
			idEpi = idEpi +  "_" + cadena.substring(0, app2);
			
			int tipoEpisodio1 = cadena.indexOf("tipoEpisodio=");
			cadena = cadena.substring(tipoEpisodio1 + 14);
			int tipoEpisodio2 = cadena.indexOf(";") - 1;
			idEpi = idEpi +  "_" + cadena.substring(0, tipoEpisodio2);
			
			System.out.println(idEpi);
			GestionJacob.putAnclaPadre(CadenasJavascript.forzarNodo(idEpi));

		}


		
	}
}
