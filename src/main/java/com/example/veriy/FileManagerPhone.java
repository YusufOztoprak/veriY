package com.example.veriy;

/*import Models.Phone;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagerPhone {

    public static void saveData(List<Phone> phones, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Phone phone : phones) {
                String line = String.format("%s;%s;%d;%d;%b;%d;%b",
                        phone.getId(),
                        phone.getName(),
                        phone.getPrice(),
                        phone.getAmount(),
                        phone.isFiveGsupport(),
                        phone.getNumberofCameras(),
                        phone.isFastCharging());
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public static List<Phone> loadData(String fileName) throws IOException {
        List<Phone> phones = new ArrayList<>();
        File file = new File(fileName);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length == 7) {
                        Phone phone = new Phone(
                                parts[0], // ID
                                parts[1], // Name
                                Integer.parseInt(parts[2]), // Price
                                Integer.parseInt(parts[3]), // Amount
                                0, // Unused field
                                0, // Unused field
                                "N/A", // Unused field
                                0, // Unused field
                                "N/A", // Unused field
                                Boolean.parseBoolean(parts[4]), // 5G support
                                Integer.parseInt(parts[5]), // Number of cameras
                                Boolean.parseBoolean(parts[6]) // Fast charging
                        );
                        phones.add(phone);
                    }
                }
            }
        }

        return phones;
    }
}*/


import Models.Phone;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagerPhone {

    public static void saveData(List<Phone> phones, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(phones);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Phone> loadData(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Phone>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Data format error", e);
        }
    }
}

