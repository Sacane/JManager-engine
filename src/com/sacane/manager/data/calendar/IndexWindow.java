package com.sacane.manager.data.calendar;
import com.sacane.manager.wrapper.ModelWrapper;

import javax.swing.*;

public class IndexWindow extends JFrame {

    public static final int WIDTH = 650;
    public static final int HEIGHT = 350;
    public IndexWindow(){
        build();
        setTitle("JManager");
        setResizable(false);
    }

    private void build(){
        var model = new ModelWrapper();
        var calendar = new CalendarController(model);
        var mainPanel = new JPanel();
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = calendar.getCalendarPanel();
        setContentPane(mainPanel);
        setJMenuBar(calendar.getMenu());
    }
}
