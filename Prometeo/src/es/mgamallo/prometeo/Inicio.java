package es.mgamallo.prometeo;


import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class Inicio {

	protected static final String LS = System.getProperty("line.separator");
	
	public static InterfazPrincipal panelPrincipal;
	
	static public Usuario[] usuarios;
	static public Usuario usuario;
	static public boolean contraseña = true;
	
	static Rectangle rVentanaInterfazPrincipal = new Rectangle(0, 0, 850, 1000);
	static Rectangle rVentanaExplorador = new Rectangle(851, 0, 175, 1000);
	static Rectangle rVentanaNombres = new Rectangle(0, 1001, 1024, 250);
	static Rectangle rVentanaControlIanus = new Rectangle();
	
	static public InicioIanus inicioIanus;
	
	//	Ruta completa de las carpetas seleccionadas
	static ArrayList<String> carpetasSeleccionadas = new ArrayList<String>();
	
	
	static public VentanaNombres vNombres;
	static public VentanaExplorador vExplorador;
	static public VentanaControlIanus vControlIanus;
	
	static public Paciente paciente1= new Paciente();
	static public Paciente paciente2 = new Paciente();
	
	
	static boolean ventanasCargadas = false;
	
	static public Documento[] documento;  //	Array de documentos de la tanda
	static public int indiceArchivoSelecc = 0;   // Nº de la lista de pdfs
	static public Documento documentoActivo;
	static public String nhcDelIanus1 = ""; 
	static public String nhcDelIanus2 = ""; 

	static public boolean ocr = true;
	static public boolean ianus1onTop = true;
	
	static public boolean teclashabilitadas = false;

	static public Gestion2Ianus gestion;
	
	static public String unidadHDD = "";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		NativeInterface.open(); 
		
		// Inicializamos el interfaz cargando los usuarios
		
		cargarUsuarios();
		
		String cadenaUsuarios = CadenasJavascript.getCadenaUsuarios();
		
		File miDir = new File(".");
		
		try {
			// System.out.println(miDir.getCanonicalPath());
			unidadHDD = miDir.getCanonicalPath().substring(0,1);
			System.out.println(unidadHDD);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	String textoBase = LeerArchivos.obtenerHtml("d:/Desarrollo/git/Prometeo/Prometeo/Prometeo/prometeo/Htmls/usuarios/Digitalizacion/usuarios.html");
		String textoBase = LeerArchivos.obtenerHtml(unidadHDD + ":/Desarrollo/git/Prometeo/Prometeo/Prometeo/prometeo/Htmls/usuarios/Digitalizacion/usuarios.html");
				
		int puntoInsercion = textoBase.indexOf("id=\"seccion\">");
		String textoInicio = textoBase.substring(0,puntoInsercion + 14);
		String textoFinal = textoBase.substring(puntoInsercion + 14);
		String htmlCompleto = textoInicio + cadenaUsuarios + textoFinal;
		
		EscribirArchivos.escribeHtml(htmlCompleto);
		
		panelPrincipal = new InterfazPrincipal("Prometeo 1.0.0", new Color(255,222,173), false);
	
		CapturaRatonYTeclado capturaTeclado = new CapturaRatonYTeclado();
		
		
		NativeInterface.runEventPump(); 
	}

	static private void cargarUsuarios(){
		
		String datosBrutos[] = Txt.obtenerUsuarios("prometeo/txt/usuarios.txt");
		
		usuarios = new Usuario[datosBrutos.length];
		
		for(int i=0;i<datosBrutos.length;i++){
			String[] us = datosBrutos[i].split("@");
			Usuario user = new Usuario();
			user.alias= us[0];
			user.imagen= us[1];
			usuarios[i] = user;
		}
	}
	
}


