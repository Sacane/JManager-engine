package com.sacane.manager.gui;
import com.sacane.manager.database.DataBuild;
import javax.swing.*;


public class AccountFrame extends JFrame {

    private final DataBuild builder;

    private final JPanel mainPanel = new JPanel();


    private final JLabel name = new JLabel("name");
    private final JLabel value = new JLabel("value");

    private final JTextField nameAccount = new JTextField();
    private final JTextField valueAccount = new JTextField();
    private final JButton addAccount = new JButton("Add a new Account");



    public AccountFrame(DataBuild builder){
        this.builder = builder;
        build();

    }


    void build(){
        setSize(IncomeFrame.WIDTH, IncomeFrame.HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel.add(name);
        nameAccount.setColumns(10);
        mainPanel.add(nameAccount);

        mainPanel.add(value);
        valueAccount.setColumns(10);
        mainPanel.add(valueAccount);
        mainPanel.add(addAccount);
        add(mainPanel);



        setVisible(true);
    }

    public static void main(String[] args) {
        var builder = new DataBuild();

        var test = new AccountFrame(builder);
    }
}
