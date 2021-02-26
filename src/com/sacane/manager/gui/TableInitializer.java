package com.sacane.manager.gui;

import com.sacane.manager.database.DbHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;


public class TableInitializer {


    private final ArrayList<String> titleArray;
    private int total = 0;

    public TableInitializer(ArrayList<String> titleArray){
        Objects.requireNonNull(titleArray);
        this.titleArray = new ArrayList<>(titleArray);
    }

    public Object[] buildTitles(){
        return titleArray.toArray();
    }

    void updateTotal(DbHandler builder) throws SQLException{
        var array = builder.getSetTotal();
        total = array.getInt("total");

    }

    Object[][] tableIncome(DbHandler builder, double total) throws SQLException {
        var nbRow = builder.getNumberRow();
        var set = builder.getSetIncome();
        var data = new Object[nbRow][titleArray.size()];

        for(int i = 0; set.next(); i++){

            var label = set.getString("label");

            var date = set.getString("date");
            boolean is_in = Boolean.parseBoolean(set.getString("is_in"));

            var value = set.getDouble("value");
            double translateValue = (is_in) ? value : value*(-1);

            data[i][0] = date;
            data[i][1] = label;
            data[i][2] = translateValue;
            data[i][3] = total;

        }
        return data;
    }

    Object[][] tableAccount(DbHandler handler) throws SQLException{
        var nbRow = handler.getNumberRowAccount();
        var set = handler.getSetAccount();
        var data = new Object[nbRow][titleArray.size()];

        for(int i = 0; set.next(); i++){
            var name = set.getString("name_account");
            var amount = set.getDouble("amount");

            data[i][0] = name;
            data[i][1] = amount;
        }

        return data;
    }




    public JTable sendTable(DbHandler builder, double total) throws SQLException{
        var model = new DefaultTableModel(tableIncome(builder, total), buildTitles());
        var table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        return table;
    }


    void buildTable(){

    }

    public JTable getTable(){
        return null;
    }
}
