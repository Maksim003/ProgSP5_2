package com.company;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DocTableModel extends AbstractTableModel {

    final String[] COLUMNS = {"Название", "Номер"};
    List<Document> documents;

    public DocTableModel(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public int getRowCount() {
        return documents.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (documents.size() > 0) {
            return switch (columnIndex) {
                case 0 -> documents.get(rowIndex).getName();
                case 1 -> documents.get(rowIndex).getNumber();
                default -> "";
            };
        }
        else return 0;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    public void removeRow(int row) {
        documents.remove(row);
    }

    public void addDoc(Document document) {
        documents.add(document);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (getValueAt(0, columnIndex) != null) {
            return getValueAt(0, columnIndex).getClass();
        }
        else return Object.class;
    }


}
