package es.mgamallo.prometeo;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class GestionCarpetasXedoc {

	protected static final String LS = System.getProperty("line.separator");
	
	String usuario1 = "";
	String usuario2 = "";
	String usuario3 = "";
	
	ArrayList<ListaXedoc> listasXedoc = new ArrayList<ListaXedoc>();
	
	public GestionCarpetasXedoc() {
		// TODO Auto-generated constructor stub
		
		
		
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
			crearCarpetaContainer(listasXedoc.get(i));
		}
		
		Inicio.panelPrincipal.webBrowserOperaciones.reloadPage();
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
		String rutaCopiaCarpetaDestino = calendario.getCarpetaFinal(false,Inicio.usuario.urgencias);
		File rut = new File(rutaCopiaCarpetaDestino);
		rut.mkdirs();
		
		System.out.println(rutaCopiaCarpetaDestino);
		
		String rutaBaseInicial = Inicio.rutaFirmadosXedoc;
		if(Inicio.usuario.urgencias){
			rutaBaseInicial = Inicio.rutaFirmadosUrgencias + "\\01 " + Inicio.usuario.alias + "\\03 Firmado Xedoc";
		}
		
		System.out.println(rutaBaseInicial);
				
		for(int i=0;i<carpetas.length;i++){
			carpetas[i] = new File(rutaBaseInicial + "/" + lista.rutasCarpetas[i]);
			String rutaCopiaCarpetaDestinoCompleta = rutaCopiaCarpetaDestino + "\\" + carpetas[i].getName() + " " + lista.usuario;
			System.out.println("Ruta de la copia de xedoc... \n" + rutaCopiaCarpetaDestinoCompleta);
			
			File ficheroAux = new File(rutaCopiaCarpetaDestinoCompleta);
			
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