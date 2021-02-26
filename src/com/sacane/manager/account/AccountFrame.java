package com.sacane.manager.account;

import javax.swing.*;
import java.awt.*;

public class AccountFrame extends JFrame {

    private final AccountModel model;
    private final JTable table;

    public AccountFrame(){
        super();
        setTitle("Account");
//        setPreferredSize(new Dimension(600, 700));
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        model = new AccountModel();
        table = new JTable(model);
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

    }

    private void build(){

    }


}
