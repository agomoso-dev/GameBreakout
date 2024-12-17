/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package xanxa.objectfight.uiStartLevelEnd;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author wenfi
 */
public class InterfazInformacion extends javax.swing.JDialog {

    public InterfazInformacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setGameDescription();
        adjustTextToPanel();
        centerOnScreen();
    }

    private void setGameDescription() {
        String gameDescription = "<html>" +
            "<h2>Breakout</h2>" +
            "<p>Destruye bloques con la pelota usando la barra. Avanza por 5 fases desafiantes:</p>" +
            "<ol>" +
            "<li><b>Clásica:</b> Una barra, una pelota.</li>" +
            "<li><b>2 Barra:</b> Dos barra y una pelota.</li>" +
            "<li><b>1 Barra:</b> Juega con dos pelotas, agrega bloques resistentes.</li>" +
            "<li><b>2 Barras:</b> Dos barras y bloques resistentes.</li>" +
            "<li><b>Final:</b> Todos los desafíos combinados.</li>" +
            "</ol>" +
            "<p>Usa flechas izquierda/derecha. ESC para pausar.</p>" +
            "<p>¿Llegarás a la fase final?</p>" +
            "</body></html>";
        Texto.setText(gameDescription);
    }
     private void adjustTextToPanel() {
        // Ajusta el tamaño del diálogo basado en el contenido del texto
        pack();
        // Asegura un tamaño mínimo y máximo para el diálogo
        setSize(new Dimension(400, 450));
        setMinimumSize(new Dimension(300, 350));
        setMaximumSize(new Dimension(500, 550));
        // Hace que el diálogo sea redimensionable
        setResizable(true);
    }

    private void centerOnScreen() {
        // Obtiene el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Obtiene el tamaño del diálogo
        Dimension dialogSize = getSize();
        // Calcula la posición para centrar el diálogo
        int x = (screenSize.width - dialogSize.width) / 2;
        int y = (screenSize.height - dialogSize.height) / 2;
        // Establece la ubicación del diálogo
        setLocation(x, y);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Texto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Texto.setText("jLabel1");
        Texto.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Texto, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Texto, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(InterfazInformacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazInformacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazInformacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazInformacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InterfazInformacion dialog = new InterfazInformacion(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel Texto;
    // End of variables declaration//GEN-END:variables
}
