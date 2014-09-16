package es.mgamallo.prometeo;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import org.jnativehook.GlobalScreen;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class VentanaExplorador extends javax.swing.JFrame {

    private Component jScrollPane1;
	/**
     * Creates new form VentanaExplorador
     */
    public VentanaExplorador() {
       
         initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listaPdfs = new javax.swing.JList();
        
  
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        
 
  /*             
        jMenuItem1.setText("Abrir Carpeta");
        jMenuItem1.setToolTipText("A�ade el nombre del usuario a la carpeta seleccionada");
        jMenuItem1.addActionListener(
        		new ActionListener(){
        			public void actionPerformed(ActionEvent evento){

        				
        				CargaListaPdfs pdfs = new CargaListaPdfs(true);
        				if(pdfs.cargado == true){
        					int tama�o = pdfs.nombrePdfs.length;
        					tama�oLista = tama�o;
        					InicioIanus.rutaCompletaPdfs = new String[tama�o];
        					rutaCompletaPdfs = new String[tama�o];
        					objetoPuente = new Object[tama�oLista];	//	Para pasar los datos a un jOptionPane (ya subidos)
    					
        					modelo = new DefaultListModel();

        					//	Almacena las carpetas por las que navega el usuario
        					if(tama�o>0){
        						String aux = pdfs.rutaPdfs[0];
        						int auxInt = aux.lastIndexOf("\\");
        						aux = aux.substring(0,auxInt);
        						auxInt = aux.lastIndexOf("\\");
        						aux = aux.substring(0, auxInt);
        					//	System.out.println(aux);
        						InicioIanus.carpetasAbiertas.add(aux);
        					}
        					
        					if(InicioIanus.documentacion == 2 || InicioIanus.documentacion == 3){    // Si utilizamos el m�todo experimental
        						InicioIanus.documentos = new DocumentoProp[tama�o];  
        					}
        					
        					modelo = new DefaultListModel();
        					for(int i=0;i<tama�o;i++){
        						objetoPuente[i] = pdfs.nombrePdfs[i];
        						rutaCompletaPdfs[i] = pdfs.rutaPdfs[i];
        						InicioIanus.rutaCompletaPdfs[i] = pdfs.rutaPdfs[i];
        						if(InicioIanus.documentacion == 2 || InicioIanus.documentacion == 3){
        							//System.out.println("fichero numero... " + i);
            						InicioIanus.documentos[i] = new DocumentoProp(InicioIanus.rutaCompletaPdfs[i]);
            						//InicioIanus.documentos[i].imprimePropiedades();
        						}
        						if(InicioIanus.documentacion == 2 || InicioIanus.documentacion == 3){
        							
        							String nhc = InicioIanus.documentos[i].nhc;
        							String espaciosEnBlanco = "";
        							int numDigitos = 9 - nhc.length();
        							for(int z = 0;z<numDigitos;z++){
        								espaciosEnBlanco += "_";
        							}
        							nhc = espaciosEnBlanco + nhc;
        							
        							String nombreFormateado = pdfs.nombrePdfs[i].substring(0, 4) + " " + nhc + "   " +
        									InicioIanus.documentos[i].nombreNormalizado;
        							modelo.addElement(nombreFormateado);
        						}
        						else{
        							modelo.addElement(pdfs.nombrePdfs[i]);
        						}
        						
        						
        						
        					}
 
        					//	Determina el directorio firmados
        					        					
        					listaPdfs.setModel(modelo);
  //      					listaPdfs.setFont(new Font("Arial",Font.BOLD,10));
        			    	setTitle(pdfs.getRutaCarpeta());
        					InicioIanus.ficherosCargados= true;
        				}
        		        if(InicioIanus.ficherosCargados){
        		        	if(InicioIanus.ventanaNombresAbierta == false){
	        		        	java.awt.EventQueue.invokeLater(new Runnable() {
	
	        		        		public void run() {
	        		        	        jMenu3.setEnabled(true);
	        		        	        jMenu2.setEnabled(true);
	        		        	        jMenuItem51.setEnabled(true);

	        		        			InicioIanus.ventanaD = new InterFazTabla();
	        		        			InicioIanus.ventanaD.setBounds(Inicio.coordenadasVentanas.vTabla);
	        		        			InicioIanus.ventanaD.setVisible(true);
	        		        			if(InicioIanus.documentacion == 0 || InicioIanus.documentacion == 3){
	        		        				InicioIanus.ventanaBotonesTeclas = new VentanaBotonesDocMini();
	        		        				InicioIanus.ventanaBotonesTeclas.setBounds(Inicio.coordenadasVentanas.vTeclas);
	        		        				InicioIanus.ventanaBotonesTeclas.comboVentanas.setSelectedItem(InicioIanus.ventanaBotonesTeclaAtributo);
	        		        			}else{
	        		        				InicioIanus.ventanaBotonesTeclas = new VentanaBotonesDocMini();
	        		        				InicioIanus.ventanaBotonesTeclas.setBounds(Inicio.coordenadasVentanas.vTeclas);
	        		        				InicioIanus.ventanaBotonesTeclas.comboVentanas.setSelectedItem(InicioIanus.ventanaBotonesTeclaAtributo);
	        		        			}
	        		        			
	        		        		}
	        		        	});
	        		        	InicioIanus.ventanaNombresAbierta = true;
	        		        	
        		        	}
        		        }
        		        
        		        */
        			}
        		});
        

        

        
        
        
 
 
        
        
        jMenuItem22.addActionListener(new ActionListener(){			//	Ya subidos

			@Override
			public void actionPerformed(ActionEvent evento) {
				// TODO Auto-generated method stub
					            
	            
				Inicio.coordenadasVentanas.guardarCoordenadasVentana("Coordenadas.xls",InicioIanus.usuario,InicioIanus.numeroPantallas, InicioIanus.numeroIanus);
				
				Inicio.navegador1.frame.dispose();
  				Inicio.navegador2.frame.dispose();
				
  				/*
				Inicio.navegador1.webBrowser.navigate("");
  	  			Inicio.navegador1.webBrowser.setVisible(false);
  	  			
  				if(InicioIanus.numeroIanus == 2){
  					Inicio.navegador2.webBrowser.navigate("");
  	  				Inicio.navegador2.webBrowser.setVisible(false);
  				}
				*/
  				
  				
  				
				Object seleccion = JOptionPane.showInputDialog(null,"Seleccione el �ltimo documento subido","Documentos ya subidos",JOptionPane.QUESTION_MESSAGE,null,objetoPuente,listaPdfs.getSelectedValue());
				if(seleccion.toString() != null){
					CerrarTodo cerrar = new CerrarTodo();
					cerrar.close();
					

					
	        		FicheroTXT fTxt = new FicheroTXT();										//	Guarda las estadisticas en un fichero txt
	        		fTxt.escribeTXT();
	        		
	        		// GestionEstadistica ge = new GestionEstadistica();						
	        		// GuardarEstadisticaSantiago ges = new GuardarEstadisticaSantiago();		//	Guarda las estadisticas en un fichero excel
	        		// ges.leerExcel(InicioIanus.NOMBRE_FICHERO_EXCEL_ESTADISTICA,false);
					
					int indice = objetoPuente.length;
					int i=0;
					while(!(objetoPuente[i].toString().contains(seleccion.toString()))&& i<indice){
						i++;
					}
					MoverCarpetas mv = new MoverCarpetas();
					mv.moverPdfs(InicioIanus.rutaCompletaPdfs[0].toString(),i);

					
					
	            	mv.moverDudas();
	            	if(InicioIanus.documentacion != 0 && InicioIanus.documentacion != 3 ){
	                	mv.mover();
	            	}else{
	            		mv.moverCarpetasUrg();
	            	}
	            	
	            	System.exit(0);
	            	
	            	
				}
			}
        	
        });
        
        
    
               
 
        
        

 
        

        

        
       // jMenuItemEstadistica.setEnabled(false);
 
        
        
                
        jScrollPane1.setViewportView(listaPdfs);
 
        

        
        listaPdfs.addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent evt){
        		pulsarListaPdfs();  
        	}
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
        );

        pack();
   /*     if(InicioIanus.coordenadas.grabadas) 
        	setSize(InicioIanus.coordenadas.coordenadas[1].x,InicioIanus.coordenadas.coordenadas[1].y);  */

        
    }// </editor-fold>
    
    
    public void pulsarListaPdfs(){
  		
		numArchivo = listaPdfs.getSelectedIndex();
		InicioIanus.pdfSeleccionado = numArchivo;
		int tama�oLista = listaPdfs.getModel().getSize();

		
		InicioIanus.tandaDePdfs[0] = new File(InicioIanus.rutaCompletaPdfs[numArchivo]);
		
		Inicio.navegador1.webBrowser.setVisible(true);
		Inicio.navegador1.setPdf(InicioIanus.tandaDePdfs[0].getAbsolutePath().toString(),
				InicioIanus.tandaDePdfs[0].getName().toString(), new Color(80,200,120));
			
		InicioIanus.numPdfsAbiertos = 1;   //	N�mero de pdfs abiertos en pantalla
		
		if(InicioIanus.documentacion == 2 || InicioIanus.documentacion == 3){
			InicioIanus.teclaAzul1.setText(InicioIanus.documentos[numArchivo].nhc);
			InicioIanus.teclaAzul2.setText(InicioIanus.documentos[numArchivo].nombreNormalizado);
 			
 			
			if(InicioIanus.ocr2IanusAutomatico){
				InicioIanus.botonIanus1.setText(InicioIanus.nhcIanus1);
     			InicioIanus.botonIanus2.setText(InicioIanus.nhcIanus2);
			}
			else{
				InicioIanus.botonIanus1.setText(InicioIanus.documentos[numArchivo].nhc);
				System.out.println("El boton de ianus1 deber�a poner... " + InicioIanus.documentos[numArchivo].nhc);
			}
 			
 			InicioIanus.botonServicio.setText(InicioIanus.documentos[numArchivo].servicio);
 			InicioIanus.botonNombreNormalizado.setText(InicioIanus.documentos[numArchivo].nombreNormalizado);
 			
 			if(!InicioIanus.comboServicios.getSelectedItem().toString().equals(InicioIanus.documentos[numArchivo].servicio)){
 				InicioIanus.botonServicio.setBackground(Color.red);
 				Inicio.navegador1.ocrPanel.setBackground(Color.yellow);
 			}
 			else{
 				InicioIanus.botonServicio.setBackground(Color.green);
 				Inicio.navegador1.ocrPanel.setBackground(new Color(255,222,173));
 			}
 			
 			if(numArchivo > 1){
 				if(!InicioIanus.documentos[numArchivo].nhc.equals(InicioIanus.documentos[numArchivo-1].nhc)){
     				Inicio.navegador1.ocrPanel.setBackground(Color.red);
     				Inicio.navegador1.webBrowserPanel.setBackground(Color.red);
     			}
     			else{
     				Inicio.navegador1.ocrPanel.setBackground(new Color(255,222,173));
     				Inicio.navegador1.webBrowserPanel.setBackground(new Color(80,200,120));
     			}
 			}
 			
 			
 			InicioIanus.comboServicios.setSelectedItem(InicioIanus.documentos[numArchivo].servicio);
			
			
		}
		        		
		if(triggerVigo){
				docVigo.subirVigo(InicioIanus.tandaDePdfs[0].getName());
				if(VentanaExplorador.errorData){
					docVigo.subirVigoError(InicioIanus.tandaDePdfs[0].getName());
					VentanaExplorador.errorData = false;
				}
			}

		if((numArchivo + 1<tama�oLista) && InicioIanus.numeroIanus == 2){
			numArchivo++;
			listaPdfs.setSelectedIndex(numArchivo);
			
			InicioIanus.tandaDePdfs[1] = new File(InicioIanus.rutaCompletaPdfs[numArchivo]);
			
			Inicio.navegador2.webBrowser.setVisible(true);
			Inicio.navegador2.setPdf(InicioIanus.tandaDePdfs[1].getAbsolutePath().toString(),
					InicioIanus.tandaDePdfs[1].getName().toString(),new Color(255,246,143));
			            			
    		InicioIanus.numPdfsAbiertos++;   //	N�mero de pdfs abiertos en pantalla
    		
    		
		}
    }
    



   
    
    
		    

    
 

    
    
    public void copiarFichero(File f1, File f2){
    	
    	if(!f2.exists()){
    	  	try {
    			InputStream in = new FileInputStream(f1);
    			OutputStream out = new FileOutputStream(f2);
    			
    			byte[] buffer = new byte[1024];
    			int len;
    			
    			while((len = in.read(buffer))>0){
    				out.write(buffer,0,len);
    			}
    			
    			in.close();
    			out.close();
    			
    		} catch (FileNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
  	
    }
    
    
    
    // Variables declaration - do not modify
    static javax.swing.JList listaPdfs;


    


    
    
   
    private DefaultListModel modelo;
    
	String[] rutaCompletaPdfs;
	static int numArchivo=0;
	static int tama�oLista=0;

	

    // End of variables declaration
}



