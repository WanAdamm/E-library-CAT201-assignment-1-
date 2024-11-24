package com.example;

import java.util.ArrayList;
import com.example.Book;

public class Library {
    
    private ArrayList<Book> bookList = new ArrayList <>();

    public void addBooks(String imgURL, String title, String author, String ISBN) 
    {
        Book book = new Book(imgURL, title, author, ISBN, " "); // No borrower at initialization
        bookList.add(book); 
    }

    // public Book searchByTitle()
    // {
    //     return Book;
    // }

    // public Book searchByAuthor()
    // {
        
    // }

    // public Book searchByISBN()
    // {
        
    // }
}
