package es.mgamallo.prometeo;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Tiempo {

	private static final String folderPath = "e:/pruebaFotos1/";
	
	static String tiempoPontevedra = "http://servizos.meteogalicia.es/rss/predicion/rssLocalidades.action?idZona=36038&dia=-1&request_locale=gl";
	
	static String tiempoGalicia = "http://www.meteogalicia.es/web/predicion/cprazo/cprazoIndex.action?dia=0";
	static String tiempoGaliciaM = "http://www.meteogalicia.es/web/predicion/cprazo/getImaxeM.action?dia=0";
	static String tiempoGaliciaT = "http://www.meteogalicia.es/web/predicion/cprazo/getImaxeT.action?dia=0";
	static String tiempoGaliciaN = "http://www.meteogalicia.es/web/predicion/cprazo/getImaxeN.action?dia=0";
	
	static String temperaturas ="http://www.meteogalicia.es/web/predicion/cprazo/mapasTempIndex.action?dia=0&request_locale=gl";
	static String temperaturaMax = "http://www.meteogalicia.es/web/predicion/cprazo/getMapa.action?idVarMapa=340";
	static String temperaturaMin = "http://www.meteogalicia.es/web/predicion/cprazo/getMapa.action?idVarMapa=341";

	static String videoGif = "http://www.meteogalicia.es/datosred/satelite/ULTIMOS_30_DIAS/VIDEOS/20150129IR87.gif";
	
	String idMapaM = "srcImxM";
	String idMapaT = "srcImxT";
	String idMapaN = "srcImxN";
	
	String idMapaTempMin = "srcMin";
	String idMapaTempMax = "srcMax";
	
	Tiempo(){
		
		try {
		//	Document doc = Jsoup.connect(tiempoGalicia).get();
			
			// getImagen(doc, idMapaM);
			
			getImages("69.178.0.60:3128", "galiciaM.jpg");
			getImages(tiempoGaliciaT, "galiciaT.jpg");
			getImages(tiempoGaliciaN, "galiciaN.jpg");
			
			getImages(temperaturaMax, "galiciaM.jpg");
			getImages(temperaturaMin, "galiciaM.jpg");
			
			getImages(videoGif,"video.gif");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getImagen(Document doc, String id) throws IOException{
		
		Element img = doc.getElementById(id);
		String src = img.absUrl("src");
		
		getImages(src, id);
	}

	
    private static void getImages(String src, String id) throws IOException {

        String folder = null;

        /*
        //Exctract the name of the image from the src attribute
        int indexname = src.lastIndexOf("/");

        if (indexname == src.length()) {
            src = src.substring(1, indexname);
        }

        indexname = src.lastIndexOf("/");
        String name = src.substring(indexname, src.length());

        System.out.println(name);

        if(name.substring(name.length()-3).equals("jpg")){
            //Open a URL Stream
            URL url = new URL(src);
            InputStream in = url.openStream();

            OutputStream out = new BufferedOutputStream(new FileOutputStream( folderPath+ name));

            for (int b; (b = in.read()) != -1;) {
                out.write(b);
            }
            out.close();
            in.close();
        }
         */

        URL url = new URL(src);
        URLConnection conn = url.openConnection(Proxy.NO_PROXY);
        conn.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        
        /*
        InputStream in = url.openStream();
         */
        OutputStream out = new BufferedOutputStream(new FileOutputStream("prometeo/Htmls/tiempo/" + id));

        for (int b; (b = in.read()) != -1;) {
            out.write(b);
        }
        out.close();
        in.close();
        
        System.out.println("Descargado " + id);
    }

    static public void main(String args[]){
    	Tiempo t = new Tiempo();
    }
}
