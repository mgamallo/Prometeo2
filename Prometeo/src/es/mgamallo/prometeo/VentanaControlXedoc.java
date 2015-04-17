package es.mgamallo.prometeo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class VentanaControlXedoc extends JFrame{

	private Point coordenadasRaton = new Point();

	ActiveXComponent bandejaXedoc1;
	ActiveXComponent xedoc2;
	
	JPanel panel1 = new JPanel();
	JPanel panelMover = new JPanel();
	
	JPanel panel2 = new JPanel(new FlowLayout());
	JButton jBiniciar = new JButton("Iniciar");
	JButton jBxedoc1 = new JButton("Xedoc 1");
	JButton jBxedoc2 = new JButton("Xedoc 2");
	JButton jBianus = new JButton("Ianus");
	JButton jBbandeja1 = new JButton("Bandeja 1");
	JButton jBprometeo = new JButton("Prometeo");
	JButton jBmaquetar = new JButton("Maquetar Todo");
	JButton jBpdf1 = new JButton("Recargar Xedoc 1");
	JButton jBpdf2 = new JButton("Recargar Xedoc 2");
	JButton jBsalir = new JButton("Salir");
	JButton jBpegarTitulo = new JButton("Pegar Titulo");
	JButton jBmaquetarIndividual = new JButton("Maquetar 1");
	JButton jBrecargarArbol = new JButton("Recargar árbol");
	JButton jBsaltarPdf = new JButton("Saltar pdf");
	
	JComboBox comboInicio = new JComboBox();
	
	JLabel etiquetaVacia = new JLabel("      ");
	
	static boolean xedoc1inicializado = false;
	
	public VentanaControlXedoc(final ActiveXComponent bandejaXedoc1, final ActiveXComponent xedoc2) {
		// TODO Auto-generated constructor stub
		
		this.bandejaXedoc1 = bandejaXedoc1;
		this.xedoc2 = xedoc2;
		
		
		setSize(550,100);
		setResizable(false);
		getContentPane().add(panel1);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(true);
		setUndecorated(true);
		setLocation(1324, 950);
		
		

		

		
		panel1.setBackground(Color.blue);
		panel1.setLayout(new BorderLayout());
		panel1.add(panel2,BorderLayout.CENTER);
		panel1.add(panelMover, BorderLayout.WEST);
		
		panel2.setBackground(Color.pink);
		panel2.add(comboInicio);
		panel2.add(jBiniciar);
	//	panel2.add(jBmaquetar);
		panel2.add(jBxedoc1);
		panel2.add(jBxedoc2);
		panel2.add(jBbandeja1);
		panel2.add(jBianus);
		panel2.add(jBprometeo);
		panel2.add(jBpdf1);
		panel2.add(jBpdf2);

		panel2.add(jBpegarTitulo);
		panel2.add(jBmaquetarIndividual);
		panel2.add(jBrecargarArbol);
		panel2.add(jBsaltarPdf);
		
		panel2.add(jBsalir);
		
		
		Dispatch documento = Dispatch.call(bandejaXedoc1, "document").getDispatch();
		Dispatch tabla = Dispatch.call(documento,"getElementById","row").getDispatch();
		Dispatch filas = Dispatch.call(tabla,"getElementsByTagName","tr").getDispatch();
		String numFilas = Dispatch.get(filas,"length").toString();
		int numeroFilas = Integer.valueOf(numFilas) - 1;
		System.out.println("Numero de pdfs es... " + numeroFilas);
		
		for(int i=0;i<numeroFilas;i++){
			comboInicio.addItem(String.valueOf(i+1));
		}
		
		comboInicio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		jBiniciar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Inicializa bandejas xedoc.");
				
				int pdfSeleccionado = comboInicio.getSelectedIndex();
				
				System.out.println("Seleccionado el pdf numero ... " + pdfSeleccionado);
				
				GestionJacobXedoc.capturaBandeja(bandejaXedoc1, xedoc2, "Xedoc 1", "Xedoc 2", pdfSeleccionado, pdfSeleccionado + 1);
								
				xedoc1inicializado = true;
				
				jBmaquetar.setBackground(Color.green);
				jBiniciar.setBackground(Color.DARK_GRAY);
				jBiniciar.setEnabled(false);
			}
		});
		
		jBxedoc1.setBackground(Color.darkGray);
		jBxedoc1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(xedoc1inicializado){
					if(jBxedoc1.getBackground() == Color.DARK_GRAY){
						Dispatch.put(Inicio.documento1.xedoc, "visible","true");
						jBxedoc1.setBackground(Color.green);
					}
					else{
						Dispatch.put(Inicio.documento1.xedoc, "visible","false");
						jBxedoc1.setBackground(Color.DARK_GRAY);
					}
						
				}

			}
		});
		
		jBxedoc2.setBackground(Color.darkGray);
		jBxedoc2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(xedoc1inicializado){
					if(jBxedoc2.getBackground() == Color.DARK_GRAY){
						Dispatch.put(Inicio.documento2.xedoc, "visible","true");
						jBxedoc2.setBackground(Color.green);
					}
					else{
						Dispatch.put(Inicio.documento2.xedoc, "visible","false");
						jBxedoc2.setBackground(Color.DARK_GRAY);
					}
				}
			}
		});
		
		jBianus.setBackground(Color.DARK_GRAY);
		jBianus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			if(jBianus.getBackground() == Color.DARK_GRAY){
				Dispatch.put(GestionJacobXedoc.ianusApoyoXedoc, "visible","true");
				jBianus.setBackground(Color.green);
			}
			else{
				Dispatch.put(GestionJacobXedoc.ianusApoyoXedoc, "visible","false");
				jBianus.setBackground(Color.DARK_GRAY);
			}
				

			}
		});
		
		
		jBbandeja1.setBackground(Color.green);
		jBbandeja1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			if(jBbandeja1.getBackground() == Color.DARK_GRAY){
				Dispatch.put(GestionJacobXedoc.bandejaXedoc1, "visible","true");
				jBbandeja1.setBackground(Color.green);
			}
			else{
				Dispatch.put(GestionJacobXedoc.bandejaXedoc1, "visible","false");
				jBbandeja1.setBackground(Color.DARK_GRAY);
			}
				

			}
		});
		
				
		
		jBprometeo.setBackground(Color.darkGray);
		jBprometeo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			if(jBprometeo.getBackground() == Color.DARK_GRAY){
				Inicio.panelPrincipal.frame.setVisible(true);
				jBprometeo.setBackground(Color.green);
			}
			else{
				Inicio.panelPrincipal.frame.setVisible(false);
				jBprometeo.setBackground(Color.DARK_GRAY);
			}
				

			}
		});
		
		
		jBpdf1.setBackground(Color.darkGray);
		jBpdf1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dispatch.call(Inicio.documento1.xedoc, "refresh");

			}
		});
		
		jBpdf2.setBackground(Color.darkGray);
		jBpdf2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dispatch.call(Inicio.documento2.xedoc, "refresh");

			}
		});
		
		
		jBmaquetar.setBackground(Color.darkGray);
		jBmaquetar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			if(jBmaquetar.getBackground() == Color.DARK_GRAY){
				jBmaquetar.setBackground(Color.green);
				
			}
			else{
				Inicio.panelPrincipal.frame.setVisible(false);
				jBmaquetar.setBackground(Color.DARK_GRAY);
				
				new MaquetadoXedoc(Inicio.documento1.xedoc, "Xedoc 1");
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				new MaquetadoXedoc(Inicio.documento2.xedoc, "Xedoc 2");
				Dispatch.put(Inicio.documento2.xedoc,"visible","false");
			}
				

			}
		});
		
		jBsalir.setBackground(Color.orange);
		jBsalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i=0;i<3;i++){
					Cerrar.cerrarIexplorer();
				}
				
				Inicio.panelPrincipal.frame.setVisible(true);
				dispose();

			}
		});
		
		
		jBpegarTitulo.setBackground(Color.cyan);
		jBpegarTitulo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ActiveXComponent xedoc;
				
				if(Inicio.xedoc1onTop){
					xedoc = Inicio.documento1.xedoc;
				}
				else{
					xedoc = Inicio.documento2.xedoc;
				}
				
				
				MaquetadoXedoc maquetadoSoloTitulo = new MaquetadoXedoc(xedoc, true);
				maquetadoSoloTitulo.putTitulo();
			}
		});
		
		jBmaquetarIndividual.setBackground(Color.cyan);
		jBmaquetarIndividual.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ActiveXComponent xedoc;
				String texto = "Xedoc ";
				if(Inicio.xedoc1onTop){
					xedoc = Inicio.documento1.xedoc;
					texto += "1";
				}
				else{
					xedoc = Inicio.documento2.xedoc;
					texto += "2";
				}
				
				new MaquetadoXedoc(xedoc, texto);
			}
		});
		
		jBrecargarArbol.setBackground(Color.cyan);
		jBrecargarArbol.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ActiveXComponent xedoc;
				String texto = "Xedoc ";
				if(Inicio.xedoc1onTop){
					xedoc = Inicio.documento1.xedoc;
					texto += "1";
				}
				else{
					xedoc = Inicio.documento2.xedoc;
					texto += "2";
				}
				
				Dispatch documento = Dispatch.call(xedoc, "document").getDispatch();
				
				Dispatch consultas = Dispatch.call(documento,"getElementById","OTROS-noSeleccionable-rama").getDispatch();
				Dispatch listaConsultas = Dispatch.call(consultas, "getElementsByTagName","li").getDispatch();
				
				int numListas = Integer.valueOf(Dispatch.get(listaConsultas,"length").toString());
				
				for(int i=0,j=0;i<numListas;i++){
					Dispatch lista = Dispatch.get(listaConsultas,String.valueOf(i)).getDispatch();
					String nombreId = Dispatch.get(lista,"id").toString();
					

					Dispatch estiloLista = Dispatch.get(lista,"style").getDispatch();
					Dispatch.put(estiloLista,"display","block");
	
				}
			}
		});
		
		
		jBsaltarPdf.setBackground(Color.magenta);
		jBsaltarPdf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ActiveXComponent xedoc;
				
				String texto = "Xedoc ";
				if(Inicio.xedoc1onTop){
					xedoc = Inicio.documento1.xedoc;
					texto += "1";
				}
				else{
					xedoc = Inicio.documento2.xedoc;
					texto += "2";
				}
				
				Dispatch documento = Dispatch.get(xedoc, "document").getDispatch();
				Dispatch siguiente = Dispatch.call(documento,"getElementById","siguiente").getDispatch();
			
				String contenidoDeSiguiente;
				try {
					contenidoDeSiguiente = Dispatch.get(siguiente,"innerHTML").toString();
					System.out.println("Contenido de siguiente1... " + contenidoDeSiguiente );
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					contenidoDeSiguiente = null;
				}
				
				if(contenidoDeSiguiente != null){
					Dispatch.call(siguiente, "click");
					Inicio.saltarXedoc = true;
				}
			
			}
		});
		
		
		panelMover.setBackground(Color.red);
		panelMover.add(etiquetaVacia);

		panelMover.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getComponent() == panelMover){
					coordenadasRaton = e.getPoint();
					System.out.println("Pinche en el panel");
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		panelMover.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				Point punto = MouseInfo.getPointerInfo().getLocation();
				setLocation(punto.x - coordenadasRaton.x, punto.y
						- coordenadasRaton.y);
			}
		});
		
		setVisible(false);
		
	}

}
