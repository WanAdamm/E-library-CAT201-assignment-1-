package com.example;

import java.io.IOException;

import javafx.fxml.FXML;

public class AddBookPageController {

    @FXML
    private void switchToHomePage() throws IOException {
        App.setRoot("home");
    }
    
    @FXML
    private void switchToBookPage() throws IOException {
        App.setRoot("book");
    }
}