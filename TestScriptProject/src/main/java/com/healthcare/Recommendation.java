/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthcare;

/**
 *
 * @author Himangshu
 */
public class Recommendation {
    public Integer bpSystolicLow;
    public Integer bpSystolicHigh;
    public Integer bpDiastolicLow;
    public Integer bpDiastolicHigh;
    public Integer bpFrequency;
    public Float oxySatLow;
    public Float oxySatHigh;
    public Integer oxySatFrequency;
    public String painLevel;
    public Integer painLevelFrequency;
    public String mood;
    public Integer moodFrequency;
    public Float temperatureLow;
    public Float temperatureHigh;
    public Integer temperatureFrequency;
    public Float weightLow;
    public Float weightHigh;
    public Integer weightFrequency;

    public Recommendation(Integer bpSystolicLow, Integer bpSystolicHigh, Integer bpDiastolicLow, Integer bpDiastolicHigh, Integer bpFrequency, Float oxySatLow, Float oxySatHigh, Integer oxySatFrequency, String painLevel, Integer painLevelFrequency, String mood, Integer moodFrequency, Float temperatureLow, Float temperatureHigh, Integer tempertureFrequency, Float weightLow, Float weightHigh, Integer weightFrequency) {
        this.bpSystolicLow = bpSystolicLow;
        this.bpSystolicHigh = bpSystolicHigh;
        this.bpDiastolicLow = bpDiastolicLow;
        this.bpDiastolicHigh = bpDiastolicHigh;
        this.bpFrequency = bpFrequency;
        this.oxySatLow = oxySatLow;
        this.oxySatHigh = oxySatHigh;
        this.oxySatFrequency = oxySatFrequency;
        this.painLevel = painLevel;
        this.painLevelFrequency = painLevelFrequency;
        this.mood = mood;
        this.moodFrequency = moodFrequency;
        this.temperatureLow = temperatureLow;
        this.temperatureHigh = temperatureHigh;
        this.temperatureFrequency = tempertureFrequency;
        this.weightLow = weightLow;
        this.weightHigh = weightHigh;
        this.weightFrequency = weightFrequency;
    }

    public Recommendation() {
        
    }

    @Override
    public String toString() {
        return "Recommendation{" + "bpSystolicLow=" + bpSystolicLow + ", bpSystolicHigh=" + bpSystolicHigh + ", bpDiastolicLow=" + bpDiastolicLow + ", bpDiastolicHigh=" + bpDiastolicHigh + ", bpFrequency=" + bpFrequency + ", oxySatLow=" + oxySatLow + ", oxySatHigh=" + oxySatHigh + ", oxySatFrequency=" + oxySatFrequency + ", painLevel=" + painLevel + ", painLevelFrequency=" + painLevelFrequency + ", mood=" + mood + ", moodFrequency=" + moodFrequency + ", temperatureLow=" + temperatureLow + ", temperatureHigh=" + temperatureHigh + ", temperatureFrequency=" + temperatureFrequency + ", weightLow=" + weightLow + ", weightHigh=" + weightHigh + ", weightFrequency=" + weightFrequency + '}';
    }

     
}
