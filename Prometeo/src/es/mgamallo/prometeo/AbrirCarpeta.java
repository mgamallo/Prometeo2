package es.mgamallo.prometeo;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class AbrirCarpeta {
	
	String ruta =InicioIanus.RUTA; 
	String rutab =InicioIanus.RUTAB;												// trabajo
	
	String nombreCarpeta;
	JFileChooser explorador;
	
	boolean directorioSeleccionado;
		
	AbrirCarpeta(boolean renombrar){
		if(InicioIanus.documentacion == 0){
			ruta = InicioIanus.RUTAURG;
			rutab = InicioIanus.RUTAURGB;
		}
		

ruta = "K:/Desarrollo Zona Pruebas/03 Firmado";
		
		directorioSeleccionado = listaPdfs();
	}

	
	boolean listaPdfs(){
		explorador = new JFileChooser();
		explorador.setDialogTitle("Abrir carpeta...");
		if(!(new File(ruta).exists()))
			ruta = rutab;
		explorador.setCurrentDirectory(new File(ruta));
		explorador.setFileFilter(new FileNameExtensionFilter("Documentos PDF","pdf"));
		explorador.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int seleccion = explorador.showOpenDialog(null);
		
		if( seleccion == JFileChooser.APPROVE_OPTION){			
				return true;
		}
		else
			return false;
	}
	
	
	public File[] getPdfs(boolean renombrar){
		
		//	Renombrar directorio
		if(renombrar){
			File nombreViejo = new File(explorador.getSelectedFile().toString());
			File nombreNuevo = new File(explorador.getSelectedFile().toString() + " " + Inicio.usuario.alias);
			
			boolean renombrado = nombreViejo.renameTo(nombreNuevo);
			if(renombrado){
				//	Se asigna la nueva ruta
				explorador.setSelectedFile(nombreNuevo);
			}else{
				JOptionPane.showMessageDialog( null,"Directorio en uso. No se ha podido renombrar.");
			}
		}
		
		//	Eliminamos los espacios en blanco duplicados de la ruta
		File nombreConEspacios = new File(explorador.getSelectedFile().toString());
		String cadenaSinEspaciosDobles = eliminarEspaciosEnBlanco(nombreConEspacios);
		
		//	Obtener ficheros pdf
		File directorio = new File(cadenaSinEspaciosDobles);

		String rutaLarga = cadenaSinEspaciosDobles;
		int i = rutaLarga.lastIndexOf("\\");
		nombreCarpeta = rutaLarga.substring(i+1);

		File[] pdfs = directorio.listFiles(new FilenameFilter(){
				public boolean accept(File directorio, String name){
					return name.toLowerCase().endsWith(".pdf");
				}
		});
		
		return pdfs;
	}
	
	
	
	public File[] getPdfsDudas(){
		
		
		//	Obtener ficheros pdf
		if(!(new File(ruta).exists())){
			ruta = rutab;
		}
		File directorio = new File(ruta);
		// System.out.println(directorio.getAbsolutePath());

		String rutaLarga = explorador.getSelectedFile().toString();
		int i = rutaLarga.lastIndexOf("\\");
		nombreCarpeta = rutaLarga.substring(i+1);

		File[] pdfs = directorio.listFiles(new FilenameFilter(){
				public boolean accept(File directorio, String name){
					return name.toLowerCase().endsWith(".pdf");
				}
		});
		
		return pdfs;
	}

	
	
	public String eliminarEspaciosEnBlanco(File fichero){
		System.out.println(fichero.getAbsolutePath().toString());
		String cadenaActual = fichero.getAbsolutePath().toString();
		String aux = "";
		
		int longitud = cadenaActual.length();
		aux = Character.toString(cadenaActual.charAt(0));
		for(int i=1;i<longitud;i++){
			if(cadenaActual.charAt(i)!= ' '){
				aux += Character.toString(cadenaActual.charAt(i));
			}else if(cadenaActual.charAt(i-1) != ' '){
				aux += Character.toString(cadenaActual.charAt(i));
			}	
		}
		
		if(!aux.equals(cadenaActual)){

			File nombreNuevo = new File(aux);
		
			boolean renombrado = fichero.renameTo(nombreNuevo);
			if(renombrado){
				System.out.println("Fichero renombrado");
			}
		}
		return aux;
	}
}

