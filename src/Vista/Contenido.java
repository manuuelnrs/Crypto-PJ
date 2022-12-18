package Vista;

import Controlador.Algoritmos;
import Controlador.Graficas;

// GUI Component Library
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Contenido extends javax.swing.JFrame {
    Algoritmos Controlador = new Algoritmos();
    
    // Default Table Models - Vars
    DefaultTableModel dtmEncryption = new DefaultTableModel();
    DefaultTableModel dtmDecryption = new DefaultTableModel();
    DefaultTableModel dtmHash = new DefaultTableModel();
    DefaultTableModel dtmSigning = new DefaultTableModel();
    DefaultTableModel dtmVerifying = new DefaultTableModel();
    
    // Heads Table Models
    public static String[] cabeceraEncryption = {"Encrypt Operations", "Chacha20 256", "AES-EBC 256", "AES-CBC 256", "RSA-OAEP 1024" };
    public static String[] cabeceraDecryption = {"Decrypt Operations", "Chacha20 256", "AES-EBC 256", "AES-CBC 256", "RSA-OAEP 1024" };
    public static String[] cabeceraHash = {"Hash Operations", "SHA-2 384", "SHA-2 512", "SHA-3 384", "SHA-3 512" };
    public static String[] cabeceraSigning = {"Signing Operations", "RSA-PSS 1024", "ECDSA-PF 521", "ECDSA-BF 571" };
    public static String[] cabeceraVerifying = {"Verifying Operations", "RSA-PSS 1024", "ECDSA-PF 521", "ECDSA-BF 571" };
    
    // Function Content GUI Main 
    public Contenido() throws Exception {
        initComponents();
        setModelos();
    }
    
    private void setModelos() throws Exception{
        dtmEncryption.setColumnIdentifiers(cabeceraEncryption);
        dtmDecryption.setColumnIdentifiers(cabeceraDecryption);
        dtmHash.setColumnIdentifiers(cabeceraHash);
        dtmSigning.setColumnIdentifiers(cabeceraSigning);
        dtmVerifying.setColumnIdentifiers(cabeceraVerifying);
        
        JTableEncryption.setModel(dtmEncryption);
        JTableDecryption.setModel(dtmDecryption);
        JTableHash.setModel(dtmHash);
        JTableSigning.setModel(dtmSigning);
        JTableVerifying.setModel(dtmVerifying);
        
        // Tests
        Controlador.EncryptionOperations(); //Encrypt and Decrypt
        Controlador.HashOperations();       //Hash
        Controlador.SigningOperations();    //Signing and Verifying
        
        // SetTableEncryption
        for (String[] TestVectorEncryption : Algoritmos.TestVectorEncryption) {
            String[] datos = new String[TestVectorEncryption.length];
            for (int j = 0; j < TestVectorEncryption.length; j++) {
                datos[j] = TestVectorEncryption[j];
            }
            dtmEncryption.addRow(datos);
        }
        
        // SetTableDecryption
        for (String[] TestVectorDecryption : Algoritmos.TestVectorDecryption) {
            String[] datos = new String[TestVectorDecryption.length];
            for (int j = 0; j < TestVectorDecryption.length; j++) {
                datos[j] = TestVectorDecryption[j];
            }
            dtmDecryption.addRow(datos);
        }
        
        // SetTableHash
        for (String[] TestVectorHash : Algoritmos.TestVectorHash) {
            String[] datos = new String[TestVectorHash.length];
            for (int j = 0; j < TestVectorHash.length; j++) {
                datos[j] = TestVectorHash[j];
            }
            dtmHash.addRow(datos);
        }
        
        // SetTableSigning
        for (String[] TestVectorSigning : Algoritmos.TestVectorSigning) {
            String[] datos = new String[TestVectorSigning.length];
            for (int j = 0; j < TestVectorSigning.length; j++) {
                datos[j] = TestVectorSigning[j];
            }
            dtmSigning.addRow(datos);
        }
        
        // SetTableVerifying
        for (String[] TestVectorVerifying : Algoritmos.TestVectorVerifying) {
            String[] datos = new String[TestVectorVerifying.length];
            for (int j = 0; j < TestVectorVerifying.length; j++) {
                datos[j] = TestVectorVerifying[j];
            }
            dtmVerifying.addRow(datos);
        }
        
    }
    
    public void cerrar(){
        Icon icon = new javax.swing.ImageIcon(getClass().getResource("key64.png"));
        Object[] options = {"OK"};
        int opcion = JOptionPane.showOptionDialog(null,
                   "Comparador de Algoritmos\n--- Elaborado por ---\n   @liiz-durang\n   @NellyPLopezSosa\n   @manuuelnrs","Acerca de",
                   JOptionPane.INFORMATION_MESSAGE,
                   JOptionPane.QUESTION_MESSAGE,
                   icon,
                   options,
                   options[0]);
        if (opcion == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        content = new javax.swing.JPanel();
        ScrollEncryption = new javax.swing.JScrollPane();
        JTableEncryption = new javax.swing.JTable();
        ScrollDecryption = new javax.swing.JScrollPane();
        JTableDecryption = new javax.swing.JTable();
        ScrollHash = new javax.swing.JScrollPane();
        JTableHash = new javax.swing.JTable();
        ScrollSigning = new javax.swing.JScrollPane();
        JTableSigning = new javax.swing.JTable();
        ScrollVerifying = new javax.swing.JScrollPane();
        JTableVerifying = new javax.swing.JTable();
        panelMenu = new javax.swing.JPanel();
        btnEnc = new javax.swing.JButton();
        btnDec = new javax.swing.JButton();
        btnVectores = new javax.swing.JButton();
        lbGraficas = new javax.swing.JLabel();
        btnHash = new javax.swing.JButton();
        btnSign = new javax.swing.JButton();
        btnVerif = new javax.swing.JButton();
        separador = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Algorithm Comparison");
        setIconImage(getIconImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        JTableEncryption.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        ScrollEncryption.setViewportView(JTableEncryption);

        JTableDecryption.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        ScrollDecryption.setViewportView(JTableDecryption);

        JTableHash.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        ScrollHash.setViewportView(JTableHash);

        JTableSigning.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        ScrollSigning.setViewportView(JTableSigning);

        JTableVerifying.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        ScrollVerifying.setViewportView(JTableVerifying);

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollHash)
                    .addComponent(ScrollSigning)
                    .addComponent(ScrollVerifying, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ScrollEncryption, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ScrollDecryption))
                .addContainerGap())
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentLayout.createSequentialGroup()
                .addComponent(ScrollEncryption, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollDecryption, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollHash, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollSigning, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollVerifying, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnEnc.setText("Cifrado");
        btnEnc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncActionPerformed(evt);
            }
        });

        btnDec.setText("Descifrado");
        btnDec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecActionPerformed(evt);
            }
        });

        btnVectores.setText("Vectores de Prueba");
        btnVectores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVectoresActionPerformed(evt);
            }
        });

        lbGraficas.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lbGraficas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbGraficas.setText("Gráficas");
        lbGraficas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnHash.setText("Hash");
        btnHash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHashActionPerformed(evt);
            }
        });

        btnSign.setText("Firma");
        btnSign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignActionPerformed(evt);
            }
        });

        btnVerif.setText("Verificación");
        btnVerif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifActionPerformed(evt);
            }
        });

        separador.setOrientation(javax.swing.SwingConstants.VERTICAL);
        separador.setAlignmentX(1.0F);
        separador.setAlignmentY(1.0F);

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbGraficas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEnc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDec)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHash)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSign)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVerif)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separador, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVectores, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnc)
                    .addComponent(btnDec)
                    .addComponent(btnVectores)
                    .addComponent(lbGraficas)
                    .addComponent(btnHash)
                    .addComponent(btnSign)
                    .addComponent(btnVerif))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(separador)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifActionPerformed
        Graficas.activarGrafico(5);
    }//GEN-LAST:event_btnVerifActionPerformed

    private void btnSignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignActionPerformed
        Graficas.activarGrafico(4);
    }//GEN-LAST:event_btnSignActionPerformed

    private void btnHashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHashActionPerformed
        Graficas.activarGrafico(3);
    }//GEN-LAST:event_btnHashActionPerformed

    private void btnVectoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVectoresActionPerformed
        String vectores = "VECTORES PRUEBA\n[1] "+Algoritmos.input+"\n[2] "+Algoritmos.input2+"\n[3] "+Algoritmos.input3+"\n[4] "+Algoritmos.input4;
        JOptionPane.showMessageDialog(null,vectores, "Test Vectors",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnVectoresActionPerformed

    private void btnDecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecActionPerformed
        Graficas.activarGrafico(2);
    }//GEN-LAST:event_btnDecActionPerformed

    private void btnEncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncActionPerformed
        Graficas.activarGrafico(1);
    }//GEN-LAST:event_btnEncActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrar();
    }//GEN-LAST:event_formWindowClosing

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
        getImage(ClassLoader.getSystemResource("Vista/key64.png"));

       return retValue;
    }

    public static void main(String args[]) {
        /* Custom Theme */
        try {
            
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Contenido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* Display */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Contenido().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Contenido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTableDecryption;
    private javax.swing.JTable JTableEncryption;
    private javax.swing.JTable JTableHash;
    private javax.swing.JTable JTableSigning;
    private javax.swing.JTable JTableVerifying;
    private javax.swing.JScrollPane ScrollDecryption;
    private javax.swing.JScrollPane ScrollEncryption;
    private javax.swing.JScrollPane ScrollHash;
    private javax.swing.JScrollPane ScrollSigning;
    private javax.swing.JScrollPane ScrollVerifying;
    private javax.swing.JButton btnDec;
    private javax.swing.JButton btnEnc;
    private javax.swing.JButton btnHash;
    private javax.swing.JButton btnSign;
    private javax.swing.JButton btnVectores;
    private javax.swing.JButton btnVerif;
    private javax.swing.JPanel content;
    private javax.swing.JLabel lbGraficas;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JSeparator separador;
    // End of variables declaration//GEN-END:variables
}
