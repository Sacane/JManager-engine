package com.sacane.manager.income;

import com.sacane.manager.gui.ModelWrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncomeVue extends JFrame {



    private JPanel mainPanel;
    private IncomeController controller;



    IncomeVue(ModelWrapper wrapper){
        setTitle("Articles");
        controller = new IncomeController(wrapper);
        setSize(1024, 750);
        setLocationRelativeTo(null);
        mainPanel = controller.getPane();
        setContentPane(mainPanel);
        //getContentPane().add(northPanel, BorderLayout.NORTH);
    }




    public static void launchIncome(ModelWrapper wrapper){
        var launcher = new IncomeVue(wrapper);
        launcher.setVisible(true);

    }

}
