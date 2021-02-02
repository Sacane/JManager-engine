package com.sacane.calc.graphic;
import com.sacane.calc.evaluator.BaseOperator;
import com.sacane.calc.evaluator.Evaluator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;
import java.util.HashSet;

public class ActionManager implements ActionListener {

    public static final int NUMBER = 10;

    
    private JButton[] buttonNumbers = new JButton[NUMBER];
    private final HashSet<JButton> numericButton = new HashSet<>();
    private final HashSet<JButton> operatorButton = new HashSet<>();
    private final HashSet<JButton> otherButton = new HashSet<>();
    private final JButton equalButton = new JButton("Calculate");
    private final JLabel label = new JLabel("Enter a math expression : ");
    private final JTextField textField = new JTextField();
    private final JLabel labelHistory = new JLabel();
    private final StringBuilder history = new StringBuilder();
    private final JButton clearHistory = new JButton("clear");
    private final StringBuilder builder = new StringBuilder();
    private final JPanel buttonPanel = new JPanel(new BorderLayout());
    private final JButton ac = new JButton("AC");
    private final StringBuilder saveTextDisplay = new StringBuilder();
    private final JPanel panelOperator = new JPanel(new BorderLayout());


    JPanel contentPanel(){
        var panel = new JPanel();
        panel.add(label);
        textField.setColumns(30);
        panel.add(textField);
        equalButton.addActionListener(this);
        clearHistory.addActionListener(this);
        panel.add(equalButton);
        clearHistory.setVisible(false);
        buttonPanel.setLayout(new GridLayout(5, 5, 3, 3));
        panelOperator.setLayout(new GridLayout(6, 1));
        panelOperator.add(clearHistory);
        panelOperator.add(labelHistory);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(panelOperator, BorderLayout.SOUTH);

        //Button management

        ac.addActionListener(this);
        setButtonAll();

        return panel;
    }

    private void setNumericButton(){
        for(int i = 0; i < NUMBER; i++){
            var button = new JButton(String.valueOf(i));
            button.addActionListener(this);
            buttonNumbers[i] = button;
            numericButton.add(button);
        }
    }

    private void setOperatorButton(){
        for(var symbols : BaseOperator.values()){
            var button = new JButton(symbols.getSymbol());
            button.addActionListener(this);
            operatorButton.add(button);
            buttonPanel.add(button);
        }
    }

    private void setButtonAll(){

        var leftP = new JButton("(");
        var rightP = new JButton(")");

        numericButton.add(leftP);
        numericButton.add(rightP);
        buttonPanel.add(leftP);
        buttonPanel.add(rightP);

        leftP.addActionListener(this);
        rightP.addActionListener(this);
        setNumericButton();
        setOperatorButton();



        for(int i = 0; i < NUMBER; i++){
            buttonPanel.add(buttonNumbers[i]);
        }
        buttonPanel.add(ac);

    }

    void displayTextButton(Object src, JTextField text){
        var buttonSrc = (JButton)src;
        if(numericButton.contains(buttonSrc) || operatorButton.contains(buttonSrc)){
            saveTextDisplay.append(buttonSrc.getText());
            text.setText(saveTextDisplay.toString());
        }
        if(src.equals(ac)){
            clearBuilder(saveTextDisplay);
            text.setText("");
        }

    }

    void clearBuilder(StringBuilder toDelete){
        toDelete.delete(0, toDelete.length());
    }

    String getResult(String input){
        try {
            var evaluated = Evaluator.eval(input);
            if ((evaluated == Math.floor(evaluated)) && !Double.isInfinite(evaluated)) {
               return input + " = " + ((int)evaluated) + "<br/>";
            }
            else{
                return input + " = " + evaluated;
            }
        }catch (IllegalStateException | EmptyStackException e){
            return "Illegal expression" + "<br/>";
        }
    }


    void updateHistory(String input){
        clearBuilder(history);
        history.append(beginOrEnd(true)).append(input).append(beginOrEnd(false));
    }

    String beginOrEnd(boolean isBegin){
        return (isBegin) ? "<html><body>" : "</body></html>";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var src = e.getSource();

        if(src == equalButton){
            builder.append(getResult(textField.getText()));
            clearHistory.setVisible(true);
            updateHistory(builder.toString());
            labelHistory.setText(history.toString());
        }
        if(src == clearHistory){
            clearBuilder(builder);
            clearBuilder(history);
            labelHistory.setText("");
            clearHistory.setVisible(false);
        }
        displayTextButton(src, textField);
    }
}
