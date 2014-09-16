package es.mgamallo.prometeo;

import java.awt.Color;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class Inicio {

	public static InterfazPrincipal panelPrincipal;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		NativeInterface.open(); 
		
		String prueba[] = Txt.obtenerUsuarios("txt/usuarios.txt");
		
		for(int i=0;i<prueba.length;i++)
			System.out.println(prueba[i]);
				
		panelPrincipal = new InterfazPrincipal("Prometeo 1.0.0", new Color(255,222,173), false);
			
		NativeInterface.runEventPump(); 
	}

}
