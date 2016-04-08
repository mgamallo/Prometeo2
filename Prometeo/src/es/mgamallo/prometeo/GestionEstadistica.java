package es.mgamallo.prometeo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.omg.CORBA.portable.ValueOutputStream;

public class GestionEstadistica {

	ArrayList<String> listaIanus = new ArrayList<String>();
	ArrayList<String> listaUrg = new ArrayList<String>();
	ArrayList<String> listaXedoc = new ArrayList<String>();
	ArrayList<String> listaSalnes = new ArrayList<String>();
	
	ArrayList<EstadisticaDia> estadisticaIanus = new ArrayList<EstadisticaDia>();
	ArrayList<EstadisticaDia> estadisticaUrg = new ArrayList<EstadisticaDia>();
	ArrayList<EstadisticaDia> estadisticaXedoc = new ArrayList<EstadisticaDia>();
	ArrayList<EstadisticaDia> estadisticaSalnes = new ArrayList<EstadisticaDia>();
	
	ArrayList<EstadisticaDia> estadisticaTotal = new ArrayList<EstadisticaDia>();
	
	ArrayList<EstadisticaAño> estadisticasAnualesI = new ArrayList<EstadisticaAño>();
	ArrayList<EstadisticaAño> estadisticasAnualesU = new ArrayList<EstadisticaAño>();
	ArrayList<EstadisticaAño> estadisticasAnualesX = new ArrayList<EstadisticaAño>();
	ArrayList<EstadisticaAño> estadisticasAnualesS = new ArrayList<EstadisticaAño>();
	ArrayList<EstadisticaAño> estadisticasAnualesT = new ArrayList<EstadisticaAño>();
	
	String graficoTarta = "";
	String cadena5Dias[] = new String[5];
	String cadenaMes = "";
	String cadenaAñoMes = "";
	String cadenaAños = "";
	String cadenaHistorico = "";
	
	String maximoTotal = "";
	String maximoXedoc = "";
	String maximoIanus = "";
	String maximoUrg = "";
	String maximoSalnes = "";
	
	String mediaTotal = "";
	String mediaXedoc = "";
	String mediaIanus = "";
	String mediaUrg = "";
	String mediaSalnes = "";
	
	ArrayList<String> años = new ArrayList<String>();
	
	static String añoActual = "";
	static String mesActual = "";
	static Fecha fecha;
	
	static ArrayList<DatosEstimados> correcionesAnualesUrgencias = new ArrayList<DatosEstimados>();
	static ArrayList<DatosEstimados> correcionesAnualesIanus = new ArrayList<DatosEstimados>();
	
	static public void main(String args[]){
		new GestionEstadistica();
	}
	
	
	public GestionEstadistica() {
		// TODO Auto-generated constructor stub
		
		// Datos estimados
		correcionesAnualesUrgencias.add(new DatosEstimados("2009", 6431, 6, 6));
		correcionesAnualesUrgencias.add(new DatosEstimados("2010", 7116, 0, 12));
		correcionesAnualesUrgencias.add(new DatosEstimados("2011", 7420, 0, 12));
		correcionesAnualesUrgencias.add(new DatosEstimados("2012", 7673, 0, 12));
		correcionesAnualesUrgencias.add(new DatosEstimados("2013", 6780, 0, 12));
		correcionesAnualesUrgencias.add(new DatosEstimados("2014", 12530, 0, 2));
		
		correcionesAnualesIanus.add(new DatosEstimados("2011", 7947, 6, 6));
		correcionesAnualesIanus.add(new DatosEstimados("2012", 16504, 0, 12));
		correcionesAnualesIanus.add(new DatosEstimados("2013", 21765, 0, 12));
		correcionesAnualesIanus.add(new DatosEstimados("2014", 25105, 0, 9));
		
		/*
		listaXedoc = leerFicheroEstadistica("C:\\desarrollo\\git\\prometeo\\prometeo\\Prometeo\\Prometeo\\Estadisticas\\Xedoc.txt");
		listaUrg = leerFicheroEstadistica("C:\\desarrollo\\git\\prometeo\\prometeo\\Prometeo\\Prometeo\\Estadisticas\\Urgencias.txt");
		listaIanus = leerFicheroEstadistica("C:\\desarrollo\\git\\prometeo\\prometeo\\Prometeo\\Prometeo\\Estadisticas\\Documentacion.txt");
		*/
		
		listaXedoc = leerFicheroEstadistica(Inicio.rutaEstadisticaXedoc + "Xedoc.txt");
		listaUrg = leerFicheroEstadistica(Inicio.rutaEstadisticaUrg + "Urgencias.txt");
		listaIanus = leerFicheroEstadistica(Inicio.rutaEstadisticaIanus + "Documentacion.txt");
		listaSalnes = leerFicheroEstadistica(Inicio.rutaEstadisticaSalnes + "Salnes.txt");
		
	//	System.out.println(Inicio.rutaEstadisticaIanus + "Documentacion.txt");

		estadisticaXedoc = convertirEstadisticaDia(listaXedoc);
		estadisticaUrg = convertirEstadisticaDia(listaUrg);
		estadisticaIanus = convertirEstadisticaDia(listaIanus);
		estadisticaSalnes = convertirEstadisticaDia(listaSalnes);
		
		recolocaSabados(estadisticaXedoc);
		recolocaSabados(estadisticaUrg);
		recolocaSabados(estadisticaIanus);
		recolocaSabados(estadisticaSalnes);
		getFusionListas();
		
		/*
		System.out.println("Listado de estadisticatotal");
		for(int i=0;i<estadisticaTotal.size();i++){
			System.out.println(estadisticaTotal.get(i).fecha);
		}
		System.out.println("Fin.");
		*/
		
		maximoTotal = getMaximaSubida(estadisticaTotal);
		maximoIanus = getMaximaSubida(estadisticaIanus);
		maximoUrg = getMaximaSubida(estadisticaUrg);
		maximoXedoc = getMaximaSubida(estadisticaXedoc);
		maximoSalnes = getMaximaSubida(estadisticaSalnes);
		
		años = getAños(estadisticaTotal);
		
		fecha = new Fecha();
		añoActual = String.valueOf(fecha.año);
		mesActual = fecha.numeroMes;
		
		
		/*
		System.out.println(maximoTotal);
		System.out.println(maximoIanus);
		System.out.println(maximoUrg);
		System.out.println(maximoXedoc);
		*/
		
		getEstadisticasAnuales();
		
		graficoTarta = getJSONTarta(añoActual, "false");
		
		
		/*
		for(int i=estadisticaTotal.size()-1, j=0;j<5;i--,j++){
			System.out.println("Total: " + estadisticaTotal.get(i).fecha + "  " + estadisticaTotal.get(i).numeroFicheros );
			System.out.println("Ianus: " + estadisticaIanus.get(estadisticaIanus.size()-(j+1)).fecha + "  " + estadisticaIanus.get(estadisticaIanus.size()-(j+1)).numeroFicheros );
			System.out.println("Urgen: " + estadisticaUrg.get(estadisticaUrg.size()-(j+1)).fecha + "  " + estadisticaUrg.get(estadisticaUrg.size()-(j+1)).numeroFicheros );
			System.out.println("Xedoc: " + estadisticaXedoc.get(estadisticaXedoc.size()-(j+1)).fecha + "  " + estadisticaXedoc.get(estadisticaXedoc.size()-(j+1)).numeroFicheros );
			System.out.println();
		}
		*/
		
		
		for(int i=1;i<6;i++){
			cadena5Dias[i-1] = getJSON5Dias(i);
		}
		
		
		cadenaMes = getJSONMesDias(1,añoActual,mesActual);
		cadenaAñoMes = getJSONAnualMes(1,añoActual);
		cadenaAños = getJSONTipoGrafico(1,"mscolumn2d");
		cadenaHistorico = getJSONHistorico(1,"Total");
		
		/*
		String codigoXML5DiasX = getXML5Dias(estadisticaXedoc, "Xedoc semanal");
		String codigoXML5DiasU = getXML5Dias(estadisticaUrg, "Urgencias semanal");
		String codigoXML5DiasI = getXML5Dias(estadisticaIanus, "Ianus semanal");
		
		System.out.println(codigoXML5DiasX);
		System.out.println(codigoXML5DiasU);
		System.out.println(codigoXML5DiasI);
		*/
	}
	
	
	private void getEstadisticasAnuales(){
		

		estadisticasAnualesI.add(new EstadisticaAño("2009", "I", 0,0,0));
		estadisticasAnualesU.add(new EstadisticaAño("2009", "U", 38587, 125, 38587/125));
		estadisticasAnualesX.add(new EstadisticaAño("2009", "X", 0,0,0));
		estadisticasAnualesS.add(new EstadisticaAño("2009", "S", 0,0,0));
		estadisticasAnualesT.add(new EstadisticaAño("2009", "T", 38587, 125, 38587/125));
		
		estadisticasAnualesI.add(new EstadisticaAño("2010", "I", 0,0,0));
		estadisticasAnualesU.add(new EstadisticaAño("2010", "U", 85396, 250, 85396/250));
		estadisticasAnualesX.add(new EstadisticaAño("2010", "X", 0,0,0));
		estadisticasAnualesS.add(new EstadisticaAño("2010", "S", 0,0,0));
		estadisticasAnualesT.add(new EstadisticaAño("2010", "T", 85396, 250, 85396/250));
		
		estadisticasAnualesI.add(new EstadisticaAño("2011", "I", 47682,125,47682/125));
		estadisticasAnualesU.add(new EstadisticaAño("2011", "U", 89035, 250, 89035/250));
		estadisticasAnualesX.add(new EstadisticaAño("2011", "X", 0,0,0));
		estadisticasAnualesS.add(new EstadisticaAño("2011", "S", 0,0,0));
		estadisticasAnualesT.add(new EstadisticaAño("2011", "T", 136717, 250, 47682/125 + 89035/250));
		
		estadisticasAnualesI.add(new EstadisticaAño("2012", "I", 198046,250,198046/250));
		estadisticasAnualesU.add(new EstadisticaAño("2012", "U", 92073, 250, 92073/250));
		estadisticasAnualesX.add(new EstadisticaAño("2012", "X", 0,0,0));
		estadisticasAnualesS.add(new EstadisticaAño("2012", "S", 0,0,0));
		estadisticasAnualesT.add(new EstadisticaAño("2012", "T", 290119, 250, 290119/250));
		
		estadisticasAnualesI.add(new EstadisticaAño("2013", "I", 261175,250,261175/250));
		estadisticasAnualesU.add(new EstadisticaAño("2013", "U", 81365, 250, 81365/250));
		estadisticasAnualesX.add(new EstadisticaAño("2013", "X", 0,0,0));
		estadisticasAnualesS.add(new EstadisticaAño("2013", "S", 0,0,0));
		estadisticasAnualesT.add(new EstadisticaAño("2013", "T", 342540, 250, 342540/250));
		
		int tam = estadisticasAnualesT.size();
		for(int i=0;i<años.size();i++){
			estadisticasAnualesI.add(new EstadisticaAño(años.get(i), "I", estadisticaIanus));
			estadisticasAnualesU.add(new EstadisticaAño(años.get(i), "U", estadisticaUrg));
			estadisticasAnualesX.add(new EstadisticaAño(años.get(i), "X", estadisticaXedoc));
			estadisticasAnualesS.add(new EstadisticaAño(años.get(i), "S", estadisticaSalnes));
			
			int docs = 0;
			int numDias = 0;
			int media = 0;
			
			docs =    estadisticasAnualesI.get(i + tam).documentosT 
					+ estadisticasAnualesU.get(i+ tam).documentosT 
					+ estadisticasAnualesX.get(i+ tam).documentosT 
					+ estadisticasAnualesS.get(i+ tam).documentosT ;
			
			numDias =    estadisticasAnualesI.get(i+ tam).numeroDiasT 
					+ estadisticasAnualesU.get(i+ tam).numeroDiasT 
					+ estadisticasAnualesX.get(i+ tam).numeroDiasT 
					+ estadisticasAnualesS.get(i+ tam).numeroDiasT ;

			media =    estadisticasAnualesI.get(i+ tam).mediaDiariaT
					+ estadisticasAnualesU.get(i+ tam).mediaDiariaT 
					+ estadisticasAnualesX.get(i+ tam).mediaDiariaT 
					+ estadisticasAnualesS.get(i+ tam).mediaDiariaT ;
			
			System.out.println(años.get(i) + " T " + docs + " " + numDias + " " + media);
			estadisticasAnualesT.add(new EstadisticaAño(años.get(i), "T", docs, numDias, media));
		}
		

		
		estadisticasAnualesI.get(5).documentosT = estadisticasAnualesI.get(5).documentosT + 25105*9;
		estadisticasAnualesI.get(5).numeroDiasT = 250;
		estadisticasAnualesU.get(5).documentosT = estadisticasAnualesU.get(5).documentosT + 12530*2;
		estadisticasAnualesU.get(5).numeroDiasT = 250;
		
		estadisticasAnualesT.get(5).documentosT = estadisticasAnualesU.get(5).documentosT 
												+ estadisticasAnualesI.get(5).documentosT;
		estadisticasAnualesT.get(5).numeroDiasT = 250;
		
		/*
		estadisticasAnualesI.add(new EstadisticaAño(años.get(i), "I", estadisticaIanus));
		estadisticasAnualesU.add(new EstadisticaAño(años.get(i), "U", estadisticaUrg));
		estadisticasAnualesX.add(new EstadisticaAño(años.get(i), "X", estadisticaXedoc));
		estadisticasAnualesS.add(new EstadisticaAño(años.get(i), "S", estadisticaSalnes));
		*/
		
	}
	
	
	private void getFusionListas(){
				
		for(int i=0;i<estadisticaUrg.size();i++){
						
			estadisticaTotal.add(new EstadisticaDia(estadisticaUrg.get(i).fecha, estadisticaUrg.get(i).numeroFicheros, estadisticaUrg.get(i).diaSemana));
		}
		
		int empiezaDoc = 0;
		int empiezaXed = 0;
		int empiezaSal = 0;
		
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

		
		//	Salnés
		
		for(int i=0;i<estadisticaTotal.size();i++){

			if(Integer.valueOf(estadisticaTotal.get(i).fecha) >= Integer.valueOf(estadisticaSalnes.get(0).fecha)){
	//			System.out.println("encontrado en " + i);
				empiezaSal = i;
				break;
			}
		}
		
		for(int i= empiezaSal, j=0;i<estadisticaTotal.size();i++,j++){

	//		System.out.println(i + "  " + j + "  " + estadisticaTotal.get(i).fecha + "  " + estadisticaXedoc.get(j).fecha);
			if(estadisticaTotal.get(i).fecha.equals(estadisticaSalnes.get(j).fecha)){
				int num1 = Integer.valueOf(estadisticaTotal.get(i).numeroFicheros);
				int num2 = Integer.valueOf(estadisticaSalnes.get(j).numeroFicheros);
				
				estadisticaTotal.get(i).numeroFicheros = String.valueOf(num1 + num2);
			}
			else{
				if(Integer.valueOf(estadisticaTotal.get(i).fecha) > Integer.valueOf(estadisticaSalnes.get(j).fecha)){
	//				System.out.println("Fecha mayor");
					
	//				System.out.println(estadisticaTotal.size());
					
					estadisticaTotal.add(i,estadisticaSalnes.get(j));
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
		
	//	System.out.println("La ruta es... " + ruta);
		
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
	//		System.out.println(lista.get(i));
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
	
	
	public String getJSONTarta(String año, String ayer){
		
		String cadenaFecha = estadisticaIanus.get(estadisticaIanus.size()-1).fecha;
		
		FechaGraficos f = new FechaGraficos(cadenaFecha);
		
		boolean ultimoDia = Boolean.valueOf(ayer);
		
		String numI = "";
		String numU = "";
		String numX = "";
		String numS = "";
		
		
		if(ultimoDia){
			cadenaFecha = f.dia + " de " + f.mes + " de " + f.año;
			numI = estadisticaIanus.get(estadisticaIanus.size()-1).numeroFicheros;
			numU = estadisticaUrg.get(estadisticaUrg.size()-1).numeroFicheros;
			numX = estadisticaXedoc.get(estadisticaXedoc.size()-1).numeroFicheros;
			numS = estadisticaSalnes.get(estadisticaSalnes.size()-1).numeroFicheros;
		}
		else{
			cadenaFecha = año;
			if(año.equals("Tota")){
				cadenaFecha = "Acumulado Total";
				
				int nI = 0;
				int nU = 0;
				int nX = 0;
				int nS = 0;
				
				for(int i=0;i<estadisticasAnualesI.size();i++){
						nI += estadisticasAnualesI.get(i).documentosT;
				}
				
				for(int i=0;i<estadisticasAnualesU.size();i++){
						nU += estadisticasAnualesU.get(i).documentosT;
				}
				
				for(int i=0;i<estadisticasAnualesX.size();i++){
						nX += estadisticasAnualesX.get(i).documentosT;
				}
				
				for(int i=0;i<estadisticasAnualesS.size();i++){
						nS += estadisticasAnualesS.get(i).documentosT;
				}
				
				numI = "" + nI;
				numU = "" + nU;
				numX = "" + nX;
				numS = "" + nS;
				
			}
			else{
				for(int i=0;i<estadisticasAnualesI.size();i++){
					if(año.equals(estadisticasAnualesI.get(i).año)){
						numI = "" + estadisticasAnualesI.get(i).documentosT;
					}
				}
				
				for(int i=0;i<estadisticasAnualesU.size();i++){
					if(año.equals(estadisticasAnualesU.get(i).año)){
						numU = "" + estadisticasAnualesU.get(i).documentosT;
					}
				}
				
				for(int i=0;i<estadisticasAnualesX.size();i++){
					if(año.equals(estadisticasAnualesX.get(i).año)){
						numX = "" + estadisticasAnualesX.get(i).documentosT;
					}
				}
				
				for(int i=0;i<estadisticasAnualesS.size();i++){
					if(año.equals(estadisticasAnualesS.get(i).año)){
						numS = "" + estadisticasAnualesS.get(i).documentosT;
					}
				}
			}
			

						
		}
		
		

		
		
		
	//	System.out.println(cadenaFecha);
		
		String titulo = cadenaFecha;

		
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
					    			+ "caption : '" + titulo + "',"
					    			+ "animation : '1',"
					    			+ "formatNumberScale : '0',"
					    			+ "decimals : '0',"
					    		//	+ "formatNumber : '0',"
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
							        + "radius3D: '40'"
					    		+ "},"
					    			
					    		+ "data : ["
					    			+ "{"
					    				+ "label : 'Xedoc',"
					    				+ "value :  '" + numX + "'," 
					    				+ "issliced : '1',"
					    				+ "color : '#fa9000'"
					    			+ "},"
						    		+ "{"
					    				+ "label : 'Ianus Doc',"
					    				+ "value : '" + numI + "',"
					    				+ "issliced : '1',"
					    				+ "color : '#c40000'"
					    			+ "},"
						    		+ "{"
					    				+ "label : 'Ianus Urg',"
					    				+ "value : '" + numU + "',"
						    			+ "issliced : '1',"
					    				+ "color : '#750303'"
					    			+ "},"
						    		+ "{"
					    				+ "label : 'Salnés',"
					    				+ "value : '" +numS + "',"
						    			+ "issliced : '1',"
					    				+ "color : '#f8bd19'"
					    			+ "}"					    				
					    		+ "]"
					    	+ "}"
					    	+ ""
					    	+ "});"
					    	+ "chartTarta.render('chartTarta');"
					    	
							 + "var h = document.getElementById('tarta_año');"
							 + "var k = h.getElementsByTagName('option');"
							 + "for(var i=0;i<h.options.length;i++){"
							 	  + "if(k[i].value.localeCompare('" + año + "') == 0){"
							 	  		+ "k[i].selected = 'selected';"
							 	  + "}"
							 + "}"	
					    
 							 + "var h = document.getElementById('ayerCh');"
 							 + "h.checked = " + ultimoDia + ";"
					    
					    
					    + "});"
					    + ""
					    + "";
		
	//	System.out.println(cadena01);
		
		return cadena01;
	}
	
	
	public String getJSON5Dias(int tipo){

		// 1 Todos
		// 2 Ianus
		// 3 Urg
		// 4 Xedoc
		// 5 Salnes
		
		ArrayList<EstadisticaDia> lista = new ArrayList<EstadisticaDia>();
		String titulo = "";
		String max = "";
		String med = "";
		
		String limiteVertical = "";

		
		switch (tipo) {
		case 1:
			  	lista = estadisticaTotal;
			  	titulo = "Total";
			  	max = maximoTotal;
			  	med = getMediaSubidaAnual(false, tipo, lista, añoActual);
			  	break;
		case 2:
				lista = estadisticaIanus;
				titulo = "Ianus";
				max = maximoIanus;
				med = getMediaSubidaAnual(false, tipo, lista, añoActual);
				break;
		case 3:
				lista = estadisticaUrg;
				titulo = "Urgencias";
				max = maximoUrg;
				med = getMediaSubidaAnual(false, tipo, lista, añoActual);
				break;
		case 4:
				lista = estadisticaXedoc;
				titulo = "Xedoc";
				max = maximoXedoc;
				med = getMediaSubidaAnual(false, tipo, lista, añoActual);
				break;
		case 5:
				lista = estadisticaSalnes;
				titulo = "Salnés";
				max = maximoSalnes;
				med = getMediaSubidaAnual(false, tipo, lista, añoActual);
				break;
		}
		
		int num = (int) (Integer.valueOf(max) * 1.1);
		
		limiteVertical = String.valueOf(num);
		
		
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
					    			+ "bgcolor : '#ffffff',"
					    			+ "useroundedges : '1',"
					    			+ "showborder : '0',"
					    			+ "palettecolors : '#008ee4,#6baa01,#f8bd19,#e44a00,#33bdda',"
					    			+ "},"
					    			
					    		+ "data : "
					    		+ ""
					    		+ "";
		
		String cadena02 = ""
				+ "[";
		
		
		for (int i = 5; i > 0; i--) {
			if (lista.size() > 4) {
				cadena02 += ("{ label: '"
						+ lista.get(lista.size() - i).diaSemana + "', value: '"
						+ lista.get(lista.size() - i).numeroFicheros + "'}");
				if (i != 1) {
					cadena02 += ",";
				} else {
					cadena02 += "],";
				}
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
										+ "},"
										+ "{ "
											+ "startvalue : '" + med + "',"
										 	+ "color : '#c40000',"
										 	+ "valueOnRight : '1',"
										 	+ "tooltext : 'Media anual de $startvalue',"
										 	+ "displayvalue : 'Media'"
										 + "}"
										+ "]"
								+ "}"
							   + "]"
						    + "}"
						 + "});"
						 + ""
						 + "chart5Dias.render('chart5DiasA');"
						 + ""
						 + "var h = document.getElementById('secciones5dias');"
						 + "var k = h.getElementsByTagName('option');"
						 + "for(var i=0;i<h.options.length;i++){"
						 	  + "if(k[i].value.localeCompare('" + titulo + "') == 0){"
						 	  		+ "k[i].selected = 'selected';"
						 	  + "}"
						 + "}"
					+ "})"
					+ "";

		
		
	//	System.out.println(cadena01 + cadena02 + cadena03);
		
		return (cadena01 + cadena02 + cadena03);
	}

	
	public String getJSONMesDias(int tipo, String año, String mes){

		// 1 Todos
		// 2 Ianus
		// 3 Urg
		// 4 Xedoc
		// 5 Salnés
		
		class ParDiaDocs{
			String dia = "";
			String numDocs = "";
			
			ParDiaDocs(String dia, String numDocs){
				this.dia = dia;
				this.numDocs = numDocs;
			}
		}
		
				
		ArrayList<EstadisticaDia> lista = new ArrayList<EstadisticaDia>();
		String titulo = "";
		String max = "";
		String med = "";
		
		String limiteVertical = "";
			
		
		switch (tipo) {
		case 1:
			  	lista = estadisticaTotal;
			  	titulo = "Total";
			  	max = maximoTotal;
			  	med = getMediaSubidaAnual(false, tipo, lista, año);
			  	break;
		case 2:
				lista = estadisticaIanus;
				titulo = "Ianus";
				max = maximoIanus;
				med = getMediaSubidaAnual(false, tipo, lista, año);
				break;
		case 3:
				lista = estadisticaUrg;
				titulo = "Urgencias";
				max = maximoUrg;
				med =  getMediaSubidaAnual(false, tipo, lista, año);
				break;
		case 4:
				lista = estadisticaXedoc;
				titulo = "Xedoc";
				max = maximoXedoc;
				med = getMediaSubidaAnual(false, tipo, lista, año);
				break;
				
		case 5:
				lista = estadisticaSalnes;
				titulo = "Salnés";
				max = maximoSalnes;
				med = getMediaSubidaAnual(false, tipo, lista, año);
				break;

		}
		

		String mascaraFecha = año + mes;
		ArrayList<ParDiaDocs> pares = new ArrayList<ParDiaDocs>();
			
		for(int i=0;i<lista.size();i++){
			if(lista.get(i).fecha.substring(0,6).equals(mascaraFecha)){
				ParDiaDocs par = new ParDiaDocs(lista.get(i).fecha.substring(6,8), lista.get(i).numeroFicheros);
				if(Integer.valueOf(par.numDocs) > 500  || tipo != 1)
					pares.add(par);
			}
		}
	
		int numeroMes = Integer.valueOf(mes) - 1;
		
		int num = (int) (Integer.valueOf(max) * 1.1);
		
		limiteVertical = String.valueOf(num);
		
		String cadena01 = ""
				+ ""
				+ "FusionCharts.ready(function() {"
					+ "var seleccion, inputs, selElem, "
				    	+ "chartMes = new FusionCharts({"
					    	+ "type : 'column2d',"
					    	+ "renderAt : 'chart5DiasB',"
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
					    			+ "bgcolor : '#ffffff',"
					    			+ "usePlotGradientColor : '1',"
					    		//	+ "useroundedges : '1',"
					    		//	+ "showborder : '0',"
					    			+ "showValues : '0',"
					    			+ "canvasborderthickness: '1',"
					    			+ "canvasborderalpha : '50',"
					    			+ "palettecolors : '#8BBA00',"
					    			+ "},"
					    			
					    		+ "data : "
					    		+ ""
					    		+ "";
		
		String cadena02 = ""
				+ "[";
		
		for(int i=0;i<pares.size();i++){
			cadena02 += (
							"{ label: '" + pares.get(i).dia
					     + "', value: '" + pares.get(i).numDocs + "'}"
						);
			if(i != pares.size()-1){
				cadena02 += ",";
			}
			else{
				cadena02 += "],";
			}
		}
		
		
		if(pares.size() == 0){
			cadena02 += (
					"{ label: '" + "Sin Datos"
			     + "', value: '" + 0 + "'}"
			     + "],"
				);
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
										+ "},"
										+ "{ "
											+ "startvalue : '" + med + "',"
										 	+ "color : '#c40000',"
										 	+ "valueOnRight : '1',"
										 	+ "tooltext : 'Media anual de $startvalue',"
										 	+ "displayvalue : 'Media'"
										 + "}"
										+ "]"
								+ "}"
							   + "]"
						    + "}"
						 + "});"
						 + ""
						 + "chartMes.render('chart5DiasB');"
						 + ""
						 + "var h = document.getElementById('Mes_año');"
						 + "var k = h.getElementsByTagName('option');"
						 + "for(var i=0;i<h.options.length;i++){"
						 	  + "if(k[i].value.localeCompare('" + año + "') == 0){"
						 	  		+ "k[i].selected = 'selected';"
						 	  + "}"
						 + "}"
						 + ""
						 
    					 + "var h = document.getElementById('Mes_mes');"
						 + "var k = h.getElementsByTagName('option');"
						 + "k[" + numeroMes + "].selected = 'selected';"
						 + ""
					+ "})"
					+ "";

		
		
	//	System.out.println(cadena01 + cadena02 + cadena03);
		
		return (cadena01 + cadena02 + cadena03);
	}
	
	
	public String getJSONHistorico(int tipo, String media){

		// 1 Todos
		// 2 Ianus
		// 3 Urg
		// 4 Xedoc
		// 5 Salnés
		
		
		ArrayList<EstadisticaAño> lista = new ArrayList<EstadisticaAño>();
		String titulo = "";
		
		String limiteVertical = "";
		
		switch (tipo) {
		case 1:
			  	lista = estadisticasAnualesT;
			  	titulo = "Total";
			  	break;
		case 2:
				lista = estadisticasAnualesI;
				titulo = "Ianus";
				break;
		case 3:
				lista = estadisticasAnualesU;
				titulo = "Urgencias";
				break;
		case 4:
				lista = estadisticasAnualesX;
				titulo = "Xedoc";
				break;
		case 5:
				lista = estadisticasAnualesS;
				titulo = "Salnés";
				break;
		}

	
		boolean esMedia = false;
		
		if(!media.contains("Total")){
			esMedia = true;
		}
		
		int maximo = 0;
		
		for(int i=0;i< lista.size();i++){
			if(lista.get(i).documentosT > maximo){
				if(esMedia)
					maximo = lista.get(i).mediaDiariaT;
				else
					maximo = lista.get(i).documentosT;
			}
		}
		
		
		int num = (int) (maximo * 1.1);
		
		limiteVertical = String.valueOf(num);
		
		
		

		
		String cadena01 = ""
				+ ""
				+ "FusionCharts.ready(function() {"
					+ "var seleccion, inputs, selElem, "
				    	+ "chartAñoMes = new FusionCharts({"
					    	+ "type : 'column2d',"
					    	+ "renderAt : 'chart5DiasD',"
					    	+ "width : '360',"
					    	+ "height : '240',"
					    	+ "dataFormat : 'json',"
					    	+ "dataSource : {"
					    		+ "chart : {"
					    			+ "caption : '" + titulo + "',"
					    			+ "theme : 'fint',"
					    			+ "yAxisMaxValue : '" + limiteVertical + "',"
					    		//	+ "rotateValues : '0',"
					    			+ "formatNumber : '0',"
					    			+ "formatNumberScale : '0',"
					    			+ "bgcolor : '#ffffff',"
					    		//	+ "plotgradientcolor: '',"
					    		//	+ "basefontcolor: '000000',"
					    		//	+ "usePlotGradientColor : '1',"
					    			+ "useroundedges : '1',"
					    			+ "showborder : '0',"
					    		//	+ "showValues : '0',"
					    		//	+ "canvasborderthickness: '1',"
					    		//	+ "canvasborderalpha : '50',"
					    		    + "palettecolors : '#008ee4,#6baa01,#f8bd19,#e44a00,#33bdda',"
					    			+ "},"
					    			
					    		+ "data : "
					    		+ ""
					    		+ "";
		
		String cadena02 = ""
				+ "[";
		
		for(int i=0;i<lista.size();i++){
			
			if(!esMedia){
				cadena02 += (
						"{ label: '" + lista.get(i).año
				     + "', value: '" + lista.get(i).documentosT + "'}"
					);
			}
			else{
				cadena02 += (
						"{ label: '" + lista.get(i).año
				     + "', value: '" + lista.get(i).mediaDiariaT + "'}"
					);
			}

			if(i != lista.size()-1){
				cadena02 += ",";
			}
			else{
				cadena02 += "]";
			}
		}
		
		if(lista.size() == 0){
			cadena02 += (
					"{ label: '" + "Sin Datos"
			     + "', value: '" + 0 + "'}"
			     + "]"
				);
		}
		
		String cadena03 = ""
			/*				+ "trendlines : ["
								+ "{"
									+ "line: ["
										+ "{ "
											+ "startvalue : '" + maximo + "',"
										 	+ "color : '#1aaf5d',"
										 	+ "valueOnRight : '1',"
										 	+ "tooltext : 'Maxima subida diaria de $startvalue',"
										 	+ "displayvalue : 'Máximo'"
										+ "},"
										+ "{ "
											+ "startvalue : '" + lista.get(0) + "',"
										 	+ "color : '#c40000',"
										 	+ "valueOnRight : '1',"
										 	+ "tooltext : 'Media de subida diaria de $startvalue',"
										 	+ "displayvalue : 'Media'"
										 + "}"
										+ "]"
								+ "}"
							   + "]"  */
						    + "}"
						 + "});"
						 + ""
						 + "chartAñoMes.render('chart5DiasD');"
						 
					
					+ "})"
					+ "";

		
		
	//	System.out.println(cadena01 + cadena02 + cadena03);
		
		return (cadena01 + cadena02 + cadena03);
	}
	
	public String getJSONAnualMes(int tipo, String año){

		// 1 Todos
		// 2 Ianus
		// 3 Urg
		// 4 Xedoc
		// 5 Salnés
		
		class ParMesDocs{
			String mes = "";
			String numDocs = "";
			
			ParMesDocs(String mes, String numDocs){
				this.mes = mes;
				this.numDocs = numDocs;
			}
		}
		
				
		ArrayList<EstadisticaDia> lista = new ArrayList<EstadisticaDia>();
		String titulo = "";
		
		String limiteVertical = "";
		
		switch (tipo) {
		case 1:
			  	lista = estadisticaTotal;
			  	titulo = "Total";
			  	break;
		case 2:
				lista = estadisticaIanus;
				titulo = "Ianus";
				break;
		case 3:
				lista = estadisticaUrg;
				titulo = "Urgencias";
				break;
		case 4:
				lista = estadisticaXedoc;
				titulo = "Xedoc";
				break;
		case 5:
				lista = estadisticaSalnes;
				titulo = "Salnés";
				break;
		}

		
		String mascaraFecha = año;
		ArrayList<ParMesDocs> pares = new ArrayList<ParMesDocs>();
		
		int sumTotal = 0;
		int maximo = 0;
		int media = 0;
			
		for(int i=0;i<lista.size();i++){
			
		//	System.out.println(lista.get(i).fecha);
			
			if(lista.get(i).fecha.substring(0,4).equals(mascaraFecha)){
				
				String mes = lista.get(i).fecha.substring(4,6);
				
				int sum = 0;
				int numDias = 0;
				
		//		System.out.println();
				
				boolean control = false;
				
				while(i < lista.size()  && lista.get(i).fecha.substring(4,6).equals(mes)){
					control = true;
		//			System.out.println(lista.get(i).fecha + "....   " + Integer.valueOf(lista.get(i).numeroFicheros));
					
					sum = sum + Integer.valueOf(lista.get(i).numeroFicheros);
					numDias++;
					
					i++;
				}
				
				if(control){
					i--;
				}
				
				if(sum > maximo){
					maximo = sum;
				}
				
				sumTotal += sum;
				
				mes = getMes(mes);
				
				ParMesDocs par = new ParMesDocs(mes, String.valueOf(sum));
				pares.add(par);
			}
		}
	
		int numPares = pares.size();
		
		/*
		for(int i=0;i<numPares;i++){
			System.out.println(pares.get(i).mes + " -- " + pares.get(i).numDocs);
		}
		*/
		
		if(numPares == 0)
			media = 0;
		else
			media = sumTotal / pares.size();
		
		
		int num = (int) (maximo * 1.1);
		
		limiteVertical = String.valueOf(num);
		
		String cadena01 = ""
				+ ""
				+ "FusionCharts.ready(function() {"
					+ "var seleccion, inputs, selElem, "
				    	+ "chartAñoMes = new FusionCharts({"
					    	+ "type : 'column2d',"
					    	+ "renderAt : 'chart5DiasC',"
					    	+ "width : '360',"
					    	+ "height : '240',"
					    	+ "dataFormat : 'json',"
					    	+ "dataSource : {"
					    		+ "chart : {"
					    			+ "caption : '" + titulo + "',"
					    			+ "theme : 'fint',"
					    			+ "yAxisMaxValue : '" + limiteVertical + "',"
					    		//	+ "rotateValues : '0',"
					    			+ "formatNumber : '0',"
					    			+ "formatNumberScale : '0',"
					    			+ "bgcolor : '#ffffff',"
					    		//	+ "plotgradientcolor: '',"
					    			+ "basefontcolor: '000000',"
					    			+ "usePlotGradientColor : '1',"
					    		//	+ "useroundedges : '1',"
					    			+ "showborder : '0',"
					    		//	+ "showValues : '0',"
					    			+ "canvasborderthickness: '1',"
					    			+ "canvasborderalpha : '50',"
					    			+ "palettecolors : '#e44a00',"
					    			+ "},"
					    			
					    		+ "data : "
					    		+ ""
					    		+ "";
		
		String cadena02 = ""
				+ "[";
		
		for(int i=0;i<pares.size();i++){
			cadena02 += (
							"{ label: '" + pares.get(i).mes
					     + "', value: '" + pares.get(i).numDocs + "'}"
						);
			if(i != pares.size()-1){
				cadena02 += ",";
			}
			else{
				cadena02 += "],";
			}
		}
		
		if(pares.size() == 0){
			cadena02 += (
					"{ label: '" + "Sin Datos"
			     + "', value: '" + 0 + "'}"
			     + "],"
				);
		}
		
		String cadena03 = ""
							+ "trendlines : ["
								+ "{"
									+ "line: ["
										+ "{ "
											+ "startvalue : '" + maximo + "',"
										 	+ "color : '#1aaf5d',"
										 	+ "valueOnRight : '1',"
										 	+ "tooltext : 'Maxima subida de $startvalue',"
										 	+ "displayvalue : 'Máximo'"
										+ "},"
										+ "{ "
											+ "startvalue : '" + media + "',"
										 	+ "color : '#c40000',"
										 	+ "valueOnRight : '1',"
										 	+ "tooltext : 'Media mensual de $startvalue',"
										 	+ "displayvalue : 'Media'"
										 + "}"
										+ "]"
								+ "}"
							   + "]"
						    + "}"
						 + "});"
						 + ""
						 + "chartAñoMes.render('chart5DiasC');"
						 
						 + "var h = document.getElementById('Año_año');"
						 + "var k = h.getElementsByTagName('option');"
						 + "for(var i=0;i<h.options.length;i++){"
						 	  + "if(k[i].value.localeCompare('" + año + "') == 0){"
						 	  		+ "k[i].selected = 'selected';"
						 	  + "}"
						 + "}"
					
					+ "})"
					+ "";

		
		
	//	System.out.println(cadena01 + cadena02 + cadena03);
		
		return (cadena01 + cadena02 + cadena03);
	}
	
	public String getJSONTipoGrafico(int tipo, String tipoGrafico){
		
		class ParMesDocs{
			String mes = "";
			String numDocs = "";
			
			ParMesDocs(String mes, String numDocs){
				this.mes = mes;
				this.numDocs = numDocs;
			}
		}
		
		
		class DatosGraficoAnual{
			String año = "";
			ArrayList<ParMesDocs> pares = new ArrayList<ParMesDocs>();
			
			DatosGraficoAnual(String año, ArrayList<ParMesDocs> pares){
				this.año = año;
				this.pares = pares;
			}
		}
		
		
		ArrayList<DatosGraficoAnual> datosGraficosAnuales = new ArrayList<DatosGraficoAnual>();
		
				
		ArrayList<EstadisticaDia> lista = new ArrayList<EstadisticaDia>();
		String titulo = "";
		
		String limiteVertical = "";
		
		switch (tipo) {
		case 1:
			  	lista = estadisticaTotal;
			  	titulo = "Total";
			  	break;
		case 2:
				lista = estadisticaIanus;
				titulo = "Ianus";
				break;
		case 3:
				lista = estadisticaUrg;
				titulo = "Urgencias";
				break;
		case 4:
				lista = estadisticaXedoc;
				titulo = "Xedoc";
				break;
		case 5:
				lista = estadisticaSalnes;
				titulo = "Salnés";
				break;
		}

		String mascaraFecha[] = {"2014", "2015"};
		
		
		
				//new ArrayList<ParMesDocs>();
		
		int sumTotal = 0;
		int maximo = 0;
		int media = 0;
		
		String añoActual = lista.get(0).fecha.substring(0,4);
/*		
		System.out.println("**********************************************************************");
		for(int i=0;i<lista.size();i++){
			if(lista.get(i).fecha.substring(0,4).equals("2016")){
				System.out.println(lista.get(i).fecha + " " + lista.get(i).diaSemana + " " + lista.get(i).numeroFicheros);
			}
		}
		System.out.println("**********************************************************************");
*/		
		for(int i=0;i<lista.size();i++){
			
			ArrayList<ParMesDocs> pares = new ArrayList<ParMesDocs>();
			
			while(i<lista.size() 
						&& lista.get(i).fecha.substring(0,4).equals(añoActual)){
				
				String mes = lista.get(i).fecha.substring(4,6);
				
				
				
				int sum = 0;
				

				while(i<lista.size() 
						&& lista.get(i).fecha.substring(0,4).equals(añoActual) 
						&& lista.get(i).fecha.substring(4,6).equals(mes)){

				
					sum = sum + Integer.valueOf(lista.get(i).numeroFicheros);
					i++;
				}
				
				
				if(sum > maximo){
					maximo = sum;
				}
				
				sumTotal += sum;
				
				mes = getMes(mes);
				
				ParMesDocs par = new ParMesDocs(mes, String.valueOf(sum));
				pares.add(par);
								
			}
			
			datosGraficosAnuales.add(new DatosGraficoAnual(añoActual, pares));
			if(i<lista.size()){
				añoActual = lista.get(i).fecha.substring(0,4);
			}
			i--;
		}
		
		
		/*   Codigo para inferir estadisticas anteriores
		     Urgencias empieza 01/06/2009 hasta 01/03/2014
		     Docum. empieza 01/06/2011 hasta 01/09/2014
		     Xedoc  empieza 01/02/2015
		 */
		
		/* Urgencias*/
		
		ArrayList<DatosGraficoAnual> datosInferidos = new ArrayList<DatosGraficoAnual>();
		
		for(int i=2014;i<2016;i++){
			
			
			if(tipo == 5){
				ArrayList<ParMesDocs> parMesSal = new ArrayList<ParMesDocs>();
				for(int j=0;j<12;j++){
					if(i < 2016){
						parMesSal.add(new ParMesDocs(this.getMes(j+1), "0"));
					}
					if(i== 2016){
						if(j< 2){
							parMesSal.add(new ParMesDocs(this.getMes(j+1), "0"));
						}
						else{
							parMesSal.add(datosGraficosAnuales.get(0).pares.get(j-1));
						}
					}
					
				}
				datosInferidos.add(new DatosGraficoAnual(String.valueOf(i), parMesSal));
			}
			
			
			// Xedoc
			if(tipo == 4){
				ArrayList<ParMesDocs> parMesXedoc = new ArrayList<ParMesDocs>();
				for(int j=0;j<12;j++){
					if(i==2014){
						parMesXedoc.add(new ParMesDocs(this.getMes(j+1), "0"));
					}
					if(i== 2015){
						if(j== 0){
							parMesXedoc.add(new ParMesDocs(this.getMes(j+1), "0"));
						}
						else{
							parMesXedoc.add(datosGraficosAnuales.get(0).pares.get(j-1));
						}
					}

					
				}
				datosInferidos.add(new DatosGraficoAnual(String.valueOf(i), parMesXedoc));
			}
			
			// Urgencias
			if(tipo == 3){
				ArrayList<ParMesDocs> parMesUrgencias = new ArrayList<ParMesDocs>();
				for(int j=0;j<12;j++){
					if(i== 2014){
						if(j < 2){
							parMesUrgencias.add(new ParMesDocs(this.getMes(j+1), "14530"));
						}
						else{
							parMesUrgencias.add(datosGraficosAnuales.get(0).pares.get(j));
						}
					}

					
				}
				if(i == 2014)
					datosInferidos.add(new DatosGraficoAnual(String.valueOf(i), parMesUrgencias));
			}
			
			
			// Ianus
			if(tipo == 2){
				ArrayList<ParMesDocs> parMesIanus = new ArrayList<ParMesDocs>();
				for(int j=0;j<12;j++){

					if(i== 2014){
						if(j < 9){
							parMesIanus.add(new ParMesDocs(this.getMes(j+1), "25105"));
						}
						else{
							parMesIanus.add(datosGraficosAnuales.get(0).pares.get(j-9));
						}
					}
					
				}
				if(i == 2014)
					datosInferidos.add(new DatosGraficoAnual(String.valueOf(i), parMesIanus));
			}
			
			// Total
			if(tipo == 1){
				ArrayList<ParMesDocs> parMesTotal = new ArrayList<ParMesDocs>();
				for(int j=0;j<12;j++){
					if(i== 2014){
						if(j < 2){
							int totalN = (14530 + 25105);
							String total = "" + totalN;
							parMesTotal.add(new ParMesDocs(this.getMes(j+1), total));
						}
						else if(j < 9){
							int totalN = (25105 + Integer.valueOf(datosGraficosAnuales.get(0).pares.get(j).numDocs));
							String total = "" + totalN;
							parMesTotal.add(new ParMesDocs(this.getMes(j+1), total));
						}
						else{
							parMesTotal.add(datosGraficosAnuales.get(0).pares.get(j));
						}
					}
				}
				if(i == 2014)
					datosInferidos.add(new DatosGraficoAnual(String.valueOf(i), parMesTotal));
			}
		}
		
		
		if(tipo == 4){
			for(int i=0;i<datosGraficosAnuales.size();i++){
				if(!datosGraficosAnuales.get(i).año.equals("2015"))
				datosInferidos.add(datosGraficosAnuales.get(i));
			}
			
			datosGraficosAnuales = datosInferidos;
		}
		
		if(tipo == 2 || tipo == 3 || tipo == 1){
			
			for(int i=0;i<datosGraficosAnuales.size();i++){
				if(!datosGraficosAnuales.get(i).año.equals("2014"))
				datosInferidos.add(datosGraficosAnuales.get(i));
			}
			
			datosGraficosAnuales = datosInferidos;
		}
/*
		if(tipo == 5){
			for(int i=0;i<datosGraficosAnuales.size();i++){
				if(!datosGraficosAnuales.get(i).año.equals("2016"))
				datosInferidos.add(datosGraficosAnuales.get(i));
			}
			
			datosGraficosAnuales = datosInferidos;
		}
*/	
		
		/*
		System.out.println("------------------------- Datos reales totales");
		
		for(int i=0;i<datosGraficosAnuales.size();i++){
			for(int j=0;j<datosGraficosAnuales.get(i).pares.size();j++){
				System.out.println(datosGraficosAnuales.get(i).año + "    " + datosGraficosAnuales.get(i).pares.get(j).mes 
						+ datosGraficosAnuales.get(i).pares.get(j).numDocs );
			}
			System.out.println("-------------------------");
		}
		*/
		
		/*
		for(int i=2009;i<2014;i++){
			
		//	DatosGraficoAnual datosUrgencias = new DatosGraficoAnual(i, pares)
			ArrayList<ParMesDocs> parMesUrgencias = new ArrayList<ParMesDocs>();
			ArrayList<ParMesDocs> parMesIanus = new ArrayList<ParMesDocs>();
			
			for(int j=0;j<12;j++){
				
				if(i==2009){
					if(j<6){
						parMesUrgencias.add(new ParMesDocs(this.getMes(j+1), "0"));
					}
					else{
						parMesUrgencias.add(new ParMesDocs(this.getMes(j+1), "6431"));
					}
					parMesIanus.add(new ParMesDocs(this.getMes(j+1), "0"));
				}
				
				if(i==2010){
					parMesUrgencias.add(new ParMesDocs(this.getMes(j+1), "7116"));
					parMesIanus.add(new ParMesDocs(this.getMes(j+1), "0"));
				}
				
				if(i==2011){
					if(j<6){
						parMesIanus.add(new ParMesDocs(this.getMes(j+1), "0"));
					}
					else{
						parMesIanus.add(new ParMesDocs(this.getMes(j+1), "7947"));
					}
					parMesUrgencias.add(new ParMesDocs(this.getMes(j+1), "7420"));
				}
				
				if(i == 2012){
					parMesUrgencias.add(new ParMesDocs(this.getMes(j+1), "7673"));
					parMesIanus.add(new ParMesDocs(this.getMes(j+1), "16504"));
				}
				
				if(i == 2013){
					parMesUrgencias.add(new ParMesDocs(this.getMes(j+1), "6780"));
					parMesIanus.add(new ParMesDocs(this.getMes(j+1), "21765"));
				}
				
				if(i == 2014){
					if(j<2){
						parMesUrgencias.add(new ParMesDocs(this.getMes(j+1), "14530"));
						parMesIanus.add(new ParMesDocs(this.getMes(j+1), "25105"));
					}
					else if(j < 9){
						parMesIanus.add(new ParMesDocs(this.getMes(j+1), "25105"));
					}
				}
			}
			
			datosInferidos.add(new DatosGraficoAnual(String.valueOf(i), parMesUrgencias));
		}
		
		
		
		
		JOptionPane.showMessageDialog(null, "Pausa. Mirar consola");
		
		
		System.out.println("------------------------- Datos inferidos de urgencias");
		
		for(int i=0;i<datosInferidos.size();i++){
			for(int j=0;j<datosInferidos.get(i).pares.size();j++){
				System.out.println(datosInferidos.get(i).año + "    " + datosInferidos.get(i).pares.get(j).mes 
						+ datosInferidos.get(i).pares.get(j).numDocs );
			}
			System.out.println("-------------------------");
		}
		
		
		System.out.println("------------------------- Datos reales totales");
		
		for(int i=0;i<datosGraficosAnuales.size();i++){
			for(int j=0;j<datosGraficosAnuales.get(i).pares.size();j++){
				System.out.println(datosGraficosAnuales.get(i).año + "    " + datosGraficosAnuales.get(i).pares.get(j).mes 
						+ datosGraficosAnuales.get(i).pares.get(j).numDocs );
			}
			System.out.println("-------------------------");
		}
		
		// Fusion Ingresos
		if(tipo == 2){
			for(int i=0;i<datosGraficosAnuales.size();i++){
				datosInferidos.add(datosGraficosAnuales.get(i));
			}
			
			datosGraficosAnuales = datosInferidos;
		}
		
		*/
		
		
	//	System.out.println("Tamaño del array... " + datosGraficosAnuales.size());
		
	/*	System.out.println(datosGraficosAnuales.get(0).pares.get(0).mes);
	/*	System.out.println(datosGraficosAnuales.get(1).pares.get(0).mes);
		
		/*
		for(int i=0;i<lista.size();i++){
			if(lista.get(i).fecha.substring(0,4).equals(mascaraFecha)){
				
				String mes = lista.get(i).fecha.substring(4,6);
				
				int sum = 0;
				
				while(i < lista.size()  && lista.get(i).fecha.substring(4,6).equals(mes)){
					sum = sum + Integer.valueOf(lista.get(i).numeroFicheros);
					i++;
				}
				
				if(sum > maximo){
					maximo = sum;
				}
				
				sumTotal += sum;
				
				mes = getMes(mes);
				
				ParMesDocs par = new ParMesDocs(mes, String.valueOf(sum));
				pares.add(par);
			}
		}
	
		int numPares = pares.size();
		if(numPares == 0)
			media = 0;
		else
			media = sumTotal / pares.size();
		
		*/
		
		int num = (int) (maximo * 1.1);
		
		limiteVertical = String.valueOf(num);
		
		String cadena01 = ""
				+ ""
				+ "FusionCharts.ready(function() {"
					+ "var seleccion, inputs, selElem, "
				    	+ "chartAñoMes = new FusionCharts({"
					    	+ "type : '"  + tipoGrafico  + "',"
					    	+ "renderAt : 'chart5DiasE',"
					    	+ "width : '760',"
					    	+ "height : '200',"
					    	+ "dataFormat : 'json',"
					    	+ "dataSource : {"
					    		+ "chart : {"

									+ "useroundedges : '1',"
					    		
					    		//	+ "caption : '" + titulo + "',"
					    			+ "theme : 'fint',"
					    			+ "yAxisMaxValue : '" + limiteVertical + "',"
					    		//	+ "rotateValues : '0',"
					    			+ "formatNumber : '0',"
					    			+ "formatNumberScale : '0',"
					    			+ "bgcolor : '#ffffff',"
					    		//	+ "plotgradientcolor: '',"
					    			+ "basefontcolor: '000000',"
					    			+ "usePlotGradientColor : '1',"
					    		//	+ "useroundedges : '1',"
					    			+ "showborder : '0',"
					    		//	+ "showValues : '0',"
					    			+ "canvasborderthickness: '1',"
					    			+ "canvasborderalpha : '50',"
					    			+ "palettecolors : '#6baa01,#e44a00,#33bdda'"
					    			+ "},"
					    			
					    		+ "categories : ["
					    			+ "{"
					    				+ "category : ["
					    					+ "{ label : 'Ene'},"
					    					+ "{ label : 'Feb'},"
					    					+ "{ label : 'Mar'},"
					    					+ "{ label : 'Abr'},"
					    					+ "{ label : 'May'},"
					    					+ "{ label : 'Jun'},"
					    					+ "{ label : 'Jul'},"
					    					+ "{ label : 'Ago'},"
					    					+ "{ label : 'Sep'},"
					    					+ "{ label : 'Oct'},"
					    					+ "{ label : 'Nov'},"
					    					+ "{ label : 'Dic'}"
					    				+ "]"
					    			+ "}"
					    		+ "],"
					    		
					    		+ "dataset : ["
					    			+ "";
		/*
		String cadena02b = ""
				+ "";
		*/
		
		
		String cadena02 = ""
				+ "";
		
		for(int i=0;i<datosGraficosAnuales.size();i++){
			cadena02 += (
					
				"{"
					+ "seriesname : '" + datosGraficosAnuales.get(i).año + "',"
					+ "data : ["
					);
			
			for(int j=0, z=0;j<12;j++){
				
				if(z<datosGraficosAnuales.get(i).pares.size()){
					if(!datosGraficosAnuales.get(i).pares.get(z).mes.equals(getMes(j+1))){
						cadena02 += (
								"{ value : '0' }"
								
								);
						if(j != 11){
							cadena02 += ",";
						}
						z++;
					}
					else{
						cadena02 += (
								"{ value : '" + datosGraficosAnuales.get(i).pares.get(z).numDocs + "'}"
								
								);
						
						if(j != 11){
							cadena02 += ",";
						}
						z++;
					}
				}
				else{
					cadena02 += (
							"{ value : '0' }"
							
							);
					if(j != 11){
						cadena02 += ",";
					}
					z++;
				}

			}
			
			cadena02 += ""
						+ "]"
					+ "}";
			
			if(i != datosGraficosAnuales.size()-1){
				cadena02 += ",";
			}
		}
		
		cadena02 += "],";
		
	/*	
		for(int i=0;i<pares.size();i++){
			cadena02 += (
							"{ label: '" + pares.get(i).mes
					     + "', value: '" + pares.get(i).numDocs + "'}"
						);
			if(i != pares.size()-1){
				cadena02 += ",";
			}
			else{
				cadena02 += "],";
			}
		}
		
		if(pares.size() == 0){
			cadena02 += (
					"{ label: '" + "Sin Datos"
			     + "', value: '" + 0 + "'}"
			     + "],"
				);
		}
		
		*/
		
		
		String cadena03 = ""
							+ "trendlines : ["
								+ "{"
									+ "line: ["
										+ "{ "
											+ "startvalue : '" + maximo + "',"
										 	+ "color : '#1aaf5d',"
										 	+ "valueOnRight : '1',"
										 	+ "tooltext : 'Maxima subida de $startvalue',"
										 	+ "displayvalue : 'Máximo'"
										+ "}"
										+ ""
								/*		+ ""
										+ ","
										+ "{ "
											+ "startvalue : '" + 40000 + "',"
										 	+ "color : '#c40000',"
										 	+ "valueOnRight : '1',"
										 	+ "tooltext : 'Media anual de $startvalue',"
										 	+ "displayvalue : 'Media'"                          
										 + "}"
										 + ""
										 + ""  */
										+ "]"
								+ "}"
							   + "]"
						    + "}"
						 + "});"
						 + ""
						 + "chartAñoMes.render('chart5DiasE');"
					+ "});"
					+ ""
					+ "var h = document.getElementById('cargando');"
					+ "h.style.visibility = 'visible';"
					+ ""
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

	private ArrayList<String> getAños(ArrayList<EstadisticaDia> estadistica){
		
		ArrayList<String> años = new ArrayList<String>();
		String aux = "";
		
		for(int i=0;i<estadistica.size();i++){
			String an = estadistica.get(i).fecha.substring(0, 4);
			if(!aux.equals(an)){
				aux = an;
				años.add(aux);
			}
		}
		
		return años;
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
	
	
	private String getMediaSubidaAnual(boolean mensual, int tipo, ArrayList<EstadisticaDia> estadistica, String año){
		
		if(mensual){
			int suma = 0;
			int media = 0;
			int dias = 0;
			
			for(int i=0;i<estadistica.size();i++){
				String fecha = estadistica.get(i).fecha;
				if(fecha.substring(0,4).equals(año)){
					dias++;
					int cantidad = Integer.valueOf(estadistica.get(i).numeroFicheros);
					suma = suma + cantidad;
				}
			}
			if(dias != 0){
				media = suma / dias;
			}
			else{
				media = 0;
			}
			
			return String.valueOf(media);
		}
		else{
			
			ArrayList<EstadisticaAño> lista = new ArrayList<EstadisticaAño>();
			
			switch (tipo) {
			case 1:
				  	lista = estadisticasAnualesT;
				  	break;
			case 2:
					lista = estadisticasAnualesI;
					break;
			case 3:
					lista = estadisticasAnualesU;
					break;
			case 4:
					lista = estadisticasAnualesX;
					break;
			case 5:
					lista = estadisticasAnualesS;
					break;
			}
			
			int media = 0;
			
			for(int i=0;i<lista.size();i++){
				if(año.equals(lista.get(i).año)){
					media = lista.get(i).mediaDiariaT;
					break;
				}
			}
			
			return String.valueOf(media);
		}
		
		
		
	}
	
	
	private void recolocaSabados(ArrayList<EstadisticaDia> lista){
		
	//	System.out.println("Tamaño... " + lista.size());
		for(int i=0;i<lista.size();i++){
	//		System.out.println(lista.get(i).diaSemana + "  " + lista.get(i).fecha);
			if(lista.get(i).diaSemana.equals("Sábado")){
				if(i>0){
					int numDocSabado = Integer.valueOf(lista.get(i).numeroFicheros);
					int numDocAnterior = Integer.valueOf(lista.get(i-1).numeroFicheros);
					int suma = numDocAnterior + numDocSabado;
					lista.get(i-1).numeroFicheros = String.valueOf(suma);
					lista.remove(i);i--;
				}
			}
		}
		
		/*
		System.out.println("Tamaño... " + lista.size());
		
		for(int i=0;i<lista.size();i++){
			System.out.println(lista.get(i).fecha + "  -  " + lista.get(i).diaSemana);
		}
		*/
	}
	
	
	public String inicializaAños(){
		
		String aux = "var años = [";
		
		for(int i=años.size()-1;i>=0;i--){
			
			aux += "'" + años.get(i) + "'";
			if(i == 0){
				aux += "];";
			}
			else{
				aux += ",";
			}
		}
		
		aux += "putAños('Mes_año',años);";
		aux += "putAños('Año_año',años);";
		aux += "putAños('tarta_año',años);"
				;
		/*
		+ "var años = ['2014','2015','2016','2017'];"
		+ "putAños('Mes_año',años);"
		*/
		
		return aux;
	}
	
	private String getMes(String mes){
		
		int mesNum = Integer.valueOf(mes);
		
		switch (mesNum) {
		case 1:
			mes = "Ene";
			break;
		case 2:
			mes = "Feb";
			break;
		case 3:
			mes = "Mar";
			break;
		case 4:
			mes = "Abr";
			break;
		case 5:
			mes = "May";
			break;
		case 6:
			mes = "Jun";
			break;
		case 7:
			mes = "Jul";
			break;
		case 8:
			mes = "Ago";
			break;
		case 9:
			mes = "Sep";
			break;
		case 10:
			mes = "Oct";
			break;
		case 11:
			mes = "Nov";
			break;
		case 12:
			mes = "Dic";
			break;

		}
		
		return mes;
	}
	
	
	
	private String getMes(int mesNum){

		String mes = "";
		
		switch (mesNum) {
		case 1:
			mes = "Ene";
			break;
		case 2:
			mes = "Feb";
			break;
		case 3:
			mes = "Mar";
			break;
		case 4:
			mes = "Abr";
			break;
		case 5:
			mes = "May";
			break;
		case 6:
			mes = "Jun";
			break;
		case 7:
			mes = "Jul";
			break;
		case 8:
			mes = "Ago";
			break;
		case 9:
			mes = "Sep";
			break;
		case 10:
			mes = "Oct";
			break;
		case 11:
			mes = "Nov";
			break;
		case 12:
			mes = "Dic";
			break;

		}
		
		return mes;
	}
	
}

class FechaGraficos{
	String dia = "";
	String mes = "";
	String año = "";
	
	FechaGraficos(String fecha) {
		// TODO Auto-generated method stub
		año = fecha.substring(0,4);
		mes = fecha.substring(4,6);
		int aux = Integer.valueOf(mes);
		mes = getNombreMes(aux);
		dia = fecha.substring(6);
	}
	
	private String getNombreMes(int mes){
		
		mes--;
		
		String nombre = "";
		
		switch(mes){
		case 0:	nombre = "Enero";break;
		case 1:	nombre = "Febrero";break;
		case 2:	nombre = "Marzo";break;
		case 3:	nombre = "Abril";break;
		case 4:	nombre = "Mayo";break;
		case 5:	nombre = "Junio";break;
		case 6:	nombre = "Julio";break;
		case 7:	nombre = "Agosto";break;
		case 8:	nombre = "Septiembre";break;
		case 9:	nombre = "Octubre";break;
		case 10:	nombre = "Noviembre";break;
		case 11:	nombre = "Diciembre";break;
		}
		
		return nombre;
	}
}

class DatosEstimados{
	
	private String año;
	private int multiplicador;
	private int mesInicio;
	private int numeroMeses;
	
	public DatosEstimados(String año, int multiplicador, int mesInicio,
			int numeroMeses) {
		super();
		this.año = año;
		this.multiplicador = multiplicador;
		this.mesInicio = mesInicio;
		this.numeroMeses = numeroMeses;
	}

	public String getAño() {
		return año;
	}

	public void setAño(String año) {
		this.año = año;
	}

	public int getMultiplicador() {
		return multiplicador;
	}

	public void setMultiplicador(int multiplicador) {
		this.multiplicador = multiplicador;
	}

	public int getMesInicio() {
		return mesInicio;
	}

	public void setMesInicio(int mesInicio) {
		this.mesInicio = mesInicio;
	}

	public int getNumeroMeses() {
		return numeroMeses;
	}

	public void setNumeroMeses(int numeroMeses) {
		this.numeroMeses = numeroMeses;
	}

}

class EstadisticaAño{
	
	String año = "";
	String centro = "";
	
	int documentosT = 0;
	int maximoDiarioT = 0;
	int numeroDiasT = 0;
	int mediaDiariaT = 0;
	
	EstadisticaAño(String año, String centro, int documentosT, int numerodiasT, int mediaDiariaT){
		this.año = año;
		this.centro = centro;
		this.documentosT = documentosT;
		this.numeroDiasT = numerodiasT;
		this.mediaDiariaT = mediaDiariaT;
	}
	
	EstadisticaAño(String año, String centro, ArrayList<EstadisticaDia> estadisticaDia){
		this.año = año; this.centro = centro;	
		
		int añoInt = Integer.valueOf(año);
		
		for(int i=0;i<estadisticaDia.size();i++){
			if(año.equals(estadisticaDia.get(i).fecha.substring(0,4))){
				
				int num = Integer.valueOf(estadisticaDia.get(i).numeroFicheros);
				if(num > maximoDiarioT){
					maximoDiarioT = num;
				}
				documentosT += num;
				numeroDiasT++;
			}
			else if(añoInt < Integer.valueOf(estadisticaDia.get(i).fecha.substring(0,4))){
				break;
			}
			
		}
		System.out.print("Año: " + año + ". Centro: " + centro + ". ");
		System.out.println("Numero de dias... " + numeroDiasT + "  número de docs.... " +  documentosT);
		
		if(numeroDiasT != 0){
			mediaDiariaT = documentosT / numeroDiasT;
		}
	}
}
