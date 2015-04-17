package es.mgamallo.prometeo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class HiloXedoc extends Thread {

	int retardo;

	public HiloXedoc(int retardo) {
		// TODO Auto-generated constructor stub

		this.retardo = retardo;
	}

	public void run() {

		try {
			Robot robot = new Robot();
			robot.delay(retardo);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(1000);
			System.out.println("Presionado enter");
			
			String pin = "1519";
			for(int k = 0; k< pin.length();k++){
				getChar(pin.charAt(k));
				robot.delay(50);
			}
			robot.delay(100);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			// System.out.println("Jacob break al capturar la bandeja " +
			// bandeja);

			// GestionJacob.rescateJacob(nombreIanus);
		}

	}

	public void imprimeChar(boolean inverso, int codigo, boolean acento) {
		try {
			Robot robot = new Robot();

			robot.keyPress(codigo);
			robot.keyRelease(codigo);
			System.out.println("Imprime por defecto: " + ((char) codigo));

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

}
