/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controlador.ControladorVistaPrincipal;
import controlador.ControladorVistaProveedores;

/**
 *
 * @author ValeS
 */
public class VistaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jLabel1 = new javax.swing.JLabel();
                inventario = new javax.swing.JButton();
                inventario1 = new javax.swing.JButton();
                inventario2 = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setTitle("Menu Principal");
                setBackground(new java.awt.Color(102, 0, 102));
                setResizable(false);

                jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
                jLabel1.setText("Sistema Drugstore");

                inventario.setBackground(new java.awt.Color(102, 0, 102));
                inventario.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
                inventario.setForeground(new java.awt.Color(255, 255, 255));
                inventario.setText("Inventario");
                inventario.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                inventarioActionPerformed(evt);
                        }
                });

                inventario1.setBackground(new java.awt.Color(102, 0, 102));
                inventario1.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
                inventario1.setForeground(new java.awt.Color(255, 255, 255));
                inventario1.setText("Ventas");
                inventario1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                inventario1ActionPerformed(evt);
                        }
                });

                inventario2.setBackground(new java.awt.Color(102, 0, 102));
                inventario2.setFont(new java.awt.Font("Tw Cen MT", 2, 18)); // NOI18N
                inventario2.setForeground(new java.awt.Color(255, 255, 255));
                inventario2.setText("Proveedores");
                inventario2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                inventario2ActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inventario1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inventario2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(37, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(inventario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inventario1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inventario2)
                                .addContainerGap(25, Short.MAX_VALUE))
                );

                layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {inventario, inventario1, inventario2});

                pack();
        }// </editor-fold>//GEN-END:initComponents

    private void inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventarioActionPerformed
                ControladorVistaPrincipal.botonInventario();
    }//GEN-LAST:event_inventarioActionPerformed

        private void inventario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventario1ActionPerformed
                ControladorVistaPrincipal.botonVenta();
        }//GEN-LAST:event_inventario1ActionPerformed

        private void inventario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventario2ActionPerformed
		ControladorVistaPrincipal.botonProveedores();
        }//GEN-LAST:event_inventario2ActionPerformed

    /**
     * @param args the command line arguments
     */


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton inventario;
        private javax.swing.JButton inventario1;
        private javax.swing.JButton inventario2;
        private javax.swing.JLabel jLabel1;
        // End of variables declaration//GEN-END:variables
}
