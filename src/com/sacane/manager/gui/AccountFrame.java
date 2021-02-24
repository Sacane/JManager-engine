package com.sacane.manager.gui;
import com.sacane.manager.database.DbHandler;
import com.sacane.manager.database.QueryBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;


public class AccountFrame extends JFrame {

    private final DbHandler builder;

    private final JPanel mainPanel = new JPanel();
    private final JPanel tablePanel = new JPanel();

    public AccountFrame(DbHandler builder){
        this.builder = builder;
        build();
    }


    void build(){
        var account = new AccountListener(builder, this);
        setTitle("Account Window");

        mainPanel.setLayout(new BorderLayout());
        builder.connection();
        setLayout(new BorderLayout());
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainPanel.add(account.getMainPanel(), BorderLayout.CENTER);

        setContentPane(mainPanel);
        setVisible(true);
        account.buildTable();
        builder.close();
    }

    public static void main(String[] args) {
        var builder = new DbHandler();
        SwingUtilities.invokeLater(() -> {

            var test = new AccountFrame(builder);

        });

    }
}
