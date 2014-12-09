package es.mgamallo.prometeo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 *
 * @author Coco
 */
public class Portapapeles {

	String valorTemporal = "";

	public void copiarAlPortapapeles(String documento) {
		Clipboard portapapeles = Toolkit.getDefaultToolkit()
				.getSystemClipboard();
		StringSelection texto = new StringSelection(documento);
		portapapeles.setContents(texto, texto);
	}
	
	public void setDatosOriginales() {
		Clipboard portapapeles = Toolkit.getDefaultToolkit()
				.getSystemClipboard();
		StringSelection texto = new StringSelection(valorTemporal);
		portapapeles.setContents(texto, texto);
	}

	public String getDatosPortapapelesTemporal() {
		Clipboard portapapeles = Toolkit.getDefaultToolkit()
				.getSystemClipboard();
		Transferable datoActual = portapapeles.getContents(this);

		DataFlavor flavorString;
		try {
			flavorString = new DataFlavor(
					"application/x-java-serialized-object; class=java.lang.String");

			if (datoActual.isDataFlavorSupported(flavorString)) {
				valorTemporal = (String) datoActual
						.getTransferData(flavorString);
				return valorTemporal;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}

	public String getDatosPaciente() {
		Clipboard portapapeles = Toolkit.getDefaultToolkit()
				.getSystemClipboard();
		Transferable datoActual = portapapeles.getContents(this);

		DataFlavor flavorString;
		try {
			flavorString = new DataFlavor(
					"application/x-java-serialized-object; class=java.lang.String");

			if (datoActual.isDataFlavorSupported(flavorString)) {
				return (String) datoActual.getTransferData(flavorString);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	
	public String getTipoDeSubida() {
		Clipboard portapapeles = Toolkit.getDefaultToolkit()
				.getSystemClipboard();
		Transferable datoActual = portapapeles.getContents(this);

		DataFlavor flavorString;
		try {
			flavorString = new DataFlavor(
					"application/x-java-serialized-object; class=java.lang.String");

			if (datoActual.isDataFlavorSupported(flavorString)) {
				return (String) datoActual.getTransferData(flavorString);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}
}
