package com.sacane.manager.income;

import com.sacane.manager.gui.ModelWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncomeController implements ActionListener {

    // fields to manage the table

    private IncomeModel model;
    private JTable table;



    //panels
    private final JPanel southPanel = new JPanel();
    private final JPanel northPanel = new JPanel();

    private final JPanel mainPanel = new JPanel();
    private ModelWrapper wrapper;

    //design
    private final JLabel info = new JLabel("Total Sold : ");
    private final JLabel value;


    public IncomeController(ModelWrapper wrapper){
        this.wrapper = wrapper;
        model = new IncomeModel(wrapper);
        table = new JTable(model);
        value = new JLabel((model.getTotal()) + " €");
        northPanel.add(info, BorderLayout.CENTER);
        northPanel.add(value, BorderLayout.CENTER);
        southPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.CENTER);

    }

    JPanel getPane(){
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
