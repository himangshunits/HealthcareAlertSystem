/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthcare;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rahul
 */
public class NonEditableModel extends DefaultTableModel {

    NonEditableModel(Object[][] data, String[] columnNames) {
        super(data, columnNames);
    }
    
    NonEditableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}