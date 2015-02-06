package es.mgamallo.prometeo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Txt {
	static public String[] obtenerUsuarios(String ruta){
		return LeerArchivos.obtenerUsuarios(ruta);
	}
	
	
	static public void escribirNormasTxt(Norma norma){
		
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            

            if(new File(norma.rutaNormaTxt).exists()){
            	
            	fichero = new FileWriter(norma.rutaNormaTxt,false);
            	
                pw = new PrintWriter(fichero);

		         String servicios = "";
                
		         for(int i=0;i<norma.servicios.size();i++){
		        	 servicios += norma.servicios.get(i);
		        	 if((i+1) != norma.servicios.size()){
		        		 servicios += "-";
		        	 }
		         }

                pw.println(norma.fecha);
                pw.println("@0@");
                pw.println(norma.rutaImagen);
                pw.println("@0@");
                pw.println(servicios);
                pw.println("@0@");
                pw.println(norma.texto);
                pw.println("@0@");
      
            }
            
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}

	//  Metodo para borrar
	static public void escribirNormasTxt(String rutaCompleta, String[] campos){
		
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(rutaCompleta);
            pw = new PrintWriter(fichero);


            pw.println(campos[0]);
            pw.println("@0@");
            pw.println("No");
            pw.println("@0@");
            pw.println(campos[3]);
            pw.println("@0@");
            pw.println(campos[1]);
            pw.println("@0@");
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}
	
	
	static public ArrayList<Norma> leerNormasTxt(String rutaCarpeta){
		
		ArrayList<Norma> listaNormas = new ArrayList<Norma>();
		
		File directorio = new File(rutaCarpeta);
		
		FilenameFilter filtro = new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.toLowerCase().endsWith(".txt");
			}
		};
		
		File[] txts = directorio.listFiles(filtro);
		
		
		for(int i=0;i<txts.length;i++){
			listaNormas.add(leerNormaTxt(txts[i].getAbsolutePath().toString()));
		}

		
		return listaNormas;
	}
	
	static private Norma leerNormaTxt(String ruta){
	      File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      
	      Norma norma = new Norma();
	 
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File (ruta);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	 
	         // Lectura del fichero
	         String linea;
	         String texto = "";
	         while((linea=br.readLine())!=null){
	        	 // System.out.println(linea);
	        	 texto += linea;
	         }
	         
	      //   System.out.println(texto);
	         
	         String[] campos = texto.split("@0@");
	         
	         if(campos.length == 4){
		         norma.fecha = campos[0];
		         norma.rutaImagen = "No";
		         norma.texto = campos[3];
		         
		         String[] servicios = campos[2].split("-");
		         ArrayList<String> listaServicios = new ArrayList<String>();
		         for(int i=0;i<servicios.length;i++){
		        	 listaServicios.add(servicios[i]);
		         }
		         norma.servicios = listaServicios;
	         }
	         norma.rutaNormaTxt = archivo.getAbsolutePath();

	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	      return norma;
	}
	
	public static void main(String args[]){
		/*
		Norma norma = new Norma();
		
		norma = leerNormaTxt("C:\\Desarrollo\\git\\prometeo\\prometeo\\Prometeo\\Prometeo\\txt\\Normas\\N 0006.txt");
		
		System.out.println(norma.fecha);
		System.out.println(norma.rutaImagen);
		System.out.println(norma.servicios);
		System.out.println(norma.texto);
		 */
		
		ArrayList<Norma> listaNormas = new ArrayList<Norma>();
		
		listaNormas = Txt.leerNormasTxt("C:\\Desarrollo\\git\\prometeo\\prometeo\\Prometeo\\Prometeo\\txt\\Normas\\");
		
		System.out.println(listaNormas.size());
		System.out.println(listaNormas.get(6).texto);
	}
}
