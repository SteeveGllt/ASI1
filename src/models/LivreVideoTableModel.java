package models;

import classes.Article;
import classes.Client;
import classes.Livre;
import classes.Video;
import main.Main;

import javax.swing.table.AbstractTableModel;

public class LivreVideoTableModel extends AbstractTableModel {
    private String[] header = {"Référence", "Désignation", "Prix", "ISBN", "NbPage", "Auteur","Durée", "Résalisateur", "Sélection"};
    private Object[][] data;

    public LivreVideoTableModel() {
        data = new Object[Main.articles.size()][];
        int i = 0;
        for (Article a : Main.articles) {
            data[i] = new Object[9];
            data[i][0] = a.getReference();
            data[i][1] = a.getDesignation();
            data[i][2] = a.getPrix();
            if(a instanceof Video){
                data[i][3] = null;
                data[i][4] = null;
                data[i][5] = null;
            } else{
                data[i][3] = ((Livre) a).getIsbn();
                data[i][4] = ((Livre) a).getNbPages();
                data[i][5] = ((Livre) a).getAuteur();
            }

            if(a instanceof Livre){
                data[i][6] = null;
                data[i][7] = null;
            } else{
                data[i][6] = ((Video) a).getDuree();
                data[i][7] = ((Video) a).getRealisateur();
            }

            data[i][8] = false;
            i++;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 8;
    }

    @Override
    public Class<?> getColumnClass(int column) {
        if (column == 8) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 8) {
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
