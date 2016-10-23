/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthcare;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.validator.routines.EmailValidator;
/**
 *
 * @author Rahul
 */

public class SignUp extends javax.swing.JFrame {

    /**
     * Creates new form SignUp
     */
    public SignUp() {
        initComponents();
    }

    private boolean isValidEmail(String email)
    {
        return EmailValidator.getInstance().isValid(email);
    }
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try {
          dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
          return false;
        }
        return true;
    }
    boolean validateForm(HashMap<String, String> map)
    {
        for(Map.Entry<String, String> entry: map.entrySet())
        {
            if(entry.getValue().equals(""))
            {
                if(entry.getKey().equals("supporter") || entry.getKey().equals("supporter2"))
                {
                    continue;
                }
                JOptionPane.showMessageDialog(null, entry.getKey() + "is empty");
                return false;
            }
        }
        if(!(map.get("password").equals(map.get("cpassword"))))
        {
            JOptionPane.showMessageDialog(null, "Password dont match");
            return false;
        }
        if(!isValidEmail(map.get("email")))
        {
            JOptionPane.showMessageDialog(null, "Email not valid");
            return false;
        }
        if(!isValidDate(map.get("dob")))
        {
            JOptionPane.showMessageDialog(null, "Date not valid");
            return false;
        }
        try
        {
            Integer.parseInt(map.get("zip"));
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(null, "zip code is not valid");
            return false;
        }


        return true;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gender = new javax.swing.ButtonGroup();
        isSick = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        submit = new javax.swing.JToggleButton();
        nameLbl = new javax.swing.JLabel();
        dobLbl = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        genderLbl = new javax.swing.JLabel();
        isSickLbl = new javax.swing.JLabel();
        cpasswordLbl = new javax.swing.JLabel();
        usernameLbl = new javax.swing.JLabel();
        passwordLbl = new javax.swing.JLabel();
        genderFemale = new javax.swing.JRadioButton();
        genderMale = new javax.swing.JRadioButton();
        isSickYes = new javax.swing.JRadioButton();
        isSickNo = new javax.swing.JRadioButton();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        cpassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        supporters = new javax.swing.JComboBox();
        months = new javax.swing.JComboBox();
        years = new javax.swing.JComboBox();
        days = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        street = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        apt = new javax.swing.JTextField();
        city = new javax.swing.JTextField();
        state = new javax.swing.JTextField();
        country = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ssn = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        pmobile = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        smobile = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        zip = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        aday = new javax.swing.JComboBox<>();
        amonth = new javax.swing.JComboBox<>();
        ayear = new javax.swing.JComboBox<>();
        supporters2 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();

        gender.add(genderMale);
        gender.add(genderFemale);

        isSick.add(isSickYes);
        isSick.add(isSickNo);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Please add these fields:");

        submit.setText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        nameLbl.setText("Name:");

        dobLbl.setText("Date of Birth:");

        name.setText("Rahul Shah");

        genderLbl.setText("Gender:");

        isSickLbl.setText("Are you Sick?");

        cpasswordLbl.setText("Confirm Password:");

        usernameLbl.setText("Username:");

        passwordLbl.setText("Password:");

        genderFemale.setText("Female");
        genderFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderFemaleActionPerformed(evt);
            }
        });

        genderMale.setText("Male");
        genderMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderMaleActionPerformed(evt);
            }
        });

        isSickYes.setText("Yes");
        isSickYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isSickYesActionPerformed(evt);
            }
        });

        isSickNo.setText("No");

        jLabel2.setText("Add Health Supporter 1");

        supporters.setModel(new javax.swing.DefaultComboBoxModel<>(Database.getInstance().getSupporters()));

        months.setModel(new javax.swing.DefaultComboBoxModel<>(getMonths()));
        months.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthsActionPerformed(evt);
            }
        });

        years.setModel(new javax.swing.DefaultComboBoxModel<>(getYears()));

        days.setModel(new javax.swing.DefaultComboBoxModel<>(getDays()));

        jLabel3.setText("Street");

        street.setText("qwe");
        street.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                streetActionPerformed(evt);
            }
        });

        jLabel4.setText("City");

        jLabel5.setText("State");

        jLabel6.setText("Country");

        apt.setText("asdasdsad");
        apt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aptActionPerformed(evt);
            }
        });

        city.setText("asdsad");

        state.setText("asdasd");

        country.setText("asdasd");

        jLabel7.setText("Apt No");

        ssn.setText("sdasdasdasd");

        jLabel8.setText("SSN");

        pmobile.setText("919874752");

        jLabel9.setText("Primary Contact No");

        jLabel10.setText("Secondary Contact No");

        smobile.setText("asdaskdl");

        jLabel11.setText("Email Address");

        email.setText("wha@asdasd.com");

        zip.setText("27606");
        zip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zipActionPerformed(evt);
            }
        });

        jLabel12.setText("Zipcode");

        jLabel13.setText("Authorization Date");

        aday.setModel(new javax.swing.DefaultComboBoxModel<>(getDays()));

        amonth.setModel(new javax.swing.DefaultComboBoxModel<>(getMonths()));

        ayear.setModel(new javax.swing.DefaultComboBoxModel<>(getYears()));

        supporters2.setModel(new javax.swing.DefaultComboBoxModel<>(Database.getInstance().getSupporters()));

        jLabel14.setText("Add Health Supporter 2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(apt, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(street, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ssn, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(35, 35, 35)
                                .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(usernameLbl)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11)
                                .addComponent(jLabel9)))
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(zip))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(nameLbl)
                            .addComponent(genderLbl)
                            .addComponent(isSickLbl)
                            .addComponent(passwordLbl)
                            .addComponent(jLabel2)
                            .addComponent(cpasswordLbl)
                            .addComponent(dobLbl))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(128, 128, 128)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cpassword)
                            .addComponent(password)
                            .addComponent(username)
                            .addComponent(name)
                            .addComponent(smobile)
                            .addComponent(pmobile)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(months, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(years, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(genderMale)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(genderFemale))
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(aday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(amonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ayear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(isSickYes)
                                        .addGap(18, 18, 18)
                                        .addComponent(isSickNo))
                                    .addComponent(supporters, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(supporters2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLbl)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dobLbl)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(years, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(months, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(genderLbl)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(genderFemale)
                        .addComponent(genderMale)))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(isSickLbl)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(isSickYes)
                        .addComponent(isSickNo)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(supporters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(supporters2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(aday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(amonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ayear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ssn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(street, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(apt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(country, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(zip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pmobile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(usernameLbl))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(smobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLbl)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cpasswordLbl)
                    .addComponent(cpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submit)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        // TODO add your handling code here:

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("password", new String(this.password.getPassword()));
        map.put("cpassword", new String(this.cpassword.getPassword()));
        map.put("street", new String(this.street.getText().trim()));
        map.put("apt", new String(this.apt.getText().trim()));
        map.put("ssn", new String(this.ssn.getText().trim()));
        map.put("city", new String(this.city.getText().trim()));
        map.put("state", new String(this.state.getText().trim()));
        map.put("country", new String(this.country.getText().trim()));
        map.put("username", new String(this.username.getText().trim()));
        map.put("name", new String(this.name.getText().trim()));
        map.put("pmobile", new String(this.pmobile.getText().trim()));
        map.put("smobile", new String(this.smobile.getText().trim()));
        map.put("email", new String(this.email.getText().trim()));
        map.put("zip", new String(this.zip.getText().trim()));
        map.put("supporter", (String)(this.supporters.getSelectedItem()));
        map.put("supporter2", (String)(this.supporters2.getSelectedItem()));
        
        java.util.Date date = new java.util.Date();
        String modifiedDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
        map.put("date", modifiedDate);
        String day = (String)days.getSelectedItem();
        String month = (String)months.getSelectedItem();
        String year = (String)years.getSelectedItem();
        map.put("dob", day + "-" + month + "-" + year);
        if(this.genderMale.isSelected())
        {
            map.put("gender", "MALE");
        }
        else
        {
            map.put("gender", "FEMALE");
        }


        map.put("isSick", "0");

        String auth_date = (String)aday.getSelectedItem() + "-" + (String)amonth.getSelectedItem() + "-" + (String)ayear.getSelectedItem();
        if(validateForm(map))
        {
            Database db = Database.getInstance();
            try
            {
                ArrayList<String> o1 = null, o2 = null, o3 = null;
                o1 = db.addPerson(map);
                if(this.isSickYes.isSelected())
                {
                    o2 = db.addHealthSupporter(map.get("username"), map.get("supporter"), getDate(auth_date));
                    o3 = db.toggleIsSick(map.get("username"));
                }

                if(o1.get(0).equals("SUCCESS") && (o2 == null || o2.get(0).equals("SUCCESS")) && (o2 == null || o3.get(0).equals("SUCCESS")))
                {

                    JOptionPane.showMessageDialog(null, o1.get(1));
                    Main m = new Main(db);
                    m.setVisible(true);
                    this.dispose();
                }
                else
                {
                    //TODO: how to effectively rollback
                    db.removePerson(map.get("username"));

                    //db.removeHealthSupporter(map.get("username"), map.get("supporter"));
                    if(!o1.get(0).equals("SUCCESS"))
                    {
                        JOptionPane.showMessageDialog(null, o1.get(1));
                    }
                    else if(o2 != null && !o2.get(0).equals("SUCCESS"))
                        JOptionPane.showMessageDialog(null, o2.get(1));
                    else if(o3 != null && !o3.get(0).equals("SUCCESS"))
                        JOptionPane.showMessageDialog(null, o3.get(1));
                }
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }//GEN-LAST:event_submitActionPerformed

    private void genderFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderFemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderFemaleActionPerformed

    private void genderMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderMaleActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_genderMaleActionPerformed

    private void isSickYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isSickYesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isSickYesActionPerformed

    private void monthsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthsActionPerformed

    private void aptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aptActionPerformed

    private void streetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_streetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_streetActionPerformed

    private void zipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zipActionPerformed
    private Date getDate(String s)
    {
        String b[] = s.split("-");

        return new Date(Integer.parseInt(b[0]), Integer.parseInt(b[1]), Integer.parseInt(b[2]));
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
    private javax.swing.JComboBox<String> aday;
    private javax.swing.JComboBox<String> amonth;
    private javax.swing.JTextField apt;
    private javax.swing.JComboBox<String> ayear;
    private javax.swing.JTextField city;
    private javax.swing.JTextField country;
    private javax.swing.JPasswordField cpassword;
    private javax.swing.JLabel cpasswordLbl;
    private javax.swing.JComboBox<String> days;
    private javax.swing.JLabel dobLbl;
    private javax.swing.JTextField email;
    private javax.swing.ButtonGroup gender;
    private javax.swing.JRadioButton genderFemale;
    private javax.swing.JLabel genderLbl;
    private javax.swing.JRadioButton genderMale;
    private javax.swing.ButtonGroup isSick;
    private javax.swing.JLabel isSickLbl;
    private javax.swing.JRadioButton isSickNo;
    private javax.swing.JRadioButton isSickYes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<String> months;
    private javax.swing.JTextField name;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordLbl;
    private javax.swing.JTextField pmobile;
    private javax.swing.JTextField smobile;
    private javax.swing.JTextField ssn;
    private javax.swing.JTextField state;
    private javax.swing.JTextField street;
    private javax.swing.JToggleButton submit;
    private javax.swing.JComboBox<String> supporters;
    private javax.swing.JComboBox<String> supporters2;
    private javax.swing.JTextField username;
    private javax.swing.JLabel usernameLbl;
    private javax.swing.JComboBox<String> years;
    private javax.swing.JTextField zip;
    // End of variables declaration//GEN-END:variables
}
