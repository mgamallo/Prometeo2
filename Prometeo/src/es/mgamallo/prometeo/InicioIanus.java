package es.mgamallo.prometeo;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;

import chrriis.dj.nativeswing.swtimpl.demo.examples.webbrowser.JavascriptExecution;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


public class InicioIanus {

	static final String RUTA = "j:/digitalizaci�n/00 documentacion/03 Firmado";
	static final String RUTAB = "H:/DIGITALIZACI�N/00 DOCUMENTACION/03 Firmado";
	static final String RUTAURG = "j:/DIGITALIZACI�N/01 INFORMES URG (Colectiva)";
	static final String RUTAURGB = "H:/DIGITALIZACI�N/01 INFORMES URG (Colectiva)";
static final String RUTAPC = "c:/ianus/ianus.txt";

	static final String CONSENTIMIENTO = "Consentimento informado";
	static final String EKG = "ECG";
	static final String ECO = "Ecograf�a";
	static final String LISTAESPERA = "Folla inclusi�n LE";
	static final String MANOMETRIA = "Manometr�a";
	static final String PHMETRIA = "Phmetr�a";
	static final String ENFERMERIA_ENDOSCOPIAS = "Folla enfermar�a endoscopias";
	static final String ENDOSCOPIA_DIGESTIVA = "Endoscopia Digestiva";
	static final String INTERCONSULTA = "Interconsulta";
	
	static final String PRUEBAS_DIAGNOSTICAS = "Proba diagn�stica";
	static final String PRICK = "Prick test";
	static final String ESPIROMETRIA = "Espirometr�a";
	static final String PRUEBAS_EPICUTANEAS = "Probas epicut�neas";
	static final String PRUEBAS_PROVOCACION = "Probas provocaci�n";
	
	static final String INFORMEURG = "Informe alta";
	static final String ENFERMERIAURG = "Folla enfermar�a urxencias";
	
	static final String DOC_ANULADO = "Documento anulado";
	static final String DESCONOCIDO = "Des";
	static final String HOSP = "HOSP";
	static final String HOSP_JACOB = "HOS:";



	static int documentacion = 0; // 0 Urgencias
									// 1 Documentacion

	
	static String[] rutaCompletaPdfs;
	static File[] tandaDePdfs;
	
	

	LeerExcel leerExcel;

	static Nodo listaNodos[];
	
	static DefaultListModel listaModelNombresComunes = new DefaultListModel();
	static DefaultListModel listaModelHabituales1 = new DefaultListModel();
	static DefaultListModel listaModelHabituales2 = new DefaultListModel();
	static DefaultListModel listaModelNombresServicio = new DefaultListModel();
	static DefaultListModel listaModelTodosLosNombres = new DefaultListModel();
	static DefaultComboBoxModel listaModelServicios = new DefaultComboBoxModel();

	String[][] tablaDocumentos;
	String[] listaServicios;
	String[] listaNombresDocumentos;
	String[][] tablaHabituales;

	public InicioIanus(CargaListaPdfs pdfs) {
		// TODO Auto-generated constructor stub

		leerExcel = new LeerExcel();
		leerExcel.leer("Documentos.xls");
		setDefaultsModels();

		if(!Inicio.ventanasCargadas){

			GestionJacob.capturaWebs();
			
			Inicio.vNombres = new VentanaNombres(listaServicios,
					listaNombresDocumentos, tablaDocumentos, tablaHabituales);
			Inicio.vNombres.setBounds(Inicio.rVentanaNombres);
			Inicio.vNombres.setVisible(true);
			
			Inicio.vExplorador = new VentanaExplorador(pdfs);
			Inicio.vExplorador.setBounds(Inicio.rVentanaExplorador);
			Inicio.vExplorador.setVisible(true);
			Inicio.vExplorador.setPdfs(pdfs);
			

			Inicio.vControlIanus = new VentanaControlIanus();
			Rectangle rectangulo = Inicio.vControlIanus.getBounds();
			int ancho = rectangulo.width;
			int alto = rectangulo.height;
			
			Inicio.vControlIanus.setBounds(1024, 0, ancho, alto);
		}
		else{
			Inicio.vExplorador.setPdfs(pdfs);
		}

	}

	private void setDefaultsModels() {

		listaNodos = leerExcel.getNodos();
		
		listaServicios = leerExcel.getServicios();
		listaNombresDocumentos = leerExcel.getNombres();
		tablaHabituales = leerExcel.getHabituales();
		tablaDocumentos = leerExcel.getTabla();

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

	
	static public void main(String args[]) {
		new InicioIanus(null);
	}
}


