package com.sacane.calc.gui;

import com.notkamui.keval.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActionManager implements ActionListener {
    public ActionManager() {
        setButtonNumber();
        setButtonOp();
    }

    //buttons
    private final ArrayList<JButton> buttonNumber = new ArrayList<>();

    private final HashMap<JButton, String> buttonOpe = new HashMap<>();

    // Functional Button
    private final JButton ac = new JButton("AC");
    private final JButton clear = new JButton("clear");
    private final JButton calculate = new JButton("=");
    //label
    private final JLabel history = new JLabel();

    //TextField
    private final JTextField input = new JTextField();

    //StringBuilders to build the label
    private final StringBuilder lines = new StringBuilder();
    private final StringBuilder inputBuild = new StringBuilder();
    private final StringBuilder historyBuild = new StringBuilder();

    ArrayList<JButton> getButtonNumber() {
        return buttonNumber;
    }

    Map<JButton, String > getButtonOp() {
        return buttonOpe;
    }

    void setButtonNumber() {
        for(int i = 0; i < 10; i++){
            var buttonAdded = new JButton(String.valueOf(i));
            buttonAdded.addActionListener(this);
            buttonNumber.add(buttonAdded);
        }
    }

    JButton getAc() {
        return ac;
    }

    JButton getClear() {
        return clear;
    }

    JButton getCalculate() {
        return calculate;
    }

    //TODO : BUILD ENUM
    void setButtonOp(){
        ac.addActionListener(this);
        clear.addActionListener(this);
        calculate.addActionListener(this);
        var add = new JButton("+");
        var sub = new JButton("-");
        var div = new JButton("/");
        var mod = new JButton("%");
        var mul = new JButton("*");
        var exp = new JButton("^");
        var lp = new JButton("(");
        var rp = new JButton(")");
        add.addActionListener(this);
        sub.addActionListener(this);
        div.addActionListener(this);
        mod.addActionListener(this);
        mul.addActionListener(this);
        exp.addActionListener(this);
        lp.addActionListener(this);
        rp.addActionListener(this);
        buttonOpe.put(add, add.getText());
        buttonOpe.put(sub, sub.getText());
        buttonOpe.put(div, div.getText());
        buttonOpe.put(mod, mod.getText());
        buttonOpe.put(mul, mul.getText());
        buttonOpe.put(exp, exp.getText());
        buttonOpe.put(lp, lp.getText());
        buttonOpe.put(rp, rp.getText());
    }

    void clearBuilder(StringBuilder toDelete){
        toDelete.delete(0, toDelete.length());
    }

    JLabel getHistory() {
        return history;
    }

    JTextField getInput() {
        return input;
    }

    private String getResult(String input){
        var kvl = new Keval();

        try {
            var evaluated = 0D;
            evaluated = kvl.eval(input);
            if ((evaluated == Math.floor(evaluated)) && !Double.isInfinite(evaluated)) {
                return input + " = " + ((int)evaluated) + "<br/>";
            }
            else{
                return input + " = " + evaluated + "<br/>";
            }
        } catch (KevalDSLException | KevalInvalidExpressionException e) {
            return "Invalid expression<br/>";
        }catch(KevalZeroDivisionException kze){
            return "Expression division by zero<br/>";
        }
    }


    private void updateHistory(String input){
        clearBuilder(lines);
        lines.append(beginOrEnd(true)).append(input).append(beginOrEnd(false));
    }

    private String beginOrEnd(boolean isBegin){
        return (isBegin) ? "<html><body>" : "</body></html>";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var src = e.getSource();
        var button = (JButton)src;

        if(buttonOpe.containsKey(button)){
            inputBuild.append(buttonOpe.get(button));
            input.setText(inputBuild.toString());
        }
        for(var buttons : buttonNumber){
            if(src == buttons){
                inputBuild.append(buttons.getText());
                input.setText(inputBuild.toString());
            }
        }
        if(src == ac){
            clearBuilder(inputBuild);
            input.setText("");
        }
        if(src == calculate){
            historyBuild.append(getResult(input.getText()));
            updateHistory(historyBuild.toString());
            history.setText(lines.toString());
        }
    }
}