package es.mgamallo.prometeo;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RobotIanus {
	
	public final String DOC_ANULADO = "Documento anulado";
	public final String  RUTA_DOC_ANULADO = Inicio.unidadHDD + ":\\DIGITALIZACIÓN\\DOC. ANULADO.pdf";

	public void asocia(String titulo){
		
		String alias = titulo.substring(0,5);
		
		Robot robot;
		try {
			robot = new Robot();

		
		//	1 Coge foco de la ventana modal
		
		robot.mouseMove(1550, 825);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.delay(100);
		
		//	2 Tabulador
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.delay(50);
		
		// 	3.0 Escribe título
		
			/********** codificar *****  Asumimos siempre consent. info *****/
		
		if(!titulo.contains(DOC_ANULADO)){
			for(int k = 0; k< titulo.length();k++){
				getChar(titulo.charAt(k));
				robot.delay(10);
				// System.out.println(titulo.charAt(k));
			}
		}
		else{
			robot.delay(100);
			getChar('a');
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyRelease(KeyEvent.VK_UP);
			robot.delay(75);
			System.out.println("anulando");
		}
		
		//	3.1	Enter
		robot.delay(100);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(100);		
		
		
		// 4 Tabulador
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.delay(50);
		
		//	5 Pega título
		CopEnPortapapeles copiar = new CopEnPortapapeles();
		copiar.copiarAlPortapapeles(titulo);
		
		robot.delay(50);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(50);
		
		//	6-7 Tabulador
		for(int i=0;i<2;i++){
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.delay(50);
		}
		
		//	8 Barra espaciadora;
		robot.keyPress(KeyEvent.VK_SPACE);
		robot.keyRelease(KeyEvent.VK_SPACE);
		robot.delay(50);
		
		
		//	9 Pega ruta
		
		if(titulo.toLowerCase().contains(InicioIanus.DOC_ANULADO.toLowerCase())){
			copiar.copiarAlPortapapeles(RUTA_DOC_ANULADO);
		}else{
			copiar.copiarAlPortapapeles(Inicio.documento[Inicio.indiceArchivoSelecc].rutaArchivo);
		}

		robot.delay(100);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);	
		
		robot.delay(250);
		
		//	10 Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(200);
		
		
		
		//	11 Tabulador 9 veces
		for(int i=0;i<9;i++){
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.delay(10);
		}
		
		//	12 Aceptar
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(200);
		
		System.out.println("pegado");
		
		//	13 Aceptar definitivo
/*		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
*/		
		
		
		
		// CapturaRatonYTeclado.barraEspaciadoraOn = true;
		
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[]){
		RobotIanus robot = new RobotIanus();
		robot.asocia("Consentimento informado");
	}
	
	public static void imprimeAscii(int codigoAscii){
		String digitos = String.valueOf(codigoAscii);
		char dig[] = digitos.toCharArray();
		
		try {
			Robot robot = new Robot();
			
			robot.keyPress(KeyEvent.VK_ALT);
			for(int i=0;i<dig.length;i++){
				int teclaNumerica = 0;
				switch (dig[i]) {
				case '0':
					teclaNumerica = KeyEvent.VK_NUMPAD0;
					break;
				case '1':
					teclaNumerica = KeyEvent.VK_NUMPAD1;
					break;
				case '2':
					teclaNumerica = KeyEvent.VK_NUMPAD2;
					break;
				case '3':
					teclaNumerica = KeyEvent.VK_NUMPAD3;
					break;
				case '4':
					teclaNumerica = KeyEvent.VK_NUMPAD4;
					break;
				case '5':
					teclaNumerica = KeyEvent.VK_NUMPAD5;
					break;
				case '6':
					teclaNumerica = KeyEvent.VK_NUMPAD6;
					break;
				case '7':
					teclaNumerica = KeyEvent.VK_NUMPAD7;
					break;
				case '8':
					teclaNumerica = KeyEvent.VK_NUMPAD8;
					break;
				case '9':
					teclaNumerica = KeyEvent.VK_NUMPAD9;
					break;
				}
				robot.keyPress(teclaNumerica);
				robot.keyRelease(teclaNumerica);
			}
			
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.delay(20);
			
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void imprimeChar(boolean inverso,int codigo, boolean acento){
		try{
			Robot robot = new Robot();

			if (inverso){
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(codigo);
				robot.keyRelease(codigo);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}
			else if(acento){
				robot.keyPress(KeyEvent.VK_DEAD_ACUTE);
				robot.keyPress(codigo);
			}
			else{
				
				System.out.println("Este es el código a imprimir " + ((char)codigo));
				
				switch (codigo) {
				case KeyEvent.VK_SPACE:
					imprimeAscii(32);
					break;
				case KeyEvent.VK_A:
					imprimeAscii(97);
					break;
				case KeyEvent.VK_S:
					imprimeAscii(115);
					break;
				case KeyEvent.VK_D:
					imprimeAscii(100);
					break;
				case KeyEvent.VK_F:
					imprimeAscii(102);
					break;
				case KeyEvent.VK_Z:
					imprimeAscii(122);
					break;
				case KeyEvent.VK_X:
					imprimeAscii(120);
					break;
				case KeyEvent.VK_C:
					imprimeAscii(99);
					System.out.println("Imprime ascii");
					break;
				case KeyEvent.VK_V:
					imprimeAscii(118);
					break;

				default:
					robot.keyPress(codigo);
					robot.keyRelease(codigo);
					System.out.println("Imprime por defecto: " + ((char) codigo));
					break;
				}
							}
					
		}catch (AWTException e){
			e.printStackTrace();
		}
	}
	
	public void getChar(char c){
		int codigo=0;
		boolean inverso = false;
		boolean acento = false;
		
		switch (c){
		case 'a': codigo = KeyEvent.VK_A;break;
		case 'b': codigo = KeyEvent.VK_B;break;
        case 'c': codigo = KeyEvent.VK_C; break;
        case 'd': codigo = KeyEvent.VK_D; break;
        case 'e': codigo = KeyEvent.VK_E; break;
        case 'f': codigo = KeyEvent.VK_F; break;
        case 'g': codigo = KeyEvent.VK_G; break;
        case 'h': codigo = KeyEvent.VK_H; break;
        case 'i': codigo = KeyEvent.VK_I; break;
        case 'j': codigo = KeyEvent.VK_J; break;
        case 'k': codigo = KeyEvent.VK_K; break;
        case 'l': codigo = KeyEvent.VK_L; break;
        case 'm': codigo = KeyEvent.VK_M; break;
        case 'n': codigo = KeyEvent.VK_N; break;
        case 'o': codigo = KeyEvent.VK_O; break;
        case 'p': codigo = KeyEvent.VK_P; break;
        case 'q': codigo = KeyEvent.VK_Q; break;
        case 'r': codigo = KeyEvent.VK_R; break;
        case 's': codigo = KeyEvent.VK_S; break;
        case 't': codigo = KeyEvent.VK_T; break;
        case 'u': codigo = KeyEvent.VK_U; break;
        case 'v': codigo = KeyEvent.VK_V; break;
        case 'w': codigo = KeyEvent.VK_W; break;
        case 'x': codigo = KeyEvent.VK_X; break;
        case 'y': codigo = KeyEvent.VK_Y; break;
        case 'z': codigo = KeyEvent.VK_Z; break;
        case ' ': codigo = KeyEvent.VK_SPACE;break;
        case '-': codigo = KeyEvent.VK_MINUS;break;
        case '.': codigo = 46;break;
//        case '\\': codigo = KeyEvent.VK_ALT_GRAPH; break;
        case '_': inverso = true; codigo = KeyEvent.VK_MINUS;break;
        case ':': inverso = true; codigo = 46;break;       
        case 'A': inverso = true;codigo = KeyEvent.VK_A; break;
        case 'B': inverso = true; codigo = KeyEvent.VK_B; break;
        case 'C': inverso = true; codigo = KeyEvent.VK_C; break;
        case 'D': inverso = true; codigo = KeyEvent.VK_D; break;
        case 'E': inverso = true; codigo = KeyEvent.VK_E; break;
        case 'F': inverso = true; codigo = KeyEvent.VK_F; break;
        case 'G': inverso = true; codigo = KeyEvent.VK_G; break;
        case 'H': inverso = true; codigo = KeyEvent.VK_H; break;
        case 'I': inverso = true; codigo = KeyEvent.VK_I; break;
        case 'J': inverso = true; codigo = KeyEvent.VK_J; break;
        case 'K': inverso = true; codigo = KeyEvent.VK_K; break;
        case 'L': inverso = true; codigo = KeyEvent.VK_L; break;
        case 'M': inverso = true; codigo = KeyEvent.VK_M; break;
        case 'N': inverso = true; codigo = KeyEvent.VK_N; break;
        case 'O': inverso = true; codigo = KeyEvent.VK_O; break;
        case 'P': inverso = true; codigo = KeyEvent.VK_P; break;
        case 'Q': inverso = true; codigo = KeyEvent.VK_Q; break;
        case 'R': inverso = true; codigo = KeyEvent.VK_R; break;
        case 'S': inverso = true; codigo = KeyEvent.VK_S; break;
        case 'T': inverso = true; codigo = KeyEvent.VK_T; break;
        case 'U': inverso = true; codigo = KeyEvent.VK_U; break;
        case 'V': inverso = true; codigo = KeyEvent.VK_V; break;
        case 'W': inverso = true; codigo = KeyEvent.VK_W; break;
        case 'X': inverso = true; codigo = KeyEvent.VK_X; break;
        case 'Y': inverso = true; codigo = KeyEvent.VK_Y; break;
        case 'Z': inverso = true; codigo = KeyEvent.VK_Z; break;
        case 'á': acento = true; codigo = KeyEvent.VK_A; break;
        case 'é': acento = true; codigo = KeyEvent.VK_E; break;
        case 'í': acento = true; codigo = KeyEvent.VK_I; break;
        case 'ó': acento = true; codigo = KeyEvent.VK_O; break;
        case 'ú': acento = true; codigo = KeyEvent.VK_U; break;
        
 
        case '0': codigo = KeyEvent.VK_0; break;
        case '1': codigo = KeyEvent.VK_1; break;
        case '2': codigo = KeyEvent.VK_2; break;
        case '3': codigo = KeyEvent.VK_3; break;
        case '4': codigo = KeyEvent.VK_4; break;
        case '5': codigo = KeyEvent.VK_5; break;
        case '6': codigo = KeyEvent.VK_6; break;
        case '7': codigo = KeyEvent.VK_7; break;
        case '8': codigo = KeyEvent.VK_8; break;
        case '9': codigo = KeyEvent.VK_9; break;		
		
		
		
		
		
		default:
		//	codigo = KeyEvent.VK_C;
		}
		
		this.imprimeChar(inverso,codigo, acento);
		
	}
}
