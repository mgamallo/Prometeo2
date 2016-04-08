package es.mgamallo.prometeo;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JOptionPane;

public class GestionCarpetasXedoc {

	protected static final String LS = System.getProperty("line.separator");
	
	String usuario1 = "";
	String usuario2 = "";
	String usuario3 = "";
	
	ArrayList<ListaXedoc> listasXedoc = new ArrayList<ListaXedoc>();
	
	public GestionCarpetasXedoc() {
		// TODO Auto-generated constructor stub
		
		Inicio.panelPrincipal.webBrowserOperaciones.executeJavascript("document.getElementById('enlaceEnviar').style.visibility='hidden';");
		Inicio.panelPrincipal.webBrowserOperaciones.executeJavascript("mostrarVentana();");
		
		String cadena = "return document.getElementById('b1').innerHTML";
		Object respuesta = Inicio.panelPrincipal.webBrowserOperaciones.executeJavascriptWithResult(cadena);
		
		if(!respuesta.equals("vacío")){
			usuario1 = respuesta.toString();
			listasXedoc.add(new ListaXedoc(usuario1, getCarpetas(1)));
		}
		
		cadena = "return document.getElementById('b2').innerHTML";
		respuesta = Inicio.panelPrincipal.webBrowserOperaciones.executeJavascriptWithResult(cadena);
		
		if(!respuesta.equals("vacío")){
			usuario2 = respuesta.toString();
			listasXedoc.add(new ListaXedoc(usuario2, getCarpetas(2)));
		}
		
		cadena = "return document.getElementById('b3').innerHTML";
		respuesta = Inicio.panelPrincipal.webBrowserOperaciones.executeJavascriptWithResult(cadena);
		
		if(!respuesta.equals("vacío")){
			usuario3 = respuesta.toString();
			listasXedoc.add(new ListaXedoc(usuario3, getCarpetas(3)));
		}
		
				
		for(int i=0;i<listasXedoc.size();i++){
			boolean resultado = crearCarpetaContainer(listasXedoc.get(i));
			// Inicio.panelPrincipal.webBrowserOperaciones.executeJavascript("alert('hola');");
		}
		
		// Inicio.panelPrincipal.webBrowserOperaciones.reloadPage();
		Inicio.panelPrincipal.webBrowserOperaciones.navigate(Inicio.panelPrincipal.DIR_ABRIR_X);
		Inicio.panelPrincipal.webBrowserOperaciones.setBarsVisible(true);
		Inicio.panelPrincipal.webBrowserOperaciones.setBarsVisible(false);
		
		
		Inicio.panelPrincipal.inicializaUsuarios();
		Inicio.panelPrincipal.getCarpetasFormato(null, null, null);
		
		Inicio.panelPrincipal.panelActivo = Inicio.panelPrincipal.ABRIR;
	}

	private String getCarpetas(int numLista){
		
		String idLista = "list" + numLista;
		
		String cadena = ""
				+ ""
				+ "var list = document.getElementById('" + idLista + "');" + LS
				+ "var carpetas = list.getElementsByTagName('li');" + LS
				+ "var cadena = '';" + LS
				+ "for(var i=0;i<carpetas.length;i++){cadena = cadena + carpetas[i].getAttribute('title') + ';';} " + LS
				+ "return cadena;" + LS
				+ "";
		
		Object respuesta = Inicio.panelPrincipal.webBrowserOperaciones.executeJavascriptWithResult(cadena);
		
		return respuesta.toString();
	}
	
	
	private boolean crearCarpetaContainer(ListaXedoc lista){
		
		
		String user = "";
		
		int aux = Inicio.usuarios.length;
		for(int i=0;i<aux;i++){
			if(lista.usuario.equals(Inicio.usuarios[i].alias)){
				user = Inicio.usuarios[i].usuario;
			}
		}
		
		File carpetas[] = new File[lista.rutasCarpetas.length];
		
		
		Calendario calendario = new Calendario();
		String rutaCopiaCarpetaDestino = calendario.getCarpetaFinal(false,Inicio.usuario.tipoDocumentacion);
		File rut = new File(rutaCopiaCarpetaDestino);
		rut.mkdirs();
		
		System.out.println(rutaCopiaCarpetaDestino);
		
		String rutaBaseInicial = Inicio.rutaFirmadosXedoc;
		if(Inicio.usuario.tipoDocumentacion == 0){
			rutaBaseInicial = Inicio.rutaFirmadosUrgencias + "\\01 " + Inicio.usuario.alias + "\\03 Firmado Xedoc";
		}
		else if(Inicio.usuario.tipoDocumentacion == 2){
			rutaBaseInicial = Inicio.rutaFirmadosXedocSalnes;
		}
		
		System.out.println(rutaBaseInicial);
				
		for(int i=0;i<carpetas.length;i++){
			carpetas[i] = new File(rutaBaseInicial + "/" + lista.rutasCarpetas[i]);
			String rutaCopiaCarpetaDestinoCompleta = rutaCopiaCarpetaDestino + "\\" + carpetas[i].getName() + " " + lista.usuario;
			System.out.println("Ruta de la copia de xedoc... \n" + rutaCopiaCarpetaDestinoCompleta);
			
			
			
			if(carpetas[i].getName().toLowerCase().contains("666 rebotado")){
				Calendar calend = Calendar.getInstance();
				int hora = calend.get(Calendar.HOUR_OF_DAY);
				int min = calend.get(Calendar.MINUTE);
				int sec = calend.get(Calendar.MILLISECOND);
				
				String id = hora + "" + min + "" + sec;
				
				rutaCopiaCarpetaDestinoCompleta += " " + id;
			}
			
			File ficheroAux = new File(rutaCopiaCarpetaDestinoCompleta);
			
			while(ficheroAux.exists()){
				String nuevoNombre = ficheroAux.getAbsolutePath() + " bis";
				ficheroAux = new File(nuevoNombre);
			}
			System.out.println("Existe fichero? " + ficheroAux.exists());

			System.out.println("Renombrando... " + carpetas[i].renameTo(ficheroAux));
		
			
			System.out.println(carpetas[i].getAbsolutePath());
			carpetas[i] = ficheroAux;
			System.out.println(carpetas[i].getAbsolutePath());
			/*
			CopiarDirectorio.copiarDirectorios(carpetas[i], new File(rutaCopiaCarpetaDestino));
			JOptionPane.showMessageDialog(null, "Carpeta copiada en \"05 Xedoc originales\"");
			*/
		}
		
		
		
		ArrayList<File> listaPdfs = new ArrayList<File>();
		
		for(int i=0;i<carpetas.length;i++){
			// carpetas[i] = new File(Inicio.rutaFirmadosXedoc + "/" + lista.rutasCarpetas[i]);
			File pdfsCarpeta[] = carpetas[i].listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					// TODO Auto-generated method stub
					return pathname.getName().endsWith(".pdf");

				}
			});
			
			for(int j=0;j<pdfsCarpeta.length;j++){
				System.out.println(pdfsCarpeta[j].getName());
				listaPdfs.add(pdfsCarpeta[j]);
			}
		}
		
		
		/*  borrar
		 * 
		for(int i=0;i<carpetas.length;i++){
			System.out.println(carpetas[i].getAbsolutePath());
			System.out.println(carpetas[i].exists());
		}
		*/
		
		Calendar calend = Calendar.getInstance();
		int hora = calend.get(Calendar.HOUR_OF_DAY);
		int min = calend.get(Calendar.MINUTE);
		int sec = calend.get(Calendar.MILLISECOND);
		
		String id = hora + "" + min + "" + sec;
		
		
		String directorioDestino = Inicio.rutaXedoc + "\\" + user + "\\";
		String rutaDestino =  directorioDestino + id;
		
		System.out.println(rutaDestino);
		
		File carpetaDestino = new File(rutaDestino);
		
		carpetaDestino.mkdirs();
		
		
		String gif = gifAleatorio();
		String cadena = ""
				+ "var gf = document.getElementById('gif');"
				+ "gf.src = '" + gif + "';"
				+ "";
		
		Inicio.panelPrincipal.webBrowserOperaciones.executeJavascript(cadena);
		
		boolean correcto = true;
		for(int i=0;i<listaPdfs.size();i++){
			
			String index = String.valueOf(i);
			if(index.length() < 2){
				index = "000" + index;
			}else if(index.length() < 3){
				index = "00" + index;
			}else if(index.length() <4){
				index = "0" + index;
			}
			
			String nuevoDestino = rutaDestino + "/" + index + "_" + listaPdfs.get(i).getName();
			System.out.println(listaPdfs.get(i));
			System.out.println(nuevoDestino);
			
						
			cadena = ""
						+ "var sub = document.getElementById('subiendoUsuario');"
						+ "sub.innerHTML='Subiendo los documentos de " + lista.usuario + ".';"
						+ "var subDoc = document.getElementById('subiendoDoc');"
						+ "subDoc.innerHTML='" + (i + 1) + " de " + listaPdfs.size() + "';"
						+ "";
			Inicio.panelPrincipal.webBrowserOperaciones.executeJavascript(cadena);

			
			
			if(!CopiarFichero.copiar(listaPdfs.get(i).getAbsolutePath(),nuevoDestino)){
				correcto = false;
				break;
			}
			
		}
		
		System.out.println("Resultado " + correcto);
		
		if(!correcto){
			JOptionPane.showMessageDialog(null, "Error al enviar a Xedoc");
		}
		
		else{
			JOptionPane.showMessageDialog(null, "Copia finalizada de " + lista.usuario);
		}
		
		return correcto;

	}
	
	private String gifAleatorio(){
		
		String cadena = "imagenes/preloaders/0";
		
		Random rnd = new Random();
		int num = rnd.nextInt(6);
		cadena += ("" + (num+1) + ".gif");
		
		return cadena;
	}
	
}


class ListaXedoc{
	String usuario;
	String rutasCarpetas[];
	
	public ListaXedoc(String usuario, String cadenaRutas) {
		// TODO Auto-generated constructor stub
		this.usuario = usuario;
		rutasCarpetas = cadenaRutas.split(";");
		// imprimeCarpetas();
	}
	
	private void imprimeCarpetas(){
		System.out.println(usuario);
		for(int i=0;i<rutasCarpetas.length;i++){
			System.out.println(rutasCarpetas[i]);
		}
	}
}