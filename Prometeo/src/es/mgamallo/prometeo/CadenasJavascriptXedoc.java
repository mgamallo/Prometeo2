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
}
