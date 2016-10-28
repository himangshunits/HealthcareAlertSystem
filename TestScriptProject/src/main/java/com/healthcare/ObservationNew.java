/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthcare;

import java.util.Date;

/**
 *
 * @author akshat
 */
public class ObservationNew {
    String type = "";
    String value1 = "";
    String value2 = "";
    Date observed_on = null;
    Date recorded_on = null;

    @Override
    public String toString() {
        return "Observation{" + "type=" + type + ", value1=" + value1 + ", value2=" + value2 + ", observed_on=" + observed_on + ", recorded_on=" + recorded_on + '}';
    }

    public ObservationNew(String type, String value1, String value2, Date observed_on, Date recorded_on) {
        this.type = type;
        this.value1 = value1;
        this.value2 = value2;
        this.observed_on = observed_on;
        this.recorded_on = recorded_on;
    }
}
