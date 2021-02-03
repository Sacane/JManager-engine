package com.sacane.manager;

public class Date {

    public enum Day {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }


    public enum Month{
        JANUARY,
        FEBRUARY,
        MARS,
        APRIL,
        MAY,
        JUNE,
        JULY,
        SEPTEMBER,
        OCTOBER,
        DECEMBER
    }

    private Month month;

    private Day day;

    private int year;


    public Date(Month month, Day day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }



}
