package com.example.veriy;

import Models.PC;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileManagerPC {

    /**
     * Adds a PC object to the list in sorted order based on price.
     *
     * @param pcList the list to insert into
     * @param pc     the PC object to add
     */
    public static void addPCInSortedOrder(LinkedList<PC> pcList, PC pc) {
        if (pcList.isEmpty() || pc.getPrice() < pcList.getFirst().getPrice()) {
            pcList.addFirst(pc);
        } else {
            int index = 0;
            for (PC existingPC : pcList) {
                if (pc.getPrice() < existingPC.getPrice()) {
                    break;
                }
                index++;
            }
            pcList.add(index, pc);
        }
    }

    /**
     * Saves the list of PC objects to a file.
     *
     * @param pcList   the list of PC objects to save
     * @param fileName the name of the file
     * @throws IOException if an I/O error occurs
     */
    public static void savePCData(List<PC> pcList, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (PC pc : pcList) {
                writer.write(String.join(",",
                        pc.getId(),
                        pc.getName(),
                        String.valueOf(pc.getPrice()),
                        String.valueOf(pc.getAmount()),
                        String.valueOf(pc.getRam()),
                        String.valueOf(pc.getStorage()),
                        pc.getCpu(),
                        String.valueOf(pc.getWarranty()),
                        String.valueOf(pc.getEkranboyutu())
                ));
                writer.newLine();
            }
        }
    }

    /**
     * Loads PC objects from a file and returns a sorted LinkedList.
     *
     * @param fileName the name of the file to read from
     * @return a sorted LinkedList of PC objects
     * @throws IOException if an I/O error occurs
     */
    public static LinkedList<PC> loadPCData(String fileName) throws IOException {
        LinkedList<PC> pcList = new LinkedList<>();
        File file = new File(fileName);

        if (!file.exists()) {
            if (file.createNewFile()) {
                System.out.println("File created: " + fileName);
            }
            return pcList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 9) {
                    try {
                        PC pc = new PC(
                                parts[0],
                                parts[1],
                                Integer.parseInt(parts[2]),
                                Integer.parseInt(parts[3]),
                                Integer.parseInt(parts[4]),
                                Integer.parseInt(parts[5]),
                                parts[6],
                                Integer.parseInt(parts[7]),
                                Integer.parseInt(parts[8])
                        );
                        addPCInSortedOrder(pcList, pc);
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping invalid line: " + line);
                    }
                } else {
                    System.err.println("Invalid data format: " + line);
                }
            }
        }

        return pcList;
    }
}


