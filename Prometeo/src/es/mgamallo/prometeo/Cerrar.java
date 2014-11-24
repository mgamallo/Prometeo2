package es.mgamallo.prometeo;

import java.io.IOException;

public class Cerrar {

	static public void cerrarTodo(){
		String cmd1 = "taskkill.exe /F /IM AcroRd32.exe /T";
		String cmd2 = "taskkill.exe /F /IM iexplore.exe /T";
		
		Process hijo1, hijo2;
		
		try {
			hijo1 = Runtime.getRuntime().exec(cmd1);
			hijo2 = Runtime.getRuntime().exec(cmd2);
			
			hijo1.waitFor();
			hijo2.waitFor();
			
			Thread.sleep(500);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
