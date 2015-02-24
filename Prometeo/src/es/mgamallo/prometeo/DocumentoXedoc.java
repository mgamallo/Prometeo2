package es.mgamallo.prometeo;

import com.jacob.activeX.ActiveXComponent;

public class DocumentoXedoc {

	ActiveXComponent xedoc;
	DatosFichaXedoc ficha;
	
	DocumentoXedoc(){
		ficha = new DatosFichaXedoc();
	}
}
