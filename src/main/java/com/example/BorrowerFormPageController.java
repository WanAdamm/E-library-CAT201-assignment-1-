package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

public class BorrowerFormPageController {

    private Book book;

    @FXML
    private Label title;

    @FXML
    private Label author;

    @FXML
    private Label ISBN;
    
    @FXML
    private ImageView bookCover; // This is linked to the fx:id in FXML

    // Setter method to receive the book object
    public void setBook(Book book) {
        this.book = book; // Save the book object
        System.out.println(book.getISBN());
        updateUI(); // Update the UI based on the book data
    }

    // Method to update UI elements
    private void updateUI() {
        if (book != null) {
            if (book.getImgUrl() != null) {
                bookCover.setImage(new Image(book.getImgUrl()));
            }
            if (book.getTitle() != null) {
                title.setText(book.getTitle());
            }
            if (book.getAuthor() != null) {
                author.setText(book.getAuthor());
            }
            if (book.getISBN() != null) {
                ISBN.setText(book.getISBN());
            }
        }
    }

    @FXML
    private void switchToHomePage() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void switchToBookPage() throws IOException {
        App.setRoot("book");
    }

}
