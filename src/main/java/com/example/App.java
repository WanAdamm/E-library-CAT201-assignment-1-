package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Library library = new Library();

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("home"), 700, 480);
        stage.setScene(scene);
        stage.show();

        String filePath = "src/main/data/book data.csv"; // Replace with your file path

        // only read the file at initialization once
        try (
                BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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
                App.library.addBook(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Define the function to run when the app is closed
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                onAppClose(); // Run your function when the application is stopped
            }
        });
    }

    // save data on app close
    private void onAppClose() {
        String filePath = "src/main/data/book data.csv";

        String[] headers = { "Image URL", "Title", "Author", "ISBN" };

        try (FileWriter writer = new FileWriter(filePath)) {
            // Write headers
            writer.append(String.join(",", headers)); // Join headers with commas
            writer.append("\n");

            // Write data rows
            for (Book bookData : App.library.getBook()) {
                String data = bookData.getImgUrl() + "," + bookData.getTitle() + "," + bookData.getAuthor() + ","
                        + bookData.getISBN();
                writer.append(data); // Join each row with commas
                writer.append("\n"); // Newline after each row
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void setRoot(Parent root) throws IOException {
        scene.setRoot(root);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}