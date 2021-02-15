package com.sacane.manager.gui;

import javax.swing.*;
import java.awt.*;

public class FrameTool extends JFrame {
    public static final int WIDTH = 650;
    public static final int HEIGHT = 350;
    public static final int WIDTH_DB = 900;
    public static final int HEIGHT_DB = 700;

    public FrameTool(){
        super();
        setLayout(new BorderLayout());
        setSize(WIDTH_DB, HEIGHT_DB);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
