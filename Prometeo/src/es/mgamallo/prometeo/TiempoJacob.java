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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class TiempoJacob {

    // Declaración de la variable que almacenará el objeto OLE
    static ActiveXComponent IE;
    
    ArrayList<PaginaMeteogalicia> links = new ArrayList<PaginaMeteogalicia>();
    
    TiempoJacob(){
        // Inicialización de la librería
        ComThread.InitSTA();
        // Creacón del objeto
        IE = new ActiveXComponent("InternetExplorer.Application");
        // Cambio de una propiedad del objeto
        Dispatch.put(IE, "Visible", false);

        
        links.add(new PaginaMeteogalicia("galiciaM.jpg", 
        		"http://www.meteogalicia.es/web/predicion/cprazo/getImaxeM.action?dia=0"));
        links.add(new PaginaMeteogalicia("galiciaT.jpg", 
        		"http://www.meteogalicia.es/web/predicion/cprazo/getImaxeT.action?dia=0"));
        links.add(new PaginaMeteogalicia("galiciaN.jpg", 
        		"http://www.meteogalicia.es/web/predicion/cprazo/getImaxeN.action?dia=0"));
        
        links.add(new PaginaMeteogalicia("galiciaTMax.jpg", 
        		"http://www.meteogalicia.es/web/predicion/cprazo/getMapa.action?idVarMapa=340"));
        links.add(new PaginaMeteogalicia("galiciaTMin.jpg", 
        		"http://www.meteogalicia.es/web/predicion/cprazo/getMapa.action?idVarMapa=341"));
        
        links.add(new PaginaMeteogalicia("video.gif", 
        		"http://www.meteogalicia.es/datosred/satelite/ULTIMOS_30_DIAS/VIDEOS/20150130IR87.gif"));
        
        
        for(int i=0;i<links.size();i++){
            Dispatch.call(IE, "Navigate", links.get(i).ruta);
            
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
              
              guardaDelClipBoard(links.get(i).nombreImagen);
        }
        // Imagen galicia mañana	

          
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
            if(!nombre.equals("video.gif")){
            	ImageIO.write(image, "jpg", file);
            }else{
            	ImageIO.write(image, "gif", file);
            }
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

class PaginaMeteogalicia{
	String nombreImagen = "";
	String ruta = "";
	
	public PaginaMeteogalicia(String nombre, String ruta) {
		// TODO Auto-generated constructor stub
		this.nombreImagen = nombre;
		this.ruta = ruta;
	}
}

