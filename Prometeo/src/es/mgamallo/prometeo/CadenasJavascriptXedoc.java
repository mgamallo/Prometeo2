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
	
	
	static public final String maquetado(){
		String cadena = ""
				+ "document.getElementById('entornoLogin').style.display='none';"
				+ "document.getElementById('branding').style.display='none';"
				+ "document.getElementById('header').style.height='0px';"
				+ "alert('Maquetando');"
				+ "";
		
		return cadena;
	}
}
