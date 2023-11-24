/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

/**
 *
 * @author evaas
 */
public class CrearDoc extends javax.swing.JFrame {

    /**
     * Creates new form CrearDoc
     */
    public CrearDoc() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        TUsuario = new javax.swing.JLabel();
        Usuarios = new javax.swing.JComboBox<>();
        TNombre = new javax.swing.JLabel();
        NombrejTextField = new javax.swing.JTextField();
        TTamaño = new javax.swing.JLabel();
        TTipo = new javax.swing.JLabel();
        TipojTextField = new javax.swing.JTextField();
        Cancelar = new javax.swing.JButton();
        Aceptar = new javax.swing.JButton();
        Titulo = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TUsuario.setText("Usuario:");
        jPanel1.add(TUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        Usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuariosActionPerformed(evt);
            }
        });
        jPanel1.add(Usuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 170, 30));

        TNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TNombre.setText("Nombre:");
        jPanel1.add(TNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));
        jPanel1.add(NombrejTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 250, 30));

        TTamaño.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TTamaño.setText("Tamaño:");
        jPanel1.add(TTamaño, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));

        TTipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TTipo.setText("Tipo:");
        jPanel1.add(TTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, -1, -1));

        TipojTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipojTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(TipojTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 120, 30));

        Cancelar.setText("Cancelar");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });
        jPanel1.add(Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, -1, -1));

        Aceptar.setText("Aceptar");
        jPanel1.add(Aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 310, -1, -1));

        Titulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Titulo.setText("Crear Documento");
        jPanel1.add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 130, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsuariosActionPerformed

    private void TipojTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipojTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipojTextFieldActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_CancelarActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(CrearDoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearDoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearDoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearDoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearDoc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JButton Cancelar;
    private javax.swing.JTextField NombrejTextField;
    private javax.swing.JLabel TNombre;
    private javax.swing.JLabel TTamaño;
    private javax.swing.JLabel TTipo;
    private javax.swing.JLabel TUsuario;
    private javax.swing.JTextField TipojTextField;
    private javax.swing.JLabel Titulo;
    private javax.swing.JComboBox<String> Usuarios;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
