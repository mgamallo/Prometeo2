package es.mgamallo.prometeo;



import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Calendar;

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
	

	static public ArrayList<Directorio> getCarpetasSubidas(){
		
		String ruta = InicioIanus.RUTA;
		String rutab = InicioIanus.RUTAB;
		
		if(Inicio.usuario.urgencias){
			ruta = InicioIanus.RUTAURG + "\\01 " + Inicio.usuario.alias + "\\03 Firmado";
			rutab = InicioIanus.RUTAURGB + "\\01 " + Inicio.usuario.alias + "\\03 Firmado";
		}
		
		File directorioFirmados = new File(ruta);
		if(!directorioFirmados.exists()){
			ruta = rutab;
			directorioFirmados = new File(ruta);
		}
		
		String dudas = Inicio.rutaDudas + "\\" + Inicio.usuario.alias;
		File fDudas = new File(dudas);
		boolean tieneCarpetaDudas = false;
		if(fDudas.exists()){
			tieneCarpetaDudas = true;
		}
		
		if(directorioFirmados.exists()){

			FileFilter filtro = new FileFilter(){
	
				@Override
				public boolean accept(File fichero) {
					// TODO Auto-generated method stub
					
					if(fichero.isDirectory()){
						if(fichero.getName().endsWith("@" + Inicio.usuario.alias)){
							return true;
						}
					}
					return false;
				}
				
			};
			
			File[] directoriosUsuario = directorioFirmados.listFiles(filtro);
			
			ArrayList<Directorio> carpetas = new ArrayList<Directorio>();
			for(int i=0;i<directoriosUsuario.length;i++){
				carpetas.add(new Directorio(directoriosUsuario[i],true));
				System.out.println(carpetas.get(i).servicio + "   " + carpetas.get(i).numeroPdfs);
			}
			
			if(tieneCarpetaDudas){
				directoriosUsuario = fDudas.listFiles(filtro);
				for(int i=0;i<directoriosUsuario.length;i++){
					carpetas.add(new Directorio(directoriosUsuario[i],true));
					System.out.println(carpetas.get(i).servicio + "   " + carpetas.get(i).numeroPdfs);
				}
			}
			
			return carpetas;
		}
		
		return null;
	}
	
	static public void enviarAasociados(ArrayList<Directorio> carpetas){
		String rutaAsociados = Inicio.rutaAsociados;
		if(Inicio.usuario.urgencias){
			rutaAsociados = Inicio.rutaAsociadosUrgencias;
		}
		
		// Borrar
	//	rutaAsociados = "j:\\digitalización\\00 documentacion\\04 Asociado";
		
		int contadorMovidos=0;
		int contadorError=0;
		
		// Obsoleto. Cambiado para que se separe por dias
		// rutaAsociados = devuelveFecha(rutaAsociados);
		Calendario calendario = new Calendario();
		rutaAsociados = calendario.getCarpetaFinal(true);
		System.out.println("Ruta asociados: " + rutaAsociados);
		
		File directorio = new File(rutaAsociados);
		boolean creaDirectorio = directorio.mkdirs();
		
		for(int i=0;i<carpetas.size();i++){
			if(carpetas.get(i).asociado){
				String rutaNueva = rutaAsociados + "\\" + carpetas.get(i).directorio.getName();
				
				System.out.println("Ruta asociados completa " + rutaNueva);
				
				File nombreNuevo = new File(rutaNueva);
				boolean renombrado = carpetas.get(i).directorio.renameTo(nombreNuevo);
				
				if(renombrado){
					contadorMovidos++;
				}
				else{
					contadorError++;
				}
			}
		}
		
		JOptionPane.showMessageDialog(null, "Se han movido " + contadorMovidos +" carpetas.\n No se han podido mover " +contadorError );
		
	}
	
	private static String devuelveFecha(String rutaAsociados){
		
		Calendar fecha = Calendar.getInstance();
		int año = fecha.get(Calendar.YEAR);
		rutaAsociados+=("\\" + año);
		int mes = fecha.get(Calendar.MONTH);
		String nombreMes = "";
		switch(mes){
		case 0:	nombreMes = "01 Enero";break;
		case 1:	nombreMes = "02 Febrero";break;
		case 2:	nombreMes = "03 Marzo";break;
		case 3:	nombreMes = "04 Abril";break;
		case 4:	nombreMes = "05 Mayo";break;
		case 5:	nombreMes = "06 Junio";break;
		case 6:	nombreMes = "07 Julio";break;
		case 7:	nombreMes = "08 Agosto";break;
		case 8:	nombreMes = "09 Septiembre";break;
		case 9:	nombreMes = "10 Octubre";break;
		case 10:	nombreMes = "11 Noviembre";break;
		case 11:	nombreMes = "12 Diciembre";break;
		}
		rutaAsociados+=("\\" + nombreMes);
			
		return rutaAsociados;
		
	}
}
