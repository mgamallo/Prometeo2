package es.mgamallo.prometeo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class HiloAsociar extends Thread{
	int retardo;
	
	public HiloAsociar(int retardo){
		this.retardo = retardo;
	}
	
	public void run(){
		try {
			Thread.sleep(retardo);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
