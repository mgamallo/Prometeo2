package es.mgamallo.prometeo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class AyudaIndex {

	String tablaHermes1[][];
	String tablaHermes2[][];
	TreeMap<String, Indices>  indiceGeneral = new TreeMap<String, Indices>();
	
	public AyudaIndex() {
		// TODO Auto-generated constructor stub
		
		LeerExcelHermes leerExcel = new LeerExcelHermes();
		leerExcel.leer("Hermes.xls");
		
		this.tablaHermes1 = leerExcel.tablaHermes1;
		this.tablaHermes2 = leerExcel.tablaHermes2;
		
		int filas = tablaHermes1.length;
		int columnas = tablaHermes1[0].length;
		
		System.out.println("Filas... " + filas);
		System.out.println("Columnas... " + columnas);
		
		for(int fil = 0;fil<filas;fil++){
			for(int col = 2;col<columnas;col++){
				
				String valor = tablaHermes1[fil][col].toLowerCase();
				
				if(col == 9){
					
				}
				else if(col == 10){
					if(valor.length() > 0){
						incluirClave("#",fil,tablaHermes1);
					}
				}
				else{
					incluirClave(valor, fil, tablaHermes1);
				}
			}
		}
		
		
		filas = tablaHermes2.length;
		columnas = tablaHermes2[0].length;
		
		System.out.println("Filas... " + filas);
		System.out.println("Columnas... " + columnas);
		
		for(int fil = 0;fil<filas;fil++){
			for(int col = 3;col<6;col++){
				
				String valor = tablaHermes2[fil][col].toLowerCase();
				incluirClave(valor, fil, tablaHermes2);

			}
		}
		
		Txt.escribirIndiceTxt(indiceGeneral);
		
		/*
		Iterator<String> it = indiceGeneral.keySet().iterator();
		while(it.hasNext()){
		  String key = (String) it.next();
		  System.out.println("Clave: " + key + " ->"); 
		  for(int i=0;i<indiceGeneral.get(key).indices.size();i++){
			  System.out.println("   Valor: " + indiceGeneral.get(key).indices.get(i));
		  }
		  
		}
		*/
	}

	
	private void incluirClave(String valor, int fil, String tabla[][]){
		
		if(valor.length()>0){
			String tablaHermes[][] = tabla;
			
			String imagenNumero = String.valueOf(Integer.valueOf(tablaHermes[fil][0].substring(6)));
			
			if(indiceGeneral.containsKey(valor)){
				Indices ind = indiceGeneral.get(valor);
				ind.indices.add(imagenNumero);
				indiceGeneral.put(valor,ind);
			}
			else{
				Indices ind = new Indices();
				ind.indices.add(imagenNumero);
				indiceGeneral.put(valor, ind );
			}
		}
		

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		Indices ind1 = new Indices();
		ind1.indices.add("001");
		ind1.indices.add("005");
		ind1.indices.add("007");
		
		Indices ind2 = new Indices();
		ind2.indices.add("001");
		ind2.indices.add("002");
		ind2.indices.add("003");
		
		Indices ind3 = new Indices();
		ind3.indices.add("004");

		TreeMap<String, Indices>  indiceGeneral = new TreeMap<String, Indices>();
		indiceGeneral.put("hola", ind1);
		indiceGeneral.put("adios", ind2);
		indiceGeneral.put("despues", ind3);
		
		System.out.println(indiceGeneral.containsKey("adios"));
		
		Iterator<String> it = indiceGeneral.keySet().iterator();
		while(it.hasNext()){
		  String key = (String) it.next();
		  System.out.println("Clave: " + key + " ->"); 
		  for(int i=0;i<indiceGeneral.get(key).indices.size();i++){
			  System.out.println("   Valor: " + indiceGeneral.get(key).indices.get(i));
		  }
		  
		}
		*/
		
		new AyudaIndex();
	}

}


class Indices{
	ArrayList<String> indices = new ArrayList<String>();
}