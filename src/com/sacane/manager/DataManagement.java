package com.sacane.manager;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class DataManagement {

    private Connection connection;
    private Statement statement;

    public DataManagement(){

        connection = null;
        statement = null;
    }

    static String getPathToSql(){
        var pathJdbc = "jdbc:sqlite:";
        var pwd = System.getProperty("user.dir");
        var pathDb = "/database/manager.db";
        return pathJdbc + pwd + pathDb;
    }

    public void connection(){
        try {
            Class.forName("org.sqlite.JDBC");

            // create a connection to the database
            connection = DriverManager.getConnection(getPathToSql());
            statement = connection.createStatement();

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        try {
            connection.close();
            statement.close();
            System.out.println("All close successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void executeRequest(String request){
        try{
            statement.executeUpdate(request);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private ResultSet getElementByRequest(boolean is_income){
        ResultSet res;
        try{
            res = statement.executeQuery("SELECT value_inc FROM income WHERE is_income = " + String.valueOf(is_income));
        } catch (SQLException se) {
            return null;
        }
        return res;
    }

    public ArrayList<Double> getInOutcome(boolean is_income){
        var listIncome = new ArrayList<Double>();
        var incomes = getElementByRequest(true);
        try {
            while(Objects.requireNonNull(incomes).next()){
                listIncome.add(incomes.getDouble("value_inc"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }
        return listIncome;
    }



}
