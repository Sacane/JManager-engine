package com.sacane.manager.gui;
import javax.swing.*;

public class IndexWindow extends JFrame {

    public IndexWindow(){
        build();
        setTitle("JManager");
        setResizable(false);
    }

    private void build(){
        var model = new ModelWrapper();
        var calendar = new CalendarController(model);
        var mainPanel = new JPanel();
        setSize(FrameTool.WIDTH, FrameTool.HEIGHT);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = calendar.getCalendarPanel();
        setContentPane(mainPanel);
        setJMenuBar(calendar.getMenu());
    }
}
