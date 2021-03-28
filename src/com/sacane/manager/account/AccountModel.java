package com.sacane.manager.account;
import com.sacane.manager.gui.TableInitializer;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Class use to manage the tableModel of the accounts
 */
class AccountModel extends AbstractTableModel {

    private AccountService service;
    private List<AccountManager> account;
    private final TableInitializer initializer;

    public AccountModel(){
        this.fireTableDataChanged();
        service = AccountService.getInstance();
        account = service.findLastAccount();
        this.initializer = getInitializer();
    }


    /**
     * Use to actualise the model and its visual representation.
     */
    public void actualiseModel(){

        service = AccountService.getInstance();
        account = service.findLastAccount();
        int index = account.size();
        this.fireTableRowsUpdated(index-1, index);
        this.fireTableRowsInserted(index-1, index);
        this.fireTableStructureChanged();
    }

    private TableInitializer getInitializer(){
        var titles = new ArrayList<String>();
        titles.add("name account");
        titles.add("amount");

        return new TableInitializer(titles);
    }

    /**
     * Build an array of Object using to build the table.
     * @return Object[] which contains the header of the model.
     */
    private Object[] getHeader(){
        return initializer.buildTitles();
    }

    @Override
    public int getRowCount() {
        return account.size();
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
            case 0 -> account.get(rowIndex).getName();
            case 1 -> account.get(rowIndex).getValue();
            default -> throw new IllegalArgumentException("Index invalid");
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> String.class;
            case 1 -> Double.class;
            default -> Object.class;
        };
    }
}
