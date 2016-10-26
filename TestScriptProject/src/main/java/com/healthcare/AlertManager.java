/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthcare;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author akshat
 */
public class AlertManager {
    String patientName;
    Database mDb;

    public AlertManager(String patientName) {
        this.patientName = patientName;
        this.mDb = Database.getInstance();
        checkForAlerts();
    }
    
    private void checkForAlerts() {
        ArrayList<ArrayList<Object>> recommendations = this.mDb.getBestRecommendations(patientName);
        Recommendation recommendation = new Recommendation();
        recommendation.painLevel = (String) recommendations.get(0).get(0);
        recommendation.painLevelFrequency = (Integer) recommendations.get(0).get(1);
        recommendation.mood = (String) recommendations.get(0).get(2);
        recommendation.moodFrequency = (Integer) recommendations.get(0).get(3);
        recommendation.temperatureLow = (Float) recommendations.get(0).get(4);
        recommendation.temperatureHigh = (Float) recommendations.get(0).get(5);
        recommendation.tempertureFrequency = (Integer) recommendations.get(0).get(6);
        recommendation.weightLow = (Float) recommendations.get(0).get(7);
        recommendation.weightHigh = (Float) recommendations.get(0).get(8);
        recommendation.weightFrequency = (Integer) recommendations.get(0).get(9);
        recommendation.bpDiastolicLow = (Integer) recommendations.get(0).get(10);
        recommendation.bpDiastolicHigh = (Integer) recommendations.get(0).get(11);
        recommendation.bpSystolicLow = (Integer) recommendations.get(0).get(12);
        recommendation.bpSystolicHigh = (Integer) recommendations.get(0).get(13);
        recommendation.bpFrequency = (Integer) recommendations.get(0).get(14);
        recommendation.oxySatLow = (Float) recommendations.get(0).get(15);
        recommendation.oxySatHigh = (Float) recommendations.get(0).get(16);
        recommendation.oxySatFrequency = (Integer) recommendations.get(0).get(17);
        
        Observation observation = mDb.getLatestObservation(patientName);
        
        Calendar aaj = new GregorianCalendar();
        aaj.set(Calendar.HOUR_OF_DAY, 0); //anything 0 - 23
        aaj.set(Calendar.MINUTE, 0);
        aaj.set(Calendar.SECOND, 0);
        Calendar today = Calendar.getInstance();
        String[] obsDateString = observation.observedOn.split("-");
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        String inputString1 = obsDateString[2] + " " + obsDateString[1] + 
                " " + obsDateString[0];
        String inputString2 = today.get(Calendar.DAY_OF_MONTH) + " " +
                today.get(Calendar.MONTH) + " " +
                today.get(Calendar.YEAR);
        
        long daysDiff = 0;
        try {
        Date date1 = myFormat.parse(inputString1);
        Date date2 = aaj.getTime();
        
        long diff = date2.getTime() - date1.getTime();
        daysDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        
        // Outside_the_limit threshold alert
        if (recommendation.temperatureLow != null && isOutside(recommendation.bpDiastolicLow, recommendation.bpDiastolicHigh, observation.bpDiastolic)) {
            //create alert id 7
        } 
        if (recommendation.weightLow != null && isOutside(recommendation.weightLow, recommendation.weightHigh, observation.weight)) {
            // create alert id 1
        } 
        if (recommendation.bpDiastolicLow != null && isOutside(recommendation.bpDiastolicLow, recommendation.bpDiastolicHigh, observation.bpDiastolic)) {
            // create alert id 2
        }
        if (recommendation.bpSystolicLow != null && isOutside(recommendation.bpSystolicLow, recommendation.bpSystolicHigh, observation.bpSystolic)) {
            // create alert id 3
        } 
        if (recommendation.oxySatFrequency != null && isOutside(recommendation.oxySatLow, recommendation.oxySatHigh, observation.oxygenSat)) {
            // create alert id 4
        }// Low_activity_alert
        if (recommendation.weightFrequency != null && isLowFrequency(daysDiff, recommendation.weightFrequency)) {
            // create alert id 8
        }
        if (recommendation.bpFrequency != null && isLowFrequency(daysDiff, recommendation.bpFrequency)) {
            // create alert id 9
        }
        if (recommendation.oxySatFrequency != null && isLowFrequency(daysDiff, recommendation.oxySatFrequency)) {
            // create alert id 10
        }
        if (recommendation.painLevelFrequency != null && isLowFrequency(daysDiff, recommendation.painLevelFrequency)) {
            // create alert id 11
        }
        if (recommendation.moodFrequency != null && isLowFrequency(daysDiff, recommendation.moodFrequency)) {
            // create alert id 12
        }
        if (recommendation.tempertureFrequency != null && isLowFrequency(daysDiff, recommendation.tempertureFrequency)) {
            // create alert id 13
        }

    }

    private boolean isOutside(Integer low, Integer high, Integer x) {
        return (x < low || x > high);
    }

    private boolean isOutside(Float low, Float high, Float x) {
        return (x < low || x > high);
    }

    private boolean isLowFrequency(long daysDiff, Integer painLevelFrequency) {
        return daysDiff > painLevelFrequency;
    }
    
    
    
}
