package com.example.veriy;


import Models.Shampoo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagerShampoo {

    // Dosyaya veri kaydetme
    public static void saveShampooData(List<Shampoo> shampooList, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Shampoo shampoo : shampooList) {
                writer.write(shampoo.getId() + "," +
                        shampoo.getName() + "," +
                        shampoo.getPrice() + "," +
                        shampoo.getAmount() + "," +
                        shampoo.getHairType() + "," +
                        shampoo.getVolume());
                writer.newLine();
            }
        }
    }

    // Dosyadan veri okuma
    public static List<Shampoo> loadShampooData(String fileName) throws IOException {
        List<Shampoo> shampooList = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            // Eğer dosya yoksa, yeni bir dosya oluşturulması sağlanabilir.
            return shampooList;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Shampoo shampoo = new Shampoo(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), 0, "N/A", parts[4], Double.parseDouble(parts[5]));
                    shampooList.add(shampoo);
                }
            }
        }
        return shampooList;
    }
}
