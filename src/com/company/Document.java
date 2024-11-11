package com.company;

import java.time.LocalDate;

public class Document {

    private String name;
    private int number;
    private String category;
    private String type;
    private String status;
    private LocalDate date;

    public Document() {
    }

    public Document(String name, int number, String category, String type, String status, LocalDate date) {
        this.name = name;
        this.number = number;
        this.category = category;
        this.type = type;
        this.status = status;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
