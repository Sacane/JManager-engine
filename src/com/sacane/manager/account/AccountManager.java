package com.sacane.manager.account;

/**
 * Data class use to manage the data of the accounts.
 * An account is represented by its name and its value : the sold of this account.
 */
class AccountManager {

    private final String name;
    private final double value;

    public AccountManager(String name, double value){
        this.name = name;
        this.value = value;
    }

    /**
     *
     * @return double : value of the sold.
     */
    public double getValue() {
        return value;
    }

    /**
     * @return String : name of the account
     */
    public String getName() {
        return name;
    }

}
