package com.sacane.manager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSql {
    private Connection connection;
    private final String dbPath;
    private Statement statement;

    public DataSql(String dbPath){
        this.dbPath = dbPath;
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
            connection = DriverManager.getConnection(dbPath);
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

    public void addIncome(String request){
        try{
            statement.executeUpdate(request);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




    String getDbPath(){
        return dbPath;
    }
}
