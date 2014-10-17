package es.mgamallo.prometeo;

/*
 * Christopher Deckers (chrriis@nextencia.net)
 * http://www.nextencia.net
 *
 * See the file "readme.txt" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

/**
 * @author Christopher Deckers
 */
public class WebIanus implements MouseListener{

	protected final String LS = System.getProperty("line.separator");
	
	private Point coordenadasRaton = new Point();
	
	public JFrame frame;
	
	public JWebBrowser webBrowser;
	JButton botonNHC;
    JButton botonServicio;
    JButton botonNombreDocumento;
	
	public JPanel panelControl = new JPanel(new BorderLayout());
	public JLabel labelMaximizar;
	public JLabel labelMinimizar;
	private boolean maximizada = false;
	
   	JPanel panelControlIanus = new JPanel(new BorderLayout());
    JLabel labelNumeroIanus = new JLabel();
    JLabel labelVacia1 = new JLabel();
	

	public JComponent createContent(String nombre, Color color) {

		webBrowser = new JWebBrowser();
		webBrowser.navigate("http://ianuschop.sergas.local/ianus_chp_pro/inicio.jsp");
		webBrowser.setMenuBarVisible(false);
		
		JPanel webBrowserPanel = new JPanel(new BorderLayout());
		webBrowserPanel.setBorder(BorderFactory.createTitledBorder(nombre));
		webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
		webBrowserPanel.setBackground(color);

		
		//	Panel Control
		
	       	panelControlIanus = new javax.swing.JPanel();
	        labelNumeroIanus = new javax.swing.JLabel();
	        labelVacia1 = new javax.swing.JLabel();
	        labelMaximizar = new JLabel();
	        labelMinimizar = new JLabel();
	        JPanel panelBotones = new javax.swing.JPanel();
	        botonNHC = new javax.swing.JButton();
	        botonServicio = new javax.swing.JButton();
	        botonNombreDocumento = new javax.swing.JButton();
	        JPanel panelControlesAux = new javax.swing.JPanel();

	        panelControl.setPreferredSize(new java.awt.Dimension(1024, 120));

	        panelControlIanus.setBackground(new java.awt.Color(0, 0, 0));
	        panelControlIanus.setPreferredSize(new java.awt.Dimension(1024, 20));

	        labelNumeroIanus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        labelNumeroIanus.setForeground(new java.awt.Color(255, 255, 255));
	        labelNumeroIanus.setText(nombre);

	        labelVacia1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        labelVacia1.setForeground(new java.awt.Color(255, 255, 255));

	        labelMaximizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        labelMaximizar.setForeground(new java.awt.Color(255, 255, 255));
	        labelMaximizar.setText("X");
	        labelMaximizar.setFocusable(false);
	        labelMaximizar.addMouseListener(this);

	        labelMinimizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
	        labelMinimizar.setForeground(new java.awt.Color(255, 255, 255));
	        labelMinimizar.setText("_");
	        labelMinimizar.setFocusable(false);
	        labelMinimizar.addMouseListener(this);
	        
	        javax.swing.GroupLayout panelControlIanusLayout = new javax.swing.GroupLayout(panelControlIanus);
	        panelControlIanus.setLayout(panelControlIanusLayout);
	        panelControlIanusLayout.setHorizontalGroup(
	            panelControlIanusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(panelControlIanusLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(labelNumeroIanus, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(labelMinimizar)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(labelMaximizar)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(labelVacia1))
	        );
	        panelControlIanusLayout.setVerticalGroup(
	            panelControlIanusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(panelControlIanusLayout.createSequentialGroup()
	                .addGroup(panelControlIanusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(labelNumeroIanus)
	                    .addComponent(labelVacia1)
	                    .addComponent(labelMaximizar)
	                    .addComponent(labelMinimizar))
	                .addGap(0, 5, Short.MAX_VALUE))
	        );

	        panelBotones.setBackground(new Color(255,246,143));
	        panelBotones.setPreferredSize(new java.awt.Dimension(0, 60));

	        botonNHC.setBackground(new java.awt.Color(0, 204, 51));
	        botonNHC.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
	        botonNHC.setText("2.345.678");

	        botonServicio.setBackground(new java.awt.Color(0, 204, 51));
	        botonServicio.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
	        botonServicio.setText("CARC");

	        botonNombreDocumento.setBackground(new java.awt.Color(0, 204, 51));
	        botonNombreDocumento.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
	        botonNombreDocumento.setText("Consentimiento informado");


	        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
	        panelBotones.setLayout(panelBotonesLayout);
	        panelBotonesLayout.setHorizontalGroup(
	            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(panelBotonesLayout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(botonNHC, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(botonServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(botonNombreDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addContainerGap())
	        );
	        panelBotonesLayout.setVerticalGroup(
	            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesLayout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(botonNHC, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
	                    .addComponent(botonNombreDocumento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(botonServicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addContainerGap())
	        );

	        panelControlesAux.setBackground(new java.awt.Color(255, 204, 153));
	        panelControlesAux.setPreferredSize(new java.awt.Dimension(0, 20));

	        javax.swing.GroupLayout panelControlesAuxLayout = new javax.swing.GroupLayout(panelControlesAux);
	        panelControlesAux.setLayout(panelControlesAuxLayout);
	        panelControlesAuxLayout.setHorizontalGroup(
	            panelControlesAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 0, Short.MAX_VALUE)
	        );
	        panelControlesAuxLayout.setVerticalGroup(
	            panelControlesAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGap(0, 20, Short.MAX_VALUE)
	        );

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panelControl);
	        panelControl.setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(panelControlIanus, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
	            .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
	            .addComponent(panelControlesAux, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addComponent(panelControlIanus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(0, 0, 0)
	                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(0, 0, 0)
	                .addComponent(panelControlesAux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(230, Short.MAX_VALUE))
	        );
	        
	        panelControl.addMouseListener(this);
	        
			panelControl.addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseMoved(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseDragged(MouseEvent e) {
					// TODO Auto-generated method stub
					Point punto = MouseInfo.getPointerInfo().getLocation();
					frame.setLocation(punto.x - coordenadasRaton.x, punto.y
							- coordenadasRaton.y);
				}
			});
		
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.add(webBrowserPanel, BorderLayout.CENTER);
		contentPane.add(panelControl, BorderLayout.NORTH);
		return contentPane;
	}

	public WebIanus(final String nombreIanus, final Color color){
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
			    	  
						final String introducirUsuarioPulido = 
								"var framePrincipal = window.frames;" + LS +
								"var frameLogin = framePrincipal['principal'].frames['main'];" + LS +
								"var login = frameLogin.document.getElementById('login');" + LS +
								"var password = frameLogin.document.getElementById('password');" + LS +
								"login.value = 'mgamgul1';" + LS +
								"password.value = 'archivo0';" + LS +
								"frameLogin.aceptar();"
								;  
			    	  
			    	  
			    	frame = new JFrame(nombreIanus);
			        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
			        frame.getContentPane().add(createContent(nombreIanus,color), BorderLayout.CENTER);   
			        frame.setSize(860, 1000);   
			        frame.setMinimumSize(new Dimension(800,300));
			        frame.setLocationByPlatform(true); 
			        
			        int retardo = 5000;
			        if(nombreIanus.equals("Ianus 1")){
			        	// frame.setAlwaysOnTop(true);
			        	retardo = 2000;
			        }
			        
			        Inicio.teclashabilitadas = true;
			        
			        HiloNHCyNodo introUsuario = new HiloNHCyNodo(webBrowser,introducirUsuarioPulido,retardo);
				    introUsuario.start();
			        
			        frame.setUndecorated(true);
			        
			       // webBrowserPanel.setBackground(color);
			        	        
			       frame.setVisible(true); 
			       

			       
			       
			      }   
		    });

	}
	
	/* Standard main method to try that test as a standalone application. */
	/*
	public static void main(String[] args) {
		NativeInterface.open();
		UIUtils.setPreferredLookAndFeel();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame = new JFrame("DJ Native Swing Test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane()
						.add(createContent(), BorderLayout.CENTER);
				frame.setSize(800, 600);
				frame.setLocationByPlatform(true);
				frame.setUndecorated(true);
				frame.setVisible(true);
				
			}
		});
		NativeInterface.runEventPump();
	}
	 */
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getComponent() == panelControl){
			coordenadasRaton = e.getPoint();
			System.out.println("Pinche en el panel");
		}
		if(e.getComponent() == labelMaximizar){
			if(!maximizada){
				frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
				System.out.println("Pinche en maximizar");
				maximizada = true;
			}
			else{
				frame.setExtendedState(Frame.NORMAL);
				System.out.println("Restaurar");
				maximizada = false;
			}
			
			
		}
		if(e.getComponent() == labelMinimizar){
			frame.setState(Frame.ICONIFIED);
			System.out.println("Pinche en minimizar");
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
