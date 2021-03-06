package com.sacane.manager;

import com.sacane.calc.evaluator.BaseOperator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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

    public String representation(){
        return representation;
    }

    public static String formattedDate(int dayBegin, Month month, int year){
        if(month == null){
            throw new IllegalArgumentException("Month null");
        }
        var formatDay = (dayBegin <= 9) ? "0" + dayBegin : String.valueOf(dayBegin);

        return year + "-" + month.representation() + "-" + formatDay;
    }

    public static Month getMonthByRep(int rep){
        if(rep > 12){
            rep = rep%12;
        }
        if(rep < 0){
            throw new IllegalArgumentException("representation can't be negative");
        }
        int finalRep = rep;
        return Arrays.stream(Month.values()).filter(it -> Integer.valueOf(it.representation).equals(finalRep)).findFirst().orElse(null);
    }

    public static Month get(String symbol) {
        return Arrays.stream(Month.values()).filter(it -> it.typeText.equals(symbol)).findFirst().orElse(null);
    }

}
