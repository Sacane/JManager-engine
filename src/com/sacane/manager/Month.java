package com.sacane.manager;

public enum Month {
    JANUARY("January"),
    FEBRUARY("February"),
    MARCH("March"),
    APRIL("April"),
    MAY("May"),
    JUNE("June"),
    JULY("July"),
    SEPTEMBER("September"),
    OCTOBER("October"),
    NOVEMBER("November"),
    DECEMBER("December");

    private final String typeText;
    Month(String typeText){
        this.typeText = typeText;
    }

    String getTypeText(){
        return typeText;
    }
}
