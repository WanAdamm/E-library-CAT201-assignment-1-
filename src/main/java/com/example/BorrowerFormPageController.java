package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

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

    @FXML
    private TextField borrowerName; // Use to enter borrower name

    @FXML
    private TextField studentID; // No use

    @FXML
    private Button submit;

    // Method to check all fields and update button state
    private void checkFields() {
        boolean allValid = borrowerName.getText() != null && !borrowerName.getText().trim().isEmpty() &&
                studentID.getText() != null && !studentID.getText().trim().isEmpty();

        submit.setDisable(!allValid); // Enable button if all fields are valid
    }

    @FXML
    public void submitBorrowerInfo() throws IOException {
        String borrowerNameString = borrowerName.getText();

        for (Book bookk : App.library.getBook()) {
            if (bookk.getAuthor().equals(book.getAuthor())) {

                book.setAvailability(false); // set the availability to false
                book.setBorrowerName(borrowerNameString); // set the borrowerName
            }
        }

        // clear both textField
        borrowerName.clear();
        studentID.clear();

        App.setRoot("book");
    }

    @FXML
    private void initialize() {
        submit.setDisable(true);

        borrowerName.textProperty().addListener((observable, oldValue, newValue) -> checkFields());
        studentID.textProperty().addListener((observable, oldValue, newValue) -> checkFields());
    }

}
