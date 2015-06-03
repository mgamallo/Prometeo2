package es.mgamallo.prometeo;

import java.awt.Toolkit;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class HiloNHC extends Thread{

	final String introduceNhc1;
	final String nombreIanus;
	final String nhc;
	int retardo;
	ActiveXComponent ianus;
	
	HiloNHC(ActiveXComponent ianus,String introduceNhc1, String nombreIanus, int retardo, String nhc){
		this.ianus = ianus;
		this.introduceNhc1 = introduceNhc1;
		this.nombreIanus = nombreIanus;
		this.retardo = retardo;
		this.nhc = nhc;
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
					System.out.println("Empieza el hilo de introducir nhc " + nombreIanus); 
					Thread.sleep(1000); 
					
					Dispatch documento = Dispatch.call(ianus, "document").toDispatch();
				    Dispatch framePrincipal = Dispatch.call(documento,"getElementById","framesetGlobal").toDispatch();
				      Variant rowsVariant = Dispatch.get(framePrincipal,"rows");
				      String rows = rowsVariant.getString();
				      System.out.println("Rows vale... " + rows );
				      
				      if(rows.contains("0,*,0")){
				    	  
				  		for(int i=0;i<5;i++){
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Toolkit.getDefaultToolkit().beep();
						}
						Toolkit.getDefaultToolkit().beep();
				    	  
				    	  GestionJacob.reIntroduceNHC(nombreIanus);
				      }
				      else{
					       Dispatch.call(ianus, "navigate",introduceNhc1);
				      }




				      

					// Thread.sleep(Retardos.retardoIntroducirNHC);
					// Dispatch.call(ianus, "navigate",CadenasJavascript.introducirNHC2(nhc));
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
