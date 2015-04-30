package es.mgamallo.prometeo;


import java.awt.Color;




import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;


public class Gestion2Ianus {
	
	public static String nombreIanus1 = "Ianus 1";
	public static String nombreIanus2 = "Ianus 2";

	protected static final String LS = System.getProperty("line.separator");
	
	int indiceNhc1 = 0;
	int indiceNhc2 = 0;
		
	int retardoAsociar = 1500;
	int retardoBuscarNodoFase2 = 200;
	
	
	Gestion2Ianus(){
		buscaNHCInicio();
	}
	
	private void buscaNHCInicio(){
		for(int i=0;i<Inicio.documento.length;i++){
			if(!Inicio.documento[i].nhc.contains("Separador")){
				Inicio.nhcDelIanus1 = Inicio.documento[i].nhc;
				indiceNhc1 = i;
				break;
			}
		}
		
		for(int i=indiceNhc1 + 1;i<Inicio.documento.length;i++){
			if(!Inicio.documento[i].nhc.equals(Inicio.nhcDelIanus1) &&
					!Inicio.documento[i].nhc.contains("Separador")){
				Inicio.nhcDelIanus2 = Inicio.documento[i].nhc;
				indiceNhc2 = i;
				break;
			}
		}
	}
	
	public void impresionInicial(){
		
		System.out.println("Indice 1: " + indiceNhc1);
		System.out.println("Indice 2: " + indiceNhc2);
		
		Inicio.indiceArchivoSelecc = indiceNhc1;
		Inicio.vExplorador.listaPdfs.setSelectedIndex(Inicio.indiceArchivoSelecc);
		Inicio.documentoActivo = Inicio.documento[Inicio.indiceArchivoSelecc];
		
		Inicio.panelPrincipal.webBrowserOperaciones.setVisible(true);
		
		if(!Inicio.esWin64){
		Inicio.panelPrincipal.webBrowserOperaciones
		.navigate(Inicio.documento[Inicio.indiceArchivoSelecc].rutaArchivo);
		}
		else{
		//	new VisualizaPdfWeb(Inicio.documento[Inicio.indiceArchivoSelecc].rutaArchivo);
			new VisualizaPdfReader(Inicio.documento[Inicio.indiceArchivoSelecc].rutaArchivo);
		}
		
		
		Inicio.vControlIanus.botonNHC.setText(Inicio.documento[indiceNhc1].nhc);
		Inicio.vControlIanus.botonServicio.setText(Inicio.documento[indiceNhc1].servicio);
		Inicio.vControlIanus.botonNombreDocumento.setText(Inicio.documento[indiceNhc1].nombreNormalizado);
		Inicio.vControlIanus.labelNumeroIanus.setText(nombreIanus1);
		Inicio.vControlIanus.labelNombrePdf.setText(Inicio.documento[indiceNhc1].nombreArchivo);
		
//**** Aquí deberíamos gestionar los colores verde, rojo...
		Inicio.vControlIanus.panelBotones.setBackground(new Color(80,200,120));
		
		
		Inicio.vNombres.comboServicio.setSelectedItem(Inicio.documentoActivo.servicio);
		System.out.println("Introduce el nhc 1 " + Inicio.nhcDelIanus1);
//		introduceNHC(Inicio.paciente1.ianus,Inicio.nhcDelIanus1,Inicio.documento[indiceNhc1].servicio,50,false);
		introduceNHC(Inicio.paciente1.ianus,nombreIanus1,Inicio.nhcDelIanus1,100);
		
		/*
	//	Inicio.ianus2.botonNHC.setText(Inicio.documento[indiceNhc2].nhc);
	//	Inicio.ianus2.botonServicio.setText(Inicio.documento[indiceNhc2].servicio);
	//	Inicio.ianus2.botonNombreDocumento.setText(Inicio.documento[indiceNhc2].nombreNormalizado);
	 * */
	
		System.out.println("Introduce el nhc 2 " + Inicio.nhcDelIanus2);
		
		Dispatch.put(Inicio.paciente2.ianus,"visible",false);
		introduceNHC(Inicio.paciente2.ianus,nombreIanus2,Inicio.nhcDelIanus2,100);
	//	introduceNHC(Inicio.paciente2.ianus,Inicio.nhcDelIanus2,Inicio.documento[indiceNhc2].servicio,2000,false);
	//	Dispatch.put(Inicio.ianus2,"visible",false);
		
		Inicio.ianus1onTop = true;
		
		buscaNodo(Inicio.paciente1.ianus,Inicio.documento[indiceNhc1].servicio,Inicio.documento[indiceNhc1].nombreNormalizado,true,false,Retardos.retardoCargarPaciente,false);
		buscaNodo(Inicio.paciente2.ianus,Inicio.documento[indiceNhc2].servicio,Inicio.documento[indiceNhc2].nombreNormalizado,true,false,Retardos.retardoCargarPaciente,false);
	//	Inicio.ianus1.frame.setAlwaysOnTop(true);
	}
	
	
	public static void introduceNHC(ActiveXComponent ianus, String nombreIanus, String nhc, int retardo ){
		HiloNHC introNHC = new HiloNHC(ianus,CadenasJavascript.introducirNHC1(nhc),nombreIanus,retardo,nhc);
		introNHC.start();
	}
	
	
	public static void buscaNodo(ActiveXComponent ianus, String servicio, String nombreDocumento, boolean inicializar,boolean buscaOtroNodo, int retardo, boolean subirAuto){
		
		System.out.println("BuscaNodo: Servicio... " + servicio + "NombreDoc... " + nombreDocumento);
		String tipoNodo = detectaTipoNodo(servicio, nombreDocumento);
		
		System.out.println("El tipo de nodo en buscaNodo es " + tipoNodo);
		
		String nombreNodoServicio = buscaNombreNodoServicio(servicio);
		
		HiloServicio hiloServicio = new HiloServicio(ianus, nombreNodoServicio, tipoNodo, retardo,inicializar,buscaOtroNodo,subirAuto);
		hiloServicio.start();
	}
	
	private static String detectaTipoNodo(String servicio, String nombreDocumento){
		
		String tipoNodo = "x";    // Nodo padre, sin excepciones
		
		/*
		for(int i=0;i<InicioIanus.tablaExcepciones.size();i++){
			System.out.println(InicioIanus.tablaExcepciones.get(i).servicio);
			for(int j=0;j<InicioIanus.tablaExcepciones.get(i).excepciones.size();j++){
				System.out.println(InicioIanus.tablaExcepciones.get(i).excepciones.get(j).nombreDocumento + 
						" Tipo de excepción: " + InicioIanus.tablaExcepciones.get(i).excepciones.get(j).tipoExcepcion);
			}
		}
		*/
		
		for(int i=0;i<InicioIanus.tablaExcepciones.size();i++){
			if(servicio.equals(InicioIanus.tablaExcepciones.get(i).servicio)){
				for(int j=0;j<InicioIanus.tablaExcepciones.get(i).excepciones.size();j++){
					if(nombreDocumento.equals(InicioIanus.tablaExcepciones.get(i).excepciones.get(j).nombreDocumento)){
						tipoNodo = InicioIanus.tablaExcepciones.get(i).excepciones.get(j).tipoExcepcion;
					}
				}
			}
		}
		return tipoNodo;
	}
	
	private static String buscaNombreNodoServicio(String servicio){
		
		System.out.println("Empieza a buscar el nombre del servicio");
		
		for(int i=0;i< InicioIanus.listaNodos.length;i++){
			if(InicioIanus.listaNodos[i].alias.contains(servicio)){
				System.out.println("Encontró el nombre del servicio y es :" + InicioIanus.listaNodos[i].nombre);
				return InicioIanus.listaNodos[i].nombre;
			}
		}
		System.out.println("No encontró el nombre del servicio");
		return null;
	}
	
	
	private int buscaIndice(int indice){
		
		int nuevoIndice = -1;
		
		for(int i=indice+1;i<Inicio.documento.length;i++){
			if(!Inicio.documento[i].nhc.contains("Separador") &&
					!Inicio.documento[i].nhc.equals(Inicio.documento[indice].nhc)){
				nuevoIndice = i;
				break;
			}
		}
		
		return nuevoIndice;
	}
	
	
	public void gestion(){
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if(Inicio.ianus1onTop){
					
					System.out.println("El ianus1 está activo " + Inicio.ianus1onTop);
								
						System.out.println("Comparar... " + Inicio.documento[Inicio.indiceArchivoSelecc].nhc +
								"   " + Inicio.nhcDelIanus1);
						
						if(Inicio.documento[Inicio.indiceArchivoSelecc].nhc.equals(Inicio.documento[Inicio.indiceArchivoSelecc-1].nhc)){
							System.out.println("Imprime nhc 1. Es el mismo paciente: " + Inicio.nhcDelIanus1);
							
							if(Inicio.documento[Inicio.indiceArchivoSelecc].servicio.equals(Inicio.documento[Inicio.indiceArchivoSelecc-1].servicio)){
								buscaNodo(Inicio.paciente1.ianus,Inicio.documento[Inicio.indiceArchivoSelecc].servicio,
										Inicio.documento[Inicio.indiceArchivoSelecc].nombreNormalizado,false,false,retardoBuscarNodoFase2,false);
							
							}
							else{
								buscaNodo(Inicio.paciente1.ianus,Inicio.documento[Inicio.indiceArchivoSelecc].servicio,
										Inicio.documento[Inicio.indiceArchivoSelecc].nombreNormalizado,true,true,retardoBuscarNodoFase2,false);
							}
							
							if(InicioIanus.vAuto){
	
								// asociar(1400);
								GestionJacob.pulsaBotonAsociar();
							}
							
							/*
							try {
								Thread.sleep(retardoAsociar);
							//	Inicio.vExplorador.asociaDocumento(Inicio.documento[Inicio.indiceArchivoSelecc].nombreNormalizado);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							*/
						}
						else{
							
							System.out.println("No es el mismo paciente y me salgo de ianus1");
							
							GestionJacob.cambiaPaciente(Inicio.paciente1.ianus, nombreIanus1);
							
							Inicio.vControlIanus.panelControlesAux.setBackground(new Color(80,200,120));

							Inicio.vControlIanus.labelNumeroIanus.setText(nombreIanus2);
							Inicio.vControlIanus.botonNHC.setText(Inicio.nhcDelIanus2);
							Inicio.vControlIanus.botonServicio.setText(Inicio.documento[indiceNhc2].servicio);
							Inicio.vControlIanus.botonNombreDocumento.setText(Inicio.documento[indiceNhc2].nombreNormalizado);
							Inicio.vControlIanus.labelNombrePdf.setText(Inicio.documento[indiceNhc2].nombreArchivo);
							
							indiceNhc1 = buscaIndice(indiceNhc2);
							
							System.out.println("Indice 1: " + indiceNhc1);
							System.out.println("Indice 2: " + indiceNhc2);
							
							if(indiceNhc1 != -1){
								Inicio.nhcDelIanus1 = Inicio.documento[indiceNhc1].nhc;
								
								/*
								if(Inicio.esWin64){
									try {
										Thread.sleep(750);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								 */
								
								
								introduceNHC(Inicio.paciente1.ianus, nombreIanus1, Inicio.nhcDelIanus1,Retardos.retIntroNHC);
								// Inicio.ianus1.frame.setAlwaysOnTop(false);
								// Inicio.ianus2.frame.setAlwaysOnTop(true);
								
								System.out.println("Introdujimos el nhc del ianus 1: " + Inicio.nhcDelIanus1);

								
								Dispatch.put(Inicio.paciente1.ianus,"Visible",false);
								retardo(250);
								Dispatch.put(Inicio.paciente2.ianus,"Visible",true);
								Inicio.ianus1onTop = false;
								
								buscaNodo(Inicio.paciente1.ianus,Inicio.documento[indiceNhc1].servicio,
										Inicio.documento[indiceNhc1].nombreNormalizado, true,false,Retardos.retardoCargarPaciente,false);
								
							}
							else{
								// Inicio.ianus1.frame.setAlwaysOnTop(false);
								// Inicio.paciente2.ianus.frame.setAlwaysOnTop(true);
								// System.out.println("No hay más pacientes");
								
								System.out.println("No hay mas pacientes.");
								
								Dispatch.put(Inicio.paciente1.ianus,"Visible",false);
								retardo(250);
								Dispatch.put(Inicio.paciente2.ianus,"Visible",true);
								Inicio.ianus1onTop = false;
							
								
								
								
							}
						}
				}
				else{
						if(Inicio.documento[Inicio.indiceArchivoSelecc].nhc.equals(Inicio.documento[Inicio.indiceArchivoSelecc-1].nhc)){
							System.out.println("Imprime nhc 2. Es el mismo paciente: " + Inicio.nhcDelIanus2);
						
							if(Inicio.documento[Inicio.indiceArchivoSelecc].servicio.equals(Inicio.documento[Inicio.indiceArchivoSelecc-1].servicio)){
								buscaNodo(Inicio.paciente2.ianus,Inicio.documento[Inicio.indiceArchivoSelecc].servicio,
										Inicio.documento[Inicio.indiceArchivoSelecc].nombreNormalizado,false,false,retardoBuscarNodoFase2,false);

							}
							else{
								buscaNodo(Inicio.paciente2.ianus,Inicio.documento[Inicio.indiceArchivoSelecc].servicio,
										Inicio.documento[Inicio.indiceArchivoSelecc].nombreNormalizado, true,true,retardoBuscarNodoFase2,false);
							}
							
							if(InicioIanus.vAuto){
								
								// asociar(1400);
								GestionJacob.pulsaBotonAsociar();
							}
							
							// GestionJacob.pulsaBotonAsociar();
							
							/*
							try {
								Thread.sleep(retardoAsociar);
								//Inicio.vExplorador.asociaDocumento(Inicio.documento[Inicio.indiceArchivoSelecc].nombreNormalizado);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							*/
						//	Inicio.vExplorador.asociaDocumento(Inicio.documento[Inicio.indiceArchivoSelecc].nombreNormalizado);

						}
						else{
							
							//	Salir del paciente
							// boolean aux = GestionJacob.salirDelPaciente();
							
							GestionJacob.cambiaPaciente(Inicio.paciente2.ianus, nombreIanus2);
													
							Inicio.vControlIanus.panelControlesAux.setBackground(new Color(80,200,120));

							Inicio.vControlIanus.labelNumeroIanus.setText(nombreIanus1);
							Inicio.vControlIanus.botonNHC.setText(Inicio.nhcDelIanus1);
							Inicio.vControlIanus.botonServicio.setText(Inicio.documento[indiceNhc1].servicio);
							Inicio.vControlIanus.botonNombreDocumento.setText(Inicio.documento[indiceNhc1].nombreNormalizado);
							Inicio.vControlIanus.labelNombrePdf.setText(Inicio.documento[indiceNhc1].nombreArchivo);

							
							indiceNhc2 = buscaIndice(indiceNhc1);
							
							System.out.println("Indice 1: " + indiceNhc1);
							System.out.println("Indice 2: " + indiceNhc2);
							
							if(indiceNhc2 != -1){
								Inicio.nhcDelIanus2 = Inicio.documento[indiceNhc2].nhc;
								
								/*
								if(Inicio.esWin64){
									try {
										Thread.sleep(750);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								*/
								
								introduceNHC(Inicio.paciente2.ianus, nombreIanus2,Inicio.nhcDelIanus2,Retardos.retIntroNHC);
								// Inicio.ianus2.frame.setAlwaysOnTop(false);
								// Inicio.ianus1.frame.setAlwaysOnTop(true);
								
								System.out.println("Introdujimos el nhc del ianus 2: " + Inicio.nhcDelIanus2);
								
								Dispatch.put(Inicio.paciente2.ianus,"Visible",false);
								retardo(250);
								Dispatch.put(Inicio.paciente1.ianus,"Visible",true);
								Inicio.ianus1onTop = true;
								
								buscaNodo(Inicio.paciente2.ianus, Inicio.documento[indiceNhc2].servicio,
										Inicio.documento[indiceNhc2].nombreNormalizado, true,false,Retardos.retardoCargarPaciente,false);
								
							}
							else{
								// Inicio.ianus2.frame.setAlwaysOnTop(false);
								// Inicio.ianus1.frame.setAlwaysOnTop(true);
								
								System.out.println("No hay mas pacientes.");
								
								Dispatch.put(Inicio.paciente2.ianus,"Visible",false);
								retardo(250);
								Dispatch.put(Inicio.paciente1.ianus,"Visible",true);
								Inicio.ianus1onTop = true;
								
								
							}
						}
				}

			}
		});
		

	}
	
	public void reset(){
		
		
		indiceNhc1 = 0;
		indiceNhc2 = 0;
		
		indiceNhc1 = Inicio.vExplorador.listaPdfs.getSelectedIndex();
		
		for(int i=indiceNhc1;i<Inicio.documento.length;i++){
			if(!Inicio.documento[i].nhc.contains("Separador")){
				Inicio.nhcDelIanus1 = Inicio.documento[i].nhc;
				indiceNhc1 = i;
				break;
			}
		}
		
		for(int i=indiceNhc1 + 1;i<Inicio.documento.length;i++){
			if(!Inicio.documento[i].nhc.equals(Inicio.nhcDelIanus1) &&
					!Inicio.documento[i].nhc.contains("Separador")){
				Inicio.nhcDelIanus2 = Inicio.documento[i].nhc;
				indiceNhc2 = i;
				break;
			}			
		}
		
		Inicio.indiceArchivoSelecc = indiceNhc1;
		Inicio.vExplorador.listaPdfs.setSelectedIndex(Inicio.indiceArchivoSelecc);
		Inicio.documentoActivo = Inicio.documento[Inicio.indiceArchivoSelecc];

		
		Inicio.vControlIanus.botonNHC.setText(Inicio.documento[indiceNhc1].nhc);
		Inicio.vControlIanus.botonServicio.setText(Inicio.documento[indiceNhc1].servicio);
		Inicio.vControlIanus.botonNombreDocumento.setText(Inicio.documento[indiceNhc1].nombreNormalizado);
		Inicio.vControlIanus.labelNumeroIanus.setText(nombreIanus1);
		
		Inicio.vControlIanus.panelBotones.setBackground(new Color(80,200,120));
		
		
		Inicio.vNombres.comboServicio.setSelectedItem(Inicio.documentoActivo.servicio);
		System.out.println("Introduce el nhc 1 " + Inicio.nhcDelIanus1);
//		introduceNHC(Inicio.paciente1.ianus,Inicio.nhcDelIanus1,Inicio.documento[indiceNhc1].servicio,50,false);
		introduceNHC(Inicio.paciente1.ianus,nombreIanus1,Inicio.nhcDelIanus1,100);
		
		/*
	//	Inicio.ianus2.botonNHC.setText(Inicio.documento[indiceNhc2].nhc);
	//	Inicio.ianus2.botonServicio.setText(Inicio.documento[indiceNhc2].servicio);
	//	Inicio.ianus2.botonNombreDocumento.setText(Inicio.documento[indiceNhc2].nombreNormalizado);
	 * */
	
		System.out.println("Introduce el nhc 2 " + Inicio.nhcDelIanus2);
		
		Dispatch.put(Inicio.paciente2.ianus,"visible",false);
		introduceNHC(Inicio.paciente2.ianus,nombreIanus2,Inicio.nhcDelIanus2,100);
	//	introduceNHC(Inicio.paciente2.ianus,Inicio.nhcDelIanus2,Inicio.documento[indiceNhc2].servicio,2000,false);
	//	Dispatch.put(Inicio.ianus2,"visible",false);
		
		Inicio.ianus1onTop = true;
		
		buscaNodo(Inicio.paciente1.ianus,Inicio.documento[indiceNhc1].servicio,Inicio.documento[indiceNhc1].nombreNormalizado,true,false,Retardos.retardoCargarPaciente,false);
		buscaNodo(Inicio.paciente2.ianus,Inicio.documento[indiceNhc2].servicio,Inicio.documento[indiceNhc2].nombreNormalizado,true,false,Retardos.retardoCargarPaciente,false);
	//	Inicio.ianus1.frame.setAlwaysOnTop(true);

		

		
		
	}
	
	
	private void retardo(int retardo){
		try {
			Thread.sleep(retardo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static public void asociar(int retardo){
		GestionJacob.pulsaBotonAsociar();

		retardo = 1400;
		
			try {
				Thread.sleep(retardo);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		
		Portapapeles cbTemporal = new Portapapeles();
		String tipoSubida = cbTemporal.getTipoDeSubida();
		InicioIanus.tipoSubida = tipoSubida;
		
		System.out.println("El tipo de subida es..... " + tipoSubida);
	}
	
}
