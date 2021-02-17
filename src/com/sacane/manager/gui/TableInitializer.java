package com.sacane.manager.gui;

import javax.swing.*;
import java.sql.ResultSet;
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


    void buildTable(){

    }

    public JTable getTable(){
        return null;
    }
}
