package com.sacane.manager;

import java.util.Calendar;

import java.util.HashSet;



public class Grid {

    private Calendar calendar = Calendar.getInstance();
    private HashSet<Income> manager = new HashSet<>();


    public void addIncome(String nameLabel, double income, boolean isIncome, Date day){

        manager.add(new Income(nameLabel, income, isIncome, day));

    }

    public double incomeForMonth(){
        return manager.stream().mapToDouble(Income::cost).sum();
    }
}
