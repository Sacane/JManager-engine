package com.sacane.manager.gui;

import com.sacane.manager.Month;
import com.sacane.manager.database.DbHandler;

import java.sql.SQLException;


public class ModelWrapper {


    private int currentYear;
    private Month currentMonth;
    private int maxYear;
    private final DbHandler handler = new DbHandler();
    private double totalSold = updateSold();


    public Month getCurrentMonth() {
        return currentMonth;
    }

    public double updateSold() {
        handler.connection();
        var total = 0D;
        try {
            var dbSet = handler.getSetTotal();
            total = dbSet.getDouble("total");
            handler.close();
            return total;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        handler.close();
        return 0D;
    }

    public void update(){
        totalSold = updateSold();
    }

    public double getTotalSold() {
        return totalSold;
    }

    public int getCurrentYear() {
        return currentYear;
    }



    public int getMaxYear() {
        return maxYear;
    }

    public void setCurrentMonth(Month currentMonth) {
        this.currentMonth = currentMonth;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }



    public void setMaxYear(int maxYear) {
        this.maxYear = maxYear;
    }

    public String getDateByDay(int day) throws IllegalArgumentException{
        if(day > currentMonth.getNumberDay() || day < 0){
            throw new IllegalArgumentException("day is not available");
        }

        return Month.formattedDate(day, currentMonth, currentYear);

    }

}

