/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthcare;

/**
 *
 * @author akshat
 */
public class Observation {
    Float weight;
    Integer bpDiastolic, bpSystolic;
    Float oxygenSat;
    String painLevel, mood;
    Float temperature;
    String observedOn;

    public Observation() {
    }
    
    public Observation(Float weight, Integer bpDiastolic, Integer bpSystolic, Float oxygenSat, String painLevel, String mood, Float temperature, String observedOn) {
        this.weight = weight;
        this.bpDiastolic = bpDiastolic;
        this.bpSystolic = bpSystolic;
        this.oxygenSat = oxygenSat;
        this.painLevel = painLevel;
        this.mood = mood;
        this.temperature = temperature;
        this.observedOn = observedOn;
    }
    
    @Override
    public String toString() {
        return "Observation{" + "weight=" + weight + ", bpDiastolic=" + bpDiastolic + ", bpSystolic=" + bpSystolic + ", oxygenSat=" + oxygenSat + ", painLevel=" + painLevel + ", mood=" + mood + ", temperature=" + temperature + ", observedOn=" + observedOn + '}';
    }

}
