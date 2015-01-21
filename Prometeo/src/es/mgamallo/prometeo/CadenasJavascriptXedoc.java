package es.mgamallo.prometeo;

public class CadenasJavascriptXedoc {

	static public final String introUsuario(Usuario usuario){
		
		String cadena = ""
				+ "var login = document.getElementById('j_username');"
				+ "var password = document.getElementById('j_password');"
				+ "var centro = document.getElementById('j_entorno');"
				+ "login.value= '" + usuario.usuario + "';"
				+ "password.value= '" + usuario.password + "';"
				+ "centro.value='HC_CHOPO';"
			//	+ "alert('hola');"
				+ "document.getElementById('loginForm').submit();"
				+ "";
		
		return cadena;
	}
	
	static public final String selectMiBandeja(){
		
		String miBandeja = "Todas bandejas";  // "Mi bandeja";
		
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
	
	
	static public final String contexto(){
		String cadena = ""
				+ "document.getElementById('contextoMenuSuperior').click();"
				+ "var nhc = document.getElementById('{hc}numeroHC');"
				+ "nhc.value='52378';"
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
	
	static public final String inicio1Xedoc(){
		String cadena = ""
			//	+ "alert('hola');"
				+ "var filas = document.getElementsByTagName('tr');"
				+ "var ancla = filas[1].getElementsByTagName('a');"
			//	+ "alert(ancla[0].innerHTML);"
				+ "ancla[0].click();"
				+ "";
		
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
	
	
	static public final String maquetado2(){
		String cadena = ""
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
			
				+ "alert('Maquetando');"
				+ "";
		
		return cadena;
	}
}
