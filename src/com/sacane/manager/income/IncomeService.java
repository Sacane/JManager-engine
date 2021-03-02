package com.sacane.manager.income;


import com.sacane.manager.database.DbHandler;

import java.sql.SQLException;


public class IncomeService {

    private static IncomeService instance;
    private final DbHandler dbHandler = new DbHandler();

    private void loadByDb(){
        dbHandler.connection();



        dbHandler.close();
    }


    double getTotal(){
        dbHandler.connection();
        var total = 0D;
        try {
            var dbSet = dbHandler.getSetTotal();
            total = dbSet.getDouble("total");
            dbHandler.close();
            return total;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        dbHandler.close();
        return 0D;
    }
}
