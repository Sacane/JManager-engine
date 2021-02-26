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

public class Account extends JFrame implements ActionListener {


    private final DbHandler builder;

    private final JPanel mainPanel = new JPanel();
    private final JPanel tablePanel = new JPanel();

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

    private final JPanel actionPanel = new JPanel();

    Account(DbHandler builder){
        this.builder = builder;
        build();
        contentPanel();
    }


    private void build(){

        setTitle("Account Window");

        mainPanel.setLayout(new BorderLayout());
        builder.connection();
        setLayout(new BorderLayout());
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buildTable();
        mainPanel.add(actionPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setVisible(true);

        builder.close();
    }

    static double updateTotal(DbHandler builder) throws SQLException{
        var array = builder.getSetTotal();
        return array.getInt("total");
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
            actionPanel.add(panel, BorderLayout.SOUTH);
        }catch(SQLException sqe){
            System.out.println(sqe.getMessage());
        }
    }

    public void contentPanel(){

        actionPanel.add(name);
        nameAccount.setColumns(10);
        actionPanel.add(nameAccount);
        actionPanel.add(value);
        valueAccount.setColumns(10);
        actionPanel.add(valueAccount);
        addAccount.addActionListener(this);
        actionPanel.add(addAccount);
        actionPanel.add(nameDelete);
        insertNameDelete.setColumns(10);
        actionPanel.add(insertNameDelete);
        deleteAccount.addActionListener(this);
        actionPanel.add(deleteAccount);

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

    public static void main(String[] args) {
        var builder = new DbHandler();
        SwingUtilities.invokeLater(() -> {

            var test = new Account(builder);

        });

    }
}
