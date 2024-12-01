package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
    public static Library library = new Library(); //Library is a static object accessible by all page

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("home"), 700, 480);
        stage.setScene(scene);
        stage.show();

        String filePath = "src/main/data/book data.csv";

        // only read the file at initialization once
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

                // create new book object for each line of data
                Book book = new Book(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4));
                if(row.get(5).trim().equalsIgnoreCase("1"))
                {
                    book.setAvailability(true);
                }
                else
                {
                    book.setAvailability(false);
                }
                
                // add those book to the library
                App.library.addBook(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // run when the app is closed
    @Override
    public void stop() {
        // Code to run when the application is stopped
        onAppClose();  // Run the function to clean up resources
    }

    // save data on app close
    private void onAppClose() {
        String filePath = "src/main/data/book data.csv";

        String[] headers = { "Image URL", "Title", "Author", "ISBN", "Borrower Name", "Availability" };

        // by default the file is opened and rewritten
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write headers
            writer.append(String.join(",", headers)); // Join headers with commas
            writer.append("\n");

            // Write data rows
            for (Book bookData : App.library.getBook()) {
                String data = bookData.getImgUrl() + "," + bookData.getTitle() + "," + bookData.getAuthor() + ","
                        + bookData.getISBN();

                if(bookData.isAvailable())
                {
                    data = data + "," + "" + "," + "1";
                }
                else
                {
                    data = data + "," + bookData.getBorrowerName() + "," + "0";
                }

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

    // overloaded setRoot function that accept Root Node instead of fxml file
    static void setRoot(Parent root) throws IOException {
        scene.setRoot(root);
    }

    // function to load fxml
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}