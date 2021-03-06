package com.sacane.manager.income;

import com.sacane.manager.Month;
import com.sacane.manager.database.DbHandler;
import com.sacane.manager.database.QueryBuilder;
import com.sacane.manager.gui.ModelWrapper;
import com.sacane.manager.gui.TableInitializer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class IncomeController implements ActionListener {

    // fields to manage the table

    private final IncomeModel model;
    private final JTable table;

    //panels
    final JPanel southPanel = new JPanel();
    final JPanel northPanel = new JPanel();
    final JPanel bottomPanel = new JPanel();
    final JPanel mainPanel = new JPanel();
    ModelWrapper wrapper;

    //design
    final JLabel info = new JLabel("Total Sold : ");
    final JLabel value;



    //label to add
    final JLabel label = new JLabel("Label :");
    final JTextField labelName = new JTextField();
    final JLabel cost = new JLabel("price");
    final JTextField putCost = new JTextField("");
    final JComboBox day;
    final JComboBox account;

    final JButton addButton = new JButton("add Income");

    final JTextField deleteName = new JTextField();
    final JButton deleteButton = new JButton("delete Income");
    //db access
    private final DbHandler handler = new DbHandler();

    IncomeController(ModelWrapper wrapper){
        this.wrapper = wrapper;
        mainPanel.setLayout(new BorderLayout());
        model = new IncomeModel(wrapper);
        table = new JTable(model);
        value = new JLabel((model.getTotal()) + " €");

        day = new JComboBox(TableInitializer.buildDayCheckBox(wrapper.getCurrentMonth().getNumberDay()));
        account = new JComboBox(TableInitializer.buildAccountCheckBox());
        northPanel.add(info, BorderLayout.CENTER);
        northPanel.add(value, BorderLayout.CENTER);
        southPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        bottomPanel.add(label);
        labelName.setColumns(10);
        bottomPanel.add(labelName);
        bottomPanel.add(cost);
        putCost.setColumns(10);
        bottomPanel.add(putCost);
        bottomPanel.add(day);

        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        bottomPanel.add(addButton);
        deleteName.setColumns(10);
        bottomPanel.add(account);
        bottomPanel.add(deleteName);
        bottomPanel.add(deleteButton);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

    }

    JPanel getPane(){
        return mainPanel;
    }

    private boolean isDouble(String number){
        try{
            Double.parseDouble(number);
            return true;
        }catch(NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var o = e.getSource();
        if(o == addButton){
            if(!putCost.getText().equals("") && isDouble(putCost.getText()) && !labelName.getText().equals("")) {
                var soldTyped = Double.parseDouble(putCost.getText());
                try {
                    handler.connection();
                    System.out.println(Month.formattedDate(Integer.parseInt(String.valueOf(day.getSelectedItem())),
                            wrapper.getCurrentMonth(),
                            wrapper.getCurrentYear()));
                    handler.addDbIncome(soldTyped > 0,
                            labelName.getText(),
                            soldTyped,
                            "",
                            Month.formattedDate(Integer.parseInt(String.valueOf(day.getSelectedItem())),
                                    wrapper.getCurrentMonth(),
                                    wrapper.getCurrentYear())

                    );
                    model.actualiseModel();
                    handler.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
