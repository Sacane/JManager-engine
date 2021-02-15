package com.sacane.manager;

import com.sacane.calc.evaluator.BaseOperator;

import java.util.Arrays;

public enum Month {

    JANUARY("January", "01"),
    FEBRUARY("February", "02"),
    MARCH("March", "03"),
    APRIL("April", "04"),
    MAY("May", "05"),
    JUNE("June", "06"),
    JULY("July", "07"),
    AUGUST("August", "08"),
    SEPTEMBER("September", "09"),
    OCTOBER("October", "10"),
    NOVEMBER("November", "11"),
    DECEMBER("December", "12");

    private final String typeText;
    private final String representation;



    Month(String typeText, String representation){
        this.typeText = typeText;
        this.representation = representation;
    }

    public String getTypeText(){
        return typeText;
    }

    String getRepresentation(){
        return representation;
    }

    public static Month get(String symbol) {
        return Arrays.stream(Month.values()).filter(it -> it.typeText.equals(symbol)).findFirst().orElse(null);
    }
}
