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

	static final String RUTA = "j:/digitalización/00 documentacion/03 Firmado";
	static final String RUTAB = "H:/DIGITALIZACIÓN/00 DOCUMENTACION/03 Firmado";
	static final String RUTAURG = "j:/DIGITALIZACIÓN/01 INFORMES URG (Colectiva)";
	static final String RUTAURGB = "H:/DIGITALIZACIÓN/01 INFORMES URG (Colectiva)";
static final String RUTAPC = "c:/ianus/ianus.txt";

	static int documentacion = 0; // 0 Urgencias
									// 1 Documentacion

	
	static String[] rutaCompletaPdfs;
	static File[] tandaDePdfs;
	


	LeerExcel leerExcel;

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
			
			ComThread.InitSTA();
			
	        ActiveXComponent oShell = new ActiveXComponent("Shell.Application"); 
	        ActiveXComponent oWindows = oShell.invokeGetComponent("Windows");

	        
	        try {
				Runtime.getRuntime().exec("C:/Archivos de programa/Internet Explorer/iexplore.exe");
				Runtime.getRuntime().exec("C:/Archivos de programa/Internet Explorer/iexplore.exe");
				Thread.sleep(2000);
	        } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        int iCount = oWindows.getProperty("Count").getInt();
	        System.out.println("iCount: " + iCount);        
			
	        for (int i=iCount-1,j= 1; i >iCount-3 ; i--,j++) {
	            ActiveXComponent oWindow = oWindows.invokeGetComponent("Item", new Variant(i));     
	            String sLocName = oWindow.getProperty("LocationName").getString();
	            String sFullName = oWindow.getProperty("FullName").getString();
	            boolean isIE = sFullName.toLowerCase().endsWith("iexplore.exe");
	            boolean bVisible = oWindow.getProperty("Visible").getBoolean();
	            System.out.println("i: " + i + ", loc: " + sLocName + ", name: " + sFullName + ", isIE: " + isIE + ", vis: " + bVisible);
	            /*
	            if ((isIE)&&(sLocName.startsWith("about:blank"))) {
	                oIE = oWindow;
	            }
	            */
	            if(j==1){
	            	Inicio.ianus1 = oWindow;
	            }
	            if(j==2){
	            	Inicio.ianus2 = oWindow;
	            }
	        }
			
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
			
			
			Dispatch.call(Inicio.ianus1, "Navigate","http://ianuschop.sergas.local/ianus_chp_pro/inicio.jsp");

			
			try {
			
			Thread.sleep(2000);	
				
			Dispatch.call(Inicio.ianus2, "Navigate","http://ianuschop.sergas.local/ianus_chp_pro/inicio.jsp");

			
			if(Inicio.contraseña){
				Dispatch.call(Inicio.ianus1, "Navigate","javascript:" + CadenasJavascript.introducirUsuario(Inicio.usuario));
				Thread.sleep(4000);
				
				Dispatch.put(Inicio.ianus1,"Visible",true);
				Dispatch.put(Inicio.ianus1,"menubar",false);
				Dispatch.put(Inicio.ianus1,"toolbar",false);
				
			    Dispatch.put(Inicio.ianus1,"height",1079);
			    Dispatch.put(Inicio.ianus1,"top",200);
			    Dispatch.put(Inicio.ianus1,"left",1024);
				
				Dispatch.call(Inicio.ianus2, "Navigate","javascript:" + CadenasJavascript.introducirUsuario(Inicio.usuario));
			
				Thread.sleep(4000);
				
				Dispatch.put(Inicio.ianus2,"Visible",true);	
				Dispatch.put(Inicio.ianus2,"menubar",false);
				Dispatch.put(Inicio.ianus2,"toolbar",false);
				
			    Dispatch.put(Inicio.ianus2,"height",1079);
			    Dispatch.put(Inicio.ianus2,"top",200);
			    Dispatch.put(Inicio.ianus2,"left",1024);
			}
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			Inicio.vExplorador.setPdfs(pdfs);
		}

	}

	private void setDefaultsModels() {

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
