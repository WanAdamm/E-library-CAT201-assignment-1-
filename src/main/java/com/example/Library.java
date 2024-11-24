package com.example;

import java.util.ArrayList;

public class Library {

    private ArrayList<Book> bookList = new ArrayList<>();

    public void addBooks(String imgURL, String title, String author, String ISBN) {
        Book book = new Book(imgURL, title, author, ISBN, " "); // No borrower at initialization
        bookList.add(book);
    }

    public Book searchByTitle(String title) {
        for (Book book : bookList) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; // If no book with the given title is found
    }

    public Book searchByAuthor(String author) {
        for (Book book : bookList) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        return null; // If no book with the given author is found
    }

    public Book searchByISBN(String ISBN) {
        for (Book book : bookList) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null; // If no book with the given ISBN is found
    }
}
