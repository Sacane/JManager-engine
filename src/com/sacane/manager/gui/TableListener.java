package com.sacane.manager.gui;

import com.sacane.manager.database.DataBuild;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TableListener implements ActionListener {

    private final JTable table = new JTable();
    private final DataBuild builder;

    public TableListener(DataBuild builder){
        this.builder = builder;
    }

    private final JLabel name = new JLabel("name");
    private final JLabel price = new JLabel("price");

    private final JTextField putName = new JTextField();
    private final JTextField putPrice = new JTextField();
    private final JComboBox<String> box = new JComboBox<String>();

    private final JButton addIncome = new JButton("add Income");



    JPanel contentPanel(){
        JPanel panel = new JPanel();

        panel.add(name);
        putName.setColumns(10);
        putPrice.setColumns(10);
        panel.add(putName);
        panel.add(price);
        panel.add(putPrice);
        buildBox();

        panel.add(addIncome);
        return panel;
    }

    private void buildBox(){
        try {
            var set = builder.getSetByRequest("SELECT name FROM account");
            while(set.next()){
                box.addItem(set.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.getMessage();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
