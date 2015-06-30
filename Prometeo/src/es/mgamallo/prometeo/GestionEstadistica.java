package es.mgamallo.prometeo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.omg.CORBA.portable.ValueOutputStream;

public class GestionEstadistica {

	ArrayList<String> listaIanus = new ArrayList<String>();
	ArrayList<String> listaUrg = new ArrayList<String>();
	ArrayList<String> listaXedoc = new ArrayList<String>();
	
	ArrayList<EstadisticaDia> estadisticaIanus = new ArrayList<EstadisticaDia>();
	ArrayList<EstadisticaDia> estadisticaUrg = new ArrayList<EstadisticaDia>();
	ArrayList<EstadisticaDia> estadisticaXedoc = new ArrayList<EstadisticaDia>();
	
	ArrayList<EstadisticaDia> estadisticaTotal = new ArrayList<EstadisticaDia>();
	
	
	String cadena5Dias[] = new String[4];
	
	String maximoTotal = "";
	String maximoXedoc = "";
	String maximoIanus = "";
	String maximoUrg = "";
			
	
	static public void main(String args[]){
		new GestionEstadistica();
	}
	
	public GestionEstadistica() {
		// TODO Auto-generated constructor stub
		
		listaXedoc = leerFicheroEstadistica("C:\\desarrollo\\git\\prometeo\\prometeo\\Prometeo\\Prometeo\\Estadisticas\\Xedoc.txt");
		listaUrg = leerFicheroEstadistica("C:\\desarrollo\\git\\prometeo\\prometeo\\Prometeo\\Prometeo\\Estadisticas\\Urgencias.txt");
		listaIanus = leerFicheroEstadistica("C:\\desarrollo\\git\\prometeo\\prometeo\\Prometeo\\Prometeo\\Estadisticas\\Documentacion.txt");

		estadisticaXedoc = convertirEstadisticaDia(listaXedoc);
		estadisticaUrg = convertirEstadisticaDia(listaUrg);
		estadisticaIanus = convertirEstadisticaDia(listaIanus);
		
		getFusionListas();
		
		maximoTotal = getMaximaSubida(estadisticaTotal);
		maximoIanus = getMaximaSubida(estadisticaIanus);
		maximoUrg = getMaximaSubida(estadisticaUrg);
		maximoXedoc = getMaximaSubida(estadisticaXedoc);
		
		System.out.println(maximoTotal);
		System.out.println(maximoIanus);
		System.out.println(maximoUrg);
		System.out.println(maximoXedoc);
	
		getJSONAyer();
		
		/*
		for(int i=estadisticaTotal.size()-1, j=0;j<5;i--,j++){
			System.out.println("Total: " + estadisticaTotal.get(i).fecha + "  " + estadisticaTotal.get(i).numeroFicheros );
			System.out.println("Ianus: " + estadisticaIanus.get(estadisticaIanus.size()-(j+1)).fecha + "  " + estadisticaIanus.get(estadisticaIanus.size()-(j+1)).numeroFicheros );
			System.out.println("Urgen: " + estadisticaUrg.get(estadisticaUrg.size()-(j+1)).fecha + "  " + estadisticaUrg.get(estadisticaUrg.size()-(j+1)).numeroFicheros );
			System.out.println("Xedoc: " + estadisticaXedoc.get(estadisticaXedoc.size()-(j+1)).fecha + "  " + estadisticaXedoc.get(estadisticaXedoc.size()-(j+1)).numeroFicheros );
			System.out.println();
		}
		*/
		
		
		for(int i=1;i<5;i++){
			cadena5Dias[i-1] = getJSON5Dias(i);
		}
		

		
		/*
		String codigoXML5DiasX = getXML5Dias(estadisticaXedoc, "Xedoc semanal");
		String codigoXML5DiasU = getXML5Dias(estadisticaUrg, "Urgencias semanal");
		String codigoXML5DiasI = getXML5Dias(estadisticaIanus, "Ianus semanal");
		
		System.out.println(codigoXML5DiasX);
		System.out.println(codigoXML5DiasU);
		System.out.println(codigoXML5DiasI);
		*/
	}
	
	
	private void getFusionListas(){
				
		for(int i=0;i<estadisticaUrg.size();i++){
						
			estadisticaTotal.add(new EstadisticaDia(estadisticaUrg.get(i).fecha, estadisticaUrg.get(i).numeroFicheros, estadisticaUrg.get(i).diaSemana));
		}
		
		int empiezaDoc = 0;
		int empiezaXed = 0;
		
		for(int i=0;i<estadisticaTotal.size();i++){
			if(estadisticaTotal.get(i).fecha.equals(estadisticaIanus.get(0).fecha)){
				empiezaDoc = i;
				break;
			}
		}
		
	
		
		for(int i= empiezaDoc, j=0;i<estadisticaTotal.size();i++,j++){
	//		System.out.println(i + "  " + j + "  " + estadisticaTotal.get(i).fecha + "  " + estadisticaIanus.get(j).fecha);
			if(estadisticaTotal.get(i).fecha.equals(estadisticaIanus.get(j).fecha)){
				
				int num1 = Integer.valueOf(estadisticaTotal.get(i).numeroFicheros);
				int num2 = Integer.valueOf(estadisticaIanus.get(j).numeroFicheros);
				
				estadisticaTotal.get(i).numeroFicheros = String.valueOf(num1 + num2);
			}
			else{
				if(Integer.valueOf(estadisticaTotal.get(i).fecha) > Integer.valueOf(estadisticaIanus.get(j).fecha)){
	//				System.out.println("Fecha mayor");
					
	//				System.out.println(estadisticaTotal.size());
					
					estadisticaTotal.add(i,estadisticaIanus.get(j));
	//				System.out.println(estadisticaTotal.size());
				}
				else{
	//				System.out.println("Fecha menor");
					j--;
				}
			}
		}
		
	//	System.out.println("Añado xedoc...............");
		
		
		for(int i=0;i<estadisticaTotal.size();i++){
			// System.out.println(i + "  " + estadisticaTotal.get(i).fecha + "  " + estadisticaXedoc.get(0).fecha);

			if(Integer.valueOf(estadisticaTotal.get(i).fecha) >= Integer.valueOf(estadisticaXedoc.get(0).fecha)){
	//			System.out.println("encontrado en " + i);
				empiezaXed = i;
				break;
			}
		}
		
		for(int i= empiezaXed, j=0;i<estadisticaTotal.size();i++,j++){

	//		System.out.println(i + "  " + j + "  " + estadisticaTotal.get(i).fecha + "  " + estadisticaXedoc.get(j).fecha);
			if(estadisticaTotal.get(i).fecha.equals(estadisticaXedoc.get(j).fecha)){
				int num1 = Integer.valueOf(estadisticaTotal.get(i).numeroFicheros);
				int num2 = Integer.valueOf(estadisticaXedoc.get(j).numeroFicheros);
				
				estadisticaTotal.get(i).numeroFicheros = String.valueOf(num1 + num2);
			}
			else{
				if(Integer.valueOf(estadisticaTotal.get(i).fecha) > Integer.valueOf(estadisticaXedoc.get(j).fecha)){
	//				System.out.println("Fecha mayor");
					
	//				System.out.println(estadisticaTotal.size());
					
					estadisticaTotal.add(i,estadisticaXedoc.get(j));
	//				System.out.println(estadisticaTotal.size());
				}
				else{
	//				System.out.println("Fecha menor");
					j--;
				}
			}
		}

	}
	
	
	
	private ArrayList<String> leerFicheroEstadistica(String ruta){
		
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
		
		return lista;
	}
	
	private ArrayList<EstadisticaDia> convertirEstadisticaDia(ArrayList<String> lista){
		
		ArrayList<EstadisticaDia> estadistica = new ArrayList<EstadisticaDia>();
		
		for(int i=0;i<lista.size();i++){
			estadistica.add(new EstadisticaDia(lista.get(i)));
		}
		return estadistica;
	}

	
	/* 
	 <chart palette="2" caption="Product Comparison" showlabels="1" showvalues="0" numberprefix="$" showsum="1" decimals="0" useroundedges="1" legendborderalpha="0" showborder="0">
		<categories>
		<category label="Product A" />
		<category label="Product B" />
		<category label="Product C" />
		<category label="Product D" />
		<category label="Product E" />
		</categories>
		<dataset seriesname="2004" color="AFD8F8" showvalues="0">
		<set value="25601.34" />
		<set value="20148.82" />
		<set value="17372.76" />
		<set value="35407.15" />
		<set value="38105.68" />
		</dataset>
		<dataset seriesname="2005" color="F6BD0F" showvalues="0">
		<set value="57401.85" />
		<set value="41941.19" />
		<set value="45263.37" />
		<set value="117320.16" />
		<set value="114845.27" />
		</dataset>
		<dataset seriesname="2006" color="8BBA00" showvalues="0">
		<set value="45000.65" />
		<set value="44835.76" />
		<set value="18722.18" />
		<set value="77557.31" />
		<set value="92633.68" />
		</dataset>
	</chart>
 */
	
	
	public String getJSONAyer(){
		String cadena01 = ""
				+ ""
				+ "FusionCharts.ready(function() {"
					+ "var chartTarta = new FusionCharts({"
					    	+ "type : 'pie3d',"
					    	+ "renderAt : 'chartTarta',"
					    	+ "width : '760',"
					    	+ "height : '240',"
					    	+ "dataFormat : 'json',"
					    	+ "dataSource : {"
					    		+ "chart : {"
					    			+ "caption : '" + "Distribución diaria" + "',"
					    			+ "animation : '1',"
					    		//	+ "theme : 'fint',
					    			+ "showLegend: '1',"
					    			+ "legendBorderAlpha : '0',"
					    			+ "pieslicedepth : '10',"
					    			+ "startingangle : '200',"
					    			+ "showBorder : '0',"
					    			+ "showHoverEffect : '1',"
							        + "toolTipColor: '#ffffff',"
							        + "toolTipBgColor: '#000000',"
							        + "toolTipBgAlpha: '80',"
							        + "toolTipBorderRadius: '2',"
							        + "toolTipPadding: '5',"
							        + "use3DLighting: '1',"
							        + "radius3D: '40',"
					    			+ "formatNumber : '0',"
					    			+ "formatNumberScale : '0',"
					    			+ "bgcolor : 'ffffff',"
					    			+ "},"
					    			
					    		+ "data : ["
					    			+ "{"
					    				+ "label : 'Xedoc',"
					    				+ "value : '" + estadisticaXedoc.get(estadisticaXedoc.size()-1).numeroFicheros + "',"
					    				+ "issliced : '1',"
					    				+ "color : '#fa9000'"
					    			+ "},"
						    		+ "{"
					    				+ "label : 'Ianus Doc',"
					    				+ "value : '" + estadisticaIanus.get(estadisticaIanus.size()-1).numeroFicheros + "',"
					    				+ "issliced : '1',"
					    				+ "color : '#c40000'"
					    			+ "},"
						    		+ "{"
					    				+ "label : 'Ianus Urg',"
					    				+ "value : '" + estadisticaUrg.get(estadisticaUrg.size()-1).numeroFicheros + "',"
					    				+ "issliced : '1',"
					    				+ "color : '#750303'"
					    			+ "}"					    				
					    		+ "]"
					    	+ "}"
					    	+ ""
					    	+ "});"
					    	+ "chartTarta.render(chartTarta);"
					    + "});"
					    + ""
					    + "";
		
		System.out.println(cadena01);
		
		return cadena01;
	}
	
	public String getJSON5Dias(int tipo){

		// 1 Todos
		// 2 Ianus
		// 3 Urg
		// 4 Xedoc
		
		ArrayList<EstadisticaDia> lista = new ArrayList<EstadisticaDia>();
		String titulo = "";
		String max = "";
		
		String limiteVertical = "";
		
		switch (tipo) {
		case 1:
			  	lista = estadisticaTotal;
			  	titulo = "Total";
			  	max = maximoTotal;
			  	
			  	break;
		case 2:
				lista = estadisticaIanus;
				titulo = "Ianus";
				max = maximoIanus;
				break;
		case 3:
				lista = estadisticaUrg;
				titulo = "Urgencias";
				max = maximoUrg;
				break;
		case 4:
				lista = estadisticaXedoc;
				titulo = "Xedoc";
				max = maximoXedoc;
				break;
		}
		
		int num = (int) (Integer.valueOf(max) * 1.1);
		
		limiteVertical = String.valueOf(num);
		
		/*
		String enteraDeEjemplo = ""
				+ ""
				+ "FusionCharts.ready(function() {"
					+ "var seleccion, inputs, selElem, "
				    	+ "chart5Dias = new FusionCharts({"
					    	+ "type : 'column2d',"
					    	+ "renderAt : 'chart5DiasA',"
					    	+ "width : '360',"
					    	+ "height : '240',"
					    	+ "dataFormat : 'json',"
					    	+ "dataSource : {"
					    		+ "chart : {"
					    			+ "theme : 'fint',"
					    			+ "rotateValues : '0',"
					    			+ "bgcolor : 'ffffff',"
					    			+ "useroundedges : '1',"
					    			+ "showborder : '0',"
					    			+ "palettecolors : '#008ee4,#6baa01,#f8bd19,#e44a00,#33bdda',"
					    			+ "},"
					    			
					    		+ "data : ["
					    			+ "{ label: 'Pera', value: '13773'},"
					    			+ "{ label: 'limon', value: '3773'}"
					    			+ "]"
					    	+ "}"
				+ "});"
				+ ""
				+ "chart5Dias.render('chart5DiasA');"
				+ "})"
				+ "";
		*/
		
		String cadena01 = ""
				+ ""
				+ "FusionCharts.ready(function() {"
					+ "var seleccion, inputs, selElem, "
				    	+ "chart5Dias = new FusionCharts({"
					    	+ "type : 'column2d',"
					    	+ "renderAt : 'chart5DiasA',"
					    	+ "width : '360',"
					    	+ "height : '240',"
					    	+ "dataFormat : 'json',"
					    	+ "dataSource : {"
					    		+ "chart : {"
					    			+ "caption : '" + titulo + "',"
					    			+ "theme : 'fint',"
					    			+ "yAxisMaxValue : '" + limiteVertical + "',"
					    			+ "rotateValues : '0',"
					    			+ "formatNumber : '0',"
					    			+ "formatNumberScale : '0',"
					    			+ "bgcolor : 'ffffff',"
					    			+ "useroundedges : '1',"
					    			+ "showborder : '0',"
					    			+ "palettecolors : '#008ee4,#6baa01,#f8bd19,#e44a00,#33bdda',"
					    			+ "},"
					    			
					    		+ "data : "
					    		+ ""
					    		+ "";
		
		String cadena02 = ""
				+ "[";
		
		for(int i=6;i>1;i--){
			cadena02 += (
							"{ label: '" + lista.get(lista.size() - i).diaSemana 
					     + "', value: '" + lista.get(lista.size() - i).numeroFicheros + "'}"
						);
			if(i != 2){
				cadena02 += ",";
			}
			else{
				cadena02 += "],";
			}
		}
		
		
		String cadena03 = ""
							+ "trendlines : ["
								+ "{"
									+ "line: ["
										+ "{ "
											+ "startvalue : '" + max + "',"
										 	+ "color : '#1aaf5d',"
										 	+ "valueOnRight : '1',"
										 	+ "tooltext : 'Maxima subida de $startvalue',"
										 	+ "displayvalue : 'Máximo'"
										 + "}"
										+ "]"
								+ "}"
							   + "]"
						    + "}"
						 + "});"
						 + ""
						 + "chart5Dias.render('chart5DiasA');"
					+ "})"
					+ "";

		
		
	//	System.out.println(cadena01 + cadena02 + cadena03);
		
		return (cadena01 + cadena02 + cadena03);
	}
	
	private String getXML5Dias(ArrayList<EstadisticaDia> estadistica, String titulo){
		
		
		String cadena1 = ""
				+ ""
				+ "<chart caption='" + titulo + "' theme='fint' bgcolor='FFFFFF' useroundedges='1' showborder='0'>"
				+ "";
		
		String cadena2 = "";
		
		for(int i=estadistica.size()-6, j=0;j<5;i++,j++){
			cadena2 = cadena2 + ""
					+ "<set label='" + estadistica.get(i).diaSemana 
					+ "' value='" + estadistica.get(i).numeroFicheros + "'/>"
					+ "";
		}
		
		String cadena3 = ""
				+ ""
				+ "</chart>"
				+ "";
						
		return cadena1 + cadena2 + cadena3;
	}

	private String getMaximaSubida(ArrayList<EstadisticaDia> estadistica){
		
		int maximo = 0;
		
		for(int i=0;i<estadistica.size();i++){
			if(maximo < Integer.valueOf(estadistica.get(i).numeroFicheros)){
				maximo = Integer.valueOf(estadistica.get(i).numeroFicheros);
			}
		}
		
		return String.valueOf(maximo);
	}
}
