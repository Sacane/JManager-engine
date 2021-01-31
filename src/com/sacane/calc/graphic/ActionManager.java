package com.sacane.calc.graphic;
import com.sacane.calc.evaluator.Evaluator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;
import java.util.Stack;


public class ActionManager implements ActionListener {

    private final JButton equalButton = new JButton("Calculate");
    private final JLabel label = new JLabel("Enter a math expression : ");
    private final JTextField textField = new JTextField();
    private JLabel finalLabel = new JLabel();
    private StringBuilder history = new StringBuilder();
    private final Stack<JLabel> historyStack = new Stack<>();
    private final JButton clearHistory = new JButton("clear history");
    private StringBuilder builder = new StringBuilder();

    JPanel contentPanel(){
        var panel = new JPanel();
        panel.add(label);
        textField.setColumns(20);
        panel.add(textField);
        equalButton.addActionListener(this);
        clearHistory.addActionListener(this);
        panel.add(equalButton);
        clearHistory.setVisible(false);
        panel.add(clearHistory);
        panel.add(finalLabel);

        return panel;
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
            finalLabel.setText(history.toString());

        }


        if(src == clearHistory){
            clearBuilder(builder);
            clearBuilder(history);
            finalLabel.setText("");
            clearHistory.setVisible(false);
        }
    }
}
