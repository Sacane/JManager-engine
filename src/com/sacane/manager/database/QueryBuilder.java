package com.sacane.manager.database;
import java.util.Objects;


public class QueryBuilder {

    private static String syntaxBuild(String toInsert, boolean isEnd){
        return(isEnd) ? "'" + toInsert + "'" : "'" + toInsert + "', ";
    }

    static String getIdTransaction(String label){
        return "SELECT id_trans FROM trans WHERE label=" + "'" + label + "'";
    }

    static String deleteTrans(String label){
        return "DELETE FROM trans WHERE label ='" + label + "'";
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

    public static String deleteProject(String name_project){
        return "DELETE FROM project WHERE name_project = '" + name_project + "'";
    }

    public static String deletePromises(String name){
        Objects.requireNonNull(name);
        return "DELETE FROM promises WHERE name ='" + name + "'";
    }

    public static String getNumberRow(){
        return "SELECT COUNT(id_income) AS numberRow FROM income";
    }

    public static String selectTrans(String table){
        return "SELECT date, label, value, description FROM " + table + " NATURAL JOIN trans";
    }
}
