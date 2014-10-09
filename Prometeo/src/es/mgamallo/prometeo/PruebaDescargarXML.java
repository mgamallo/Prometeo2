package es.mgamallo.prometeo;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
 
/**
 * Ejemplo de descarga de un fichero de imagen desde la web.
 * 
 * @author chuidiang
 * 
 */
public class PruebaDescargarXML {
 
    /**
     * Descarga un fichero jpeg y lo guarda en e:/foto.jpg
     * 
     * @param args
     */
    public static void main(String[] args) {
        try {
            // Url con la foto
            URL url = new URL(
                    "http://servizos.meteogalicia.es/rss/predicion/rssLocalidades.action?idZona=36038&dia=-1&request_locale=es");
 
            // establecemos conexion
            URLConnection urlCon = url.openConnection();
 
            // Sacamos por pantalla el tipo de fichero
            System.out.println(urlCon.getContentType());
 
            // Se obtiene el inputStream de la foto web y se abre el fichero
            // local.
            InputStream is = urlCon.getInputStream();
            FileOutputStream fos = new FileOutputStream("Prometeo/temp/tiempo.xml");
 
            // Lectura de la foto de la web y escritura en fichero local
            byte[] array = new byte[1000]; // buffer temporal de lectura.
            int leido = is.read(array);
            while (leido > 0) {
                fos.write(array, 0, leido);
                leido = is.read(array);
            }
 
            // cierre de conexion y fichero.
            is.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
}
