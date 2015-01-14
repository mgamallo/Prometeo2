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

	protected static final String LS = System.getProperty("line.separator");

	private Point coordenadasRaton = new Point();

	private final String ABRIR = "Abrir";
	private final String AYUDA = "Ayuda";
	private final String NORMAS = "Normas";
	private final String AVISOS = "Avisos";
	private final String USUARIO = "Usuario";
	public static final String SALIR = "Salir";

	public String panelActivo = USUARIO;
	public boolean abracadabra = false;
	
	final String DIR_CONTROL = Inicio.unidadHDDejecutable + ":/Desarrollo/git/Prometeo/Prometeo/Prometeo/prometeo/Htmls/control/control.html";
	final String DIR_OPERACIONES = Inicio.unidadHDDejecutable + ":/Desarrollo/git/Prometeo/Prometeo/Prometeo/prometeo/Htmls/usuarios/Digitalizacion/usuariosSesion.html";

//	final String DIR_ABRIR = Inicio.unidadHDD +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/abrir/abrir.html";
	final String DIR_ABRIR = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/abrir.html";
	final String DIR_AYUDA = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/ayuda/ayuda.html";
	final String DIR_NORMAS = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/normas.html";
	final String DIR_AVISOS = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/avisos/avisos.html";
	// final String DIR_USUARIO = Inicio.unidadHDD +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/prometeo/Htmls/Usuario/usuario.html";
	final String DIR_USUARIO = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/prometeo/Htmls/usuario.html";
	static final String DIR_SALIR = Inicio.unidadHDDejecutable +":/Desarrollo/git/Prometeo/Prometeo/Prometeo/prometeo/Htmls/salir.html";

	
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
					
					panelActivo = ABRIR;
				} else if ("ayuda".equals(command)) {
					webBrowserOperaciones.navigate(DIR_AYUDA);
					webBrowserOperaciones.setVisible(true);
					panelActivo = AYUDA;
				} else if ("normas".equals(command)) {
					webBrowserOperaciones.navigate(DIR_NORMAS);
					webBrowserOperaciones.setVisible(true);
					panelActivo = NORMAS;
				} else if ("avisos".equals(command)) {
					webBrowserOperaciones.navigate(DIR_AVISOS);
					webBrowserOperaciones.setVisible(true);
					panelActivo = AVISOS;
				}
				if ("usuario".equals(command)) {
					webBrowserOperaciones.navigate(DIR_USUARIO);
					webBrowserOperaciones.setVisible(true);
					MiHilo miHilo = new MiHilo(Inicio.usuario);
					miHilo.start();
					panelActivo = USUARIO;
				} else if ("salir".equals(command)) {
					webBrowserOperaciones.navigate(DIR_SALIR);
					webBrowserOperaciones.setVisible(true);
					panelActivo = SALIR;
					inicioCarpetasSubidas = true;

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
				System.out.println(command
						+ (parameters.length > 0 ? " "
								+ Arrays.toString(parameters) : ""));

				if (command.contains("user_")) {
					int numUsuario = Integer.parseInt(command.substring(5, 7));

					Inicio.usuario = Inicio.usuarios[numUsuario];
					
					new VentanaPassword(Inicio.usuario.alias, Inicio.usuario.usuario).setVisible(true);;
					
					if(abracadabra){
						barraPanelControlVisible = true;

						webBrowserOperaciones.navigate(DIR_USUARIO);
						MiHilo miHilo = new MiHilo(Inicio.usuario);
						miHilo.start();

						frame.setBounds(Inicio.rVentanaInterfazPrincipalMax);
						Inicio.panelPrincipal.panelControl.setVisible(true);
					}

					// panelActivo = "Usuario";
				} else if(panelActivo.equals(USUARIO)){
					System.out.println("Panel usuario");
					
					String cadena = "";
					
					if (command.equals("urg")) {
						String claseOn = "tile bg-cobalt bg-hover-lightGreen bd-yellow selected";
						String claseOff = "tile bg-green bg-hover-lightGreen bd-yellow";
						cadena = ""
								+ "document.getElementById('urg').className='" + claseOn + "';" + LS
								+ "document.getElementById('doc').className='" + claseOff + "';" + LS
								+ "document.getElementById('tipoDocumentacion').innerHTML = 'URGENCIAS';";
						
						Inicio.usuario.urgencias = true;
					}
					else if(command.equals("doc")){
						String claseOff = "tile bg-cobalt bg-hover-lightGreen bd-yellow";
						String claseOn = "tile bg-green bg-hover-lightGreen bd-yellow selected";
						cadena = ""
								+ "document.getElementById('doc').className='" + claseOn + "';" + LS
								+ "document.getElementById('urg').className='" + claseOff + "';" + LS
								+ "document.getElementById('tipoDocumentacion').innerHTML = 'DOCUMENTACIÓN';";
						
						Inicio.usuario.urgencias = false;
					}
					else if(command.equals("salir")){
						frame.dispose();
						Cerrar.cerrarTodo();
						System.exit(0);
					}
					
					webBrowserOperaciones.executeJavascript(cadena);
					
				}else if (panelActivo.equals(ABRIR)) {
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
						
						carpeta = new Carpetas(true);
						
						String codigoCarpetasmetro = carpeta.getCodigoJavascript();
						
						
						codigoCarpetasmetro = 	"" +
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
					
						
						webBrowserOperaciones.executeJavascript(codigoCarpetasmetro);
					}
					if(command.contains("carpeta_")){
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
								rutaCarpetaFirmados = Inicio.unidadHDDvirtual + Inicio.rutaFirmadosUrgencias + "\\01 " + Inicio.usuario.alias + "\\03 Firmado" ;
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

		labelFecha = new JLabel();
		labelMaximizar = new JLabel();
		labelMinimizar = new JLabel();

		panelTitulo.setBackground(new java.awt.Color(0, 0, 0));
		panelTitulo.setMaximumSize(new java.awt.Dimension(1200, 20));
		panelTitulo.setMinimumSize(new java.awt.Dimension(860, 20));
		panelTitulo.setPreferredSize(new java.awt.Dimension(860, 20));

		labelFecha.setBackground(new java.awt.Color(0, 0, 0));
		labelFecha.setForeground(new java.awt.Color(255, 255, 255));
		labelFecha.setText("  JUEVES, 18 DE SEPTIEMBRE DE 2014");

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

		webBrowserControl.navigate(ruta);

		TitledBorder tb = BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "  " + nombrePdf
						+ "        ", TitledBorder.RIGHT, TitledBorder.TOP,
				new Font("TimesRoman", Font.BOLD, 14), Color.black);

		panelOperaciones.setBorder(tb);
		panelOperaciones.setBackground(color);

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
				
				frame.setMinimumSize(new Dimension(800, 300));
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

	final Usuario usuario;
	protected static final String LS = System.getProperty("line.separator");

	MiHilo(final Usuario usuario) {
		this.usuario = usuario;
	}

	public void run() {
		try {
			Thread.sleep(500);
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

				final String cadena = CadenasJavascript
						.putUsuario(usuario);
				
			//	Inicio.panelPrincipal.webBrowserOperaciones.executeJavascript("alert('hola');");

				Inicio.panelPrincipal.webBrowserOperaciones
						.executeJavascript(cadena);
				
				
			}
		});
	}
}
