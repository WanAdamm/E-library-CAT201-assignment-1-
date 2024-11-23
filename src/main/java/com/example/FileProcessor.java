package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileProcessor {
    public static void main(String[] args) {
        String filePath = "src/main/data/book data.csv"; // Replace with your file path
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line on commas, respecting quoted fields
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                
                // Convert the array to an ArrayList and add it to the main list
                ArrayList<String> row = new ArrayList<>();
                for (String field : fields) {
                    row.add(field.trim()); // Trim to remove unnecessary spaces
                }
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the data to verify the 2D ArrayList
        System.out.println("CSV Data:");
        for (ArrayList<String> row : data) {
            System.out.println(row);
        }
    }
}