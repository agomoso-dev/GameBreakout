/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package xanxa.objectfight.uiStartLevelEnd;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author wenfi
 */
public class PausarJuego extends javax.swing.JDialog {
    private Image imagenFondo;

    public PausarJuego(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        // Cargar la imagen de fondo
        this.imagenFondo = new ImageIcon(getClass().getResource("/EstadosDelJuego/InicioJuego.jpg")).getImage();

        // Usar un JPanel personalizado para el fondo del diálogo
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenFondo != null) {
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // Configura el panel de fondo como el contenedor principal
        setContentPane(panelFondo);
        panelFondo.setLayout(null);

        // Agregar los componentes al panel
        panelFondo.add(Texto1);
        panelFondo.add(BtnRetomar);
        panelFondo.add(BtnSalir);
        // Establecer el tamaño del JDialog como un porcentaje de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int dialogWidth = (int) (screenSize.width * 0.5);
        int dialogHeight = (int) (screenSize.height * 0.5);
        setSize(dialogWidth, dialogHeight);

        // Centrar el JDialog en la pantalla
        setLocationRelativeTo(null);

        // Ajustar la posición de los componentes
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                adjustComponentPositions();
            }
        });
    }

    private void adjustComponentPositions() {
       int panelWidth = getContentPane().getWidth();
       int panelHeight = getContentPane().getHeight();

       // Ajustar el tamaño de los botones
       int buttonWidth = (int)(panelWidth * 0.6);
       int buttonHeight = (int)(panelHeight * 0.12);
       int buttonSpacing = (int)(panelHeight * 0.05);

       // Calcular la posición de los botones
       int centerX = (panelWidth - buttonWidth) / 2;
       int startY = (int)(panelHeight * 0.3); // Comenzar los botones al 40% de la altura del panel

       // Posicionar los botones
       BtnRetomar.setBounds(centerX, startY, buttonWidth, buttonHeight);
       BtnSalir.setBounds(centerX, startY + 2 * (buttonHeight + buttonSpacing), buttonWidth, buttonHeight);

       // Ajustar las fuentes de los botones
       BtnRetomar.setFont(new Font("Segoe UI", Font.PLAIN, (int)(buttonHeight * 0.6)));
       BtnSalir.setFont(new Font("Segoe UI", Font.PLAIN, (int)(buttonHeight * 0.6)));

       // Ajustar el tamaño y posición del texto
       int labelWidth = buttonWidth;
       int labelHeight = (int)(panelHeight * 0.15);
       int labelY = (int)(panelHeight * 0.15); // Colocar el texto al 15% de la altura del panel
       Texto1.setBounds(centerX, labelY, labelWidth, labelHeight);

       // Ajustar el tamaño de la fuente del texto
       Font labelFont = new Font("Segoe UI Emoji", Font.PLAIN, (int)(labelHeight * 0.5));
       Texto1.setFont(labelFont);
       Texto1.setText("Pausar Partida");
       Texto1.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto horizontalmente

       // Ajustar el texto si es necesario
       FontMetrics fm = Texto1.getFontMetrics(labelFont);
       while (fm.stringWidth(Texto1.getText()) > labelWidth) {
           labelFont = new Font(labelFont.getName(), labelFont.getStyle(), labelFont.getSize() - 1);
           Texto1.setFont(labelFont);
           fm = Texto1.getFontMetrics(labelFont);
       }
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnRetomar = new javax.swing.JButton();
        BtnSalir = new javax.swing.JToggleButton();
        Texto1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        BtnRetomar.setText("RETOMAR PARTIDA");
        BtnRetomar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRetomarActionPerformed(evt);
            }
        });

        BtnSalir.setText("SALIR DEL JUEGO");
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(BtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(BtnRetomar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(Texto1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(Texto1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(BtnRetomar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(BtnSalir)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnRetomarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRetomarActionPerformed
    this.dispose();       // TODO add your handling code here:
    }//GEN-LAST:event_BtnRetomarActionPerformed

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        // Cerrar la aplicación
        System.exit(0);
    }//GEN-LAST:event_BtnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(PausarJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PausarJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PausarJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PausarJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PausarJuego dialog = new PausarJuego(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnRetomar;
    private javax.swing.JToggleButton BtnSalir;
    private javax.swing.JLabel Texto1;
    // End of variables declaration//GEN-END:variables
}
