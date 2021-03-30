package com.sacane.manager.wrapper;

import com.sacane.manager.data.Month;
import com.sacane.manager.wrapper.DbHandler;

import java.sql.SQLException;



public class ModelWrapper {

    //DataBase accessor
    private final DbHandler handler = new DbHandler();

    //Actual data
    private int currentYear;
    private Month currentMonth;
    private final double totalSold = updateSold();


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
        } finally {
            System.out.println("Sold updated");
            handler.close();
        }
        return 0D;

    }

    public static int getNumberTrans(){
        var access = new DbHandler();
        access.connection();
        try {
            var set = access.getSetByRequest("SELECT MAX(id_trans) as count FROM trans");
            var count = set.getInt("count");
            System.out.println(count);
            access.close();
            return count;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return 0;
    }

    public double getTotalSold() {
        return totalSold;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentMonth(Month currentMonth) {
        this.currentMonth = currentMonth;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

}

