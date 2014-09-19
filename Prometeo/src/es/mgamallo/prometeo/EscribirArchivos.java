package es.mgamallo.prometeo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EscribirArchivos {
	public static void escribeHtml(String textoHtml){
		FileWriter fichero = null;
		PrintWriter pw = null;
		
		try {
			fichero = new FileWriter("K:/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/usuarios/Digitalizacion/usuariosSesion.html");
			pw = new PrintWriter(fichero);
			pw.print(textoHtml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null != fichero){
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
