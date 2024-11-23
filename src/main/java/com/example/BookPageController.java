package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class BookPageController {

    @FXML
    private void switchToHomePage() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void switchToAddBookPage() throws IOException {
        App.setRoot("addBook");
    }

    @FXML
    private TextField searchBar;

    public void onUserHittingEnterInSearchBar() {
        String searchString = searchBar.getText();

    }

    @FXML
    private VBox bookListContainer;

    private ObservableList<Book> books;

    @FXML
    public void initialize() {
        books = FXCollections.observableArrayList();

        String filePath = "src/main/data/book data.csv"; // Replace with your file path

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                // Split the line on commas, respecting quoted fields
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                // Convert the array to an ArrayList and add it to the main list
                ArrayList<String> row = new ArrayList<>();
                for (String field : fields) {
                    row.add(field.trim()); // Trim to remove unnecessary spaces
                }

                Book book = new Book(row.get(0), row.get(1), row.get(2), row.get(3), "");

                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Book book : books) {
            HBox container = new HBox();
            container.setPrefHeight(100);
            container.setPrefWidth(200);
            

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
                imageUrl = "http://ecx.images-amazon.com/images/I/51l6XIoa3rL.jpg";
                Image image = new Image(imageUrl);
                bookThumbnail.setImage(image);
            }

            VBox infoContainer = new VBox();
            infoContainer.setAlignment(Pos.CENTER_LEFT);
            infoContainer.setPrefHeight(96.0);  
            infoContainer.setPrefWidth(344.0);
            infoContainer.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));

            Label title = new Label(book.getTitle());
            Label author = new Label(book.getAuthor());
            Label isbn = new Label(book.getISBN());

            infoContainer.getChildren().add(title);
            infoContainer.getChildren().add(author);
            infoContainer.getChildren().add(isbn);

            container.getChildren().add(bookThumbnail);
            container.getChildren().add(infoContainer);

            HBox.setMargin(bookThumbnail, new Insets(5.0, 5.0, 5.0, 5.0));
            HBox.setMargin(infoContainer, new Insets(5.0, 5.0, 5.0, 5.0));

            bookListContainer.getChildren().add(container);
        }
    }
}
