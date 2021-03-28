package com.sacane.manager.database;
import com.sacane.manager.Month;

import java.util.Objects;


public class QueryBuilder {

    private static String syntaxBuild(String toInsert, boolean isEnd){
        return(isEnd) ? "'" + toInsert + "'" : "'" + toInsert + "', ";
    }

    static String getIdTransaction(String label){
        return "SELECT id_trans FROM trans WHERE label=" + "'" + label + "'";
    }

    public static String deleteAccount(String nameAccount){
        return "DELETE FROM account WHERE name_account = " + "'" + nameAccount + "'";
    }

    public static String addAccount(String name_account, double amount){
        return "INSERT INTO account VALUES(null," +
                syntaxBuild(name_account, false) +
                syntaxBuild(String.valueOf(amount), true) + ")";
    }
    static String deleteTrans(String label){
        return "DELETE FROM trans WHERE label ='" + label + "'";
    }
    static String deleteTrans(int id_trans){
        return "DELETE FROM trans WHERE id_trans =" + id_trans;
    }

    public static String insertTrans(boolean is_in, String label, double value, String description){
        Objects.requireNonNull(label);
        return "INSERT INTO trans VALUES(null," + syntaxBuild(String.valueOf(is_in), false) + syntaxBuild(label, false) +
                syntaxBuild(String.valueOf(value), false) + syntaxBuild(description, true) + ")";
    }

    public static String insertIncome(int id_trans, String date){
        Objects.requireNonNull(date);

        return "INSERT INTO INCOME VALUES (" + syntaxBuild(String.valueOf(id_trans), false) + syntaxBuild(date, true) + ")";
    }


    public static String insertProject(int id_trans, int day, int duration){

        return "INSERT INTO Project VALUES ("+ syntaxBuild(String.valueOf(id_trans), false) + syntaxBuild(String.valueOf(day), false) + syntaxBuild(String.valueOf(duration), true) + ")";
    }

    public static String insertPromises(int id_trans, String name_owner){
        Objects.requireNonNull(name_owner);
        return "INSERT INTO Promise VALUES (" + syntaxBuild(String.valueOf(id_trans), false) + syntaxBuild(name_owner, true) +
                ")";
    }

    public static String insertAccount(String name, double amount){
        Objects.requireNonNull(name);
        return "INSERT INTO account VALUES(null," +
                syntaxBuild(name, false) +
                syntaxBuild(String.valueOf(amount), true) + ")";
    }

    public static String deleteIncome(int id_trans){
        return "DELETE FROM income WHERE id_income = " + "'"+ id_trans + "'";
    }

    //TODO : Project
//    public static String deleteProject(String name_project){
//        return "DELETE FROM project WHERE name_project = '" + name_project + "'";
//    }

    //TODO : promises
//    public static String deletePromises(String name){
//        Objects.requireNonNull(name);
//        return "DELETE FROM promises WHERE name ='" + name + "'";
//    }

    public static String getNumberRow(){
        return "SELECT COUNT(id_income) AS numberRow FROM income";
    }

    public static String getNumberRowAccount(){
        return "SELECT COUNT(id) AS numberRow FROM account";
    }

    public static String selectTrans(String table){
        return "SELECT date, label, value, is_in, description FROM income NATURAL JOIN trans";
    }

//    public static String selectMonthInfos(int monthRep, int year){
//
//        int nextMonthRep = (monthRep + 1) % 14;
//        var prevMonth = Month.getMonthByRep(monthRep);
//
//        var nextMonth = Month.getMonthByRep(nextMonthRep);
//        System.out.println("actual : " + prevMonth + " Next : " + nextMonth);
//        if(monthRep == 12) {
//            return "SELECT date, label, value, is_in FROM trans NATURAL JOIN income WHERE date >= " + Month.formattedDate(1, prevMonth, year)
//                    + " AND date <= " + Month.formattedDate(1, nextMonth, year+1);
//        }
//        return "SELECT date, label, value, is_in FROM trans NATURAL JOIN income WHERE date >= " + "'" + Month.formattedDate(1, prevMonth, year) + "'"
//                + " AND date <= " + "'" + Month.formattedDate(1, nextMonth, year) + "'";
//    }
//SELECT * FROM trans JOIN income i on trans.id_trans = i.transition
    public static String getIncomeMonth(int monthRep, int year){
        int nextMonthRep = (monthRep + 1) % 14;
        var prevMonth = Month.getMonthByRep(monthRep);
        var nextMonth = Month.getMonthByRep(nextMonthRep);
        System.out.println("SELECT * FROM trans INNER JOIN income ON income.id_income = transaction.id_trans WHERE date >= " + "'" + Month.formattedDate(1, prevMonth, year) + "'"
                + " AND date <= " + "'" + Month.formattedDate(1, nextMonth, year) + "'");

        if(monthRep == 12) {
            return "SELECT * FROM trans INNER JOIN income ON income.id_income = trans.id_trans WHERE date >= " + "'" + Month.formattedDate(1, prevMonth, year) + "'"
                    + " AND date <= " + "'" + Month.formattedDate(1, nextMonth, year+1) + "'";
        }
        return "SELECT * FROM trans INNER JOIN income ON income.id_income = trans.id_trans WHERE date >= " + "'" + Month.formattedDate(1, prevMonth, year) + "'"
                + " AND date <= " + "'" + Month.formattedDate(1, nextMonth, year) + "'";
    }

    public static String selectTotal(){
        return "SELECT SUM(amount) as total FROM account";
    }
    public static String selectAccount(){
        return "SELECT name_account, amount FROM account" ;
    }

    public static String getNumberAccount(){
        return "SELECT COUNT(id) as count FROM account";
    }


    public static String dbUpdateSold(double value, String name_account){
        return "UPDATE account SET amount = amount +'" + value + "' WHERE name_account = '" + name_account + "'";
    }

}
