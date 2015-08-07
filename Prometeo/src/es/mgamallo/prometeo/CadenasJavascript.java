package es.mgamallo.prometeo;

import java.util.ArrayList;

import com.jacob.activeX.ActiveXComponent;

public class CadenasJavascript {
	
	protected static final String LS = System.getProperty("line.separator");
	
	static public final String putUsuario(Usuario usuario){
		
		String seleccionado = "";
		String clase = "";
		String tipoDocumentacion = "";
		
		if(usuario.urgencias){
			seleccionado = "urg";
			clase = "tile bg-cobalt bg-hover-lightGreen bd-yellow selected";
			tipoDocumentacion = "Urgencias".toUpperCase();
		}else{
			seleccionado = "doc";
			clase = "tile bg-green bg-hover-lightGreen bd-yellow selected";
			tipoDocumentacion = "Documentación".toUpperCase();
		}
		
		String cadena = "document.getElementById('usuario').innerHTML = '" + usuario.alias + "';" + LS
				+ "document.getElementById('imagenAvatar').src='images/" + usuario.imagen + ".jpg';" + LS
				+ "document.getElementById('avatarS').src='images/" + usuario.imagen + ".jpg';" + LS
				+ "document.getElementById('" + seleccionado + "').className='" + clase + "';" + LS
				+ "document.getElementById('tipoDocumentacion').innerHTML = '" + tipoDocumentacion + "';";
		
		return cadena;
	}
	
	static public String getListaNormas(){
	
		String cadena = "<div id='da-slider' class='da-slider'>"
				+ "";
		int tamaño = Inicio.listaNormasIanus.size();
		for(int i=0;i<tamaño;i++){
			
			int tam = Inicio.listaNormasIanus.get(i).servicios.size();
			String servi = "";
			for(int j=0;j<tam;j++){
				servi += Inicio.listaNormasIanus.get(i).servicios.get(j);
				if(j+1 == tam){
					servi += ".";
				}
				else{
					servi += ", ";
				}
			}
			
			cadena += ("<div class='da-slide'>"
					    + "<button onclick=\"editar('" + (i+1) + "')\" style='background-color: red; color: white; margin-left: 40px; margin-top: 400px'> Editar </button>"
						+ "<button onclick='modalOff()' style='background-color: teal; color: white;  margin-top: 400px; margin-left: 10px;'> Cerrar </button>"
						+ "<h2 style='color: maroon;'>" + servi + "</h2>"
						+ "<p>" + Inicio.listaNormasIanus.get(i).textoSinFormato + "</p>"
						+ "<br><br>"
						+ "<a href='#' class='da-link' ></a>"

						+ "<div class='da-img'><img src='" + Inicio.listaNormasIanus.get(i).rutaImagen +"' style='border:4px solid maroon;' /></div>"					
					+ "</div>"
				);
		}
			
		cadena +=  "<nav class='da-arrows'>"
						+ "<span class='da-arrows-prev'></span>"
						+ "<span class='da-arrows-next'></span>"
					+ "</nav>"
				+ "</div>";
		
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
		
			/*
			String ca = "<li><a id='" + numUsuario + "' onclick=\"modalOn('" + numUsuario + "');\" class='login-window' href=\'command://user_" + numUsuario + "_" + Inicio.usuarios[i].alias + "\'>" +
					"<img src=\'images/" + Inicio.usuarios[i].imagen + ".jpg\' width=\'200px\'/>" +
						"<div><span>" + Inicio.usuarios[i].alias + "</span></div>" +
				"</a></li>" + LS;
			*/
			
			String ca = "<li><a id='" + numUsuario + "' onclick=\"modalOn('" + numUsuario + "','" + Inicio.usuarios[i].alias + "','" + Inicio.usuarios[i].usuario + "');\" class='login-window' href='#'>" +
					"<img src=\'images/" + Inicio.usuarios[i].imagen + ".jpg\' width=\'200px\'/ height=\'155px\'>" +
						"<div><span>" + Inicio.usuarios[i].alias + "</span></div>" +
				"</a></li>" + LS;
			
			cadena = cadena + ca;
		}
		
		String ca = "<li><a id='99' onclick=\"modalOn('99','Nuevo Usuario','');\" class = 'login-window' href='#'>" +
				"<img src=\'images/99.jpg\' width=\'200px\'/>" +
					"<div><span> Nuevo usuario </span></div>" +
			"</a></li>" + LS;
		
		
		cadena = cadena + ca + "</ul>";
		
		return cadena;
		
	}
	
	static public String getCarpetasSubidas(ArrayList<Directorio> carpetas){
		
		String cadena = "";
		
		for(int i=0;i<carpetas.size();i++){
			String numero = String.valueOf(i);
			
			carpetas.get(i).asociado = true;
			
			String cadenaId = "carpeta_" + numero;
			
			cadena += ""
					+ "<a class='list' href='command://carpeta_" + numero + "' style='float:left; margin-right:20px; width:380px;'>"
				+ "<div class='list-content data' name='hola' style='background: red;' id='" + cadenaId + "'>" 
					+ "<span class='icon icon-file-pdf fg-white'></span>" 
					+ "<div class='fg-white'>" 
							+ "<span class='list-title'><strong>" + carpetas.get(i).servicio + "</strong></span>" 
							+ "<span class='list-remark'>Pdfs: " + carpetas.get(i).numeroPdfs + "</span>"
					+ "</div>"
					+ "<label class=''><input type='checkbox' disabled id='" + cadenaId + "c' checked style='float: right' /></label>"
			+ "</div>" 
		+ "</a>";
			
		}

		// cadena = "<a href='#' class='list'> hola </a>";
		
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
			
			if(carpetas.get(i).usuario.length() == 0){
				cadena += ""
						+ "<a href='command://carpeta_" + numero + "' class='tile ";
			}
			else{
				cadena += ""
						+ "<a href='#' class='tile ";
			}
			if(carpetas.get(i).numeroPdfs > 89){
				cadena += "double double-vertical ";
			}
			else if(carpetas.get(i).numeroPdfs >49 || Inicio.carpetaDudas){
				cadena += "double ";
			}
			else if(carpetas.get(i).numeroPdfs <20){
				cadena += "half ";
			}
			
			/*
			if(Inicio.carpetaDudas){
				cadena += "double ";
			}
			*/
			
			cadena += carpetas.get(i).color;
			
			if(carpetas.get(i).usuario.length() == 0){
				cadena += " bg-hover-lightGreen'";
			}
			else{
				cadena += " '";
			}
			
			if(carpetas.get(i).duda){
				cadena += " title='" + carpetas.get(i).contestacion + "'>";
			}
			else{
				cadena += " title='" +  carpetas.get(i).servicio + "'>";
			}
			
			
			cadena += // " bg-hover-lightGreen'>" + 
					    "<div class='tile-content'>" + 
					    	"<div class='padding10'>";
			
			if(carpetas.get(i).numeroPdfs >= 20 && !carpetas.get(i).duda){
				cadena += "" +
			    		"<h1 class='fg-white'></h1>" + 
			    		"<h2 class='fg-white center'>" + carpetas.get(i).servicio + "</h2>"
			    				+ "<div style='text-align: left'><h3 style='color: white'>#" + carpetas.get(i).numCarpeta + " " + carpetas.get(i).dia.toLowerCase() + "</h3></div>" +
			    	/*	"<span class='fg-white>" + carpetas.get(i).numCarpeta + "</span>" + */
			    	"</div>" + 
			    "</div>" + 
			    "<div class='brand'>" + 
			    	"<span class='label fg-white'><strong>" + carpetas.get(i).usuario + "</strong></span>" + 
			    	
			    	"<div class='badge fg-white'><strong>" /*<span class='label fg-white'style='text-align: left'>"+ carpetas.get(i).dia + "</span>" */ + carpetas.get(i).numeroPdfs + "</strong></div>" + 
			    "</div></a>"
			    + "";
			}
			else{
				if(carpetas.get(i).duda){
					cadena += "" +
					    	//	"<h1 class='fg-white'></h1>" + 
					    		"<h2 class='fg-white center'>" + carpetas.get(i).pregunta + "</h2>" +
					    		"<hr>" +
					    		"<h2 class='fg-white center'>" + carpetas.get(i).contestacion + "</h2>" +
					    	//	"<div style='text-align: left'><h3 style='color: white'>" + carpetas.get(i).numCarpeta +  "</h3></div>" +
					    	/*	"<span class='fg-white>" + carpetas.get(i).numCarpeta + "</span>" + */
					    	"</div>" + 
					    "</div>" + 
					    "<div class='brand'>" + 
					    	"<span class='label fg-white'><strong>" + carpetas.get(i).usuario + "</strong></span>" + 
					    	
					 //   	"<div class='badge fg-white'><strong>" /*<span class='label fg-white'style='text-align: left'>"+ carpetas.get(i).dia + "</span>" */ + carpetas.get(i).numeroPdfs + "</strong></div>" + 
					    "</div></a>"
					    + "";
				}
				else{
					cadena += "" +
					    	//	"<h1 class='fg-white'></h1>" + 
					    	//	"<h2 class='fg-white center'>" + carpetas.get(i).servicio + "</h2>" +
					    		"<div style='text-align: left'><h3 style='color: white'>#" + carpetas.get(i).numCarpeta +  "</h3></div>" +
					    	/*	"<span class='fg-white>" + carpetas.get(i).numCarpeta + "</span>" + */
					    	"</div>" + 
					    "</div>" + 
					    "<div class='brand'>" + 
					    	"<span class='label fg-white'><strong>" + carpetas.get(i).usuario + "</strong></span>" + 
					    	
					 //   	"<div class='badge fg-white'><strong>" /*<span class='label fg-white'style='text-align: left'>"+ carpetas.get(i).dia + "</span>" */ + carpetas.get(i).numeroPdfs + "</strong></div>" + 
					    "</div></a>"
					    + "";
				}
	
			}
		}
		
		System.out.println(cadena);
		
		return cadena;
	}

	static public final String introducirUsuario(Usuario usuario){
		
		System.out.println(usuario.alias);
		
		
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
	
	static public final String introducirNHC1(String nhc){
		String introducirNHCPulido = 
			"var framePrincipal = window.frames;" + LS +
			"var frameNHC = framePrincipal['principal'].frames['mainFrame'];" + LS +
			"var NHC = frameNHC.document.buscarPacienteForm.ID_NHC;" + LS +
	
			"NHC.value = " + nhc + ";"//  + LS +
	//		"frameNHC.buscar();"
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
		
		String introducirNHCfase1 = ""
				+ "javascript:window.frames.principal.mainFrame.document.buscarPacienteForm.ID_NHC.value = " 
				+ nhc 
				+ ";";
		
		String introducirConFuncion1 = ""
				+ ""
				+ "function introNHC(nhc){"
					+ "window.frames.principal.mainFrame.document.buscarPacienteForm.ID_NHC.value = nhc;"
					+ "window.principal.mainFrame.buscar();"
				+ "}"
				+ "";
		
		String introducirSoloNHC = ""
				+ "window.frames.principal.mainFrame.document.buscarPacienteForm.ID_NHC.value = " + nhc + ";"
				+ "";
		
		String introducirEnUnPaso = ""
				+ "window.frames.principal.mainFrame.document.buscarPacienteForm.ID_NHC.value = " + nhc + ";"
			//	+ "var anclas = window.frames.principal.mainFrame.document.anchors;"
			//	+ "setTimeout(function(){ window.frames.principal.mainFrame.document.anchors[0].click();alert('hola');}, 100);"
			
				+ "var anclas = window.frames.principal.mainFrame.document.anchors;anclas[0].click();"
				+ "";
		
		// return introducirNHCresumido;
		return "javascript: " + introducirEnUnPaso;
	}

	
	static public final String introducirNHC2(String nhc){
		
		String introducirNHCfase2 = 
				"javascript:window.principal.mainFrame.buscar();"
						;
		
		String introducirConFuncion2 = "javascript:introNHC(" + nhc + ")";
		
		String introducirNHCfase2b = 
				"javascript:window.frames.principal.mainFrame.document.anchors;hola[0].click();";
		
		return introducirNHCfase2b;
	}
	
	static public final String reloadNhc01(){
		String reload1 = ""
				+ ""
				+ "var pag = principal.mainFrame;"
				+ "pag.location.reload();"
				+ "";
		
		return reload1;
	}
	
	static public final String reloadNhc02(){
		String reload2 = ""
				+ "pag.busquedaAvanzada();"
				+ "";
		
		String reload2a = ""
				+ "var hola = principal.mainFrame.document.anchors;"
				+ "hola[1].click();"
				+ "";
		
		return reload2a;
	}
	
	static public final String reloadNhc03(){
		String reload3 = ""
				+ "principal.botonera.inicio();"
				+ "";
		
		return reload3;
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
				"var tipoSubida = '';" +
				"var rutas = [];" +
				"rutas[0] = principal.datos.ficha.hosp_botonera;" +
				"rutas[1] = principal.datos.ficha.inf_botonera;" +
				"rutas[2] = principal.datos.ficha.cex_botonera;" +
				"rutas[3] = principal.datos.ficha.menu;" +
				"rutas[4] = principal.datos.ficha.pro_botonera;"
				+ "var rutaSelec = 0;"
				+ "var servicio = false;" +
				
				"for(var i=0;i<5;i++){" +
					"if(!(rutas[i] === undefined)){" +
						"tipoSubida = 'HOS';" +
						"if(i==4){" +
							"tipoSubida='CEX';" +
						"}" +
						"if(i==3){ " +
							"if(principal.datos.ficha.menu.document.anchors.length === 3){" +
								"tipoSubida='CEX';" +
								"var hola = window.clipboardData.setData('Text', tipoSubida);" +
								"rutaSelec = 3;" +
								"servicio = true;" +
								"rutas[3].parent.parent.arbol.despliegue.crearDocExtServicioCEX();break;" +
							"}" + 
							"tipoSubida='URG';" +
							"var hola = window.clipboardData.setData('Text', tipoSubida);"
							+ "rutaSelec = i;" 
							+ "rutas[i].asociarDocumento(); break;" +
						"}" +
						"if(i==2){" +
						   "tipoSubida='EPI';"  +
						"}"	+
						"if(i==1){" +
							"if(!(rutas[i].pulsadoQuirofano === undefined)){" +
								"tipoSubida='QUI';" + 
							"}else{" +
								"tipoSubida='CIA';" +
							"}" +
						"}"	+

						"var hola = window.clipboardData.setData('Text', tipoSubida);"
						+ "rutaSelec = i;"
						+ "rutas[i].asociarDocumento(); break;" +
					"}" +
						
				"}"		
				
				;
		return botonAsociar;
	}

	static public final String pulsarVersionar(){
		final String botonVersionar = 
				"javascript:" +
				"principal.datos.ficha.docext_botonera.versionar();" +
				""
				
				;
		return botonVersionar;
	}

	
	static public final String buscarNodoUrgInicial(String fecha){
		
		final String cadena = 
			  	"var nodo = principal.datos.arbol.despliegue.document.anchors;"
				+ "var anclaPadre = 0;"
				+ "var anclaHijo = 0;"
				+ "var numeroAncla = 0;"
				+ "var nodoHosp = 0;"
				+ "var nodoUrg = 0;"
				+ "var urgDentro = false;"
				+ "var anoI = '0';"
				+ "var mesI = '0';"
				+ "var diaI = '0';"
				+ "var anoU = '0';"
				+ "var mesU = '0';"
				+ "var diaU = '0';"
				+ "for(var i=0;i<nodo.length;i++){"
					+ "if(nodoHosp == 0 && (nodo[i].innerHTML.indexOf('" + InicioIanus.HOSP_JACOB + "') != -1)){"
							+ "var cadena = nodo[i].innerHTML;"
							+ "if(cadena.indexOf('Ingres') == -1){"
										+ "nodoHosp = i;"
										+ "var barra1 = cadena.indexOf('/');"
										+ "var barra2 = cadena.lastIndexOf('/');"
										+ "diaI = cadena.slice(barra1-2,barra1);"

										+ "mesI = cadena.slice(barra1+1,barra2);"
										+ "anoI = cadena.slice(barra2+1,barra2+5);"
									
										+ "if(diaI.charAt(0).localeCompare('0') == 0){"
											+ "diaI = diaI.slice(1);"
										+ "}"
										+ "if(mesI.charAt(0).localeCompare('0') == 0){"
											+ "mesI = mesI.slice(1);"
										+ "}"
										+ "diaI = parseInt(diaI);"
										+ "mesI = parseInt(mesI);"
										+ "anoI = parseInt(anoI);"
							+ "}"
					+ "}"
					+ "if(nodo[i].innerHTML.indexOf('" + InicioIanus.URG_JACOB + "') != -1){"
						+ "numeroAncla = i;"
						+ ""
						+ "var cadena = nodo[i].innerHTML;"
						+ "var barra1 = cadena.indexOf('/');"
						+ "var barra2 = cadena.lastIndexOf('/');"
						+ "diaU = cadena.slice(barra1-2,barra1);"
						+ "mesU = cadena.slice(barra1+1,barra2);"
						+ "anoU = cadena.slice(barra2+1,barra2+5);"
						
	
						+ "if(diaU.charAt(0).localeCompare('0') == 0){"
							+ "diaU = diaU.slice(1);"
						+ "}"
						+ "if(mesU.charAt(0).localeCompare('0') == 0){"
							+ "mesU = mesU.slice(1);"
						+ "}"		
							
						+ "diaU = parseInt(diaU);"
						+ "mesU = parseInt(mesU);"
						+ "anoU = parseInt(anoU);"
						+ "break;"
					+ "}"
				+ "}"
				+ "if(numeroAncla != 0){"
					+ "if(anoI > anoU){"
				//		+ "alert(anoI);"
						+ "urgDentro = true;"
						+ "anclaPadre = nodoHosp;"
					//	+ "alert(anoI + ' > ' + anoU);"
						+ "nodo[nodoHosp].click();"
					+ "}"
					+ "else if(anoI == anoU){"
				//		+ "alert(anoI);"
						+ "if(mesI > mesU){"
				//			+ "alert(mesI);"
							+ "urgDentro = true;"
							+ "anclaPadre = nodoHosp;"
							+ "nodo[nodoHosp].click();"
						+ "}"
						+ "else if(mesI == mesU){"
							+ "if(diaI > diaU){"
								+ "urgDentro = true;"
								+ "anclaPadre = nodoHosp;"
								+ "nodo[nodoHosp].click();"
							+ "}"
							+ "else{"
								+ "anclaPadre = numeroAncla;"
								+ "nodoUrg=numeroAncla;"
								+ "nodo[numeroAncla].click();"
							+ "}"
						+ "}"
						+ "else{"
							+ "anclaPadre = numeroAncla;"
							+ "nodoUrg=numeroAncla;"
							+ "nodo[numeroAncla].click();" 
						+ "}"
					+ "}"
					+ "else{"
			//			+ "alert(anoI);"
						+ "anclaPadre = numeroAncla;"
						+ "nodoUrg=numeroAncla;"
						+ "nodo[numeroAncla].click();" 
					+ "}"
				+ "}else if(nodoHosp !=0){"
					+ "urgDentro = true;"
					+ "anclaPadre = nodoHosp;"
					+ "nodo[nodoHosp].click();"
				+ "}"	
			//	+ "alert(anoI + ' ' + anoU + ' ' + mesI + ' ' + mesU + ' ' + diaI + ' ' + diaU);"
				;
		
		
		return cadena;
	}
	
	static public final String buscarNodoUrgIngreso(String fecha){
		
		final String cadena = ""
			  	+ "if(urgDentro){"
			  		+ "var nodo = principal.datos.arbol.despliegue.document.anchors;"
					+ "for(var i=0;i<nodo.length;i++){"
						+ "if(nodo[i].innerHTML.indexOf('" + InicioIanus.URG_JACOB + "') != -1){"
							+ "numeroAncla = i;"
							+ "anclaPadre = numeroAncla;"
							+ "nodo[anclaPadre].click();" 
							+ "break;"
						+ "}"
					+ "}"
				+ "}"
					
				
				;
		
		
		return cadena;
	}
	
	static public final String buscarNodoConsultasInicial(String servicio){
		
		
		final String cadena = 
				  	"var nodo = principal.datos.arbol.despliegue.document.anchors;"
					+ "var anclaPadre = 0;"
					+ "var anclaHijo = 0;"
					+ "var numeroAncla = 0;"
					+ "var nodoCEX = 0;"
					+ "for(var i=0;i<nodo.length;i++){"
						+ "if(nodo[i].innerHTML.indexOf('externas') != -1){"
							+ "nodoCEX = i;"
						+ "}"
						+ "if(nodo[i].innerHTML.indexOf('" + servicio + "') == 6){"
							+ "numeroAncla = i;"
							+ "break;"
						+ "}"
					+ "}"
					+ "if(numeroAncla != 0){"
						+ "anclaPadre = numeroAncla;"
						+ "nodo[numeroAncla].click();" 
					+ "}else if(nodoCEX !=0){"
						+ "nodo[nodoCEX].click();"
					+ "}"	
					
					;
		
		return cadena;
	}
	
	static public final String buscarNodoConsHijo(String clave){
		String cadena = ""
				+ "if(anclaHijo != 0){"
					+ "nodo[anclaHijo].click();"
				+ "}else{"		
					+ "numeroAncla = 0;"
					+ "var nodo=principal.datos.arbol.despliegue.document.anchors;"
					+ "for(var i=0;i<nodo.length;i++){"
						+ "if(nodo[i].innerHTML.toLowerCase().indexOf('" + clave + "') != -1){"
								+ "numeroAncla = i;"
								+ "break;"
						+ "}"
					+ "}"
					+ "if(numeroAncla != 0){"
						+ "anclaHijo = numeroAncla;"
						+ "nodo[numeroAncla].click();"
					+ "}"
					+ "else{"
						+ "nodo[anclaPadre].click();"
					+ "}"
				+ "}"
				;
				
		
		return cadena;
	}
	
	static public final String pulsaNodoPadre(){
		String cadena = ""

					+ "nodo[anclaPadre].click();"
					;
		
		
		return cadena;		
	}
	
	static public final String creaNodoCEX(String servicio){
		
		String siglasServicio = Inicio.documento[Inicio.indiceArchivoSelecc].servicio;
		
		String cadena = ""
				+ "if(nodoCEX != 0){"
					+ "var serv = principal.datos.ficha.main.servicios;"
					+ "serv.crearServicioCEX('" + siglasServicio + "','" + servicio + "');"
				+ "}";
		
		return cadena;
	}


	static public final String buscarNodoCiaInicial(String servicio){
		
		String servicioH = servicio;

		
		final String nodo = 

				"var nodo = principal.datos.arbol.despliegue.document.anchors;"
				+ "var anclaPadre = 0;"
				+ "var anclaHijo = 0;"
				+ "var numeroAncla = 0;"
				+ "var nodoIngreso = 0;"
				+ "var p = 0;"
				+ "for(var i=0;i<nodo.length;i++){"
					+ "if(anclaPadre == 0 && (nodo[i].innerHTML.indexOf('" + InicioIanus.HOSP_JACOB + "') != -1)){"
							+ "nodoIngreso = i;"
							+ "anclaPadre = i;"
					+ "}"
					+ "if(p == 0 && nodo[i].innerHTML.indexOf('" + servicioH + "') != -1){"
						+ "numeroAncla = i;"
						+ "p = p + 1;"
					+ "}"
					+ "if(nodo[i].innerHTML.indexOf('rogramad') != -1){"
						+ "numeroAncla = i;"
						+ "break;"
					+ "}" 
				+ "}"
				+ "if(numeroAncla != 0){"
					+ "anclaPadre = numeroAncla;"
					+ "nodoIngreso = 0;"
				+ "}"
				+ "nodo[anclaPadre].click();" 
	
				;
		
		return nodo;
	}
	
	
	static public final String buscarNodoCiaIngreso(String servicio){
		
		String servicioH = servicio;

		
		final String nodo = 

				""
				+ "if(anclaHijo != 0){"
	//				+ "alert('tiene anclaHijo');"
					+ "nodo[anclaHijo].click();"
				+ "}"
				+ "else{"
		//			+ "alert('no tiene anclaHijo');"
					+ "numeroAncla = 0;"
					+ "var nodo = principal.datos.arbol.despliegue.document.anchors;"
					+ "for(var i=0;i<nodo.length;i++){"
						+ "if(nodo[i].innerHTML.indexOf('" + "QUI:" + "') != -1){"
							+ "numeroAncla = i;"
							+ "break;"
						+ "}"
					+ "}"
					+ "if(numeroAncla != 0){"
						+ "anclaHijo = numeroAncla;"
						+ "nodo[anclaHijo].click();"
					+ "}else{"
						+ "nodo[anclaPadre].click();"
					+ "}"
					
				+ "}"


	
				;
		
		return nodo;
	}
	
	
	static public final String seleccionarFichaHosp(){
		
		return  "principal.datos.ficha.hosp_main.cambiar('pFicha');alert('ficha abierta')";
	}
	
	static public final String obtieneDatosFichaHosp(){
		/*
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
			*/
	
		
		String datosFichaSimple = ""
				+ "var tablas = principal.datos.ficha.hosp_main.episodio.document.getElementsByTagName('table');"
				+ "var celdas = tablas[6].getElementsByTagName('td');"
				+ "var fechaIngreso = celdas[5].innerHTML;"
				+ "var horaIngreso = '1@' + fechaIngreso + '@' + celdas[7].innerHTML;"

				+ "var hola = window.clipboardData.setData('Text', horaIngreso);"
				;
			//	+ "alert(cadena);";
		
		/*
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
		*/

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
		    + "var anclaPadre = 0;"
			+ "var anclaHijo = 0;"
			+ "var numeroAncla = 0;"
			+ "for(var i=0;i<nodo.length;i++){"
				+ "if(nodo[i].innerHTML.indexOf('" + servicio + "') != -1){"
					+ "if(nodo[i].innerHTML.indexOf('Ingresado') == -1){"
						+ "var noSergas = nodo[i].innerHTML;"
						+ "var ultimaLetra = noSergas.charAt(noSergas.length-1);"
						+ "if(ultimaLetra == 'M' || ultimaLetra == 'H' || ultimaLetra == 'P'){"
							+ "numeroAncla = i;"
							+ "break;"
						+ "}"
					+ "}"
				+ "}"
				+ "if(nodo[i].innerHTML.indexOf('" + InicioIanus.CIA_JACOB + "') != -1){"
					+ "numeroAncla = i;"
					+ "break;"
				+ "}"
			+ "}"
			+ "anclaPadre = numeroAncla;"
			+ "nodo[numeroAncla].click();"
			+ "var cargado = 0"; 
		//	+ "alert(principal.datos.arbol.despliegue.document.readyState);"
			;

		
		cadenaFinal = arbolCargado + nodoHosp; //+ nodoHosp + nodoSeleccionadoHosp + fichaSeleccionada + fichaCargada + datosFichaHosp;
		
		return cadenaFinal;
	}
	
	static public final String buscarNodoHospHijo(String clave){
		String cadena = ""
				+ "if(anclaHijo != 0){"
					+ "nodo[anclaHijo].click();"
				+ "}else{"
					+ "numeroAncla = 0;"
					+ "var nodo=principal.datos.arbol.despliegue.document.anchors;"
					+ "for(var i=0;i<nodo.length;i++){"
						+ "if(nodo[i].innerHTML.indexOf('" + clave + "') != -1){"
								+ "numeroAncla = i;"
								+ "break;"
						+ "}"
						+ "if(nodo[i].innerHTML.indexOf('" + InicioIanus.CIA_JACOB + "') != -1){"
								+ "numeroAncla = i;"
								+ "break;"
						+ "}"
					+ "}"
					+ "if(numeroAncla != 0){"
						+ "anclaHijo = numeroAncla;"
						+ "nodo[numeroAncla].click();"
					+ "}"
					+ "else{"
						+ "nodo[anclaPadre].click();"
					+ "}"
				+ "}"
				;
				
		
		return cadena;
	}
	
	static public final String inicializacionPacienteSetFichaHosp(){
		
		String cadenaFinal = "";
		
	
		String compruebaEstado = ""

						+ "var ficha = principal.datos.ficha.hosp_main.cambiar('pFicha');"

							;


		
		cadenaFinal = compruebaEstado; 
		
		return cadenaFinal;
	}
	
	static public final String inicializacionPacienteSetFichaUrg(){
		
		String cadenaFinal = ""

						+ "var ficha = principal.datos.ficha.main.cambiar('pFicha');";
		
		return cadenaFinal;
	}
	
	
	
	static public final String resaltarFechaIngreso01(){
		
		String cadena = ""
		+ "function colores(){"
			+ "var tablas = principal.datos.ficha.hosp_main.episodio.document.getElementsByTagName('table');"
			+ "var celdas = tablas[6].getElementsByTagName('td');"
			+ "celdas[5].style.fontSize = '34pt';"
			+ "celdas[7].style.fontSize = '34pt';"
			+ "celdas[5].style.color = '#ff0000';"
			+ "celdas[7].style.color = '#ff0000';"
			+ "celdas[5].style.fontWeight = 'bold';"
			+ "celdas[7].style.fontWeight = 'bold';"
			+ "celdas[5].style.border = 'thin solid #000000';"
			+ "celdas[7].style.border = 'thin solid #000000';"
			+ "celdas[5].style.background = '#ffff00';"
			+ "celdas[7].style.background = '#ffff00';"
			+ "var ficha = principal.datos.ficha.hosp_main.episodio.document.getElementsByTagName('body');ficha[0].style.background='#94bfbf';"
		+ "}"
	//		+ "setTimeout(function(){colores();},2000);"
	//		+ "alert('vivan los colores');"			
			;
		
		return cadena;
	}
	
	static public final String resaltarFechaIngreso02(){
		
		String cadena = ""
				+ "colores();"

	//		+ "setTimeout(function(){colores();},2000);"
	//		+ "alert('vivan los colores');"			
			;
		
		return cadena;
	}
	
	
	static public final String resaltarFechaUrg01(){
		
		String cadena = ""
		+ "function coloresUrg(){"
		+ "var tablas = principal.datos.ficha.main.myFrame.document.getElementsByTagName('table');"
			+ "var celdas = tablas[6].getElementsByTagName('td');"
			+ "celdas[5].style.fontSize = '34pt';"
			+ "celdas[7].style.fontSize = '34pt';"
			+ "celdas[5].style.color = '#ff0000';"
			+ "celdas[7].style.color = '#ff0000';"
			+ "celdas[5].style.fontWeight = 'bold';"
			+ "celdas[7].style.fontWeight = 'bold';"
			+ "celdas[5].style.border = 'thin solid #000000';"
			+ "celdas[7].style.border = 'thin solid #000000';"
			+ "celdas[5].style.background = '#ffff00';"
			+ "celdas[7].style.background = '#ffff00';"
			+ "var ficha = principal.datos.ficha.main.myFrame.document.getElementsByTagName('body');ficha[0].style.background='#94bfbf';"
		+ "}"
	//		+ "setTimeout(function(){colores();},2000);"
	//		+ "alert('vivan los colores');"			
			;
		
		return cadena;
	}
	
	static public final String resaltarFechaUrg02(){
		
		String cadena = ""
				+ "coloresUrg();"

	//		+ "setTimeout(function(){colores();},2000);"
	//		+ "alert('vivan los colores');"			
			;
		
		return cadena;
	}
	
	
	
	static public final String cambiaFondoConstultas01(){
		String cadena = ""
				+ "function colorFondo(){"
			+ "var fondo = principal.datos.ficha.main.document.getElementsByTagName('td');"
			+ "fondo[0].style.background = '#94bfbf';"
			+ "var pie = principal.document.getElementsByTagName('body')[0].style.background = '#94bfbf';"
			+ "}"
				
				;
		
		return cadena;
	}
	
	static public final String cambiaFondoConstultas02(){
		String cadena = ""
				+ "colorFondo();"

				;
		
		return cadena;
	}
	
	
	static public final String forzarNodo(String idEpi){
		
		
		
		final String cadena = 
				"javascript:"
				+ "var anclaPadre = 0;" +
					"for(var i=0;i<nodo.length;i++){"
						+ "if(nodo[i].href.indexOf('" + idEpi + "') != -1){"
							+ "anclaPadre = i;"
							+ "break;"
						+ "}"
				+ "};"
				+ ""
				+ "alert('Nodo fijado');"
				
				 ;
		
		return cadena;
	}

	
	static public final String buscarIdentificacionEpisodio(){
		
		final String cadena = 
				"javascript:" +
				"var tipoSubida = '';" +
				"var rutas = [];" +
				"rutas[0] = principal.datos.ficha.hosp_botonera;" +
				"rutas[1] = principal.datos.ficha.inf_botonera;" +
				"rutas[2] = principal.datos.ficha.cex_botonera;" +
				"rutas[3] = principal.datos.ficha.menu;" +
				"rutas[4] = principal.datos.ficha.pro_botonera;" +
				
				"for(var i=0;i<5;i++){" +
					"if(!(rutas[i] === undefined)){" +
						"var cabeza = rutas[i].document.getElementsByTagName('head')[0].innerHTML;" +
						"var hola = window.clipboardData.setData('Text', cabeza);" +
						"break;" +
					"}" +
						
				"}"		
				
				;
		

		
		return cadena;

		
		
	}
}



