package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SearchResultPageController {
    @FXML
    private void switchToHomePage() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void switchToBookPage() throws IOException {
        App.setRoot("book");
    }

    @FXML
    private TextField searchBar;

    @FXML
    private Label title;

    @FXML
    private Label author;

    @FXML
    private Label ISBN;

    @FXML
    private Label availability;

    @FXML
    private Label borrowerName;

    @FXML
    private ImageView bookCover; // This is linked to the fx:id in FXML

    private Book book;

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
            if (book.isAvailable()) {
                availability.setText("available");
                borrowerName.setText(" ");
            } else {
                availability.setText("unavailable");
                borrowerName.setText(book.getBorrowerName());
            }
        }
    }

    public void setBook(Book book) {
        this.book = book; // Save the book object
        updateUI(); // Update the UI based on the book data
    }

    @FXML
    public void initialize() {
        searchBar.setDisable(true);

        bookCover.setOnMousePressed(e -> {
            try {
                // Load the FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("bookDetail.fxml"));
                Parent root = loader.load(); // This initializes the controller

                // Get the controller
                BookDetailPageController controller = loader.getController();
                controller.setBook(book); // Pass the book to the next page

                // Switch the scene
                App.setRoot(root);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }
}
