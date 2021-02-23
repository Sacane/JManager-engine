package com.sacane.manager.gui;

import com.sacane.manager.database.DataBuild;
import com.sacane.manager.database.QueryBuilder;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AccountListener implements ActionListener {

    private final JLabel name = new JLabel("name");
    private final JLabel value = new JLabel("value");

    private final JTextField nameAccount = new JTextField();
    private final JTextField valueAccount = new JTextField();
    private final JButton addAccount = new JButton("Add a new Account");

    private final JLabel nameDelete = new JLabel("name to delete");
    private final JTextField insertNameDelete = new JTextField();
    private final JButton deleteAccount = new JButton("delete");
    private DataBuild builder;

    private final JPanel mainPanel = new JPanel();

    public AccountListener(DataBuild builder){
        this.builder = builder;
        mainPanel.setLayout(new GridLayout(4, 10));
    }

    public JPanel contentPanel(){

        JPanel mainPanel = new JPanel();


        mainPanel.add(name);
        nameAccount.setColumns(10);
        mainPanel.add(nameAccount);

        mainPanel.add(value);
        valueAccount.setColumns(10);
        mainPanel.add(valueAccount);

        addAccount.addActionListener(this);
        mainPanel.add(addAccount);

        mainPanel.add(nameDelete);
        insertNameDelete.setColumns(10);
        mainPanel.add(insertNameDelete);

        deleteAccount.addActionListener(this);
        mainPanel.add(deleteAccount);



        return mainPanel;
    }

    public JPanel getMainPanel() {
        try {
            contentTablePanel();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return mainPanel;
    }

    public void contentTablePanel() throws SQLException {
        builder.connection();



        var array = builder.getSetByRequest("SELECT name_account, amount FROM account");
        while(array.next()){
            mainPanel.add(new JLabel(array.getString("name_account")));
            mainPanel.add(new JLabel(String.valueOf(array.getDouble("amount"))));
        }
        mainPanel.validate();
        mainPanel.repaint();
        builder.close();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        var o = e.getSource();
        builder.connection();

        if(o == addAccount){
            try {

                if(!nameAccount.getText().equals("") && !valueAccount.getText().equals("")) {
                    builder.executeRequest(QueryBuilder.addAccount(nameAccount.getText(), Double.parseDouble(valueAccount.getText())));
                }
                contentTablePanel();
                mainPanel.validate();
                mainPanel.repaint();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.exit(1);
            }

        }
        if(o == deleteAccount){
            try {
                var statement = builder.getNewStatement();
                if(!insertNameDelete.getText().equals("")) {
                    statement.executeUpdate("DELETE FROM account WHERE name_account =" + "'" + insertNameDelete.getText() +"'");
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.exit(1);
            }
        }
        builder.close();
    }
}
