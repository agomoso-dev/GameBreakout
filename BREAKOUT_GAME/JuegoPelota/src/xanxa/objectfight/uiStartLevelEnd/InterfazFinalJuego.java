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
import javax.swing.SwingUtilities;

public class InterfazFinalJuego extends javax.swing.JDialog {
    private Image imagenFondo;

    public InterfazFinalJuego(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Cargar la imagen de fondo
        this.imagenFondo = new ImageIcon(getClass().getResource("/EstadosDelJuego/FinalJuego.jpg")).getImage();

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
        panelFondo.add(Texto2);
        panelFondo.add(btnVolverJugar);
        panelFondo.add(btnSalir);

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
        btnVolverJugar.setBounds(centerX, startY, buttonWidth, buttonHeight);
        btnVolverJugar.setFont(new Font("Segoe UI", Font.PLAIN, (int)(buttonHeight * 0.4)));

        btnSalir.setBounds(centerX, startY + buttonHeight + (int)(panelHeight * 0.05), buttonWidth, buttonHeight);
        btnSalir.setFont(new Font("Segoe UI", Font.PLAIN, (int)(buttonHeight * 0.4)));

        // Ajustar el tamaño y posición de los textos
        int labelWidth = buttonWidth;
        int labelHeight = (int)(panelHeight * 0.15);
        int label1Y = startY - labelHeight * 2 - (int)(panelHeight * 0.05); // Encima del segundo texto
        int label2Y = startY - labelHeight - (int)(panelHeight * 0.05); // Justo encima de los botones

        Texto1.setBounds(centerX, label1Y, labelWidth, labelHeight);
        Texto2.setBounds(centerX, label2Y, labelWidth, labelHeight);
        
        // Ajustar el tamaño de la fuente de los textos
        Font labelFont1 = new Font("Segoe UI Emoji", Font.PLAIN, (int)(labelHeight * 0.4));
        Font labelFont2 = new Font("Segoe UI Emoji", Font.PLAIN, (int)(labelHeight * 0.4));
        Texto1.setFont(labelFont1);
        Texto2.setFont(labelFont2);
        
        // Agregar emojis de victoria
        Texto1.setText("🏆 ¡VICTORIA! 🏆");
        Texto2.setText("🌟🌟🌟 ¡FELICIDADES! 🌟🌟🌟");
        
        Texto1.setHorizontalAlignment(SwingConstants.CENTER);
        Texto2.setHorizontalAlignment(SwingConstants.CENTER);

        // Ajustar el texto si es necesario
        adjustFontSize(Texto1, labelWidth);
        adjustFontSize(Texto2, labelWidth);
    }

    private void adjustFontSize(javax.swing.JLabel label, int maxWidth) {
        Font labelFont = label.getFont();
        FontMetrics fm = label.getFontMetrics(labelFont);
        while (fm.stringWidth(label.getText()) > maxWidth) {
            labelFont = new Font(labelFont.getName(), labelFont.getStyle(), labelFont.getSize() - 1);
            label.setFont(labelFont);
            fm = label.getFontMetrics(labelFont);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalir = new javax.swing.JToggleButton();
        btnVolverJugar = new javax.swing.JToggleButton();
        Texto1 = new javax.swing.JLabel();
        Texto2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnVolverJugar.setText("VOLVER A JUGAR");
        btnVolverJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverJugarActionPerformed(evt);
            }
        });

        Texto1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 3, 36)); // NOI18N
        Texto1.setText("ENHORABUENA!!!! LO HAS "+"\n"+"CONSEGUIDO :3 <3 :) :D");
        Texto1.setForeground(new java.awt.Color(255, 255, 102));
        Texto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Texto1.setText("ENHORABUENA!!!! ");

        Texto2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 3, 36)); // NOI18N
        Texto2.setForeground(new java.awt.Color(255, 255, 102));
        Texto2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Texto2.setText("LO HAS CONSEGUIDO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(157, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Texto1)
                        .addGap(177, 177, 177))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Texto2, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVolverJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(216, 216, 216))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(Texto1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Texto2, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVolverJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverJugarActionPerformed
       // Cerrar la ventana actual
        this.dispose();

        // Iniciar el juego (xanxa.objectfight.Main)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    xanxa.objectfight.Main.main(new String[]{});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        });    }//GEN-LAST:event_btnVolverJugarActionPerformed
        
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // Cerrar la aplicación
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(InterfazFinalJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazFinalJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazFinalJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazFinalJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InterfazFinalJuego dialog = new InterfazFinalJuego(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel Texto1;
    private javax.swing.JLabel Texto2;
    private javax.swing.JToggleButton btnSalir;
    private javax.swing.JToggleButton btnVolverJugar;
    // End of variables declaration//GEN-END:variables
}
