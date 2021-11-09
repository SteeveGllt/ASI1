package models;

import javax.swing.table.AbstractTableModel;

import classes.Auteur;
import classes.Client;
import classes.Personne;
import classes.Realisateur;
import main.Main;

public class AuteurTableModel extends AbstractTableModel {

    private String[] header = { "Nom", "Prénom", "Type", "Sélection" };
    private Object[][] data;

    public AuteurTableModel() {
        data = new Object[Main.autReals.size()][];
        int i = 0;
        for (Personne a : Main.autReals) {
                data[i] = new Object[4];
                data[i][0] = a.getNom();
                data[i][1] = a.getPrenom();
                if(a instanceof Auteur){
                data[i][2] = "Auteur";
            }
            else {
                data[i][2] = "Réalisateur";
            }
                data[i][3] = false;
                i++;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 3;
    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (column == 3) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 3) {
            if (aValue instanceof Boolean) {
                data[rowIndex][columnIndex] = aValue;
                fireTableCellUpdated(rowIndex, columnIndex);
            }
        }
    }

    @Override
    public int getRowCount() {
        if(data!=null) {
            return data.length;
        }else{return 0;}
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
