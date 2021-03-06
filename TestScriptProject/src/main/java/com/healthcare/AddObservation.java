package com.healthcare;


import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rahul
 */
public class AddObservation extends javax.swing.JFrame {

    /**
     * Creates new form AddObservation
     */
    Database db;
    String username;
    private ObservationSignature signature;
    public AddObservation() {
        initComponents();
    }

    AddObservation(String username) {
        this.username = username;
        db = Database.getInstance();
        signature = new ObservationSignature();
        populateObservationSignature();
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

        observationType = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        value1 = new javax.swing.JTextField();
        value2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rdate = new javax.swing.JComboBox<>();
        rmonth = new javax.swing.JComboBox<>();
        ryear = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        odate = new javax.swing.JComboBox<>();
        omonth = new javax.swing.JComboBox<>();
        oyear = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ArrayList<String> typesList = new ArrayList<String>();
        if(!signature.isBp){
            typesList.add("Blood Pressure");
        }

        if(!signature.isMood){
            typesList.add("Mood");
        }

        if(!signature.isOxygenSat){
            typesList.add("Oxygen Saturation");
        }

        if(!signature.isPainLevel){
            typesList.add("Pain Level");
        }

        if(!signature.isTemperature){
            typesList.add("Temperature");
        }

        if(!signature.isWeight){
            typesList.add("Weight");
        }

        String types[] = new String[typesList.size()];
        typesList.toArray(types);
        observationType.setModel(new javax.swing.DefaultComboBoxModel<>(types));
        observationType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                observationTypeItemStateChanged(evt);
            }
        });
        observationType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                observationTypeActionPerformed(evt);
            }
        });
        observationType.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                observationTypePropertyChange(evt);
            }
        });

        jLabel1.setText("Observation Type:");

        jLabel2.setText("Value 1:");

        value1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                value1ActionPerformed(evt);
            }
        });

        if(!((String)observationType.getSelectedItem()).equals("Blood Pressure"))
        {
            value2.setEnabled(false);
        }

        jLabel3.setText("Value 2:");
        if(!((String)observationType.getSelectedItem()).equals("Blood Pressure"))
        {
            jLabel3.setEnabled(false);
        }

        jLabel13.setText("Recorded On");

        rdate.setModel(new javax.swing.DefaultComboBoxModel<>(DateFormatManager.getDays()));

        rmonth.setModel(new javax.swing.DefaultComboBoxModel<>(DateFormatManager.getMonths()));

        ryear.setModel(new javax.swing.DefaultComboBoxModel<>(DateFormatManager.getYears()));

        jLabel14.setText("Observed On");

        odate.setModel(new javax.swing.DefaultComboBoxModel<>(DateFormatManager.getDays()));

        omonth.setModel(new javax.swing.DefaultComboBoxModel<>(DateFormatManager.getMonths()));

        oyear.setModel(new javax.swing.DefaultComboBoxModel<>(DateFormatManager.getYears()));

        jButton1.setText("Add Observation");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Add Observation");

        jButton2.setText("Close");
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
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(54, 54, 54)
                        .addComponent(odate, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(omonth, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(oyear, 0, 66, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(55, 55, 55)
                        .addComponent(rdate, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ryear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(value1)
                                .addComponent(value2)
                                .addComponent(observationType, 0, 224, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(39, 39, 39)
                .addComponent(jButton2)
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(observationType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(value1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(value2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(rdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rmonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ryear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(odate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(omonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void value1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_value1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_value1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        Date observed_on = DateFormatManager.getDate((String)odate.getSelectedItem(), (String)omonth.getSelectedItem(), (String)oyear.getSelectedItem());
        Date recorded_on = DateFormatManager.getDate((String)rdate.getSelectedItem(), (String)rmonth.getSelectedItem(), (String)ryear.getSelectedItem());
        
        String type = (String)observationType.getSelectedItem();
        String v1 = this.value1.getText();
        String v2 = this.value2.getText();
        
        ObservationNew ob = new ObservationNew(type, v1, v2, observed_on, recorded_on);
        try{
            ArrayList<String> out = db.addObservation(username, ob);
            JOptionPane.showMessageDialog(null, "Message from DB :: "+ out.get(1));
            if (out.get(0).equalsIgnoreCase("SUCCESS")) {
                AlertManager am = new AlertManager(username);
                am.checkForOutsideLimitAlerts(type);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error is add Observation = " + e.getMessage());
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void observationTypePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_observationTypePropertyChange
        // TODO add your handling code here:
        
        
        if(observationType.getSelectedItem().equals("Blood Pressure"))
        {
            jLabel3.setEnabled(true);
            value2.setEnabled(true);
        }
    }//GEN-LAST:event_observationTypePropertyChange

    private void observationTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_observationTypeItemStateChanged
        // TODO add your handling code here:
        value1.setText("");
        value2.setText("");
        
        if(observationType.getSelectedItem().equals("Blood Pressure"))
        {
            jLabel3.setEnabled(true);
            value2.setEnabled(true);
        } else {
            jLabel3.setEnabled(false);
            value2.setEnabled(false);
        }
    }//GEN-LAST:event_observationTypeItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void observationTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_observationTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_observationTypeActionPerformed

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
            java.util.logging.Logger.getLogger(AddObservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddObservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddObservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddObservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddObservation().setVisible(true);
            }
        });
    }
    
    private void populateObservationSignature() {
       ArrayList<Object> bestReco = db.getBestRecommendations(username).get(0);
       if(bestReco.size() == 2){
           System.out.println("Severe error is recommendation rerieval, nulls coming :: " + bestReco);
           System.exit(0);
       }
       // if the best reco has more than one element, then it's a severe error!
        signature.isPainLevel = bestReco.get(1) == null || (Integer)bestReco.get(1) == 0;
        signature.isMood = bestReco.get(3) == null || (Integer)bestReco.get(3) == 0;
        signature.isTemperature = bestReco.get(6) == null || (Integer)bestReco.get(6) == 0;
        signature.isWeight = bestReco.get(9) == null || (Integer)bestReco.get(9) == 0;
        signature.isBp = bestReco.get(14) == null || (Integer)bestReco.get(14) == 0;
        signature.isOxygenSat = bestReco.get(17) == null || (Integer)bestReco.get(17) == 0;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> observationType;
    private javax.swing.JComboBox<String> odate;
    private javax.swing.JComboBox<String> omonth;
    private javax.swing.JComboBox<String> oyear;
    private javax.swing.JComboBox<String> rdate;
    private javax.swing.JComboBox<String> rmonth;
    private javax.swing.JComboBox<String> ryear;
    private javax.swing.JTextField value1;
    private javax.swing.JTextField value2;
    // End of variables declaration//GEN-END:variables
}
