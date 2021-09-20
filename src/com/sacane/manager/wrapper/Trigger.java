package com.sacane.manager.wrapper;


import java.sql.SQLException;

//TODO : WHEN an income is insert, Update the table histo
public class Trigger {

    private final ModelWrapper wrapper;

    public Trigger(ModelWrapper wrapper){
        this.wrapper = wrapper;
    }


    public static void updateHisto(double actualSold, int id_income, String date, String label){
        var handler = new DbHandler();
        handler.connection();
        try {
            handler.executeRequest(QueryBuilder.insertHisto(actualSold, id_income,date, label));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }finally {
            handler.close();
        }
    }

}
