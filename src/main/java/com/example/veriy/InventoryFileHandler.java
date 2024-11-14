package com.example.veriy;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryFileHandler {
    private static final String FILE_PATH = "inventory.csv";

    public void writeToFile(List<Product> products) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH))) {
            for (Product product : products) {
                writer.write(product.getId() + "," + product.getName() + "," + product.getPrice() + "," + product.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Product> readFromFile() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                int quantity = Integer.parseInt(parts[3]);
                products.add(new Product(id, name, price, quantity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
}
