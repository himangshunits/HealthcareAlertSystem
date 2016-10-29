/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthcare;

import java.sql.Date;
import java.text.SimpleDateFormat;
import org.joda.time.DateTime;
import org.joda.time.Days;

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
        int date = today.getDate();
        
        result[0] = String.valueOf(1900 + year);
        
        result[1] = String.valueOf(1 + month);
        if(result[1].length() == 1)
        {
            result[1] = "0" + result[1];
        }
        result[2] = String.valueOf(date);
        if(result[2].length() == 1)
        {
            result[2] = "0" + result[2];
        }
        return result;
    }

    static Date getDate(String dateS, String monthS, String yearS) {
        int year = Integer.parseInt(yearS)- 1900;
        int month = Integer.parseInt(monthS) - 1;
        int date = Integer.parseInt(dateS);
        return new Date(year, month, date);
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
    public int getDays(Date date1, Date date2)
    {
        DateTime dt1 = new DateTime(date1);
	DateTime dt2 = new DateTime(date2);
        return Days.daysBetween(dt1, dt2).getDays();
    }
}

