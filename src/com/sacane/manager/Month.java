package com.sacane.manager;

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
}
