/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import EDD.Documento;
import EDD.Usuario;
import Ejecutable.main;
import javax.swing.JOptionPane;

/**
 *
 * @author S. Estefania, G. Angelo y S. Jose
 */
public class InsertarDocCola extends javax.swing.JFrame {

    /**
    * Constructor de la ventana para insertar documentos en la cola de impresión.
    * Inicializa los componentes gráficos y ubica la ventana en el centro de la pantalla.
    * Si la base de usuarios no está vacía, llena el desplegable de usuarios con los nombres
    * de los usuarios disponibles en la tabla hash.
    *
    * @author S. Estefania, G. Angelo y S. Jose
    */
    public InsertarDocCola() {
        initComponents();
        this.setLocationRelativeTo(null);
        if (main.hashtable.getTamaño() == 0) {
            System.out.println("La base de usuarios esta vacia");
        } else {
            for (int i = 0; i < main.hashtable.getTamaño(); i++) {
                if (main.hashtable.arreglo[i] != null) {
                    this.Usuarios.addItem(main.hashtable.arreglo[i].getNombre());
                    Usuario aux = main.hashtable.arreglo[i].getpSig();
                    while (aux != null) {
                        this.Usuarios.addItem(aux.getNombre());
                        aux = aux.getpSig();
                    }
                }
            }
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

        jPanel1 = new javax.swing.JPanel();
        Titulo = new javax.swing.JLabel();
        TUsuario = new javax.swing.JLabel();
        Usuarios = new javax.swing.JComboBox<>();
        TDocumento = new javax.swing.JLabel();
        Documento = new javax.swing.JComboBox<>();
        Enviar = new javax.swing.JButton();
        Cancelar1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Titulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Titulo.setText("Enviar Documento a la Cola de Impresión");
        jPanel1.add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

        TUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TUsuario.setText("Usuario:");
        jPanel1.add(TUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, -1, -1));

        Usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuariosActionPerformed(evt);
            }
        });
        jPanel1.add(Usuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 170, 30));

        TDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TDocumento.setText("Documento:");
        jPanel1.add(TDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, -1, -1));

        Documento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DocumentoActionPerformed(evt);
            }
        });
        jPanel1.add(Documento, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 170, 30));

        Enviar.setText("Enviar");
        Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarActionPerformed(evt);
            }
        });
        jPanel1.add(Enviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, -1, -1));

        Cancelar1.setText("Cancelar");
        Cancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar1ActionPerformed(evt);
            }
        });
        jPanel1.add(Cancelar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, -1, -1));

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * Maneja el evento de clic en el botón "Enviar". Obtiene el usuario y el documento seleccionados
    * en los desplegables, busca el documento en la lista de documentos del usuario y lo inserta en la
    * cola de impresión mediante el montículo binario. Muestra un mensaje informativo y cierra la ventana.
    *
    * @param evt Objeto que contiene información sobre el evento de acción.
    *
    * @author S. Estefania, G. Angelo y S. Jose
    */
    private void EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarActionPerformed
        String usuario = Usuarios.getSelectedItem().toString();
        Usuario actual = main.hashtable.buscarUsuario(usuario);
        String doc= Documento.getSelectedItem().toString();
        Documento aux = actual.getpPrim();
        while (aux != null) {
            if (aux.getNombre().equals(doc)) {
                main.monticulo.insertar(aux, actual);
                JOptionPane.showMessageDialog(main.ventana, "Documento: " + doc + " enviado a la cola de impresión");
                break;
            }
            aux = aux.getpSig();
        }
        main.ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_EnviarActionPerformed

    private void DocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DocumentoActionPerformed

    private void UsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsuariosActionPerformed

    private void Cancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar1ActionPerformed
        main.ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_Cancelar1ActionPerformed

    /**
    * Maneja el evento de clic en el botón "jButton1". Obtiene el usuario seleccionado en el desplegable,
    * busca la lista de documentos del usuario y actualiza el desplegable de documentos, removiendo aquellos
    * documentos que ya han sido impresos (tiempo igual a -1). Si el usuario no tiene documentos, limpia
    * completamente el desplegable de documentos.
    *
    * @param evt Objeto que contiene información sobre el evento de acción.
    * @author S. Estefania, G. Angelo y S. Jose
    */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String usuario = Usuarios.getSelectedItem().toString();
        Usuario actual = main.hashtable.buscarUsuario(usuario);
        if (actual.getpPrim() == null) {
            this.Documento.removeAllItems();
        } else {
            this.Documento.removeAllItems();
            Documento aux = actual.getpPrim();
            while (aux != null) {
                if (aux.getTiempo() == -1) {
                    this.Documento.addItem(aux.getNombre());
                }
                aux = aux.getpSig();
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus lTitulo feel */
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
            java.util.logging.Logger.getLogger(InsertarDocCola.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InsertarDocCola.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InsertarDocCola.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InsertarDocCola.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InsertarDocCola().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelar1;
    private javax.swing.JComboBox<String> Documento;
    private javax.swing.JButton Enviar;
    private javax.swing.JLabel TDocumento;
    private javax.swing.JLabel TUsuario;
    private javax.swing.JLabel Titulo;
    private javax.swing.JComboBox<String> Usuarios;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
