/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthcare;

import java.util.Date;

/**
 *
 * @author Rahul
 */
public class HsInfo{
        String name;
        String username;
        Date auth_date;
        public HsInfo(String name, String username) {
            this.name = name;
            this.username = username;
        }
        public HsInfo(String name, String username, Date auth_date) {
            this.name = name;
            this.username = username;
            this.auth_date = auth_date;
        }
    }