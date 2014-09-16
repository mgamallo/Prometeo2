package es.mgamallo.prometeo;
  
import java.awt.AWTException;
import java.awt.BorderLayout;   
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;   
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;   
import java.awt.event.ItemListener;   
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
  
import javax.swing.BorderFactory;   
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;   
import javax.swing.JComboBox;
import javax.swing.JComponent;   
import javax.swing.JFrame;   
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;   
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;   
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//import org.eclipse.swt.widgets.Slider;
  
import chrriis.common.UIUtils;   
import chrriis.common.WebServer;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;   
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;   
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;
import chrriis.dj.nativeswing.swtimpl.demo.examples.webbrowser.ClasspathPages;
  
/**  
 * @author Christopher Deckers  
 */  


public class InterfazPrincipal {   
  
	 public JWebBrowser webBrowserControl;
	 public JWebBrowser webBrowserOperaciones;
	 
	 public JPanel panelPrincipal = new JPanel(new BorderLayout());
	 public JPanel panelControl = new JPanel(new BorderLayout());
	 public JPanel panelOperaciones = new JPanel(new BorderLayout());
	 
	 public boolean barraPanelControlVisible;
		
	 public JFrame frame;

	
	 public JComponent createContent() {  

		 	// panelOperaciones.setBorder(BorderFactory.createTitledBorder("Digitalización"));   
		    
		 	webBrowserControl = new JWebBrowser();
		    webBrowserControl.navigate("K:/Desarrollo/git/Prometeo/Prometeo/Prometeo/Htmls/control/control.html");
		    // webBrowser.navigate("http://ianuschop");
		    // webBrowser.navigate("http://intranetchopo.sergas.local/");
		    //ocrControlPanel.setBorder(BorderFactory.createTitledBorder(""));
		    webBrowserControl.setBarsVisible(false);
		    webBrowserControl.setMenuBarVisible(false);
		    webBrowserControl.setJavascriptEnabled(true);
		    
		    webBrowserControl.addWebBrowserListener(new WebBrowserAdapter() {   
		        @Override  
		        public void commandReceived(WebBrowserCommandEvent e) {   
		          String command = e.getCommand();   
		          Object[] parameters = e.getParameters();   
		          System.out.println(command + (parameters.length > 0? " " + Arrays.toString(parameters): ""));   
		          if("Abrir".equals(command)) {   
		        	  JOptionPane.showMessageDialog(null, "Abrir carpeta");
		          }
		          else if("Salir".equals(command)){
		        	  frame.dispose();
		          }
		        }   
		      });   

    
		    webBrowserOperaciones = new JWebBrowser();
		    //webBrowserOperaciones.navigate(WebServer.getDefaultWebServer().getResourcePathURL("Htmls/usuarios/Digitalizacion", "Index0.html"));
		    webBrowserOperaciones.navigate("K:/Desarrollo/git/Prometeo/Prometeo/Prometeo/Htmls/usuarios/Digitalizacion/Index0.html");
		    webBrowserOperaciones.setBarsVisible(false);
		    webBrowserOperaciones.setMenuBarVisible(false);
		    webBrowserOperaciones.setJavascriptEnabled(true);
		    
		    webBrowserOperaciones.addWebBrowserListener(new WebBrowserAdapter() {   
		        @Override  
		        public void commandReceived(WebBrowserCommandEvent e) {   
		          String command = e.getCommand();   
		          Object[] parameters = e.getParameters();   
		          System.out.println(command + (parameters.length > 0? " " + Arrays.toString(parameters): ""));   
		          if("Paloma".equals(command)) {   
		        	  JOptionPane.showMessageDialog(null, "Abrir carpeta");
		        	  barraPanelControlVisible=true;
		        	  panelControl.setVisible(barraPanelControlVisible);
		          }   
		        }   
		      });   
		    
		    panelOperaciones.add(webBrowserOperaciones,BorderLayout.CENTER);   
		    panelControl.add(webBrowserControl,BorderLayout.CENTER);

		    panelOperaciones.setBackground(Color.yellow);
		    panelControl.setBackground(Color.blue);
		    panelPrincipal.setBackground(Color.green);	
		    
		    panelControl.setMinimumSize(new Dimension(860,100));
		    panelControl.setMaximumSize(new Dimension(860,100));
		    panelControl.setPreferredSize(new Dimension(860,100));
		//    TitledBorder borde = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder());
		//	panelControl.setBorder(borde);
		    panelControl.setVisible(barraPanelControlVisible);
		    
			panelPrincipal.add(panelControl, BorderLayout.NORTH);
			panelPrincipal.add(panelOperaciones, BorderLayout.CENTER);
			//panelPrincipal.setBackground(Color.black);

		    return panelPrincipal;   
	  }   

	  
  public void setPdf(String ruta, String nombrePdf, Color color){
  
	  webBrowserControl.navigate(ruta);
	  
	  TitledBorder tb = BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder() ,
			  "  " + nombrePdf + "        ", TitledBorder.RIGHT, TitledBorder.TOP, new Font("TimesRoman",Font.BOLD,14), Color.black);
	  
	  panelOperaciones.setBorder(tb);
	  panelOperaciones.setBackground(color);

  }
  
  
  
  /* Standard main method to try that test as a standalone application. */  
  public InterfazPrincipal(final String nombreFrame, final Color color, final boolean barraControl) {   
  
	    //UIUtils.setPreferredLookAndFeel();   
	  
	  try {
          for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
              if ("Nimbus".equals(info.getName())) {
                  javax.swing.UIManager.setLookAndFeel(info.getClassName());
                  break;
              }
          }
      } catch (ClassNotFoundException ex) {
          java.util.logging.Logger.getLogger(VentanaExplorador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex) {
          java.util.logging.Logger.getLogger(VentanaExplorador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex) {
          java.util.logging.Logger.getLogger(VentanaExplorador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex) {
          java.util.logging.Logger.getLogger(VentanaExplorador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
	  	  
	  ///////
	  
	    SwingUtilities.invokeLater(new Runnable() {   
	      public void run() {   
	    	frame = new JFrame(nombreFrame);
	    	barraPanelControlVisible = barraControl;
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
	        frame.getContentPane().add(createContent());   
	        frame.setSize(860, 1000);   
	        frame.setMinimumSize(new Dimension(800,300));
	        frame.setLocationByPlatform(true); 
	        
	        panelOperaciones.setBackground(color);
	        frame.setUndecorated(true);
	        frame.setLocationRelativeTo(null);
	        
	        frame.setVisible(true); 
	      }   
	    });   
	  } 
  
}


