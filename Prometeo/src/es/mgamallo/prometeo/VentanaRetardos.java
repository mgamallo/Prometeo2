package es.mgamallo.prometeo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class VentanaRetardos extends JFrame{

	/**
	 * @param args
	 */
	JPanel panelPrincipal = new JPanel();
	
	JLabel labelCargarPaciente = new JLabel("Retardo para cargar paciente      " + String.valueOf(Retardos.retardoCargarPaciente));
	JLabel labelAutomatico = new JLabel("Retardo automático    " + String.valueOf(Retardos.retardoAutomatico));
	
//	JLabel labelAsociar = new JLabel("Retardo para pulsar el botón asociar      " + String.valueOf(InicioIanus.retardoAsociar));
//	JLabel labelVentana = new JLabel("RETARDO ESPERAR A QUE SE DIBUJE LA VENTANA DE ASOCIAR   " + String.valueOf(InicioIanus.retardoAbrirVentanaPropiaAsociar));
//	JLabel labelTitulo = new JLabel("Retardo para pegar el título      " + String.valueOf(InicioIanus.retardoPegarTitulo));
	JLabel labelExplorar = new JLabel("Retardo tras pulsar el botón examinar      " + String.valueOf(Retardos.retardoTrasPulsarExaminar));
//	JLabel labelAceptar = new JLabel("Retardo para pulsar el botón aceptar       " + String.valueOf(InicioIanus.retardoAceptar));
	JLabel labelVacio = new JLabel(" ");
	JLabel labelVacio1 = new JLabel(" ");
	JLabel labelVacio2 = new JLabel(" ");
	JLabel labelVacio3 = new JLabel(" ");
	JLabel labelVacio4 = new JLabel(" ");
	
	JLabel labelTrasPegarRuta = new JLabel("Retardo tras pegar ruta     " + String.valueOf(Retardos.retardoTrasPegarRuta));
	JLabel labelTrasPegarTipo = new JLabel("Retardo tras pegar Tipo    " + String.valueOf(Retardos.retardoTrasPegarTipo));	
	
	JButton botonResetear = new JButton("Resetear retardos");
	JButton modoRapido = new JButton("Rapido");
	JButton modoLento = new JButton("Lento");
	
	int retardoInicialCargarPaciente = Retardos.retardoCargarPaciente;
	int retardoInicialDibujarVentana = Retardos.retardoDibujarVentana;
	int retardoInicialExaminar = Retardos.retardoTrasPulsarExaminar;
	int retardoInicialTrasPegarRuta = Retardos.retardoTrasPegarRuta;
	int retardoInicialTrasPegarTipo = Retardos.retardoTrasPegarTipo;
	
	JSlider sliderCargarPaciente = new JSlider(JSlider.HORIZONTAL,5000,12500,Retardos.retardoCargarPaciente);
	JSlider sliderDibujarVentana = new JSlider(JSlider.HORIZONTAL,500,2000,Retardos.retardoDibujarVentana);
	JSlider sliderAutomatico = new JSlider(JSlider.HORIZONTAL,250,600,Retardos.retardoAutomatico);
//	JSlider sliderAsociar = new JSlider(JSlider.HORIZONTAL,0,3000,InicioIanus.retardoAsociar);
//	JSlider sliderDibujarVentana = new JSlider(JSlider.HORIZONTAL,0,3000,InicioIanus.retardoAsociar);
//	JSlider sliderTitulo = new JSlider(JSlider.HORIZONTAL,0,1000,InicioIanus.retardoPegarTitulo);
	JSlider sliderExplorar = new JSlider(JSlider.HORIZONTAL,600,2600,Retardos.retardoTrasPulsarExaminar);
	JSlider sliderTrasPegarRuta = new JSlider(JSlider.HORIZONTAL,150,550,Retardos.retardoTrasPegarRuta);
	JSlider slidertrasPegarTipo = new JSlider(JSlider.HORIZONTAL,50,550,Retardos.retardoTrasPegarTipo);
//	JSlider sliderAceptar = new JSlider(JSlider.HORIZONTAL,0,1000,InicioIanus.retardoAceptar);

	JPanel panel = new JPanel();
	JPanel panelSur = new JPanel();

	private JLabel labelDibujarVentana = new JLabel("Retardo esperar a que se dibuje la ventana de asociar   " + String.valueOf(Retardos.retardoDibujarVentana));
	
	public VentanaRetardos() {
		// TODO Auto-generated constructor stub
		
		setTitle("Temporizadores");
		setSize(650,450);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		labelCargarPaciente.setFont(new Font("Arial", Font.BOLD, 16));
		labelCargarPaciente.setForeground(Color.RED);
		labelDibujarVentana.setFont(new Font("Arial", Font.BOLD, 16));
		labelDibujarVentana.setForeground(Color.RED);
		labelExplorar.setFont(new Font("Arial", Font.BOLD, 16));
		labelExplorar.setForeground(Color.RED);
		labelTrasPegarRuta.setFont(new Font("Arial", Font.BOLD, 16));
		labelTrasPegarRuta.setForeground(Color.RED);
		labelTrasPegarTipo.setFont(new Font("Arial", Font.BOLD, 16));
		labelTrasPegarTipo.setForeground(Color.RED);
		labelAutomatico.setFont(new Font("Arial", Font.BOLD, 16));
		labelAutomatico.setForeground(Color.RED);
	    
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//		panel.add(labelVacio);
//		panel.add(labelVentana);
		panel.add(labelCargarPaciente);
	    panel.add(sliderCargarPaciente);
	    
		panel.add(labelAutomatico);
		panel.add(sliderAutomatico);
	    
		panel.add(labelDibujarVentana);
		panel.add(sliderDibujarVentana);
//		panel.add(labelAsociar);
//		panel.add(sliderAsociar);
//		panel.add(labelVacio2);
//		panel.add(labelTitulo);
//		panel.add(sliderTitulo);

		panel.add(labelExplorar);
		panel.add(sliderExplorar);
//		panel.add(labelVacio3);
		panel.add(labelTrasPegarRuta);
		panel.add(sliderTrasPegarRuta);
		panel.add(labelTrasPegarTipo);
		panel.add(slidertrasPegarTipo);
//		panel.add(labelVacio4);

		
//		panel.add(sliderAceptar);
		
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.add(panel,BorderLayout.CENTER);
		
		panelSur.setLayout(new BorderLayout());
		panelSur.add(modoRapido,BorderLayout.WEST);
		panelSur.add(botonResetear,BorderLayout.CENTER);
		panelSur.add(modoLento,BorderLayout.EAST);
		
		panelPrincipal.add(panelSur,BorderLayout.SOUTH);
		
		botonResetear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Retardos.retardoCargarPaciente = retardoInicialCargarPaciente;
				Retardos.retardoDibujarVentana = retardoInicialDibujarVentana;
				Retardos.retardoTrasPulsarExaminar = retardoInicialExaminar;
				Retardos.retardoTrasPegarRuta = retardoInicialTrasPegarRuta;
				Retardos.retardoTrasPegarTipo = retardoInicialTrasPegarTipo;
				
				sliderCargarPaciente.setValue(retardoInicialCargarPaciente);
				labelCargarPaciente.setText("Retardo para cargar paciente      " + String.valueOf(Retardos.retardoCargarPaciente));
				
				sliderDibujarVentana.setValue(Retardos.retardoDibujarVentana);
				labelDibujarVentana.setText("Retardo esperar a que se dibuje la ventana de asociar      " + String.valueOf(Retardos.retardoDibujarVentana));
				
				sliderExplorar.setValue(retardoInicialExaminar);
				labelExplorar.setText("Retardo para pulsar el botón examinar      " + String.valueOf(Retardos.retardoTrasPulsarExaminar));

				sliderTrasPegarRuta.setValue(retardoInicialTrasPegarRuta);
				labelTrasPegarRuta.setText("Retardo tras pegar ruta      " + String.valueOf(Retardos.retardoTrasPegarRuta));

				slidertrasPegarTipo.setValue(retardoInicialTrasPegarTipo);
				labelTrasPegarTipo.setText("Retardo tras pegar tipo      " + String.valueOf(Retardos.retardoTrasPegarTipo));

				


			}
		});
		
		modoRapido.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Retardos.retardoDibujarVentana = 700;
				Retardos.retardoTrasPulsarExaminar = 700;
				Retardos.retardoTrasPegarRuta = 150;
				
				sliderDibujarVentana.setValue(Retardos.retardoDibujarVentana);
				labelDibujarVentana.setText("Retardo esperar a que se dibuje la ventana de asociar      " + String.valueOf(Retardos.retardoDibujarVentana));

				sliderExplorar.setValue(Retardos.retardoTrasPulsarExaminar);
				labelExplorar.setText("Retardo para pulsar el botón examinar      " + String.valueOf(Retardos.retardoTrasPulsarExaminar));
	
				sliderTrasPegarRuta.setValue(Retardos.retardoTrasPegarRuta);
				labelTrasPegarRuta.setText("Retardo tras pegar ruta      " + String.valueOf(Retardos.retardoTrasPegarRuta));

			
			}
		});
		
		
		modoLento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Retardos.retardoTrasPulsarExaminar = 1500;
				Retardos.retardoTrasPegarRuta = 500;
				
				sliderExplorar.setValue(Retardos.retardoTrasPulsarExaminar);
				labelExplorar.setText("Retardo para pulsar el botón examinar      " + String.valueOf(Retardos.retardoTrasPulsarExaminar));
	
				sliderTrasPegarRuta.setValue(Retardos.retardoTrasPegarRuta);
				labelTrasPegarRuta.setText("Retardo tras pegar ruta      " + String.valueOf(Retardos.retardoTrasPegarRuta));

			}
		});
		
//	    sliderAsociar.setMinorTickSpacing(10);
//	    sliderAsociar.setMajorTickSpacing(500);
	    // sliderAsociar.setPaintLabels(true);
	    // sliderAsociar.setPaintTicks(true);
//	    sliderAsociar.setBackground(Color.white);
	    
/*	    sliderAsociar.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				InicioIanus.retardoAsociar = (int) sliderAsociar.getValue();
				labelAsociar.setText("Retardo para pulsar el botón asociar      " + String.valueOf(InicioIanus.retardoAsociar));
				//System.out.println(InicioIanus.retardoAsociar);
			}
		});
*/
		

 	    sliderDibujarVentana.setMinorTickSpacing(100);

	    sliderDibujarVentana.setMajorTickSpacing(500);
	    sliderDibujarVentana.setPaintLabels(true);
	    sliderDibujarVentana.setForeground(Color.gray);
	    sliderDibujarVentana.setPaintTicks(true);
	    sliderDibujarVentana.setBackground(Color.white);
	    
	    sliderDibujarVentana.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				Retardos.retardoDibujarVentana = (int) sliderDibujarVentana.getValue();
				labelDibujarVentana.setText("Retardo esperar a que se dibuje la ventana de asociar   " + String.valueOf(Retardos.retardoDibujarVentana));
				//System.out.println(InicioIanus.retardoAsociar);
			}
		});
	    
	    
	    
	    sliderCargarPaciente.setMinorTickSpacing(500);
	    sliderCargarPaciente.setMajorTickSpacing(2500);
	    sliderCargarPaciente.setPaintLabels(true);
	    sliderCargarPaciente.setForeground(Color.gray);
	    sliderCargarPaciente.setPaintTicks(true);
	    sliderCargarPaciente.setBackground(Color.white);
	    
	    sliderCargarPaciente.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				Retardos.retardoCargarPaciente = (int) sliderCargarPaciente.getValue();
				labelCargarPaciente.setText("Retardo para cargar paciente      " + String.valueOf(Retardos.retardoCargarPaciente));
				//System.out.println(InicioIanus.retardoAsociar);
			}
		});
	    
	    sliderExplorar.setMinorTickSpacing(10);
	    sliderExplorar.setMajorTickSpacing(500);
	    sliderExplorar.setPaintLabels(true);
	    sliderExplorar.setForeground(Color.gray);
	    sliderExplorar.setPaintTicks(true);
	    sliderExplorar.setBackground(Color.white);
	    
	    sliderExplorar.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				Retardos.retardoTrasPulsarExaminar = (int) sliderExplorar.getValue();
				labelExplorar.setText("Retardo para pulsar el botón examinar      " + String.valueOf(Retardos.retardoTrasPulsarExaminar));
				//System.out.println(InicioIanus.retardoAsociar);
			}
		});
	    
	    
	    sliderTrasPegarRuta.setMinorTickSpacing(10);
	    sliderTrasPegarRuta.setMajorTickSpacing(100);
	    sliderTrasPegarRuta.setPaintLabels(true);
	    sliderTrasPegarRuta.setForeground(Color.gray);
	    sliderTrasPegarRuta.setPaintTicks(true);
	    sliderTrasPegarRuta.setBackground(Color.white);
	    
	    sliderTrasPegarRuta.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				Retardos.retardoTrasPegarRuta = (int) sliderTrasPegarRuta.getValue();
				labelTrasPegarRuta.setText("Retardo tras pegar ruta      " + String.valueOf(Retardos.retardoTrasPegarRuta));
				//System.out.println(InicioIanus.retardoAsociar);
			}
		});

	    
	    slidertrasPegarTipo.setMinorTickSpacing(10);
	    slidertrasPegarTipo.setMajorTickSpacing(100);
	    slidertrasPegarTipo.setPaintLabels(true);
	    slidertrasPegarTipo.setForeground(Color.gray);
	    slidertrasPegarTipo.setPaintTicks(true);
	    slidertrasPegarTipo.setBackground(Color.white);
	    
	    slidertrasPegarTipo.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				Retardos.retardoTrasPegarTipo = (int) slidertrasPegarTipo.getValue();
				labelTrasPegarTipo.setText("Retardo tras pegar tipo      " + String.valueOf(Retardos.retardoTrasPegarTipo));
				//System.out.println(InicioIanus.retardoAsociar);
			}
		});
	    
	    sliderAutomatico.setMinorTickSpacing(10);
	    sliderAutomatico.setMajorTickSpacing(50);
	    sliderAutomatico.setPaintLabels(true);
	    sliderAutomatico.setForeground(Color.gray);
	    sliderAutomatico.setPaintTicks(true);
	    sliderAutomatico.setBackground(Color.white);
	    
	    sliderAutomatico.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				Retardos.retardoAutomatico = (int) sliderAutomatico.getValue();
				labelAutomatico.setText("Retardo automático    " + String.valueOf(Retardos.retardoAutomatico));
				//System.out.println(InicioIanus.retardoAsociar);
			}
		});
	    
/*
	    sliderTitulo.setMinorTickSpacing(10);
	    sliderTitulo.setMajorTickSpacing(500);
	    // sliderTitulo.setPaintLabels(true);
	    // sliderTitulo.setPaintTicks(true);
	    sliderTitulo.setBackground(Color.white);
		
	    sliderTitulo.addChangeListener(new ChangeListener() {
	 			@Override
	 			public void stateChanged(ChangeEvent arg0) {
	 				// TODO Auto-generated method stub
	 				InicioIanus.retardoPegarTitulo = (int) sliderTitulo.getValue();
	 				labelTitulo.setText("Retardo para pegar el título      " + String.valueOf(InicioIanus.retardoPegarTitulo));
	 				//System.out.println(InicioIanus.retardoAsociar);
	 			}
	 		});
*/
/*
	    sliderAceptar.setMinorTickSpacing(10);
	    sliderAceptar.setMajorTickSpacing(500);
	    // sliderTitulo.setPaintLabels(true);
	    // sliderTitulo.setPaintTicks(true);
	    sliderAceptar.setBackground(Color.white);
		
	    sliderAceptar.addChangeListener(new ChangeListener() {
	 			@Override
	 			public void stateChanged(ChangeEvent arg0) {
	 				// TODO Auto-generated method stub
	 				InicioIanus.retardoAceptar = (int) sliderAceptar.getValue();
	 				labelAceptar.setText("Retardo para pulsar el botón aceptar      " + String.valueOf(InicioIanus.retardoAceptar));
	 				//System.out.println(InicioIanus.retardoAsociar);
	 			}
	 		});
*/	    
	    panel.setBackground(Color.white);
		getContentPane().add(panelPrincipal);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new VentanaRetardos();
	}

}
