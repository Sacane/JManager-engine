package com.sacane.manager.income;


import com.sacane.manager.Month;
import com.sacane.manager.account.AccountService;
import com.sacane.manager.database.DbHandler;
import com.sacane.manager.database.QueryBuilder;
import com.sacane.manager.gui.ModelWrapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class IncomeService {

    private static IncomeService instance;
    private final DbHandler dbHandler = new DbHandler();
    private List<IncomeManager> income;
    private ModelWrapper wrapper;

    public IncomeService(ModelWrapper wrapper){
        this.wrapper = wrapper;
    }

    private void loadByDb(){

        income = new ArrayList<>();
        dbHandler.connection();

        try {
            var set = dbHandler.getSetByRequest(QueryBuilder.selectMonthInfos(Integer.parseInt(wrapper.getCurrentMonth().representation()), wrapper.getCurrentYear()));
            while(set.next()){
                income.add(new IncomeManager(set.getString("label"), set.getDouble("value"), set.getString("date")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }


        dbHandler.close();
    }

    public List<IncomeManager> findLastIncome(){
        loadByDb();
        return income;
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

    public static synchronized IncomeService getInstance(ModelWrapper wrapper) {
        if (instance == null) {
            instance = new IncomeService(wrapper);
        }
        return instance;
    }
}
