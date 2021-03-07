package com.sacane.manager.gui;

import com.sacane.manager.database.DbHandler;
import com.sacane.manager.database.QueryBuilder;

import javax.management.Query;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;


public class TableInitializer {


    private final ArrayList<String> titleArray;

    public TableInitializer(ArrayList<String> titleArray){
        Objects.requireNonNull(titleArray);
        this.titleArray = new ArrayList<>(titleArray);
    }

    public Object[] buildTitles(){
        return titleArray.toArray();
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


    public static Object[] buildDayCheckBox(int numberDay){
        var builder = new Object[numberDay+5];
        for(int i = 0; i < numberDay; i++){
            builder[i] = i+1;
        }
        return builder;
    }

    private static int getNumberAccount(){
        var handler = new DbHandler();
        handler.connection();
        int number;
        try {
            var setNumber = handler.getSetByRequest(QueryBuilder.getNumberAccount());
            number = setNumber.getInt("count");
            handler.close();
            return number;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

    public static Object[] buildAccountCheckBox(){

        var handler = new DbHandler();


        try {
            handler.connection();
            var setNames = handler.getSetAccount();


            var builder = new Object[getNumberAccount()];

            for(int i = 0; setNames.next(); i++){
                System.out.println("hello");
                builder[i] = setNames.getString("name_account");
            }
            handler.close();
            return builder;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }

    public JTable sendTable(DbHandler builder, double total) throws SQLException{
        var model = new DefaultTableModel(tableIncome(builder, total), buildTitles());
        var table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        return table;
    }


}
