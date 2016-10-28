/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthcare;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author akshat
 */
public class Observations extends javax.swing.JFrame {

    Float weight;
    Integer bpDiastolic, bpSystolic;
    Float oxygenSat;
    String painLevel, mood;
    Float temperature;
    String observedOn;
    String recordedOn;
    
    String patientName;
    Database mDb;
    ObservationSignature signature;

    public Observations(String patientName) {
        this.patientName = patientName;
        //this.todayDate = DateFormatManager.getYearMonthDayFromDate(new java.util.Date());
        this.mDb = Database.getInstance();
        this.signature = new ObservationSignature();
        populateObservationSignature();
        initComponents();
    }
    
    /**
     * Creates new form Observations1
     */
    public Observations() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        submitObservation = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        weightInput = new javax.swing.JTextField();
        diastolicInput = new javax.swing.JTextField();
        systolicInput = new javax.swing.JTextField();
        oxygenSaturationInput = new javax.swing.JTextField();
        moodInput = new javax.swing.JComboBox<>();
        temperatureInput = new javax.swing.JTextField();
        painLevelInput = new javax.swing.JSpinner();
        days = new javax.swing.JComboBox<>();
        months = new javax.swing.JComboBox<>();
        years = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        days1 = new javax.swing.JComboBox<>();
        months1 = new javax.swing.JComboBox<>();
        years1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Enter Observation : ");

        jLabel2.setText("Weight");

        jLabel3.setText("BP Systolic");

        jLabel4.setText("BP Diastolic");

        jLabel5.setText("Oxygen Saturation");

        jLabel6.setText("Pain Level");

        jLabel7.setText("Mood");

        jLabel8.setText("Temperature");

        submitObservation.setText("Submit");
        submitObservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitObservationActionPerformed(evt);
            }
        });

        jLabel9.setText("Observation Date");

        weightInput.setPreferredSize(new java.awt.Dimension(75, 26));
        if(signature.isWeight){
            weightInput.setEnabled(false);
        }

        diastolicInput.setPreferredSize(new java.awt.Dimension(75, 26));
        if(signature.isBp){
            diastolicInput.setEnabled(false);
        }

        systolicInput.setPreferredSize(new java.awt.Dimension(75, 26));
        if(signature.isBp){
            systolicInput.setEnabled(false);
        }

        oxygenSaturationInput.setPreferredSize(new java.awt.Dimension(75, 26));
        if(signature.isOxygenSat){
            oxygenSaturationInput.setEnabled(false);
        }

        moodInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Happy", "Sad", "Neutral" }));
        if(signature.isMood){
            moodInput.setEnabled(false);
        }

        temperatureInput.setPreferredSize(new java.awt.Dimension(75, 26));
        if(signature.isTemperature){
            temperatureInput.setEnabled(false);
        }

        painLevelInput.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));
        if(signature.isPainLevel){
            painLevelInput.setEnabled(false);
        }

        days.setModel(new javax.swing.DefaultComboBoxModel<>(getDays()));

        months.setModel(new javax.swing.DefaultComboBoxModel<>(getMonths()));

        years.setModel(new javax.swing.DefaultComboBoxModel<>(getYears()));

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Recording Date :: Today's Date");

        days1.setModel(new javax.swing.DefaultComboBoxModel<>(getDays()));

        months1.setModel(new javax.swing.DefaultComboBoxModel<>(getMonths()));

        years1.setModel(new javax.swing.DefaultComboBoxModel<>(getYears()));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(submitObservation))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(days1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(months1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(years1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(moodInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(oxygenSaturationInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(systolicInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(diastolicInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(weightInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(painLevelInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(temperatureInput, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(months, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(years, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(161, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(weightInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addComponent(diastolicInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(systolicInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(oxygenSaturationInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(painLevelInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(moodInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(temperatureInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(years, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(months, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(days1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(months1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(years1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitObservation)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 641, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 483, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitObservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitObservationActionPerformed
        // TODO add yourzs handling code here:
    try {
        if (this.weightInput.getText() != null) {
            weight = Float.parseFloat(this.weightInput.getText());
        }
        if (this.diastolicInput.getText() != null) {
            bpDiastolic = Integer.parseInt(this.diastolicInput.getText());
        }
        if (this.systolicInput.getText() != null) {
            bpSystolic = Integer.parseInt(this.systolicInput.getText());
        }
        if (this.oxygenSaturationInput.getText() != null) {
            oxygenSat = Float.parseFloat(this.oxygenSaturationInput.getText());
        }
        painLevel = this.painLevelInput.getValue().toString()  ;
        mood = (String)this.moodInput.getSelectedItem();

        if (this.temperatureInput.getText() != null) {
            temperature = Float.parseFloat(this.temperatureInput.getText());
        }

        String day = (String)days.getSelectedItem();
        String month = (String)months.getSelectedItem();
        String year = (String)years.getSelectedItem();

        observedOn = year + "/" + month + "/" + day;
        
        
        String day1 = (String)days1.getSelectedItem();
        String month1 = (String)months1.getSelectedItem();
        String year1 = (String)years1.getSelectedItem();

        recordedOn = year1 + "/" + month1 + "/" + day1;
       
        Observation observation = null;
        //Observation observation = new Observation(weight, bpDiastolic, bpSystolic, oxygenSat, painLevel, mood, temperature, observedOn, recordedOn);
        //Obers
        ArrayList<String> result = null;//mDb.addObservation(patientName, observation);
        if(result == null)
            throw new Exception("Useless Exception!");
        JOptionPane.showMessageDialog(null, "Message from the Database Service :: " + result.get(1));
        System.out.println(result.get(0));
        if (result.get(0).equalsIgnoreCase("SUCCESS")) {
            AlertManager am = new AlertManager(patientName);
        }
        this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Message from the Service Daemon:: " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_submitObservationActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Observations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Observations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Observations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Observations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Observations().setVisible(true);
            }
        });
    }
    
    private String[] getMonths()
{
        String months[] = new String[12];
        for(int i=0;i<12;i++)
        {

            months[i] = "" + (i + 1);
            if(months[i].length() == 1)
            {
                months[i] = "0" + months[i];
            }
        }
        return months;
    }
    private String[] getDays()
    {
        String days[] = new String[31];
        for(int i=0;i<31;i++)
        {
            days[i] = "" + (i + 1);
            if(days[i].length() == 1)
            {
                days[i] = "0" + days[i];
            }
        }
        return days;
    }
    private String[] getYears()
    {
        String years[] = new String[76];
        for(int i=0;i<76;i++)
        {
            years[i] = "" + (1940 + i + 1);
        }
        return years;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> days;
    private javax.swing.JComboBox<String> days1;
    private javax.swing.JTextField diastolicInput;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> months;
    private javax.swing.JComboBox<String> months1;
    private javax.swing.JComboBox<String> moodInput;
    private javax.swing.JTextField oxygenSaturationInput;
    private javax.swing.JSpinner painLevelInput;
    private javax.swing.JButton submitObservation;
    private javax.swing.JTextField systolicInput;
    private javax.swing.JTextField temperatureInput;
    private javax.swing.JTextField weightInput;
    private javax.swing.JComboBox<String> years;
    private javax.swing.JComboBox<String> years1;
    // End of variables declaration//GEN-END:variables

    private void populateObservationSignature() {
       ArrayList<Object> bestReco = mDb.getBestRecommendations(patientName).get(0);
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
}
