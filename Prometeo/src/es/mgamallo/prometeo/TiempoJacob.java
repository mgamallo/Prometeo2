package es.mgamallo.prometeo;

//  http://bytes.com/topic/javascript/answers/161822-help-copy-image-clipboard
//  http://forum.codecall.net/topic/58122-java-copy-image-from-clipboard/


import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class TiempoJacob {

    // Declaración de la variable que almacenará el objeto OLE
    static ActiveXComponent IE;
    
    TiempoJacob(){
        // Inicialización de la librería
        ComThread.InitSTA();
        // Creacón del objeto
        IE = new ActiveXComponent("InternetExplorer.Application");
        // Cambio de una propiedad del objeto
        Dispatch.put(IE, "Visible", false);


        // Imagen galicia mañana	
        Dispatch.call(IE, "Navigate", "http://www.meteogalicia.es/web/predicion/cprazo/getImaxeM.action?dia=0");
        
        Variant cadena = Dispatch.call(IE, "readyState");
        
        while(true){
      	  
      	  cadena = Dispatch.call(IE, "readyState");
      	  System.out.println(cadena.toString());
      	  if(Integer.valueOf(cadena.toString()) ==4){
      		  System.out.println("CArgado");
      		  break;
      	  }
        }
        
        try {
    		Thread.sleep(1000);
    	} catch (InterruptedException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        
        String codigo = ""
          		+ "function CopyToClip(){"
          		+     "var div = document.getElementsByTagName('img')[0];"
          	//		+ "alert(div.innerHTML);"
          			+ "div.contentEditable = 'true';"
          			+ "var controlRange;"
          			+ "if (document.body.createControlRange) {"
          				+ "controlRange = document.body.createControlRange();"
          				+ "controlRange.addElement(div);"
          				+ "controlRange.execCommand('Copy');"
          			+ "}"
          			+ "div.contentEditable = 'false';"
          			+ ""
          		+ "}"
          		+ "CopyToClip();"
          			;
          
          Dispatch.call(IE, "Navigate", "javascript:" + codigo);
          
          //Espera de 5 segundos
          try {
              Thread.sleep(2000);
          } catch (InterruptedException ex) {
              Logger.getLogger(TiempoJacob.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          guardaDelClipBoard("galiciaM.jpg");
          
          // Llamada al método que cierra la aplicación
          Dispatch.call(IE, "Quit");
    }
    
    
    
    public static void main(String[] args){
    	new TiempoJacob();
  }
    
    private void guardaDelClipBoard(String nombre){
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            //Get data from clipboard and assign it to an image.
            //clipboard.getData() returns an object, so we need to cast it to a BufferdImage.
            BufferedImage image = (BufferedImage)clipboard.getData(DataFlavor.imageFlavor);
            
            //file that we'll save to disk.
            File file = new File("prometeo/htmls/tiempo/" + nombre);
            
            //class to write image to disk.  You specify the image to be saved, its type,
            // and then the file in which to write the image data.
            ImageIO.write(image, "jpg", file);
        }
        //getData throws this.
        catch(UnsupportedFlavorException ufe) {
            ufe.printStackTrace();
        }
        
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}


