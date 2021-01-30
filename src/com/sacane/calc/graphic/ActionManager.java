package com.sacane.calc.graphic;
import com.sacane.calc.evaluator.BaseOperator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class ActionManager implements ActionListener {

    public final static int NUMBER = 10;

    private final HashSet<JButton> numberButton = new HashSet<>();
    private final HashSet<JButton> operatorButton = new HashSet<>();

    private StringBuilder builder = new StringBuilder();


    JPanel initButton(){
        var panel = new JPanel();
        for(int i = 0; i < NUMBER; i++){
            var button = new JButton(String.valueOf(i));
            numberButton.add(button);
            button.addActionListener(this);
            panel.add(button);
        }
        for(var operator : BaseOperator.values()){
            var button = new JButton(operator.getSymbol());
            operatorButton.add(button);
            button.addActionListener(this);
            panel.add(button);
        }
        panel.add(new JButton("="));
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var src = e.getSource();
        var button = (JButton)src;
        if(numberButton.contains(src)){
            System.out.printf("Click on %s%n", button.getText());
        }
        else if(operatorButton.contains(src)){
            System.out.printf("Click on %s%n", button.getText());
        }
    }
}
