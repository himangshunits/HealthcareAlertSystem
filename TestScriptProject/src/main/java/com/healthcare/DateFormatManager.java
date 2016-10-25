/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthcare;

import java.text.SimpleDateFormat;

/**
 *
 * @author Himangshu
 */
public class DateFormatManager {
    public static java.sql.Date getSqlDateFromString(String date){
        try{
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                java.util.Date utilDate = formatter.parse(date);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                return sqlDate;
            } catch(Exception e){
                System.out.println("Error in Observed On parse =" + e.getMessage());
                e.printStackTrace();
            }
        return null;
    }
    
    
    public static java.sql.Date getSqlDateFromJavaDate(java.util.Date date){
        try{
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                return sqlDate;
            } catch(Exception e){
                System.out.println("Error in Observed On parse =" + e.getMessage());
                e.printStackTrace();
            }
        return null;
    }
    
    
    
}
