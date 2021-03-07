package com.sacane.manager.database;
import com.sacane.manager.gui.ModelWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class DbHandler {

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

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        try {

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Statement getNewStatement() throws SQLException {
        return connection.createStatement();
    }

    public void executeRequest(String request) throws SQLException{
        statement.executeUpdate(request);
    }

    public void addDbIncome(boolean is_in, String label, double value, String description, String date) throws SQLException {
        statement.executeUpdate(QueryBuilder.insertTrans(is_in, label, value, description));
        statement.executeUpdate(QueryBuilder.insertIncome(ModelWrapper.getNumberTrans(), date));
    }

    public int getIdTransByLabel(String name_label) throws SQLException {
        var sql = "SELECT id_trans FROM trans WHERE label = '" + name_label + "'";
        var set = statement.executeQuery(sql);
        var id = set.getInt("id_trans");
        return id;
    }

    public void deleteIncome(String name_label) throws SQLException {
        var id = getIdTransByLabel(name_label);
        statement.executeUpdate("DELETE FROM income WHERE id_income =" + id);
        statement.executeUpdate("DELETE FROM trans WHERE id_trans = " + id);
    }


    public void addDbPromise(boolean is_in, String label, double value, String description, String name_owner)throws SQLException{
        statement.executeUpdate(QueryBuilder.insertTrans(is_in, label, value, description));
        statement.executeUpdate(QueryBuilder.insertPromises(getIdTrans(label), name_owner));
    }

    public void addDbProject(boolean is_in, String label, double value, String description, int day, int duration)throws SQLException{
        statement.executeUpdate(QueryBuilder.insertTrans(is_in, label, value, description));
        statement.executeUpdate(QueryBuilder.insertProject(getIdTrans(label), day, duration));
    }

    public void deleteIncome(int id_trans) throws SQLException {
        statement.executeUpdate(QueryBuilder.deleteIncome(id_trans));
        statement.executeUpdate(QueryBuilder.deleteTrans(id_trans));
    }
    private ResultSet getAmount(boolean is_income) throws SQLException{

        var res = statement.executeQuery("SELECT value_inc FROM income WHERE is_income = " + is_income);
        return res;
    }

    int getIdTrans(String label) throws SQLException{
        connection();

        var res = statement.executeQuery(QueryBuilder.getIdTransaction(label));
        var ret = res.getInt("id_trans");
        close();
        return ret;

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

    public int getNumberRowAccount() throws SQLException{
        var res = statement.executeQuery(QueryBuilder.getNumberRowAccount());
        return res.getInt("numberRow");
    }

    public ResultSet getSetIncome() throws SQLException{
        return statement.executeQuery(QueryBuilder.selectTrans("income"));
    }



    public ResultSet getSetAccount() throws SQLException{
        return statement.executeQuery(QueryBuilder.selectAccount());
    }

    public ResultSet getSetTotal() throws SQLException {
        return statement.executeQuery(QueryBuilder.selectTotal());
    }

    public ResultSet getSetByRequest(String request) throws SQLException {
        return statement.executeQuery(request);

    }
}
