package com.sacane.manager.data.income;

import com.sacane.manager.wrapper.ModelWrapper;

import javax.swing.*;

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
    }


    void updateSold(){
        controller.updateValue();
    }

    public static void launchIncome(ModelWrapper wrapper){
        var launcher = new IncomeVue(wrapper);
        launcher.updateSold();
        launcher.setVisible(true);

    }

}
