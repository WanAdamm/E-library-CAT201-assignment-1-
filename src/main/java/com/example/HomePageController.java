package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HomePageController {

    @FXML
    private void switchToBookPage() throws IOException {
        App.setRoot("book");
    }

    @FXML
    private TextField searchBar;

    public void initialize() {
        searchBar.setDisable(true);
    }
}
