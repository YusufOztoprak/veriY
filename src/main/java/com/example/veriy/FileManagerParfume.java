package com.example.veriy;

import Models.Parfume;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileManagerParfume {

    public static void addParfumeInSortedOrder(LinkedList<Parfume> parfumeList, Parfume parfume) {
        // Liste boşsa veya yeni parfüm en düşük fiyatlıysa en başa ekle
        if (parfumeList.isEmpty() || parfume.getPrice() < parfumeList.getFirst().getPrice()) {
            parfumeList.addFirst(parfume);
            return; // İşlem tamamlandı, metottan çık
        }

        // Doğru pozisyonu bul ve ekle
        int index = 0;
        for (Parfume p : parfumeList) {
            if (parfume.getPrice() < p.getPrice()) {
                break; // Yeni parfümün fiyatı mevcut parfümden düşük, ekleme pozisyonu bulundu
            }
            index++;
        }
        parfumeList.add(index, parfume); // Pozisyonda ekle
    }


    // Save method to write parfume data to a file
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

    // Load method to read parfume data from a file and maintain sorted order
    public static LinkedList<Parfume> loadParfumeData(String fileName) throws IOException {
        LinkedList<Parfume> parfumeList = new LinkedList<>();
        File file = new File(fileName);

        // Eğer dosya yoksa yeni dosya oluştur
        if (!file.exists()) {
            file.createNewFile();
            return parfumeList;
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
                        // Parfümü sıralı şekilde listeye ekle
                        addParfumeInSortedOrder(parfumeList, parfume);
                    } catch (NumberFormatException e) {
                        System.out.println("Veri okuma hatası: " + e.getMessage() + " (Satır: " + line + ")");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
            throw e;
        }

        return parfumeList;
    }
}
