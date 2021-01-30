package com.sacane.calc.graphic;
import com.sacane.calc.evaluator.BaseOperator;
import com.sacane.calc.evaluator.Evaluator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class ActionManager implements ActionListener {

    public final static int NUMBER = 10;

    private final HashSet<JButton> numberButton = new HashSet<>();
    private final HashSet<JButton> operatorButton = new HashSet<>();
    private final JButton equalButton = new JButton("=");
    private final JButton leftParenthesis = new JButton("(");
    private final JButton rightParenthesis = new JButton(")");

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
        leftParenthesis.addActionListener(this);
        rightParenthesis.addActionListener(this);
        equalButton.addActionListener(this);
        panel.add(equalButton);
        panel.add(leftParenthesis);
        panel.add(rightParenthesis);
        return panel;
    }

    void clearBuilder(){
        builder.delete(0, builder.length());
    }

    void display_result(String input){
        var evaluated = Evaluator.eval(input);
        System.out.printf("evaluating : %s%n", builder.toString());
        if ((evaluated == Math.floor(evaluated)) && !Double.isInfinite(evaluated)) {

            System.out.println("results : " + (int)evaluated);
        }
        else{
            System.out.println("results :" + Evaluator.eval(input));
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var src = e.getSource();
        var button = (JButton)src;
        if(numberButton.contains(src)){

            builder.append(button.getText());
        }
        else if(operatorButton.contains(src)){

            builder.append(button.getText());
        }
        else if(leftParenthesis == src) {
            builder.append(leftParenthesis.getText());

        } else if(rightParenthesis == src){
            builder.append(rightParenthesis.getText());
        }
        else if(src == equalButton){

            display_result(builder.toString());
            clearBuilder();
        }

    }
}
