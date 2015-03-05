package es.mgamallo.prometeo;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

public class RobotIanusXedoc {

	public final String DOC_ANULADO = "Documento cancelado";
	public final String RUTA_DOC_ANULADO = Inicio.unidadHDDvirtual
			+ ":\\DIGITALIZACIÓN\\DOC. ANULADO.pdf";

	public Point coordExaminar = new Point(0, 0);
	public Point coordLupa = new Point(0, 0);
	public Point coordTitulo = new Point(0, 0);
	public Point coordAceptar = new Point(0, 0);
	public Point coordTipoDoc = new Point(0, 0);

	public String titulo = "";

	private int aceptarManualX = 1627, aceptarManualY = 710;

	private void setCoordenadas(String tipoSubida) {

		int columna = 0;

		if (tipoSubida.equals("HOSP") || tipoSubida.equals("URG")
				|| tipoSubida.equals("EPI")) {
			columna = 0;
		} else if (tipoSubida.equals("CEX")) {
			columna = 1;
		} else if (tipoSubida.equals("CIA")) {
			columna = 2;
		} else if (tipoSubida.equals("QUI")) {
			columna = 3;
		}

		coordExaminar.x = Inicio.inicioIanus.coordenadasAsociar[0][columna];
		coordExaminar.y = Inicio.inicioIanus.coordenadasAsociar[1][columna];
		coordLupa.x = Inicio.inicioIanus.coordenadasAsociar[2][columna];
		coordLupa.y = Inicio.inicioIanus.coordenadasAsociar[3][columna];
		coordTitulo.x = Inicio.inicioIanus.coordenadasAsociar[4][columna];
		coordTitulo.y = Inicio.inicioIanus.coordenadasAsociar[5][columna];
		coordAceptar.x = Inicio.inicioIanus.coordenadasAsociar[6][columna];
		coordAceptar.y = Inicio.inicioIanus.coordenadasAsociar[7][columna];
		coordTipoDoc.x = Inicio.inicioIanus.coordenadasAsociar[8][columna];
		coordTipoDoc.y = Inicio.inicioIanus.coordenadasAsociar[9][columna];

		System.out.println(coordExaminar.y + ", " + coordAceptar.y);
	}

	public void asocia(String tituloCDU) {

		System.out.println(tituloCDU);

		// String alias = titulo.substring(0,5);


		setCoordenadas(InicioIanus.tipoSubida);

		boolean tieneTipo = false;

//		if (!InicioIanus.versionar) {
			tieneTipo = compruebaAsociar(tituloCDU);
			if (tieneTipo) {
				Robot robot;
				try {
					robot = new Robot();

					// 01 Pulsa boton examinar

					if(!InicioIanus.versionar){
						robot.delay(100 + Retardos.retardoDibujarVentana);
					}

					robot.mouseMove(coordExaminar.x, coordExaminar.y);

					robot.delay(25);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);

					Portapapeles copiar = new Portapapeles();

					if (tituloCDU.toLowerCase().contains(InicioIanus.DOC_ANULADO.toLowerCase())) {
							copiar.copiarAlPortapapeles(RUTA_DOC_ANULADO);
					} else {
							copiar.copiarAlPortapapeles(Inicio.documento[Inicio.indiceArchivoSelecc].rutaArchivo);
					}

					robot.delay(Retardos.retardoTrasPulsarExaminar  /* + Retardos.S_lento */);
						
						//	Pega Ruta archivo
						
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);

						robot.delay(150 /* + Retardos.S_lento */);

						// 10 Enter
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
						robot.delay(250);

						
					// 02 Comprueba si tiene un subtitulo
						int indexParentesis = tituloCDU.indexOf("(");
						if (indexParentesis != -1) {
							
							titulo = tituloCDU.substring(indexParentesis + 1,tituloCDU.length() - 1);
							tituloCDU = tituloCDU.substring(0,indexParentesis);
							System.out.println(tituloCDU);
							System.out.println(titulo);
						}
						
						
					//	03 Pulsa lupa
						
					if (!InicioIanus.versionar) {
						robot.mouseMove(coordLupa.x, coordLupa.y);
						robot.delay(50);
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
						robot.delay(Retardos.retardoTrasLupa);
					} 

					// 3 Pega tipo documento
					
					robot.mouseMove(coordTipoDoc.x, coordTipoDoc.y);
					robot.delay(50);
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
					robot.delay(150);					
					
					copiar.copiarAlPortapapeles(tituloCDU);
					robot.delay(50);
					
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_CONTROL);

					robot.delay(100);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					robot.delay(250);
					
					robot.delay(100);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					robot.delay(250);
					

					// 04 Pega titulo si procede
					if(indexParentesis != -1){
						robot.mouseMove(coordTitulo.x, coordTitulo.y);
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
						robot.delay(50);
						
						copiar.copiarAlPortapapeles(titulo);
						
						robot.delay(50);
						
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.delay(100);
					}


					// 05 Aceptar

					if (!InicioIanus.versionar) {
						
						robot.mouseMove(coordAceptar.x, coordAceptar.y);
						robot.delay(100);
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
						robot.delay(200);
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
						robot.delay(200);  
					}

					System.out.println("pegado");

					// 13 Aceptar definitivo
					/*
					 * robot.keyPress(KeyEvent.VK_ENTER);
					 * robot.keyRelease(KeyEvent.VK_ENTER);
					 */
					if (!InicioIanus.versionar) {
						robot.mouseMove(aceptarManualX, aceptarManualY);
					}

					// CapturaRatonYTeclado.barraEspaciadoraOn = true;

					// Inicio.panelPrincipal.webBrowserOperaciones.setVisible(false);

				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					InicioIanus.versionar = false;
				}
			} else {
				JOptionPane.showMessageDialog(null, "En principio no se puede subir este documento. \n" + 
							Inicio.documento[Inicio.indiceArchivoSelecc].nombreNormalizado + "\n" + 
							Inicio.documento[Inicio.indiceArchivoSelecc].servicio +
							 "\nPrueba a actualizar el ianus, y espera a que se dibujen los botones de asociar");
			}
//		}

		InicioIanus.versionar = false;

	}

	public static void imprimeAscii(int codigoAscii) {
		String digitos = String.valueOf(codigoAscii);
		char dig[] = digitos.toCharArray();

		try {
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_ALT);
			for (int i = 0; i < dig.length; i++) {
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

	public void imprimeChar(boolean inverso, int codigo, boolean acento) {
		try {
			Robot robot = new Robot();

			if (inverso) { // No vamos a escribir mayusculas
				/*
				 * robot.keyPress(KeyEvent.VK_SHIFT); robot.keyPress(codigo);
				 * robot.keyRelease(codigo);
				 * robot.keyRelease(KeyEvent.VK_SHIFT);
				 */
			}
			if (acento) {
				robot.keyPress(KeyEvent.VK_DEAD_ACUTE);
				robot.keyPress(codigo);
			} else {

				// System.out.println("Este es el código a imprimir " +
				// ((char)codigo));

				switch (codigo) {
				case KeyEvent.VK_SPACE:
					imprimeAscii(32);
					break;

				/*
				 * case KeyEvent.VK_A: imprimeAscii(97); break; case
				 * KeyEvent.VK_S: imprimeAscii(115); break; case KeyEvent.VK_D:
				 * imprimeAscii(100); break; case KeyEvent.VK_F:
				 * imprimeAscii(102); break; case KeyEvent.VK_Z:
				 * imprimeAscii(122); break; case KeyEvent.VK_X:
				 * imprimeAscii(120); break; case KeyEvent.VK_C:
				 * imprimeAscii(99); break;
				 */

				case KeyEvent.VK_V:
					imprimeAscii(118);
					break;

				default:
					robot.keyPress(codigo);
					robot.keyRelease(codigo);
					System.out.println("Imprime por defecto: "
							+ ((char) codigo));
					break;
				}
			}

		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void getChar(char c) {
		int codigo = 0;
		boolean inverso = false;
		boolean acento = false;

		switch (c) {
		case 'a':
			codigo = KeyEvent.VK_A;
			break;
		case 'b':
			codigo = KeyEvent.VK_B;
			break;
		case 'c':
			codigo = KeyEvent.VK_C;
			break;
		case 'd':
			codigo = KeyEvent.VK_D;
			break;
		case 'e':
			codigo = KeyEvent.VK_E;
			break;
		case 'f':
			codigo = KeyEvent.VK_F;
			break;
		case 'g':
			codigo = KeyEvent.VK_G;
			break;
		case 'h':
			codigo = KeyEvent.VK_H;
			break;
		case 'i':
			codigo = KeyEvent.VK_I;
			break;
		case 'j':
			codigo = KeyEvent.VK_J;
			break;
		case 'k':
			codigo = KeyEvent.VK_K;
			break;
		case 'l':
			codigo = KeyEvent.VK_L;
			break;
		case 'm':
			codigo = KeyEvent.VK_M;
			break;
		case 'n':
			codigo = KeyEvent.VK_N;
			break;
		case 'o':
			codigo = KeyEvent.VK_O;
			break;
		case 'p':
			codigo = KeyEvent.VK_P;
			break;
		case 'q':
			codigo = KeyEvent.VK_Q;
			break;
		case 'r':
			codigo = KeyEvent.VK_R;
			break;
		case 's':
			codigo = KeyEvent.VK_S;
			break;
		case 't':
			codigo = KeyEvent.VK_T;
			break;
		case 'u':
			codigo = KeyEvent.VK_U;
			break;
		case 'v':
			codigo = KeyEvent.VK_V;
			break;
		case 'w':
			codigo = KeyEvent.VK_W;
			break;
		case 'x':
			codigo = KeyEvent.VK_X;
			break;
		case 'y':
			codigo = KeyEvent.VK_Y;
			break;
		case 'z':
			codigo = KeyEvent.VK_Z;
			break;
		case ' ':
			codigo = KeyEvent.VK_SPACE;
			break;
		case '-':
			codigo = KeyEvent.VK_MINUS;
			break;
		case '.':
			codigo = 46;
			break;
		// case '\\': codigo = KeyEvent.VK_ALT_GRAPH; break;
		case '_':
			inverso = true;
			codigo = KeyEvent.VK_MINUS;
			break;
		case ':':
			inverso = true;
			codigo = 46;
			break;
		case 'A':
			inverso = true;
			codigo = KeyEvent.VK_A;
			break;
		case 'B':
			inverso = true;
			codigo = KeyEvent.VK_B;
			break;
		case 'C':
			inverso = true;
			codigo = KeyEvent.VK_C;
			break;
		case 'D':
			inverso = true;
			codigo = KeyEvent.VK_D;
			break;
		case 'E':
			inverso = true;
			codigo = KeyEvent.VK_E;
			break;
		case 'F':
			inverso = true;
			codigo = KeyEvent.VK_F;
			break;
		case 'G':
			inverso = true;
			codigo = KeyEvent.VK_G;
			break;
		case 'H':
			inverso = true;
			codigo = KeyEvent.VK_H;
			break;
		case 'I':
			inverso = true;
			codigo = KeyEvent.VK_I;
			break;
		case 'J':
			inverso = true;
			codigo = KeyEvent.VK_J;
			break;
		case 'K':
			inverso = true;
			codigo = KeyEvent.VK_K;
			break;
		case 'L':
			inverso = true;
			codigo = KeyEvent.VK_L;
			break;
		case 'M':
			inverso = true;
			codigo = KeyEvent.VK_M;
			break;
		case 'N':
			inverso = true;
			codigo = KeyEvent.VK_N;
			break;
		case 'O':
			inverso = true;
			codigo = KeyEvent.VK_O;
			break;
		case 'P':
			inverso = true;
			codigo = KeyEvent.VK_P;
			break;
		case 'Q':
			inverso = true;
			codigo = KeyEvent.VK_Q;
			break;
		case 'R':
			inverso = true;
			codigo = KeyEvent.VK_R;
			break;
		case 'S':
			inverso = true;
			codigo = KeyEvent.VK_S;
			break;
		case 'T':
			inverso = true;
			codigo = KeyEvent.VK_T;
			break;
		case 'U':
			inverso = true;
			codigo = KeyEvent.VK_U;
			break;
		case 'V':
			inverso = true;
			codigo = KeyEvent.VK_V;
			break;
		case 'W':
			inverso = true;
			codigo = KeyEvent.VK_W;
			break;
		case 'X':
			inverso = true;
			codigo = KeyEvent.VK_X;
			break;
		case 'Y':
			inverso = true;
			codigo = KeyEvent.VK_Y;
			break;
		case 'Z':
			inverso = true;
			codigo = KeyEvent.VK_Z;
			break;
		case 'á':
			acento = true;
			codigo = KeyEvent.VK_A;
			break;
		case 'é':
			acento = true;
			codigo = KeyEvent.VK_E;
			break;
		case 'í':
			acento = true;
			codigo = KeyEvent.VK_I;
			break;
		case 'ó':
			acento = true;
			codigo = KeyEvent.VK_O;
			break;
		case 'ú':
			acento = true;
			codigo = KeyEvent.VK_U;
			break;

		case '0':
			codigo = KeyEvent.VK_0;
			break;
		case '1':
			codigo = KeyEvent.VK_1;
			break;
		case '2':
			codigo = KeyEvent.VK_2;
			break;
		case '3':
			codigo = KeyEvent.VK_3;
			break;
		case '4':
			codigo = KeyEvent.VK_4;
			break;
		case '5':
			codigo = KeyEvent.VK_5;
			break;
		case '6':
			codigo = KeyEvent.VK_6;
			break;
		case '7':
			codigo = KeyEvent.VK_7;
			break;
		case '8':
			codigo = KeyEvent.VK_8;
			break;
		case '9':
			codigo = KeyEvent.VK_9;
			break;

		default:
			// codigo = KeyEvent.VK_C;
		}

		this.imprimeChar(inverso, codigo, acento);

	}

	private boolean compruebaAsociar(String tituloCDU) {
		if (InicioIanus.tipoSubida.equals("HOS")) {
			if (Inicio.inicioIanus.titHosp.containsKey(tituloCDU)) {
				return true;
			}

		} else if (InicioIanus.tipoSubida.equals("CEX")
				|| InicioIanus.tipoSubida.equals("EPI")) {
			if (Inicio.inicioIanus.titCons.containsKey(tituloCDU)) {
				return true;
			}
		} else if (InicioIanus.tipoSubida.equals("URG")) {
			if (Inicio.inicioIanus.titUrg.containsKey(tituloCDU)) {
				return true;
			}
		} else if (InicioIanus.tipoSubida.equals("QUI")) {
			if (Inicio.inicioIanus.titQui.containsKey(tituloCDU)) {
				return true;
			}
		} else if (InicioIanus.tipoSubida.equals("CIA")) {
			if (Inicio.inicioIanus.titCIA.containsKey(tituloCDU)) {
				return true;
			}
		}
		return false;
	}
}
