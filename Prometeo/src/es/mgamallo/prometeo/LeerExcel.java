package es.mgamallo.prometeo;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;
import jxl.biff.WorkbookMethods;
import jxl.read.biff.BiffException;

public class LeerExcel {

	
	private String[] servicios;
	private String[] nombres;
	private String[][] tablaDocumentos;
	public String[][] asociacionesDocumentos;
	private String[][] habituales;
	private int[][] coordenadasAsociar;
	
	public String[][] tablaNormas;
	
	private Nodo[] nodos;
	
	public void leer(String archivoFuente){
		
		WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setEncoding("ISO-8859-1");
        wbSettings.setLocale(new Locale("es", "ES"));
        wbSettings.setExcelDisplayLanguage("ES"); 
        wbSettings.setExcelRegionalSettings("ES"); 
        wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
        
        Workbook archivoExcel;
		try {
			archivoExcel = Workbook.getWorkbook(new File(archivoFuente));
			
	        Sheet hoja = archivoExcel.getSheet(0);
	        
	        //	Obtiene los nombres
	        int numColumnas = 0;
	        int numFilas = 0;
	        
	        while(!hoja.getCell(numColumnas,0).getContents().toString().equals("@finH")){
	        	numColumnas++;
	        }
	        
	        while(!hoja.getCell(0,numFilas).getContents().toString().equals("@finV")){
	        	numFilas++;
	        }
	        
	        System.out.println("Num filas = " + numFilas + ". Num columnas = " + numColumnas);
	        
	        servicios = new String[numColumnas - 13];
	        nombres = new String[numFilas - 1];
	        
	        tablaDocumentos = new String[nombres.length + 1][servicios.length +1];
	        
	        //	Tabla de todos los documentos
	        for(int fila=0;fila<nombres.length;fila++){
	        	for(int columna=0;columna<servicios.length;columna++){
	        		tablaDocumentos[fila][columna] = hoja.getCell(columna+2,fila+1).getContents().toString();
	        	}
	        }
	        
	        /*
	        for(int fila=0;fila<nombres.length;fila++){
	        	for(int columna=0;columna<servicios.length;columna++){
	        		System.out.print(tablaDocumentos[fila][columna]);
	        	}
	        	System.out.println();
	        }
	        */
	        
	        // Lista de todos los servicios
	        for(int columna = 0;columna<servicios.length;columna++){
	        	servicios[columna] = hoja.getCell(columna + 2,0).getContents().toString();
	        }
	        
	        // Lista de todos los nombres
	        for(int fila = 0;fila<nombres.length;fila++){
	        	nombres[fila] = hoja.getCell(0,fila + 1).getContents().toString();
	        }
	        
	        /*
	        for(int columna = 0;columna<servicios.length;columna++){
	        	System.out.println(servicios[columna]); 
	        }
	        for(int fila = 0;fila<nombres.length;fila++){
	        	System.out.println(nombres[fila]); 
	        }
	        */
	        
	        //  Habituales **********************************************
	        // 0..... comunes
	        // 1..... habituales 1
	        // 2..... habituales 2
	        // 3..... habituales urgencias
	        
	        habituales = new String[nombres.length][4];
	        
	        for(int fila = 0; fila < nombres.length;fila++){
	        	for(int columna = 0;columna < 4; columna++){
	        		habituales[fila][columna] = hoja.getCell(columna + servicios.length + 2 + 7,fila +1).getContents().toString();
	        	}
	        }
	        
	        /*
	        for(int fila = 0; fila < nombres.length;fila++){
	        	for(int columna = 0;columna < 4; columna++){
	        		System.out.print((fila+2) + " " + habituales[fila][columna]);
	        	}
	        	System.out.println();
	        }
	        */
	        
	        //	Asociaciones
	        asociacionesDocumentos = new String[nombres.length][6];
	        
	        for(int fila = 0; fila < nombres.length;fila++){
	        	for(int columna = 0;columna < 6; columna++){
	        		asociacionesDocumentos[fila][columna] = hoja.getCell(columna + servicios.length + 2 + 1,fila +1).getContents().toString();
	        	}
	        }
	        
	        /*
	        for(int fila = 0; fila < nombres.length;fila++){
	        	for(int columna = 0;columna < 6; columna++){
	        		System.out.print((fila+2) + " " + asociacionesDocumentos[fila][columna]);
	        	}
	        	System.out.println();
	        }
	        */
	        
	        //	Obtiene los nombres de los nodos
	        
	        hoja = archivoExcel.getSheet(1);
	        numFilas = 0;
	        while(!hoja.getCell(0,numFilas).getContents().toString().equals("@finV")){
	        	numFilas++;
	        }
	        
	        System.out.println("numero de filas es... " + numFilas);
	        
	        nodos = new Nodo[numFilas];
	        
	        //	Array con los nombres de los nodos
	        for(int fila=0;fila<nodos.length;fila++){
	        		nodos[fila] = new Nodo();
	        		nodos[fila].alias = hoja.getCell(0,fila).getContents().toString();
	        		nodos[fila].nombre = hoja.getCell(1,fila).getContents().toString();
	        }
	        
	        
	        //	Obtiene las coordenadas
	        
	        hoja = archivoExcel.getSheet(2);
	        numFilas = 0;
	        
	        coordenadasAsociar = new int[6][4];
	        for(int fila=0;fila<6;fila++){
	        	for(int columna = 0;columna<4;columna++){
	        		coordenadasAsociar[fila][columna] = Integer.parseInt(hoja.getCell(1 + columna,2 +fila).getContents().toString());

	        	}
	        }

	        // Obtiene las nuevas coordenadas
	        
	        hoja = archivoExcel.getSheet(3);
	        
	        coordenadasAsociar = new int[10][4];
	        for(int fila=0;fila<10;fila++){
	        	for(int columna = 0;columna<4;columna++){
	        		coordenadasAsociar[fila][columna] = Integer.parseInt(hoja.getCell(1 + columna,2 +fila).getContents().toString());

	        	}
	        }	        
	        
	        
	        for(int i=0;i<10;i++){
	        	for(int j=0;j<4;j++){
	        		System.out.print(coordenadasAsociar[i][j] + "\t");
	        	}
	        	System.out.println();
	        }
	        
	        
	        /*   Esta parte se borrará */
	        
	        hoja = archivoExcel.getSheet(4);
	        
	        tablaNormas = new String[31][4];
	        for(int fila=0;fila<31;fila++){
	        	for(int columna=0;columna<4;columna++){
	        		tablaNormas[fila][columna]=hoja.getCell(columna, fila+1).getContents().toString();
	        	}
	        	
	        }
	        
	        for(int fila=0;fila<31;fila++){
	        	for(int columna=0;columna<4;columna++){
	        		System.out.print(tablaNormas[fila][columna] + "\t");
	        	}
	        	System.out.println();
	        }
	        
	        
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String[] getServicios(){
		return servicios;
	}
	
	public String[] getNombres(){
		return nombres;
	}
	
	public String[][] getTabla(){
		return tablaDocumentos;
	}
	
	public String[][] getHabituales(){
		return habituales;
	}
	
	public String[][] getAsociaciones(){
		return asociacionesDocumentos;
	}
	
	public Nodo[] getNodos(){
		/*
		for(int i=0;i<nodos.length;i++){
			System.out.println(nodos[i].alias);
		}
		*/
		return nodos;
	}
	
	public int[][] getCoordenadasAsociar(){
		return coordenadasAsociar;
	}
	
	static public void main(String args[]){
		LeerExcel leerExcel = new LeerExcel();
		leerExcel.leer("Documentos.xls");
	}

	
}
