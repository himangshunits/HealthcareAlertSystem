/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthcare;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Himangshu
 */
public class DateFormatManager {
    
    public static java.sql.Date getSqlDateFromString(String date, String format){
        try{
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                java.util.Date utilDate = formatter.parse(date);
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                return sqlDate;
            } catch(Exception e){
                System.out.println("Error in Observed On parse =" + e.getMessage());
                e.printStackTrace();
            }
        return null;
    }
    
    
    public static java.util.Date getJavaDateFromString(String date, String format){
        java.sql.Date sqlDate = getSqlDateFromString(date, format);
        return(getJavaDateFromSqlDate(sqlDate));
    }
    
    
    public static java.sql.Date getSqlDateFromJavaDate(java.util.Date date){
        try{
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                return sqlDate;
            } catch(Exception e){
                System.out.println("Error in Observed On parse =" + e.getMessage());
                e.printStackTrace();
            }
        return null;
    }
    
    
    public static java.util.Date getJavaDateFromSqlDate(java.sql.Date date){
        try{
                java.util.Date utilDate = new java.util.Date(date.getTime());
                return utilDate;
            } catch(Exception e){
                System.out.println("Error in Observed On parse =" + e.getMessage());
                e.printStackTrace();
            }
        return null;
    }
    
    
    
    public static String[] getYearMonthDayFromDate(java.util.Date today){
        String[] result = new String[3];
        int year = today.getYear();
        int month = today.getMonth();
        int day = today.getDay();
        result[0] = String.valueOf(year);
        result[1] = String.valueOf(month);
        result[2] = String.valueOf(day);
        return result;
    }

    static Date getDate(String day, String month, String year) {
        Date date = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        return date;
    }
    
    
    public static String[] getMonths()
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
    public static String[] getDays()
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
    public static String[] getYears()
    {
        String years[] = new String[76];
        for(int i=0;i<76;i++)
        {
            years[i] = "" + (1940 + i + 1);
        }
        return years;
    }
}
