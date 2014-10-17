package es.mgamallo.prometeo;

import java.awt.Color;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class Gestion2Ianus {

	protected static final String LS = System.getProperty("line.separator");
	
	int indiceNhc1 = 0;
	int indiceNhc2 = 0;
	
	
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
		
		Inicio.panelPrincipal.webBrowserOperaciones
		.navigate(Inicio.documento[Inicio.indiceArchivoSelecc].rutaArchivo);
		
		Inicio.vControlIanus.botonNHC.setText(Inicio.documento[indiceNhc1].nhc);
		Inicio.vControlIanus.botonServicio.setText(Inicio.documento[indiceNhc1].servicio);
		Inicio.vControlIanus.botonNombreDocumento.setText(Inicio.documento[indiceNhc1].nombreNormalizado);
		Inicio.vControlIanus.labelNumeroIanus.setText("Ianus 1");
		
//**** Aquí deberíamos gestionar los colores verde, rojo...
		Inicio.vControlIanus.panelBotones.setBackground(new Color(80,200,120));
		
		
		Inicio.vNombres.comboServicio.setSelectedItem(Inicio.documentoActivo.servicio);
		System.out.println("Introduce el nhc 1 " + Inicio.nhcDelIanus1);
		introduceNHC(Inicio.ianus1,Inicio.nhcDelIanus1,"OFTAL",50,false);
	
		/*
	//	Inicio.ianus2.botonNHC.setText(Inicio.documento[indiceNhc2].nhc);
	//	Inicio.ianus2.botonServicio.setText(Inicio.documento[indiceNhc2].servicio);
	//	Inicio.ianus2.botonNombreDocumento.setText(Inicio.documento[indiceNhc2].nombreNormalizado);
	 * */

		
		System.out.println("Introduce el nhc 2 " + Inicio.nhcDelIanus2);
		
		Dispatch.put(Inicio.ianus2,"visible",false);
		introduceNHC(Inicio.ianus2,Inicio.nhcDelIanus2,"OFTAL",2000,false);
	//	Dispatch.put(Inicio.ianus2,"visible",false);
		
		Inicio.ianus1onTop = true;
		
	//	Inicio.ianus1.frame.setAlwaysOnTop(true);
	}
	
	
	private void introduceNHC(ActiveXComponent ianus, String nhc, String servicio, int retardo,boolean primerDocumento){
		
		// Dispatch.call(ianus, "Navigate","javascript:" + CadenasJavascript.introducirNHC(nhc));
		
		// GestionJacob.introduceNHC(ianus, nhc);
		
		String nombreNodoServicio = buscaNombreNodoServicio(servicio);
		
		
		HiloNHCyNodo introNHC = new HiloNHCyNodo(ianus ,CadenasJavascript.introducirNHC(nhc),nombreNodoServicio,retardo,primerDocumento);
	    introNHC.start();
	    
	}
	
	
	private String buscaNombreNodoServicio(String servicio){
		for(int i=0;i< InicioIanus.listaNodos.length;i++){
			if(InicioIanus.listaNodos[i].alias.contains(servicio)){
				return InicioIanus.listaNodos[i].nombre;
			}
		}
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
							
							GestionJacob.pulsaBotonAsociar();
							// Inicio.vExplorador.asociaDocumento();

							try {
								Thread.sleep(1000);
								Inicio.vExplorador.asociaDocumento(Inicio.documento[Inicio.indiceArchivoSelecc].nombreNormalizado);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else{
							
							System.out.println("No es el mismo paciente y me salgo de ianus1");
							
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
								Dispatch.call(Inicio.ianus1, "navigate","javascript:window.frames.principal.botonera.inicio()");
								Inicio.ianus1onTop = false;
								}																	
								
							});
							Inicio.vControlIanus.panelControlesAux.setBackground(new Color(80,200,120));

							Inicio.vControlIanus.labelNumeroIanus.setText("Ianus 2");
							Inicio.vControlIanus.botonNHC.setText(Inicio.nhcDelIanus2);
							Inicio.vControlIanus.botonServicio.setText(Inicio.documento[indiceNhc2].servicio);
							Inicio.vControlIanus.botonNombreDocumento.setText(Inicio.documento[indiceNhc2].nombreNormalizado);
					
							
							indiceNhc1 = buscaIndice(indiceNhc2);
							
							System.out.println("Indice 1: " + indiceNhc1);
							System.out.println("Indice 2: " + indiceNhc2);
							
							if(indiceNhc1 != -1){
								Inicio.nhcDelIanus1 = Inicio.documento[indiceNhc1].nhc;
								
								introduceNHC(Inicio.ianus1, Inicio.nhcDelIanus1, "OFTAL",1000,false);
								// Inicio.ianus1.frame.setAlwaysOnTop(false);
								// Inicio.ianus2.frame.setAlwaysOnTop(true);
								
								System.out.println("Introdujimos el nhc del ianus 1: " + Inicio.nhcDelIanus1);

								
								Dispatch.put(Inicio.ianus1,"Visible",false);
								retardo(250);
								Dispatch.put(Inicio.ianus2,"Visible",true);
								Inicio.ianus1onTop = false;

							}
							else{
								// Inicio.ianus1.frame.setAlwaysOnTop(false);
								// Inicio.ianus2.frame.setAlwaysOnTop(true);
								// System.out.println("No hay más pacientes");
								
								System.out.println("No hay mas pacientes.");
								
								Dispatch.put(Inicio.ianus1,"Visible",true);
								retardo(250);
								Dispatch.put(Inicio.ianus2,"Visible",true);
								Inicio.ianus1onTop = true;
								
							}
						}
				}
				else{
						if(Inicio.documento[Inicio.indiceArchivoSelecc].nhc.equals(Inicio.documento[Inicio.indiceArchivoSelecc-1].nhc)){
							System.out.println("Imprime nhc 2. Es el mismo paciente: " + Inicio.nhcDelIanus2);
							
							GestionJacob.pulsaBotonAsociar();
							
							try {
								Thread.sleep(1000);
								Inicio.vExplorador.asociaDocumento(Inicio.documento[Inicio.indiceArchivoSelecc].nombreNormalizado);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						//	Inicio.vExplorador.asociaDocumento(Inicio.documento[Inicio.indiceArchivoSelecc].nombreNormalizado);

						}
						else{
							
							//	Salir del paciente
							// boolean aux = GestionJacob.salirDelPaciente();
							Dispatch.call(Inicio.ianus2, "navigate","javascript:window.frames.principal.botonera.inicio()");
							Inicio.ianus1onTop = true;
							
							Inicio.vControlIanus.panelControlesAux.setBackground(new Color(80,200,120));

							Inicio.vControlIanus.labelNumeroIanus.setText("Ianus 1");
							Inicio.vControlIanus.botonNHC.setText(Inicio.nhcDelIanus1);
							Inicio.vControlIanus.botonServicio.setText(Inicio.documento[indiceNhc1].servicio);
							Inicio.vControlIanus.botonNombreDocumento.setText(Inicio.documento[indiceNhc1].nombreNormalizado);
	
							
							indiceNhc2 = buscaIndice(indiceNhc1);
							
							System.out.println("Indice 1: " + indiceNhc1);
							System.out.println("Indice 2: " + indiceNhc2);
							
							if(indiceNhc2 != -1){
								Inicio.nhcDelIanus2 = Inicio.documento[indiceNhc2].nhc;
								
								introduceNHC(Inicio.ianus2, Inicio.nhcDelIanus2,"OFTAL", 1000,false);
								// Inicio.ianus2.frame.setAlwaysOnTop(false);
								// Inicio.ianus1.frame.setAlwaysOnTop(true);
								
								System.out.println("Introdujimos el nhc del ianus 2: " + Inicio.nhcDelIanus2);
								
								Dispatch.put(Inicio.ianus2,"Visible",false);
								retardo(250);
								Dispatch.put(Inicio.ianus1,"Visible",true);
								Inicio.ianus1onTop = true;
								
							}
							else{
								// Inicio.ianus2.frame.setAlwaysOnTop(false);
								// Inicio.ianus1.frame.setAlwaysOnTop(true);
								
								System.out.println("No hay mas pacientes.");
								
								Dispatch.put(Inicio.ianus2,"Visible",true);
								retardo(250);
								Dispatch.put(Inicio.ianus1,"Visible",true);
								Inicio.ianus1onTop = true;
							}
						}
				}

			}
		});
		

	}
	

	
	
	private void retardo(int retardo){
		try {
			Thread.sleep(retardo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
