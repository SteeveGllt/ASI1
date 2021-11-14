package models;

import classes.Client;
import classes.Livre;
import classes.Video;
import main.Main;
import vues.LivrePanel;

import javax.swing.table.AbstractTableModel;

public class VideoTableModel extends AbstractTableModel {
    private String[] header = {"Référence", "Désignation", "Prix", "Durée","Sélection"};
    private Object[][] data;

    public VideoTableModel() {
        data = new Object[Main.videos.size()][];
        int i = 0;
        for (Video v : Main.videos) {
            data[i] = new Object[6];
            data[i][0] = v.getReference();
            data[i][1] = v.getDesignation();
            data[i][2] = v.getPrix();
            data[i][3] = v.getDuree();
            data[i][4] = false;
            i++;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 4;
    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (column == 4) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 4) {
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
