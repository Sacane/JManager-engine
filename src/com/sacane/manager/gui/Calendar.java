package com.sacane.manager.gui;

import com.sacane.manager.Month;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Calendar implements ActionListener {

    private final JMenuBar menu = new JMenuBar();
    private final ArrayList<JButton> yearButton = new ArrayList<>();
    private final JPanel calendar = new JPanel();
    private final JPanel yearPanel = new JPanel();
    private final JPanel monthPanel = new JPanel();
    private final JButton addYear = new JButton("add");
    private int maxYear;



    public Calendar(){

        calendar.setLayout(new BorderLayout());
        yearPanel.setLayout(new GridLayout(7, 2));
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
        calendar.add(menu, BorderLayout.NORTH);
        calendar.add(yearPanel, BorderLayout.WEST);
        calendar.add(monthPanel, BorderLayout.CENTER);
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
            monthPanel.add(buttonMonth);
        }
        addYear.addActionListener(this);
        yearPanel.add(addYear);
        createButton(2020);
        createButton(2021);
        updateButton();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        var o = e.getSource();
        if(o == addYear){
            System.out.println("typed");
            maxYear +=1;
            createButton(maxYear);
            updateButton();
            yearPanel.validate();
            yearPanel.repaint();
        }
    }
}
