/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthcare;

import com.oracle.util.Checksums;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
            case "Weight" : {
                // New observation for weight added. Thus inactivate low activity alert if present
                mDb.updateSentAlerts(patientName, 8); 
                Float recommendedWeightLow = (Float) recommendations.get(0).get(7);
                Float recommendedWeightHigh = (Float) recommendations.get(0).get(8);
                
                ObservationNew observation = mDb.getObservedWeight(patientName);
                Float observedWeight = new Float(observation.value1);
                
                if (isOutside(recommendedWeightLow, recommendedWeightHigh, observedWeight)) {
                    alert = true;
                    alertId = 1;
                    if (isLow(observedWeight, recommendedWeightLow)) {
                        alertReason = "Patient's weight is "+ (recommendedWeightLow - observedWeight) + " lesser than the lower limit.";
                    } else {
                        alertReason = "Patient's weight is "+ (observedWeight - recommendedWeightHigh) + " higher than the upper limit.";
                    }
                } else {
                    mDb.updateSentAlerts(patientName, 1); 
                }
                break;
            }
            case "Blood Pressure" : {
                mDb.updateSentAlerts(patientName, 9); 
                Integer recommendedDiastolicLow = (Integer) recommendations.get(0).get(10);
                Integer recommendedDiastolicHigh = (Integer) recommendations.get(0).get(11);
                Integer recommendedSystolicLow = (Integer) recommendations.get(0).get(12);
                Integer recommendedSystolicHigh = (Integer) recommendations.get(0).get(13);
                
                ObservationNew observation = mDb.getObservedBloodPressure(patientName);
                Integer observedDiastolic = new Integer(observation.value1);
                Integer observedSystolic = new Integer(observation.value2);
                
                if (isOutside(recommendedDiastolicLow, recommendedDiastolicHigh, observedDiastolic)) {                  
                    if (isLow(observedDiastolic, recommendedDiastolicLow)) {
                        alertReason = "Patient's diastolic pressure is "+ (recommendedDiastolicLow - observedDiastolic) + " lesser than the lower limit.";
                    } else {
                        alertReason = "Patient's diastolic pressure is "+ (observedDiastolic - recommendedDiastolicHigh) + " higher than the upper limit.";
                    }
                    try {
                        this.mDb.createAlert(patientName, 2, alertReason);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    mDb.updateSentAlerts(patientName, 2); 
                }
            
                if (isOutside(recommendedSystolicLow, recommendedSystolicHigh, observedSystolic)) {
                    
                    if (isLow(observedSystolic, recommendedSystolicLow)) {
                        alertReason = "Patient's systolic pressure is "+ (recommendedSystolicLow - observedSystolic) + " lesser than the lower limit.";
                    } else {
                        alertReason = "Patient's systolic pressure is "+ (observedSystolic - recommendedSystolicHigh) + " higher than the upper limit.";
                    }
                    try {
                        this.mDb.createAlert(patientName, 3, alertReason);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    mDb.updateSentAlerts(patientName, 3); 
                }
                break;
            }
            case "Oxygen Saturation" : {
                mDb.updateSentAlerts(patientName, 10);
                Float recommendedOxySatLow = (Float) recommendations.get(0).get(15);
                Float recommendedOxySatHigh = (Float) recommendations.get(0).get(16);
                
                ObservationNew observation = mDb.getObservedOxySat(patientName);
                Float observedOxySat = new Float(observation.value1);
                
                if (isOutside(recommendedOxySatLow, recommendedOxySatHigh, observedOxySat)) {
                    alert = true;
                    alertId = 4;
                    if (isLow(observedOxySat, recommendedOxySatLow)) {
                        alertReason = "Patient's oxygen saturation is "+ (recommendedOxySatLow - observedOxySat) + " lesser than the lower limit.";
                    } else {
                        alertReason = "Patient's oxygen saturation is "+ (observedOxySat - recommendedOxySatHigh) + " higher than the upper limit.";
                    }
                } else {
                    mDb.updateSentAlerts(patientName, 4);
                }
                break;
            }
            case "Temperature" : {
                mDb.updateSentAlerts(patientName, 13);
                Float recommendedTemperatureLow = (Float) recommendations.get(0).get(4);
                Float recommendedTemperatureHigh = (Float) recommendations.get(0).get(5);
                
                ObservationNew observation = mDb.getObservedTemperature(patientName);
                Float observedTemperature = new Float(observation.value1);
                
                if (isOutside(recommendedTemperatureLow, recommendedTemperatureHigh, observedTemperature)) {
                    alert = true;
                    alertId = 7;
                    if (isLow(observedTemperature, recommendedTemperatureLow)) {
                        alertReason = "Patient's temperature is "+ (recommendedTemperatureLow - observedTemperature) + " lesser than the lower limit.";
                    } else {
                        alertReason = "Patient's temperature is "+ (observedTemperature - recommendedTemperatureHigh) + " higher than the upper limit.";
                    }
                } else {
                    mDb.updateSentAlerts(patientName, 7);
                }
                break;
            }
            case "Pain Level" : {
                mDb.updateSentAlerts(patientName, 11);
                break;
            }
            case "Mood" : {
                mDb.updateSentAlerts(patientName, 12);
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
    }
    
    public void checkForLowActivityAlerts() {
        ArrayList<ArrayList<Object>> recommendations = this.mDb.getBestRecommendations(patientName);
        
        Recommendation recommendation = new Recommendation();
        recommendation.painLevelFrequency = (Integer) recommendations.get(0).get(1);
        recommendation.moodFrequency = (Integer) recommendations.get(0).get(3);
        recommendation.tempertureFrequency = (Integer) recommendations.get(0).get(6);        
        recommendation.weightFrequency = (Integer) recommendations.get(0).get(9);
        recommendation.bpFrequency = (Integer) recommendations.get(0).get(14);
        recommendation.oxySatFrequency = (Integer) recommendations.get(0).get(17);
                
        ObservationNew weightObservation = mDb.getObservedWeight(patientName);
        ObservationNew bpObservation = mDb.getObservedBloodPressure(patientName);
        ObservationNew oxySatObservation = mDb.getObservedOxySat(patientName);
        ObservationNew painObservation = mDb.getObservedPain(patientName);
        ObservationNew moodObservation = mDb.getObservedMood(patientName);
        ObservationNew temperatureObservation = mDb.getObservedTemperature(patientName);
        
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        long wDays = getDifferenceDays(weightObservation.observed_on, sqlDate) - 1;
        long bpDays = getDifferenceDays(bpObservation.observed_on, utilDate) - 1;
        long osDays = getDifferenceDays(oxySatObservation.observed_on, utilDate) - 1;
        long pDays = getDifferenceDays(painObservation.observed_on, utilDate) - 1;
        long mDays = getDifferenceDays(moodObservation.observed_on, utilDate) - 1;
        long tDays = getDifferenceDays(temperatureObservation.observed_on, utilDate) - 1;
        
        if (recommendation.weightFrequency != null && isLowFrequency(wDays, recommendation.weightFrequency)) {
            // create alert id 8
            String alertReason = "Patient's weight has not been entered from " + wDays + " days.";
            try {
                this.mDb.createAlert(patientName, 8, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if (recommendation.bpFrequency != null && isLowFrequency(bpDays, recommendation.bpFrequency)) {
            // create alert id 9
            String alertReason = "Patient's blood pressure has not been entered from " + bpDays + " days.";
            try {
                this.mDb.createAlert(patientName, 9, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if (recommendation.oxySatFrequency != null && isLowFrequency(osDays, recommendation.oxySatFrequency)) {
            // create alert id 10
            String alertReason = "Patient's oxygen saturation has not been entered from " + osDays + " days.";
            try {
                this.mDb.createAlert(patientName, 10, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if (recommendation.painLevelFrequency != null && isLowFrequency(pDays, recommendation.painLevelFrequency)) {
            // create alert id 11
            String alertReason = "Patient's pain level has not been entered from " + pDays + " days.";
            try {
                this.mDb.createAlert(patientName, 11, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if (recommendation.moodFrequency != null && isLowFrequency(mDays, recommendation.moodFrequency)) {
            // create alert id 12
            String alertReason = "Patient's mood has not been entered from " + mDays + " days.";
            try {
                this.mDb.createAlert(patientName, 12, alertReason);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if (recommendation.tempertureFrequency != null && isLowFrequency(tDays, recommendation.tempertureFrequency)) {
            // create alert id 13
            String alertReason = "Patient's temperature has not been entered from " + tDays + " days.";
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

    private boolean isLow(Integer x, Integer y) {
        return x < y;
    }
    
    private static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
