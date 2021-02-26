package com.sacane.manager.gui;

import com.sacane.manager.database.DbHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class IncomeFrame extends JFrame {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;


    private final DbHandler builder;
    private double total;

    public IncomeFrame(DbHandler builder){
        super();
        setLayout(new FlowLayout());
        Objects.requireNonNull(builder);
        this.builder = builder;
        try {
            this.total = Account.updateTotal(builder);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        build();

    }
    private void buildSouthPanel(){

    }
    private void build(){
        var listener = new IncomeListener(builder);
        setTitle("Income");
        ArrayList<String> titles = new ArrayList<>();
        titles.add("date");
        titles.add("label");
        titles.add("value");
        titles.add("Total Sold");

        var initializer = new TableInitializer(titles);

        try {
            var model = new DefaultTableModel(initializer.tableIncome(builder, total),
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
        var builder = new DbHandler();
        builder.connection();
        var incomeWindow = new IncomeFrame(builder);
        incomeWindow.setVisible(true);
        builder.close();
    }
}
