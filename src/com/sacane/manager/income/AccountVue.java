package com.sacane.manager.income;

import com.sacane.manager.Month;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountVue extends JFrame implements ActionListener {

    private final JTable table;
    private final IncomeModel model = new IncomeModel();


    //panels
    private final JPanel southPanel = new JPanel();
    private final JPanel northPanel = new JPanel();

    //infos
    private int currentYear;
    private Month currentMonth;
    private double totalSold;



    //design
    private final JLabel info = new JLabel("current Sold : ");
    private final JLabel value;



    public AccountVue(){
        table = new JTable(model);
        value = new JLabel(String.valueOf(totalSold));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
