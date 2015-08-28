function retornarFecha() {
	var fecha;
	fecha = new Date();
	var cadena = fecha.getDate() + '/' + (fecha.getMonth() + 1) + '/'
			+ fecha.getYear();
	return cadena;
}