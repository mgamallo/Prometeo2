package es.mgamallo.prometeo;

import java.awt.Color;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class Gestion2Ianus {

	protected static final String LS = System.getProperty("line.separator");
	
	int indiceNhc1 = 0;
	int indiceNhc2 = 0;
	
	boolean ianus1Activo = true;
	
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
		introduceNHC(Inicio.ianus1,Inicio.nhcDelIanus1,50);
	
		/*
	//	Inicio.ianus2.botonNHC.setText(Inicio.documento[indiceNhc2].nhc);
	//	Inicio.ianus2.botonServicio.setText(Inicio.documento[indiceNhc2].servicio);
	//	Inicio.ianus2.botonNombreDocumento.setText(Inicio.documento[indiceNhc2].nombreNormalizado);
	 * */

		
		System.out.println("Introduce el nhc 2 " + Inicio.nhcDelIanus2);
		
		introduceNHC(Inicio.ianus2,Inicio.nhcDelIanus2,4000);
		Dispatch.put(Inicio.ianus2,"visible",false);
		
	//	Inicio.ianus1.frame.setAlwaysOnTop(true);
	}
	
	private void introduceNHC(ActiveXComponent ianus, String nhc, int retardo){
				
		// Dispatch.call(ianus, "Navigate","javascript:" + CadenasJavascript.introducirNHC(nhc));
		
		
		Hilo introNHC = new Hilo(ianus ,CadenasJavascript.introducirNHC(nhc),retardo);
	    introNHC.start();
	    
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
		if(getIanus1onTop()){
			
			System.out.println("El ianus1 está activo " + Inicio.ianus1onTop);
						
				System.out.println("Comparar... " + Inicio.documento[Inicio.indiceArchivoSelecc].nhc +
						"   " + Inicio.nhcDelIanus1);
				
				if(Inicio.documento[Inicio.indiceArchivoSelecc].nhc.equals(Inicio.documento[Inicio.indiceArchivoSelecc-1].nhc)){
					System.out.println("Imprime nhc 1. Es el mismo paciente: " + Inicio.nhcDelIanus1);
					
					
					Inicio.vExplorador.asociaDocumento();
					CapturaRatonYTeclado.barraEspaciadoraOn = true;
				}
				else{
					
					System.out.println("No es el mismo paciente y me salgo");
					
					salirDelPaciente(Inicio.ianus1);
					Inicio.vControlIanus.panelControlesAux.setBackground(new Color(80,200,120));
					
					CapturaRatonYTeclado.barraEspaciadoraOn = true;
					
					indiceNhc1 = buscaIndice(indiceNhc2);
					if(indiceNhc1 != -1){
						Inicio.nhcDelIanus1 = Inicio.documento[indiceNhc1].nhc;
						
						introduceNHC(Inicio.ianus1, Inicio.nhcDelIanus1, 1000);
						// Inicio.ianus1.frame.setAlwaysOnTop(false);
						// Inicio.ianus2.frame.setAlwaysOnTop(true);
						
						Dispatch.put(Inicio.ianus1,"Visible",false);
						retardo(250);
						Dispatch.put(Inicio.ianus2,"Visible",true);
						ianus1Activo = false;
					}
					else{
						// Inicio.ianus1.frame.setAlwaysOnTop(false);
						// Inicio.ianus2.frame.setAlwaysOnTop(true);
						// System.out.println("No hay más pacientes");
						
						Dispatch.put(Inicio.ianus1,"Visible",false);
						retardo(250);
						Dispatch.put(Inicio.ianus2,"Visible",true);
						ianus1Activo= false;
						
					}
					
					
				}
		}
		else{
				if(Inicio.documento[Inicio.indiceArchivoSelecc].nhc.equals(Inicio.documento[Inicio.indiceArchivoSelecc-1].nhc)){
					System.out.println("Imprime nhc 2. Es el mismo paciente: " + Inicio.nhcDelIanus2);
					
					Inicio.vExplorador.asociaDocumento();
					CapturaRatonYTeclado.barraEspaciadoraOn = true;
				}
				else{
					salirDelPaciente(Inicio.ianus2);
					Inicio.vControlIanus.panelControlesAux.setBackground(new Color(80,200,120));
					CapturaRatonYTeclado.barraEspaciadoraOn = true;
					
					indiceNhc2 = buscaIndice(indiceNhc1);
					if(indiceNhc2 != -1){
						Inicio.nhcDelIanus2 = Inicio.documento[indiceNhc2].nhc;
						
						introduceNHC(Inicio.ianus2, Inicio.nhcDelIanus2, 1000);
						// Inicio.ianus2.frame.setAlwaysOnTop(false);
						// Inicio.ianus1.frame.setAlwaysOnTop(true);
						
						Dispatch.put(Inicio.ianus2,"Visible",false);
						retardo(250);
						Dispatch.put(Inicio.ianus1,"Visible",true);
						ianus1Activo = true;
						
						
					}
					else{
						// Inicio.ianus2.frame.setAlwaysOnTop(false);
						// Inicio.ianus1.frame.setAlwaysOnTop(true);
						
						Dispatch.put(Inicio.ianus2,"Visible",false);
						retardo(250);
						Dispatch.put(Inicio.ianus1,"Visible",true);
						ianus1Activo = true;
					}
				}
		}
	}
	
	private boolean getIanus1onTop(){
		// return Inicio.ianus1.frame.isAlwaysOnTop();
		
		// return Dispatch.get(Inicio.ianus1,"visible").getBoolean();
		return ianus1Activo;
	}
	
	public void salirDelPaciente(ActiveXComponent ianus){
		final String salirPaciente = CadenasJavascript.salirDelPaciente();
			
		
		// Dispatch.call(ianus, "Navigate","javascript:" + salirPaciente);
		Dispatch.call(ianus, "Navigate","javascript:window.frames.principal.botonera.inicio()");
		// ianus.webBrowser.executeJavascript(salirPaciente);
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
