package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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

    public void onUserHittingEnterInSearchBar()
    {
        String searchString = searchBar.getText();

    }

}