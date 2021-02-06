package com.sacane.manager;
import java.util.Objects;

public class Leaf {

    private String label;
    private double amount;
    private final boolean isIncome;
    private String description;
    private int day;

    public Leaf(String label, double amount, boolean isIncome, String description, int day) throws IllegalArgumentException, NullPointerException{
        Objects.requireNonNull(label);
        Objects.requireNonNull(description);
        if(amount < 0){
            throw new IllegalArgumentException("amount can't be negative");
        }
        if(day < 0 || day > 31){
            throw new IllegalArgumentException("day can't exist");
        }
        this.label = label;
        this.amount = amount;
        this.isIncome = isIncome;
        this.description = description;
        this.day = day;
    }

    double getAmount() {
        return (isIncome) ? amount : -amount;
    }

    void setAmount(double amount) {
        this.amount = amount;
    }

    void changeLabel(String newLabel){
        this.label = newLabel;
    }

    boolean isIncome() {
        return isIncome;
    }

    public String getLabel() {
        return label;
    }

    public void setDay(int newDay) {
        this.day = newDay;
    }

    public int getDay(){
        return day;
    }

    void changeDescription(String description){
        this.description = description;
    }

}