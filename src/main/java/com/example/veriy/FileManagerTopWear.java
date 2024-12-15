package com.example.veriy;

import Models.BottomWear;
import Models.TopWear;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
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
        LinkedList<TopWear> topWearList = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 10) {
                    TopWear topWear = new TopWear(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
                            parts[4], parts[5], parts[6], parts[7], parts[8], parts[9]);
                    addTopWearInSortedOrder(topWearList,topWear);
                }
            }
        }
        return topWearList;
    }
    public static void addTopWearInSortedOrder(LinkedList<TopWear> TWList, TopWear TW) {
        if (TWList.isEmpty() || TW.getPrice() < TWList.getFirst().getPrice()) {
            TWList.addFirst(TW);
        } else {
            int index = 0;
            for (TopWear existingTopWear : TWList) {
                if (TW.getPrice() < existingTopWear.getPrice()) {
                    break;
                }
                index++;
            }
            TWList.add(index, TW);
        }
    }
}
