package com.sacane.manager.gui;


import com.sacane.calc.main.Run;
import com.sacane.manager.Month;
import com.sacane.manager.account.AccountVue;
import com.sacane.manager.income.IncomeVue;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalendarController implements ActionListener {

    private final JMenuBar menu = new JMenuBar();


    //Models
    private final ModelWrapper wrapper;


    //dataStructures
    private final ArrayList<JButton> yearButton = new ArrayList<>();
    private final ArrayList<JButton> monthButton = new ArrayList<>();



    final JPanel calendar = new JPanel();
    final JPanel yearPanel = new JPanel();
    final JPanel monthPanel = new JPanel();

    final JButton addYear = new JButton("add");

    private int maxYear;


    private final JButton calculator = new JButton("Calc");
    private final JButton accountButton = new JButton("Check your accounts");
    final JPanel centerPanel = new JPanel();
    final JPanel leftPanel = new JPanel();
    final JPanel rightPanel = new JPanel();
    final JPanel northPanel = new JPanel();

    public CalendarController(ModelWrapper wrapper){

        this.wrapper = wrapper;

        //Set all the panel in the main panel
        centerPanel.setLayout(new FlowLayout());
        leftPanel.setLayout(new FlowLayout());
        rightPanel.setLayout(new BorderLayout());
        northPanel.setLayout(new BorderLayout());

        calendar.setLayout(new BorderLayout());
        yearPanel.setLayout(new GridLayout(15, 3));
        monthPanel.setLayout(new GridLayout(3, 4));

        buildPanel();
        maxYear = 2021;
    }

    JPanel getCalendarPanel(){
        return calendar;
    }



    public JMenuBar getMenu() {
        return menu;
    }

    /**
     * build the mainCalendar panel
     */
    private void buildPanel(){

        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu View = new JMenu("View");

        menu.add(file);
        menu.add(edit);
        menu.add(View);
        setButton();
        leftPanel.add(yearPanel, FlowLayout.LEFT);
        rightPanel.add(calculator, BorderLayout.SOUTH);
        centerPanel.add(accountButton, BorderLayout.SOUTH);
        calendar.add(rightPanel, BorderLayout.EAST);
        calendar.add(centerPanel, BorderLayout.CENTER);
        calendar.add(menu, BorderLayout.NORTH);
        calendar.add(leftPanel, BorderLayout.WEST);

    }

    /**
     * add a new button designed by a year in the JPanel man.
     * @param year to add in the JPanel.
     */
    void createButton(int year){
        var newButton = new JButton(String.valueOf(year));
        newButton.addActionListener(this);
        yearButton.add(newButton);
    }

    void updateButton(){
        for(var button : yearButton){
            yearPanel.add(button);
        }
    }

    /**
     * set all the buttons in the main panel of the calendar
     */
    void setButton(){
        for(var month : Month.values()){
            var buttonMonth = new JButton(month.getTypeText());
            buttonMonth.addActionListener(this);
            monthButton.add(buttonMonth);
            monthPanel.add(buttonMonth);
        }
        centerPanel.add(monthPanel, BorderLayout.CENTER);
        addYear.addActionListener(this);
        calculator.addActionListener(this);
        accountButton.addActionListener(this);
        yearPanel.add(addYear);
        createButton(2020);
        createButton(2021);
        updateButton();
        monthPanel.setVisible(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        var o = e.getSource();
        var button = (JButton)o;
        if(o == addYear){
            maxYear++;
            createButton(maxYear);
            updateButton();
            yearPanel.validate();
            yearPanel.repaint();
        }
        if(yearButton.contains(button)){
            wrapper.setCurrentYear(Integer.parseInt(button.getText()));
            monthPanel.setVisible(true);
        }
        if(monthButton.contains(button)){
            wrapper.setCurrentMonth(Month.get(button.getText()));
            System.out.println("current month : " + Month.get(button.getText()));
            IncomeVue.launchIncome(wrapper);
        }
        if(o == calculator){
            Run.runCalculator();
        }
        if(o == accountButton){
            AccountVue.runAccountPanel();
        }
    }
}
