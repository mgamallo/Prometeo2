package es.mgamallo.prometeo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class EscribirArchivos {
	public static void escribeHtml(String textoHtml, String ruta){
		FileWriter fichero = null;
		PrintWriter pw = null;
		
		System.out.println(ruta);
		
		try {
			// fichero = new FileWriter("d:/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/usuarios/Digitalizacion/usuariosSesion.html");
			fichero = new FileWriter(Inicio.unidadHDDejecutable + ruta );
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
	
	
	public static void escribeHtmlUtf(String textoHtml, String ruta){

		Writer out = null;
		
		try {
			FileOutputStream fos = new FileOutputStream(ruta);
			OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
					
			out = new BufferedWriter(osw);
			out.write(textoHtml);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
	
	static public void main(String args[]){
		EscribirArchivos.escribeHtmlUtf("Esto es un ejémplo", "e:/ejemplo.txt");
	}
}
