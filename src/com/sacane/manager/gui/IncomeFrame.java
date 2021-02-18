package com.sacane.manager.gui;

import com.sacane.manager.database.DataBuild;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class IncomeFrame extends JFrame {

    public static final int WIDTH = 1080;
    public static final int HEIGHT = 800;
    private final DataBuild builder;

    public IncomeFrame(DataBuild builder){
        super();
        Objects.requireNonNull(builder);
        this.builder = builder;
        build();
    }

    void build(){
        setTitle("Income");
        ArrayList<String> titles = new ArrayList<>();
        titles.add("date");
        titles.add("label");
        titles.add("value");
        titles.add("description");

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
            add(panel);
            setSize(WIDTH, HEIGHT);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
            System.exit(1);
        }
    }

    public void closeDb(){
        builder.close();
    }

    public static void main(String[] args) {
        var builder = new DataBuild();
        builder.connection();
        var incomeWindow = new IncomeFrame(builder);
        builder.close();
    }
}
