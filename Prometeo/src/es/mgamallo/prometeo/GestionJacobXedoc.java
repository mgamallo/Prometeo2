package es.mgamallo.prometeo;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Calendar;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class GestionJacobXedoc {
	
	
	public static ActiveXComponent ianusApoyoXedoc;
	public static ActiveXComponent bandejaXedoc1;
	public static ActiveXComponent xedoc2;
	
	private static final String COLOR_BANDEJA_XEDOC1 = "#A1BEE6";
	private static final String COLOR_BANDEJA_XEDOC2 = "#FF8080";
	
	public static int RANGO_DIAS_CONSULTA = 100;
	
	private static boolean xedocImpar = true;
	
	public static boolean xedoc1visible;
	
	public static void inicializa2Xedocs(){
		
		
		capturaWebsXedoc();
		

		
		formateaWebsXedoc(bandejaXedoc1);
		formateaWebsXedoc(xedoc2);
		formateaIanus(ianusApoyoXedoc);
		Dispatch.put(ianusApoyoXedoc, "visible","false");

		
		
		inicializaBandeja(bandejaXedoc1, "Bandeja Xedoc 1");
		inicializaBandeja(xedoc2, "Bandeja Xedoc 2");
		
		Inicio.panelPrincipal.frame.setVisible(false);
		
		Inicio.ventanaControlXedoc = new VentanaControlXedoc(bandejaXedoc1, xedoc2);
		Inicio.ventanaControlXedoc.setVisible(true);
		
		Inicio.panelPrincipal.frame.setExtendedState(Frame.ICONIFIED);	
		
	}
	
	
	public static ActiveXComponent capturaUltimoExplorer(){
        int iCount = InicioXedoc.oWindows.getProperty("Count").getInt();
        System.out.println("iCount: " + iCount);  
        
        ActiveXComponent oWindow = InicioXedoc.oWindows.invokeGetComponent("Item", new Variant(iCount-1));     
        String sLocName = oWindow.getProperty("LocationName").getString();
        String sFullName = oWindow.getProperty("FullName").getString();
        boolean isIE = sFullName.toLowerCase().endsWith("iexplore.exe");
        boolean bVisible = oWindow.getProperty("Visible").getBoolean();
        System.out.println("i: " + (iCount-1) + ", loc: " + sLocName + ", name: " + sFullName + ", isIE: " + isIE + ", vis: " + bVisible);

        Inicio.documento1.xedoc = oWindow;
        
        Dispatch doc = Dispatch.call(Inicio.documento1.xedoc, "document").getDispatch();
        Dispatch.put(doc,"title","Xedoc 1");

        Inicio.ventanaControlXedoc.jBxedoc1.setBackground(Color.green);
        Inicio.ventanaControlXedoc.jBxedoc2.setBackground(Color.green);
        
        return oWindow;
	}
	
	
	public static void capturaUltimos2Explorer(){
        
        int iCount = InicioXedoc.oWindows.getProperty("Count").getInt();
        System.out.println("iCount: " + iCount);  
        

		
        for (int i=iCount-1,j= 1; i >iCount-3 ; i--,j++) {
            ActiveXComponent oWindow = InicioXedoc.oWindows.invokeGetComponent("Item", new Variant(i));     
            String sLocName = oWindow.getProperty("LocationName").getString();
            String sFullName = oWindow.getProperty("FullName").getString();
            boolean isIE = sFullName.toLowerCase().endsWith("iexplore.exe");
            boolean bVisible = oWindow.getProperty("Visible").getBoolean();
            System.out.println("i: " + i + ", loc: " + sLocName + ", name: " + sFullName + ", isIE: " + isIE + ", vis: " + bVisible);
            
            if(j==2){
            	Inicio.documento1.xedoc = oWindow;
                Dispatch doc1 = Dispatch.call(Inicio.documento1.xedoc, "document").getDispatch();
                Dispatch.put(doc1,"title","Xedoc 1");
             }
            if(j==1){
            	// Inicio.paciente2.xedoc = oWindow;
            	Inicio.documento2.xedoc = oWindow;
                Dispatch doc2 = Dispatch.call(Inicio.documento2.xedoc, "document").getDispatch();
                Dispatch.put(doc2,"title","Xedoc 2");
            }	
            


        }
         
	}
	
	

	
	
	public static void capturaWebsXedoc(){
		
		for(int i=0;i<2;i++)
			Cerrar.cerrarIexplorer();;
		
		xedocImpar = true;
		
	    InicioXedoc.oShell = new ActiveXComponent("Shell.Application"); 
	    InicioXedoc.oWindows = InicioXedoc.oShell.invokeGetComponent("Windows");
	    
        try {
			Runtime.getRuntime().exec("C:/Archivos de programa/Internet Explorer/iexplore.exe");
			Thread.sleep(1000);
			
			Runtime.getRuntime().exec("C:/Archivos de programa/Internet Explorer/iexplore.exe");
			Thread.sleep(1000);
			
			Runtime.getRuntime().exec("C:/Archivos de programa/Internet Explorer/iexplore.exe");
			Thread.sleep(1000);
			
        } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        int iCount = InicioXedoc.oWindows.getProperty("Count").getInt();
        System.out.println("iCount: " + iCount);        
		
        for (int i=iCount-1,j= 1; i >iCount-4 ; i--,j++) {
            ActiveXComponent oWindow = InicioXedoc.oWindows.invokeGetComponent("Item", new Variant(i));     
            String sLocName = oWindow.getProperty("LocationName").getString();
            String sFullName = oWindow.getProperty("FullName").getString();
            boolean isIE = sFullName.toLowerCase().endsWith("iexplore.exe");
            boolean bVisible = oWindow.getProperty("Visible").getBoolean();
            System.out.println("i: " + i + ", loc: " + sLocName + ", name: " + sFullName + ", isIE: " + isIE + ", vis: " + bVisible);
            
            /*
            if ((isIE)&&(sLocName.startsWith("about:blank"))) {
                oIE = oWindow;
            }
            */
            
            if(j==1){
            	bandejaXedoc1 = oWindow;
        		Dispatch.call(bandejaXedoc1, "Navigate","http://xedocidx.sergas.local/xedoc_idx/login");
            }
            if(j==2){
            	// Inicio.paciente2.xedoc = oWindow;
            	xedoc2 = oWindow;
            	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		Dispatch.call(xedoc2, "Navigate","http://xedocidx.sergas.local/xedoc_idx/login");
            }
            if(j==3){
            	ianusApoyoXedoc = oWindow;
            	Dispatch.call(ianusApoyoXedoc, "Navigate","http://ianuschop.sergas.local/ianus_chp_pro/inicio.jsp");

            }
        }
	}
	
	public static void formateaWebsXedoc(ActiveXComponent bandejaXedoc){
		
		int ancho = 0;
		int alto = 0;
		int izquierda = 0;
		int arriba = 0;
		if(Inicio.numeroPantallas == 1){
			
			ancho = 1919 / 2;
			alto = 1049;
			arriba = 0;
			if(xedocImpar){
				izquierda = 0;
				xedocImpar = false;
			}
			else{
				izquierda = ancho +1;
			}
			
			/*
			ancho = 1070;
			alto = 1049;
			arriba = 0;
			izquierda = 850;
			*/
		}
		else{
			ancho = 1023;
			alto = 1279;
			arriba = 0;
			izquierda = 1025;
		}
		
	    Dispatch.put(bandejaXedoc,"height",alto);
	    Dispatch.put(bandejaXedoc,"width",ancho);
	    Dispatch.put(bandejaXedoc,"top",arriba);  
	    Dispatch.put(bandejaXedoc,"left",izquierda);
		
		
		
		if(Inicio.contraseña){
			Dispatch.call(bandejaXedoc, "Navigate","javascript:" + CadenasJavascriptXedoc.introUsuario0(Inicio.usuario));
		
			Dispatch.put(bandejaXedoc,"menubar",false);
			Dispatch.put(bandejaXedoc,"toolbar",false);
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Dispatch.call(bandejaXedoc, "Navigate","javascript:" + CadenasJavascriptXedoc.introUsuario1(Inicio.usuario));

			
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	 
		/*
		
		if(Inicio.numeroPantallas == 1){
			ancho = 1919;
			alto = 1049;
			arriba = 0;
			izquierda = 0;
		}
		else{
			ancho = 2047;
			alto = 1251;
			arriba = 0;
			izquierda = 0;
		}
		
		*/
		
	    Dispatch.put(bandejaXedoc,"height",alto);
	    Dispatch.put(bandejaXedoc,"width",ancho);
	    Dispatch.put(bandejaXedoc,"top",arriba);  
	    Dispatch.put(bandejaXedoc,"left",izquierda);
		
		
		}
		
		Variant estado = Dispatch.call(bandejaXedoc,"readyState");
		
		while(true ){
			estado = Dispatch.call(bandejaXedoc, "readyState");
			if(Integer.valueOf(estado.toString()) == 4){
				System.out.println("El estado está en 4");
				break;
			}
		}
	    
		Dispatch.call(bandejaXedoc, "Navigate","javascript:" + CadenasJavascriptXedoc.selectMiBandeja());

		System.out.println("Empieza el tercer readyState");
		
		while(true){
			estado = Dispatch.call(bandejaXedoc, "readyState");
			if(Integer.valueOf(estado.toString()) == 4){
				System.out.println("El estado está en 4");
				break;
			}
		}
		
		
	}
	
	public static void formateaIanus(ActiveXComponent ianus){
	if(Inicio.contraseña){
			
			// introduceUsuarioJacob(Inicio.ianus1, Inicio.usuario);
			
			Dispatch.call(ianusApoyoXedoc,"Navigate","javascript:" + CadenasJavascript.introducirUsuario(Inicio.usuario));
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Dispatch.put(ianusApoyoXedoc,"Visible",true);
			Dispatch.put(ianusApoyoXedoc,"menubar",false);
			Dispatch.put(ianusApoyoXedoc,"toolbar",false);
			
			
		    Dispatch.put(ianusApoyoXedoc,"height",1149);  // 1099
		    Dispatch.put(ianusApoyoXedoc,"top",0);  // 180
		    Dispatch.put(ianusApoyoXedoc,"left",1024);
		    if(Inicio.numeroPantallas == 1){
		    	Dispatch.put(ianusApoyoXedoc,"height",1052);  // 1099
		    	Dispatch.put(ianusApoyoXedoc,"width",895);
		    }
		
			
			
//		    introduceUsuarioJacob(Inicio.ianus2, Inicio.usuario);

		    
		}
	}
	
	public static void inicializaBandeja(ActiveXComponent bandejaXedoc,String nombreBandeja){

		Dispatch documento = Dispatch.call(bandejaXedoc,"document").getDispatch();
		
		
		Dispatch entornoLogin = Dispatch.call(documento,"getElementById","entornoLogin").getDispatch();
		Dispatch.put(entornoLogin,"innerHTML",nombreBandeja);
		Dispatch estiloEntornoLogin = Dispatch.get(entornoLogin,"style").getDispatch();
		
		Dispatch.put(estiloEntornoLogin,"font","bold 38px arial, sans-serif");
		Dispatch.put(estiloEntornoLogin, "color","yellow");
		Dispatch.put(estiloEntornoLogin, "marginRight","400px");
		
		Dispatch header = Dispatch.call(documento, "getElementById","header").getDispatch();
		Dispatch estiloHeader = Dispatch.get(header, "style").getDispatch();
		
		if(nombreBandeja.equals("Bandeja Xedoc 1")){
			Dispatch.put(estiloHeader, "background","black");	
			Dispatch.put(documento,"title","Bandeja 1");
		}
		else{
			Dispatch.put(estiloHeader, "background","black");	
			Dispatch.put(estiloEntornoLogin, "color","red");
			Dispatch.put(documento,"title","Bandeja 2");
		}
	}
	
	private static String getCadenaComun(int filaInicial, String fechaInicio, String fechaFin){
		String cadenaComun = ""
				+ ""
				+ "var nhc;"
				+ "var filaSeleccionada;"
				+ "var filaInicial = " + filaInicial + ";"
				+ ""
				+ "function getNHC(nodo){"
					+ "var cadena = nodo.innerHTML;"
					+ "var campos = cadena.split(' @');"
					+ "campos[3] = campos[3].replace('r_f.pdf','');"
					+ "nhc = campos[1];"
					+ "filaSeleccionada = nodo.id;"
					+ "alert(filaSeleccionada);"
					+ "cargaContexto();"
				+ "}"
				
				+ "function cargaContexto(){"
					+ "document.getElementById('contextoMenuSuperior').click();"
					+ "var nhcElement = document.getElementById('{hc}numeroHC');"
					+ "nhcElement.value= nhc;"
					+ "var fechaI = document.getElementById('FechaIni');"
					+ "var fechaF = document.getElementById('FechaFin');"
					+ "fechaI.value = '" + fechaInicio + "';"
					+ "fechaF.value = '" + fechaFin + "';"
					+ "alert('En medio de la carga de contexto');"
					+ "document.getElementById('submitFormContexto').click();"
					+ "var claveEntera = nhc + '-360340';"
					+ "setTimeout(function(){cambiarContexto(claveEntera);"
						+ "var anclaSeleccionada = celdas[filaSeleccionada*5 +4];"
						+ "anclaSeleccionada = anclaSeleccionada.getElementsByTagName('a');"
						+ "anclaSeleccionada[0].click();},4000);"
					+ ""
					+ ""
					+ ""
				//	+ "var anclaSeleccionada = celdaSeleccionada.getElementsByTagName('a');"
				//	+ "anclaSeleccionada[0].click();"
					+ ""
				+ "}"
					
				+ "function saludar(numrFila){alert('Fila numero ' + numrFila);}"
				+ ""

				+ ""
				+ "var tabla = document.getElementById('row');"
				+ "var celdas = tabla.getElementsByTagName('td');"
				+ "var filas = celdas.length / 5;"
				+ "";
		
		return cadenaComun;
	}
	
	
	public static void capturaBandeja(ActiveXComponent bandejaXedoc1, ActiveXComponent xedoc2, String nombreXedoc1, String nombreXedoc2, int filaInicial1, int finlaInicial2){
		
		String fechaInicio = "";
		String fechaFin = "";

		int diaHoy = 1;
		int mesHoy = 1;
		int añoHoy = 1;
		
		int diaHaceUnMes = 1;
		int mesHaceUnMes = 1;
		int añoHaceUnMes = 1;
		
		Calendar calendario = Calendar.getInstance();
		diaHoy = calendario.get(Calendar.DAY_OF_MONTH);
		mesHoy = calendario.get(Calendar.MONTH) + 1;
		añoHoy = calendario.get(Calendar.YEAR);
		
		fechaFin = diaHoy + "/" + mesHoy + "/" + añoHoy;

		calendario.add(Calendar.DAY_OF_MONTH,- RANGO_DIAS_CONSULTA);
		
		diaHaceUnMes = calendario.get(Calendar.DAY_OF_MONTH);
		mesHaceUnMes = calendario.get(Calendar.MONTH) + 1;
		añoHaceUnMes = calendario.get(Calendar.YEAR);
		
		fechaInicio = diaHaceUnMes + "/" + mesHaceUnMes + "/" + añoHaceUnMes;
		
		String cadenaComun1 = getCadenaComun(filaInicial1, fechaInicio, fechaFin);
		String cadenaComun2 = getCadenaComun(finlaInicial2, fechaInicio, fechaFin);
		

		
		String cadena_blank = ""		
				+ "var numFila = 1;"
				+ "var sal = 'holaaa';"
				+ "for(var i=0;i<filas;i++){"
					+ "celdas[i*5 + 2].setAttribute('id',i);"
					+ "celdas[i*5 + 2].setAttribute('onclick','getNHC(this);');"  
			//		+ "celdas[i*5 + 2].setAttribute('onclick','javascript:alert(sal);try{getNHC(this)}catch(e){alert(e)}');"  
			
					+ "var anclas = celdas[i*5 + 4].getElementsByTagName('a');"
					+ "anclas[0].target = '_blank';"
					+ "numFila++;"
				+ "}"
				+ "celdas[filaInicial*5 + 2].click();"
			//	+ "alert(filas);"
				+ "";
		
		String cadena_sin_blank = ""
				+ "var numFila = 1;"
				+ "for(var i=0;i<filas;i++){"
					+ "celdas[i*5 + 2].setAttribute('id',i);"
					+ "celdas[i*5 + 2].setAttribute('onclick','getNHC(this);');"
					+ "var anclas = celdas[i*5 + 4].getElementsByTagName('a');"
					+ "numFila++;"
				+ "}"
				+ "celdas[filaInicial*5 + 2].click();"
			//	+ "alert(filas);"
				+ "";
				
		
		String cadena1 = cadenaComun1 + cadena_blank;
		String cadena2 = cadenaComun2 + cadena_sin_blank;
		
		System.out.println("Cadena 1 " + cadena1);
		
		/*
		if(nombreXedoc.contains("Xedoc 1")){
			cadena += cadena_blank;
		}
		else if(nombreXedoc.contains("Xedoc 2")){
			cadena += cadena_sin_blank;
		}
	
		
		Dispatch.call(bandejaXedoc, "Navigate","javascript:" +  cadena );
		*/
		
		Dispatch.call(bandejaXedoc1, "Navigate","javascript:" +  cadena1 );
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Dispatch.call(xedoc2, "Navigate","javascript:" +  cadena2 );
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		capturaUltimoExplorer();
		// capturaUltimos2Explorer();
		
		
		if(nombreXedoc2.contains("Xedoc 2")){
			Inicio.documento2.xedoc = xedoc2;
	        Dispatch doc = Dispatch.call(Inicio.documento2.xedoc, "document").getDispatch();
	        Dispatch.put(doc,"title","Xedoc 2");
		}
		
		

		new MaquetadoXedoc(Inicio.documento1.xedoc, "Xedoc 1");
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		new MaquetadoXedoc(Inicio.documento2.xedoc, "Xedoc 2");
		Dispatch.put(Inicio.documento2.xedoc,"visible","false");
		
		xedoc1visible = true;
		Dispatch.put(bandejaXedoc1,"visible","false");
		Inicio.ventanaControlXedoc.jBbandeja1.setBackground(Color.DARK_GRAY);
		Inicio.ventanaControlXedoc.panelMover.setBackground(Color.green);
		
		
	}
	
	
	public static void maquetado2Xedoc(){
		
		 MaquetadoXedoc maquetado1 = new MaquetadoXedoc(Inicio.documento1.xedoc, "Xedoc 1");
		 
		 try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 		
		 MaquetadoXedoc maquetado2 = new MaquetadoXedoc(Inicio.documento2.xedoc, "Xedoc 2");
	}
	
	public static void captura2XedocIndividuales(){
		
		
		try {
		
			 capturaUltimos2Explorer();
			
			 MaquetadoXedoc maquetado1 = new MaquetadoXedoc(Inicio.documento1.xedoc, "Xedoc 1");
			 
			 Thread.sleep(200);
			 		
			 MaquetadoXedoc maquetado2 = new MaquetadoXedoc(Inicio.documento2.xedoc, "Xedoc 2");
		
			 
			 xedoc1visible = true;
			 
		}catch(InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public static void aceptar(){
		
		ActiveXComponent xedocActivo;
		
		if(xedoc1visible){
			xedocActivo = Inicio.documento1.xedoc;
		}
		else{
			xedocActivo = Inicio.documento2.xedoc;
		}
		
		System.out.println("clickeando");
		
		
		HiloXedoc hilo = new HiloXedoc(2000);
		hilo.start();
		
		Dispatch documento = Dispatch.call(xedocActivo, "document").getDispatch();
		Dispatch enviar = Dispatch.call(documento,"getElementById","submitFormFirmar").getDispatch();
		Variant var = Dispatch.call(enviar,"click");
		
	}
	
	
/******************************************************************/	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void capturaWebXedoc(){
	    InicioXedoc.oShell = new ActiveXComponent("Shell.Application"); 
	    InicioXedoc.oWindows = InicioXedoc.oShell.invokeGetComponent("Windows");
	    
        try {
			Runtime.getRuntime().exec("C:/Archivos de programa/Internet Explorer/iexplore.exe");
			Thread.sleep(1000);
			
			Runtime.getRuntime().exec("C:/Archivos de programa/Internet Explorer/iexplore.exe");
			Thread.sleep(1000);
			
			
        } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        int iCount = InicioXedoc.oWindows.getProperty("Count").getInt();
        System.out.println("iCount: " + iCount);        
		
        for (int i=iCount-1,j= 1; i >iCount-3 ; i--,j++) {
            ActiveXComponent oWindow = InicioXedoc.oWindows.invokeGetComponent("Item", new Variant(i));     
            String sLocName = oWindow.getProperty("LocationName").getString();
            String sFullName = oWindow.getProperty("FullName").getString();
            boolean isIE = sFullName.toLowerCase().endsWith("iexplore.exe");
            boolean bVisible = oWindow.getProperty("Visible").getBoolean();
            System.out.println("i: " + i + ", loc: " + sLocName + ", name: " + sFullName + ", isIE: " + isIE + ", vis: " + bVisible);
            /*
            if ((isIE)&&(sLocName.startsWith("about:blank"))) {
                oIE = oWindow;
            }
            */
            if(j==1){
            	bandejaXedoc1 = oWindow;
            }
            
            
            if(j==2){
            	// Inicio.paciente2.xedoc = oWindow;
            	ianusApoyoXedoc = oWindow;
            	Dispatch.call(ianusApoyoXedoc, "Navigate","http://ianuschop.sergas.local/ianus_chp_pro/inicio.jsp");
            }
            
        }
        
		Dispatch.call(bandejaXedoc1, "Navigate","http://xedocidx.sergas.local/xedoc_idx/login");
		
		Variant estado = Dispatch.call(bandejaXedoc1,"readyState");
		
		System.out.println("Empieza el primer readyState");
		
		int i=0;
		while(true /* && i < 25000 */){
			estado = Dispatch.call(bandejaXedoc1, "readyState");
		//	System.out.println(i++);
		//	System.out.println(estado.toString());
			if(Integer.valueOf(estado.toString()) == 4){
				System.out.println("El estado está en 4");
				break;
			}
		}
		
		

		
		/*
		try {
		
		Thread.sleep(900);	
			
		 Dispatch.call(Inicio.paciente2.xedoc, "Navigate","http://xedocidx.sergas.local/xedoc_idx/login");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		int ancho = 0;
		int alto = 0;
		int izquierda = 0;
		int arriba = 0;
		if(Inicio.numeroPantallas == 1){
			ancho = 1070;
			alto = 1049;
			arriba = 0;
			izquierda = 850;
		}
		else{
			ancho = 1023;
			alto = 1279;
			arriba = 0;
			izquierda = 1025;
		}
		
	    Dispatch.put(bandejaXedoc1,"height",alto);
	    Dispatch.put(bandejaXedoc1,"width",ancho);
	    Dispatch.put(bandejaXedoc1,"top",arriba);  
	    Dispatch.put(bandejaXedoc1,"left",izquierda);
		
		
		
		if(Inicio.contraseña){
			Dispatch.call(bandejaXedoc1, "Navigate","javascript:" + CadenasJavascriptXedoc.introUsuario0(Inicio.usuario));
		
			Dispatch.put(bandejaXedoc1,"menubar",false);
			Dispatch.put(bandejaXedoc1,"toolbar",false);
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Dispatch.call(bandejaXedoc1, "Navigate","javascript:" + CadenasJavascriptXedoc.introUsuario1(Inicio.usuario));

			
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	 
		
		
		if(Inicio.numeroPantallas == 1){
			ancho = 1919;
			alto = 1049;
			arriba = 0;
			izquierda = 0;
		}
		else{
			ancho = 2047;
			alto = 1251;
			arriba = 0;
			izquierda = 0;
		}
		
	    Dispatch.put(bandejaXedoc1,"height",alto);
	    Dispatch.put(bandejaXedoc1,"width",ancho);
	    Dispatch.put(bandejaXedoc1,"top",arriba);  
	    Dispatch.put(bandejaXedoc1,"left",izquierda);
		
		
		}
		
	if(Inicio.contraseña){
			
			// introduceUsuarioJacob(Inicio.ianus1, Inicio.usuario);
			
			Dispatch.call(ianusApoyoXedoc,"Navigate","javascript:" + CadenasJavascript.introducirUsuario(Inicio.usuario));
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Dispatch.put(ianusApoyoXedoc,"Visible",true);
			Dispatch.put(ianusApoyoXedoc,"menubar",false);
			Dispatch.put(ianusApoyoXedoc,"toolbar",false);
			
			
		    Dispatch.put(ianusApoyoXedoc,"height",1149);  // 1099
		    Dispatch.put(ianusApoyoXedoc,"top",0);  // 180
		    Dispatch.put(ianusApoyoXedoc,"left",1024);
		    if(Inicio.numeroPantallas == 1){
		    	Dispatch.put(ianusApoyoXedoc,"height",1052);  // 1099
		    	Dispatch.put(ianusApoyoXedoc,"width",895);
		    }
		
			
			
//		    introduceUsuarioJacob(Inicio.ianus2, Inicio.usuario);

		    
		}
	}
	
	
	public static void pruebaCapturaWebXedoc(){
	    InicioXedoc.oShell = new ActiveXComponent("Shell.Application"); 
	    InicioXedoc.oWindows = InicioXedoc.oShell.invokeGetComponent("Windows");
	    
        try {
			Runtime.getRuntime().exec("C:/Archivos de programa/Internet Explorer/iexplore.exe");
			Thread.sleep(1000);
			/*
			Runtime.getRuntime().exec("C:/Archivos de programa/Internet Explorer/iexplore.exe");
			Thread.sleep(1000);
			*/
        } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        int iCount = InicioXedoc.oWindows.getProperty("Count").getInt();
        System.out.println("iCount: " + iCount);        
		
        for (int i=iCount-1,j= 1; i >iCount-3 ; i--,j++) {
            ActiveXComponent oWindow = InicioXedoc.oWindows.invokeGetComponent("Item", new Variant(i));     
            String sLocName = oWindow.getProperty("LocationName").getString();
            String sFullName = oWindow.getProperty("FullName").getString();
            boolean isIE = sFullName.toLowerCase().endsWith("iexplore.exe");
            boolean bVisible = oWindow.getProperty("Visible").getBoolean();
            System.out.println("i: " + i + ", loc: " + sLocName + ", name: " + sFullName + ", isIE: " + isIE + ", vis: " + bVisible);
            /*
            if ((isIE)&&(sLocName.startsWith("about:blank"))) {
                oIE = oWindow;
            }
            */
            if(j==1){
            	bandejaXedoc1 = oWindow;
            }
            /*
            if(j==2){
            	Inicio.paciente2.xedoc = oWindow;
            }
            */
        }
        
		Dispatch.call(bandejaXedoc1, "Navigate","http://xedocidx.sergas.local/xedoc_idx/login");
		
		Variant estado = Dispatch.call(bandejaXedoc1,"readyState");
		
		System.out.println("Empieza el primer readyState");
		
		int i=0;
		while(true /* && i < 25000 */){
			estado = Dispatch.call(bandejaXedoc1, "readyState");
		//	System.out.println(i++);
		//	System.out.println(estado.toString());
			if(Integer.valueOf(estado.toString()) == 4){
				System.out.println("El estado está en 4");
				break;
			}
		}
			
		
		int ancho = 0;
		int alto = 0;
		int izquierda = 0;
		int arriba = 0;
		if(Inicio.numeroPantallas == 1){
			ancho = 1070;
			alto = 1049;
			arriba = 0;
			izquierda = 850;
		}
		else{
			ancho = 1023;
			alto = 1279;
			arriba = 0;
			izquierda = 1025;
		}
		
	    Dispatch.put(bandejaXedoc1,"height",alto);
	    Dispatch.put(bandejaXedoc1,"width",ancho);
	    Dispatch.put(bandejaXedoc1,"top",arriba);  
	    Dispatch.put(bandejaXedoc1,"left",izquierda);
		
		
		
		if(Inicio.contraseña){
			Dispatch.call(bandejaXedoc1, "Navigate","javascript:" + CadenasJavascriptXedoc.introUsuario0(Inicio.usuario));
		
			Dispatch.put(bandejaXedoc1,"menubar",false);
			Dispatch.put(bandejaXedoc1,"toolbar",false);
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Dispatch.call(bandejaXedoc1, "Navigate","javascript:" + CadenasJavascriptXedoc.introUsuario1(Inicio.usuario));

			
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	 
		
		
		if(Inicio.numeroPantallas == 1){
			ancho = 1919;
			alto = 1049;
			arriba = 0;
			izquierda = 0;
		}
		else{
			ancho = 2047;
			alto = 1251;
			arriba = 0;
			izquierda = 0;
		}
		
	    Dispatch.put(bandejaXedoc1,"height",alto);
	    Dispatch.put(bandejaXedoc1,"width",ancho);
	    Dispatch.put(bandejaXedoc1,"top",arriba);  
	    Dispatch.put(bandejaXedoc1,"left",izquierda);
		
		
		}
		    
		    
		if(Inicio.contraseña){    
		    
			System.out.println("Empieza el segundo readyState");
			
			i=0;
			while(true /* && i < 25000 */){
				estado = Dispatch.call(bandejaXedoc1, "readyState");
			//	System.out.println(i++);
			//	System.out.println(estado.toString());
				if(Integer.valueOf(estado.toString()) == 4){
					System.out.println("El estado está en 4");
					break;
				}
			}
		    
			Dispatch.call(bandejaXedoc1, "Navigate","javascript:" + CadenasJavascriptXedoc.selectMiBandeja());

			System.out.println("Empieza el tercer readyState");
			
			i=0;
			while(true /* && i < 25000 */){
				estado = Dispatch.call(bandejaXedoc1, "readyState");
			//	System.out.println(i++);
			//	System.out.println(estado.toString());
				if(Integer.valueOf(estado.toString()) == 4){
					System.out.println("El estado está en 4");
					break;
				}
			}
			
		    Dispatch.put(bandejaXedoc1,"height",Inicio.altoP);
		    Dispatch.put(bandejaXedoc1,"width",Inicio.anchoP);
		    Dispatch.put(bandejaXedoc1,"top",0);  
		    Dispatch.put(bandejaXedoc1,"left",0);
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			Dispatch.call(bandejaXedoc1, "Navigate","javascript:" + CadenasJavascriptXedoc.contexto());
			
			
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Dispatch.call(bandejaXedoc1, "Navigate","javascript:" + CadenasJavascriptXedoc.rellenaContexto());
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Dispatch.call(bandejaXedoc1, "Navigate","javascript:" + CadenasJavascriptXedoc.inicio1Xedoc());

			
			System.out.println("Empieza el cuarto readyState");
			
			i=0;
			while(true /* && i < 25000 */){
				estado = Dispatch.call(bandejaXedoc1, "readyState");
			//	System.out.println(i++);
			//	System.out.println(estado.toString());
				if(Integer.valueOf(estado.toString()) == 4){
					System.out.println("El estado está en 4");
					break;
				}
			}
			
			/*
			try {
				Thread.sleep(5000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			

			
			Dispatch documento = Dispatch.call(bandejaXedoc1, "Document").toDispatch();
			Dispatch branding = Dispatch.call(documento, "getElementById","branding").toDispatch();
			Dispatch brandingStilo = Dispatch.get(branding, "style").toDispatch();
			Dispatch.put(brandingStilo, "display","none");
			
			Dispatch entornoLogin = Dispatch.call(documento, "getElementById","entornoLogin").toDispatch();
			Dispatch entornoLoginStilo = Dispatch.get(entornoLogin, "style").toDispatch();
			Dispatch.put(entornoLoginStilo, "display","none");
			
			Dispatch header = Dispatch.call(documento, "getElementById","header").toDispatch();
			Dispatch headerStilo = Dispatch.get(header, "style").toDispatch();
			Dispatch.put(headerStilo, "height","0px");
			

	/*
			
			
			Dispatch columnaIzquierdaEdicion = Dispatch.call(documento, "getElementById","columnaIzquierdaEdicion").toDispatch();
			Dispatch columnaIzquierdaEdicionStilo = Dispatch.get(columnaIzquierdaEdicion, "style").toDispatch();
			Dispatch.put(columnaIzquierdaEdicionStilo, "width","750px");
			Dispatch.put(columnaIzquierdaEdicionStilo, "height","1100px");
			Dispatch.put(columnaIzquierdaEdicionStilo, "float","left");
			
			
			Dispatch completePreview = Dispatch.call(documento, "getElementById","completePreview").toDispatch();
			Dispatch completePreviewStilo = Dispatch.get(completePreview, "style").toDispatch();
			Dispatch.put(completePreviewStilo, "width","750px");
			Dispatch.put(completePreviewStilo, "height","1100px");
			
			Dispatch previewer = Dispatch.call(documento, "getElementById","previewer").getDispatch();
			Dispatch previewerStilo = Dispatch.get(previewer, "style").getDispatch();
			Dispatch.put(previewerStilo, "width","750px");
			Dispatch.put(previewerStilo, "height","1100px");
			
			Dispatch columnaDerechaEdicion = Dispatch.call(documento, "getElementById","columnaDerechaEdicion").getDispatch();
			Dispatch columnaDerechaEdicionStilo = Dispatch.get(columnaDerechaEdicion, "style").getDispatch();
			Dispatch.put(columnaDerechaEdicionStilo, "margin","0px");
	//		Dispatch.put(columnaDerechaEdicionStilo, "float","left");
			
			Dispatch edicionForm = Dispatch.call(documento, "getElementById","edicionForm").getDispatch();
			Dispatch edicionFormStilo = Dispatch.get(edicionForm, "style").getDispatch();
			Dispatch.put(edicionFormStilo, "width","1025px");
		//	Dispatch.put(edicionFormStilo, "float","left");
			
			Dispatch tablaElementosAjax = Dispatch.call(documento, "getElementById","tablaElementosAjax").getDispatch();
			Dispatch tablaElementosAjaxStilo = Dispatch.get(tablaElementosAjax, "style").getDispatch();
			Dispatch.put(tablaElementosAjaxStilo, "width","450px");
			Dispatch.put(tablaElementosAjaxStilo, "height","1000px");
		//	Dispatch.put(tablaElementosAjaxStilo, "float","left");
		
	*/		
			
			try {
				Thread.sleep(4000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Dispatch.call(bandejaXedoc1, "Navigate","javascript:" + CadenasJavascriptXedoc.maquetado2());

			 
		}
	}
	
	
	
	public static void CapturaWebXedoc_nuevoproyecto(){
	    InicioXedoc.oShell = new ActiveXComponent("Shell.Application"); 
	    InicioXedoc.oWindows = InicioXedoc.oShell.invokeGetComponent("Windows");
	    
        try {
			Runtime.getRuntime().exec("C:/Archivos de programa/Internet Explorer/iexplore.exe");
			Thread.sleep(1000);
			/*
			Runtime.getRuntime().exec("C:/Archivos de programa/Internet Explorer/iexplore.exe");
			Thread.sleep(1000);
			*/
        } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        int iCount = InicioXedoc.oWindows.getProperty("Count").getInt();
        System.out.println("iCount: " + iCount);        
		
        for (int i=iCount-1,j= 1; i >iCount-3 ; i--,j++) {
            ActiveXComponent oWindow = InicioXedoc.oWindows.invokeGetComponent("Item", new Variant(i));     
            String sLocName = oWindow.getProperty("LocationName").getString();
            String sFullName = oWindow.getProperty("FullName").getString();
            boolean isIE = sFullName.toLowerCase().endsWith("iexplore.exe");
            boolean bVisible = oWindow.getProperty("Visible").getBoolean();
            System.out.println("i: " + i + ", loc: " + sLocName + ", name: " + sFullName + ", isIE: " + isIE + ", vis: " + bVisible);
            /*
            if ((isIE)&&(sLocName.startsWith("about:blank"))) {
                oIE = oWindow;
            }
            */
            if(j==1){
            	bandejaXedoc1 = oWindow;
            }
            /*
            if(j==2){
            	Inicio.paciente2.xedoc = oWindow;
            }
            */
        }
        
		Dispatch.call(bandejaXedoc1, "Navigate","http://xedocidx.sergas.local/xedoc_idx/login");
		
		Variant estado = Dispatch.call(bandejaXedoc1,"readyState");
		
		System.out.println("Empieza el primer readyState");
		
		long tiempoInicial = System.currentTimeMillis();
		int numRepeticiones = 0;
		while(true){
			estado = Dispatch.call(bandejaXedoc1, "readyState");
		//	System.out.println(i++);
		//	System.out.println(estado.toString());
			if(Integer.valueOf(estado.toString()) == 4){
				System.out.println("El estado está en 4");
				break;
			}
			if(tiempoInicial + 3000 < System.currentTimeMillis()){
				System.out.println("Reiniciando la navegación.");
				Dispatch.call(bandejaXedoc1, "Quit");
				tiempoInicial = System.currentTimeMillis();
				numRepeticiones++;
				bandejaXedoc1 = new ActiveXComponent("InternetExplorer.Application");
				//Dispatch.call(bandejaXedoc, "Visible",true);
				Dispatch.call(bandejaXedoc1, "Navigate","http://xedocidx.sergas.local/xedoc_idx/login");
			}
			if(numRepeticiones>2){
				break;
			}
		}
			
		
		int ancho = 0;
		int alto = 0;
		int izquierda = 0;
		int arriba = 0;
		if(Inicio.numeroPantallas == 1){
			ancho = 1070;
			alto = 1049;
			arriba = 0;
			izquierda = 850;
		}
		else{
			ancho = 1023;
			alto = 1279;
			arriba = 0;
			izquierda = 1025;
		}
		
	    Dispatch.put(bandejaXedoc1,"height",alto);
	    Dispatch.put(bandejaXedoc1,"width",ancho);
	    Dispatch.put(bandejaXedoc1,"top",arriba);  
	    Dispatch.put(bandejaXedoc1,"left",izquierda);
		
		
		
		if(Inicio.contraseña){
			Dispatch.call(bandejaXedoc1, "Navigate","javascript:" + CadenasJavascriptXedoc.introUsuario0(Inicio.usuario));
		
			Dispatch.put(bandejaXedoc1,"menubar",false);
			Dispatch.put(bandejaXedoc1,"toolbar",false);
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Dispatch.call(bandejaXedoc1, "Navigate","javascript:" + CadenasJavascriptXedoc.introUsuario1(Inicio.usuario));

			
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	 
		
		
		if(Inicio.numeroPantallas == 1){
			ancho = 1919;
			alto = 1049;
			arriba = 0;
			izquierda = 0;
		}
		else{
			ancho = 2047;
			alto = 1251;
			arriba = 0;
			izquierda = 0;
		}
		
	    Dispatch.put(bandejaXedoc1,"height",alto);
	    Dispatch.put(bandejaXedoc1,"width",ancho);
	    Dispatch.put(bandejaXedoc1,"top",arriba);  
	    Dispatch.put(bandejaXedoc1,"left",izquierda);
		
		
		}
		    
		    
		if(Inicio.contraseña){    
		    
			System.out.println("Empieza el segundo readyState");
			
			while(true /* && i < 25000 */){
				estado = Dispatch.call(bandejaXedoc1, "readyState");
			//	System.out.println(i++);
			//	System.out.println(estado.toString());
				if(Integer.valueOf(estado.toString()) == 4){
					System.out.println("El estado está en 4");
					break;
				}
			}
		    
			Dispatch.call(bandejaXedoc1, "Navigate","javascript:" + CadenasJavascriptXedoc.selectMiBandeja());

			System.out.println("Empieza el tercer readyState");
			
			while(true /* && i < 25000 */){
				estado = Dispatch.call(bandejaXedoc1, "readyState");
			//	System.out.println(i++);
			//	System.out.println(estado.toString());
				if(Integer.valueOf(estado.toString()) == 4){
					System.out.println("El estado está en 4");
					break;
				}
			}
			
		    Dispatch.put(bandejaXedoc1,"height",Inicio.altoP);
		    Dispatch.put(bandejaXedoc1,"width",Inicio.anchoP);
		    Dispatch.put(bandejaXedoc1,"top",0);  
		    Dispatch.put(bandejaXedoc1,"left",0);
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		//	Dispatch.call(bandejaXedoc, "Navigate","javascript:" + CadenasJavascriptXedoc.pruebaTabla());

			
	/*		
			
			Dispatch.call(bandejaXedoc, "Navigate","javascript:" + CadenasJavascriptXedoc.contexto());
			
			
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Dispatch.call(bandejaXedoc, "Navigate","javascript:" + CadenasJavascriptXedoc.rellenaContexto());
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Dispatch.call(bandejaXedoc, "Navigate","javascript:" + CadenasJavascriptXedoc.inicio1Xedoc());
			

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			capturaUltimoExplorer();
			
			System.out.println("readyState del xedoc1");
			while(true){
				estado = Dispatch.call(Inicio.documento1.xedoc, "readyState");
			//	System.out.println(i++);
			//	System.out.println(estado.toString());
				if(Integer.valueOf(estado.toString()) == 4){
					System.out.println("El estado está en 4");
					break;
				}
			}
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			inicializaMaquetado(Inicio.documento1.xedoc);
	*/		
			/*
			
			Dispatch columnaIzquierdaEdicion = Dispatch.call(documento, "getElementById","columnaIzquierdaEdicion").toDispatch();
			Dispatch columnaIzquierdaEdicionStilo = Dispatch.get(columnaIzquierdaEdicion, "style").toDispatch();
			Dispatch.put(columnaIzquierdaEdicionStilo, "width","750px");
			Dispatch.put(columnaIzquierdaEdicionStilo, "height","1100px");
			Dispatch.put(columnaIzquierdaEdicionStilo, "float","left");
			
			
			Dispatch completePreview = Dispatch.call(documento, "getElementById","completePreview").toDispatch();
			Dispatch completePreviewStilo = Dispatch.get(completePreview, "style").toDispatch();
			Dispatch.put(completePreviewStilo, "width","750px");
			Dispatch.put(completePreviewStilo, "height","1100px");
			
			Dispatch previewer = Dispatch.call(documento, "getElementById","previewer").getDispatch();
			Dispatch previewerStilo = Dispatch.get(previewer, "style").getDispatch();
			Dispatch.put(previewerStilo, "width","750px");
			Dispatch.put(previewerStilo, "height","1100px");
			
			Dispatch columnaDerechaEdicion = Dispatch.call(documento, "getElementById","columnaDerechaEdicion").getDispatch();
			Dispatch columnaDerechaEdicionStilo = Dispatch.get(columnaDerechaEdicion, "style").getDispatch();
			Dispatch.put(columnaDerechaEdicionStilo, "margin","0px");
	//		Dispatch.put(columnaDerechaEdicionStilo, "float","left");
			
			Dispatch edicionForm = Dispatch.call(documento, "getElementById","edicionForm").getDispatch();
			Dispatch edicionFormStilo = Dispatch.get(edicionForm, "style").getDispatch();
			Dispatch.put(edicionFormStilo, "width","1025px");
		//	Dispatch.put(edicionFormStilo, "float","left");
			
			Dispatch tablaElementosAjax = Dispatch.call(documento, "getElementById","tablaElementosAjax").getDispatch();
			Dispatch tablaElementosAjaxStilo = Dispatch.get(tablaElementosAjax, "style").getDispatch();
			Dispatch.put(tablaElementosAjaxStilo, "width","450px");
			Dispatch.put(tablaElementosAjaxStilo, "height","1000px");
		//	Dispatch.put(tablaElementosAjaxStilo, "float","left");
		
	
			
			try {
				Thread.sleep(4000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Dispatch.call(bandejaXedoc, "Navigate","javascript:" + CadenasJavascriptXedoc.maquetado2());

			*/ 
		}
	}
	
	
	public static void inicializaMaquetado(ActiveXComponent xedoc){
		
		int ancho = 0;
		int alto = 0;
		int izquierda = 0;
		int arriba = 0;
		
		if(Inicio.numeroPantallas == 1){
			ancho = 1919;
			alto = 1049;
			arriba = 0;
			izquierda = 0;
		}
		else{
			ancho = 2047;
			alto = 1251;
			arriba = 0;
			izquierda = 0;
		}
		
	    Dispatch.put(xedoc,"height",alto);
	    Dispatch.put(xedoc,"width",ancho);
	    Dispatch.put(xedoc,"top",arriba);  
	    Dispatch.put(xedoc,"left",izquierda);
		Dispatch.put(xedoc,"menubar",false);
		Dispatch.put(xedoc,"toolbar",false);
		
		
		
	//	xedoc.invoke("eval","clearAjaxDiv('formResult'); document.getElementById('formContexto').reset();");
		
	    
		 Dispatch.call(xedoc, "Navigate","javascript:" + CadenasJavascriptXedoc.zoomPdf()  /* CadenasJavascriptXedoc.maquetado2() */ );
	}
	
	
	
	public static void cargaNuevoPdf(ActiveXComponent xedoc){
		
		Dispatch documento = Dispatch.get(xedoc, "document").getDispatch();
		Dispatch siguiente = Dispatch.call(documento,"getElementById","siguiente").getDispatch();
		
		
		
		String contenidoDeSiguiente;
		
		if(!Inicio.saltarXedoc){
			try {
				contenidoDeSiguiente = Dispatch.get(siguiente,"innerHTML").toString();
				System.out.println("Contenido de siguiente1... " + contenidoDeSiguiente );
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				contenidoDeSiguiente = null;
			}
		}
		else{
			contenidoDeSiguiente = "ok";
		}

		
		if(contenidoDeSiguiente != null){
			
			if(!Inicio.saltarXedoc){
				Dispatch.call(siguiente, "click");
			}	
				
			try {
					Thread.sleep(2000); 
			} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}

			Inicio.saltarXedoc = false;
			
			documento = Dispatch.get(xedoc, "document").getDispatch();
			siguiente = Dispatch.call(documento,"getElementById","siguiente").getDispatch();
			
			System.out.println("Siguiente... " + siguiente);
			
			try {
				contenidoDeSiguiente = Dispatch.get(siguiente,"innerHTML").toString();
				System.out.println("Contenido de siguiente2... " + contenidoDeSiguiente );
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				contenidoDeSiguiente = null;
			}
			
			if(contenidoDeSiguiente != null){
				Dispatch.call(siguiente, "click");
				
				try {
					Thread.sleep(2000);  
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Dispatch labelAtributo = Dispatch.call(documento,"getElementById","labelAtributo").getDispatch();
				String nombreFichero = Dispatch.get(labelAtributo,"innerHTML").getString();
				String nhc = extraerNHC(nombreFichero);
				
				Dispatch loadContexto = Dispatch.call(documento,"getElementById","loadContexto").getDispatch();
				String nombrePacienteContexto = Dispatch.get(loadContexto,"innerHTML").getString();
				
				
				System.out.println(nhc);
				System.out.println(nombrePacienteContexto);
				
				if(!nombrePacienteContexto.contains(nhc)){
					
					Dispatch contexto = Dispatch.call(documento, "getElementById","contextoMenuSuperior").getDispatch();
					Dispatch.call(contexto, "click");
					
					String fechaInicio = "";
					String fechaFin = "";
					
					String fechas[] = getFechasContexto();
					fechaInicio = fechas[0];
					fechaFin = fechas[1];
					
					Dispatch numeroHC = Dispatch.call(documento, "getElementById","{hc}numeroHC").getDispatch();
					Dispatch.put(numeroHC,"value",nhc);
					
					Dispatch fechaI = Dispatch.call(documento,"getElementById","FechaIni").getDispatch();
					Dispatch fechaF = Dispatch.call(documento,"getElementById","FechaFin").getDispatch();
					
					Dispatch.put(fechaI,"value",fechaInicio);
					Dispatch.put(fechaF,"value",fechaFin);
					
					Dispatch submitFormContexto =  Dispatch.call(documento,"getElementById","submitFormContexto").getDispatch();
					Dispatch.call(submitFormContexto, "click");
					
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					String cambiarContexto = nhc + "-360340";
					String codigoJavascript = "javascript:cambiarContexto('" + cambiarContexto + "');";
					
					Dispatch.call(xedoc, "navigate",codigoJavascript);
					
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					String xed = "Xedoc ";
					if(Inicio.xedoc1onTop){
						xed += 2;
					}
					else{
						xed += 1;
					}
					new MaquetadoXedoc(xedoc, xed);
				}
				else{
					String xed = "Xedoc ";
					if(Inicio.xedoc1onTop){
						xed += 2;
					}
					else{
						xed += 1;
					}
					new MaquetadoXedoc(xedoc, xed);
				}

			}
			else{
				String xed = "Xedoc ";
				if(Inicio.xedoc1onTop){
					xed += 2;
				}
				else{
					xed += 1;
				}
				finXedoc(xedoc, xed);
				System.out.println("Este xedoc se acabó en 2.");
			}
		}
		else{
			String xed = "Xedoc ";
			if(Inicio.xedoc1onTop){
				xed += 2;
			}
			else{
				xed += 1;
			}
			finXedoc(xedoc, xed);
			System.out.println("Este xedoc se acabó en 1.");
		}
	}
	
	
	private static String extraerNHC(String nombreFichero){
		
		String nhc = "";
		
		String campos[] = nombreFichero.split(" @");
		if(campos.length == 4){
			nhc = campos[1];
		}
		
		return nhc;
	}
	
	private static String[] getFechasContexto(){
		
		String fechas[] = new String[2];
		
		String fechaInicio = "";
		String fechaFin = "";

		int diaHoy = 1;
		int mesHoy = 1;
		int añoHoy = 1;
		
		int diaHaceUnMes = 1;
		int mesHaceUnMes = 1;
		int añoHaceUnMes = 1;
		
		Calendar calendario = Calendar.getInstance();
		diaHoy = calendario.get(Calendar.DAY_OF_MONTH);
		mesHoy = calendario.get(Calendar.MONTH) + 1;
		añoHoy = calendario.get(Calendar.YEAR);
		
		fechaFin = diaHoy + "/" + mesHoy + "/" + añoHoy;

		calendario.add(Calendar.DAY_OF_MONTH,- RANGO_DIAS_CONSULTA);
		
		diaHaceUnMes = calendario.get(Calendar.DAY_OF_MONTH);
		mesHaceUnMes = calendario.get(Calendar.MONTH) + 1;
		añoHaceUnMes = calendario.get(Calendar.YEAR);
		
		fechaInicio = diaHaceUnMes + "/" + mesHaceUnMes + "/" + añoHaceUnMes;
		
		fechas[0] = fechaInicio;
		fechas[1] = fechaFin;
		
		return fechas;

	}
	
	public static void finXedoc(ActiveXComponent xedoc, String nombre ){
		
		Dispatch.call(xedoc, "quit");
		if(nombre.equals("Xedoc1")){
			Inicio.xedoc1activo = false;
			Inicio.ventanaControlXedoc.jBxedoc1.setBackground(Color.red);
			
		}
		else{
			Inicio.xedoc2activo = false;
			Inicio.ventanaControlXedoc.jBxedoc2.setBackground(Color.red);
		}
		
	}
}
