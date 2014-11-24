package es.mgamallo.prometeo;


import java.io.File;
import java.io.FileFilter;

import javax.swing.JOptionPane;

public class MoverCarpetas {
	static public void moverPdfs(String rutaCarpeta, int hastaAqui){
		
		if(Inicio.documento.length > 0){
			rutaCarpeta = Inicio.documento[0].rutaArchivo;
			
			System.out.println(rutaCarpeta);
			
			int indice = rutaCarpeta.lastIndexOf("\\");
			String ruta = rutaCarpeta.substring(0, indice);
			
			File rutaVieja = new File(ruta);
			
			FileFilter filtro = new FileFilter(){

				@Override
				public boolean accept(File fichero) {
					// TODO Auto-generated method stub
					
					if(!fichero.isDirectory()){
						return true;
					}
					return false;
				}
			};
			
			File[] archivosRutaVieja = rutaVieja.listFiles(filtro);
			if(archivosRutaVieja == null){
				JOptionPane.showMessageDialog(null, "No hay ficheros para mover");
			}
			
			int contadorM = 0;
			int contadorE = 0;
			String rutaYaSubidos = ruta + "\\Ya subidos @" + Inicio.usuario.alias;
			
			int index;
			String aux;
			String rutaNueva;
			
			File directorio = new File(rutaYaSubidos);
			directorio.mkdir();
			
			int numFicheros = archivosRutaVieja.length;
			for(int i=0;i<=hastaAqui;i++){
				aux = archivosRutaVieja[i].getName();
		//		aux = archivosRutaVieja[0].getAbsolute
				rutaNueva = rutaYaSubidos + "\\" + aux;
				
				File nombreNuevo = new File(rutaNueva);
				boolean renombrado = archivosRutaVieja[i].renameTo(nombreNuevo);

				if(renombrado){
					contadorM++;
				}else{
					contadorE++;
				}
			}
			if(contadorE == 0)
				JOptionPane.showMessageDialog(null, "Se han movido todos los pdfs: " + contadorM);
			else
				JOptionPane.showMessageDialog(null, "Se han movido " + contadorM + " pdfs.\n No se han podido mover " + contadorE
						+ ". Revisa la carpeta. ");

			File carpetaVieja = new File(ruta);
			
			System.out.println("Renombrar ruta: " + ruta);
			
			int j= ruta.lastIndexOf(Inicio.usuario.alias);
			ruta = ruta.substring(0, j-1);

			File carpetaNueva = new File(ruta);
			boolean renombrado = carpetaVieja.renameTo(carpetaNueva);
			if(!renombrado){
				JOptionPane.showMessageDialog(null, "No se ha podido renombrar la carpeta");
			}
		}

		//System.exit(0);
	}
}
