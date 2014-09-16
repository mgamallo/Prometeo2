package es.mgamallo.prometeo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeerArchivos {
	
	static public String[] obtenerUsuarios(String ruta){
		File archivo = new File(ruta);
		
		FileReader fr = null;
		BufferedReader br = null;
		
		ArrayList<String> usuarios = new ArrayList<String>();
		
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String linea;

			while((linea=br.readLine()) != null){
				System.out.println(linea);
				usuarios.add(linea);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try{
				if(null != fr){
					fr.close();
				}
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		
		int tamañoArray = usuarios.size();
		String arrayUsuarios[] = new String[tamañoArray];
		for(int i=0;i<tamañoArray;i++){
			arrayUsuarios[i] = usuarios.get(i).toString();
		}
		
		
		return arrayUsuarios;
	}

	static public String obtenerHtml(String ruta) throws IOException{
		File archivo = new File(ruta);
		
		FileReader fr = null;
		BufferedReader br = null;
		
		String texto = "";
		
		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String linea;

			while((linea=br.readLine()) != null){
				System.out.println(linea);
				texto += linea;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try{
				if(null != fr){
					fr.close();
				}
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		System.out.println(texto);
		return texto;
	}
	
	static public void main(String args[]){

			try {
				LeerArchivos.obtenerHtml("Htmls/control.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}
