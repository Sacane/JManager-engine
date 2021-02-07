package com.sacane.manager;

public class SqlTest {
    public static void main(String[] args) {
        var connecting = new DataSql(DataSql.getPathToSql());
        connecting.connection();
        var insert = TableBuilder.insertIncome("2020-02-11", "keyboard", "true", "185", "Keyboard to buy");
        connecting.addIncome(insert);
        connecting.close();
    }
}
