package es.mgamallo.prometeo;


import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



//import org.eclipse.swt.widgets.Slider;

















import chrriis.common.UIUtils;
import chrriis.common.WebServer;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;
import chrriis.dj.nativeswing.swtimpl.demo.examples.webbrowser.ClasspathPages;

/**
 * @author Christopher Deckers
 */

public class InterfazPrincipal implements MouseListener{

	public static String fechaHoy = "";
	
	protected static final String LS = System.getProperty("line.separator");
	
	private boolean inicioPrograma = true;
	
	public int numeroDudasContestadas = 0; 
	public int numeroApartadoFirmados = 0; 
	public int numeroApartadoRevisados = 0; 
	public int numeroNormasNuevas = 0;
	

	private Point coordenadasRaton = new Point();

	private final String ABRIR = "Abrir";
	private final String AYUDA = "Ayuda";
	private final String NORMAS = "Normas";
	private final String AVISOS = "Avisos";
	private final String USUARIO = "Usuario";
	private final String ESTADISTICAS = "Estadisticas";
	public static final String SALIR = "Salir";

	public String panelActivo = USUARIO;
	public boolean abracadabra = false;
	
	final String CUADRANTE_ARCHIVO = "http://cuadrantearchivo.appspot.com/";
	final String ERROR_CUADRANTE = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/errorXedoc.html";
	
	final String DIR_CONTROL = Inicio.unidadHDDejecutable + ":/Desarrollo/git/Prometeo/Prometeo/Prometeo/prometeo/Htmls/control/control.html";
	final String DIR_OPERACIONES = Inicio.unidadHDDejecutable + ":/Desarrollo/git/Prometeo/Prometeo/Prometeo/prometeo/Htmls/usuarios/Digitalizacion/usuariosSesion.html";

//	final String DIR_ABRIR = Inicio.unidadHDD +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/abrir/abrir.html";
	final String DIR_ABRIR = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/abrir.html";
	final String DIR_ABRIR_X = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/abrirXedoc.html";
	
	final String DIR_ABRIR_X1 = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/xedoc/abrirXedoc1Inicio.html";
	final String DIR_ABRIR_X2 = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/xedoc/abrirXedoc2Final.html";
	final String DIR_ABRIR_XFINAL = ":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/abrirXedocFinal.html";

	public static final String DIR_AYUDA_X1 = Inicio.unidadHDDejecutable +":/Desarrollo/git/prometeo/Prometeo/Prometeo/Prometeo/Htmls/ayuda/ayuda_1.html";
	public static final String DIR_AYUDA_X2 = Inicio.unidadHDDejecutable +":/Desarrollo/git/prometeo/Prometeo/Prometeo/Prometeo/Htmls/ayuda/ayuda_2.html";
	public static final String DIR_AYUDA_F = Inicio.unidadHDDejecutable +":/Desarrollo/git/prometeo/Prometeo/Prometeo/Prometeo/Htmls/ayuda_f.html";

	
	public static final String DIR_AYUDA = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/ayuda4.html";
	
	final String DIR_NORMAS = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/normas.html";
	final String DIR_AVISOS = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/avisos/avisos.html";
	// final String DIR_USUARIO = Inicio.unidadHDD +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/prometeo/Htmls/Usuario/usuario.html";
	final String DIR_USUARIO = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/prometeo/Htmls/usuario.html";
	static final String DIR_SALIR = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/prometeo/Htmls/salir.html";
//	final String DIR_ESTADISTICAS = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/estadistica.html";

	final String DIR_ESTADISTICAS = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/EstadisticaCasi.html";
	final String CARGANDO_ESTADISTICAS = Inicio.unidadHDDejecutable + ":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/Cargando estadisticas.html";
//	final String DIR_ESTADISTICAS = "C:/Users/Manuel/git/Prometeo2/Prometeo/Prometeo/Htmls/EstadisticaCasi.html";
	
	/*
	final String DIR_CONTROL = Inicio.unidadHDD + ":/DIGITALIZACIÓN/00 DOCUMENTACION/99 Nombres Normalizados/Prometeo/prometeo/Htmls/control/control.html";
	final String DIR_OPERACIONES = Inicio.unidadHDD + ":/DIGITALIZACIÓN/00 DOCUMENTACION/99 Nombres Normalizados/Prometeo/prometeo/Htmls/usuarios/Digitalizacion/usuariosSesion.html";

//	final String DIR_ABRIR = Inicio.unidadHDD +":/DIGITALIZACIÓN/00 DOCUMENTACION/99 Nombres Normalizados/Prometeo/Prometeo/Htmls/abrir/abrir.html";
	final String DIR_ABRIR = Inicio.unidadHDD +":/DIGITALIZACIÓN/00 DOCUMENTACION/99 Nombres Normalizados/Prometeo/Prometeo/Htmls/abrir.html";
	final String DIR_AYUDA = Inicio.unidadHDD +":/DIGITALIZACIÓN/00 DOCUMENTACION/99 Nombres Normalizados/Prometeo/Prometeo/Htmls/ayuda/ayuda.html";
	final String DIR_NORMAS = Inicio.unidadHDD +":/DIGITALIZACIÓN/00 DOCUMENTACION/99 Nombres Normalizados/Prometeo/Prometeo/Htmls/normas/normas.html";
	final String DIR_AVISOS = Inicio.unidadHDD +":/DIGITALIZACIÓN/00 DOCUMENTACION/99 Nombres Normalizados/Prometeo/Prometeo/Htmls/avisos/avisos.html";
	// final String DIR_USUARIO = Inicio.unidadHDD +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/prometeo/Htmls/Usuario/usuario.html";
	final String DIR_USUARIO = Inicio.unidadHDD +":/DIGITALIZACIÓN/00 DOCUMENTACION/99 Nombres Normalizados/Prometeo/prometeo/Htmls/usuario.html";
	final String DIR_SALIR = Inicio.unidadHDD +":/DIGITALIZACIÓN/00 DOCUMENTACION/99 Nombres Normalizados/Prometeo/prometeo/Htmls/salir.html";

	*/
	
	public JWebBrowser webBrowserControl;
	public JWebBrowser webBrowserOperaciones;

	public JPanel panelPrincipal = new JPanel(new BorderLayout());
	public JPanel panelTitulo = new JPanel(new BorderLayout());
	public JPanel panelControl = new JPanel(new BorderLayout());
	public JPanel panelOperaciones = new JPanel(new BorderLayout());

	public JLabel labelMinimizar;
	public JLabel labelMaximizar;
	private boolean maximizada = false;
	public JLabel labelFecha;

	public boolean barraPanelControlVisible;

	public JFrame frame;
	
	public Carpetas carpeta;
	
	public boolean inicioCarpetasSubidas = true;
	private ArrayList<Directorio> carpetasSubidas = new ArrayList<Directorio>();

	public JComponent createContent() {

		webBrowserControl = new JWebBrowser();
		webBrowserControl.navigate(DIR_CONTROL);
		webBrowserControl.setBarsVisible(false);
		webBrowserControl.setMenuBarVisible(false);
		webBrowserControl.setJavascriptEnabled(true);
		// webBrowser.navigate("http://ianuschop");
		// webBrowser.navigate("http://intranetchopo.sergas.local/");

		webBrowserControl.addWebBrowserListener(new WebBrowserAdapter() {
			@Override
			public void commandReceived(WebBrowserCommandEvent e) {
				String command = e.getCommand();
				Object[] parameters = e.getParameters();
				System.out.println(command
						+ (parameters.length > 0 ? " "
								+ Arrays.toString(parameters) : ""));
				if ("abrir".equals(command)) {
					webBrowserOperaciones.setVisible(true);
					webBrowserOperaciones.navigate(DIR_ABRIR);
					
					Inicio.carpetaDudas = false;
					Inicio.carpetaXedocFirmado = false;
					
					panelActivo = ABRIR;
					
					if(Inicio.esWin64){
						frame.setBounds(Inicio.rVentanaInterfazPrincipalMax);
					}
					
				} else if ("ayuda".equals(command)) {
					webBrowserOperaciones.navigate(DIR_AYUDA);
					webBrowserOperaciones.setVisible(true);
					panelActivo = AYUDA;
					
					maximizada = Pantalla.maximizar(frame);
					
					if(Inicio.esWin64){
						frame.setBounds(Inicio.rVentanaInterfazPrincipalMax);
					}
					
				} else if ("normas".equals(command)) {
					webBrowserOperaciones.navigate(DIR_NORMAS);
					webBrowserOperaciones.setVisible(true);
					panelActivo = NORMAS;
					
					if(Inicio.esWin64){
						frame.setBounds(Inicio.rVentanaInterfazPrincipalMax);
					}
					
					Inicio.listaNormasIanus = Txt.leerNormasTxt(Inicio.rutaNormas);
				} else if ("avisos".equals(command)) {
					webBrowserOperaciones.navigate(DIR_AVISOS);
					webBrowserOperaciones.setVisible(true);
					panelActivo = AVISOS;
					
					if(Inicio.esWin64){
						frame.setBounds(Inicio.rVentanaInterfazPrincipalMax);
					}
				}
				if ("usuario".equals(command)) {
					maximizada = Pantalla.restaurar(frame);
					webBrowserOperaciones.navigate(DIR_USUARIO);
					webBrowserOperaciones.setVisible(true);
					Inicio.carpetaDudas = false;
					Inicio.carpetaXedocFirmado = false;
					MiHilo miHilo = new MiHilo(Inicio.usuario,500);
					miHilo.start();
					panelActivo = USUARIO;
					
					if(Inicio.esWin64){
						frame.setBounds(Inicio.rVentanaInterfazPrincipalMax);
					}
					
				} else if ("salir".equals(command)) {
					webBrowserOperaciones.navigate(DIR_SALIR);
					webBrowserOperaciones.setVisible(true);
					panelActivo = SALIR;
					inicioCarpetasSubidas = true;

					if(Inicio.esWin64){
						frame.setBounds(Inicio.rVentanaInterfazPrincipalMax);
					}
					// frame.dispose();
				} else if ("minimizar".equals(command)) {
					frame.setState(frame.ICONIFIED);
				}
				
			}
		});

		// Navegador de Operaciones Iniciales

		webBrowserOperaciones = new JWebBrowser();
		// webBrowserOperaciones.navigate(WebServer.getDefaultWebServer().getResourcePathURL("Htmls/usuarios/Digitalizacion",
		// "Index0.html"));
		webBrowserOperaciones.navigate(DIR_OPERACIONES);
		// webBrowserOperaciones.navigate("H:/DIGITALIZACIÓN/00 DOCUMENTACION/99 Nombres Normalizados/Prometeo/Htmls/usuarios/Digitalizacion/Index3.html");

		webBrowserOperaciones.setBarsVisible(false);
		webBrowserOperaciones.setMenuBarVisible(false);
		webBrowserOperaciones.setJavascriptEnabled(true);

		webBrowserOperaciones.addWebBrowserListener(new WebBrowserAdapter() {
			@Override
			public void commandReceived(WebBrowserCommandEvent e) {
				String command = e.getCommand();
				Object[] parameters = e.getParameters();

				if (command.contains("user_")) {
					int numUsuario = Integer.parseInt(command.substring(5, 7));

					System.out.println("Número de usuario " + numUsuario);
					
					Inicio.usuario = Inicio.usuarios[numUsuario];
					Inicio.usuario.usuario = parameters[0].toString();
					Inicio.usuario.password = parameters[1].toString();
					
			//		new VentanaPassword(Inicio.usuario.alias, Inicio.usuario.usuario).setVisible(true);;
					
					abracadabra = true;
					if(abracadabra){
						barraPanelControlVisible = true;

						webBrowserOperaciones.navigate(DIR_USUARIO);
						
						numeroDudasContestadas = Detecta.dudasResueltas();
						System.out.println("Numero de dudas resueltas " + numeroDudasContestadas);
						numeroApartadoFirmados = Detecta.apartadoPendiente(true);
						numeroApartadoRevisados = Detecta.apartadoPendiente(false);
						
						
						MiHilo miHilo = new MiHilo(Inicio.usuario,500);
						miHilo.start();

						frame.setBounds(Inicio.rVentanaInterfazPrincipalMax);
						Inicio.panelPrincipal.panelControl.setVisible(true);
					}

		
					
	// panelActivo = "Usuario" *********************************************************;
				
				} else if(panelActivo.equals(USUARIO)){
					System.out.println("Panel usuario");
										
					Inicio.carpetaDudas = false;
					Inicio.carpetaXedocFirmado = false;
					
					String cadena = "";
					
					if (command.equals("urg")) {
						String claseOn = "tile bg-cobalt bg-hover-lightGreen bd-yellow selected";
						String claseOff = "tile bg-green bg-hover-lightGreen bd-yellow";
						cadena = ""
								+ "document.getElementById('urg').className='" + claseOn + "';" + LS
								+ "document.getElementById('doc').className='" + claseOff + "';" + LS
								+ "document.getElementById('tipoDocumentacion').innerHTML = 'URGENCIAS';";
						
						Inicio.usuario.urgencias = true;
						Inicio.xedoc = false;
						
						webBrowserOperaciones.executeJavascript(cadena);
					}
					else if(command.equals("doc")){
						String claseOff = "tile bg-cobalt bg-hover-lightGreen bd-yellow";
						String claseOn = "tile bg-green bg-hover-lightGreen bd-yellow selected";
						cadena = ""
								+ "document.getElementById('doc').className='" + claseOn + "';" + LS
								+ "document.getElementById('urg').className='" + claseOff + "';" + LS
								+ "document.getElementById('tipoDocumentacion').innerHTML = 'DOCUMENTACIÓN';";
						
						Inicio.usuario.urgencias = false;
						Inicio.xedoc = false;
						
						webBrowserOperaciones.executeJavascript(cadena);
					}
					else if(command.equals("xedoc")){
						Inicio.xedoc = true;
						Inicio.carpetaXedocFirmado = true;
						
						// getHTMLXedoc();
						
						webBrowserOperaciones.setVisible(true);
						// webBrowserOperaciones.navigate(Inicio.unidadHDDejecutable + DIR_ABRIR_XFINAL);
						webBrowserOperaciones.navigate(DIR_ABRIR_X);
						panelActivo = ABRIR;
						
				//		InicioXedoc xedoc = new InicioXedoc();
						
					//	webBrowserOperaciones.navigate("http://xedocidx.sergas.local/xedoc_idx/login");
						
					}
					else if(command.equals("aptdoRevis")){
						Dudas.abrirCarpetaApartadoRevisado();
					}
					else if(command.equals("aptdoFirm")){
						Dudas.abrirCarpetaApartadoFirmado();
					}
					else if(command.equals("dudas")){
						
						Inicio.carpetaDudas = true;
						webBrowserOperaciones.setVisible(true);
						webBrowserOperaciones.navigate(DIR_ABRIR);
						panelActivo = ABRIR;
					}
					else if(command.equals("tiempo")){
						maximizada = Pantalla.maximizar(frame);
						webBrowserOperaciones.navigate("http://www.meteogalicia.es/web/index.action");
					}
					else if(command.equals("correo")){
						maximizada = Pantalla.maximizar(frame);
						webBrowserOperaciones.navigate("https://correo.sergas.local/");
					}
					else if(command.equals("sughus")){
						maximizada = Pantalla.maximizar(frame);
						webBrowserOperaciones.navigate("http://sughusponpro.sergas.local:8080/sughus");
					}
					else if(command.equals("fides")){
						maximizada = Pantalla.maximizar(frame);
						webBrowserOperaciones.navigate("http://fides.sergas.local/");
					}
					else if(command.equals("salir")){
						frame.dispose();
						Cerrar.cerrarTodo();
						System.exit(0);
					}
					else if(command.equals("normasNuevas") || command.equals("normas")){
						webBrowserOperaciones.navigate(DIR_NORMAS);
						panelActivo = NORMAS;
						Inicio.listaNormasIanus = Txt.leerNormasTxt(Inicio.rutaNormas);
					}
					else if(command.equals("docSubidos")){
						
						webBrowserOperaciones.navigate(DIR_ESTADISTICAS);
						// webBrowserOperaciones.navigate(CARGANDO_ESTADISTICAS);
						panelActivo = ESTADISTICAS;

/*
						int docIanus = estadistica.estadisticaDiaria.numeroArchivosSubidosIanus;
						int docIanusUrg = estadistica.estadisticaDiaria.numeroArchivosSubidosIanusUrg;
						int docXedoc = estadistica.estadisticaDiaria.numeroArchivosSubidosXedoc;
*/						
					}
					else if(command.equals("cuadrantearchivo")){
						if(!Inicio.usuarioLogeadoWindows.equals("cahcpon")){
							webBrowserOperaciones.navigate(CUADRANTE_ARCHIVO);
							maximizada = Pantalla.maximizar(frame);
						}
						else{
							
						}
						
					}
			/*		else if(command.equals("cargarEstadisticas")){
				*/		
												
						
						/*
						
						int total = Inicio.estadistica.estadisticaDiaria.numeroArchivosSubidosIanus 
									+ Inicio.estadistica.estadisticaDiaria.numeroArchivosSubidosIanusUrg
									+ Inicio.estadistica.estadisticaDiaria.numeroArchivosSubidosXedoc;
						
										
						String cadenaJavascript = 
								"var dia = document.getElementById('dia');" + LS +
								"var ianusDoc = document.getElementById('ianusDoc');" + LS +
								"var ianusUrg = document.getElementById('ianusUrg');" + LS +
								"var xedoc = document.getElementById('xedoc');" + LS +
								"var total = document.getElementById('total');" + LS +
								"dia.innerHTML = '" + Inicio.estadistica.calendario.nombreDiaHabil + " " + Inicio.estadistica.calendario.fechaHabil + "';"
										+ "ianusDoc.innerHTML = '" + Inicio.estadistica.estadisticaDiaria.numeroArchivosSubidosIanus +"';" + LS
										+ "ianusUrg.innerHTML = '" + Inicio.estadistica.estadisticaDiaria.numeroArchivosSubidosIanusUrg +"';" + LS
										+ "xedoc.innerHTML = '" + Inicio.estadistica.estadisticaDiaria.numeroArchivosSubidosXedoc +"';" + LS
										+ "total.innerHTML = '" + total +"';" + LS
										+ "";
						
						webBrowserOperaciones.executeJavascript(cadenaJavascript);
						
						*/
						
						/*
						cadenaJavascript = ""
								+ ""
								+ "FusionCharts.ready(function() {" + LS +
									"var revenueChart1 = new FusionCharts({" + LS +
										"type : 'pie3d', renderAt : 'chartContainer1'," + LS +
										"width : '620', height : '310', dataFormat : 'json'," + LS +
										"dataSource :  {" + LS +
										    "'chart': { " + LS +
										        "'caption': 'Distribución diaria','animation': '0','formatnumberscale': '1'," + LS +
										        "'decimals': '0', 'showLegend': '1', 'legendBorderAlpha': '0'," + LS +
										        "'pieslicedepth': '10', 'startingangle': '200', 'showBorder': '0'," + LS +
										        "'showHoverEffect': '1', 'toolTipColor': '#ffffff', 'toolTipBgColor': '#000000'," + LS +
										        "'toolTipBgAlpha': '80', 'toolTipBorderRadius': '2', 'toolTipPadding': '5'," + LS +
										        "'use3DLighting': '1', 'radius3D': '40'}," + LS +
										    "'data': [" + LS +
										        "{ 'label': 'Xedoc', 'value': '" + Inicio.estadistica.estadisticaDiaria.numeroArchivosSubidosXedoc + "', " + LS +
										            "'issliced': '1', 'color': '#FA9000' }," + LS +
										        "{ 'label': 'Ianus Doc.', 'value': '" + Inicio.estadistica.estadisticaDiaria.numeroArchivosSubidosIanus + "', " + LS +
										            "'issliced': '1', 'color': '#C40000' }," + LS +
										        "{ 'label': 'Ianus Urg.', 'value': '" + Inicio.estadistica.estadisticaDiaria.numeroArchivosSubidosIanusUrg + "', " + LS +
										            "'issliced': '0', 'color': '#750303' }" + LS +
										            "]" + LS +
										  "}" + LS +
									"});" + LS +	
									"revenueChart1.render('chartContainer1');" + LS +
								"});'" + LS 
								+ "";
						 
						
						 webBrowserOperaciones.executeJavascript(cadenaJavascript);
						 */
			/*		}
			 
			 */
					else if(command.equals("carrusel")){
						if(inicioPrograma){
							String codigo = "";
							boolean nada = true;
							
							if(numeroDudasContestadas>0){
								codigo += "document.getElementById('numDudasContestadas').innerHTML='Tienes " + numeroDudasContestadas + " dudas contestadas.';" + LS;
								nada = false;
							}
							else{
								codigo += "var nodillo = document.getElementById('pantallaDudas');" + LS;
								codigo += "nodillo.parentNode.removeChild(nodillo);" + LS;
							}
								
							if(numeroApartadoFirmados >0){
								nada = false;
								codigo += "document.getElementById('numApartadosFirmados').innerHTML ='Tienes " + numeroApartadoFirmados + " documentos o carpetas apartadas en firmados.';" + LS;
							}
							else{
								codigo += "var nodillo = document.getElementById('pantallaApartadosFirmados');" + LS;
								codigo += "nodillo.parentNode.removeChild(nodillo);" + LS;
							}
							
							if(numeroApartadoRevisados >0){
								nada = false;
								codigo += "document.getElementById('numApartadosRevisados').innerHTML ='Tienes " + numeroApartadoRevisados + " documentos o carpetas apartadas en revisados.';" + LS;
							}
							else{
								codigo += "var nodillo = document.getElementById('pantallaApartadosRevisados');" + LS;
								codigo += "nodillo.parentNode.removeChild(nodillo);" + LS;
							}
							
							if(numeroNormasNuevas > 0){
								nada=false;
								codigo += "document.getElementById('numNormasNuevas').innerHTML ='Hay " + numeroNormasNuevas + " normas nuevas.';" + LS;
							}
							else{
								codigo += "var nodillo = document.getElementById('normasNuevas');" + LS;
								codigo += "nodillo.parentNode.removeChild(nodillo);" + LS;
							}
							
							if(!nada){
								codigo += "modalOn();";
							}

							
							webBrowserOperaciones.executeJavascript(codigo);
						}
						inicioPrograma = false;
					}
					
					
				}
				
				
	//   Estadisticas *****************************************************************
				else if (panelActivo.equals(ESTADISTICAS)) {
					
					maximizada = Pantalla.maximizar(frame);
					
					// 0 Todos
					// 1 Ianus
					// 2 Urg
					// 3 Xedoc
					
					GestionEstadistica ges = new GestionEstadistica();
					
					
					
					System.out.println("El comando es.... " + command.toString());
					
					if(command.equals("cargarEstadisticas")){
						
						System.out.println("El comando es Inicializando ... " + command.toString());
						
						String cod = ""
								+ "var f = document.getElementById('fecha');" + LS
								+ "f.innerHTML = '" + fechaHoy + "';" + LS;
						
						webBrowserOperaciones.executeJavascript(cod);
						
						webBrowserOperaciones.executeJavascript(ges.cadena5Dias[0]);
						webBrowserOperaciones.executeJavascript(ges.graficoTarta);
						webBrowserOperaciones.executeJavascript(ges.cadenaMes);
						webBrowserOperaciones.executeJavascript(ges.cadenaAñoMes);
						webBrowserOperaciones.executeJavascript(ges.cadenaAños);
						
												
					}
					
					if(command.equals("5diasTotal")){
						webBrowserOperaciones.executeJavascript(ges.cadena5Dias[0]);
					}else if(command.equals("5diasIanus")){
						webBrowserOperaciones.executeJavascript(ges.cadena5Dias[1]);
					}else if(command.equals("5diasUrgencias")){
						webBrowserOperaciones.executeJavascript(ges.cadena5Dias[2]);
					}else if(command.equals("5diasXedoc")){
						webBrowserOperaciones.executeJavascript(ges.cadena5Dias[3]);
					}
					else if(command.contains("Todos")){
						String tipo = command.substring(5);
						if(command.contains("Ianus")){
							webBrowserOperaciones.executeJavascript(ges.cadena5Dias[1]);
						}
					}
					else if(command.substring(0,3).equals("Mes")){
						String año = command.substring(3,7);
						String mes = command.substring(7,9);
						String tipo = command.substring(9);
						
						// 1 Todos
						// 2 Ianus
						// 3 Urg
						// 4 Xedoc
						
						
						int tip = 0;
						if(tipo.equals("Total")){
							tip = 1;
						}
						else if(tipo.equals("Ianus")){
							tip = 2;
						}
						else if(tipo.equals("Urgencias")){
							tip = 3;
						}
						else if(tipo.equals("Xedoc")){
							tip = 4;
						}
						
						webBrowserOperaciones.executeJavascript(ges.getJSONMesDias(tip, año, mes));
					}
					else if(command.substring(0,3).equals("Año")){
						String año = command.substring(3,7);
						String tipo = command.substring(7);
						
						// 1 Todos
						// 2 Ianus
						// 3 Urg
						// 4 Xedoc
						
						int tip = 0;
						if(tipo.equals("Total")){
							tip = 1;
						}
						else if(tipo.equals("Ianus")){
							tip = 2;
						}
						else if(tipo.equals("Urgencias")){
							tip = 3;
						}
						else if(tipo.equals("Xedoc")){
							tip = 4;
						}
						
						webBrowserOperaciones.executeJavascript(ges.getJSONAnualMes(tip, año));
					}
					else if(command.substring(0,3).equals("Añs")){
					//	area2d_Total
						String cadena = command.substring(3);
						String[] partes = cadena.split("_");
						
						// 1 Todos
						// 2 Ianus
						// 3 Urg
						// 4 Xedoc
						
						String tipo = partes[1];
						
						int tip = 0;
						if(tipo.equals("Total")){
							tip = 1;
						}
						else if(tipo.equals("Ianus")){
							tip = 2;
						}
						else if(tipo.equals("Urgencias")){
							tip = 3;
						}
						else if(tipo.equals("Xedoc")){
							tip = 4;
						}
						
						webBrowserOperaciones.executeJavascript(ges.getJSONTipoGrafico(tip,partes[0] ));
						
						
					}

					
				}
				
				
	//   ABRIR *****************************************************************
				else if (panelActivo.equals(ABRIR)) {
					System.out.println("Abrimos carpeta");

					if (command.equals("abrir")) {
						CargaListaPdfs pdfs = new CargaListaPdfs(true);
						Inicio.carpetasSeleccionadas.add(pdfs.rutaCarpeta);
						if(!pdfs.cancelado){
							Inicio.inicioIanus = new InicioIanus(pdfs);
							Inicio.inicioIanus.rutaCarpeta = pdfs.rutaCompletaCarpeta;
							System.out.println("Ruta completa carpeta " + pdfs.rutaCompletaCarpeta);
							Inicio.ventanasCargadas = true;
							//webBrowserOperaciones.navigate("K:/Desarrollo/git/Prometeo/Prometeo/Prometeo/ocr.pdf");
							webBrowserOperaciones.setVisible(false);
							
						}

					}
					if(command.equals("firmado")){
						
						System.out.println("Firmado... entrando");
						
						carpeta = new Carpetas(true);
						
						System.out.println("inicializado carpetas");
						
						String codigoCarpetasmetro;
						
						if(!Inicio.carpetaXedocFirmado){
							codigoCarpetasmetro = carpeta.getCodigoJavascript();
						}
						else{
							codigoCarpetasmetro = carpeta.getCodigoJavascriptXedoc();
						}
						
						String tipoCarpeta = "Firmado";
						if(Inicio.carpetaDudas){
							tipoCarpeta = "Dudas " + Inicio.usuario.alias;
						}
						
						if(Inicio.carpetaXedocFirmado){
							 tipoCarpeta = "Xedoc";
						}
						
						if(!Inicio.carpetaXedocFirmado){
							codigoCarpetasmetro = 	""
									
									+ "document.getElementById('firmado').innerHTML = '" + tipoCarpeta + "';" + LS +
									 "document.getElementById('pdfstotales').innerHTML='" + carpeta.numeroPdfsTotales + "';" + LS +
									 "document.getElementById('pdfspendientes').innerHTML='" + carpeta.numeroPdfsPendientes + "';" + LS +
									 "var oldNodo = document.getElementById('nuevo');" + LS +
									 "if(oldNodo != null){oldNodo.parentNode.removeChild(oldNodo);}" + LS +
									 "var nodo = document.createElement('div');" + LS +
													"nodo.id='nuevo';" + LS + 
													"nodo.innerHTML = \"" + codigoCarpetasmetro +"\";" + LS +
													"var contenedor = document.getElementById('insertar');" + LS +
													"contenedor.appendChild(nodo);" + 
													"";// <a href='#' >holaaaa</a>";
						}
						
						/*
						else{
							
							codigoCarpetasmetro = ""

									+ "document.getElementById('pdfstotales').innerHTML='" + carpeta.numeroPdfsTotales + "';" + LS +
									"var oldNodo = document.getElementById('nuevo');" + LS +
									"if(oldNodo != null){oldNodo.parentNode.removeChild(oldNodo);}" + LS +
									"var nodo = document.createElement('div');" + LS +
													"nodo.id='nuevo';" + LS + 
													"nodo.innerHTML = \"" + codigoCarpetasmetro +"\";" + LS +
													"var contenedor = document.getElementById('insertar');" + LS +
													"contenedor.appendChild(nodo);" + LS +
													"";// <a href='#' >holaaaa</a>";
								
							
						}
						*/
						// System.out.println(codigoCarpetasmetro);
						System.out.println("Ejecutamos el codigo");
						
						webBrowserOperaciones.executeJavascript(codigoCarpetasmetro);
						
						/******   Bandejas ************************/
						/*
						if(Inicio.carpetaXedocFirmado){
							
							BandejasXedoc bandejas = new BandejasXedoc();
							
							String cadenaUsuarios = "";
							
							for(int i=0;i<bandejas.listaBandejas.size();i++){
								
								String cadenaAux = "";
								
								if(bandejas.listaBandejas.get(i).usuario.equals("Ana")){
									cadenaAux = "document.getElementById('p1').innerHTML = '" + bandejas.listaBandejas.get(i).numPdfs + "';" + LS;
								}else if(bandejas.listaBandejas.get(i).usuario.equals("Asun")){
									cadenaAux = "document.getElementById('p2').innerHTML = '" + bandejas.listaBandejas.get(i).numPdfs + "';" + LS;
								}else if(bandejas.listaBandejas.get(i).usuario.equals("Conchi")){
									cadenaAux = "document.getElementById('p3').innerHTML = '" + bandejas.listaBandejas.get(i).numPdfs + "';" + LS;
								}else if(bandejas.listaBandejas.get(i).usuario.equals("Esther")){
									cadenaAux = "document.getElementById('p4').innerHTML = '" + bandejas.listaBandejas.get(i).numPdfs + "';" + LS;
								}else if(bandejas.listaBandejas.get(i).usuario.equals("Iago")){
									cadenaAux = "document.getElementById('p5').innerHTML = '" + bandejas.listaBandejas.get(i).numPdfs + "';" + LS;
								}else if(bandejas.listaBandejas.get(i).usuario.equals("Irene")){
									cadenaAux = "document.getElementById('p6').innerHTML = '" + bandejas.listaBandejas.get(i).numPdfs + "';" + LS;
								}else if(bandejas.listaBandejas.get(i).usuario.equals("Isa")){
									cadenaAux = "document.getElementById('p7').innerHTML = '" + bandejas.listaBandejas.get(i).numPdfs + "';" + LS;
								}else if(bandejas.listaBandejas.get(i).usuario.equals("Juanma")){
									cadenaAux = "document.getElementById('p8').innerHTML = '" + bandejas.listaBandejas.get(i).numPdfs + "';" + LS;
								}else if(bandejas.listaBandejas.get(i).usuario.equals("Man")){
									cadenaAux = "document.getElementById('p9').innerHTML = '" + bandejas.listaBandejas.get(i).numPdfs + "';" + LS;
								}else if(bandejas.listaBandejas.get(i).usuario.equals("Oscar")){
									cadenaAux = "document.getElementById('p10').innerHTML = '" + bandejas.listaBandejas.get(i).numPdfs + "';" + LS;
								}else if(bandejas.listaBandejas.get(i).usuario.equals("Salva")){
									cadenaAux = "document.getElementById('p11').innerHTML = '" + bandejas.listaBandejas.get(i).numPdfs + "';" + LS;
								}else if(bandejas.listaBandejas.get(i).usuario.equals("Ruben")){
									cadenaAux = "document.getElementById('p12').innerHTML = '" + bandejas.listaBandejas.get(i).numPdfs + "';" + LS;
								}
								cadenaUsuarios = cadenaUsuarios + cadenaAux;
							}
							
							System.out.println("Ejecutando bandejas....");
							webBrowserOperaciones.executeJavascript(cadenaUsuarios);
						}
							*/
						
						
					}
					if(command.contains("carpeta_")){
						if(!Inicio.carpetaXedocFirmado){
							System.out.println(command);
							int numCarpeta = Integer.parseInt(command.substring(8, 10));

							Directorio dir = carpeta.arrayCarpetas.get(numCarpeta);
							
							System.out.println(dir.directorio.getAbsolutePath());
							
							CargaListaPdfs pdfs = new CargaListaPdfs(true,dir.directorio);
							Inicio.carpetasSeleccionadas.add(pdfs.rutaCarpeta);
							
							
							
							Inicio.inicioIanus = new InicioIanus(pdfs);
							Inicio.inicioIanus.rutaCarpeta = pdfs.rutaCompletaCarpeta;
							
							System.out.println("Ruta completa carpeta " + pdfs.rutaCompletaCarpeta);
							
							Inicio.ventanasCargadas = true;
								//webBrowserOperaciones.navigate("K:/Desarrollo/git/Prometeo/Prometeo/Prometeo/ocr.pdf");
							webBrowserOperaciones.setVisible(false);
						}
						else{
							System.out.println("Carpeta de xedoc firmado");
							int numCarpeta = Integer.parseInt(command.substring(8, 10));

							Directorio dir = carpeta.arrayCarpetas.get(numCarpeta);
							
							File carpetaOrigen = dir.directorio;
							
							String nombreSinAlmohadilla = dir.directorio.getName().replace("#", "");
							
							String directorioDestino = Inicio.rutaXedoc + "\\" + Inicio.usuario.usuario + "\\";
							String rutaDestino =  directorioDestino + nombreSinAlmohadilla;
							File carpetaDestino = new File(rutaDestino);
							
							carpetaOrigen.renameTo(carpetaDestino);
							
					    	String cadena = "explorer.exe " + directorioDestino;
							try {
								Runtime.getRuntime().exec(cadena);
							} catch (IOException ev) {
								// TODO Auto-generated catch block
								ev.printStackTrace();
							}
							
							webBrowserOperaciones.executeJavascript("document.all.ins.click()");
							
							Calendario calendario = new Calendario();
							String rutaCopiaCarpetaDestino = calendario.getCarpetaFinal(false,Inicio.usuario.urgencias);
							
							rutaCopiaCarpetaDestino = rutaCopiaCarpetaDestino + "\\" + carpetaDestino.getName() + " " + Inicio.usuario.alias;
							System.out.println("Ruta de la copia de xedoc... \n" + rutaCopiaCarpetaDestino);
							
							CopiarDirectorio.copiarDirectorios(carpetaDestino, new File(rutaCopiaCarpetaDestino));
							JOptionPane.showMessageDialog(null, "Carpeta copiada en \"05 Xedoc originales\"");
						}

			
					}
					if(command.equals("aptdoRevis")){
						Dudas.abrirCarpetaApartadoRevisado();
					}
					if(command.equals("aptdoFirm")){
						Dudas.abrirCarpetaApartadoFirmado();
					}
					if(command.equals("dudas")){

						Inicio.carpetaDudas = true;
						webBrowserOperaciones.setVisible(true);
						webBrowserOperaciones.navigate(DIR_ABRIR);
						panelActivo = ABRIR;
					}
					if(command.equals("enviarXedoc")){
						new GestionCarpetasXedoc();
					}
					
					if(command.equals("iniciarXedoc")){
						
				//		if(Inicio.carpetaXedocFirmado){

							InicioXedoc xedoc = new InicioXedoc();
							
							// webBrowserOperaciones.navigate(GestionJacob.direccionIanus);
							

							
							String codig = ""
									+ "principal.main.document.getElementById('login').value = '" + Inicio.usuario.usuario + "';"
									+ "principal.main.document.getElementById('password').value = '" + Inicio.usuario.password + "';"
									+ "principal.main.aceptar();"
									+ "";
							
							MiHilo hiloXedoc = new MiHilo(codig, 1000);
							hiloXedoc.start();
							
							
							webBrowserOperaciones.executeJavascript(codig);
							
							frame.setState(Frame.ICONIFIED);
				//		}
					}
					if(command.equals("carpetaXedocO")){
						Dudas.abrirCarpetaXedocOriginales();
					}
					
					maximizada = Pantalla.restaurar(frame);
				} 
				
		//   AYUDA *****************************************************************	
				else if(panelActivo.equals(AYUDA)){
					
					if(command.contains("buscar_")){
						
						String campos[] = command.split("_");
						
						System.out.println("Número de campos... " + campos.length);
						for(int i=0;i<campos.length;i++){
							System.out.println(campos[i]);
						}
						
						String busqueda = command.substring(7);
						
						getHTMLayuda(campos);
						
						/*
						String resultado = CadenasJavascript.getCodigoAyuda(busqueda);
						
						System.out.println("    \n\n" + resultado + "\n\n");
						
						resultado = "<div id='thumbs' class='navigation'>Borrar esta parte.</div>"
								+ "<ul class='thumbs noscript'>"
								+ "</ul>"
								+ "</div>"
								+ "<div style='clear: both;'></div>"
								+ ""
								+ ""; 
						
						String insertarCodigo = 	"" +
								
									 "var oldNodo = document.getElementById('nuevo');" + LS +
									 "if(oldNodo != null){oldNodo.parentNode.removeChild(oldNodo);}" + LS +
									 "var nodo = document.createElement('div');" + LS +
													"nodo.id='nuevo';" + LS + 
													"nodo.innerHTML = \"" + resultado +"\";" + LS +
													"var contenedor = document.getElementById('hor');" + LS +
													"contenedor.appendChild(nodo);" + 
													"";// <a href='#' >holaaaa</a>";
						
						 resultado = "alert('" + resultado + "');"; 
						
					//	webBrowserOperaciones.executeJavascript(insertarCodigo);
						
						webBrowserOperaciones.executeJavascript(insertarCodigo);
						
						String insertarJavascript = ""
								+ ""
								+ "function loadJs(fichero){" + LS
									+ "var fileref = document.createElement('script');" + LS
									+ "fileref.setAttribute('type','text/javascript');" + LS
									+ "fileref.setAttribute('src','fichero');" + LS
									+ "document.getElementsByTagName('head')[0].appendChild(fileref);" + LS
								+ "}" + LS
								+ ""
								+ "loadJs('C:/ianus/ayuda.js');" + LS
								+ "alert(retornarFecha(););" + LS
								;
						
						*/
						
						
						webBrowserOperaciones.navigate(DIR_AYUDA_F);

						
					}
					maximizada = Pantalla.maximizar(frame);
				}
		
		//   NORMAS *****************************************************************				
				else if(panelActivo.equals(NORMAS)){
					
					if(command.contains("recargar")){
						webBrowserOperaciones.navigate(DIR_NORMAS);
					}
					
					if(command.contains("norma_")){
						String index = command.substring(6);
						System.out.println(index);
						String codigo = "modalOn('" + index + "');" + LS;
						
						int indice = Integer.parseInt(index) - 1;
						
						String fecha = Inicio.listaNormasIanus.get(indice).fecha;
						int tamaño = Inicio.listaNormasIanus.get(indice).servicios.size();
						String serv = "";
						for(int j=0;j<tamaño;j++){
							serv += Inicio.listaNormasIanus.get(indice).servicios.get(j);
							if(j+1 == tamaño){
								serv += ".";
							}
							else{
								serv += ", ";
							}
						}
						String textoNorma = Inicio.listaNormasIanus.get(indice).texto;
						
						String codigo2 = ""
								+ "document.getElementById('fecha').innerHTML = '" + fecha + "';" + LS
								+ "document.getElementById('servicios').innerHTML = '" + serv + "';" + LS
								+ "document.getElementById('textoNorma').innerHTML = '" + textoNorma + "';" + LS
								+ "document.getElementById('editar').name='" + index + "';"
								+ "document.getElementById('imagen').src='" + Inicio.listaNormasIanus.get(indice).rutaImagen + "';"
								;
								
						codigo += codigo2;
						
						String codigo25 = "modalOn('" + index + "');" + LS;
						
						webBrowserOperaciones.executeJavascript(codigo25);
					
						

					}
					
					if(command.contains("editar")){
						String index = command.substring(7);
						int indice = Integer.parseInt(index) - 1;
						System.out.println(index);
						
						String[] serviciosSelecc = new String[Inicio.listaNormasIanus.get(indice).servicios.size()];
						for(int i=0;i<Inicio.listaNormasIanus.get(indice).servicios.size();i++){
							serviciosSelecc[i] = Inicio.listaNormasIanus.get(indice).servicios.get(i);
						}
						
						new EditorHTML(indice,Inicio.listaNormasIanus.get(indice).texto,Inicio.listaServicios, serviciosSelecc );
					}
					
					if(command.equals("todas")){
						
						System.out.println("Estamos en todas las normas");
						
						String filas = ""
								+ "<div class = \"outer\">"
									+ "<div class = \"innera\">"
										+ "<table class=\"table hovered\">"
										+ "<thead>"
										+ "<tr><th class=\"text-left\">Fecha</th>"
											+ "<th class=\"text-left\">Servicios</th>"
											+ "<th class=\"text-left\">Contenido</th>"
										+ "</tr>"
										+ "</thead>"
										+ "<tfoot></tfoot>"
										+ "<tbody>";
						
						
						
						
						for(int i=0;i<Inicio.listaNormasIanus.size();i++){
							
							
							int tamaño = Inicio.listaNormasIanus.get(i).servicios.size();
							String serv = "";
							for(int j=0;j<tamaño;j++){
								serv += Inicio.listaNormasIanus.get(i).servicios.get(j);
								if(j+1 == tamaño){
									serv += ".";
								}
								else{
									serv += ", ";
								}
							}
							
							int limite = 100;
							if(Inicio.listaNormasIanus.get(i).textoSinFormato.length() < limite){
								limite = Inicio.listaNormasIanus.get(i).textoSinFormato.length();
							}
							String encabezado = Inicio.listaNormasIanus.get(i).textoSinFormato.substring(0,limite);
									
							
							filas += (
									"<tr>"
									+ "<a id=\"" + (i+1) + "\" href=\"command://norma_" + (i+1) + "\">"
									+ "<td>" + Inicio.listaNormasIanus.get(i).fecha + "</td>"
									+ "<td>" + serv + "</td>"
									+ "<td>" + encabezado + "</td>"
									+ "</a></tr>");
						}
						

						filas += "</tbody></table></div></div>";
					
					
						String lolailo = ""		 +						 
								 "var oldNodo = document.getElementById('nuevo');" + LS +
								 "if(oldNodo != null){oldNodo.parentNode.removeChild(oldNodo);}" + LS +
								 "var nodo = document.createElement('div');" + LS +
												"nodo.id='nuevo';" + LS + 
												"nodo.innerHTML = '" + filas +"';" + LS +
												"var contenedor = document.getElementById('insertar');" + LS +
												"contenedor.appendChild(nodo);" + 
												"";// <a href='#' >holaaaa</a>";
						
						webBrowserOperaciones.executeJavascript(lolailo);
						

						
						maximizada = Pantalla.maximizar(frame);
					}
				} else if (panelActivo.equals(SALIR)) {
					if (command.contains("salir")) {
						Cerrar.cerrarTodo();
						frame.dispose();
						System.exit(0);
					}
					if (command.contains("carpetas")){
						if(inicioCarpetasSubidas){
							inicioCarpetasSubidas = false;
							carpetasSubidas = MoverCarpetas.getCarpetasSubidas();
						
							String codigoCarpetasSubidas = CadenasJavascript.getCarpetasSubidas(carpetasSubidas);
							
							System.out.println(codigoCarpetasSubidas);
							
							int numCarpetasSubidas = carpetasSubidas.size();
							int numPdfsSubidos = 0;
							
							System.out.println(numPdfsSubidos);
							
							for(int i=0;i<numCarpetasSubidas;i++){
								numPdfsSubidos += carpetasSubidas.get(i).numeroPdfs;
							}
							
							codigoCarpetasSubidas = 	"" +
								"document.getElementById('carpetasSubidas').innerHTML='" +numCarpetasSubidas + "';" + LS +
								"document.getElementById('pdfsSubidos').innerHTML='" + numPdfsSubidos + "';" + LS +
								 "var oldNodo = document.getElementById('nuevo');" + LS +
								 "if(oldNodo != null){oldNodo.parentNode.removeChild(oldNodo);}" + LS +
								 "var nodo = document.createElement('div');" + LS +
												"nodo.id='nuevo';" + LS + 
												"nodo.innerHTML = \"" + codigoCarpetasSubidas +"\";" + LS +
												"var contenedor = document.getElementById('listaCarpetas');" + LS +
												"contenedor.appendChild(nodo);" + 
												"";// <a href='#' >holaaaa</a>";			 
									 
							//		 "document.getElementById('listaCarpetas').innerHTML = '" + codigoCarpetasSubidas + "';" + LS +
							//		 		"";
									 
						
							
							webBrowserOperaciones.executeJavascript(codigoCarpetasSubidas);
						}
						else{
							String rutaCarpetaFirmados = Inicio.rutaFirmados;
							if(Inicio.usuario.urgencias){
								rutaCarpetaFirmados = Inicio.rutaFirmadosUrgencias + "\\01 " + Inicio.usuario.alias + "\\03 Firmado" ;
							}
							
							// Borrar esta asignacion
						//	rutaCarpetaFirmados = "j:\\digitalización\\00 documentacion\\03 Firmado";
							
					    	String cadena = "explorer.exe " + rutaCarpetaFirmados;
							try {
								Runtime.getRuntime().exec(cadena);
							} catch (IOException ev) {
								// TODO Auto-generated catch block
								ev.printStackTrace();
							}
						}
					}
					else if(command.contains("carpeta_")){
						int index = Integer.valueOf(command.substring(8));
						System.out.println(index);
						System.out.println(command);
				
						carpetasSubidas.get(index).asociado = !carpetasSubidas.get(index).asociado;
						
						String gestionaCarpeta = //"alert(document.all.carpeta_0.name);";
							""	+ "var lista = document.all." + command + ";" + LS
								+ "var check = document.all." + command + "c;" + LS 
								+ "var seleccionado = check.checked;" + LS
								+ "if(seleccionado === true){ " + LS
									+ "check.checked = false;lista.style.backgroundColor = 'DarkSlateGray';}" + LS
								+ "else{check.checked = true; lista.style.backgroundColor = 'red';}";
					
								webBrowserOperaciones.executeJavascript(gestionaCarpeta);		
					}
					else if(command.contains("enviar")){
						
						Cerrar.cerrarTodo();
						
						MoverCarpetas.enviarAasociados(carpetasSubidas);
						inicioCarpetasSubidas = true;
						
						webBrowserOperaciones.reloadPage();
					}
				} 
			}
		});

		// Inicializando paneles

		panelOperaciones.add(webBrowserOperaciones, BorderLayout.CENTER);
		panelControl.add(webBrowserControl, BorderLayout.CENTER);
		panelControl.add(panelTitulo, BorderLayout.NORTH);

		panelOperaciones.setBackground(Color.yellow);
		panelControl.setBackground(Color.blue);
		panelPrincipal.setBorder(BorderFactory.createLineBorder(Color.black));

		panelControl.setMinimumSize(new Dimension(860, 120));
		panelControl.setMaximumSize(new Dimension(860, 120));
		panelControl.setPreferredSize(new Dimension(860, 110));
		// TitledBorder borde =
		// BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder());

		panelTitulo.setMinimumSize(new Dimension(860, 20));
		panelTitulo.setMaximumSize(new Dimension(860, 20));
		panelTitulo.setPreferredSize(new Dimension(860, 20));

		panelControl.setVisible(barraPanelControlVisible);

		panelPrincipal.add(panelControl, BorderLayout.NORTH);
		panelPrincipal.add(panelOperaciones, BorderLayout.CENTER);

		// Panel Barra del titulo

		Fecha fecha = new Fecha();
		String fechaTexto = "   " + fecha.nombreDia.toUpperCase() + ", "
				+ fecha.dia + " DE " + fecha.nombreMes.toUpperCase() + " DE " + fecha.año;
		
		fechaHoy = fechaTexto;
		
		labelFecha = new JLabel();
		labelMaximizar = new JLabel();
		labelMinimizar = new JLabel();

		panelTitulo.setBackground(new java.awt.Color(0, 0, 0));
		panelTitulo.setMaximumSize(new java.awt.Dimension(1200, 20));
		panelTitulo.setMinimumSize(new java.awt.Dimension(860, 20));
		panelTitulo.setPreferredSize(new java.awt.Dimension(860, 20));

		labelFecha.setBackground(new java.awt.Color(0, 0, 0));
		labelFecha.setForeground(new java.awt.Color(255, 255, 255));
		labelFecha.setText(fechaTexto);

		labelMaximizar.setBackground(new java.awt.Color(0, 0, 0));
		labelMaximizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		labelMaximizar.setForeground(new java.awt.Color(255, 255, 255));

		
		labelMaximizar.setText("<html>&#61441;</html>");

		
		labelMaximizar.setFocusable(false);
		//labelMaximizar.setMargin(new java.awt.Insets(2, 2, 2, 14));
		labelMaximizar.setMaximumSize(new java.awt.Dimension(45, 20));
		labelMaximizar.setMinimumSize(new java.awt.Dimension(45, 20));
		labelMaximizar.setPreferredSize(new java.awt.Dimension(45, 20));
		labelMaximizar.addMouseListener(this);

		labelMinimizar.setBackground(new java.awt.Color(0, 0, 0));
		labelMinimizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		labelMinimizar.setForeground(new java.awt.Color(255, 255, 255));
		labelMinimizar.setText("_");
		labelMinimizar.setFocusable(false);
		// labelMinimizar.setMargin(new java.awt.Insets(2, 14, 2, 2));
		labelMinimizar.setMaximumSize(new java.awt.Dimension(39, 20));
		labelMinimizar.setMinimumSize(new java.awt.Dimension(39, 20));
		labelMinimizar.setPreferredSize(new java.awt.Dimension(39, 20));
		labelMinimizar.addMouseListener(this);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				panelTitulo);
		panelTitulo.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addComponent(labelFecha,
								javax.swing.GroupLayout.DEFAULT_SIZE, 733,
								Short.MAX_VALUE)
						.addGap(49, 49, 49)
						.addComponent(labelMinimizar,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)
						.addComponent(labelMaximizar,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(labelFecha)
												.addComponent(
														labelMaximizar,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														labelMinimizar,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(277, 277, 277)));

		panelTitulo.addMouseListener(this);

		panelTitulo.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				Point punto = MouseInfo.getPointerInfo().getLocation();
				frame.setLocation(punto.x - coordenadasRaton.x, punto.y
						- coordenadasRaton.y);
			}
		});

		return panelPrincipal;
	}



	public void setPdf(String ruta, String nombrePdf, Color color) {

		if(!Inicio.esWin64){
			webBrowserControl.navigate(ruta);

			TitledBorder tb = BorderFactory.createTitledBorder(
					BorderFactory.createEtchedBorder(), "  " + nombrePdf
							+ "        ", TitledBorder.RIGHT, TitledBorder.TOP,
					new Font("TimesRoman", Font.BOLD, 14), Color.black);

			panelOperaciones.setBorder(tb);
			panelOperaciones.setBackground(color);
		}


	}
	
	public void getHTMLayuda(String busqueda[]){
		
		String textoInicial = LeerArchivos.obtenerHtml(DIR_AYUDA_X1);
		String textoFinal = LeerArchivos.obtenerHtml(DIR_AYUDA_X2);

		String textoCentral1, textoCentral2;
		
		textoCentral1 = CadenasJavascript.getCodigoAyuda1();
		
		System.out.println("Imprime codigo 1 \n" + textoCentral1);
		
		textoCentral2 = CadenasJavascript.getCodigoAyuda2(busqueda);

		String htmlCompleto = textoInicial + textoCentral1 + textoCentral2 + textoFinal;
		
		EscribirArchivos.escribeHtmlUtf(htmlCompleto, DIR_AYUDA_F);
	}
	

	/* Standard main method to try that test as a standalone application. */
	public InterfazPrincipal(final String nombreFrame, final Color color,
			final boolean barraControl) {

		// UIUtils.setPreferredLookAndFeel();

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(
					VentanaExplorador.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(
					VentanaExplorador.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(
					VentanaExplorador.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(
					VentanaExplorador.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		// /////

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame = new JFrame(nombreFrame);
				barraPanelControlVisible = barraControl;
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(createContent());
				frame.setSize(850, 1000);
				frame.setExtendedState(Frame.MAXIMIZED_BOTH);
				
				frame.setMinimumSize(new Dimension(800, 120));
				frame.setLocationByPlatform(true);

				panelOperaciones.setBackground(color);
				frame.setUndecorated(true);
				frame.setLocationRelativeTo(null);

				 //URL pathIcon = this.getClass().getClassLoader().getResource("prometeo/ico/icono.png");
				// Toolkit kit = Toolkit.getDefaultToolkit();
				// Image img = kit.createImage(pathIcon);
			//	frame.setIconImage(new ImageIcon(getClass().getResource("/prometeo/ico/ico.png")).getImage());
				frame.setVisible(true);

			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent() == panelTitulo){
			coordenadasRaton = e.getPoint();
			System.out.println("Pinche en el panel");
		}
		if(e.getComponent() == labelMaximizar){
			if(!maximizada){
				frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
				System.out.println("Pinche en maximizar");
				maximizada = true;
			}
			else{
				frame.setExtendedState(Frame.NORMAL);
				System.out.println("Restaurar");
				maximizada = false;
			}
			
			
		}
		if(e.getComponent() == labelMinimizar){
			frame.setState(Frame.ICONIFIED);
			System.out.println("Pinche en minimizar");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

class MiHilo extends Thread {

	Usuario usuario = null;
	
	String codigo = "";
	
	boolean esCodigo = false;
	int retardo = 0;
	
	protected static final String LS = System.getProperty("line.separator");

	MiHilo(final Usuario usuario, final int retardo) {
		this.usuario = usuario;
		this.retardo = retardo;
	}
	
	MiHilo(final String codigo, final int retardo){
		this.codigo = codigo;
		esCodigo = true;
		this.retardo = retardo;
	}

	public void run() {
		try {
			Thread.sleep(retardo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				/*
				 * String cadenas = "<ul id=\'da-thumbs\' class=\'da-thumbs\'>";
				 * 
				 * final String cadena =
				 * "var nodo = document.getElementsByName('vamos');" + LS +
				 * "nodo[0].innerHTML= '" + cadenas +"';" + LS +
				 * "alert(nodo[0].id);" ;
				 */
				
				String cadena = "";
				
				if(esCodigo){
					cadena = codigo;
					System.out.println("Retardo para meter el usuario...");
				}
				else{
					cadena = CadenasJavascript.putUsuario(usuario);

				}
				
				Inicio.panelPrincipal.webBrowserOperaciones.executeJavascript(cadena);
				
			//	Inicio.panelPrincipal.webBrowserOperaciones.executeJavascript("alert('hola');");


				
				
			}
		});
	}
}
