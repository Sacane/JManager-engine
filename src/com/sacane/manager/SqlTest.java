package com.sacane.manager;

public class SqlTest {
    public static void main(String[] args) {
        var connecting = new DataManagement();
        connecting.connection();
        connecting.executeRequest(QueryBuilder.deleteIncome("keyboard"));
        connecting.close();
    }
}
