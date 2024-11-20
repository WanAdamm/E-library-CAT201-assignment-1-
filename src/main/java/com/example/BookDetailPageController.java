package com.example;

import java.io.IOException;

import javafx.fxml.FXML;

public class BookDetailPageController {
    @FXML
    private void switchToHomePage() throws IOException {
        App.setRoot("home");
    }
}
