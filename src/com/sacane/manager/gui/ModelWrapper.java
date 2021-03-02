package com.sacane.manager.gui;

import com.sacane.manager.Month;
import com.sacane.manager.income.IncomeModel;

public class ModelWrapper {


    private int currentYear;
    private Month currentMonth;
    private double totalSold;
    private int maxYear;



    public Month getCurrentMonth() {
        return currentMonth;
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

    public void setTotalSold(double totalSold) {
        this.totalSold = totalSold;
    }

    public void setMaxYear(int maxYear) {
        this.maxYear = maxYear;
    }
}

