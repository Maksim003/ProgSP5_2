package com.company;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Document> documentsData = new ArrayList<>();
    static File file = new File("documents.txt");
    public static void main(String[] args) {
        documentsData = getData();
        JFrame jFrame = new MainController(documentsData);
        jFrame.setVisible(true);
        jFrame.setLocation(300, 90);
        jFrame.setSize(600, 400);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Main.writeData();
            }
        });
    }

    public static List<Document> getData() {
        try {
            if (!file.exists()) file.createNewFile();
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bf.readLine()) != null) {
                String[] data = line.split(" ");
                String name = data[0];
                int number = Integer.parseInt(data[1]);
                String category = data[2];
                String type = data[3];
                String status = data[4];
                LocalDate date = DateUtil.parse(data[5]);
                documentsData.add(new Document(name, number, category, type, status, date));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return documentsData;
    }

    public static void writeData() {
        try {
            PrintWriter pw = new PrintWriter(file);
            for (int i = 0; i < documentsData.size(); i++) {
                pw.println(documentsData.get(i).getName() + " " + documentsData.get(i).getNumber() + " " +
                        documentsData.get(i).getCategory() + " " + documentsData.get(i).getType() + " " +
                        documentsData.get(i).getStatus() + " " + DateUtil.format(documentsData.get(i).getDate()));
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
