package utils;

import Models.Phone;
import java.io.*;
import java.util.*;

public class FileManager {

    public static <T> List<T> loadData(String fileName, Class<T> type) throws IOException {
        List<T> dataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (type == Phone.class && data.length == 7) { // 7 parametre: id, name, price, amount, 5G, cameras, fastCharging
                    boolean fiveG = Boolean.parseBoolean(data[4]);
                    int cameras = Integer.parseInt(data[5]);
                    boolean fastCharging = Boolean.parseBoolean(data[6]);
                    Phone phone = new Phone(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), 0, 0, "N/A", 0, "N/A", fiveG, cameras, fastCharging);
                    dataList.add((T) phone);
                }
            }
        }
        return dataList;
    }

    public static <T> void saveData(List<T> dataList, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (T data : dataList) {
                String line = null;
                if (data instanceof Phone) {
                    Phone phone = (Phone) data;
                    line = phone.getId() + "," + phone.getName() + "," + phone.getPrice() + "," + phone.getAmount() + ","
                            + phone.isFiveGsupport() + "," + phone.getNumberofCameras() + "," + phone.isFastCharging();
                }
                if (line != null) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
    }
}
