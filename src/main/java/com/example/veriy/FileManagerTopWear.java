package com.example.veriy;

import Models.TopWear;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagerTopWear {

    // TopWear nesnelerini dosyaya kaydetme
    public static void saveTopWearData(List<TopWear> topWearList, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (TopWear topWear : topWearList) {
                writer.write(topWear.getId() + "," +
                        topWear.getName() + "," +
                        topWear.getPrice() + "," +
                        topWear.getAmount() + "," +
                        topWear.getSize() + "," +
                        topWear.getColor() + "," +
                        topWear.getCloth() + "," +
                        topWear.getGender() + "," +
                        topWear.getSleeveType() + "," +
                        topWear.getNeckType());
                writer.newLine();
            }
        }
    }

    // Dosyadan TopWear nesnelerini okuma
    public static List<TopWear> loadTopWearData(String fileName) throws IOException {
        List<TopWear> topWearList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 10) {
                    TopWear topWear = new TopWear(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
                            parts[4], parts[5], parts[6], parts[7], parts[8], parts[9]);
                    topWearList.add(topWear);
                }
            }
        }
        return topWearList;
    }
}
