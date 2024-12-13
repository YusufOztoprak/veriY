package com.example.veriy;

import Models.Parfume;
import Models.TopWear;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagerParfume {


        // TopWear nesnelerini dosyaya kaydetme
        public static void saveParfumeData(List<Parfume> ParfumeList, String fileName) throws IOException {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (Parfume parfume : ParfumeList) {
                    writer.write(parfume.getId() + "," +
                            parfume.getName() + "," +
                            parfume.getPrice() + "," +
                            parfume.getAmount() + "," +
                            parfume.getExpiration_date()+ "," +
                            parfume.getBrand() + "," +
                            parfume.getUserInstructions() + "," +
                            parfume.getVolume() + "," +
                            parfume.getGenderTarget());
                    writer.newLine();
                }
            }
        }

        // Dosyadan TopWear nesnelerini okuma
        public static List<Parfume> loadParfumeData(String fileName) throws IOException {
            List<Parfume> parfumeList = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 9) {
                        Parfume parfume = new Parfume(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
                                Integer.parseInt(parts[4]), parts[5], parts[6], Double.parseDouble(parts[7]), parts[8]);
                        parfumeList.add(parfume);
                    }
                }
            }
            return parfumeList;
        }

}
