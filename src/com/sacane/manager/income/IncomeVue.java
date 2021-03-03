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

    private ModelWrapper wrapper;


    private IncomeModel model;


    //panels
    private final JPanel southPanel = new JPanel();
    private final JPanel northPanel = new JPanel();

    private IncomeController controller;


    //design
    private final JLabel info = new JLabel("Total Sold : ");


    public IncomeVue(ModelWrapper wrapper){
        this.wrapper = wrapper;
        model = new IncomeModel(wrapper);
        controller = new IncomeController(wrapper);
        setSize(500, 500);
        setLocationRelativeTo(null);
        mainPanel = controller.getPane();
        setContentPane(mainPanel);
        //getContentPane().add(northPanel, BorderLayout.NORTH);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        var src = e.getSource();
        //TODO : do the function
    }

    public static void launchIncome(ModelWrapper wrapper){
        var launcher = new IncomeVue(wrapper);
        launcher.setVisible(true);

    }


}
