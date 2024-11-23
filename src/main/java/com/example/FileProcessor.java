package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {
    public static void main(String[] args) {
        String filePath = "src/main/data/book data.csv"; // Replace with your file path

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read the header
            String headerLine = br.readLine();
            if (headerLine != null) {
                System.out.println("Header:");
                String[] headers = headerLine.split(",");
                for (String header : headers) {
                    System.out.print(header + "\t");
                }
                System.out.println("\n");

                // Read and print data rows
                System.out.println("Data:");
                String line;
                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Split handling quotes
                    for (String field : fields) {
                        System.out.print(field.trim() + "\t");
                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

