package com.sacane.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class testConnection {

    private static void connection() throws SQLException{
        Connection conn = null;
        try {
            //get the necessary paths
            var pathJdbc = "jdbc:sqlite:";
            var pwd = System.getProperty("user.dir");
            var pathDb = "/database/manager.db";

            // db parameters
            String url = pathJdbc + pwd + pathDb;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static boolean connectionSuccess(){
        try {
            connection();
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }

    public static void main(String[] args) {



    }
}
