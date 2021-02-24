package com.sacane.manager.gui;

import com.sacane.manager.database.DbHandler;
import com.sacane.manager.database.QueryBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountListener implements ActionListener {

    private final JLabel name = new JLabel("name");
    private final JLabel value = new JLabel("value");


    private final JTextField nameAccount = new JTextField();
    private final JTextField valueAccount = new JTextField();
    private final JButton addAccount = new JButton("Add a new Account");

    private JTable table = null;
    private DefaultTableModel model = null;

    private final JLabel nameDelete = new JLabel("name to delete");
    private final JTextField insertNameDelete = new JTextField();
    private final JButton deleteAccount = new JButton("delete");
    private final DbHandler builder;

    private final JFrame mainFrame;

    private final JPanel mainPanel = new JPanel();

    public AccountListener(DbHandler builder, JFrame mainFrame){
        this.builder = builder;

        this.mainFrame = mainFrame;
        contentPanel();
    }

    static double updateTotal(DbHandler builder) throws SQLException{
        var array = builder.getSetTotal();
        return array.getInt("total");
    }

    public void contentPanel(){

        mainPanel.add(name);
        nameAccount.setColumns(10);
        mainPanel.add(nameAccount);

        mainPanel.add(value);
        valueAccount.setColumns(10);
        mainPanel.add(valueAccount);

        addAccount.addActionListener(this);
        mainPanel.add(addAccount);

        mainPanel.add(nameDelete);
        insertNameDelete.setColumns(10);
        mainPanel.add(insertNameDelete);

        deleteAccount.addActionListener(this);
        mainPanel.add(deleteAccount);

    }



    public JPanel getMainPanel() {
        return mainPanel;
    }


    public void buildTable(){
        var titles = new ArrayList<String>();
        titles.add("name account");
        titles.add("amount");
        var initializer = new TableInitializer(titles);

        try{
            model = new DefaultTableModel(initializer.tableAccount(builder),
                    initializer.buildTitles());
            table = new JTable(model);

            table.setShowGrid(true);
            table.setShowVerticalLines(true);

            JScrollPane pane = new JScrollPane(table);
            JPanel panel = new JPanel();
            panel.add(pane);
            mainPanel.add(panel, BorderLayout.SOUTH);
        }catch(SQLException sqe){
            System.out.println(sqe.getMessage());
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        var o = e.getSource();

        builder.connection();

        if(o == addAccount){
            try {
                if(!nameAccount.getText().equals("") && !valueAccount.getText().equals("")) {
                    builder.executeRequest(QueryBuilder.addAccount(nameAccount.getText(), Double.parseDouble(valueAccount.getText())));
                }

                model.fireTableDataChanged();


            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.exit(1);
            }
        }
        if(o == deleteAccount){
            try {
                if(!insertNameDelete.getText().equals("")) {
                    builder.executeRequest("DELETE FROM account WHERE name_account =" + "'" + insertNameDelete.getText() +"'");
                }
                model.fireTableDataChanged();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.exit(1);
            }
        }
        builder.close();
    }
}
