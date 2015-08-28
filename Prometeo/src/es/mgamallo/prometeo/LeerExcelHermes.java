package es.mgamallo.prometeo;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeMap;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;
import jxl.biff.WorkbookMethods;
import jxl.read.biff.BiffException;

public class LeerExcelHermes {

	

	
	public String[][] tablaHermes1;
	public String[][] tablaHermes2;
	
	
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

	        int numColumnas = 11;
	        int numFilas = 0;
	        	        
	        while(!hoja.getCell(0,numFilas).getContents().toString().equals("ultimo")){
	        	numFilas++;
	        }
	        numFilas--;
	        
	        System.out.println("Num filas = " + numFilas + ". Num columnas = " + numColumnas);
	        
	        tablaHermes1 = new String[numFilas][numColumnas];

	        for(int fila=0;fila<numFilas;fila++){
	        	for(int columna=0;columna<numColumnas;columna++){
	        		tablaHermes1[fila][columna] = hoja.getCell(columna,fila+1).getContents().toString();
	        	}
	        }
	        
	        /*
	        
	        for(int fila=0;fila<numFilas;fila++){
	        	for(int columna=0;columna<numColumnas;columna++){
	        		System.out.print(tablaHermes1[fila][columna] + " ");
	        	}
	        	System.out.println();
	        }
	        */

	        
	        //	Obtiene las coordenadas
	        
	        hoja = archivoExcel.getSheet(1);
	        numFilas = 0;
	        numColumnas = 13;
	        
	        while(!hoja.getCell(0,numFilas).getContents().toString().equals("ultimo")){
	        	numFilas++;
	        }
	        numFilas--;
	        
	        System.out.println("Num filas = " + numFilas + ". Num columnas = " + numColumnas);
	        
	        tablaHermes2 = new String[numFilas][numColumnas];

	        for(int fila=0;fila<numFilas;fila++){
	        	for(int columna=0;columna<numColumnas;columna++){
	        		tablaHermes2[fila][columna] = hoja.getCell(columna,fila+1).getContents().toString();
	        	}
	        }
	        
	        /*
	        for(int fila=0;fila<numFilas;fila++){
	        	for(int columna=0;columna<numColumnas;columna++){
	        		System.out.print(tablaHermes2[fila][columna] + " ");
	        	}
	        	System.out.println();
	        }
	        */
	        
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	
	static public void main(String args[]){
		LeerExcelHermes leerExcel = new LeerExcelHermes();
		leerExcel.leer("Hermes.xls");
	}

	
}
