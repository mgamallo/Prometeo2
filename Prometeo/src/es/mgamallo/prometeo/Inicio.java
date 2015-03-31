package es.mgamallo.prometeo;


import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class Inicio {

	protected static final String LS = System.getProperty("line.separator");
	
	public static InterfazPrincipal panelPrincipal;
	
	public static int numeroPantallas = 0;
	public static int anchoP = 0;
	public static int altoP = 0;
	
	static public Usuario[] usuarios;
	static public Usuario usuario = new Usuario();
	static public boolean contraseña = true;
	
	static Rectangle rVentanaInterfazPrincipalMin = new Rectangle(0, 0, 850, 1000);
	static Rectangle rVentanaInterfazPrincipalMax = new Rectangle(0, 0, 850, 1250);
	static Rectangle rVentanaExploradorMin = new Rectangle(851, 0, 175, 1000);
	static Rectangle rVentanaExploradorMax = new Rectangle(851, 0, 175, 1250);
	static Rectangle rVentanaNombres = new Rectangle(0, 1001, 1024, 250);
	static Rectangle rVentanaControlIanus = new Rectangle();
	
	static public String rutaRevisados = ":\\digitalización\\00 documentacion\\02 Revisado";
	static public String rutaFirmados = ":\\digitalización\\00 documentacion\\03 Firmado";
	static public String rutaFirmadosXedoc = ":\\digitalización\\00 documentacion\\03 Firmado Xedoc";
	static public String rutaXedoc = ":\\digitalización\\00 documentacion\\03 Xedoc";
	static public String rutaFirmadosUrgencias = ":\\DIGITALIZACIÓN\\01 INFORMES URG (Colectiva)";
	static public String rutaAsociados = ":\\digitalización\\00 documentacion\\04 Asociado";
	static public String rutaAsociadosUrgencias = ":\\DIGITALIZACIÓN\\01 INFORMES URG (Colectiva)\\04 Asociado";
	static public String rutaDudas = ":\\digitalización\\00 documentacion\\99 Dudas";
	static public String rutaXedocOriginales = ":\\DIGITALIZACIÓN\\00 DOCUMENTACION\\05 XEDOC ORIGINALES";
	
	// Revisar estas rutas
	static public String rutaNormas = "c:\\Desarrollo\\git\\prometeo\\prometeo\\Prometeo\\Prometeo\\txt\\Normas\\";
	// static public String rutaNormas = ":\\DIGITALIZACIÓN\\00 DOCUMENTACION\\99 Nombres Normalizados\\Prometeo\\txt\\Normas";
	
	static public String rutaAvisos;
	static public String rutaAyuda;
	
	static public InicioIanus inicioIanus;
	
	static public LeerExcel leerExcel;
	static public ArrayList<Norma> listaNormasIanus = new ArrayList<Norma>();
	static public String[] listaServicios;
	
	static ArrayList<ExcepcionesServicio> tablaExcepcionesXedoc = new ArrayList<ExcepcionesServicio>();
	
	//	Ruta completa de las carpetas seleccionadas
	static ArrayList<String> carpetasSeleccionadas = new ArrayList<String>();
	
	
	static public VentanaNombres vNombres;
	static public VentanaExplorador vExplorador;
	static public VentanaControlIanus vControlIanus;
	
	static public Paciente paciente1= new Paciente();
	static public Paciente paciente2 = new Paciente();
	
	static public DocumentoXedoc documento1 = new DocumentoXedoc();
	static public DocumentoXedoc documento2 = new DocumentoXedoc();	
	
	static boolean ventanasCargadas = false;
	static public boolean teclasHabilitadas = false;
	
	static public Documento[] documento;  //	Array de documentos de la tanda
	static public int indiceArchivoSelecc = 0;   // Nº de la lista de pdfs
	static public Documento documentoActivo;
	static public String nhcDelIanus1 = ""; 
	static public String nhcDelIanus2 = ""; 

	static public boolean ocr = true;
	static public boolean ianus1onTop = true;


	static public Gestion2Ianus gestion;
	
	static public String unidadHDDejecutable = "";
	static public String unidadHDDvirtual = "";
	
	static public String usuarioLogeadoWindows = "";
	
	static public boolean xedoc = false;
	static public boolean carpetaDudas = false;
	static public boolean carpetaXedocFirmado = false;
	static public String contestacionDudas = "";
	
	static public Estadistica estadistica;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		NativeInterface.open(); 
		
		
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
				
		numeroPantallas = gs.length;
		
		if(numeroPantallas==1){
			rVentanaExploradorMin = new Rectangle(851, 0, 175, 800);
			rVentanaExploradorMax = new Rectangle(851, 0, 175, 1052);
			rVentanaNombres = new Rectangle(0, 751, 1024, 250);
		}

		
		anchoP = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	    altoP = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	    
	    System.out.println("El numero de pantallas es " + numeroPantallas);
	    System.out.println("Resolución: " + anchoP + ", " + altoP);
		
	    
	    usuarioLogeadoWindows = System.getProperty("user.name");
	    System.out.println("Usuario logeado: " + usuarioLogeadoWindows);
	    
	    if(!usuarioLogeadoWindows.equals("cahcpon")){
	    	
	    }
	    
		// Inicializamos el interfaz cargando los usuarios
		
		cargarUsuarios();
		
		String cadenaUsuarios = CadenasJavascript.getCadenaUsuarios();
		
		File miDir = new File(".");
		
		try {
			// System.out.println(miDir.getCanonicalPath());
			unidadHDDejecutable = miDir.getCanonicalPath().substring(0,1);
			System.out.println(unidadHDDejecutable);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

// Lio con la unidad de acceso. Parche.
		unidadHDDvirtual = detectaUnidadHDD();
		System.out.println("Letra de la unidad... " + unidadHDDvirtual);
		
		rutaFirmados = unidadHDDvirtual + rutaFirmados;
		rutaFirmadosXedoc = unidadHDDvirtual + rutaFirmadosXedoc;
		rutaXedocOriginales = unidadHDDvirtual + rutaXedocOriginales;
		rutaXedoc = unidadHDDvirtual + rutaXedoc;
		rutaRevisados = unidadHDDvirtual + rutaRevisados;
		rutaFirmadosUrgencias = unidadHDDvirtual + rutaFirmadosUrgencias;
		rutaAsociados = unidadHDDvirtual + rutaAsociados;
		rutaAsociadosUrgencias = unidadHDDvirtual + rutaAsociadosUrgencias;
		rutaDudas = unidadHDDvirtual + rutaDudas;
		
	//	rutaNormas = unidadHDDvirtual + rutaNormas;
		
	//	String textoBase = LeerArchivos.obtenerHtml("d:/Desarrollo/git/Prometeo/Prometeo/Prometeo/prometeo/Htmls/usuarios/Digitalizacion/usuarios.html");
		

		String textoBase = LeerArchivos.obtenerHtml(unidadHDDejecutable+ ":/Desarrollo/git/Prometeo/Prometeo/Prometeo/prometeo/Htmls/usuarios/Digitalizacion/usuarios.html");
	
	//	unidadHDD = "j";
	//	String textoBase = LeerArchivos.obtenerHtml(unidadHDD + ":/DIGITALIZACIÓN/00 DOCUMENTACION/99 Nombres Normalizados/Prometeo/Prometeo/Htmls/usuarios/Digitalizacion/usuarios.html");
		
		int puntoInsercion = textoBase.indexOf("id=\"seccion\">");
		String textoInicio = textoBase.substring(0,puntoInsercion + 14);
		String textoFinal = textoBase.substring(puntoInsercion + 14);
		String htmlCompleto = textoInicio + cadenaUsuarios + textoFinal;
		
		EscribirArchivos.escribeHtml(htmlCompleto, ":/Desarrollo/git/Prometeo/Prometeo/Prometeo/Prometeo/Htmls/usuarios/Digitalizacion/usuariosSesion.html");
		
		
		
		panelPrincipal = new InterfazPrincipal("Prometeo 1.0.0", new Color(255,222,173), false);
	
		CapturaRatonYTeclado capturaTeclado = new CapturaRatonYTeclado();
		
		// Carga estadisticas. De momento del día anterior.
		estadistica = new Estadistica();
		
		// Borrar
		Detecta.detectaDudasJavier();
		
		/******  Cargamos Normas
		***************************************/
		
		listaNormasIanus = Txt.leerNormasTxt(rutaNormas);
		System.out.println("Numero de normas: " + listaNormasIanus.size());
		
		
		String listaNormas = CadenasJavascript.getListaNormas();
		textoBase = LeerArchivos.obtenerHtml(unidadHDDejecutable + ":\\Desarrollo\\git\\prometeo\\prometeo\\Prometeo\\Prometeo\\Htmls\\NormasBase.html");
		
		puntoInsercion = textoBase.indexOf("class=\"container\">");
		textoInicio = textoBase.substring(0,puntoInsercion + 20);
		textoFinal = textoBase.substring(puntoInsercion + 20);
		htmlCompleto = textoInicio + listaNormas + textoFinal;
		
		EscribirArchivos.escribeHtml(htmlCompleto, ":\\Desarrollo\\git\\prometeo\\prometeo\\Prometeo\\Prometeo\\Htmls\\Normas.html");

		
		
		leerExcel = new LeerExcel();
		leerExcel.leer("Documentos.xls");
		listaServicios = leerExcel.getServicios();
		
		NativeInterface.runEventPump(); 
	}

	static private void cargarUsuarios(){
		
		String datosBrutos[] = Txt.obtenerUsuarios("prometeo/txt/usuarios.txt");

		usuarios = new Usuario[datosBrutos.length];
		
		for(int i=0;i<datosBrutos.length;i++){
			System.out.println(datosBrutos[i]);
			String[] us = datosBrutos[i].split("@");
			Usuario user = new Usuario();
			user.alias= us[0];
			user.imagen= us[1];
			if(us[2].equals("urg")){
				user.urgencias = true;
			}
			else{
				user.urgencias = false;
			}
			user.usuario = us[3];
			if(user.usuario.equals(" ")){
				user.usuario = "";
			}
			usuarios[i] = user;
		}
	}
	
	private static String detectaUnidadHDD(){
		
		String posibleRuta = "h" + rutaFirmados;
		File ruta = new File(posibleRuta);
		if(ruta.exists()){
			return "h";
		}
		else{
			posibleRuta = "j" + rutaFirmados;
			ruta = new File(posibleRuta);
			if(ruta.exists())
				return "j";
		}

		JOptionPane.showMessageDialog(null, "Problemas con la unidad de disco");
		
		return null;
	}
	
}


