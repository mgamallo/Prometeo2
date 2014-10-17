package es.mgamallo.prometeo;

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
			
			
			String ca = "<li><a href=\'command://user_" + numUsuario + "_" + Inicio.usuarios[i].alias + "\'>" +
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
				"javascript:var nhc = window.frames.principal.mainFrame.document.buscarPacienteForm.ID_NHC.value = " +
		        nhc + ";window.principal.mainFrame.buscar();";
		
		String aux = "window.frames['principal'].frames['mainFrame'].document.buscarPacienteForm.ID_NHC.value=1000;";
		
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

	static public final String buscarNodo01(String servicio){
		
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
	
}
