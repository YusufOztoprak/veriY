package com.example.veriy;

import Models.Parfume;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagerParfume {

    // Save the list of parfumes to a file
    public static void saveParfumeData(List<Parfume> parfumeList, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Parfume parfume : parfumeList) {
                writer.write(parfume.getId() + "," +
                        parfume.getName() + "," +
                        parfume.getPrice() + "," +
                        parfume.getAmount() + "," +
                        parfume.getVolume() + "," +
                        parfume.getGenderTarget() + "," +
                        parfume.getExpiration_date() + "," +
                        parfume.getUserInstructions());
                writer.newLine();
            }
        }
    }

    public static List<Parfume> loadParfumeData(String fileName) throws IOException {
        List<Parfume> parfumeList = new ArrayList<>();
        File file = new File(fileName);

        // Eğer dosya yoksa, yeni dosya oluştur
        if (!file.exists()) {
            file.createNewFile();
            return parfumeList; // Eğer dosya yoksa boş liste döndürüyoruz.
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    try {
                        Parfume parfume = new Parfume(parts[0], parts[1], Integer.parseInt(parts[2]),
                                Integer.parseInt(parts[3]), Integer.parseInt(parts[6]), parts[7],
                                Double.parseDouble(parts[4]), parts[5]);
                        parfumeList.add(parfume);
                    } catch (NumberFormatException e) {
                        System.out.println("Veri okuma hatası: " + e.getMessage() + " (Satır: " + line + ")");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
            throw e; // Hatayı yeniden fırlat
        }

        return parfumeList;
    }

}
