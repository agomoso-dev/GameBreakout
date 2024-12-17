/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package xanxa.objectfight.uiStartLevelEnd;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class NivelCompletadoN1 extends javax.swing.JDialog {
    private Image imagenFondo;

    public NivelCompletadoN1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        // Cargar la imagen de fondo
        this.imagenFondo = new ImageIcon(getClass().getResource("/EstadosDelJuego/PausarJuego.jpg")).getImage();

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
        panelFondo.add(BtnNextLevel);
        panelFondo.add(BtnAbandonar);

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
        int buttonHeight = (int)(panelHeight * 0.15);

        // Calcular la posición de los botones
        int centerX = (panelWidth - buttonWidth) / 2;
        int startY = (int)(panelHeight * 0.5); // Mover los botones un poco más abajo

        // Posicionar los botones
        BtnNextLevel.setBounds(centerX, startY, buttonWidth, buttonHeight);
        BtnNextLevel.setFont(new Font("Segoe UI", Font.PLAIN, (int)(buttonHeight * 0.4)));

        BtnAbandonar.setBounds(centerX, startY + buttonHeight + (int)(panelHeight * 0.05), buttonWidth, buttonHeight);
        BtnAbandonar.setFont(new Font("Segoe UI", Font.PLAIN, (int)(buttonHeight * 0.4)));

        // Ajustar el tamaño y posición del texto
        int labelWidth = buttonWidth;
        int labelHeight = (int)(panelHeight * 0.2);
        int labelY = startY - labelHeight - (int)(panelHeight * 0.05); // Justo encima de los botones

        Texto1.setBounds(centerX, labelY, labelWidth, labelHeight);
        
        // Ajustar el tamaño de la fuente del texto
        Font labelFont = new Font("Segoe UI Emoji", Font.PLAIN, (int)(labelHeight * 0.4));
        Texto1.setFont(labelFont);
        Texto1.setText("⭐ NIVEL 1 COMPLETADO ⭐");
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

        BtnNextLevel = new javax.swing.JToggleButton();
        BtnAbandonar = new javax.swing.JToggleButton();
        Texto1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        BtnNextLevel.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        BtnNextLevel.setText("SIGUIENTE NIVEL");
        BtnNextLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNextLevelActionPerformed(evt);
            }
        });

        BtnAbandonar.setText("ABANDONAR");
        BtnAbandonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAbandonarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnAbandonar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BtnNextLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Texto1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Texto1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnNextLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnAbandonar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnNextLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNextLevelActionPerformed
        this.dispose();
    }//GEN-LAST:event_BtnNextLevelActionPerformed

    private void BtnAbandonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAbandonarActionPerformed
        // Cerrar la aplicación
        System.exit(0);
    }//GEN-LAST:event_BtnAbandonarActionPerformed

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
            java.util.logging.Logger.getLogger(NivelCompletadoN1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NivelCompletadoN1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NivelCompletadoN1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NivelCompletadoN1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NivelCompletadoN1 dialog = new NivelCompletadoN1(new javax.swing.JFrame(), true);
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
    private javax.swing.JToggleButton BtnAbandonar;
    private javax.swing.JToggleButton BtnNextLevel;
    private javax.swing.JLabel Texto1;
    // End of variables declaration//GEN-END:variables
}
