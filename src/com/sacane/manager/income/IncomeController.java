package com.sacane.manager.income;

import com.sacane.manager.gui.ModelWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncomeController implements ActionListener {

    // fields to manage the table

    private final IncomeModel model = new IncomeModel();
    private JTable table;



    //panels
    private final JPanel southPanel = new JPanel();
    private final JPanel northPanel = new JPanel();

    private final JPanel mainPanel = new JPanel();


    //design
    private final JLabel info = new JLabel("Total Sold : ");
    private final JLabel value;


    public IncomeController(){

        table = new JTable(model);
        value = new JLabel((model.getTotal()) + " €");
        northPanel.add(info, BorderLayout.CENTER);
        northPanel.add(value, BorderLayout.CENTER);
        mainPanel.add(northPanel, BorderLayout.NORTH);

    }

    JPanel getPane(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
