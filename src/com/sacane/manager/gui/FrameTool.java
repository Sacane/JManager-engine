package com.sacane.manager.gui;

import javax.swing.*;
import java.awt.*;

class FrameTool extends JFrame {

    public static final int WIDTH = 650;
    public static final int HEIGHT = 350;

    public FrameTool(){
        super();
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
