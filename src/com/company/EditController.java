package com.company;

import com.company.DateUtil;
import com.company.DocTableModel;
import com.company.Document;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class EditController extends JDialog {
    private JPanel mainPanel;
    private JTextField numberField;
    private JTextField categoryField;
    private JTextField typeField;
    private JTextField statusField;
    private JTextField dateField;
    private JTextField nameField;
    private JButton okButton;
    private JButton cancelButton;
    Document document;
    DocTableModel docTableModel;
    int count;

    public EditController(Document document, DocTableModel docTableModel, int count) {
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(mainPanel);
        this.pack();
        this.document = document;
        this.docTableModel = docTableModel;
        this.count = count;
        dateField.setText("dd.mm.yyyy");
        dateField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(dateField.getText().equals("dd.mm.yyyy")) {
                    dateField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(dateField.getText().isEmpty()) {
                    dateField.setText("dd.mm.yyyy");
                }
            }
        });
        setDoc(document);
        okButton.addActionListener(e -> {
            if (isInputValid()) {
                if (count == -1) {
                    int key = JOptionPane.showConfirmDialog(mainPanel, "Вы действительно хотите добавить документ", "", JOptionPane.OK_CANCEL_OPTION);
                    if (key == JOptionPane.OK_OPTION) {
                        getDateFromField(document);
                        docTableModel.addDoc(document);
                        docTableModel.fireTableDataChanged();
                        this.dispose();
                    }
                }
                else {
                    int key = JOptionPane.showConfirmDialog(mainPanel, "Вы действительно хотите изменить документ", "", JOptionPane.OK_CANCEL_OPTION);
                    if (key == JOptionPane.OK_OPTION) {
                        getDateFromField(document);
                        docTableModel.fireTableDataChanged();
                        this.dispose();
                    }
                }
            }
        });
        cancelButton.addActionListener(e -> {
            this.dispose();
        });
    }

    void getDateFromField(Document document) {
        document.setName(nameField.getText());
        document.setNumber(Integer.parseInt(numberField.getText()));
        document.setCategory(categoryField.getText());
        document.setType(typeField.getText());
        document.setStatus(statusField.getText());
        document.setDate(DateUtil.parse(dateField.getText()));
    }

    void setDoc(Document document) {
        if (document.getName() != null) {
            this.document = document;
            nameField.setText(document.getName());
            numberField.setText(Integer.toString(document.getNumber()));
            categoryField.setText(document.getCategory());
            typeField.setText(document.getType());
            statusField.setText(document.getStatus());
            dateField.setText(DateUtil.format(document.getDate()));
        }
        else {
            numberField.setText("");
            numberField.setText("");
            categoryField.setText("");
            typeField.setText("");
            statusField.setText("");
        }
    }

    boolean isInputValid() {
        String errorMessage = "";
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "Некорректно введено имя!\n";
        }
        if (numberField.getText() == null || numberField.getText().length() == 0) {
            errorMessage += "Некорректно введен номер!\n";
        } else {
            try {
                Integer.parseInt(numberField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Некорректно введен номер(должен быть целочисленным) !\n ";
            }
        }
        if (categoryField.getText() == null || categoryField.getText().length() == 0) {
            errorMessage += "Некорректно введена категория!\n";
        }
        if (typeField.getText() == null || typeField.getText().length() == 0) {
            errorMessage += "Некорректно введен тип!\n";
        }
        if (statusField.getText() == null || statusField.getText().length() == 0) {
            errorMessage += "Некорректно введен статус!\n";
        }
        if (dateField.getText() == null || dateField.getText().length() == 0) {
            errorMessage += "Некорректно введена дата создания!\n";
        } else {
            if (!DateUtil.validDate(dateField.getText())) {
                errorMessage += "Неверный формат даты. Используйте дд.мм.ггг";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            JOptionPane.showMessageDialog(mainPanel, errorMessage,"",  JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

}
