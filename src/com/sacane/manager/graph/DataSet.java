package com.sacane.manager.graph;
import com.sacane.manager.data.Month;
import com.sacane.manager.wrapper.DbHandler;
import com.sacane.manager.wrapper.ModelWrapper;
import com.sacane.manager.wrapper.QueryBuilder;
import de.erichseifert.gral.data.DataTable;
import java.sql.SQLException;


@SuppressWarnings("unchecked")
public class DataSet {

    private final ModelWrapper wrapper;

    public DataSet(ModelWrapper wrapper){
        this.wrapper = wrapper;
    }

    DataTable getTableFromDate(){
        var handler = new DbHandler();
        handler.connection();

        DataTable table = new DataTable(Integer.class, Double.class);


        try {

            var set = handler.getSetByRequest(
                    QueryBuilder.getIncomeMonth(Integer.parseInt(wrapper.getCurrentMonth().representation()),
                    wrapper.getCurrentYear()));

            //Filling of the table to return
            while(set.next()){
                table.add(Month.getDayInDate(set.getString("date")), Double.parseDouble(set.getString("value")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }finally {
            handler.close();
        }
        return table;
    }


}
