/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthcare;

import java.sql.SQLException;
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
    }
    
    public void checkForOutsideLimitAlerts(String key) {
        ArrayList<ArrayList<Object>> recommendations = this.mDb.getBestRecommendations(patientName);
        Boolean alert = false;
        String alertReason = "";
        Integer alertId = 0;
        switch(key) {
            case "weight" : {
                Float recommendedWeightLow = (Float) recommendations.get(0).get(7);
                Float recommendedWeightHigh = (Float) recommendations.get(0).get(8);
                ArrayList<Float> observedWeight = mDb.getObservedWeight(patientName);
                if (isOutside(recommendedWeightLow, recommendedWeightHigh, observedWeight.get(0))) {
                    alert = true;
                    alertId = 1;
                    if (isLow(observedWeight.get(0), recommendedWeightLow)) {
                        alertReason = "Patient's weight is "+ (recommendedWeightLow - observedWeight.get(0)) + " lesser than the lower limit.";
                    } else {
                        alertReason = "Patient's weight is "+ (observedWeight.get(0) - recommendedWeightHigh) + " higher than the upper limit.";
                    }
                }
                break;
            }
            case "bpDiastolic" : {
                Integer recommendedDiastolicLow = (Integer) recommendations.get(0).get(10);
                Integer recommendedDiastolicHigh = (Integer) recommendations.get(0).get(11);
                Integer observedDiastolic = 1;
                if (isOutside(recommendedDiastolicLow, recommendedDiastolicHigh, observedDiastolic)) {
                    alert = true;
                    alertId = 2;
                    if (isLow(observedDiastolic, recommendedDiastolicLow)) {
                        alertReason = "Patient's diastolic pressure is "+ (recommendedDiastolicLow - observedDiastolic) + " lesser than the lower limit.";
                    } else {
                        alertReason = "Patient's diastolic pressure is "+ (observedDiastolic - recommendedDiastolicHigh) + " higher than the upper limit.";
                    }
                }
                break;
            }
            case "bpSystolic" : {
                Integer recommendedSystolicLow = (Integer) recommendations.get(0).get(12);
                Integer recommendedSystolicHigh = (Integer) recommendations.get(0).get(13);
                Integer observedSystolic = 1;
                
                if (isOutside(recommendedSystolicLow, recommendedSystolicHigh, observedSystolic)) {
                    alert = true;
                    alertId = 3;
                    if (isLow(observedSystolic, recommendedSystolicLow)) {
                        alertReason = "Patient's systolic pressure is "+ (recommendedSystolicLow - observedSystolic) + " lesser than the lower limit.";
                    } else {
                        alertReason = "Patient's systolic pressure is "+ (observedSystolic - recommendedSystolicHigh) + " higher than the upper limit.";
                    }
                }
                break;
            }
            case "temperature" : {
                Float recommendedTemperatureLow = (Float) recommendations.get(0).get(4);
                Float recommendedTemperatureHigh = (Float) recommendations.get(0).get(5);
                Float observedTemperature = 0.1f;
                if (isOutside(recommendedTemperatureLow, recommendedTemperatureHigh, observedTemperature)) {
                    alert = true;
                    alertId = 7;
                    if (isLow(observedTemperature, recommendedTemperatureLow)) {
                        alertReason = "Patient's temperature is "+ (recommendedTemperatureLow - observedTemperature) + " lesser than the lower limit.";
                    } else {
                        alertReason = "Patient's temperature is "+ (observedTemperature - recommendedTemperatureHigh) + " higher than the upper limit.";
                    }
                }
                break;
            }
            case "pain" : {
                break;
            }
            case "oxygen saturation" : {
                break;
            }
            case "mood" : {
                break;
            }
            default : {
                break;
            }
        }
        
        try {
            if (alert) {
                this.mDb.createAlert(patientName, alertId, alertReason);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*
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
        if (recommendation.weightLow != null && isOutside(recommendation.weightLow, recommendation.weightHigh, observation.weight)) {
            // create alert id 1
            String alertReason;
            if (recommendation.weightLow > observation.weight) {
                alertReason = "Patient's weight is "+ (recommendation.weightLow - observation.weight) + " lesser than the lower limit.";
            } else {
                alertReason = "Patient's weight is "+ (observation.weight - recommendation.weightHigh) + " higher than the upper limit.";
            }
            try {
                this.mDb.createAlert(patientName, 1, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
        if (recommendation.bpDiastolicLow != null && isOutside(recommendation.bpDiastolicLow, recommendation.bpDiastolicHigh, observation.bpDiastolic)) {
            // create alert id 2
            String alertReason;
            if (recommendation.bpDiastolicLow > observation.bpDiastolic) {
                alertReason = "Patient's diastolic pressure is "+ (recommendation.bpDiastolicLow - observation.bpDiastolic) + " lesser than the lower limit.";
            } else {
                alertReason = "Patient's diastolic pressure is "+ (observation.bpDiastolic - recommendation.bpDiastolicHigh) + " higher than the upper limit.";
            }
            try {
                this.mDb.createAlert(patientName, 2, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (recommendation.bpSystolicLow != null && isOutside(recommendation.bpSystolicLow, recommendation.bpSystolicHigh, observation.bpSystolic)) {
            // create alert id 3
            String alertReason;
            if (recommendation.bpSystolicLow > observation.bpSystolic) {
                alertReason = "Patient's systolic pressure is "+ (recommendation.bpSystolicLow - observation.bpSystolic) + " lesser than the lower limit.";
            } else {
                alertReason = "Patient's systolic pressure is "+ (observation.bpSystolic - recommendation.bpSystolicHigh) + " higher than the upper limit.";
            }
            try {
                this.mDb.createAlert(patientName, 3, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
        if (recommendation.oxySatFrequency != null && isOutside(recommendation.oxySatLow, recommendation.oxySatHigh, observation.oxygenSat)) {
            // create alert id 4
            String alertReason;
            if (recommendation.oxySatLow > observation.oxygenSat) {
                alertReason = "Patient's oxygen saturation is "+ (recommendation.oxySatLow - observation.oxygenSat) + " lesser than the lower limit.";
            } else {
                alertReason = "Patient's oxygen saturation is "+ (observation.oxygenSat - recommendation.oxySatHigh) + " higher than the upper limit.";
            }
            try {
                this.mDb.createAlert(patientName, 1, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (recommendation.temperatureLow != null && isOutside(recommendation.bpDiastolicLow, recommendation.bpDiastolicHigh, observation.bpDiastolic)) {
            //create alert id 7
            String alertReason;
            if (recommendation.temperatureLow > observation.temperature) {
                alertReason = "Patient's temperature is "+ (recommendation.temperatureLow - observation.temperature) + " lesser than the lower limit.";
            } else {
                alertReason = "Patient's temperature is "+ (observation.weight - recommendation.temperatureHigh) + " higher than the upper limit.";
            }
            try {
                this.mDb.createAlert(patientName, 7, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }// Low_activity_alert
        if (recommendation.weightFrequency != null && isLowFrequency(daysDiff, recommendation.weightFrequency)) {
            // create alert id 8
            String alertReason = "Patient's weight has not been entered from " + daysDiff + " days.";
            try {
                this.mDb.createAlert(patientName, 8, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (recommendation.bpFrequency != null && isLowFrequency(daysDiff, recommendation.bpFrequency)) {
            // create alert id 9
            String alertReason = "Patient's blood pressure has not been entered from " + daysDiff + " days.";
            try {
                this.mDb.createAlert(patientName, 9, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (recommendation.oxySatFrequency != null && isLowFrequency(daysDiff, recommendation.oxySatFrequency)) {
            // create alert id 10
            String alertReason = "Patient's oxygen saturation has not been entered from " + daysDiff + " days.";
            try {
                this.mDb.createAlert(patientName, 10, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (recommendation.painLevelFrequency != null && isLowFrequency(daysDiff, recommendation.painLevelFrequency)) {
            // create alert id 11
            String alertReason = "Patient's pain level has not been entered from " + daysDiff + " days.";
            try {
                this.mDb.createAlert(patientName, 11, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (recommendation.moodFrequency != null && isLowFrequency(daysDiff, recommendation.moodFrequency)) {
            // create alert id 12
            String alertReason = "Patient's mood has not been entered from " + daysDiff + " days.";
            try {
                this.mDb.createAlert(patientName, 12, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (recommendation.tempertureFrequency != null && isLowFrequency(daysDiff, recommendation.tempertureFrequency)) {
            // create alert id 13
            String alertReason = "Patient's temperature has not been entered from " + daysDiff + " days.";
            try {
                this.mDb.createAlert(patientName, 13, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        */
    }
    
    private void checkForLowActivityAlerts() {
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
        if (recommendation.weightLow != null && isOutside(recommendation.weightLow, recommendation.weightHigh, observation.weight)) {
            // create alert id 1
            String alertReason;
            if (recommendation.weightLow > observation.weight) {
                alertReason = "Patient's weight is "+ (recommendation.weightLow - observation.weight) + " lesser than the lower limit.";
            } else {
                alertReason = "Patient's weight is "+ (observation.weight - recommendation.weightHigh) + " higher than the upper limit.";
            }
            try {
                this.mDb.createAlert(patientName, 1, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
        if (recommendation.bpDiastolicLow != null && isOutside(recommendation.bpDiastolicLow, recommendation.bpDiastolicHigh, observation.bpDiastolic)) {
            // create alert id 2
            String alertReason;
            if (recommendation.bpDiastolicLow > observation.bpDiastolic) {
                alertReason = "Patient's diastolic pressure is "+ (recommendation.bpDiastolicLow - observation.bpDiastolic) + " lesser than the lower limit.";
            } else {
                alertReason = "Patient's diastolic pressure is "+ (observation.bpDiastolic - recommendation.bpDiastolicHigh) + " higher than the upper limit.";
            }
            try {
                this.mDb.createAlert(patientName, 2, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (recommendation.bpSystolicLow != null && isOutside(recommendation.bpSystolicLow, recommendation.bpSystolicHigh, observation.bpSystolic)) {
            // create alert id 3
            String alertReason;
            if (recommendation.bpSystolicLow > observation.bpSystolic) {
                alertReason = "Patient's systolic pressure is "+ (recommendation.bpSystolicLow - observation.bpSystolic) + " lesser than the lower limit.";
            } else {
                alertReason = "Patient's systolic pressure is "+ (observation.bpSystolic - recommendation.bpSystolicHigh) + " higher than the upper limit.";
            }
            try {
                this.mDb.createAlert(patientName, 3, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
        if (recommendation.oxySatFrequency != null && isOutside(recommendation.oxySatLow, recommendation.oxySatHigh, observation.oxygenSat)) {
            // create alert id 4
            String alertReason;
            if (recommendation.oxySatLow > observation.oxygenSat) {
                alertReason = "Patient's oxygen saturation is "+ (recommendation.oxySatLow - observation.oxygenSat) + " lesser than the lower limit.";
            } else {
                alertReason = "Patient's oxygen saturation is "+ (observation.oxygenSat - recommendation.oxySatHigh) + " higher than the upper limit.";
            }
            try {
                this.mDb.createAlert(patientName, 1, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (recommendation.temperatureLow != null && isOutside(recommendation.bpDiastolicLow, recommendation.bpDiastolicHigh, observation.bpDiastolic)) {
            //create alert id 7
            String alertReason;
            if (recommendation.temperatureLow > observation.temperature) {
                alertReason = "Patient's temperature is "+ (recommendation.temperatureLow - observation.temperature) + " lesser than the lower limit.";
            } else {
                alertReason = "Patient's temperature is "+ (observation.weight - recommendation.temperatureHigh) + " higher than the upper limit.";
            }
            try {
                this.mDb.createAlert(patientName, 7, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }// Low_activity_alert
        if (recommendation.weightFrequency != null && isLowFrequency(daysDiff, recommendation.weightFrequency)) {
            // create alert id 8
            String alertReason = "Patient's weight has not been entered from " + daysDiff + " days.";
            try {
                this.mDb.createAlert(patientName, 8, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (recommendation.bpFrequency != null && isLowFrequency(daysDiff, recommendation.bpFrequency)) {
            // create alert id 9
            String alertReason = "Patient's blood pressure has not been entered from " + daysDiff + " days.";
            try {
                this.mDb.createAlert(patientName, 9, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (recommendation.oxySatFrequency != null && isLowFrequency(daysDiff, recommendation.oxySatFrequency)) {
            // create alert id 10
            String alertReason = "Patient's oxygen saturation has not been entered from " + daysDiff + " days.";
            try {
                this.mDb.createAlert(patientName, 10, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (recommendation.painLevelFrequency != null && isLowFrequency(daysDiff, recommendation.painLevelFrequency)) {
            // create alert id 11
            String alertReason = "Patient's pain level has not been entered from " + daysDiff + " days.";
            try {
                this.mDb.createAlert(patientName, 11, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (recommendation.moodFrequency != null && isLowFrequency(daysDiff, recommendation.moodFrequency)) {
            // create alert id 12
            String alertReason = "Patient's mood has not been entered from " + daysDiff + " days.";
            try {
                this.mDb.createAlert(patientName, 12, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (recommendation.tempertureFrequency != null && isLowFrequency(daysDiff, recommendation.tempertureFrequency)) {
            // create alert id 13
            String alertReason = "Patient's temperature has not been entered from " + daysDiff + " days.";
            try {
                this.mDb.createAlert(patientName, 13, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    private boolean isLow(Float x, Float y) {
        return x < y;
    }

    private boolean isLow(Integer observedDiastolic, Integer recommendedDiastolicLow) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
