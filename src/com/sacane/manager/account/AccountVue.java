package com.sacane.manager.account;

import com.sacane.manager.database.DbHandler;
import com.sacane.manager.database.QueryBuilder;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AccountVue extends JFrame implements ActionListener {

    private final AccountModel model = new AccountModel();
    private final JTable table;
    private final JPanel southPanel = new JPanel();

    private final JLabel name = new JLabel("name");
    private final JLabel value = new JLabel("value");
    private final JButton addAccount = new JButton("Add a new Account");
    private final JTextField nameAccount = new JTextField();
    private final JTextField valueAccount = new JTextField();
    private final JLabel nameDelete = new JLabel("name to delete");
    private final JTextField insertNameDelete = new JTextField();
    private final JButton deleteAccount = new JButton("delete");

    private JScrollPane scroll;

    public AccountVue(){
        super();
        setTitle("Account");
//        setPreferredSize(new Dimension(600, 700));
        setSize(800, 500);
        setLocationRelativeTo(null);
        table = new JTable(model);
        scroll = new JScrollPane(table);
        getContentPane().add(scroll, BorderLayout.CENTER);
        contentPanel();
        getContentPane().add(southPanel, BorderLayout.SOUTH);

    }


    public void contentPanel(){

        southPanel.add(name);
        nameAccount.setColumns(10);
        southPanel.add(nameAccount);
        southPanel.add(value);
        valueAccount.setColumns(10);
        southPanel.add(valueAccount);
        addAccount.addActionListener(this);
        southPanel.add(addAccount);
        southPanel.add(nameDelete);
        insertNameDelete.setColumns(10);
        southPanel.add(insertNameDelete);
        deleteAccount.addActionListener(this);
        southPanel.add(deleteAccount);

    }
    private void build(){

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        var o = e.getSource();

        var builder = new DbHandler();
        builder.connection();

        if(o == addAccount){
            try {
                if(!nameAccount.getText().equals("") && !valueAccount.getText().equals("")) {
                    builder.executeRequest(QueryBuilder.addAccount(nameAccount.getText(), Double.parseDouble(valueAccount.getText())));
                }
                model.actualiseModel();
                table.revalidate();
                table.repaint();
                scroll.repaint();
                this.repaint();


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

                model.actualiseModel();
                model.fireTableRowsDeleted(0, 10);
                table.revalidate();
                table.repaint();
                scroll.repaint();
                this.repaint();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.exit(1);
            }
        }
        model.fireTableDataChanged();
        builder.close();
    }

    public static void runAccountPanel(){
        var panel = new AccountVue();
        panel.setVisible(true);
    }
}
