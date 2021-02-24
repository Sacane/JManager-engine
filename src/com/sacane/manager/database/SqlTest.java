package com.sacane.manager.database;

import com.sacane.manager.Month;

import java.sql.SQLException;

public class SqlTest {

    public static void main(String[] args) throws SQLException {
        var test = new DbHandler();
        test.connection();

        test.close();
    }
}
