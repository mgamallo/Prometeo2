package es.mgamallo.prometeo;

import java.awt.AWTException;
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
	public static ActiveXComponent bandejaXedoc2;
	
	private static final String COLOR_BANDEJA_XEDOC1 = "#A1BEE6";
	private static final String COLOR_BANDEJA_XEDOC2 = "#FF8080";
	
	public static int RANGO_DIAS_CONSULTA = 45;
	
	
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

        return oWindow;
	}
	
	
	public static void capturaUltimos2Explorer(){
        
        int iCount = InicioXedoc.oWindows.getProperty("Count").getInt();
        System.out.println("iCount: " + iCount);  
        
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        for (int i=iCount-1,j= 1; i >iCount-3 ; i--,j++) {
            ActiveXComponent oWindow = InicioXedoc.oWindows.invokeGetComponent("Item", new Variant(i));     
            String sLocName = oWindow.getProperty("LocationName").getString();
            String sFullName = oWindow.getProperty("FullName").getString();
            boolean isIE = sFullName.toLowerCase().endsWith("iexplore.exe");
            boolean bVisible = oWindow.getProperty("Visible").getBoolean();
            System.out.println("i: " + i + ", loc: " + sLocName + ", name: " + sFullName + ", isIE: " + isIE + ", vis: " + bVisible);

            if(j==1){
            	Inicio.documento1.xedoc = oWindow;
             }
            if(j==2){
            	// Inicio.paciente2.xedoc = oWindow;
            	Inicio.documento2.xedoc = oWindow;
            }	
        }
         
	}
	
	
	public static void inicializa2Xedocs(){
		capturaWebsXedoc();
		formateaWebsXedoc(bandejaXedoc1);
		inicializaBandeja(bandejaXedoc1, "Bandeja Xedoc 1");
		formateaWebsXedoc(bandejaXedoc2);
		inicializaBandeja(bandejaXedoc2, "Bandeja Xedoc 2");
		formateaIanus(ianusApoyoXedoc);
		
		
	// Cargar aqu� la ventana con las opciones de la bandeja:
	// 1 Capturar la bandeja, y empezar por los dos primeros nhcs
	// 2 Solo capturar la bandeja, con mensaje de confirmaci�n
			
		capturaBandeja(bandejaXedoc1,0);
		capturaBandeja(bandejaXedoc2,1);
	//	capturaBandeja(bandejaXedoc2);
		
	}
	
	
	public static void capturaWebsXedoc(){
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
            	bandejaXedoc2 = oWindow;
            	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		Dispatch.call(bandejaXedoc2, "Navigate","http://xedocidx.sergas.local/xedoc_idx/login");
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
		
	    Dispatch.put(bandejaXedoc,"height",alto);
	    Dispatch.put(bandejaXedoc,"width",ancho);
	    Dispatch.put(bandejaXedoc,"top",arriba);  
	    Dispatch.put(bandejaXedoc,"left",izquierda);
		
		
		
		if(Inicio.contrase�a){
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
		
	    Dispatch.put(bandejaXedoc,"height",alto);
	    Dispatch.put(bandejaXedoc,"width",ancho);
	    Dispatch.put(bandejaXedoc,"top",arriba);  
	    Dispatch.put(bandejaXedoc,"left",izquierda);
		
		
		}
		
		Variant estado = Dispatch.call(bandejaXedoc,"readyState");
		
		while(true ){
			estado = Dispatch.call(bandejaXedoc, "readyState");
			if(Integer.valueOf(estado.toString()) == 4){
				System.out.println("El estado est� en 4");
				break;
			}
		}
	    
		Dispatch.call(bandejaXedoc, "Navigate","javascript:" + CadenasJavascriptXedoc.selectMiBandeja());

		System.out.println("Empieza el tercer readyState");
		
		while(true){
			estado = Dispatch.call(bandejaXedoc, "readyState");
			if(Integer.valueOf(estado.toString()) == 4){
				System.out.println("El estado est� en 4");
				break;
			}
		}
	}
	
	public static void formateaIanus(ActiveXComponent ianus){
	if(Inicio.contrase�a){
			
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
		
		if(nombreBandeja.equals("Bandeja Xedoc 1"))
			Dispatch.put(estiloHeader, "background","black");	
		else{
			Dispatch.put(estiloHeader, "background","black");	
			Dispatch.put(estiloEntornoLogin, "color","red");
		}
	}
	
	public static void capturaBandeja(ActiveXComponent bandejaXedoc, int filaInicial){
		
		String fechaInicio = "";
		String fechaFin = "";

		int diaHoy = 1;
		int mesHoy = 1;
		int a�oHoy = 1;
		
		int diaHaceUnMes = 1;
		int mesHaceUnMes = 1;
		int a�oHaceUnMes = 1;
		
		Calendar calendario = Calendar.getInstance();
		diaHoy = calendario.get(Calendar.DAY_OF_MONTH);
		mesHoy = calendario.get(Calendar.MONTH) + 1;
		a�oHoy = calendario.get(Calendar.YEAR);
		
		fechaFin = diaHoy + "/" + mesHoy + "/" + a�oHoy;

		calendario.add(Calendar.DAY_OF_MONTH,-45);
		
		diaHaceUnMes = calendario.get(Calendar.DAY_OF_MONTH);
		mesHaceUnMes = calendario.get(Calendar.MONTH) + 1;
		a�oHaceUnMes = calendario.get(Calendar.YEAR);
		
		fechaInicio = diaHaceUnMes + "/" + mesHaceUnMes + "/" + a�oHaceUnMes;
		
		String cadena = ""
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
				//	+ "alert(filaSeleccionada);"
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
				//	+ "alert('hola');"
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

				+ "var numFila = 1;"
				+ "for(var i=0;i<filas;i++){"
					+ "celdas[i*5 + 2].setAttribute('id',i);"
					+ "celdas[i*5 + 2].setAttribute('onclick','getNHC(this);');"
					+ "var anclas = celdas[i*5 + 4].getElementsByTagName('a');"
					+ "anclas[0].target = '_blank';"
					+ "numFila++;"
				+ "}"
				+ "celdas[filaInicial*5 + 2].click();"
		//		+ "alert(filas);"
				+ "";
		
		Dispatch.call(bandejaXedoc, "Navigate","javascript:" +  cadena );
				
	}
	
	
	public static void captura2XedocIndividuales(){
		
		
		try {
		
			 capturaUltimos2Explorer();
			
			 MaquetadoXedoc maquetado1 = new MaquetadoXedoc(Inicio.documento1.xedoc, "Xedoc 1");
			 
			 Thread.sleep(200);
			 		
			 MaquetadoXedoc maquetado2 = new MaquetadoXedoc(Inicio.documento2.xedoc, "Xedoc 2");
		
		}catch(InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
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
				System.out.println("El estado est� en 4");
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
		
		
		
		if(Inicio.contrase�a){
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
		
	if(Inicio.contrase�a){
			
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
				System.out.println("El estado est� en 4");
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
		
		
		
		if(Inicio.contrase�a){
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
		    
		    
		if(Inicio.contrase�a){    
		    
			System.out.println("Empieza el segundo readyState");
			
			i=0;
			while(true /* && i < 25000 */){
				estado = Dispatch.call(bandejaXedoc1, "readyState");
			//	System.out.println(i++);
			//	System.out.println(estado.toString());
				if(Integer.valueOf(estado.toString()) == 4){
					System.out.println("El estado est� en 4");
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
					System.out.println("El estado est� en 4");
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
					System.out.println("El estado est� en 4");
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
				System.out.println("El estado est� en 4");
				break;
			}
			if(tiempoInicial + 3000 < System.currentTimeMillis()){
				System.out.println("Reiniciando la navegaci�n.");
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
		
		
		
		if(Inicio.contrase�a){
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
		    
		    
		if(Inicio.contrase�a){    
		    
			System.out.println("Empieza el segundo readyState");
			
			while(true /* && i < 25000 */){
				estado = Dispatch.call(bandejaXedoc1, "readyState");
			//	System.out.println(i++);
			//	System.out.println(estado.toString());
				if(Integer.valueOf(estado.toString()) == 4){
					System.out.println("El estado est� en 4");
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
					System.out.println("El estado est� en 4");
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
					System.out.println("El estado est� en 4");
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
	
}
