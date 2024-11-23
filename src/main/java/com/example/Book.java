package com.example;

public class Book {
    private String title;
    private String imgURL;
    private String author;
    private String ISBN;
    private boolean availability;
    private String borrowerName;

    // Constructor
    public Book(String imgURL, String title, String author, String ISBN, String borrowerName, boolean availability) {
        this.author = author;
        this.imgURL = imgURL;
        this.title = title;
        this.ISBN = ISBN;
        this.availability = availability;
        this.borrowerName = borrowerName;
    }

    // overloaded constructor
    public Book(String imgURL, String title, String author, String ISBN, String borrowerName) {
        this.author = author;
        this.imgURL = imgURL;
        this.title = title;
        this.ISBN = ISBN;
        this.availability = true;
        this.borrowerName = borrowerName;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgUrl() {
        return imgURL;
    }

    public void setImgUrl(String imgUrl) {
        this.imgURL = imgUrl;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
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
