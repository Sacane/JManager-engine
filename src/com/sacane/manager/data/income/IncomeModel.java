package com.sacane.manager.data.income;
import com.sacane.manager.wrapper.ModelWrapper;
import com.sacane.manager.wrapper.TableInitializer;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class IncomeModel extends AbstractTableModel {

    private List<IncomeManager> income;
    private final IncomeService service;
    private final TableInitializer initializer;

    public IncomeModel(ModelWrapper wrapper){
        this.service = IncomeService.getInstance(wrapper);
        this.initializer = getInitializer();
        income = service.findLastIncome();

    }

    private TableInitializer getInitializer(){
        var titles = new ArrayList<String>();
        titles.add("Date");
        titles.add("Label");
        titles.add("Price");
        //titles.add("Sold");
        return new TableInitializer(titles);
    }

    private Object[] getHeader(){
        return initializer.buildTitles();
    }

    public void actualiseModel(){

        income = service.findLastIncome();
        int index = income.size();
        this.fireTableRowsInserted(0, index-1);
        this.fireTableStructureChanged();
        this.fireTableRowsDeleted(0, index-1);
    }


    @Override
    public int getRowCount() {
        return income.size();
    }

    @Override
    public int getColumnCount() {
        return getHeader().length;
    }


    @Override
    public String getColumnName(int columnIndex) {
        return (String)getHeader()[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> income.get(rowIndex).getDate();
            case 1 -> income.get(rowIndex).getNameLabel();
            case 2 -> income.get(rowIndex).getValue();
            //case 3 -> income.get(rowIndex).getActualSold();
            default -> throw new IllegalArgumentException("Index invalid");
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch(columnIndex){
            case 0, 1 -> String.class;
            case 2 -> Double.class;
            default -> throw new IllegalStateException("Unexpected value: " + columnIndex);
        };
    }
}
