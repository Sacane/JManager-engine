package com.sacane.manager.income;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class IncomeModel extends AbstractTableModel {

    private final ArrayList<IncomeManager> manager = new ArrayList<>();

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
