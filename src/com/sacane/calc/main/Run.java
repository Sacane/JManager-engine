package com.sacane.calc.main;

import com.sacane.calc.gui.WindowManager;

import javax.swing.*;

public class Run {

    public static void runCalculator(){
        SwingUtilities.invokeLater(() -> {
            var window = new WindowManager();
            window.setVisible(true);
        });
    }
}
