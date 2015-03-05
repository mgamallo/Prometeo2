package es.mgamallo.prometeo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
	JLabel labelIntroNHC = new JLabel("Retardo introducir NHC. NO TOCAR.    " + String.valueOf(Retardos.retardoIntroducirNHC));
	
//	JLabel labelAsociar = new JLabel("Retardo para pulsar el botón asociar      " + String.valueOf(InicioIanus.retardoAsociar));
//	JLabel labelVentana = new JLabel("RETARDO ESPERAR A QUE SE DIBUJE LA VENTANA DE ASOCIAR   " + String.valueOf(InicioIanus.retardoAbrirVentanaPropiaAsociar));
//	JLabel labelTitulo = new JLabel("Retardo para pegar el título      " + String.valueOf(InicioIanus.retardoPegarTitulo));
	JLabel labelExplorar = new JLabel("Retardo para pulsar el botón examinar      " + String.valueOf(Retardos.retardoTrasPulsarExaminar));
//	JLabel labelAceptar = new JLabel("Retardo para pulsar el botón aceptar       " + String.valueOf(InicioIanus.retardoAceptar));
	JLabel labelVacio = new JLabel(" ");
	JLabel labelVacio1 = new JLabel(" ");
	JLabel labelVacio2 = new JLabel(" ");
	JLabel labelVacio3 = new JLabel(" ");
	JLabel labelVacio4 = new JLabel(" ");
	
	JButton botonResetear = new JButton("Resetear retardos");
	int retardoInicialCargarPaciente = Retardos.retardoCargarPaciente;
	int retardoInicialExaminar = Retardos.retardoTrasPulsarExaminar;
	
	JSlider sliderCargarPaciente = new JSlider(JSlider.HORIZONTAL,5000,12000,Retardos.retardoCargarPaciente);
	JSlider sliderIntroNHC = new JSlider(JSlider.HORIZONTAL,0,1000,Retardos.retardoIntroducirNHC);
//	JSlider sliderAsociar = new JSlider(JSlider.HORIZONTAL,0,3000,InicioIanus.retardoAsociar);
//	JSlider sliderDibujarVentana = new JSlider(JSlider.HORIZONTAL,0,3000,InicioIanus.retardoAsociar);
//	JSlider sliderTitulo = new JSlider(JSlider.HORIZONTAL,0,1000,InicioIanus.retardoPegarTitulo);
	JSlider sliderExplorar = new JSlider(JSlider.HORIZONTAL,600,2600,Retardos.retardoTrasPulsarExaminar);
//	JSlider sliderAceptar = new JSlider(JSlider.HORIZONTAL,0,1000,InicioIanus.retardoAceptar);

	JPanel panel = new JPanel();
	
	public VentanaRetardos() {
		// TODO Auto-generated constructor stub
		
		setTitle("Temporizadores");
		setSize(600,350);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(labelVacio);
//		panel.add(labelVentana);
		panel.add(labelCargarPaciente);
	    panel.add(sliderCargarPaciente);
//		panel.add(sliderDibujarVentana);
		panel.add(labelVacio1);
//		panel.add(labelAsociar);
//		panel.add(sliderAsociar);
		panel.add(labelVacio2);
//		panel.add(labelTitulo);
//		panel.add(sliderTitulo);

		panel.add(labelExplorar);
		panel.add(sliderExplorar);
		panel.add(labelVacio3);
		panel.add(labelVacio4);
		panel.add(labelIntroNHC);
		panel.add(sliderIntroNHC);
		
//		panel.add(sliderAceptar);
		
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.add(panel,BorderLayout.CENTER);
		
		panelPrincipal.add(botonResetear,BorderLayout.SOUTH);
		
		botonResetear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Retardos.retardoCargarPaciente = retardoInicialCargarPaciente;
				Retardos.retardoTrasPulsarExaminar = retardoInicialExaminar;
				
				sliderExplorar.setValue(retardoInicialExaminar);
				labelExplorar.setText("Retardo para pulsar el botón examinar      " + String.valueOf(Retardos.retardoTrasPulsarExaminar));

				sliderCargarPaciente.setValue(retardoInicialCargarPaciente);
				labelCargarPaciente.setText("Retardo para cargar paciente      " + String.valueOf(Retardos.retardoCargarPaciente));

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
		
/*
 	    sliderDibujarVentana.setMinorTickSpacing(10);

	    sliderDibujarVentana.setMajorTickSpacing(500);
	    // sliderAsociar.setPaintLabels(true);
	    // sliderAsociar.setPaintTicks(true);
	    sliderDibujarVentana.setBackground(Color.white);
	    
	    sliderDibujarVentana.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				InicioIanus.retardoAbrirVentanaPropiaAsociar = (int) sliderDibujarVentana.getValue();
				labelVentana.setText("RETARDO ESPERAR A QUE SE DIBUJE LA VENTANA DE ASOCIAR   " + String.valueOf(InicioIanus.retardoAbrirVentanaPropiaAsociar));
				//System.out.println(InicioIanus.retardoAsociar);
			}
		});
*/	    
	    
	    
	    sliderCargarPaciente.setMinorTickSpacing(500);
	    sliderCargarPaciente.setMajorTickSpacing(5000);
	    sliderCargarPaciente.setPaintLabels(true);
	    sliderCargarPaciente.setPaintTicks(true);
	    sliderCargarPaciente.setBackground(Color.pink);
	    
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
	    sliderExplorar.setPaintTicks(true);
	    sliderExplorar.setBackground(Color.pink);
	    
	    sliderExplorar.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				Retardos.retardoTrasPulsarExaminar = (int) sliderExplorar.getValue();
				labelExplorar.setText("Retardo para pulsar el botón examinar      " + String.valueOf(Retardos.retardoTrasPulsarExaminar));
				//System.out.println(InicioIanus.retardoAsociar);
			}
		});

	    
	    sliderIntroNHC.setMinorTickSpacing(50);
	    sliderIntroNHC.setMajorTickSpacing(250);
	    sliderIntroNHC.setPaintLabels(true);
	    sliderIntroNHC.setPaintTicks(true);
	    sliderIntroNHC.setBackground(Color.pink);
	    
	    sliderIntroNHC.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				Retardos.retardoIntroducirNHC = (int) sliderIntroNHC.getValue();
				labelIntroNHC.setText("Retardo introducir NHC. NO TOCAR.    " + String.valueOf(Retardos.retardoIntroducirNHC));
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
	    panel.setBackground(Color.pink);
		getContentPane().add(panelPrincipal);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new VentanaRetardos();
	}

}
