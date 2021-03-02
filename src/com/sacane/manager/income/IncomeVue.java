package com.sacane.manager.income;

import com.sacane.manager.Month;
import com.sacane.manager.gui.Calendar;
import com.sacane.manager.gui.ModelWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncomeVue extends JFrame implements ActionListener {



    private JPanel mainPanel;



    private final IncomeModel model = new IncomeModel();


    //panels
    private final JPanel southPanel = new JPanel();
    private final JPanel northPanel = new JPanel();

    private final IncomeController controller = new IncomeController();


    //design
    private final JLabel info = new JLabel("Total Sold : ");


    private ModelWrapper wrapper;

    public IncomeVue(){

        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        mainPanel = controller.getPane();
        setContentPane(mainPanel);
        getContentPane().add(northPanel, BorderLayout.NORTH);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        var src = e.getSource();
        //TODO : do the function
    }

    public static void main(String[] args) {
        var test = new IncomeVue();
        test.setVisible(true);
    }
}
