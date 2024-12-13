package com.example.veriy;


import Models.Phone;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagerPhone{

    // Phone nesnelerini dosyaya kaydetme
    public static void savePhoneData(List<Phone> Phonelist, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Phone phone : Phonelist) {
                writer.write(phone.getId() + "," +
                        phone.getName() + "," +
                        phone.getPrice() + "," +
                        phone.getAmount() + "," +
                        phone.getRam() + "," +
                        phone.getStorage() + "," +
                        phone.getCpu() + "," +
                        phone.getWarranty() + "," +
                        phone.getNumberofCameras() + "," +
                        phone.isFiveGsupport());
                writer.newLine();
            }
        }
    }

    // Dosyadan TopWear nesnelerini okuma
    public static List<Phone> loadProducts(String fileName) throws IOException {
        List<Phone> phoneList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 10) {
                    Phone phone = new Phone(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), parts[6], Integer.parseInt(parts[7]), Integer.parseInt(parts[8]),Boolean.parseBoolean(parts[9]));
                    phoneList.add(phone);
                }
            }
        }
        return phoneList;
    }
}


