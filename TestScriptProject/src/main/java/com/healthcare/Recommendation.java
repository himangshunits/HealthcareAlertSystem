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
    public double bpSystolicLow;
    public double bpSystolicHigh;
    public double bpDiastolicLow;
    public double bpDiastolicHigh;
    public int bpFrequency;
    public double oxySatLow;
    public double oxySatHigh;
    public int oxySatFrequency;
    public int painLevel;
    public int painLevelFrequency;
    public String mood;
    public int moodFrequency;
    public double temperatureLow;
    public double temperatureHigh;
    public int tempertureFrequency;
    public double weightLow;
    public double weightHigh;
    public int weightFrequency;

    public Recommendation(double bpSystolicLow, double bpSystolicHigh, double bpDiastolicLow, double bpDiastolicHigh, int bpFrequency, double oxySatLow, double oxySatHigh, int oxySatFrequency, int painLevel, int painLevelFrequency, String mood, int moodFrequency, double temperatureLow, double temperatureHigh, int tempertureFrequency, double weightLow, double weightHigh, int weightFrequency) {
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
        this.tempertureFrequency = tempertureFrequency;
        this.weightLow = weightLow;
        this.weightHigh = weightHigh;
        this.weightFrequency = weightFrequency;
    }   
}
