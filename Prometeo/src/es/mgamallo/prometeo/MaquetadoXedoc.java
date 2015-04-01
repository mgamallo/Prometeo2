package es.mgamallo.prometeo;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class MaquetadoXedoc {

	public ActiveXComponent xedocDocumento;
	public Dispatch documento;
	public Dispatch fecha;
	public String nombreXedoc;
	
	
	String colorFondo = "#10324c";
	String colorFondoInterno = "#9db7cc";
	String colorFondoCajas = "RGB(253,247,133)";
	
	String colorTitulosBarraNav = "#bbff00";
	
	String cadenaDocumentoPaciente = "";
	
	String claseInputs = "custom-combobox-input ui-widget "
			+ "ui-widget-content ui-state-default ui-corner-left ui-autocomplete-input";
	
	
	public MaquetadoXedoc(ActiveXComponent xedocDocumento, String nombreXedoc) {
		// TODO Auto-generated constructor stub
						
		this.xedocDocumento  = xedocDocumento;
		this.nombreXedoc = nombreXedoc;
		
		documento = Dispatch.call(xedocDocumento,"document").getDispatch();
		
		inicializaMaquetado(this.xedocDocumento);
		maquetado01();
	}
	
	
	public void inicializaMaquetado(ActiveXComponent xedocDocumento){
		
		int ancho = 0;
		int alto = 0;
		int izquierda = 0;
		int arriba = 0;
		
		if(Inicio.numeroPantallas == 1){
			ancho = 1919;
			alto = 1049;
			arriba = 0;
			izquierda = 0;
		}
		else{
			ancho = 2047;
			alto = 1251;
			arriba = 0;
			izquierda = 0;
		}
		
	    Dispatch.put(xedocDocumento,"height",alto);
	    Dispatch.put(xedocDocumento,"width",ancho);
	    Dispatch.put(xedocDocumento,"top",arriba);  
	    Dispatch.put(xedocDocumento,"left",izquierda);
		Dispatch.put(xedocDocumento,"menubar",false);
		Dispatch.put(xedocDocumento,"toolbar",false);
		

	}
	
	private void maquetado01(){

		
		Dispatch fondoPagina = Dispatch.call(documento,"getElementById","page").getDispatch();
		Dispatch estiloFondoPagina = Dispatch.get(fondoPagina,"style").getDispatch();
		Dispatch.put(estiloFondoPagina, "background",colorFondo);
		
		Dispatch botonSalir = Dispatch.call(documento,"getElementById","botonSalir").getDispatch();
		Dispatch.put(botonSalir,"innerHTML",nombreXedoc);
		Dispatch estiloBotonSalir = Dispatch.get(botonSalir,"style").getDispatch();
		Dispatch.put(estiloBotonSalir,"font","bold 28px arial, sans-serif");
		Dispatch.put(estiloBotonSalir, "color","red");
		
	//	Dispatch fondoMain = Dispatch.call(documento,"getElementById","content").getDispatch();
	//	Dispatch estiloFondoMain = Dispatch.get(fondoMain,"style").getDispatch();
	//	Dispatch.put(estiloFondoMain, "background",colorFondo);	
		
		////////////////////////////////////////////////////////////////////
	
		
		Dispatch entornoLogin = Dispatch.call(documento,"getElementById","entornoLogin").getDispatch();
		Dispatch estiloEntornoLogin = Dispatch.get(entornoLogin,"style").getDispatch();
		Dispatch.put(estiloEntornoLogin, "display","none");	
		
/*		Concepto teorico. Crear y añadir un nodo a la pagina
 	
		Dispatch parrafo = Dispatch.call(documento, "createElement","p").getDispatch();
		Dispatch contenido = Dispatch.call(documento, "createTextNode","Parrafo añadido").getDispatch();
		Dispatch bodys = Dispatch.call(documento, "getElementsByTagName","body").getDispatch();
		Dispatch body = Dispatch.get(bodys,"0").getDispatch();
		Dispatch.call(parrafo, "appendChild",contenido);
		Dispatch.call(body, "appendChild",parrafo);
*/
		//////////////////////////////////////////////////////////
		
		
		Dispatch branding = Dispatch.call(documento,"getElementById","branding").getDispatch();
		Dispatch estiloBranding = Dispatch.get(branding,"style").getDispatch();
		Dispatch.put(estiloBranding, "display","none");	
		
		Dispatch header = Dispatch.call(documento,"getElementById","header").getDispatch();
		Dispatch estiloHeader = Dispatch.get(header,"style").getDispatch();
		Dispatch.put(estiloHeader, "height","0px");	
		
		
		//	Columna Izquierda
		//////////////////////////////////////////////
		
		Dispatch columnaI = Dispatch.call(documento,"getElementById","columnaIzquierdaEdicion").getDispatch();
		Dispatch estiloColumnaI = Dispatch.get(columnaI,"style").getDispatch();
		Dispatch.put(estiloColumnaI, "height","1200px");	
		Dispatch.put(estiloColumnaI, "width","800px");
		
		Dispatch completePreview = Dispatch.call(documento,"getElementById","completePreview").getDispatch();
		Dispatch estilocompletePreview = Dispatch.get(completePreview,"style").getDispatch();
		Dispatch.put(estilocompletePreview, "height","1200px");	
		Dispatch.put(estilocompletePreview, "width","800px");
		
		Dispatch previewer = Dispatch.call(documento,"getElementById","previewer").getDispatch();
		Dispatch estiloPreviewer = Dispatch.get(previewer,"style").getDispatch();
		Dispatch.put(estiloPreviewer, "height","1200px");	
		Dispatch.put(estiloPreviewer, "width","800px");
		
		
		//	Columna Derecha
		//////////////////////////////////////////////
		
		Dispatch divOcultarAsociados = Dispatch.call(documento,"getElementById","divOcultarAsociados").getDispatch();
		Dispatch estiloDivOcultarAsociados = Dispatch.get(divOcultarAsociados,"style").getDispatch();
		Dispatch.put(estiloDivOcultarAsociados, "display","none");	
		
		
		Dispatch columnaD = Dispatch.call(documento,"getElementById","columnaDerechaEdicion").getDispatch();
		Dispatch estiloColumnaD = Dispatch.get(columnaD,"style").getDispatch();
		Dispatch.put(estiloColumnaD, "width","1000px");	
		Dispatch.put(estiloColumnaD, "marginLeft","800px");
		
		
		Dispatch tablaAtributos = Dispatch.call(documento,"getElementById","tablaAtributos").getDispatch();
		Dispatch estiloTablaAtributos = Dispatch.get(tablaAtributos,"style").getDispatch();
		Dispatch.put(estiloTablaAtributos, "border","none");	
		Dispatch.put(estiloTablaAtributos, "background",colorFondoInterno);
		Dispatch.put(estiloTablaAtributos, "color","#000000");
		Dispatch.put(estiloTablaAtributos, "minWidth","550px");
		Dispatch.put(estiloTablaAtributos, "width","560px");
		
		Dispatch edicionForm = Dispatch.call(documento,"getElementById","edicionForm").getDispatch();
		Dispatch estiloEdicionForm = Dispatch.get(edicionForm,"style").getDispatch();
		Dispatch.put(estiloEdicionForm, "width","1000px");	

		
		String altoArbol = "1000px";
		String altoTablaMeritos = "1050px";
		if(Inicio.numeroPantallas == 1){
			altoArbol = "830px";
			altoTablaMeritos = "850px";
		}
				
		Dispatch tablaElementosAjax = Dispatch.call(documento,"getElementById","tablaElementosAjax").getDispatch();
		Dispatch estiloTablaElementosAjax = Dispatch.get(tablaElementosAjax,"style").getDispatch();
		Dispatch.put(estiloTablaElementosAjax, "width","450px");	
		Dispatch.put(estiloTablaElementosAjax, "height",altoArbol);
		Dispatch.put(estiloTablaElementosAjax, "marginTop","-70px");
	
		Dispatch tablaMeritos = Dispatch.call(documento,"getElementById","tablaMeritos").getDispatch();
		Dispatch estiloTablaMeritos = Dispatch.get(tablaMeritos,"style").getDispatch();
		Dispatch.put(estiloTablaMeritos, "minWidth","400px");	
		Dispatch.put(estiloTablaMeritos, "height",altoTablaMeritos);
		Dispatch.put(estiloTablaMeritos, "background",colorFondoInterno);	
		Dispatch.put(estiloTablaMeritos, "border","none");
		
		Dispatch arbol = Dispatch.call(documento,"getElementById","arbol").getDispatch();
		Dispatch estiloArbol = Dispatch.get(arbol,"style").getDispatch();
		Dispatch.put(estiloArbol, "height",altoArbol);
		Dispatch.put(estiloArbol, "background",colorFondoInterno);	
		
		Dispatch tablaAtributosAjax = Dispatch.call(documento,"getElementById","tablaAtributosAjax").getDispatch();
		Dispatch estiloTablaAtributosAjax = Dispatch.get(tablaAtributosAjax,"style").getDispatch();
		Dispatch.put(estiloTablaAtributosAjax, "minWidth","500px");	
		Dispatch.put(estiloTablaAtributosAjax, "marginLeft","470px");
	//	Dispatch.put(estiloTablaAtributosAjax, "marginTop","-970px");
		Dispatch.put(estiloTablaAtributosAjax, "marginTop","-750px");
		
		//  TablaDocumento
		///////////////////////////////////////////
		
		Dispatch tablaDocumento = Dispatch.call(documento,"getElementById","tablaDocumento").getDispatch();
		Dispatch estiloTablaDocumento = Dispatch.get(tablaDocumento,"style").getDispatch();
		Dispatch.put(estiloTablaDocumento, "minWidth","560px");	
		Dispatch.put(estiloTablaDocumento, "width","560px");
		Dispatch.put(estiloTablaDocumento, "background",colorFondoInterno);	
		Dispatch.put(estiloTablaDocumento, "border","none");
		
		
		//  Otros
		///////////////////////////////////////////
		
		Dispatch contextoMenuSuperior = Dispatch.call(documento,"getElementById","contextoMenuSuperior").getDispatch();
		Dispatch estiloContextoMenuSuperior = Dispatch.get(contextoMenuSuperior,"style").getDispatch();
		Dispatch.put(estiloContextoMenuSuperior, "marginRight","400px");	

		Dispatch loadContexto = Dispatch.call(documento,"getElementById","loadContexto").getDispatch();
		Dispatch estiloLoadContexto = Dispatch.get(loadContexto,"style").getDispatch();
		Dispatch.put(estiloLoadContexto, "width","800px");
		Dispatch.put(estiloLoadContexto, "marginLeft","-700px");
		Dispatch.put(estiloLoadContexto, "color","yellow");
		Dispatch.put(estiloLoadContexto, "fontSize","25px");
		
		Variant variantUsuario = Dispatch.get(loadContexto,"innerHTML");
		String cadenaUsuario = variantUsuario.getString();
		System.out.println(cadenaUsuario);
		
		
		int index = cadenaUsuario.indexOf("(");
		if(index != -1){
			cadenaUsuario = cadenaUsuario.substring(0, cadenaUsuario.indexOf("("));
		}

		cadenaDocumentoPaciente = cadenaUsuario;
		
		Dispatch.put(loadContexto,"innerHTML",cadenaUsuario);
		
		Dispatch comprimirA = Dispatch.call(documento,"getElementById","selectDisplayButtonsTree").getDispatch();
		Dispatch estiloComprimirA = Dispatch.get(comprimirA,"style").getDispatch();
		Dispatch.put(estiloComprimirA, "left","1200px");
		Dispatch.put(estiloComprimirA, "display","none");
		
		Dispatch comprimirB = Dispatch.call(documento,"getElementById","selectDisplayButtonsAtributos").getDispatch();
		Dispatch estiloComprimirB = Dispatch.get(comprimirB,"style").getDispatch();
		Dispatch.put(estiloComprimirB, "left","1200px");
		Dispatch.put(estiloComprimirB, "display","none");
		
		
		//  Ocultar elementos
		/////////////////////////////////////////////////////////
				
	/*	Variant titulosSeccion = Dispatch.call(documento,"getElementsByTagName","div");
		Variant divs[] = titulosSeccion.getVariantArray();
		System.out.println(divs.length);
	*/	
		
		Dispatch titulosSeccion = Dispatch.call(documento,"getElementsByTagName","div").getDispatch();
		Variant numeroTitulosSeccion = Dispatch.get(titulosSeccion, "length");
		int numTitulosSeccion = numeroTitulosSeccion.getInt();
		System.out.println(numTitulosSeccion); 
		
		int contador = 0;
		
		/*
		 * 	Div elegidos a priori... 
		 * 		colPropDinamicaAncha	33	36	40	42 64
		 * 
		 * 		nuevaSeccionEdicion		47	54	61	
		 * 
		 * */
		
		
		for(int i=30;i<66;i++){
			// System.out.println("Div numero: " + i );
			
	//		if(i == 33 || i == 36 || i == 40 || i == 42 || i == 64 || i ==47 || i ==54 || i ==61){
				
				Dispatch tituloSeccion = Dispatch.get(titulosSeccion, String.valueOf(i)).getDispatch();
				Variant valorId = Dispatch.call(tituloSeccion, "getAttribute","id");
				// System.out.println(valorId.toString());
				if(valorId.toString().equals("nuevaSeccionEdicion")){
					if(contador == 4 || contador == 3 || contador == 2){
						System.out.println("Div elegido, nuevaSeccionEdicion, numero... " + i);
						Dispatch estiloTituloSeccion = Dispatch.call(tituloSeccion, "style").getDispatch();
						Dispatch.put(estiloTituloSeccion, "display","none");
					}
					contador++;
				}
				if(valorId.toString().equals("colPropDinamicaAncha")){
					
					System.out.println("Div elegido, colPropDinamicaAncha, numero... " + i);
					Dispatch estiloTituloSeccion = Dispatch.call(tituloSeccion, "style").getDispatch();
					Dispatch.put(estiloTituloSeccion, "width","300px");
					
				//	Dispatch.put(estiloTituloSeccion, "textAlign","left");
				}
	//		}

		}
		
		
		
		//	Mover nodo de aviso de error
		
		/*
		Dispatch nodoError = Dispatch.call(documento,"getElementById","edicionErrorMessage").getDispatch();
		Dispatch estiloNodoError = Dispatch.call(nodoError, "style").getDispatch();
		Dispatch.put(estiloNodoError,"display","block");
		*/
		//	Dispatch.put(tablaAtributosAjax, "appendChild",nodoError);
		
		//  NHC
		//////////////////////////////////////////////////////////////////
		
		Dispatch labelAtributo = Dispatch.call(documento,"getElementById","labelAtributo").getDispatch();
		Dispatch estiloLabelAtributo = Dispatch.get(labelAtributo,"style").getDispatch();
		Dispatch.put(estiloLabelAtributo, "width","560px");	
		Dispatch.put(estiloLabelAtributo, "color","green");	
		Dispatch.put(estiloLabelAtributo, "paddingLeft","10px");
		
		XedocIndividualJacob xedoc = new XedocIndividualJacob(Dispatch.get(labelAtributo,"innerHTML").getString(),documento,xedocDocumento);
	//	xedoc.imprimeDatos();

		putNHC(xedoc);
		colorea();
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		xedoc.buscaNodo();
		xedoc.ocultaNodos();
		xedoc.seleccionarServicio();
		xedoc.seleccionarDocumento();
		xedoc.putFecha(fecha);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xedoc.getFocus();
		
	}
	
	
	public void putNHC(XedocIndividualJacob xedoc){
		
		//	Coloca los datos del documento en el nav original
		
		// Las anclas tienen que ser:  135, 136, 137  
		// o tienen que ser:  145, 146, 147  
		
		// System.out.println("Cambiando el nav....");
		
		Dispatch anclas = Dispatch.call(documento,"getElementsByTagName","a").getDispatch();
		Variant numeroAnclas = Dispatch.get(anclas, "length");
		int numAnclas = numeroAnclas.getInt();
		// System.out.println("Numero de anclas... " + numAnclas); 
		
		
		
		for(int i=100;i<numAnclas;i++){

			Dispatch tituloAncla = Dispatch.get(anclas, String.valueOf(i)).getDispatch();
			Variant valorTitle = Dispatch.call(tituloAncla, "getAttribute","title");
			System.out.println(valorTitle.toString());
			if(valorTitle.toString().equals("Inicio")){
				//	System.out.println("Encontrada ancla inicio");
				//	System.out.println("Ancla numero: " + i + " encontrada.");
					Dispatch.put(tituloAncla, "innerHTML",xedoc.nhc);
					Dispatch estiloTituloAnclaNhc = Dispatch.get(tituloAncla,"style").getDispatch();
					Dispatch.put(estiloTituloAnclaNhc, "fontSize","30px");
					Dispatch.put(estiloTituloAnclaNhc, "marginBottom","10px");
					Dispatch.put(estiloTituloAnclaNhc, "marginRight","30px");
					
					String color = "yellow";
					if(cadenaDocumentoPaciente.contains(xedoc.nhc)){
						color = "red";
					}
					
					Dispatch.put(estiloTituloAnclaNhc, "color",color);
			}
			else if(valorTitle.toString().equals("Documentos Pendentes")){
				// System.out.println("Encontrada ancla inicio");
				// System.out.println("Ancla numero: " + i + " encontrada.");
				Dispatch.put(tituloAncla, "innerHTML",xedoc.servicio);
				Dispatch estiloTituloAnclaServicio = Dispatch.get(tituloAncla,"style").getDispatch();
				Dispatch.put(estiloTituloAnclaServicio, "fontSize","30px");
				Dispatch.put(estiloTituloAnclaServicio, "color","red");
				Dispatch.put(estiloTituloAnclaServicio, "marginRight","30px");
			}
			if(valorTitle.toString().equals("Búsqueda Documentos")){
			//	System.out.println("Encontrada ancla inicio");
			//	System.out.println("Ancla numero: " + i + " encontrada.");
				
				String nombreAcortado = xedoc.nombreDocumento;
				if(xedoc.nombreDocumento.length() > 30){
					nombreAcortado = xedoc.nombreDocumento.substring(0,30);
				}
				Dispatch.put(tituloAncla, "innerHTML",nombreAcortado.toUpperCase());
				Dispatch estiloTituloAnclaTipo = Dispatch.get(tituloAncla,"style").getDispatch();
				Dispatch.put(estiloTituloAnclaTipo, "fontSize","30px");
				Dispatch.put(estiloTituloAnclaTipo, "color","red");
				Dispatch.put(estiloTituloAnclaTipo, "marginRight","30px");
				break;
			}
	
		}
	}
	
	
	public void colorea(){
		Dispatch inputs = Dispatch.call(documento,"getElementsByTagName","input").getDispatch();
		Variant numeroInputs = Dispatch.get(inputs, "length");
		int numInputs = numeroInputs.getInt();
		System.out.println(numInputs); 
		
		
		/*
		 * 	inputs 
		 * 
		 * */
		
	//	int numInput[] = { 133, 136};
		
		for(int i=100, j= 0 ;i<numInputs && j < 3;i++){

			Dispatch input = Dispatch.call(inputs, String.valueOf(i)).getDispatch();

			Variant nombreClase = Dispatch.call(input, "getAttribute","autocomplete");
			System.out.println(nombreClase.toString());

			if(nombreClase.toString().equals("off")){
				System.out.println("Encontrado input");
				System.out.println("Input numero: " + i + " encontrado.");
				
				if(j != 0){
					String id = "cajaColoreada" + String.valueOf(j);

					Dispatch.call(input, "setAttribute","id",id);

					Dispatch estiloInput = Dispatch.get(input,"style").getDispatch();
					Dispatch.put(estiloInput, "backgroundColor",colorFondoCajas);
					Dispatch.put(estiloInput,"font","bold 18px arial, sans-serif");
					Dispatch.put(estiloInput, "paddingLeft","10px");
					Dispatch.put(estiloInput, "width","500px");
					Dispatch.put(estiloInput, "color","red");
				}
				j++;
			}	


		}

		fecha = Dispatch.call(documento,"getElementById","{hc}dataVersion-{hc}docExt").getDispatch();
		Dispatch estiloFecha = Dispatch.get(fecha,"style").getDispatch();
		Dispatch.put(estiloFecha, "backgroundColor",colorFondoCajas);
		Dispatch.put(estiloFecha,"font","bold 20px arial, sans-serif");
	}
	
}




