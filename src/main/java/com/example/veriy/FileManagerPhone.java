package com.example.veriy;

import Models.Phone;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileManagerPhone {

    /**
     * Saves a list of Phone objects to a file.
     *
     * @param phoneList the list of Phone objects to save
     * @param fileName  the name of the file to save to
     * @throws IOException if an I/O error occurs
     */
    public static void savePhoneData(List<Phone> phoneList, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Phone phone : phoneList) {
                writer.write(String.join(",",
                        phone.getId(),
                        phone.getName(),
                        String.valueOf(phone.getPrice()),
                        String.valueOf(phone.getAmount()),
                        String.valueOf(phone.getRam()),
                        String.valueOf(phone.getStorage()),
                        phone.getCpu(),
                        String.valueOf(phone.getWarranty()),
                        String.valueOf(phone.getNumberofCameras()),
                        String.valueOf(phone.isFiveGsupport())
                ));
                writer.newLine();
            }
        }
    }

    /**
     * Loads Phone objects from a file.
     *
     * @param fileName the name of the file to load from
     * @return a list of Phone objects
     * @throws IOException if an I/O error occurs
     */
    public static List<Phone> loadProducts(String fileName) throws IOException {
        List<Phone> phoneList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 10) {
                    try {
                        Phone phone = new Phone(
                                parts[0], // id
                                parts[1], // name
                                Integer.parseInt(parts[2]), // price
                                Integer.parseInt(parts[3]), // amount
                                Integer.parseInt(parts[4]), // ram
                                Integer.parseInt(parts[5]), // storage
                                parts[6], // cpu
                                Integer.parseInt(parts[7]), // warranty
                                Integer.parseInt(parts[8]), // number of cameras
                                Boolean.parseBoolean(parts[9]) // 5G support
                        );
                        phoneList.add(phone);
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping malformed line: " + line);
                    }
                } else {
                    System.err.println("Skipping incomplete line: " + line);
                }
            }
        }
        return phoneList;
    }

    /**
     * Adds a Phone object to a linked list in sorted order by price.
     *
     * @param phoneList the linked list of Phone objects
     * @param phone     the Phone object to add
     */
    public static void addPhoneInSortedOrder(LinkedList<Phone> phoneList, Phone phone) {
        if (phoneList.isEmpty() || phone.getPrice() < phoneList.getFirst().getPrice()) {
            phoneList.addFirst(phone);
            return;
        }

        int index = 0;
        for (Phone p : phoneList) {
            if (phone.getPrice() < p.getPrice()) {
                break;
            }
            index++;
        }
        phoneList.add(index, phone);
    }
}



