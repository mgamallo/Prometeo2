package es.mgamallo.prometeo;

import java.awt.Frame;

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
}
