package com.sacane.manager.gui;

import javax.swing.*;
import java.awt.*;

public class FrameTool extends JFrame {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 880;

    public FrameTool(){
        super();
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        var frame = new FrameTool();
        frame.setVisible(true);
    }
}
