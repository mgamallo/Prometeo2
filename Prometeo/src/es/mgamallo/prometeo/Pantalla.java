package es.mgamallo.prometeo;

import java.awt.Frame;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class Pantalla {

	public static boolean maximizar(JFrame frame){
		frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
		return true;
	}
	
	public static boolean restaurar(JFrame frame){
		frame.setExtendedState(Frame.NORMAL);
		return false;
	}
	
	public static boolean maximizarAyuda(JFrame frame){
		
		int ancho = 2047;
		if(Inicio.numeroPantallas == 1){
			ancho = 1920;
		}
		
		frame.setBounds(0, 0, ancho, 1250);
		return true;
	}
	
	public static boolean restaurarMaximizarAyuda(JFrame frame){
		frame.setBounds(Inicio.rectanguloAyuda);
		return false;
	}
}
