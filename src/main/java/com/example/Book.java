package com.example;

public class Book {
    private String title;
    private int ISBN;
    private boolean availability;
    private String borrowerName;

    // Constructor
    public Book(String title, int ISBN, boolean availability, String borrowerName) {
        this.title = title;
        this.ISBN = ISBN;
        this.availability = availability;
        this.borrowerName = borrowerName;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }
}
