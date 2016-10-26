/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthcare;

import static com.healthcare.Database.db;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author Himangshu
 */
public class HealthIndicators extends javax.swing.JFrame {

    /**
     * Creates new form HealthIndicators
     */
    String username;
    String statusMessage;
    boolean isHs;
    public HealthIndicators(String username, boolean isHs) {
        this.username = username;
        this.isHs = isHs;
        initComponents();
        this.recoStatus.setText(statusMessage);
    }
    
    
    public HealthIndicators() {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        observationsTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        recommendationsTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        recoStatus = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        removeObservationBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        removeRecoButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jLabel1.setText("The Best Recommendation for " + username);

        String[] header1 = new String [] {
            "ID","BP Diastolic","BP Systolic","Mood","Oxygen Saturation","Pain Level","Temperature","Weight","Observed On","Recorded On"};

        NonEditableModel model1 = new NonEditableModel(header1, 0);
        ArrayList<ArrayList<Object>> data1 = db.getAllObservations(username);
        for(ArrayList<Object> d: data1)
        {
            model1.addRow(d.toArray());
        }
        observationsTable.setModel(model1);
        jScrollPane1.setViewportView(observationsTable);

        jButton1.setText("Add New Observation");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Your Past Obeservations are below");

        String[] header = new String [] {
            "Pain Level","Pain Level Frequency","Mood","Mood Level Frequency", "Temperature Low Limit", "Temperature High Limit", "Temperature Frequency",
            "Weight Low Limit","Weight High Limit","Weight Frequency","BP Diastolic Low Limit","BP Diastolic High Limit","BP Systolic Low Limit","BP Systolic High limit",
            "BP Frequency", "Oxygen Saturation Low Limit", "Oxygen Saturation High Limit","Oxygen Saturation Frequency"};

        NonEditableModel model = new NonEditableModel(header, 0);
        ArrayList<ArrayList<Object>> data = db.getBestRecommendations(username);
        /*for(ArrayList<Object> d: data)
        {
            model.addRow(d.toArray());
        }*/
        for(int i = 0; i < data.size() - 1;i++){
            model.addRow(data.get(i).toArray());
        }
        Object[] statusMessage = data.get(data.size() - 1).toArray();
        //add it to the display
        this.statusMessage = statusMessage[1].toString();
        recommendationsTable.setModel(model);
        jScrollPane2.setViewportView(recommendationsTable);

        jLabel3.setText("Your Recommendation Status is :: ");

        recoStatus.setText("Recommendation Status Goes Here!");

        jLabel4.setText("If enabled, please select a row above and click Remove Obervation (For Health Supporters only!)");

        removeObservationBtn.setText("Remove Observation");
        if(!isHs){
            removeObservationBtn.setEnabled(false);
        }
        removeObservationBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeObservationBtnActionPerformed(evt);
            }
        });

        jLabel5.setText("If enabled, please select a row above and click Remove Recommendations(For Health Supporters Only!)");

        removeRecoButton.setText("Remove Recommendation");
        if(!isHs)
        removeRecoButton.setEnabled(false);
        removeRecoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeRecoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(recoStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(removeObservationBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 8, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(removeRecoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(21, 21, 21))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(39, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(19, 19, 19)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(recoStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeRecoButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(removeObservationBtn)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(69, 69, 69)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(247, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new Observations(username).setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        //TODO : Remove the initalizer code from constructor.
        String[] header1 = new String [] {
            "ID","BP Diastolic","BP Systolic","Mood","Oxygen Saturation","Pain Level","Temperature","Weight","Observed On","Recorded On"};

        NonEditableModel model1 = new NonEditableModel(header1, 0);
        ArrayList<ArrayList<Object>> data1 = db.getAllObservations(username);
        for(ArrayList<Object> d: data1)
        {
            model1.addRow(d.toArray());
        }
        observationsTable.setModel(model1);
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowGainedFocus

    private void removeRecoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeRecoButtonActionPerformed
                // Send DB the signal ro remove the dessease!
        //db.removeDiseaseForName();
        JOptionPane.showMessageDialog(null, "Recommendation Not Getting removed from DB yet!");
        int [] toDelete = this.recommendationsTable.getSelectedRows();
        Arrays.sort(toDelete); // be shure to have them in ascending order.
        NonEditableModel myTableModel = (NonEditableModel)recommendationsTable.getModel();
        for(int ii = toDelete.length -1; ii >=0; ii--) {
            myTableModel.removeRow(toDelete[ii]); // beginning at the largest.
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_removeRecoButtonActionPerformed

    private void removeObservationBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeObservationBtnActionPerformed
        JOptionPane.showMessageDialog(null, "Observation Not Getting removed from DB yet!");
        int [] toDelete = this.observationsTable.getSelectedRows();
        Arrays.sort(toDelete); // be shure to have them in ascending order.
        NonEditableModel myTableModel = (NonEditableModel)observationsTable.getModel();
        for(int ii = toDelete.length -1; ii >=0; ii--) {
            myTableModel.removeRow(toDelete[ii]); // beginning at the largest.
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_removeObservationBtnActionPerformed

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
            java.util.logging.Logger.getLogger(HealthIndicators.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HealthIndicators.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HealthIndicators.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HealthIndicators.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HealthIndicators().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable observationsTable;
    private javax.swing.JLabel recoStatus;
    private javax.swing.JTable recommendationsTable;
    private javax.swing.JButton removeObservationBtn;
    private javax.swing.JButton removeRecoButton;
    // End of variables declaration//GEN-END:variables
}
