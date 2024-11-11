package com.company;

import com.company.DateUtil;
import com.company.DocTableModel;
import com.company.Document;
import com.company.EditController;

import javax.swing.*;
import java.util.List;

public class MainController extends JFrame {
    private JPanel mainPanel;
    private JTable docTable;
    private JLabel nameField;
    private JLabel numberField;
    private JLabel categoryField;
    private JLabel typeField;
    private JLabel statusField;
    private JLabel dataField;
    private JButton addButton;
    private JButton editButton;
    private JButton delButton;

    List<Document> documentsData;
    Document document;

    public MainController(List<Document> documentsData) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(mainPanel);
        this.pack();
        this.documentsData = documentsData;

        DocTableModel docTableModel = new DocTableModel(documentsData);
        docTable.setModel(docTableModel);
        docTable.setAutoCreateRowSorter(true);

        delButton.addActionListener(e -> {
            int index = docTable.getSelectedRow();
            if (index >= 0) {
                int key = JOptionPane.showConfirmDialog(mainPanel, "Вы действительно хотите удалить документ", "", JOptionPane.OK_CANCEL_OPTION);
                if (key == JOptionPane.OK_OPTION) {
                    docTableModel.removeRow(index);
                    docTableModel.fireTableDataChanged();
                }
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Ошибка, выберите документ", "", JOptionPane.ERROR_MESSAGE);
            }
        });
        docTable.getSelectionModel().addListSelectionListener(e -> {
            if (!docTable.getSelectionModel().isSelectionEmpty()) {
                if (documentsData.size() >= 1) {
                    int index = docTable.convertRowIndexToModel(docTable.getSelectedRow());
                    document = documentsData.get(index);
                    showDocDetails(document);
                }
            }
        });
        editButton.addActionListener(e -> {
            int index = docTable.getSelectedRow();
            if (index >= 0) {
                showWindow(document, docTableModel, 1);
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Ошибка, выберите документ", "", JOptionPane.ERROR_MESSAGE);
            }
        });
        Document addDocument = new Document();
        addButton.addActionListener(e -> {
            showWindow(addDocument, docTableModel, -1);
        });
    }

    void showDocDetails(Document document) {
        if (document != null) {
            nameField.setText(document.getName());
            nameField.setVisible(true);
            numberField.setText(Integer.toString(document.getNumber()));
            numberField.setVisible(true);
            categoryField.setText(document.getCategory());
            categoryField.setVisible(true);
            typeField.setText(document.getType());
            typeField.setVisible(true);
            statusField.setText(document.getStatus());
            statusField.setVisible(true);
            dataField.setText(DateUtil.format(document.getDate()));
            dataField.setVisible(true);
        }
    }

    void showWindow(Document document, DocTableModel docTableModel, int count) {
        JDialog dialog = new EditController(document, docTableModel, count);
        dialog.setLocation(300, 90);
        dialog.setSize(600, 400);
        dialog.setVisible(true);
    }


}