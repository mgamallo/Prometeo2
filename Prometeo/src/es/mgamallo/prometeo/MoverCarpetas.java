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
		
		String rutas[] = {InicioIanus.RUTA,InicioIanus.RUTAB, InicioIanus.rutaSalnes, InicioIanus.rutaSalnesB};
		
		
		if(Inicio.usuario.tipoDocumentacion == 0){
			rutas[0] = InicioIanus.RUTAURG + "\\01 " + Inicio.usuario.alias + "\\03 Firmado";
			rutas[1] = InicioIanus.RUTAURGB + "\\01 " + Inicio.usuario.alias + "\\03 Firmado";
		}
		
		ArrayList<Directorio> carpetas = new ArrayList<Directorio>();
		
		
		for(int z=0;z<4;z= z+2){
			
			
			
			File directorioFirmados = new File(rutas[z]);
			if(!directorioFirmados.exists()){
				directorioFirmados = new File(rutas[z+1]);
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
				
				boolean salnes = false;
				if(z > 1){
					salnes = true;
				}
				
				for(int i=0;i<directoriosUsuario.length;i++){
					carpetas.add(new Directorio(directoriosUsuario[i],true, salnes));
					System.out.println(carpetas.get(i).servicio + "   " + carpetas.get(i).numeroPdfs);
				}
				
				if(tieneCarpetaDudas){
					directoriosUsuario = fDudas.listFiles(filtro);
					for(int i=0;i<directoriosUsuario.length;i++){
						carpetas.add(new Directorio(directoriosUsuario[i],true, false));
						System.out.println(carpetas.get(i).servicio + "   " + carpetas.get(i).numeroPdfs);
					}
				}

			}
		}

		
		return carpetas;
	}
	
	static public void enviarAasociados(ArrayList<Directorio> carpetas){
		
		String rutaAsociadosCHOP = Inicio.rutaAsociados;
		String rutaAsociadosSALNES = Inicio.rutaAsociadosSalnes;
		if(Inicio.usuario.tipoDocumentacion == 0){
			rutaAsociadosCHOP = Inicio.rutaAsociadosUrgencias;
		}
		
		// Borrar
	//	rutaAsociados = "j:\\digitalización\\00 documentacion\\04 Asociado";
		
		int contadorMovidos=0;
		int contadorError=0;
		
		// Obsoleto. Cambiado para que se separe por dias
		// rutaAsociados = devuelveFecha(rutaAsociados);
		Calendario calendario = new Calendario();
		
		int urgDoc = 1;
		if(Inicio.usuario.tipoDocumentacion == 0){
			urgDoc = 0;
		}
		rutaAsociadosCHOP = calendario.getCarpetaFinal(true, urgDoc);
		rutaAsociadosSALNES = calendario.getCarpetaFinal(true, 2);
		System.out.println("Ruta asociados: " + rutaAsociadosCHOP);
		System.out.println("Ruta asociados: " + rutaAsociadosSALNES);
		
				
		File directorioCHOP = new File(rutaAsociadosCHOP);
		boolean creaDirectorioCHOP = directorioCHOP.mkdirs();
		File directorioSALNES = new File(rutaAsociadosSALNES);
		boolean creaDirectorioSALNES = directorioSALNES.mkdirs();
		
		for(int i=0;i<carpetas.size();i++){
			if(carpetas.get(i).asociado){
				String rutaAsociados = "";
				if(carpetas.get(i).salnes){
					rutaAsociados = rutaAsociadosSALNES;
				}
				else{
					rutaAsociados = rutaAsociadosCHOP;
				}
				
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
