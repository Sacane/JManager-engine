package com.sacane.manager.gui;
import com.sacane.calc.gui.WindowManager;
import com.sacane.manager.database.DataBuild;
import com.sacane.manager.database.QueryBuilder;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class AccountFrame extends JFrame {

    private final DataBuild builder;

    private final JPanel mainPanel = new JPanel();
    private final JPanel tablePanel = new JPanel();



    public AccountFrame(DataBuild builder){
        this.builder = builder;
        build();
    }

    void tableBuild() throws SQLException {
        builder.connection();
        tablePanel.setLayout(new GridLayout(2, 10));
        builder.getSetByRequest(QueryBuilder.selectAccount());


        builder.close();
    }


    void build(){

        builder.connection();
        var listener = new AccountListener(builder);
        setLayout(new BorderLayout());
        var account = new AccountListener(builder);
        JPanel southPanel = new JPanel();
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        add(account.contentPanel(), BorderLayout.CENTER);
        southPanel.add(listener.getMainPanel());
        add(southPanel, BorderLayout.SOUTH);
        validate();
        repaint();
        setVisible(true);
        builder.close();
    }

    public static void main(String[] args) {
        var builder = new DataBuild();
        SwingUtilities.invokeLater(() -> {

            var test = new AccountFrame(builder);

        });

    }
}
