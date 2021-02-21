package com.sacane.manager.database;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class DataBuild {

    private Connection connection;
    private Statement statement;

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

            System.out.println("Connection to the database has been established.");
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

    public void executeRequest(String request) throws SQLException{
        statement.executeUpdate(request);
    }

    public void addDbIncome(boolean is_in, String label, double value, String description, String date) throws SQLException {
        statement.executeUpdate(QueryBuilder.insertTrans(is_in, label, value, description));
        statement.executeUpdate(QueryBuilder.insertIncome(getIdTrans(label), date));
    }

    public void addDbPromise(boolean is_in, String label, double value, String description, String name_owner)throws SQLException{
        statement.executeUpdate(QueryBuilder.insertTrans(is_in, label, value, description));
        statement.executeUpdate(QueryBuilder.insertPromises(getIdTrans(label), name_owner));
    }
    public void addDbProject(boolean is_in, String label, double value, String description, int day, int duration)throws SQLException{
        statement.executeUpdate(QueryBuilder.insertTrans(is_in, label, value, description));
        statement.executeUpdate(QueryBuilder.insertProject(getIdTrans(label), day, duration));
    }

    private ResultSet getAmount(boolean is_income) throws SQLException{

        var res = statement.executeQuery("SELECT value_inc FROM income WHERE is_income = " + is_income);
        return res;
    }

    int getIdTrans(String label) throws SQLException{
        var res = statement.executeQuery(QueryBuilder.getIdTransaction(label));
        return res.getInt("id_trans");
    }

    public ArrayList<Double> updateIncome(boolean is_income) throws SQLException{
        var listIncome = new ArrayList<Double>();
        var incomes = getAmount(is_income);
        while(Objects.requireNonNull(incomes).next()){
            listIncome.add(incomes.getDouble("value_inc"));
        }
        return listIncome;
    }

    public int getNumberRow() throws SQLException{
        var res = statement.executeQuery(QueryBuilder.getNumberRow());
        return res.getInt("numberRow");
    }

    public ResultSet getSetIncome() throws SQLException{
        return statement.executeQuery(QueryBuilder.selectTrans("income"));
    }

    public ResultSet getSetTotal() throws SQLException {
        return statement.executeQuery(QueryBuilder.selectTotal());
    }

}
