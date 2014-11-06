package es.mgamallo.prometeo;

import java.util.ArrayList;

import com.jacob.activeX.ActiveXComponent;

public class CadenasJavascript {
	
	protected static final String LS = System.getProperty("line.separator");
	
	static public final String putUsuario(String usuario){
		String cadena = "document.getElementById('usuario').innerHTML = '" + 
				usuario + "';";
		
		return cadena;
	}
	
	static public String getCadenaUsuarios(){
		String cadena = "<ul id=\'da-thumbs\' class=\'da-thumbs\'>" + LS;
		
		for(int i=0;i<Inicio.usuarios.length;i++){
			
			String numUsuario;
			if(i<10){
				numUsuario = "0";
				numUsuario = numUsuario + String.valueOf(i);
			}
			else{
				numUsuario = String.valueOf(i);
			}
			
			/*
			String ca = "<li><a class='login-window' href=\'command://user_" + numUsuario + "_" + Inicio.usuarios[i].alias + "\'>" +
								"<img src=\'images/" + Inicio.usuarios[i].imagen + ".jpg\' width=\'200px\'/>" +
									"<div><span>" + Inicio.usuarios[i].alias + "</span></div>" +
							"</a></li>" + LS;
			*/
			
			String ca = "<li><a class='login-window' href=\'command://user_" + numUsuario + "_" + Inicio.usuarios[i].alias + "\'>" +
					"<img src=\'images/" + Inicio.usuarios[i].imagen + ".jpg\' width=\'200px\'/>" +
						"<div><span>" + Inicio.usuarios[i].alias + "</span></div>" +
				"</a></li>" + LS;
			
			cadena = cadena + ca;
		}
		
		String ca = "<li><a href=\'command://user_99_nuevo'>" +
				"<img src=\'images/99.jpg\' width=\'200px\'/>" +
					"<div><span> Nuevo usuario </span></div>" +
			"</a></li>" + LS;
		
		
		cadena = cadena + ca + "</ul>";
		
		return cadena;
		
	}
	
	static public String getCarpetas(boolean firmado, boolean libres, ArrayList<Directorio> carpetas){
		
		
		String cadena = "";
		
		for(int i=0;i<carpetas.size();i++){
			
			String numero = "0";
			if(i<10){
				numero += String.valueOf(i);
			}
			else{
				numero = String.valueOf(i);
			}
			
			cadena += ""
					+ "<a href='command://carpeta_" + numero + "' class='tile ";
			if(carpetas.get(i).numeroPdfs > 89){
				cadena += "double double-vertical ";
			}
			else if(carpetas.get(i).numeroPdfs >49){
				cadena += "double ";
			}
			else if(carpetas.get(i).numeroPdfs <20){
				cadena += "half ";
			}
			
			cadena += carpetas.get(i).color;
			
			cadena += " bg-hover-lightGreen'>" + 
					    "<div class='tile-content'>" + 
					    	"<div class='padding10'>" +  
					    		"<h1 class='fg-white'></h1>" + 
					    		"<h2 class='fg-white center'>" + carpetas.get(i).servicio + "</h2>" + 
					    	"</div>" + 
					    "</div>" + 
					    "<div class='brand'>" + 
					    	"<span class='label fg-white'><strong>" + carpetas.get(i).usuario + "</strong></span>" + 
					    	"<div class='badge fg-white'><strong>" + carpetas.get(i).numeroPdfs + "</strong></div>" + 
					    "</div></a>" + 
					    	
					    	"";
					
		}
		
		System.out.println(cadena);
		
		return cadena;
	}
/*	
	static public final String buscaNodoPrincipal(Servicio servicio){
		
		final String seleccionarNodoConsulta = 
			"var framePrincipal = window.frames;" + LS +
			"var frameArbol = framePrincipal['principal'].frames['datos'].frames['arbol'].frames['despliegue'];" + LS +
			"alert(frameArbol.location);" + LS +
			//"var nodo = frameArbol.document.selectElementById('link10');" + LS + 
		
			"var nodo = frameArbol.document.anchors;" + LS +
			"var numeroAncla = 0;" + LS +
			"for(var i=0;i<nodo.length;i++){" + LS +
				//"var n = nodo[i].innerHTML.indexOf('REHABILITACION');" + LS +
				"if(nodo[i].innerHTML.indexOf('" + servicio.nombreCompleto + "') != -1){" + LS +
					"numeroAncla = i;" + LS +
				"}" + LS +	
				// "alert('N en ' + i + ' vale: ' + n); " + LS +
				// "alert(numeroAncla);" + LS +
			"}" + LS +
		
			// "alert(nodo[numeoAncla].innerHTML);" + LS +

			//"frameArbol.fichaServicioCEX('SvcCEX_360340_REHC')"
		
			"nodo[numeroAncla].click();"
		;
		
		return seleccionarNodoConsulta;
	}
*/	
	static public final String introducirUsuario(Usuario usuario){
		
		System.out.println(usuario.alias);
		
		usuario.usuario = "mgamgul1";
		usuario.password = "archivo0";
		
		final String introducirUsuarioPulido = 
				"var framePrincipal = window.frames;" + LS +
				"var frameLogin = framePrincipal['principal'].frames['main'];" + LS +
				"var login = frameLogin.document.getElementById('login');" + LS +
				"var password = frameLogin.document.getElementById('password');" + LS +
				"login.value = '" + usuario.usuario + "';" + LS +
				"password.value = '" + usuario.password + "';" + LS +
				"frameLogin.aceptar();"
				;  
		
		return introducirUsuarioPulido;
	}
	
	static public final String introducirNHC(String nhc){
		String introducirNHCPulido = 
			"var framePrincipal = window.frames;" + LS +
			"var frameNHC = framePrincipal['principal'].frames['mainFrame'];" + LS +
			"var NHC = frameNHC.document.buscarPacienteForm.ID_NHC;" + LS +
	
			"NHC.value = " + nhc + ";" + LS +
			"frameNHC.buscar();"
			;
		
		String introducirNHCresumido = 
				"javascript:window.frames.principal.mainFrame.document.buscarPacienteForm.ID_NHC.value = " 
				+ nhc + ";window.principal.mainFrame.buscar();"
		       /* + "var cargado = false;" 
				+ "while(!cargado){"
					+ "if (document.readyState === 3){"
						+ "alert('cargado');"
						+ "cargado = true;"
						+ "break;}"
				+ "}"
				+ "alert('salimos del bucle');"   */
						;
		
		
		return introducirNHCresumido;
	}

	static public final String salirDelPaciente(){
		
		final String salirPaciente = 
				"var framePrincipal = window.frames;" + LS +
				"var frameBotonera = framePrincipal['principal'].frames['botonera'];" + LS +

				"frameBotonera.inicio();"
				;
		
		return salirPaciente;
	}
	
	
	static public final String buscarBotonAsociar(){
		
		final String botonAsociar = 
				"javascript:" +
				"var consultas = false;" +
				"var rutas = [];" +
				"rutas[0] = principal.datos.ficha.hosp_botonera;" +
				"rutas[1] = principal.datos.ficha.inf_botonera;" +
				"rutas[2] = principal.datos.ficha.cex_botonera;" +
				"rutas[3] = principal.datos.ficha.menu;" +

				"for(var i=0;i<4;i++){" +
					"if(!(rutas[i] === undefined)){" +
						"if(i==3){ " +
							"if(principal.datos.ficha.menu.document.anchors.length === 3){" +
								"rutas[3].parent.parent.arbol.despliegue.crearDocExtServicioCEX();break;" +
							"}" + 
						"}" +
						"rutas[i].asociarDocumento(); break;" +
					"}" +
				"}"		
				
				;
		return botonAsociar;
	}

	static public final String buscarNodoConsultas(String servicio){
		
		final String nodo = 

				  "var nodo = principal.datos.arbol.despliegue.document.anchors;"
			//	+ "alert('numero de anclas ' + nodo.length);"
				+ "var numeroAncla = 0;"
				+ "for(var i=0;i<nodo.length;i++){"
					+ "if(nodo[i].innerHTML.indexOf('" + servicio + "') != -1){"
						+ "numeroAncla = i;"
					+ "}"
				+ "}"
				+ "nodo[numeroAncla].click();" 
	
				;
		
		return nodo;
	}
	
	static public final String buscarNodoHosp(String servicio){
		
		String servicioH = servicio;

		
		final String nodo = 

				  "var nodo = principal.datos.arbol.despliegue.document.anchors;"
			//	+ "alert('numero de anclas ' + nodo.length);"
				+ "var numeroAncla = 0;"
				+ "for(var i=0;i<nodo.length;i++){"
					+ "if(nodo[i].innerHTML.indexOf('" + servicioH + "') != -1){"
						+ "numeroAncla = i;"
						+ "break;"
					+ "}"
				+ "}"
				+ "nodo[numeroAncla].click();" 
	
				;
		
		return nodo;
	}
	
	
	static public final String seleccionarFichaHosp(){
		
		return  "principal.datos.ficha.hosp_main.cambiar('pFicha');alert('ficha abierta')";
	}
	
	static public final String obtieneDatosFichaHosp(){
		
		String datosFicha = ""
				
				+ "var tablas = principal.datos.ficha.hosp_main.episodio.document.getElementsByTagName('table');"
				+ "var celdas = tablas[6].getElementsByTagName('td');"
				+ "var fechaIngreso = celdas[5].innerHTML;"
				+ "var horaIngreso = celdas[7].innerHTML;"
				+ "celdas = tablas[2].getElementsByTagName('td');"
				+ "var nombre = celdas[3].innerHTML;"
				+ "var fechaNacimiento = celdas[9].innerHTML;"
				+ "var nss = celdas[15].innerHTML;"				
				+ "var cadena = '1@' + fechaIngreso + '@' + horaIngreso +'@' + nombre + '@' + fechaNacimiento + '@' + nss;"
				+ "window.clipboardData.setData('Text', cadena);"
				;
			
	
		
		String datosFichaSimple = ""
				+ "var tablas = principal.datos.ficha.hosp_main.episodio.document.getElementsByTagName('table');"
				+ "var celdas = tablas[6].getElementsByTagName('td');"
				+ "var fechaIngreso = celdas[5].innerHTML;"
				+ "var horaIngreso = '1@' + fechaIngreso + '@' + celdas[7].innerHTML;"

				+ "var hola = window.clipboardData.setData('Text', horaIngreso);"
				;
			//	+ "alert(cadena);";
		
		String datosFichaHosp2 = ""
				+ "celdas = tablas[2].getElementsByTagName('td');"
				+ "var nombre = celdas[3].innerHTML;"
				+ "var fechaNacimiento = celdas[9].innerHTML;"
				+ "var nss = celdas[15].innerHTML;"				
				+ "window.clipboardData.setData('Text', nss);"
				;
		
		String datosEnteros = ""
				+ "var tablas = principal.datos.ficha.hosp_main.episodio.document.getElementsByTagName('table');"
				+ "var celdas = '1@' + tablas[6].innerHTML + '@' + tablas[2].innerHTML;"
				+ "var hola = window.clipboardData.setData('Text', celdas);"
				;
		

			return datosFichaSimple;

		
		
	}
	
	
	static public final String obtieneDatosFichaURG(){
		
		String datosFicha = ""
				
				+ "var tablas = principal.datos.ficha.main.myFrame.document.getElementsByTagName('table');"
				+ "var celdas = tablas[6].getElementsByTagName('td');"
				+ "var cadena = '2@' + celdas[5].innerHTML + '@' + celdas[7].innerHTML;"
				+ "var hola = window.clipboardData.setData('Text', cadena);"
				;
	
			return datosFicha;
	
		
		
	}
	
	static public final String obtieneDatosFichaCIA(){
		
		String datosFicha = ""
				
				+ "var tablas = principal.datos.ficha.inf_main.episodio.noprogramado.document.getElementsByTagName('table');"
				+ "var celdas = tablas[6].getElementsByTagName('td');"
				+ "var cadena = '3@' + celdas[3].innerHTML + '@' + celdas[5].innerHTML + '@' + celdas[9].innerHTML + '@' + celdas[15].innerHTML;"
				+ "var hola = window.clipboardData.setData('Text', cadena);"
				+ "alert('hola');"
				;
	
			return datosFicha;

	}
	
	static public final String seleccionaFichaPaciente(){
		
		String datosFicha = ""
				
				+ "var hola = principal.datos.ficha.cambiar('pPruebas');"
			//	+ "while(principal.datos.ficha.pruebas.document.readyState != 3)"
				
				;
		
		return datosFicha;
	}
	
/*	static public final String getDatosPaciente(){
		
		String datosFicha = ""
				+ "var hola = principal.datos.ficha.cambiar('pPruebas');"
				+ "while(principal.datos.ficha.pruebas.document.readyState < 3){}"
	
				+ "var tablas = principal.datos.ficha.pruebas.document.getElementsByTagName('table');"
				+ "var celdas = tablas[2].getElementsByTagName('td');"
				+ "var cadena = '0@' + celdas[1].innerHTML + '@' + celdas[3].innerHTML + '@' + celdas[7].innerHTML ;"
				+ "var hola = window.clipboardData.setData('Text', cadena);"
				;
		
		return datosFicha;
	}
*/
	static public final String inicializacionPacienteIntegral01(){
		
		String cadenaFinal = "";
		
		String seleccionaFichaPaciente = "var hola = principal.datos.ficha.cambiar('pPruebas');";
		
		String getFichaPersonal = ""
				+ "while(principal.datos.ficha.pruebas.document.readyState < 3){}"
				
				+ "var tablas = principal.datos.ficha.pruebas.document.getElementsByTagName('table');"
				+ "var celdas = tablas[2].getElementsByTagName('td');"
				+ "var cadena = '0@' + celdas[1].innerHTML + '@' + celdas[3].innerHTML + '@' + celdas[7].innerHTML ;"
				+ "var hola = window.clipboardData.setData('Text', cadena);"
				;
		
		cadenaFinal = seleccionaFichaPaciente + getFichaPersonal;
		
		return cadenaFinal;
	}
	
	static public final String inicializacionPacienteNodoHosp(String servicio){
		
		String cadenaFinal = "";
		
		String arbolCargado = "while(principal.datos.arbol.despliegue.document.readyState < 3){}";
		
		String nodoHosp = ""
		//	+ "alert(principal.datos.arbol.despliegue.document.readyState);"	
			+ "var nodo = principal.datos.arbol.despliegue.document.anchors;"
			+ "var numeroAncla = 0;"
			+ "for(var i=0;i<nodo.length;i++){"
				+ "if(nodo[i].innerHTML.indexOf('" + servicio + "') != -1){"
					+ "numeroAncla = i;"
					+ "break;"
				+ "}"
			+ "}"
			+ "nodo[numeroAncla].click();"
			+ "var cargado = 0"; 
		//	+ "alert(principal.datos.arbol.despliegue.document.readyState);"
			;

		
		cadenaFinal = arbolCargado + nodoHosp; //+ nodoHosp + nodoSeleccionadoHosp + fichaSeleccionada + fichaCargada + datosFichaHosp;
		
		return cadenaFinal;
	}
	
	static public final String inicializacionPacienteSetFichaHosp(){
		
		String cadenaFinal = "";
		
	
		String compruebaEstado = ""

						+ "var ficha = principal.datos.ficha.hosp_main.cambiar('pFicha');"

							;


		
		cadenaFinal = compruebaEstado; 
		
		return cadenaFinal;
	}
}


