package com.sacane.calc.graphic;

import javax.swing.*;
import java.awt.*;


public class WindowManager extends JFrame{


    public static final int WIDTH = 500;
    public static final int HEIGHT = 350;

    public WindowManager(){
        super();
        build();
    }



    private JPanel contentPanel(){
        var panel = new JPanel();
        var panel2 = new JPanel();


        //panel management
        panel.setLayout(new FlowLayout());
        setBackground(Color.WHITE);
        JLabel label = new JLabel("Let's do maths !");
        panel.add(label);


        //panel2 management


        return panel;
    }



    private void build(){
        var panel = new JPanel();

        var action = new ActionManager();
        panel = action.contentPanel();
        setTitle("Calculator");
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
    }
}
