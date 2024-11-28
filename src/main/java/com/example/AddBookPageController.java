package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddBookPageController {

    @FXML
    private TextField title;

    @FXML
    private TextField author;

    @FXML
    private TextField isbn;

    @FXML
    private Button submit;

    @FXML
    private void switchToHomePage() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void switchToBookPage() throws IOException {
        App.setRoot("book");
    }

    @FXML
    private void addBook() throws IOException // linked to the submit button
    {
        Book book = new Book("", title.getText(), author.getText(), isbn.getText(), "");
        App.library.addBook(book);

        App.setRoot("book");
    }

    @FXML
    private void initialize() {

        submit.setDisable(true);

        title.textProperty().addListener((observable, oldValue, newValue) -> checkFields());
        author.textProperty().addListener((observable, oldValue, newValue) -> checkFields());
        isbn.textProperty().addListener((observable, oldValue, newValue) -> checkFields());
    }

    // Method to check all fields and update button state
    private void checkFields() {
        boolean allValid = title.getText() != null && !title.getText().trim().isEmpty() &&
                author.getText() != null && !author.getText().trim().isEmpty() &&
                isbn.getText() != null && !isbn.getText().trim().isEmpty();

        submit.setDisable(!allValid); // Enable button if all fields are valid
    }

}
