package es.mgamallo.prometeo;

import com.jacob.activeX.ActiveXComponent;

public class Paciente {
	ActiveXComponent ianus;
	ActiveXComponent xedoc;
	DatosFicha ficha;
	
	Paciente(){
		ficha = new DatosFicha();
	}
	
}
