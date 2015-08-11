package es.mgamallo.prometeo;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CargaEstadisticasInicio {

	
	ArrayList<String> rutasMes = new ArrayList<String>();
	ArrayList<String> rutasAños = new ArrayList<String>();
	
	public CargaEstadisticasInicio() {
		// TODO Auto-generated constructor stub
		
		Fecha fecha = new Fecha();
		
		encontrarCarpeta(fecha);
		
	}
	
	public void encontrarCarpeta(Fecha fecha){
		
		boolean recontabilizar = false;
		
		// Carpeta Ianus
		
		String ruta =  Inicio.rutaEstadisticaIanus + fecha.año + "\\" + fecha.nombreMesYnumero 
				+ "\\" + fecha.dia;
		
		System.out.println(ruta);
		
		File fichero = new File(ruta);
		
		
		if(!fichero.exists()){
			contabiliza3DiasAnteriores(Inicio.rutaEstadisticaIanus, fecha);
			fichero.mkdirs();
			recontabilizar = true;
		}
		
		
		// Carpeta Urgencias
		
		ruta =  Inicio.rutaEstadisticaUrg + fecha.año + "\\" + fecha.nombreMesYnumero 
				+ "\\" + fecha.dia;
		
		System.out.println(ruta);
		
		fichero = new File(ruta);
		
		if(!fichero.exists()){
			contabiliza3DiasAnteriores(Inicio.rutaEstadisticaUrg, fecha);
			fichero.mkdirs();
			recontabilizar = true;
		}
		
		
		// Carpeta Xedoc
		
		ruta =  Inicio.rutaEstadisticaXedoc + fecha.año + "\\" + fecha.nombreMesYnumero 
				+ "\\" + fecha.dia;
		
		System.out.println(ruta);
		
		fichero = new File(ruta);
		
		
		if(!fichero.exists()){
			contabiliza3DiasAnteriores(Inicio.rutaEstadisticaXedoc, fecha);
			fichero.mkdirs();
			recontabilizar = true;
		}

		if(recontabilizar){
			new CargaEstadisticasActoFinal();
		}
			
			
	}

	
	private void contabiliza3DiasAnteriores(String ruta, Fecha fecha){
		
		int dia = fecha.dia;
		int mes = fecha.mes;
		int año = fecha.año;
		
		String rutaInicio =  ruta + año + "\\" + fecha.nombreMesYnumero;
		String rutaInicioAño =  ruta + año;
				
		System.out.println(rutaInicio);
		
		File directorio = new File(rutaInicio);
		int diaAbuscar = dia;
		
		rutasMes.add(rutaInicio);
		rutasAños.add(rutaInicioAño);
		
		
		for(int i=1;i<=3;i++){
			File[] carpetas = directorio.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					// TODO Auto-generated method stub
					return pathname.isDirectory();
				}
			});
			
			int dias[] = new int[carpetas.length];
			for(int j=0;j<carpetas.length;j++){
				dias[j] = Integer.valueOf(carpetas[j].getName());
			}
			
			diaAbuscar = diaAbuscar - 1;
			boolean encontrado = false;
			while(!encontrado && diaAbuscar > 0){
				for(int z = 0;z < carpetas.length;z++){
					if(dias[z] == diaAbuscar){
						encontrado = true;
						break;
					}
				}
				if(encontrado){
					break;
				}
				else{
					diaAbuscar--;
				}
			}
			
			System.out.println("El día a buscar es... " + diaAbuscar);
			if(diaAbuscar >0){
				// contabilizaDia
				EstadisticaDia estadistica = getNumeroDeDocumentosSubidosDia(new File(rutaInicio + "\\" + diaAbuscar));
				estadistica.imprimeDatos();
			}
			else{
				
				mes--;
				if(mes <0){
					año--;
					mes = 11;
					rutaInicioAño =  ruta + año;
					rutasAños.add(rutaInicioAño);
				}
				
				rutaInicio =  ruta + año + "\\" + getNombreMesYNumero(mes);

				rutasMes.add(rutaInicio);
				
				directorio = new File(rutaInicio);
				diaAbuscar = 32;
				i--;
				System.out.println(rutaInicio);
			}
		}
		
		
		for(int i=0;i<rutasMes.size();i++){
			
			System.out.println("Rutas carpetas mes...");
			System.out.println(rutasMes.get(i));
			String registros = creaEstadisticaMensual(new File(rutasMes.get(i)));
			creaTxtEstadisticaMensual(new File(rutasMes.get(i)), registros);
		}
		
		
		
		for(int i=0;i<rutasAños.size();i++){
			System.out.println("Rutas carpetas años...");
			System.out.println(rutasAños.get(i));
			String registros = creaEstadisticaAnual(new File(rutasAños.get(i)));
			creaTxtEstadisticaAnual(new File(rutasAños.get(i)), registros);
		}
		
	}
	
	
	private EstadisticaDia getNumeroDeDocumentosSubidosDia( File rutaDia){
		
		System.out.println(rutaDia);
		
		int numeroArchivos = 0;
		EstadisticaDia estadisticaDia;
		
		String dia = rutaDia.getName();
		String mes = rutaDia.getParentFile().getName();
		
		mes = mes.substring(0,2);
		
		String año = rutaDia.getParentFile().getParentFile().getName();
				
		
		int diaI = Integer.valueOf(dia);
		int mesI = Integer.valueOf(mes);
		int añoI = Integer.valueOf(año);
		
		if(dia.length() <2){
			dia = "0" + dia;
		}
		if(mes.length() <2){
			dia = "0" + mes;
		}
		
		String fecha = año + mes + dia;
		
		Calendar calendario = new GregorianCalendar(añoI, mesI - 1, diaI);
		int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
		
		if(rutaDia.exists()){
			numeroArchivos = getNumeroFicherosDirectorio( rutaDia);
			
			estadisticaDia = new EstadisticaDia(fecha, String.valueOf(numeroArchivos), getDiaSemana(diaSemana));
			crearFicheroTxt(rutaDia,estadisticaDia);
			
			return estadisticaDia;
		}
			
		
		return null;
	}
	
	
	private int getNumeroFicherosDirectorio(File directorio){
		
		int numeroFicheros = 0;
		
		File fichero[] = directorio.listFiles();
		for(int i=0;i<fichero.length;i++){
			if(fichero[i].isDirectory()){
				numeroFicheros = numeroFicheros + getNumeroFicherosDirectorio(fichero[i]);
			}
			else{
				numeroFicheros++;
			}
		}
		
		return numeroFicheros;
	}
	
	
	private void crearFicheroTxt(File directorio, EstadisticaDia estadistica){
		
		String ruta = directorio.getAbsolutePath();
		
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(ruta + "\\estadistica.txt");
            
            pw = new PrintWriter(fichero);
            pw.println(estadistica.fecha + ";" + estadistica.numeroFicheros + ";" + estadistica.diaSemana);
 
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
	
	
	private String creaEstadisticaMensual(File carpetaMes){
		File[] carpetasDias = carpetaMes.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				return pathname.isDirectory();
			}
		});
		
		String registros = "";
		
		for(int i=0;i<carpetasDias.length;i++){
			File archivo = null;
		    FileReader fr = null;
		    BufferedReader br = null;
		 

		      
		      try {
		    	  
				if(!carpetasDias[i].getName().contains("estadis") && !carpetasDias[i].getName().contains(".db")){
			         archivo = new File (carpetasDias[i].getAbsoluteFile() + "\\estadistica.txt");
			         if(archivo.exists()){
			             fr = new FileReader (archivo);
				         br = new BufferedReader(fr);
				 
				         // Lectura del fichero
				         String linea;
				         while((linea=br.readLine())!=null){
				        	registros = registros + linea + "\r\n"; 
				            System.out.println("Leida esta linea " + linea);
				         }
			         }
			 
				}
		    	  
		         // Apertura del fichero y creacion de BufferedReader para poder
		         // hacer una lectura comoda (disponer del metodo readLine()).

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

		}
		
		registros = registros.substring(0,registros.length()-2);
		
		return registros;
	}
	
	private void creaTxtEstadisticaMensual(File carpetaMes, String registros){
		
		String ruta = carpetaMes.getAbsolutePath();
		
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(ruta + "\\estadistica.txt");
            
            pw = new PrintWriter(fichero);
            pw.println(registros);
 
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
	
	
	private String creaEstadisticaAnual(File carpetaAño){
		
		String registros = "";
		
		File[] carpetasMeses = carpetaAño.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				return pathname.isDirectory();
			}
		});
		
		for(int i=0;i<carpetasMeses.length;i++){
			File archivo = null;
		    FileReader fr = null;
		    BufferedReader br = null;
		 

		      
		      try {
		    	  
				if(!carpetasMeses[i].getName().contains("estadis") && !carpetasMeses[i].getName().contains(".db")){
			         archivo = new File (carpetasMeses[i].getAbsoluteFile() + "\\estadistica.txt");
			         fr = new FileReader (archivo);
			         br = new BufferedReader(fr);
			 
			         // Lectura del fichero
			         String linea;
			         while((linea=br.readLine())!=null){
			        	registros = registros + linea  + "\r\n"; 
			            System.out.println("Leida esta linea " + linea);
			         }
				}
		    	  
		         // Apertura del fichero y creacion de BufferedReader para poder
		         // hacer una lectura comoda (disponer del metodo readLine()).

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
		}
		
		registros = registros.substring(0,registros.length()-2);
		
		return registros;
	}
	
	private void creaTxtEstadisticaAnual(File carpetaAño, String registros){
		String ruta = carpetaAño.getAbsolutePath();
		
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(ruta + "\\estadistica.txt");
            
            pw = new PrintWriter(fichero);
            pw.println(registros);
 
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
	
	
	private String getNombreMesYNumero(int mes){
		
		if( mes < 0){
			mes = 11;
		}
		
		String nombre = "";
		
		switch(mes){
		case 0:	nombre = "01 Enero";break;
		case 1:	nombre = "02 Febrero";break;
		case 2:	nombre = "03 Marzo";break;
		case 3:	nombre = "04 Abril";break;
		case 4:	nombre = "05 Mayo";break;
		case 5:	nombre = "06 Junio";break;
		case 6:	nombre = "07 Julio";break;
		case 7:	nombre = "08 Agosto";break;
		case 8:	nombre = "09 Septiembre";break;
		case 9:	nombre = "10 Octubre";break;
		case 10:	nombre = "11 Noviembre";break;
		case 11:	nombre = "12 Diciembre";break;
		}
		
		return nombre;
	}
	
	
	private String getDiaSemana(int d){
		
		String cadena = "";
		switch (d) {
		case 1:
			cadena = "Domingo";
			break;
		case 2:
			cadena = "Lunes";
			break;
		case 3:
			cadena = "Martes";
			break;
		case 4:
			cadena = "Miércoles";
			break;
		case 5:
			cadena = "Jueves";
			break;
		case 6:
			cadena = "Viernes";
			break;
		case 7:
			cadena = "Sábado";
			break;

		default:
			break;
		}
		
		System.out.println("El dia es " + cadena);
		
		return cadena;
	}
	
	static public void main(String args[]){
		new CargaEstadisticasInicio();
	}
}
