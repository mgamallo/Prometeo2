package es.mgamallo.prometeo;

import com.sun.jna.*;

public class VisualizaPdfReader {

    public interface autoHotKeyDll extends Library {
        public void ahkExec(WString s);
        public void ahkdll(WString s,WString o,WString p);
        public void addFile(WString s, int a);
        public void ahktextdll(WString s,WString o,WString p);
        public Pointer ahkFunction(WString f,WString p1,WString p2,WString p3,WString p4,WString p5,WString p6,WString p7,WString p8,WString p9,WString p10);
    }

    VisualizaPdfReader(String rutaPdf){
        System.out.println("Loading dll");
        autoHotKeyDll lib = (autoHotKeyDll) Native.loadLibrary("AutoHotkey.dll", autoHotKeyDll.class);
        System.out.println("initialisation");
        lib.ahktextdll(new WString(""),new WString(""),new WString(""));
        try {
			Thread.sleep(100);
			lib.addFile(new WString(System.getProperty("user.dir") + "\\lib.ahk"), 1);
			Thread.sleep(100);
			
			System.out.println("function call");
			System.out.println("return:" + lib.ahkFunction(new WString("function"),new WString(""),new WString(""),new WString(""),new WString(""),new WString(""),new WString(""),new WString(""),new WString(""),new WString(""),new WString("")).getString(0));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        inicio(lib,rutaPdf);
    }
    
    public static void main(String args[]) {

        new VisualizaPdfReader("C:/ianus/prueba.pdf");
        
    }
    
    
    private static void inicio(autoHotKeyDll lib, String fichero)  {
    	
    	/*
    	lib.ahkExec(new WString("Run, AcroRd32.exe \"" + fichero + "\"" ));
    	
    	lib.ahkExec(new WString("WinActivate, ahk_class AcrobatSDIWindow,,,"));
    	Thread.sleep(200);
    	// lib.ahkExec(new WString("WinSet, AlwaysOnTop, On, ahk_class AcrobatSDIWindow"));
    	lib.ahkExec(new WString("WinMove, 0, 0")); 
        // lib.ahkExec(new WString("WinSet, AlwaysOnTop, On, ahk_class AdobeAcrobat"));
       //  lib.ahkExec(new WString("WinSet, AlwaysOnTop, On, ahk_class AcrobatSDIWindow"));
    	*/
    	
    	String cadena = ""
    			+ "IfWinExist, ahk_class AcrobatSDIWindow \n"
    			+ "{ \n WinActivate \n Send ^w \n } \n"
    			+ "Run, AcroRd32.exe \"" + fichero + "\" \n"
    			+ "WinWait ahk_class AcrobatSDIWindow \n"
    			+ "WinActivate \n"
    		//	+ "WinSet, AlwaysOnTop, On, \n"
    			+ "WinMove, , , 0, 120, 850, 1139 \n"
    		//	+ "Send {F8} \n" 
    		//	+ "Send ^w"
    			+ "ControlFocus, OK, Pdfs,"
    			+ "";
    	
    	lib.ahkExec(new WString(cadena ));
    	Inicio.panelPrincipal.frame.setSize(850,100);
    	
    }
    
}