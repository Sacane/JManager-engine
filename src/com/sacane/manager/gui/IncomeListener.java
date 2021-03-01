package com.sacane.manager.gui;

import com.sacane.manager.database.DbHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncomeListener implements ActionListener {

    private final DbHandler builder;

    private final JLabel name = new JLabel("name");
    private final JLabel price = new JLabel("price");

    private final JTextField putName = new JTextField();
    private final JTextField putPrice = new JTextField();
    private final JComboBox<String> box = new JComboBox<>();

    private final JButton addIncome = new JButton("add Income");

    public IncomeListener(DbHandler builder){
        this.builder = builder;
    }

    JPanel contentPanel(){
        JPanel panel = new JPanel();
        panel.add(name);
        putName.setColumns(10);
        putPrice.setColumns(10);
        panel.add(putName);
        panel.add(price);
        panel.add(putPrice);
        buildBox();
        panel.add(box);
        panel.add(addIncome);
        return panel;
    }

    private void buildBox(){
        box.addItem("Accounts");
        box.addItem("other");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
