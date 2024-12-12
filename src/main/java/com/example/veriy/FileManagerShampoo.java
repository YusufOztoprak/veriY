package com.example.veriy;
import Models.Shampoo;
import Models.TopWear;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagerShampoo {
    public static void saveShampooData(List<Shampoo> ShampooList, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Shampoo sampuan : ShampooList) {
                writer.write(sampuan.getId() + "," +
                        sampuan.getName() + "," +
                        sampuan.getPrice() + "," +
                        sampuan.getAmount() + "," +
                        sampuan.getExpiration_date() + "," +
                        sampuan.getBrand() + "," +
                        sampuan.getUserInstructions() + "," +
                        sampuan.getHairType() + "," +
                        sampuan.getVolume());
                writer.newLine();
            }
        }
    }

    // Dosyadan TopWear nesnelerini okuma
    public static List<Shampoo> loadShampooData(String fileName) throws IOException {
        List<Shampoo> ShampooList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 10) {
                    Shampoo sampuan = new Shampoo(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
                            Integer.parseInt(parts[4]), (parts[5]), parts[6], parts[7],Double.parseDouble(parts[8]));
                    ShampooList.add(sampuan);
                }
            }
        }
        return ShampooList;
    }
}
