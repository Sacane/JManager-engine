package com.sacane.manager;

import com.sacane.calc.evaluator.BaseOperator;

import java.util.Arrays;

public enum Month {

    JANUARY("January", "01", 31),
    FEBRUARY("February", "02", 29),
    MARCH("March", "03", 31),
    APRIL("April", "04", 30),
    MAY("May", "05", 31),
    JUNE("June", "06", 30),
    JULY("July", "07", 31),
    AUGUST("August", "08", 31),
    SEPTEMBER("September", "09", 30),
    OCTOBER("October", "10", 31),
    NOVEMBER("November", "11", 30),
    DECEMBER("December", "12", 31);

    private final String typeText;
    private final String representation;
    private final int numberDay;


    Month(String typeText, String representation, int numberDay){
        this.typeText = typeText;
        this.representation = representation;
        this.numberDay = numberDay;
    }

    public String getTypeText(){
        return typeText;
    }

    public int getNumberDay(){ return numberDay; }

    String getRepresentation(){
        return representation;
    }

    public static Month get(String symbol) {
        return Arrays.stream(Month.values()).filter(it -> it.typeText.equals(symbol)).findFirst().orElse(null);
    }
}
