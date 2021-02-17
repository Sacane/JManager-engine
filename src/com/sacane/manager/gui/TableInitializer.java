package com.sacane.manager.gui;

import com.sacane.manager.database.DataBuild;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

// TODO : -convert ResultSet -> Object[][] and make it...

public class TableInitializer {

    private final JTable table = new JTable();
    private final ArrayList<String> titleArray;
    private final ResultSet dataList;

    public TableInitializer(ResultSet dataList, ArrayList<String> titleArray){
        Objects.requireNonNull(titleArray);
        Objects.requireNonNull(dataList);
        this.titleArray = new ArrayList<>(titleArray);
        this.dataList = dataList;
    }

    Object[] buildTitles(){
        return titleArray.toArray();
    }

    public static Object[][] tableIncome(DataBuild builder) throws SQLException {
        var nbRow = builder.getNumberRow();
        var set = builder.getSetIncome();
        var data = new Object[nbRow][4];


        for(int i = 0; set.next(); i++){

            var label = set.getString("label");
            var value = set.getDouble("value");
            var description = set.getString("description");
            var date = set.getString("date");

            data[i][0] = data;
            data[i][1] = label;
            data[i][2] = value;
            data[i][3] = description;

        }
        return data;
    }



    void buildTable(){

    }

    public JTable getTable(){
        return null;
    }
}
