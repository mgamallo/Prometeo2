package es.mgamallo.prometeo;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;

import chrriis.dj.nativeswing.swtimpl.demo.examples.webbrowser.JavascriptExecution;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


public class InicioIanus {

	static final String RUTA = "j:/digitalización/00 documentacion/03 Firmado";
	static final String RUTAB = "H:/DIGITALIZACIÓN/00 DOCUMENTACION/03 Firmado";
	static final String RUTAURG = "j:/DIGITALIZACIÓN/01 INFORMES URG (Colectiva)";
	static final String RUTAURGB = "H:/DIGITALIZACIÓN/01 INFORMES URG (Colectiva)";
static final String RUTAPC = "c:/ianus/ianus.txt";

	static final String CONSENTIMIENTO = "Consentimento informado";
	static final String EKG = "ECG";
	static final String ECO = "Ecografía";
	static final String LISTAESPERA = "Folla inclusión LE";
	static final String MANOMETRIA = "Manometría";
	static final String PHMETRIA = "Phmetría";
	static final String ENFERMERIA_ENDOSCOPIAS = "Folla enfermaría endoscopias";
	static final String ENDOSCOPIA_DIGESTIVA = "Endoscopia Digestiva";
	static final String INTERCONSULTA = "Interconsulta";
	
	static final String PRUEBAS_DIAGNOSTICAS = "Proba diagnóstica";
	static final String PRICK = "Prick test";
	static final String ESPIROMETRIA = "Espirometría";
	static final String PRUEBAS_EPICUTANEAS = "Probas epicutáneas";
	static final String PRUEBAS_PROVOCACION = "Probas provocación";
	
	static final String INFORMEURG = "Informe alta";
	static final String ENFERMERIAURG = "Folla enfermaría urxencias";
	
	static final String DOC_ANULADO = "Documento cancelado";
	static final String DESCONOCIDO = "Des";
	static final String HOSP = "HOSP";
	static final String HOSP_JACOB = "HOS:";
	static final String URG = "URG";
	static final String URG_JACOB = "URG:";
	static final String CIA = "CIA";
	static final String CIA_JACOB = "CIA:";

	static boolean botonAsociarAuto = true;
	static boolean buscaNodoAuto = true;
	static boolean buscaDatosPacientesAuto = true;
	
	static boolean versionar = false;
	
	static boolean ventanaModalUp = false;
	
	static boolean vAuto = false;
	
    static ActiveXComponent oShell;  
    static ActiveXComponent oWindows; 

	static int documentacion = 1; // 0 Urgencias
									// 1 Documentacion

	
	String rutaCarpeta = "";
	static String[] rutaCompletaPdfs;
	static File[] tandaDePdfs;
	
	static String tipoSubida = "";



	static Nodo listaNodos[];
	
	static DefaultListModel listaModelNombresComunes = new DefaultListModel();
	static DefaultListModel listaModelHabituales1 = new DefaultListModel();
	static DefaultListModel listaModelHabituales2 = new DefaultListModel();
	static DefaultListModel listaModelNombresServicio = new DefaultListModel();
	static DefaultListModel listaModelTodosLosNombres = new DefaultListModel();
	static DefaultComboBoxModel listaModelServicios = new DefaultComboBoxModel();
	
	static TreeMap<String, String> titCons = new TreeMap<String, String>();
	static TreeMap<String, String> titHosp = new TreeMap<String, String>();
	static TreeMap<String, String> titCIA = new TreeMap<String, String>();
	static TreeMap<String, String> titQui = new TreeMap<String, String>();
	static TreeMap<String, String> titUrg = new TreeMap<String, String>();

	static ArrayList<ExcepcionesServicio> tablaExcepciones = new ArrayList<ExcepcionesServicio>();
	
	String[][] tablaDocumentos;
	String[] listaServicios;
	String[] listaNombresDocumentos;
	String[][] tablaHabituales;
	
	int coordenadasAsociar[][];
	
	Retardos retardo = new Retardos();

	public InicioIanus(CargaListaPdfs pdfs) {
		// TODO Auto-generated constructor stub

		setDefaultsModels();
		setConjuntosTitulos();
		setExcepciones();
		

		if(!Inicio.ventanasCargadas){

			GestionJacob.capturaWebs();
			
			Inicio.vNombres = new VentanaNombres(listaServicios,
					listaNombresDocumentos, tablaDocumentos, tablaHabituales);
			Inicio.vNombres.setBounds(Inicio.rVentanaNombres);
			Inicio.vNombres.setVisible(false);
			
			Inicio.vExplorador = new VentanaExplorador(pdfs);
			Inicio.vExplorador.setBounds(Inicio.rVentanaExploradorMax);
			Inicio.vExplorador.setVisible(true);
			Inicio.vExplorador.setPdfs(pdfs);
			

			Inicio.vControlIanus = new VentanaControlIanus();
			Rectangle rectangulo = Inicio.vControlIanus.getBounds();
			int ancho = rectangulo.width;
			int alto = rectangulo.height;
			
			Inicio.vControlIanus.setBounds(1024, 0, ancho, alto - 50);
			if(Inicio.numeroPantallas == 1){
				Inicio.vControlIanus.setBounds(1024, 0, 895, alto - 50);
			}
		}
		else{
			Inicio.vExplorador.setPdfs(pdfs);
		}

		Inicio.teclasHabilitadas = true;
	}

	
	private void setDefaultsModels() {

		listaNodos = Inicio.leerExcel.getNodos();
		coordenadasAsociar = Inicio.leerExcel.getCoordenadasAsociar();
		
		listaServicios = Inicio.leerExcel.getServicios();
		listaNombresDocumentos = Inicio.leerExcel.getNombres();
		tablaHabituales = Inicio.leerExcel.getHabituales();
		tablaDocumentos = Inicio.leerExcel.getTabla();

		listaModelTodosLosNombres.removeAllElements();
		listaModelNombresComunes.removeAllElements();
		listaModelHabituales1.removeAllElements();
		listaModelHabituales2.removeAllElements();
		// listaModelServicios.removeAllElements();
		
		// DefaultListModel de todos los nombres
		for (int i = 0; i < listaNombresDocumentos.length; i++) {
			listaModelTodosLosNombres.addElement(listaNombresDocumentos[i]);
			if (tablaHabituales[i][0].toLowerCase().equals("s")) {
				listaModelNombresComunes.addElement(listaNombresDocumentos[i]);
			} else if (tablaHabituales[i][1].toLowerCase().equals("s")) {
				listaModelHabituales1.addElement(listaNombresDocumentos[i]);
			} else if (tablaHabituales[i][2].toLowerCase().equals("s")) {
				listaModelHabituales2.addElement(listaNombresDocumentos[i]);
			}
		}

		for (int i = 0; i < listaServicios.length; i++) {
			listaModelServicios.addElement(listaServicios[i]);
		}

	}
	
	private void setExcepciones(){
		
		boolean servicioConExcepcion = false;
		ExcepcionesServicio excpServicio = new ExcepcionesServicio();
		
		for(int i=0;i<listaServicios.length;i++){
			servicioConExcepcion = false;
			for(int j=0;j<listaNombresDocumentos.length;j++){
				if(		tablaDocumentos[j][i].toLowerCase().equals("q") ||
						tablaDocumentos[j][i].toLowerCase().equals("f") ||
						tablaDocumentos[j][i].toLowerCase().equals("e") ||
						tablaDocumentos[j][i].toLowerCase().equals("i")     ){
					if(!servicioConExcepcion){
						servicioConExcepcion = true;
						excpServicio = new ExcepcionesServicio();
						excpServicio.servicio = listaServicios[i];
						excpServicio.excepciones = new ArrayList<Excepcion>();
					}
					Excepcion excepcion = new Excepcion(listaNombresDocumentos[j], tablaDocumentos[j][i]);
					excpServicio.excepciones.add(excepcion);
				}
			}
			if(servicioConExcepcion){
				tablaExcepciones.add(excpServicio);
			}
		}
		
		for(int i=0;i<tablaExcepciones.size();i++){
			
			System.out.println("Excepciones del servicio " + tablaExcepciones.get(i).servicio);
			for(int j=0;j<tablaExcepciones.get(i).excepciones.size();j++){
				System.out.println(tablaExcepciones.get(i).excepciones.get(j).nombreDocumento 
						+ ", " + tablaExcepciones.get(i).excepciones.get(j).tipoExcepcion	);
			}
			System.out.println();
		}
		
	}

	
	private void setConjuntosTitulos(){
		for(int i=0;i<listaNombresDocumentos.length;i++){
			if(Inicio.leerExcel.asociacionesDocumentos[i][1].toLowerCase().equals("s")){
				titCons.put(listaNombresDocumentos[i],Inicio.leerExcel.asociacionesDocumentos[i][0]);
			}
			if(Inicio.leerExcel.asociacionesDocumentos[i][2].toLowerCase().equals("s")){
				titHosp.put(listaNombresDocumentos[i],Inicio.leerExcel.asociacionesDocumentos[i][0]);
			}
			if(Inicio.leerExcel.asociacionesDocumentos[i][3].toLowerCase().equals("s")){
				titCIA.put(listaNombresDocumentos[i],Inicio.leerExcel.asociacionesDocumentos[i][0]);
			}
			if(Inicio.leerExcel.asociacionesDocumentos[i][4].toLowerCase().equals("s")){
				titQui.put(listaNombresDocumentos[i],Inicio.leerExcel.asociacionesDocumentos[i][0]);
			}
			if(Inicio.leerExcel.asociacionesDocumentos[i][5].toLowerCase().equals("s")){
				titUrg.put(listaNombresDocumentos[i],Inicio.leerExcel.asociacionesDocumentos[i][0]);
			}
		}
	}
	
	static public void main(String args[]) {
		new InicioIanus(null);
	}
}


