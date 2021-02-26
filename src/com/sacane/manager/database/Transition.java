package com.sacane.manager.database;

public class Transition {

    private final boolean isIn;
    private final String label;
    private final double value;

    public Transition(boolean isIn, String label, double value){
        this.isIn = isIn;
        this.label = label;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public boolean isIn() {
        return isIn;
    }

}
