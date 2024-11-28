package com.example;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

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
    private Button selectImage;

    @FXML
    private ImageView bookThumbnail;

    @FXML
    private void switchToHomePage() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void switchToBookPage() throws IOException {
        App.setRoot("book");
    }

    String imageUrl = null;

    // get image url
    public void getImagePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(selectImage.getScene().getWindow()); // using the current window
        imageUrl = selectedFile.getAbsolutePath();
        imageUrl = "file:/" + imageUrl; // to ensure that it detects as local file
        setImageUrl(imageUrl);
    }

    @FXML
    private void addBook() throws IOException // linked to the submit button
    {
        Book book = new Book(imageUrl, title.getText(), author.getText(), isbn.getText(), "");
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

    private void checkImage() {
        if (imageUrl != null && !imageUrl.trim().isEmpty()) {
            try {
                // Load the image
                Image image = new Image(imageUrl, true); // 'true' enables background loading
                bookThumbnail.setImage(image);

                // Optional: Add a listener for loading errors
                image.exceptionProperty().addListener((obs, oldException, newException) -> {
                    if (newException != null) {
                        System.err.println("Error loading image: " + newException.getMessage());
                        // Set a default image on failure
                        bookThumbnail.setImage(new Image(
                                "file:C:/Users/coder/OneDrive/Desktop/CAT201/Project/elibrary/elibrary/src/main/image/unknown book.jpg"));
                    }
                });
            } catch (Exception e) {
                System.err.println("Invalid image URL: " + e.getMessage());
                bookThumbnail.setImage(new Image(
                        "file:C:/Users/coder/OneDrive/Desktop/CAT201/Project/elibrary/elibrary/src/main/image/unknown book.jpg"));
            }
        } else {
            System.out.println("Image URL is empty or null.");
            bookThumbnail.setImage(new Image(
                    "file:C:/Users/coder/OneDrive/Desktop/CAT201/Project/elibrary/elibrary/src/main/image/unknown book.jpg")); // Placeholder
                                                                                                                               // for
                                                                                                                               // missing
                                                                                                                               // image
        }
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        checkImage(); // Update ImageView when URL is set
    }
}
