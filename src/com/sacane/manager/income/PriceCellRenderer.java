package com.sacane.manager.income;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class PriceCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        var price = (Double) value;

        setText(price.toString());

        if(price < 0){
            setBackground(Color.RED);
        } else {
            setBackground(Color.GREEN);
        }


        return this;
    }
}
