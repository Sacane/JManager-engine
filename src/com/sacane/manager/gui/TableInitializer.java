package com.sacane.manager.gui;

import com.sacane.manager.database.DataBuild;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
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

    Object[] buildTitles(){
        return titleArray.toArray();
    }

    void updateTotal(DataBuild builder) throws SQLException{
        var array = builder.getSetTotal();
        total = array.getInt("total");

    }

    Object[][] tableIncome(DataBuild builder) throws SQLException {
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



    public JTable sendTable(DataBuild builder) throws SQLException{
        var model = new DefaultTableModel(tableIncome(builder), buildTitles());
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
