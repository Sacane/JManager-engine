package com.sacane.manager.main;

import com.sacane.manager.testConnection;

public class Main {
    
    public static void main(String[] args) {
        if(!testConnection.connectionSuccess()){
            System.out.println("Error, cannot connect to the database");
            System.exit(1);
        }
    }
}
