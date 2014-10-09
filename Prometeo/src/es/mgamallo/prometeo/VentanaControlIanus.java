package es.mgamallo.prometeo;

public class VentanaControlIanus extends javax.swing.JFrame {

    /**
     * Creates new form VentanaControlIanus
     */
    public VentanaControlIanus() {
        initComponents();
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelControlIanus = new javax.swing.JPanel();
        labelNumeroIanus = new javax.swing.JLabel();
        labelVacia1 = new javax.swing.JLabel();
        labelMaximizar = new javax.swing.JLabel();
        labelMinimizar = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        botonNHC = new javax.swing.JButton();
        botonServicio = new javax.swing.JButton();
        botonNombreDocumento = new javax.swing.JButton();
        panelControlesAux = new javax.swing.JPanel();
        labelFecha = new javax.swing.JLabel();
        botonFecha = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        labelNombrePaciente = new javax.swing.JLabel();
        labelCIP = new javax.swing.JLabel();
        labelNSS = new javax.swing.JLabel();
        labelNombreExtraido = new javax.swing.JLabel();
        labelCIPextraido = new javax.swing.JLabel();
        labelNSSextraida = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1024, 260));

        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 220));

        panelControlIanus.setBackground(new java.awt.Color(0, 0, 0));
        panelControlIanus.setPreferredSize(new java.awt.Dimension(1024, 20));

        labelNumeroIanus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelNumeroIanus.setForeground(new java.awt.Color(255, 255, 255));
        labelNumeroIanus.setText("Ianus 1");

        labelVacia1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelVacia1.setForeground(new java.awt.Color(255, 255, 255));

        labelMaximizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelMaximizar.setForeground(new java.awt.Color(255, 255, 255));
        labelMaximizar.setText("X");

        labelMinimizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelMinimizar.setForeground(new java.awt.Color(255, 255, 255));
        labelMinimizar.setText("_");

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

        panelBotones.setBackground(new java.awt.Color(255, 255, 102));
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
        botonNombreDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNombreDocumentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonNHC, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(botonNHC, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(botonNombreDocumento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonServicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelControlesAux.setBackground(new java.awt.Color(255, 204, 153));
        panelControlesAux.setPreferredSize(new java.awt.Dimension(0, 20));

        labelFecha.setText("Fecha seleccionada:");

        botonFecha.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        botonFecha.setText("14 / 08 /1999");

        labelNombrePaciente.setText("Nombre paciente:");

        labelCIP.setText("CIP:");

        labelNSS.setText("NSS:");

        labelNombreExtraido.setText("Ga Gu Pepe");

        labelCIPextraido.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelCIPextraido.setText("710101 GG AU 5 016");

        labelNSSextraida.setText("36/0092549403");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNombrePaciente)
                    .addComponent(labelCIP)
                    .addComponent(labelNSS))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelNombreExtraido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelCIPextraido, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(labelNSSextraida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombrePaciente)
                    .addComponent(labelNombreExtraido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCIP)
                    .addComponent(labelCIPextraido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNSS)
                    .addComponent(labelNSSextraida))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelControlesAuxLayout = new javax.swing.GroupLayout(panelControlesAux);
        panelControlesAux.setLayout(panelControlesAuxLayout);
        panelControlesAuxLayout.setHorizontalGroup(
            panelControlesAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelControlesAuxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                .addComponent(labelFecha)
                .addGap(18, 18, 18)
                .addComponent(botonFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelControlesAuxLayout.setVerticalGroup(
            panelControlesAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelControlesAuxLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelControlesAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelControlesAuxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botonFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelFecha))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelControlesAux, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1004, Short.MAX_VALUE)
                    .addComponent(panelBotones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1004, Short.MAX_VALUE)
                    .addComponent(panelControlIanus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1004, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelControlIanus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelControlesAux, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 231, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 5, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 6, Short.MAX_VALUE)))
        );

        this.setUndecorated(false);
        
        pack();
    }// </editor-fold>                        

    private void botonNombreDocumentoActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        // TODO add your handling code here:
    }                                                    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaControlIanus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaControlIanus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaControlIanus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaControlIanus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaControlIanus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    public javax.swing.JButton botonFecha;
    public javax.swing.JButton botonNHC;
    public javax.swing.JButton botonNombreDocumento;
    public javax.swing.JButton botonServicio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelCIP;
    public javax.swing.JLabel labelCIPextraido;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JLabel labelMaximizar;
    private javax.swing.JLabel labelMinimizar;
    private javax.swing.JLabel labelNSS;
    public javax.swing.JLabel labelNSSextraida;
    public javax.swing.JLabel labelNombreExtraido;
    private javax.swing.JLabel labelNombrePaciente;
    public javax.swing.JLabel labelNumeroIanus;
    private javax.swing.JLabel labelVacia1;
    public javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelControlIanus;
    public javax.swing.JPanel panelControlesAux;
    // End of variables declaration                   
}
