package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NoSearchResultPageController {
    @FXML
    private TextField searchBar;

    @FXML
    private void switchToHomePage() throws IOException {
        App.setRoot("home");
    }

    @FXML
    private void switchToBookPage() throws IOException {
        App.setRoot("book");
    }

    @FXML
    private void initialize()
    {
        searchBar.setDisable(true);
    }
}
