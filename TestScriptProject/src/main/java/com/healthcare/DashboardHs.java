/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthcare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JOptionPane;


/**
 *
 * @author Rahul
 */
public class DashboardHs extends javax.swing.JFrame {
     
    

    /**
     * Creates new form Dashboard
     */
    String username;
    String name;
    Integer person_id;
    String healthSupp1;
    String healthId1;
    String healthSupp2;
    String healthId2;
    Database mDb;
    LinkedList<HsInfo> patientsUnderYou;
    public DashboardHs() {
        initComponents();
        
    }
    public DashboardHs(String username) {
        this.username = username;
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

        wecomeMessage = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        careTakerFrame = new javax.swing.JInternalFrame();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        patientsList = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        userDetailsFrame = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        getProfileBtn = new javax.swing.JButton();
        seeDiseasesBtn = new javax.swing.JButton();
        healthIndicatorsBtn = new javax.swing.JButton();
        alertsBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        primaryHsNameField = new javax.swing.JLabel();
        primaryHsIdField = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        wecomeMessage.setText("Welcome, " + this.name);

        careTakerFrame.setVisible(true);

        jLabel6.setText("Your Details as a Health Supporter");

        jLabel7.setText("Patients Under You are shown below.");

        jLabel8.setText("Please Select One from the list and click GO");

        patientsList.setModel(new javax.swing.AbstractListModel<String>() {
            //String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return patientsUnderYou.size(); }
            public String getElementAt(int i) { return patientsUnderYou.get(i).name; }
        });
        jScrollPane1.setViewportView(patientsList);

        jButton1.setText("GO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout careTakerFrameLayout = new javax.swing.GroupLayout(careTakerFrame.getContentPane());
        careTakerFrame.getContentPane().setLayout(careTakerFrameLayout);
        careTakerFrameLayout.setHorizontalGroup(
            careTakerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(careTakerFrameLayout.createSequentialGroup()
                .addGroup(careTakerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, careTakerFrameLayout.createSequentialGroup()
                        .addContainerGap(36, Short.MAX_VALUE)
                        .addGroup(careTakerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(careTakerFrameLayout.createSequentialGroup()
                        .addGroup(careTakerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(careTakerFrameLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(careTakerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)))
                            .addGroup(careTakerFrameLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        careTakerFrameLayout.setVerticalGroup(
            careTakerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(careTakerFrameLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(16, 16, 16))
        );

        userDetailsFrame.setVisible(true);

        jLabel1.setText("Your Own Details as Person");

        getProfileBtn.setText("Get Profile");
        getProfileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getProfileBtnActionPerformed(evt);
            }
        });

        seeDiseasesBtn.setText("See Diseases");
        seeDiseasesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeDiseasesBtnActionPerformed(evt);
            }
        });

        healthIndicatorsBtn.setText("Health Indicators");

        alertsBtn.setText("See Alerts");
        alertsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alertsBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Your primary health supporter");

        jLabel3.setText("Your secondary health supporter");

        primaryHsNameField.setText(healthSupp1);

        primaryHsIdField.setText(healthId1);

        jLabel4.setText(healthSupp2);

        jLabel5.setText(healthId2);

        javax.swing.GroupLayout userDetailsFrameLayout = new javax.swing.GroupLayout(userDetailsFrame.getContentPane());
        userDetailsFrame.getContentPane().setLayout(userDetailsFrameLayout);
        userDetailsFrameLayout.setHorizontalGroup(
            userDetailsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userDetailsFrameLayout.createSequentialGroup()
                .addGroup(userDetailsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userDetailsFrameLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel2))
                    .addGroup(userDetailsFrameLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(userDetailsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(alertsBtn)
                            .addComponent(healthIndicatorsBtn)
                            .addComponent(seeDiseasesBtn)
                            .addComponent(getProfileBtn))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userDetailsFrameLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(userDetailsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(primaryHsIdField)
                    .addComponent(primaryHsNameField)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(34, 34, 34))
        );
        userDetailsFrameLayout.setVerticalGroup(
            userDetailsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userDetailsFrameLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(getProfileBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seeDiseasesBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(healthIndicatorsBtn)
                .addGap(12, 12, 12)
                .addComponent(alertsBtn)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addComponent(primaryHsNameField)
                .addGap(12, 12, 12)
                .addComponent(primaryHsIdField)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jButton2.setText("LOGOUT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(wecomeMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(careTakerFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addComponent(userDetailsFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(wecomeMessage)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(careTakerFrame))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(userDetailsFrame)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getProfileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getProfileBtnActionPerformed
        // TODO add your handling code here:
        new Profile(username).setVisible(true);
        
        
    }//GEN-LAST:event_getProfileBtnActionPerformed

    private void seeDiseasesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeDiseasesBtnActionPerformed
        // TODO add your handling code here:
        new Diagnosis(username).setVisible(true);
    }//GEN-LAST:event_seeDiseasesBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int index = patientsList.getSelectedIndex();
        String curr_patient_username = patientsUnderYou.get(index).username;
        // Call Akshat's Form for curr_patient_username and username
        JOptionPane.showMessageDialog(null, "Will Show Options here for patient " + curr_patient_username);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void alertsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alertsBtnActionPerformed
        // TODO add your handling code here:
        new Alert(username).setVisible(true);
    }//GEN-LAST:event_alertsBtnActionPerformed

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alertsBtn;
    private javax.swing.JInternalFrame careTakerFrame;
    private javax.swing.JButton getProfileBtn;
    private javax.swing.JButton healthIndicatorsBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JList<String> patientsList;
    private javax.swing.JLabel primaryHsIdField;
    private javax.swing.JLabel primaryHsNameField;
    private javax.swing.JButton seeDiseasesBtn;
    private javax.swing.JInternalFrame userDetailsFrame;
    private javax.swing.JLabel wecomeMessage;
    // End of variables declaration//GEN-END:variables

    private void initLocalData() {        
        try{            
            ArrayList<String> data = mDb.getNameAndIdForUsername(username);            
            name = data.get(0);
            person_id = Integer.parseInt(data.get(1));            
            ArrayList<HsInfo> arr = mDb.getHsInfo(person_id);
            if(arr.size() == 0){
                healthSupp1 = "Not assigned yet!";
                healthId1 = "None";
                healthSupp2 = "Not assigned yet!";
                healthId2 = "None";
            } else if (arr.size() == 1){
                healthSupp1 = arr.get(0).name;
                healthId1 = arr.get(0).username;
                healthSupp2 = "Not assigned yet!";
                healthId2 = "None";
            } else if(arr.size() == 2){
                healthSupp1 = arr.get(0).name;
                healthId1 = arr.get(0).username;
                healthSupp2 = arr.get(1).name;;
                healthId2 = arr.get(1).username;
            } else {
                System.out.println("More than 2 Helath Supp!!!!!! Erorrr!");
            } 
            
            // Add the Patients under you!
            patientsUnderYou = mDb.getPatientsUnderYou(person_id);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println(" The error in name retireval = " + e.getMessage());
        }
    }
}
