package com.sacane.manager.data.income;

public class IncomeManager {
    private double value;
    private final String nameLabel;
    private final String date;


    public IncomeManager(String nameLabel, double value, String date){
        this.nameLabel = nameLabel;
        this.value = value;
        this.date = date;
    }



    public double getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }

    public String getNameLabel() {
        return nameLabel;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
