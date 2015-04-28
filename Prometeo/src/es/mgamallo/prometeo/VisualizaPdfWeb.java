package es.mgamallo.prometeo;


import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VisualizaPdfWeb {

	public static ActiveXComponent IE;

	public VisualizaPdfWeb(String ruta) {
		cerrarWebPdf();
		IE = new ActiveXComponent("InternetExplorer.Application");
		maquetar();
		IE.invoke("navigate", ruta);
	}



	private static void cerrarWebPdf() {

		ActiveXComponent oShell = new ActiveXComponent("Shell.Application");
		ActiveXComponent oWindows = oShell.invokeGetComponent("Windows");

		int iCount = oWindows.getProperty("Count").getInt();
		System.out.println("iCount: " + iCount);

		for (int i = iCount - 1; i >= 0; i--) {
			System.out.println("Comenzando... " + i);
			
			try {
				ActiveXComponent oWindow = oWindows.invokeGetComponent("Item",
						new Variant(i));
				String sLocName = oWindow.getProperty("LocationName").getString();
				String sFullName = oWindow.getProperty("FullName").getString();
				boolean isIE = sFullName.toLowerCase().endsWith("iexplore.exe");
				boolean bVisible = oWindow.getProperty("Visible").getBoolean();
				// System.out.println("i: " + i + ", loc: " + sLocName + ", name: "
				// + sFullName + ", isIE: " + isIE + ", vis: " + bVisible);
				if (sLocName.toLowerCase().contains("pdf") && isIE) {
					System.out.println("i: " + i + ", loc: " + sLocName
							+ ", name: " + sFullName + ", isIE: " + isIE
							+ ", vis: " + bVisible);
					System.out.println("Cerrando ventana");
					oWindow.invoke("quit");
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Error localizando pdf");
				e.printStackTrace();
			}
		}
	}
	
	private static void maquetar(){
		Dispatch.put(IE, "height", 1139);
		Dispatch.put(IE, "top", 119);
		Dispatch.put(IE, "left", 0);
		Dispatch.put(IE, "width", 850);
		Dispatch.put(IE, "AddressBar","false");
	}
	
	public static void main(String[] args) {
		new VisualizaPdfWeb("C:/Users/mgamgul1/Documents/area trabajo/PruebasJacob/ANULADO.pdf");
	}
}
