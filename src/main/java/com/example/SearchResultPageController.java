package com.example;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    private ArrayList<Book> searchResultBooks = new ArrayList<>();

    @FXML
    private VBox bookListContainer;

    // Method to update UI elements
    private void updateUI() {
        for (Book book : searchResultBooks) {
            if (book != null) {
                HBox container = new HBox();
                container.setPrefHeight(100);
                container.setPrefWidth(1080);

                ImageView bookThumbnail = new ImageView();
                bookThumbnail.setFitHeight(102.0);
                bookThumbnail.setFitWidth(65.0);
                bookThumbnail.setPickOnBounds(true);
                bookThumbnail.setPreserveRatio(true);

                // Load an image from a URL
                String imageUrl = book.getImgUrl(); // Replace with your image URL

                try {
                    Image image = new Image(imageUrl);
                    bookThumbnail.setImage(image);
                } catch (Exception e) {
                    // default image path
                    imageUrl = "file:C:/Users/coder/OneDrive/Desktop/CAT201/Project/elibrary/elibrary/src/main/image/unknown book.jpg";
                    Image image = new Image(imageUrl);
                    bookThumbnail.setImage(image);
                }

                // when the book thumbnail is pressed on go to the book detail page
                bookThumbnail.setOnMousePressed(e -> {
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

                VBox infoContainer = new VBox();
                infoContainer.setAlignment(Pos.CENTER_LEFT);
                infoContainer.setPrefHeight(100.0);
                infoContainer.setPrefWidth(290.0);
                infoContainer.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));

                Label title = new Label(book.getTitle());
                Label author = new Label(book.getAuthor());
                Label isbn = new Label(book.getISBN());

                infoContainer.getChildren().add(title);
                infoContainer.getChildren().add(author);
                infoContainer.getChildren().add(isbn);

                VBox buttonContainer = new VBox();
                Button borrowButton = new Button("Borrow");

                // if book is available borrow button would appear else return button appear
                String available;
                if (book.isAvailable()) {

                    // borrowButton functionality when book is available
                    borrowButton.setOnMousePressed(e -> {
                        try {
                            // Load the FXML file
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("borrowerFormPage.fxml"));
                            Parent root = loader.load(); // This initializes the controller

                            // Get the controller
                            BorrowerFormPageController controller = loader.getController();
                            controller.setBook(book); // Pass the book to the next page

                            // Switch the scene
                            App.setRoot(root); // Assuming App.setScene is defined in your app
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    });

                    available = "available";
                } else {
                    available = "unavailable";

                    borrowButton.setText("Return"); // set the borrow button to return if book is not available

                    // borrowButton functionality when book is available
                    borrowButton.setOnMousePressed(e -> {
                        book.setBorrowerName("");
                        book.setAvailability(true);

                        try {
                            App.setRoot("book");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    });
                }

                Label availability = new Label(available);
                infoContainer.getChildren().add(availability);

                buttonContainer.setAlignment(Pos.BOTTOM_CENTER);
                buttonContainer.setPrefHeight(200.0);
                buttonContainer.setPrefWidth(100.0);

                borrowButton.setPrefHeight(26.0);
                borrowButton.setPrefWidth(78.0);

                buttonContainer.getChildren().add(borrowButton);

                container.getChildren().add(bookThumbnail);
                container.getChildren().add(infoContainer);
                container.getChildren().add(buttonContainer);

                HBox.setMargin(bookThumbnail, new Insets(5.0, 5.0, 5.0, 5.0));
                HBox.setMargin(infoContainer, new Insets(5.0, 5.0, 5.0, 5.0));

                bookListContainer.getChildren().add(container);
            }
        }

    }

    public void setBook(ArrayList<Book> searchResultBooks) {
        this.searchResultBooks = searchResultBooks; // Save the book object
        updateUI(); // Update the UI based on the book data
    }

    @FXML
    public void initialize() {
        searchBar.setDisable(true);
    }
}
