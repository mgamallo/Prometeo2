package es.mgamallo.prometeo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class CargaEstadisticasActoFinal {

	static String rutaUrgencias = ":\\DIGITALIZACIÓN\\01 INFORMES URG (Colectiva)\\04 ASOCIADO";
	static String rutaDoc = ":\\DIGITALIZACIÓN\\00 DOCUMENTACION\\04 Asociado";
	static String rutaXedoc = ":\\DIGITALIZACIÓN\\00 DOCUMENTACION\\05 XEDOC ORIGINALES";
	
	File[] directoriosAnualesUrgencias;
	File[] directoriosAnualesDoc;
	File[] directoriosAnualesXedoc;

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new CargaEstadisticasActoFinal();

		
	}

	
	public CargaEstadisticasActoFinal() {
		// TODO Auto-generated constructor stub
		
		rutaUrgencias = Inicio.unidadHDDvirtual + rutaUrgencias;
		rutaDoc = Inicio.unidadHDDvirtual + rutaDoc;
		rutaXedoc = Inicio.unidadHDDvirtual + rutaXedoc;
		
		directoriosAnualesUrgencias = getDirectoriosAnuales(rutaUrgencias);
		directoriosAnualesDoc = getDirectoriosAnuales(rutaDoc);
		directoriosAnualesXedoc = getDirectoriosAnuales(rutaXedoc);
		
		ArrayList<EstadisticaDia> listaEstadisticaTotalDoc = new ArrayList<EstadisticaDia>();
		ArrayList<EstadisticaDia> listaEstadisticaTotalUrg = new ArrayList<EstadisticaDia>();
		ArrayList<EstadisticaDia> listaEstadisticaTotalXed = new ArrayList<EstadisticaDia>();
		
		for(int i=0;i<directoriosAnualesDoc.length;i++){
			String ruta = directoriosAnualesDoc[i].getAbsolutePath() + "\\estadistica.txt";
			
			String[] listaRegistros = leerTxt(ruta);
			
			ArrayList<EstadisticaDia> listaEstadisticaAnual = new ArrayList<EstadisticaDia>();
			for(int j=0;j<listaRegistros.length;j++){
				
				EstadisticaDia estadistica = new EstadisticaDia(listaRegistros[j]);
				listaEstadisticaAnual.add(estadistica);
				listaEstadisticaTotalDoc.add(estadistica);
			}
			
			System.out.println("Doc: " + listaEstadisticaAnual.size());
			for(int j=0;j<listaEstadisticaAnual.size();j++){
				System.out.println(listaEstadisticaAnual.get(j).fecha);
			}
			
			
		}
		
		crearTxt(listaEstadisticaTotalDoc, "Documentacion", rutaDoc);
		
		for(int i=0;i<directoriosAnualesUrgencias.length;i++){
			String ruta = directoriosAnualesUrgencias[i].getAbsolutePath() + "\\estadistica.txt";
			
			String[] lista = leerTxt(ruta);
			
			ArrayList<EstadisticaDia> listaEstadisticaAnual = new ArrayList<EstadisticaDia>();
			for(int j=0;j<lista.length;j++){
				
				EstadisticaDia estadistica = new EstadisticaDia(lista[j]);
				listaEstadisticaAnual.add(estadistica);
				listaEstadisticaTotalUrg.add(estadistica);
			}
			
			System.out.println("Urgencias: " + listaEstadisticaAnual.size());
			for(int j=0;j<listaEstadisticaAnual.size();j++){
				System.out.println(listaEstadisticaAnual.get(j).fecha);
			}
		}
		
		crearTxt(listaEstadisticaTotalUrg, "Urgencias", rutaUrgencias);
		
		for(int i=0;i<directoriosAnualesXedoc.length;i++){
			String ruta = directoriosAnualesXedoc[i].getAbsolutePath() + "\\estadistica.txt";
			
			String[] lista = leerTxt(ruta);
			
			ArrayList<EstadisticaDia> listaEstadisticaAnual = new ArrayList<EstadisticaDia>();
			for(int j=0;j<lista.length;j++){
				
				EstadisticaDia estadistica = new EstadisticaDia(lista[j]);
				listaEstadisticaAnual.add(estadistica);
				listaEstadisticaTotalXed.add(estadistica);
			}
			
			System.out.println("Xedoc: " + listaEstadisticaAnual.size());
			for(int j=0;j<listaEstadisticaAnual.size();j++){
				System.out.println(listaEstadisticaAnual.get(j).fecha);
			}
		}
		
		crearTxt(listaEstadisticaTotalXed, "Xedoc", rutaXedoc);
		

	}
	
	
	private File[] getDirectoriosAnuales(String ruta){
		
		// Devuelve la ruta a las carpetas anuales
		
		// aplicar un filtro de solo carpetas
		
		File[] carpetas = new File(ruta).listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File file) {
				// TODO Auto-generated method stub
				return file.isDirectory();
			}
		});
		int numAños = 0;
		
		System.out.println("Tamaño de las carpetas en: ");
		System.out.println(ruta);
		System.out.println(carpetas.length);
		
		for(int i=0;i<carpetas.length;i++){
			if(carpetas[i].getName().length() == 4 && 
					(carpetas[i].getName().charAt(0) == '2' && carpetas[i].getName().charAt(1) == '0')){
				numAños++;
			}
		}
		
		File[] carpetasFinales = new File[numAños];
		for(int i=0;i<carpetas.length;i++){
			if(carpetas[i].getName().length() == 4 && 
					(carpetas[i].getName().charAt(0) == '2' && carpetas[i].getName().charAt(1) == '0')){
				
				carpetasFinales[i] = carpetas[i];
			}
		}
		
		
		return carpetasFinales;
	}
	
	
	
	private String[] leerTxt(String ruta){
		
		ArrayList <String> lista = new ArrayList<String>();
		
		FileReader f = null;
		BufferedReader b = null;
		
		try {
			
			String cadena;
			
			f = new FileReader(ruta);
			b = new BufferedReader(f);
			while((cadena = b.readLine())!=null){
				lista.add(cadena);
			}
			b.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				b.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String[] cadenaSinOrdenar = new String[lista.size()];
		
		for(int i=0;i<lista.size();i++){
			cadenaSinOrdenar[i] = lista.get(i);
		}
		
		Arrays.sort(cadenaSinOrdenar);
		
		return cadenaSinOrdenar;
	}

	private void crearTxt(ArrayList<EstadisticaDia> lista, String nombreCarpeta, String ruta){
		
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(ruta + "\\" + nombreCarpeta + ".txt" );

            
            pw = new PrintWriter(fichero);
            for(int i=0;i<lista.size();i++){
            	String cadena = lista.get(i).fecha + ";" + lista.get(i).numeroFicheros
            			+ ";" + lista.get(i).diaSemana;
            	System.out.println(cadena);
            	pw.println(cadena);
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
}
