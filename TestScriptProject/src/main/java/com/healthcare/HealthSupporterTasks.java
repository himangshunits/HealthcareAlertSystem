/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthcare;

import java.util.ArrayList;

/**
 *
 * @author akshat
 */
public class HealthSupporterTasks extends javax.swing.JFrame {

    String healthSupporterName, patientName;
    String disease;
    Database mDb;
    
    Person healthSupporter, patient;
    /**
     * Creates new form HealthSupporterTasks
     */
    public HealthSupporterTasks() {
        initComponents();
    }
    
    public HealthSupporterTasks(String healthSupporterName, String patientName) {
        this.healthSupporterName = healthSupporterName;
        this.patientName = patientName;
        this.mDb = Database.getInstance();
        initLocalData();
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

        hsActionPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        patientInfoPanel = new javax.swing.JPanel();
        healthSupName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        PatientName = new javax.swing.JLabel();
        PatientDOB = new javax.swing.JLabel();
        PatientGender = new javax.swing.JLabel();
        PatientEmailId = new javax.swing.JLabel();
        PatientPhone = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Enter Recommendation");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("View Alerts");

        jButton3.setText("Enter Observation");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Go Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout hsActionPanelLayout = new javax.swing.GroupLayout(hsActionPanel);
        hsActionPanel.setLayout(hsActionPanelLayout);
        hsActionPanelLayout.setHorizontalGroup(
            hsActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hsActionPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(49, 49, 49))
            .addGroup(hsActionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hsActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        hsActionPanelLayout.setVerticalGroup(
            hsActionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hsActionPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(43, 43, 43))
        );

        healthSupName.setText(healthSupporter.name);

        jLabel2.setText("Patient");

        PatientName.setText(patient.name);

        PatientDOB.setText(patient.dob);

        PatientGender.setText(patient.gender);

        PatientEmailId.setText(patient.email_id);

        PatientPhone.setText(patient.phone1);

        javax.swing.GroupLayout patientInfoPanelLayout = new javax.swing.GroupLayout(patientInfoPanel);
        patientInfoPanel.setLayout(patientInfoPanelLayout);
        patientInfoPanelLayout.setHorizontalGroup(
            patientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientInfoPanelLayout.createSequentialGroup()
                .addGroup(patientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(patientInfoPanelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(healthSupName))
                    .addGroup(patientInfoPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(patientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PatientName)
                            .addComponent(jLabel2)
                            .addComponent(PatientDOB)
                            .addComponent(PatientGender)
                            .addComponent(PatientEmailId)
                            .addComponent(PatientPhone))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        patientInfoPanelLayout.setVerticalGroup(
            patientInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(healthSupName)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(PatientName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PatientDOB)
                .addGap(18, 18, 18)
                .addComponent(PatientGender)
                .addGap(18, 18, 18)
                .addComponent(PatientEmailId)
                .addGap(18, 18, 18)
                .addComponent(PatientPhone)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(patientInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hsActionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(patientInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hsActionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EnterRecommendation newReco = new EnterRecommendation(patientName);
        newReco.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Observations1 newObs = new Observations1(patientName);
        newObs.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(HealthSupporterTasks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HealthSupporterTasks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HealthSupporterTasks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HealthSupporterTasks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HealthSupporterTasks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PatientDOB;
    private javax.swing.JLabel PatientEmailId;
    private javax.swing.JLabel PatientGender;
    private javax.swing.JLabel PatientName;
    private javax.swing.JLabel PatientPhone;
    private javax.swing.JLabel healthSupName;
    private javax.swing.JPanel hsActionPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel patientInfoPanel;
    // End of variables declaration//GEN-END:variables

    private void initLocalData() {
        try {
            healthSupporter = mDb.showLimitedProfile(healthSupporterName);
            patient = mDb.showLimitedProfile(patientName);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred");
        }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}