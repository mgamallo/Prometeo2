package es.mgamallo.prometeo;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class MaquetadoXedoc {

	public ActiveXComponent xedocDocumento;
	public static Dispatch documento;
	
	String colorFondo = "#10324c";
	String colorFondoInterno = "#9db7cc";
	
	public MaquetadoXedoc(ActiveXComponent xedocDocumento) {
		// TODO Auto-generated constructor stub
						
		this.xedocDocumento  = xedocDocumento;
		
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
		
		
		////////////////////////////////////////////////////////////////////
		
		Dispatch entornoLogin = Dispatch.call(documento,"getElementById","entornoLogin").getDispatch();
		Dispatch estiloEntornoLogin = Dispatch.get(entornoLogin,"style").getDispatch();
		Dispatch.put(estiloEntornoLogin, "display","none");	
		
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
		
		Dispatch columnaD = Dispatch.call(documento,"getElementById","columnaDerechaEdicion").getDispatch();
		Dispatch estiloColumnaD = Dispatch.get(columnaD,"style").getDispatch();
		Dispatch.put(estiloColumnaD, "width","1000px");	
		Dispatch.put(estiloColumnaD, "marginLeft","800px");
		
		
		Dispatch tablaAtributos = Dispatch.call(documento,"getElementById","tablaAtributos").getDispatch();
		Dispatch estiloTablaAtributos = Dispatch.get(tablaAtributos,"style").getDispatch();
		Dispatch.put(estiloTablaAtributos, "border","none");	
		Dispatch.put(estiloTablaAtributos, "background",colorFondoInterno);
		Dispatch.put(estiloTablaAtributos, "color","#000000");
		Dispatch.put(estiloTablaAtributos, "minWidth","450px");
		Dispatch.put(estiloTablaAtributos, "width","500px");
		
		Dispatch edicionForm = Dispatch.call(documento,"getElementById","edicionForm").getDispatch();
		Dispatch estiloEdicionForm = Dispatch.get(edicionForm,"style").getDispatch();
		Dispatch.put(estiloEdicionForm, "width","1000px");	

		Dispatch tablaElementosAjax = Dispatch.call(documento,"getElementById","tablaElementosAjax").getDispatch();
		Dispatch estiloTablaElementosAjax = Dispatch.get(tablaElementosAjax,"style").getDispatch();
		Dispatch.put(estiloTablaElementosAjax, "width","450px");	
		Dispatch.put(estiloTablaElementosAjax, "height","1000px");
		
		Dispatch tablaMeritos = Dispatch.call(documento,"getElementById","tablaMeritos").getDispatch();
		Dispatch estiloTablaMeritos = Dispatch.get(tablaMeritos,"style").getDispatch();
		Dispatch.put(estiloTablaMeritos, "minWidth","400px");	
		Dispatch.put(estiloTablaMeritos, "height","1000px");
		Dispatch.put(estiloTablaMeritos, "background",colorFondoInterno);	
		Dispatch.put(estiloTablaMeritos, "border","none");
		
		Dispatch arbol = Dispatch.call(documento,"getElementById","arbol").getDispatch();
		Dispatch estiloArbol = Dispatch.get(arbol,"style").getDispatch();
		Dispatch.put(estiloArbol, "height","1000px");	
		
		Dispatch tablaAtributosAjax = Dispatch.call(documento,"getElementById","tablaAtributosAjax").getDispatch();
		Dispatch estiloTablaAtributosAjax = Dispatch.get(tablaAtributosAjax,"style").getDispatch();
		Dispatch.put(estiloTablaAtributosAjax, "minWidth","450px");	
		Dispatch.put(estiloTablaAtributosAjax, "marginLeft","470px");
		Dispatch.put(estiloTablaAtributosAjax, "marginTop","-970px");
		
		
		//  TablaDocumento
		///////////////////////////////////////////
		
		Dispatch tablaDocumento = Dispatch.call(documento,"getElementById","tablaDocumento").getDispatch();
		Dispatch estiloTablaDocumento = Dispatch.get(tablaDocumento,"style").getDispatch();
		Dispatch.put(estiloTablaDocumento, "minWidth","450px");	
		Dispatch.put(estiloTablaDocumento, "width","500px");
		Dispatch.put(estiloTablaDocumento, "background",colorFondoInterno);	
		Dispatch.put(estiloTablaDocumento, "border","none");
		
		
		//  Otros
		///////////////////////////////////////////
		
		Dispatch contextoMenuSuperior = Dispatch.call(documento,"getElementById","contextoMenuSuperior").getDispatch();
		Dispatch estiloContextoMenuSuperior = Dispatch.get(contextoMenuSuperior,"style").getDispatch();
		Dispatch.put(estiloContextoMenuSuperior, "marginRight","400px");	

		Dispatch loadContexto = Dispatch.call(documento,"getElementById","loadContexto").getDispatch();
		Dispatch estiloLoadContexto = Dispatch.get(loadContexto,"style").getDispatch();
		Dispatch.put(estiloLoadContexto, "width","600px");
		Dispatch.put(estiloLoadContexto, "marginLeft","-800px");
		Dispatch.put(estiloLoadContexto, "color","yellow");
		Dispatch.put(estiloLoadContexto, "fontSize","25px");
		
		Variant variantUsuario = Dispatch.get(loadContexto,"innerHTML");
		String cadenaUsuario = variantUsuario.getString();
		System.out.println(cadenaUsuario);
		
		cadenaUsuario = cadenaUsuario.substring(0, cadenaUsuario.indexOf("("));
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
				
		Variant titulosSeccion = Dispatch.call(documento,"getElementsByTagName","div");
		Variant divs[] = titulosSeccion.getVariantArray();
		System.out.println(divs.length);
		
		/*
		Dispatch titulosSeccion = Dispatch.call(documento,"getElementsByTagName","div").getDispatch();
		Variant numeroTitulosSeccion = Dispatch.get(titulosSeccion, "length");
		int numTitulosSeccion = numeroTitulosSeccion.getInt();
		System.out.println(numTitulosSeccion); 
		
		int contador = 0;
		
		for(int i=50;i<numTitulosSeccion;i++){
			System.out.println("Div numero: " + i );
			Dispatch tituloSeccion = Dispatch.get(titulosSeccion, i).getDispatch();
			Variant valorId = Dispatch.call(tituloSeccion, "getAttribute","id");
			if(valorId.equals("tituloSeccion")){
				if(contador == 4 || contador == 3 || contador == 2){
					Dispatch estiloTituloSeccion = Dispatch.call(tituloSeccion, "style").getDispatch();
					Dispatch.put(estiloTituloSeccion, "display","none");
				}
			}
		}
		*/
	}
}
