package com.example;

import java.util.ArrayList;

public class Library {

    private ArrayList<Book> bookList = new ArrayList<>();

    public void addBook(String imgURL, String title, String author, String ISBN) {
        Book book = new Book(imgURL, title, author, ISBN, " "); // No borrower at initialization
        bookList.add(book);
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public ArrayList<Book> getBook() {
        return bookList;
    }

    public ArrayList<Book> searchBook(String searchString) {
        ArrayList<Book> searchResult = new ArrayList<>();

        if (searchByAuthor(searchString) != null) {
            for (Book book : searchByAuthor(searchString)) {
                searchResult.add(book);
            }
        }

        if (searchByTitle(searchString) != null) {
            for (Book book : searchByTitle(searchString)) {
                searchResult.add(book);
            }
        }

        if (searchByISBN(searchString) != null) {
            for (Book book : searchByISBN(searchString)) {
                searchResult.add(book);
            }
        }

        return searchResult;
    }

    public ArrayList<Book> searchByTitle(String title) {
        ArrayList<Book> searchResult = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                searchResult.add(book);
            }
        }

        if (!searchResult.isEmpty()) {
            return searchResult;
        }
        return null; // If no book with the given title is found
    }

    public ArrayList<Book> searchByAuthor(String author) {
        ArrayList<Book> searchResult = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                searchResult.add(book);
            }
        }

        if (!searchResult.isEmpty()) {
            return searchResult;
        }

        return null; // If no book with the given author is found
    }

    public ArrayList<Book> searchByISBN(String ISBN) {
        ArrayList<Book> searchResult = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getISBN().equals(ISBN)) {
                searchResult.add(book);
            }
        }

        if (!searchResult.isEmpty()) {
            return searchResult;
        }

        return null; // If no book with the given ISBN is found
    }
}
