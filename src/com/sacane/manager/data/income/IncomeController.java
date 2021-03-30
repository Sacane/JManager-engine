package com.sacane.manager.data.income;

import com.sacane.manager.data.Month;
import com.sacane.manager.graph.GraphRenderer;
import com.sacane.manager.wrapper.*;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Manage all the control in the IncomeFrame.
 * @author Johan "Sacane" Ramaroson Rakotomihamina.
 */
@SuppressWarnings("rawtypes")
public class IncomeController implements ActionListener {

    // fields to manage the table

    private final IncomeModel model;
    private final JTable table;

    //panels
    final JPanel southPanel = new JPanel();
    final JPanel northPanel = new JPanel();
    final JPanel bottomPanel = new JPanel();
    final JPanel mainPanel = new JPanel();
    ModelWrapper wrapper;

    //design
    final JLabel info = new JLabel("Total Sold : ");
    final JLabel value;



    //labels
    final JLabel label = new JLabel("Label :");
    final JLabel cost = new JLabel("price");

    //Text
    final JTextField labelName = new JTextField();
    final JTextField putCost = new JTextField("");

    //ComboBox
    final JComboBox day;
    final JComboBox account;

    //Buttons
    final JButton visualGraph = new JButton("Graph");
    final JButton addButton = new JButton("add label");

    final JTextField deleteName = new JTextField();
    final JButton deleteButton = new JButton("delete label");

    //db access
    private final DbHandler handler = new DbHandler();

    /**
     * Initialize a controller and create the panel.
     * @param wrapper : ModelWrapper used to control the common model parts of the program.
     */
    IncomeController(ModelWrapper wrapper){
        this.wrapper = wrapper;
        mainPanel.setLayout(new BorderLayout());
        model = new IncomeModel(wrapper);
        table = new JTable(model);
        TableRowSorter<TableModel> s = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(s);
        java.util.List<RowSorter.SortKey> sortList = new ArrayList<>();
        sortList.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));


        s.setSortKeys(sortList);


        value = new JLabel((wrapper.getTotalSold()) + " €");

        day = new JComboBox(TableInitializer.buildDayCheckBox(wrapper.getCurrentMonth().getNumberDay()));
        account = new JComboBox(TableInitializer.buildAccountCheckBox());
        northPanel.add(info, BorderLayout.CENTER);
        northPanel.add(value, BorderLayout.CENTER);
        table.setAutoCreateRowSorter(true);
        table.getColumnModel().getColumn(2).setCellRenderer(new PriceCellRenderer());
        southPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        deleteName.setColumns(10);
        labelName.setColumns(10);
        putCost.setColumns(10);

        bottomPanel.add(label);
        bottomPanel.add(labelName);
        bottomPanel.add(cost);
        bottomPanel.add(putCost);
        bottomPanel.add(day);

        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        visualGraph.addActionListener(this);

        bottomPanel.add(addButton);

        bottomPanel.add(account);
        bottomPanel.add(deleteName);
        bottomPanel.add(deleteButton);
        bottomPanel.add(visualGraph);

        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

    }


    /**
     * @return JPanel of the Income Window.
     */
    JPanel getPane(){
        return mainPanel;
    }

    /**
     *
     * @param number : String representing the number to check.
     * @return true if the number is a number, false elsewhere.
     */
    private boolean isDouble(String number){
        try{
            Double.parseDouble(number);
            return true;
        }catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * update the value according to the sum of the sold.
     */
    void updateValue(){
        value.setText(String.valueOf(wrapper.updateSold()));
        value.revalidate();
        value.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var o = e.getSource();
        if(o == addButton){
            if(!putCost.getText().equals("") && isDouble(putCost.getText()) && !labelName.getText().equals("")) {
                var soldTyped = Double.parseDouble(putCost.getText());
                try {
                    handler.connection();
                    handler.addDbIncome(soldTyped >= 0,
                            labelName.getText(),
                            soldTyped,
                            "",
                            Month.formattedDate(Integer.parseInt(String.valueOf(day.getSelectedItem())),
                                    wrapper.getCurrentMonth(),
                                    wrapper.getCurrentYear())

                    );
                    Trigger.updateHisto(wrapper.getTotalSold(),
                            Month.formattedDate(Integer.parseInt(String.valueOf(day.getSelectedItem())),
                                    wrapper.getCurrentMonth(),
                                    wrapper.getCurrentYear()), labelName.getText());
                    handler.executeRequest(QueryBuilder.dbUpdateSold(Double.parseDouble(putCost.getText()), (String)account.getSelectedItem()));
                    updateValue();
                    model.actualiseModel();
                    table.revalidate();
                    table.repaint();

                    handler.close();
                    labelName.setText("");
                    putCost.setText("");
                } catch (SQLException sqe) {
                    System.out.println(sqe.getMessage());
                    System.exit(1);
                }
            }
        }
        if(o == deleteButton){
            if(!deleteName.getText().equals("")){
                try{
                    handler.connection();
                    handler.deleteIncome(deleteName.getText());
                    model.actualiseModel();
                    handler.close();
                }catch(SQLException ex){
                    System.out.println(ex.getMessage());
                    System.exit(1);
                }
            }
        }
        if(o == visualGraph){
            GraphRenderer.renderer(wrapper);
        }
    }
}
