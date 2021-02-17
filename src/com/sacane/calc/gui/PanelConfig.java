package com.sacane.calc.gui;

import javax.swing.*;
import java.awt.*;


public class PanelConfig {

    private final JPanel buttonPanel = new JPanel();
    private final JPanel textPanel = new JPanel();
    private final JPanel historyPanel = new JPanel();
    private final JPanel eastPanel = new JPanel();


    JPanel setContentPanel(){
        var action = new ActionManager();
        var window = new JPanel();
        window.setLayout(new BorderLayout());
        buttonPanel.setLayout(new GridLayout(7, 3));
        for(var button : action.getButtonNumber()){
            buttonPanel.add(button);
        }
        for(var value : action.getButtonOp().entrySet()){
            buttonPanel.add(value.getKey());
        }

        buttonPanel.add(action.getAc());
        buttonPanel.add(action.getClear());
        buttonPanel.add(action.getCalculate());

        var text = action.getInput();
        text.setColumns(20);
        textPanel.add(text);
        historyPanel.add(action.getHistory());
        window.add(historyPanel, BorderLayout.CENTER);
        window.add(eastPanel, BorderLayout.EAST);
        window.add(textPanel, BorderLayout.NORTH);
        window.add(buttonPanel, BorderLayout.SOUTH);

        return window;
    }

}
