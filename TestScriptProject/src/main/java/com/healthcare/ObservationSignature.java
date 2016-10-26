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
public class ObservationSignature {
    public boolean isWeight;
    public boolean isBp;
    public boolean isOxygenSat;
    public boolean isPainLevel;
    public boolean isMood;
    public boolean isTemperature;

    public ObservationSignature(boolean isWeight, boolean isBp, boolean isOxygenSat, boolean isPainLevel, boolean isMood, boolean isTemperature) {
        this.isWeight = isWeight;
        this.isBp = isBp;
        this.isOxygenSat = isOxygenSat;
        this.isPainLevel = isPainLevel;
        this.isMood = isMood;
        this.isTemperature = isTemperature;
    }
    
    public ObservationSignature(){
    }
    
}
