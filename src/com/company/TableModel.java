package com.company;

import javax.swing.table.AbstractTableModel;

/**
 * TableModel is needed for JTable to be able to be customized
 * @author Daniel
 */
public class TableModel extends AbstractTableModel {
    private String[] columnNames;
    private Object[][] data;

    /**
     *
     * @param columnNames names of a columns of a table
     * @param data data that we whant to show
     */
    public TableModel(String[] columnNames, Object[][] data) {
        this.columnNames = columnNames;
        this.data = data;
    }

    /**
     * overridden
     * @return the row count of a data
     */
    @Override
    public int getRowCount() {
        return data.length;
    }

    /**
     * overridden
     * @param c column number
     * @return class of data in column
     */
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /**
     * overridden
     * @return how many columns
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * overridden
     * @param col which column, number of column
     * @return name of the column
     */
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    /**
     * overridden
     * @param row number of row
     * @param col number of column
     * @return data in the cell
     */
    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    /**
     * overridden
     * @param value value that we want to update the cell with
     * @param row row of data that we want to update
     * @param col column of data that we want to update
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    public void changeData(Object[][] data){
        this.data = data;
    }
}
