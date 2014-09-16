package es.mgamallo.prometeo;

/*
 * Christopher Deckers (chrriis@nextencia.net)
 * http://www.nextencia.net
 *
 * See the file "readme.txt" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

/**
 * @author Christopher Deckers
 */
public class NuevaEra {
	
	protected static final String LS = System.getProperty("line.separator");

	static int pasos = 1;
	
	static String sessionId = "";
	
  public static JComponent createContent() {
	  
	final JWebBrowser webBrowser = new JWebBrowser();  
	  
	final String codigoJavascript = new String(
			 "var elementos = document.getElementsByTagName('frameset');" + LS +
			 "alert(elementos.length);" + LS
			);
			// "aceptar();");
			
	final String codigoIntroducirPaciente = (
			
			
			"var elemento = window.frames;" + LS +
			 // "alert(elemento.length)" + LS + 
			
			// "alert(elemento[1].frames[0].location);" + LS +
			"var frameLogin = elemento[1].frames[0];" + LS +
			// "alert(frameLogin.document.getElementById('login').name);" + LS +
			
			"var login = frameLogin.document.getElementById('login');" + LS +
			 "var password = frameLogin.document.getElementById('password');" + LS +
			 "login.value = 'mgamgul1';" + LS +
			  "password.value = 'archivo0';" + LS +
			  "frameLogin.aceptar();"
			); 
	
	final String introducirUsuarioPulido = 
			"var framePrincipal = window.frames;" + LS +
			"var frameLogin = framePrincipal['principal'].frames['main'];" + LS +
			"var login = frameLogin.document.getElementById('login');" + LS +
			"var password = frameLogin.document.getElementById('password');" + LS +
			"login.value = 'mgamgul1';" + LS +
			"password.value = 'archivo0';" + LS +
			"frameLogin.aceptar();"
			;
	
	final String introducirNHCPulido = 
			"var framePrincipal = window.frames;" + LS +
			"var frameNHC = framePrincipal['principal'].frames['mainFrame'];" + LS +
			"var NHC = frameNHC.document.buscarPacienteForm.ID_NHC;" + LS +
	
			"NHC.value = '486245';" + LS +
			"frameNHC.buscar();"
			;
	
	final String seleccionarNodoConsulta = 
			"var framePrincipal = window.frames;" + LS +
			"var frameArbol = framePrincipal['principal'].frames['datos'].frames['arbol'].frames['despliegue'];" + LS +
			"alert(frameArbol.location);" + LS +
			//"var nodo = frameArbol.document.selectElementById('link10');" + LS + 
			
			"var nodo = frameArbol.document.anchors;" + LS +
			"var numeroAncla = 0;" + LS +
			"for(var i=0;i<nodo.length;i++){" + LS +
					//"var n = nodo[i].innerHTML.indexOf('REHABILITACION');" + LS +
					"if(nodo[i].innerHTML.indexOf('REHABILITACION') != -1){" + LS +
						"numeroAncla = i;" + LS +
					"}" + LS +	
					// "alert('N en ' + i + ' vale: ' + n); " + LS +
					// "alert(numeroAncla);" + LS +
			"}" + LS +
			
			// "alert(nodo[numeoAncla].innerHTML);" + LS +

			//"frameArbol.fichaServicioCEX('SvcCEX_360340_REHC')"
			
			"nodo[numeroAncla].click();"
			;
	
	final String salirPaciente = 
			"var framePrincipal = window.frames;" + LS +
			"var frameBotonera = framePrincipal['principal'].frames['botonera'];" + LS +

			"frameBotonera.inicio();"
			;
	
	final String formsArbol = 
			"var framePrincipal = window.frames;" + LS +
			"var frameArbol = framePrincipal['principal'].frames['datos'].frames['arbol'].frames['despliegue'];" + LS + ""
			;
			
	
	final String asociarDocumentoV = 
			"function asociarDocumentoV(){" + LS +
			"var especificaciones_ventana = 'dialogWidth:650px;dialogHeight:500px;status=yes;location=yes';" + LS +
			//"var especificaciones_ventana = 'width=650,height=500,toolbar=yes,location=yes';" + LS +
			"var tipoEpisodio='URG';" + LS +
			"var direccion = '/ianus_chp_pro/multimedia/NuevoDocumentoExterno.do;jsessionid=';" + LS +
			"direccion = direccion + idSesionExtraido;" + LS +
			"direccion = direccion + '?accion=A&tipoEpisodio='+ tipoEpisodio;" + LS +
			// "alert(direccion);" + LS +
			"var rtn = window.showModalDialog(direccion,top.principal.datos.arbol.despliegue,especificaciones_ventana);" + LS +
			
			/*
			"var rtn = window.open( direccion,'',especificaciones_ventana);" + LS +
			*/
			
			"}" + LS +
			
			
			
			"var framePrincipal = window.frames;" + LS +
			"var frameAsociar = framePrincipal['principal'].frames['datos'].frames['ficha'].frames['menu'];" + LS +
			// "alert(frameAsociar.location);" + LS +
			"var anchors = frameAsociar.document.anchors;" + LS +
			"for(var i=2;i<anchors.length;i++){" + LS +
				"alert(anchors[i].href)" + LS +
				"}" + LS +
				
			"alert(parent.parent.arbol.despliegue.document.length)" + LS +	
			//"var nodo = frameArbol.document.selectElementById('link10');" + LS + 

			/*
			"var anclas = frameAsociar.document.anchors;" + LS +
			"var numeroAncla = 0;" + LS +
			"for(var i=0;i<anclas.length;i++){" + LS +
					
					"alert('Ancla en ' + i + ' vale: ' + anclas[i].innerHTML); " + LS +
					// "alert(numeroAncla);" + LS +
			"}" + LS + ""
	
			*/
			
			"var accion = frameAsociar.document.getElementsByTagName('script')" + LS +
			// "alert(accion[0].src);" + LS +
			"var idSesion = accion[0].src;" + LS + 
			"var inicio = idSesion.lastIndexOf('=');" + LS +
			"var idSesionExtraido = idSesion.substring(inicio+1);" + LS +
			
			// "alert('El id es: ' + idSesionExtraido);"
			"asociarDocumentoV();"

			;
	
	final String getJsessionId = "" +
			"function getJSessionId(){" + LS +
				"var framePrincipal = window.frames;" + LS +
				"var frameAsociar = framePrincipal['principal'].frames['datos'].frames['ficha'].frames['menu'];" + LS +
				"var accion = frameAsociar.document.getElementsByTagName('script')" + LS +
				"var idSesion = accion[0].src;" + LS + 
				"var inicio = idSesion.lastIndexOf('=');" + LS +
				"sessionId = idSesion.substring(inicio+1);" + LS +
			"}" + LS +
			"var sessionId = 'hola';" + LS +
			"getJSessionId();" + LS +
			"return sessionId;" + LS +
			"";
	
	
	final String htmlNuevoDocumentoExterno1 = 
			"<FRAMESET id = multimedia frameSpacing=0 border=0 frameBroder=NO rows=101,*,*,30,50,0>" + LS +
				"<FRAME noResize border=0 src='http://ianuschop.sergas.local/ianus_chp_pro/jsps/estructura/multimedia/ficha_doExterno_head.jsp' name=datos scrolling=no>" + LS +
				"<FRAME noResize border=0 src='http://ianuschop.sergas.local/ianus_chp_pro/jsps/estructura/multimedia/ficha_doExterno_main1.jsp?FiltroTipoDocumento=true' name=main1 scrolling=no>" + LS +
				"<FRAME noResize border=0 src='http://ianuschop.sergas.local/ianus_chp_pro/jsps/comun/busqueda_cargando.jsp;jsessionid=";

	
	final String htmlNuevoDocumentoExterno2 = "" +
						" name=cargando scrolling=no>" + LS +
				"<FRAME noResize border=1 src='http://ianuschop.sergas.local/ianus_chp_pro/jsps/estructura/multimedia/docExterno_barraProgreso.jsp' name=progreso scrolling=no>" + LS +
				"<FRAME noResize border=0 src='http://ianuschop.sergas.local/ianus_chp_pro/jsps/estructura/multimedia/ficha_doExterno_menu.jsp' name=botonera scrolling=no>" + LS +
				"<FRAME noResize border=0 src='' name=hide scrolling=no>" + LS +
			"</FRAMESET>" + LS +
			"";
	
	final String asociarDocumentoHtmlIntermedio = 
			
			
			
			"function asociarDocumentoV(){" + LS +
			"var especificaciones_ventana = 'dialogWidth:650px;dialogHeight:500px;status=yes;location=yes';" + LS +
			//"var especificaciones_ventana = 'width=650,height=500,toolbar=yes,location=yes';" + LS +
			"var tipoEpisodio='URG';" + LS +
			"var direccion = '/ianus_chp_pro/multimedia/NuevoDocumentoExterno.do;jsessionid=';" + LS +
			"direccion = direccion + idSesionExtraido;" + LS +
			"direccion = direccion + '?accion=A&tipoEpisodio='+ tipoEpisodio;" + LS +
			// "alert(direccion);" + LS +
			"var rtn = window.showModalDialog(direccion,top.principal.datos.arbol.despliegue,especificaciones_ventana);" + LS +
			
			/*
			"var rtn = window.open( direccion,'',especificaciones_ventana);" + LS +
			*/
			
			"}" + LS +
			
			
			
			"var framePrincipal = window.frames;" + LS +
			"var frameAsociar = framePrincipal['principal'].frames['datos'].frames['ficha'].frames['menu'];" + LS +

			// "alert(frameArbol.location);" + LS +
			//"var nodo = frameArbol.document.selectElementById('link10');" + LS + 

			/*
			"var anclas = frameAsociar.document.anchors;" + LS +
			"var numeroAncla = 0;" + LS +
			"for(var i=0;i<anclas.length;i++){" + LS +
					
					"alert('Ancla en ' + i + ' vale: ' + anclas[i].innerHTML); " + LS +
					// "alert(numeroAncla);" + LS +
			"}" + LS + ""
	
			*/
			
			"var accion = frameAsociar.document.getElementsByTagName('script')" + LS +
			// "alert(accion[0].src);" + LS +
			"var idSesion = accion[0].src;" + LS + 
			"var inicio = idSesion.lastIndexOf('=');" + LS +
			"var idSesionExtraido = idSesion.substring(inicio+1);" + LS +
			
			// "alert('El id es: ' + idSesionExtraido);"
			"asociarDocumentoV();"

			;
	
	final String codigoDentroVentanaModal = 
			"javascript:window.frames['main1'].document.estructuraMultimedia.cdu.value='29';" + // consentimiento inf.
					   "window.frames['main1'].document.estructuraMultimedia.titulo.value='Hola consentimiento';" +
					   "window.frames['main1'].document.estructuraMultimedia.fichero.value='D:/00 DOCUMENTACION/02 Revisado/Prueba Ocr Man/0001_OCR_20130820_112731 @Separador @DIG @X r.pdf';" +
			"alert('holaaa');"; 
	
	
    JPanel contentPane = new JPanel(new BorderLayout());
    JPanel webBrowserPanel = new JPanel(new BorderLayout());
    webBrowserPanel.setBorder(BorderFactory.createTitledBorder("Native Web Browser component"));

    webBrowser.navigate("http://ianuschop.sergas.local/ianus_chp_pro/inicio.jsp");
    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
    contentPane.add(webBrowserPanel, BorderLayout.CENTER);
    // Create an additional bar allowing to show/hide the menu bar of the web browser.
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
    JCheckBox menuBarCheckBox = new JCheckBox("Menu Bar", webBrowser.isMenuBarVisible());
    
    menuBarCheckBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
    	  
    	  // System.out.println(webBrowser.getHTMLContent());
    	  // webBrowser.executeJavascript(codigoIntroducirPaciente);
    	  
    	  if(pasos == 1){
    		  webBrowser.executeJavascript(introducirUsuarioPulido);
    	  }
    	  if(pasos == 2){
        	  webBrowser.executeJavascript(introducirNHCPulido);
    	  }
    	  if(pasos == 3){
    	      webBrowser.executeJavascript(seleccionarNodoConsulta);
    	  }
    	  if(pasos == 4){
    		 webBrowser.executeJavascript(asociarDocumentoV);
    		  
    		  /*
    		  Object resultado = webBrowser.executeJavascriptWithResult(getJsessionId);
    		  System.out.println(resultado.toString());
    		  sessionId = resultado.toString();
    		  String html = htmlNuevoDocumentoExterno1 + sessionId + htmlNuevoDocumentoExterno2;
    		  
    		  FileWriter fichero = null;
    		  PrintWriter pw = null;
    		  
    		  try {
				fichero = new FileWriter("d:/prueba.html");
				pw = new PrintWriter(fichero);
				pw.print(html);
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					try{
						if(null!=fichero)
							fichero.close();
					}catch(Exception e2){
						e2.printStackTrace();
					}
				}
    		  */
    	  }
    	  if(pasos == 5){
    		 // webBrowser.navigate("javascript:alert('hola');");
    		  webBrowser.executeJavascript(salirPaciente);
    		  pasos = 1;
    	  }
    	  pasos++;

    	  
    	  // webBrowser.navigate("http://www.google.es");
    	 // System.out.println(webBrowser.executeJavascriptWithResult(codigoIntroducirPaciente));
      }
    });
    
    
    
    buttonPanel.add(menuBarCheckBox);
    contentPane.add(buttonPanel, BorderLayout.SOUTH);
    return contentPane;
  }

  /* Standard main method to try that test as a standalone application. */
  public static void main(String[] args) {
    NativeInterface.open();
    UIUtils.setPreferredLookAndFeel();
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame("DJ Native Swing Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(createContent(), BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
      }
    });
    NativeInterface.runEventPump();
  }
}
