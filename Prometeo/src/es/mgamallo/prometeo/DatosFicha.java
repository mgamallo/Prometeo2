package es.mgamallo.prometeo;

public class DatosFicha {

	String nombreCompleto = "";
	
	String fechaIngreso = "";
	String horaIngreso = "";
	
	String cip = "";
	String nss = "";
	String fechaNacimiento = "";

	static final String FECHA_INGRESO = "Data do Ingreso:</TD>";
	static final String HORA_INGRESO = "Hora do Ingreso:</TD>";
	static final String NOMBRE = "Nome:</TD>";
	static final String CIP = "var valor = ";
	static final String FECHA_NACIMIENTO = "var nac = '";
	static final String NSS = "NSS:</TD>";
	
	public void setDatosFicha(String cadena){
		String[] datos = cadena.split("@");
		String minicadena = "";
		if(datos.length > 0){
			if(datos[0].equals("1")){    // Ingresos
				int indexFechaIngreso = datos[1].indexOf(FECHA_INGRESO);
				minicadena = datos[1].substring(indexFechaIngreso + FECHA_INGRESO.length() + 1); 
				indexFechaIngreso = minicadena.indexOf(">");
				int indexFinFechaIngreso = minicadena.indexOf("</TD>");
				
				fechaIngreso = minicadena.substring(indexFechaIngreso + 1,indexFinFechaIngreso);
				System.out.println("Fecha de ingreso "+ fechaIngreso);
			
				int indexHoraIngreso = minicadena.indexOf(HORA_INGRESO);
				minicadena = minicadena.substring(indexHoraIngreso + HORA_INGRESO.length() + 1);
				indexHoraIngreso = minicadena.indexOf(">");
				int indexFinHoraIngreso = minicadena.indexOf("</TD>");
				
				horaIngreso = minicadena.substring(indexHoraIngreso + 1, indexFinHoraIngreso);
				System.out.println("Hora de ingreso " + horaIngreso);
				
				
				int indexNombre = datos[2].indexOf(NOMBRE);
				minicadena = datos[2].substring(indexNombre + NOMBRE.length() + 1);
				indexNombre = minicadena.indexOf(">");
				int indexFinNombre = minicadena.indexOf("</TD>");
				
				nombreCompleto = minicadena.substring(indexNombre + 1,indexFinNombre);
				System.out.println(nombreCompleto);
				
				int indexCIP = minicadena.indexOf(CIP);
				minicadena = minicadena.substring(indexCIP + CIP.length() + 1);
				int indexFinCip = minicadena.indexOf(";");
				
				cip = minicadena.substring(1,indexFinCip-1);
				System.out.println(cip);
				
				int indexFechaNacimiento = minicadena.indexOf(FECHA_NACIMIENTO);
				minicadena = minicadena.substring(indexFechaNacimiento + FECHA_NACIMIENTO.length() + 1);
				int indexFinFechaNacimiento = minicadena.indexOf("'");
				
				fechaNacimiento = minicadena.substring(0,indexFinFechaNacimiento);
				System.out.println(fechaNacimiento);
				
				int indexNSS = minicadena.indexOf(NSS);
				minicadena = minicadena.substring(indexNSS + NSS.length() + 1);
				indexNSS = minicadena.indexOf(">");
				int indexFinNSS = minicadena.indexOf("</TD>");
				
				nss = minicadena.substring(indexNSS + 1, indexFinNSS);
				System.out.println(nss);
				
				
				/*	horaIngreso = datos[2];
				nombreCompleto = datos[3];
				fechaNacimiento = datos[4];
				nss = datos[5]; */
			}
			
			/*
			System.out.println("Datos extraidos");
			for(int i=0;i<datos.length;i++){
				System.out.println(datos[i]);
			}
			*/
		}
		
		
	}
}


/*
 *	Ingresos:
 *
 * 		Datos alta:      tabla[6]
 * 
 * 			Fecha ingreso:  tabla[6].celda[5]
 * 			Hora ingreso:	tabla[6].celda[7]
 * 
 * 		Datos Paciente:  tabla[2]
 * 
 * 			Nombre y apellidos:  tabla[2].celda[3]
 * 			CIP:				 tabla[2].celda[5]   ------> Hay que eliminar un script
 * 			Fecha Nacimiento	 tabla[2].celda[9]
 * 			NSS:				 tabla[2].celda[15]
 * 
 * 			javascript:var tabla = principal.datos.ficha.hosp_main.episodio.document.getElementsByTagName('table');var celdas =tabla[6].getElementsByTagName('td');alert(celdas[5].innerHTML);
 *
*/