package com.sacane.manager.income;

import com.sacane.manager.Month;
import com.sacane.manager.gui.Calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncomeVue extends JFrame implements ActionListener {


    // fields to manage the table
    private final JTable table;
    private final IncomeModel model = new IncomeModel();


    //panels
    private final JPanel southPanel = new JPanel();
    private final JPanel northPanel = new JPanel();


    //infos
    private int currentYear;
    private Month currentMonth;
    private double totalSold;

    private final static Calendar myCalendar = new Calendar();


    //design
    private final JLabel info = new JLabel("Total Sold : ");
    private final JLabel value;



    public IncomeVue(){
        updateTotal();
        table = new JTable(model);
        value = new JLabel(String.valueOf(totalSold) + " €");

        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        northPanel.add(info, BorderLayout.CENTER);
        northPanel.add(value, BorderLayout.CENTER);
        getContentPane().add(northPanel, BorderLayout.NORTH);
    }

    void updateTotal(){
        totalSold = model.getTotal();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO : do the function
    }

    public static void main(String[] args) {
        var test = new IncomeVue();
        test.setVisible(true);
    }
}
