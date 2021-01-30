package com.sacane.calc.main;

import com.sacane.calc.graphic.WindowManager;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var window = new WindowManager();
            window.setVisible(true);
        });
    }
}
