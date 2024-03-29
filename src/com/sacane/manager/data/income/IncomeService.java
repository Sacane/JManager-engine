package com.sacane.manager.data.income;
import com.sacane.manager.wrapper.DbHandler;
import com.sacane.manager.wrapper.QueryBuilder;
import com.sacane.manager.wrapper.ModelWrapper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IncomeService {

    private static IncomeService instance;
    private final DbHandler dbHandler = new DbHandler();
    private List<IncomeManager> income;
    private final ModelWrapper wrapper;

    public IncomeService(ModelWrapper wrapper){
        this.wrapper = wrapper;
    }

    private void loadByDb(){

        income = new ArrayList<>();
        dbHandler.connection();

        try {

            var set = dbHandler.getSetByRequest(QueryBuilder.getIncomeMonth(Integer.parseInt(wrapper.getCurrentMonth().representation()), wrapper.getCurrentYear()));

            while(set.next()){
                income.add(new IncomeManager(set.getString("label"), set.getDouble("value"), set.getString("date")));
                //add set.getDouble("actualSold");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        dbHandler.close();
    }

    List<IncomeManager> findLastIncome(){
        loadByDb();
        return income;
    }



    static synchronized IncomeService getInstance(ModelWrapper wrapper) {
        if (instance == null) {
            instance = new IncomeService(wrapper);
        }
        return instance;
    }
}
