package com.sacane.manager;

import java.util.Objects;

class QueryBuilder {

    private static String syntaxBuild(String toInsert, boolean isFinally){
        return(isFinally) ? "'" + toInsert + "'" : "'" + toInsert + "', ";
    }

    public static String insertIncome(String date, String label, String is_income, String value, String description){
        Objects.requireNonNull(date);
        Objects.requireNonNull(label);
        Objects.requireNonNull(is_income);
        Objects.requireNonNull(value);

        return "INSERT INTO INCOME VALUES (null," + syntaxBuild(date, false) + syntaxBuild(label, false) +
                syntaxBuild(is_income, false) + syntaxBuild(value, false) + syntaxBuild(description, true) +
                ")";
    }

    public static String insertIncome(String date, String label, String is_income, String value){
        Objects.requireNonNull(date);
        Objects.requireNonNull(label);
        Objects.requireNonNull(is_income);
        Objects.requireNonNull(value);

        return "INSERT INTO INCOME VALUES (null," + syntaxBuild(date, false) + syntaxBuild(label, false) +
                syntaxBuild(is_income, false) + syntaxBuild(value, true) + "null" +
                ")";
    }

    public static String insertProject(String name, int day, boolean is_income, double value, int duration){
        Objects.requireNonNull(name);

        return "INSERT INTO INCOME VALUES (null," + syntaxBuild(name, false) + syntaxBuild(String.valueOf(day), false) +
                syntaxBuild(String.valueOf(is_income), false) + syntaxBuild(String.valueOf(value), false) + syntaxBuild(String.valueOf(duration), true) +
                ")";
    }

    public static String insertPromises(String name, double value, boolean is_debt, String name_owner, String description){
        Objects.requireNonNull(name);
        Objects.requireNonNull(name_owner);
        return "INSERT INTO INCOME VALUES (null," + syntaxBuild(name, false) + syntaxBuild(String.valueOf(value), false) +
                syntaxBuild(String.valueOf(is_debt), false) + syntaxBuild(name_owner, false) + syntaxBuild(description, true) +
                ")";
    }

    public static String deleteIncome(String label){
        return "DELETE FROM income WHERE label ='" + label + "'";
    }

    public static String deleteProject(String name_project){
        return "DELETE FROM project WHERE name_project = '" + name_project + "'";
    }

    public static String deletePromises(String name){
        Objects.requireNonNull(name);
        return "DELETE FROM promises WHERE name ='" + name + "'";
    }
}
