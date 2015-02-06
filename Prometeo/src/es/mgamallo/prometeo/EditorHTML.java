package es.mgamallo.prometeo;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import org.eclipse.swt.events.DisposeEvent;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.HTMLEditorAdapter;
import chrriis.dj.nativeswing.swtimpl.components.HTMLEditorSaveEvent;
import chrriis.dj.nativeswing.swtimpl.components.JHTMLEditor;
import chrriis.dj.nativeswing.swtimpl.components.JHTMLEditor.HTMLEditorImplementation;

/**
 * @author Christopher Deckers
 */
public class EditorHTML {

  protected static final String LS = System.getProperty("line.separator");
  
  static JFrame frame;

  public static JComponent createContent(final int numeroDeNorma, String texto, String[] servicios, String[] serviciosSelecc) {
	  
	  
	 JList listaServicios = new JList(); 
	 JScrollPane scroll = new JScrollPane();

	 DefaultListModel listaModelNombresServicio = new DefaultListModel(); 
	 listaModelNombresServicio.addElement("Todos");
	 for(int i=0;i<servicios.length;i++){
		 listaModelNombresServicio.addElement(servicios[i]);
	 }

	 listaServicios.setModel(listaModelNombresServicio);
	 listaServicios.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	 
	 int indices[] = new int[serviciosSelecc.length];
	 for(int i=0, j= 0;i<serviciosSelecc.length;i++){
		 for(;j<listaModelNombresServicio.size();j++){
			 if(listaModelNombresServicio.getElementAt(j).equals(serviciosSelecc[i])){
				 indices[i] = j;
				 break;
			 }
		 }
	 }
	 
	 listaServicios.setSelectedIndices(indices);
	 scroll.setViewportView(listaServicios);
	  
    final JPanel contentPane = new JPanel(new BorderLayout());
    
    // Replace certain default options.
    String configurationScript =
      "FCKConfig.ToolbarSets[\"Default\"] = [\n" +
      "['Save','NewPage','Preview','-','Templates'],\n" +
      "['Cut','Copy','Paste','PasteText','PasteWord','-','Print','SpellCheck'],\n" +
      "['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat', '-', 'ShowBlocks','Source','DocProps'],\n" +
  //    "['Form','Checkbox','Radio','TextField','Textarea','Select','Button','ImageButton','HiddenField'],\n" +
      "'/',\n" +
      "['Style','FontFormat','FontName','FontSize'],\n" +
      "['TextColor','BGColor'],\n" +
      "'/',\n" +
      "['Bold','Italic','Underline','StrikeThrough','-','TextColor','BGColor','-','Subscript','Superscript'],\n" +
      "['OrderedList','UnorderedList','-','Outdent','Indent','Blockquote'],\n" +
      "['JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'],\n" +
      "['Link','Unlink','Anchor'],\n" +
      "['Image','Flash','Table','Rule','Smiley','SpecialChar','PageBreak'],\n" +
      "];\n" +
      "FCKConfig.ToolbarCanCollapse = false;\n";
   
    final JHTMLEditor htmlEditor = new JHTMLEditor(HTMLEditorImplementation.FCKEditor, JHTMLEditor.FCKEditorOptions.setCustomJavascriptConfiguration(configurationScript));
    htmlEditor.addHTMLEditorListener(new HTMLEditorAdapter() {
      @Override
      public void saveHTML(HTMLEditorSaveEvent e) {
    	  
    	String textoAGrabar = htmlEditor.getHTMLContent();
    	
    	// textoAGrabar = textoAGrabar.replace("\"", "'");
    	textoAGrabar = textoAGrabar.replaceAll("&quot;", "-");
    	
    	System.out.println(textoAGrabar);
    	
    	Inicio.listaNormasIanus.get(numeroDeNorma).texto = textoAGrabar;
    	
    	guardarCambios(numeroDeNorma);
    	
    	frame.dispose();
      }
    });
    
    contentPane.add(htmlEditor, BorderLayout.CENTER);
    
    JPanel southPanel = new JPanel(new BorderLayout());
  //  southPanel.setBorder(BorderFactory.createTitledBorder("Servicios"));
    
   // JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
  //  middlePanel.add(scroll);
    
    southPanel.add(scroll, BorderLayout.EAST);

    contentPane.add(southPanel, BorderLayout.EAST);
    

    
    htmlEditor.setHTMLContent(texto);
    return contentPane;
  }
  
  
  private static void guardarCambios(int numeroDeNorma){
	  System.out.println("Guardamos cambios de la norma... " + (numeroDeNorma + 1));
	  Txt.escribirNormasTxt(Inicio.listaNormasIanus.get(numeroDeNorma));
  }
  
  public EditorHTML(final int numeroDeNorma, final String texto, final String[] servicios, final String[] serviciosSelecc){
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
	  
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	          frame = new JFrame("DJ Native Swing Test");
	          frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	          frame.getContentPane().add(createContent(numeroDeNorma,texto,servicios,serviciosSelecc), BorderLayout.CENTER);
	          frame.setSize(900, 600);
	          frame.setLocationByPlatform(true);
	          frame.setVisible(true);
	        }
	      });
  }

  /* Standard main method to try that test as a standalone application. */
  /*
  public static void main(String[] args) {
    NativeInterface.open();
    UIUtils.setPreferredLookAndFeel();
    
    final String[] cadenas = {"avion","camion"};
    
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame("DJ Native Swing Test");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(createContent("Es un lolailo",cadenas), BorderLayout.CENTER);
        frame.setSize(900, 600);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
      }
    });
    NativeInterface.runEventPump();
    
  }
	*/
}