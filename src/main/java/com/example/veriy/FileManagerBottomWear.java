package com.example.veriy;

import Models.BottomWear;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileManagerBottomWear {
    public static void saveBottomWearData(List<BottomWear> BottomWearList, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (BottomWear bottomWear : BottomWearList) {
                writer.write(bottomWear.getId() + "," +
                        bottomWear.getName() + "," +
                        bottomWear.getPrice() + "," +
                        bottomWear.getAmount() + "," +
                        bottomWear.getSize() + "," +
                        bottomWear.getColor() + "," +
                        bottomWear.getCloth() + "," +
                        bottomWear.getGender() + "," +
                        bottomWear.hasPockets());
                writer.newLine();
            }
        }
    }

    public static List<BottomWear> loadBottomWearData(String fileName) throws IOException {
        LinkedList<BottomWear> BottomWearList = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 9) {
                    BottomWear bottomWear = new BottomWear(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
                            parts[4], parts[5], parts[6], parts[7], Boolean.parseBoolean(parts[8]));
                    addBottomWearInSortedOrder(BottomWearList,bottomWear);
                }
            }
        }
        return BottomWearList;
    }
    public static void addBottomWearInSortedOrder(LinkedList<BottomWear> BWList, BottomWear BW) {
        if (BWList.isEmpty() || BW.getPrice() < BWList.getFirst().getPrice()) {
            BWList.addFirst(BW);
        } else {
            int index = 0;
            for (BottomWear existingBottomWear : BWList) {
                if (BW.getPrice() < existingBottomWear.getPrice()) {
                    break;
                }
                index++;
            }
            BWList.add(index, BW);
        }
    }
}
