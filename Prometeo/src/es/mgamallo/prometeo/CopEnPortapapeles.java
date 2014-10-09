package es.mgamallo.prometeo;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JOptionPane;


/**
 *
 * @author Coco
 */
public class CopEnPortapapeles{
   
 public void copiarAlPortapapeles(String documento){
    Clipboard portapapeles=Toolkit.getDefaultToolkit().getSystemClipboard();
    StringSelection texto=new StringSelection(documento);
    portapapeles.setContents(texto, texto);
 }   
 

}   
    