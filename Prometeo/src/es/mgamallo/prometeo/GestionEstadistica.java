package es.mgamallo.prometeo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GestionEstadistica {

	ArrayList<String> listaIanus = new ArrayList<String>();
	ArrayList<String> listaUrg = new ArrayList<String>();
	ArrayList<String> listaXedoc = new ArrayList<String>();
	
	ArrayList<EstadisticaDia> estadisticaIanus = new ArrayList<EstadisticaDia>();
	ArrayList<EstadisticaDia> estadisticaUrg = new ArrayList<EstadisticaDia>();
	ArrayList<EstadisticaDia> estadisticaXedoc = new ArrayList<EstadisticaDia>();
	
	
	static public void main(String args[]){
		new GestionEstadistica();
	}
	
	public GestionEstadistica() {
		// TODO Auto-generated constructor stub
		
		listaXedoc = leerFicheroEstadistica("C:\\desarrollo\\git\\prometeo\\prometeo\\Prometeo\\Prometeo\\Estadisticas\\Xedoc.txt");

		estadisticaXedoc = convertirEstadisticaDia(listaXedoc);
		
		System.out.println(estadisticaXedoc.get(1).fecha);
		String codigoXML5Dias = getXML5Dias(estadisticaXedoc);
		
		System.out.println(codigoXML5Dias);
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
	
	
	
	private String getXML5Dias(ArrayList<EstadisticaDia> estadistica){
		
		
		String cadena1 = ""
				+ ""
				+ "<chart caption='Gráfica Semanal Total' theme='fint' bgcolor='FFFFFF' useroundedges='1' showborder='0'>"
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
}
