package com.sacane.calc.gui;

import javax.swing.*;
import java.awt.*;

public class WindowManager extends JFrame {

    public static final int WIDTH = 750;
    public static final int HEIGHT = 550;

    public WindowManager(){
        super();
        setLayout(new BorderLayout());
        build();
    }

    private void build(){
        var config = new PanelConfig();
        var panel = new JPanel();
        setTitle("Calculator");
        panel = config.setContentPanel();
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(true);
        setContentPane(panel);
    }
}
