package com.sacane.manager.account;

public class AccountManager {

    private final String name;
    private final double value;

    public AccountManager(String name, double value){
        this.name = name;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
