package com.sacane.calc.graphic;

import javax.swing.*;
import java.awt.*;


public class WindowManager extends JFrame{


    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    public WindowManager(){
        super();
        build();
    }



    private JPanel contentPanel(){
        var panel = new JPanel();
        panel.setLayout(new FlowLayout());
        setBackground(Color.WHITE);
        JLabel label = new JLabel("Let's do maths !");
        panel.add(label);

        return panel;
    }



    private void build(){
        var action = new ActionManager();
        setTitle("Calculator");
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(action.initButton());
    }
}
