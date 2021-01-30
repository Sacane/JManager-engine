package com.sacane.calc.graphic;

import javax.swing.*;

public class TestRenderer {

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            var window = new WindowManager();
            window.setVisible(true);
        });
    }

}
