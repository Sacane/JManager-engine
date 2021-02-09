package com.sacane.manager.gui;

import javax.swing.*;
import java.awt.*;

public class CalendarDesign extends JFrame {

    public CalendarDesign(){

        super();
        build();
        setTitle("JManager");
    }

    CalendarDesign getFrame(){
        return this;
    }

    private void build(){
        var calendar = new Calendar();
        var mainPanel = new JPanel();
        setSize(FrameTool.WIDTH, FrameTool.HEIGHT);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = calendar.getCalendarPanel();
        setContentPane(mainPanel);
        setJMenuBar(calendar.getMenu());
    }

    public static void main(String[] args) {
        var calendar = new CalendarDesign();
        calendar.setVisible(true);
    }

}
