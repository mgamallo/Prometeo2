package es.mgamallo.prometeo;

import java.awt.Color;
import java.io.IOException;

import javax.swing.SwingUtilities;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class Inicio {

	protected static final String LS = System.getProperty("line.separator");
	
	public static InterfazPrincipal panelPrincipal;
	
	static public Usuario[] usuarios;
	static public Usuario usuario;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		NativeInterface.open(); 
		
		// Inicializamos el interfaz cargando los usuarios
		
		cargarUsuarios();
		
		String cadenaUsuarios = CadenasJavascript.getCadenaUsuarios();
		
		String textoBase = LeerArchivos.obtenerHtml("K:/Desarrollo/git/Prometeo/Prometeo/Prometeo/prometeo/Htmls/usuarios/Digitalizacion/usuarios.html");
				
		int puntoInsercion = textoBase.indexOf("id=\"seccion\">");
		String textoInicio = textoBase.substring(0,puntoInsercion + 14);
		String textoFinal = textoBase.substring(puntoInsercion + 14);
		String htmlCompleto = textoInicio + cadenaUsuarios + textoFinal;
		
		EscribirArchivos.escribeHtml(htmlCompleto);
		
		panelPrincipal = new InterfazPrincipal("Prometeo 1.0.0", new Color(255,222,173), false);
	
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


