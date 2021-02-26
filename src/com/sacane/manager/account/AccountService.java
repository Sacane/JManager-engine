package com.sacane.manager.account;

import com.sacane.manager.database.DbHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountService {

    private static AccountService instance;
    private List<AccountManager> account;
    private final DbHandler dbHandler = new DbHandler();



    private void loadByDb(){
        if(account != null){
            return;
        }
        account = new ArrayList<>();

        dbHandler.connection();

        try {
            var set = dbHandler.getSetAccount();
            while(set.next()){
                account.add(new AccountManager(set.getString("name_account"), set.getDouble("amount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbHandler.close();
    }

    public synchronized List<AccountManager> findLastAccount() {
        loadByDb();
        return account;
    }

    public static synchronized AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }
}