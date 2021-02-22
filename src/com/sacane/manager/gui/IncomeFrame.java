package com.sacane.manager.gui;

import com.sacane.manager.Month;
import com.sacane.manager.database.DataBuild;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class IncomeFrame extends JFrame {

    public static final int WIDTH = 1080;
    public static final int HEIGHT = 800;

    private final DataBuild builder;



    //Management of the incomes
    private final JPanel southPanel = new JPanel();




    public IncomeFrame(DataBuild builder){
        super();
        setLayout(new FlowLayout());
        Objects.requireNonNull(builder);
        this.builder = builder;
        build();

    }



    private void buildSouthPanel(){

    }



    private void build(){
        var listener = new TableListener(builder);
        setTitle("Income");
        ArrayList<String> titles = new ArrayList<>();
        titles.add("date");
        titles.add("label");
        titles.add("value");
        titles.add("Sold");

        var initializer = new TableInitializer(titles);

        try {
            var model = new DefaultTableModel(initializer.tableIncome(builder),
                    initializer.buildTitles());
            var table = new JTable(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);
            JScrollPane pane = new JScrollPane(table);
            JPanel panel = new JPanel();
            panel.add(pane);
            add(panel, BorderLayout.CENTER);
            add(listener.contentPanel(), BorderLayout.SOUTH);
            setSize(WIDTH, HEIGHT);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
            System.exit(1);
        }
    }


    public static void main(String[] args) {
        var builder = new DataBuild();
        builder.connection();
        var incomeWindow = new IncomeFrame(builder);
        incomeWindow.setVisible(true);
        builder.close();
    }
}
