package com.sacane.manager.income;

public class IncomeManager {
    private double value;
    private final String name_label;
    private final String date;


    public IncomeManager(String name_label, double value, String date){
        this.name_label = name_label;
        this.value = value;
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }

    public String getName_label() {
        return name_label;
    }
}
