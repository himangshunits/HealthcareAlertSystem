/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthcare;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 *
 * @author akshat
 */
public class Person {
    String username, name, gender, ssn;
    Date dob;
    boolean is_sick;
    String email_id, phone1, phone2;
    String address1, address2, city, state, zipcode, country;
    public Person(ResultSet rset)
    {
        try
        {
            this.name = rset.getString("name");
            this.dob = rset.getDate("date_of_birth");
            this.gender = rset.getString("gender");
            this.ssn = rset.getString("ssn");
            this.email_id = rset.getString("email_id");
            this.phone1 = rset.getString("phone_1");
            this.phone2 = rset.getString("phone_2");
            this.address1 = rset.getString("address_line_1");
            this.address2 = rset.getString("address_line_2");
            this.city = rset.getString("city");
            this.state = rset.getString("state");
            this.zipcode = rset.getString("zipcode");
            this.country = rset.getString("country");
            this.is_sick = rset.getBoolean("is_sick");
        }
        catch(SQLException sql)
        {
        }
        
    }
    public Person(String username, String name, Date dob, String gender, String ssn, String email_id, String phone1, String phone2, String address1, String address2, String city, String state, String zipcode, String country) {
        this.username = username;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.ssn = ssn;
        this.email_id = email_id;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
    }
    
    public Person(String username, String name, Date dob, String gender, String email_id, String phone1) {
        this.username = username;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.email_id = email_id;
        this.phone1 = phone1;
    }

    Person() {
        
    }

    @Override
    public String toString() {
        return "Person{" + "username=" + username + ", name=" + name + ", dob=" + dob + ", gender=" + gender + ", ssn=" + ssn + ", email_id=" + email_id + ", phone1=" + phone1 + ", phone2=" + phone2 + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode + ", country=" + country + '}';
    }
    
    String getDateOfBirth()
    {
        return dob.getDate() + "-" + dob.getMonth() + "-" + dob.getYear();
    }
}
