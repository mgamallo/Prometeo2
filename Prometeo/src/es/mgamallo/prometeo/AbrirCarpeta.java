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
	String rutaCompletaCarpeta;
	JFileChooser explorador;
	
	boolean directorioSeleccionado;
		
	AbrirCarpeta(boolean renombrar){
		if(Inicio.usuario.tipoDocumentacion == 0){
			ruta = InicioIanus.RUTAURG + "\\01 " + Inicio.usuario.alias + "\\03 Firmado";
			rutab = InicioIanus.RUTAURGB + "\\01 " + Inicio.usuario.alias + "\\03 Firmado";
		}
		else if(Inicio.usuario.tipoDocumentacion == 2){
			ruta = InicioIanus.rutaSalnes;
			rutab = InicioIanus.rutaSalnesB;
		}
		

// ruta = "d:/02 Area Pruebas/03 Firmado";
		
		directorioSeleccionado = listaPdfs();
	}

	AbrirCarpeta(boolean renombrar, boolean metro){
		if(Inicio.usuario.tipoDocumentacion == 0){
			ruta = InicioIanus.RUTAURG + "\\01 " + Inicio.usuario.alias + "\\03 Firmado";
			rutab = InicioIanus.RUTAURGB + "\\01 " + Inicio.usuario.alias + "\\03 Firmado";
		}	
		else if(Inicio.usuario.tipoDocumentacion == 2){
			ruta = InicioIanus.rutaSalnes;
			rutab = InicioIanus.rutaSalnesB;
		}
	}
	
	boolean listaPdfs(){
		explorador = new JFileChooser();
		explorador.setDialogTitle("Abrir carpeta...");
		if(!(new File(ruta).exists()))
			ruta = rutab;
		
		System.out.println("Ruta carpeta... " + ruta);
		
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
			File nombreNuevo = new File(explorador.getSelectedFile().toString() + " @" + Inicio.usuario.alias);
			
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
		
		rutaCompletaCarpeta = cadenaSinEspaciosDobles;
		
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
	
	
	public File[] getPdfsMetro(boolean renombrar, File directori){
		
		File nombreViejo = directori;
		File nombreNuevo = new File(directori.getAbsolutePath().toString() + " @" + Inicio.usuario.alias);
		//	Renombrar directorio
		if(renombrar){
			
			nombreNuevo = new File(directori.getAbsolutePath().toString() + " @" + Inicio.usuario.alias);
			
			boolean renombrado = nombreViejo.renameTo(nombreNuevo);
			if(!renombrado){

				JOptionPane.showMessageDialog( null,"Directorio en uso. No se ha podido renombrar.");
			}
		}
		
		//	Eliminamos los espacios en blanco duplicados de la ruta
		File nombreConEspacios = nombreNuevo;
		String cadenaSinEspaciosDobles = eliminarEspaciosEnBlanco(nombreConEspacios);
		
		rutaCompletaCarpeta = cadenaSinEspaciosDobles;
		
		System.out.println(directori.getAbsolutePath().toString());
		System.out.println(cadenaSinEspaciosDobles);
		
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
		
		System.out.println("numero de pdfs metro " + pdfs.length);
		
		return pdfs;
	}
	
	
	public File[] getPdfsDudas(){
		
		
		//	Obtener ficheros pdf
		if(!(new File(ruta).exists())){
			ruta = rutab;
		}
		File directorio = new File(ruta);
		// System.out.println(directorio.getAbsolutePath());

		rutaCompletaCarpeta = ruta;
		
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

