package es.mgamallo.prometeo;

import java.util.Calendar;

public class CadenasJavascriptXedoc {

	static public final String introUsuario0(Usuario usuario){
		
		String cadena = ""
				+ "function cargar(){document.getElementById('loginForm').submit();}"
				+ "var login = document.getElementById('j_username');"
				+ "var password = document.getElementById('j_password');"
				+ "var centro = document.getElementById('j_entorno');"
				+ "login.value= '" + usuario.usuario + "';"
				+ "password.value= '" + usuario.password + "';"
				+ "centro.value='HC_CHOPO';"
		//		+ "setTimeout(cargar(),2000);"
		//	+ "alert('hola');"
		//		+ "document.getElementById('loginForm').submit();"
				+ "";
		
		
		String cadena2 = ""
				+ "function cargar(){"
					+ "var centro = document.getElementById('j_entorno');"
					+ "centro.value='HC_CHOPO';"
					+ "var login = document.getElementById('j_username');"
					+ "var password = document.getElementById('j_password');"
					+ "login.value= '" + usuario.usuario + "';"
					+ "password.value= '" + usuario.password + "';"
				+"}"

		//		+ "setTimeout(cargar(),2000);"
		//	+ "alert('hola');"
		//		+ "document.getElementById('loginForm').submit();"
				+ "";
		
		
		return cadena2;
	}
	
	
	static public final String introUsuario1(Usuario usuario){
		
		
		String cadena2 = ""
				+ "cargar();"
				+ ""
				+ "var botonM = document.getElementsByTagName('input');"
				+ "for(var i=0;i<botonM.length;i++){"
					+ "if(botonM[i].name == 'login'){"
						+ "botonM[i].click();"
					+ "};"
				+ "}"


		//		+ "setTimeout(cargar(),2000);"
		//	+ "alert('hola');"
		//		+ "document.getElementById('loginForm').submit();"
				+ "";
		
		
		return cadena2;
	}
	

	
	static public final String selectMiBandeja(){
		
		String miBandeja = "Mi bandeja";
		
		String cadena = ""
				+ "var anclas = document.getElementsByTagName('a');"
				+ "for(var n = 0;n<anclas.length;n++){"
					+ "if(anclas[n].innerHTML.indexOf('" + miBandeja + "') != -1){"
						// + "alert(hola[n].innerHTML);"
						+ "anclas[n].click();"
						+ "break;"
					+ "}"
				+ "}"
				+ "";
		
		return cadena;
	}
	
	static public final String getNHC(){
		
		String cadena = ""
				+ ""
				+ "";
		
		return cadena;
	}
	

	
	static public final String contexto(){
		String cadena = ""
				+ "document.getElementById('contextoMenuSuperior').click();"
				+ "var nhc = document.getElementById('{hc}numeroHC');"
				+ "nhc.value='52378';"
				+ "var fechaI = document.getElementById('FechaIni');"
				+ "var fechaF = document.getElementById('FechaFin');"
				+ "fechaI.value = '01/01/2015';"
				+ "fechaF.value = '22/02/2015';"
			//	+ "alert('hola');"
				+ "document.getElementById('submitFormContexto').click();"
				+ "";
		
		return cadena;
	}
	
	static public final String rellenaContexto(){
		String cadena = ""
            
				+ "cambiarContexto('52378-360340');"
				
				+ "";
		
		return cadena;
	}
	
	static public final String pruebaTabla(){
		
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

		calendario.add(Calendar.DAY_OF_MONTH,-45);
		
		diaHaceUnMes = calendario.get(Calendar.DAY_OF_MONTH);
		mesHaceUnMes = calendario.get(Calendar.MONTH) + 1;
		añoHaceUnMes = calendario.get(Calendar.YEAR);
		
		fechaInicio = diaHaceUnMes + "/" + mesHaceUnMes + "/" + añoHaceUnMes;
		
		String cadena = ""
				+ ""
				+ "var nhc;"
				+ "var filaSeleccionada;"
				+ ""
				+ "function getNHC(nodo){"
					+ "var cadena = nodo.innerHTML;"
					+ "var campos = cadena.split(' @');"
					+ "campos[3] = campos[3].replace('r_f.pdf','');"
				//	+ "alert(campos[1] + '\\n' + campos[2] + '\\n' + campos[3]);"
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
				+ "alert(filas);"
				+ "";
		return cadena;
	}
	
	
	static public final String inicio1Xedoc(){
		String cadena = ""
			//	+ "alert('hola');"
				+ "var filas = document.getElementsByTagName('tr');"
				+ "var ancla = filas[1].getElementsByTagName('a');"
				+ "ancla[0].target = '_blank';"
			//	+ "alert(ancla[0].innerHTML);"
				+ "ancla[0].click();"
				+ "";
		
		return cadena;
	}
	
	
	static public final String cargaPdf(){
		String cadena = "";
		
		return cadena;
	}
	
	
	static public final String maquetado1(){
		String cadena = ""
				+ "document.getElementById('entornoLogin').style.display='none';"
				+ "document.getElementById('branding').style.display='none';"
				+ "document.getElementById('header').style.height='0px';"
				+ "alert('Maquetando');"
				+ "";
		
		return cadena;
	}
	
	
	static public final String maquetado11(){
		String cadena = ""
				+ ""
				+ "var columnaD = document.getElementById('columnaDerechaEdicion');"
				+ "columnaD.style.float='left';"
				+ "columnaD.style.marginLeft='0px';"
				+ "columnaD.style.width='1000px';"
				+ ""
				+ "alert('hola');";
		
		return cadena;
	}
	
	
	static public final String zoomPdf2(){
		String cadena = ""
				+ "document.getElementById('entornoLogin').style.display='none';"
				+ "document.getElementById('branding').style.display='none';"
				+ "document.getElementById('header').style.height='0px';"
				+ ""
				+ "var columnaI = document.getElementById('columnaIzquierdaEdicion');"
		//		+ "columnaI.style.float='left';"
				+ "columnaI.style.width='800px';"
				+ "columnaI.style.height='1200px';"
				+ "var completePreview = document.getElementById('completePreview');"
				+ "completePreview.style.width='800px';"
				+ "completePreview.style.height='1200px';"
				+ ""
				+ "var previewer = document.getElementById('previewer');"
				+ "previewer.style.width='800px';"
				+ "previewer.style.height='1200px';"
				+ ""
				+ "var columnaD = document.getElementById('columnaDerechaEdicion');"
				+ "columnaD.style.float='left';"
				+ "columnaD.style.marginLeft='800px';"
				+ "columnaD.style.width='1000px';"
				+ ""
				+ "var fondo = document.getElementById('page').style.background='#10324c';"
				+ ""
				+ "var tablaAtributos = document.getElementById('tablaAtributos');"
				+ "tablaAtributos.style.border='none';"
				+ "tablaAtributos.style.background='#9db7cc';"
				+ "tablaAtributos.style.color='#000000';"
				+ ""
				+ "var edicionForm = document.getElementById('edicionForm');"
				+ "edicionForm.style.float='left';"
				+ "edicionForm.style.width='1000px';"
				+ "var tablaElementosAjax = document.getElementById('tablaElementosAjax');"
				+ "tablaElementosAjax.style.float='left';"
				+ "tablaElementosAjax.style.width='450px';"
				+ "tablaElementosAjax.style.height='1000px';"
				+ ""
				+ "var tablaMeritos = document.getElementById('tablaMeritos');"
				+ "tablaMeritos.style.minWidth='400px';"
				+ "tablaMeritos.style.height='1000px';"
				+ ""
				+ "tablaMeritos.style.background='#9db7cc';"
				+ "tablaMeritos.style.border='none';"
				+ ""
				+ "var arbol = document.getElementById('arbol');"
				+ "arbol.style.height='1000px';"
				+ "" 
				+ "var tablaAtributosAjax = document.getElementById('tablaAtributosAjax');"
			//	+ "tablaAtributosAjax.style.minWidth='450px';"
				+ "tablaAtributosAjax.style.float='left';"
				+ "tablaAtributosAjax.style.marginLeft='470px';"
				+ "tablaAtributosAjax.style.marginTop='-970px';"
				+ ""

				+ "var tablaAtributos = document.getElementById('tablaAtributos');"
				+ "tablaAtributos.style.minWidth='450px';"
				+ "tablaAtributos.style.width='500px';"
				+ ""
				+ "tablaAtributos.style.border='none';"
				+ "tablaAtributos.style.background='#9db7cc';"
				+ "tablaAtributos.style.color='#000000';"
				+ ""

				+ "alert('adios');";

		
		return cadena;
	}
	
	static public final String zoomPdf3(){
		String cadena = ""
				+ ""
				+ "var tablaDocumento = document.getElementById('tablaDocumento');"
				+ "tablaDocumento.style.minWidth='450px';"
				+ "tablaDocumento.style.width='500px';"
				+ ""
				+ "var contextoMenuSuperior = document.getElementById('contextoMenuSuperior');"
				+ "contextoMenuSuperior.style.marginRight='400px';"
				+ "var loadContexto = document.getElementById('loadContexto');"
				+ "loadContexto.style.width='600px';"
				+ ""
				+ "var comprimirA = document.getElementById('selectDisplayButtonsTree');"
				+ "comprimirA.style.left = '1200px';"
				
				+ "var comprimirM = document.getElementById('selectDisplayButtonsAtributos');"
				+ "comprimirM.style.left = '1200px';"
				+ ""
				+ "var nombrePaciente = document.getElementById('loadContexto');"
				+ "nombrePaciente.style.marginLeft='-800px';"
				+ "nombrePaciente.style.color= 'yellow';"
				+ "nombrePaciente.style.fontSize = '25px';"
				
				+ "var fecha = document.getElementById('{hc}dataVersion-{hc}docExt');"
				+ "fecha.style.backgroundColor='rgb(253,247,133)';"

				
				+ "var buscando = document.querySelectorAll('.custom-combobox-input');"
				+ "buscando[1].style.backgroundColor = 'rgb(253,247,133)';"
				+ "buscando[2].style.backgroundColor = 'rgb(253,247,133)';"
				+ ""
				+ "var chapuza = document.all('nuevaSeccionEdicion');"
				+ "alert(chapuza.length);"
				+ ""
				+ "var ocultar = document.getElementById('nuevaSeccionEdicion');"
				+ "ocultar.style.display='none';"
				+ ""
			
				
				+ ""
				+ "var siguiente=document.getElementById('siguiente');"
				+ "siguiente.style.color = 'yellow';"
				+ "var combobox = document.querySelectorAll('.custom-combobox');" 
				+ "var comboboxinput = document.querySelectorAll('.custom-combobox-input');"
				+ "var comboboxtoggle = document.querySelectorAll('.custom-combobox-toggle');"
				+ "alert(combobox.length);"

				
				+ "for(var z=0;z<combobox.length;z++){"
					+ "combobox[2].style.width='300px';"
					+ "comboboxinput[2].style.width='300px';"
					+ "comboboxinput[2].style.backgroundColor= 'rgb(100,100,100)';"
					+ "combobox[2].style.display='block';"
				//	+ "comboboxtoggle[i].style.right='initial';"
				+ "}"
				/*
						+ "var combos0 = document.getElementsByClassName('custom-combobox');"
						+ "var combos1 = document.getElementsByClassName('custom-combobox-input');"
						+ "var combos2 = document.getElementsByClassName('custom-combobox-toggle');"
						+ "alert(combos0.length);"
						+ "alert(combos1.length);"
						+ "alert(combos2.lenght);"
						+ "for(var z=0;z<combos0.length;z++){"
							+ "combos0[z].style.width='300px';"
							+ "combos1[z].style.width='300px';"
							+ "combos2[z].style.right='initial';"
						+ "}"			
				*/	
				+ "alert('Ommmm...');"
				+ "";
		
		return cadena;
	}
	
	
	static public final String zoomPdf(){
		String cadena = ""
				+ "document.getElementById('entornoLogin').style.display='none';"
				+ "document.getElementById('branding').style.display='none';"
				+ "document.getElementById('header').style.height='0px';"
				+ ""
				+ "var columnaI = document.getElementById('columnaIzquierdaEdicion');"
		//		+ "columnaI.style.float='left';"
				+ "columnaI.style.width='800px';"
				+ "columnaI.style.height='1200px';"
				+ "var completePreview = document.getElementById('completePreview');"
				+ "completePreview.style.width='800px';"
				+ "completePreview.style.height='1200px';"
				+ ""
				+ "var previewer = document.getElementById('previewer');"
				+ "previewer.style.width='800px';"
				+ "previewer.style.height='1200px';"
				+ ""
				+ "var columnaD = document.getElementById('columnaDerechaEdicion');"
				+ "columnaD.style.marginLeft='1000px';"
				
				+ "var fondo = document.getElementById('page').style.background='#10324c';"
				
				+ "var tablaAtributos = document.getElementById('tablaAtributos');"
				+ "tablaAtributos.style.border='none';"
				+ "tablaAtributos.style.background='#9db7cc';"
				+ "tablaAtributos.style.color='#000000';"
				
				+ "var tablaElementosAjax = document.getElementById('tablaElementosAjax');"
		//		+ "tablaElementosAjax.style.background='beige';"
				+ "tablaElementosAjax.style.background='#9db7cc';"
		
				+ "var tablaMeritos = document.getElementById('tablaMeritos');"
		//		+ "tablaMeritos.style.background='#9db7cc';"
				+ "tablaMeritos.style.border='none';"
				
				+ "var tablaDocumento = document.getElementById('tablaDocumento');"
		//		+ "tablaElementosAjax.style.background='beige';"
				+ "tablaDocumento.style.background='#9db7cc';"
				+ "tablaDocumento.style.border='none';"

				+ "var comprimirA = document.getElementById('selectDisplayButtonsTree');"
				+ "comprimirA.style.left = '1200px';"
				
				+ "var comprimirM = document.getElementById('selectDisplayButtonsAtributos');"
				+ "comprimirM.style.left = '1200px';"
				+ ""
				+ "var nombrePaciente = document.getElementById('loadContexto');"
				+ "nombrePaciente.style.marginLeft='-800px';"
				+ "nombrePaciente.style.color= 'yellow';"
				+ "nombrePaciente.style.fontSize = '25px';"
				
				+ "var fecha = document.getElementById('{hc}dataVersion-{hc}docExt');"
				+ "fecha.style.backgroundColor='rgb(253,247,133)';"

				
				+ "var buscando = document.querySelectorAll('.custom-combobox-input');"
				+ "buscando[1].style.backgroundColor = 'rgb(253,247,133)';"
				+ "buscando[2].style.backgroundColor = 'rgb(253,247,133)';"
				+ ""
				+ "var siguiente=document.getElementById('siguiente');"
				+ "siguiente.style.color = 'yellow';" 
				
				+ "alert('Ommmm...');"
				+ "";
		
		return cadena;
	}
	
	static public final String maquetado2(){
		String cadena = ""
				+ ""
				+ ""
				+ ""
				+ "var fondo = document.getElementById('page').style.background='#10324c';"

				+ ""
				// + "alert('maquetando');"
				+ "var columnaI = document.getElementById('columnaIzquierdaEdicion');"
				+ "columnaI.style.float='left';"
				+ "columnaI.style.width='800px';"
				+ "columnaI.style.height='1200px';"
				+ "var completePreview = document.getElementById('completePreview');"
				+ "completePreview.style.width='800px';"
				+ "completePreview.style.height='1200px';"
				+ ""
				+ "var previewer = document.getElementById('previewer');"
				+ "previewer.style.width='800px';"
				+ "previewer.style.height='1200px';"
				+ "" 
				
				+ "var columnaD = document.getElementById('columnaDerechaEdicion');"
				+ "columnaD.style.float='left';"
				+ "columnaD.style.marginLeft='800px';"
				+ "columnaD.style.width='1000px';"
				+ ""
				+ "var edicionForm = document.getElementById('edicionForm');"
				+ "edicionForm.style.float='left';"
				+ "edicionForm.style.width='1000px';"
				+ ""
				
				+ "var tablaElementosAjax = document.getElementById('tablaElementosAjax');"
				+ "tablaElementosAjax.style.float='left';"
				+ "tablaElementosAjax.style.width='450px';"
				+ "tablaElementosAjax.style.height='1000px';"
				+ ""
				+ "var tablaMeritos = document.getElementById('tablaMeritos');"
				+ "tablaMeritos.style.minWidth='400px';"
				+ "tablaMeritos.style.height='1000px';"
				+ ""
				+ "tablaMeritos.style.background='#9db7cc';"
				+ "tablaMeritos.style.border='none';"
				+ ""
				+ "var arbol = document.getElementById('arbol');"
				+ "arbol.style.height='1000px';"
				+ "" 
				
				+ "var tablaAtributosAjax = document.getElementById('tablaAtributosAjax');"
			//	+ "tablaAtributosAjax.style.minWidth='450px';"
				+ "tablaAtributosAjax.style.float='left';"
				+ "tablaAtributosAjax.style.marginLeft='470px';"
				+ "tablaAtributosAjax.style.marginTop='-970px';"
				+ ""

				+ "var tablaAtributos = document.getElementById('tablaAtributos');"
				+ "tablaAtributos.style.minWidth='450px';"
				+ "tablaAtributos.style.width='500px';"
				+ ""
				+ "tablaAtributos.style.border='none';"
				+ "tablaAtributos.style.background='#9db7cc';"
				+ "tablaAtributos.style.color='#000000';"
				+ ""
				+ "var tablaDocumento = document.getElementById('tablaDocumento');"
				+ "tablaDocumento.style.minWidth='450px';"
				+ "tablaDocumento.style.width='500px';"
				+ ""
				+ "var contextoMenuSuperior = document.getElementById('contextoMenuSuperior');"
				+ "contextoMenuSuperior.style.marginRight='400px';"
				+ "var loadContexto = document.getElementById('loadContexto');"
				+ "loadContexto.style.width='600px';"
				+ ""
				+ "alert(loadContexto.innerHTML);"
		//		+ "var ind = loadContexto.innerHTML.indexOf('(');"
		//		+ "loadContexto.innerHTML=loadContexto.innerHTML.slice(0,ind);"
				+ ""
		//		+ "var combos0 = document.getElementsByClassName('custom-combobox');"
		//		+ "var combos1 = document.getElementsByClassName('custom-combobox-input');"
		//		+ "var combos2 = document.getElementsByClassName('custom-combobox-toggle');"
		//		+ "alert(combos0.length);"
		//		+ "alert(combos1.length);"
		//		+ "alert(combos2.lenght);"
		//		+ "for(var z=0;z<combos0.length;z++){"
		//			+ "combos0[z].style.width='300px';"
		//			+ "combos1[z].style.width='300px';"
		//			+ "combos2[z].style.right='initial';"
		//		+ "}"
			
				+ "alert('Maquetando');"
				+ ""
				+ "";
		
		return cadena;
	}
	
	public static String completaDatos(){
		
		String cadena = ""
				+ ""
				//	Carga los datos del documento
				
				+ "var archivo = document.getElementById('labelAtributo');"
				+ "var campos = archivo.innerHTML.split(' @');"
				+ "var nhc = campos[1];"
				+ "var servicio = campos[2];"
				+ "var tipoDoc = campos[3];"
				
				//	Coloca el numero de historia arriba para facil lectura
				
				+ "var logo = document.getElementById('branding').getElementsByTagName('a')[0];"
				+ "logo.innerHTML = nhc;"
				+ ""
				
				// Selecciona el nodo
				+ ""
				+ "alert('hola');"
				+ "";
		
		return cadena;
	}
}
