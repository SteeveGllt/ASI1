package models;

import classes.Client;
import classes.Livre;
import main.Main;
import vues.LivrePanel;

import javax.swing.table.AbstractTableModel;

public class LivreTableModel extends AbstractTableModel {
    private String[] header = {"Référence", "Désignation", "Prix", "ISBN", "NbPages", "Sélection"};
    private Object[][] data;

    public LivreTableModel() {
        data = new Object[Main.livres.size()][];
        int i = 0;
        for (Livre l : Main.livres) {
            data[i] = new Object[6];
            data[i][0] = l.getReference();
            data[i][1] = l.getDesignation();
            data[i][2] = l.getPrix();
            data[i][3] = l.getIsbn();
            data[i][4] = l.getNbPages();
            data[i][5] = false;
            i++;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 5;
    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (column == 5) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 5) {
            if (aValue instanceof Boolean) {
                data[rowIndex][columnIndex] = aValue;
                fireTableCellUpdated(rowIndex, columnIndex);
            }
        }
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }
}
