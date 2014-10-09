package es.mgamallo.prometeo;

import java.awt.Color;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;


public class TestWebIanus {
	
	/*
	public static void main(String[] args) {
		NativeInterface.open();
		UIUtils.setPreferredLookAndFeel();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame = new JFrame("DJ Native Swing Test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane()
						.add(createContent(new Color(255,246,143)), BorderLayout.CENTER);
				frame.setSize(800, 600);
				frame.setLocationByPlatform(true);
				frame.setUndecorated(true);
				frame.setVisible(true);
				
			}
		});
		NativeInterface.runEventPump();
	}
	*/
	
	public static void main(String[] args){
		NativeInterface.open();
		WebIanus web1 = new WebIanus("Ianus 1", new Color(255,246,143));
		
		WebIanus web2 = new WebIanus("Ianus 2",new Color(255,222,173));

	    NativeInterface.runEventPump();
	}
}
