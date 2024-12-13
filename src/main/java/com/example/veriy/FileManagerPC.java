package com.example.veriy;

import Models.PC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManagerPC {

    // Veriyi dosyaya kaydetme metodu
    public static void savePCData(List<PC> pcList, String fileName) throws IOException {
        Path path = Paths.get(fileName);  // Dosya adını parametre olarak alıyoruz
        List<String> lines = new ArrayList<>();
        for (PC pc : pcList) {
            String line = String.format("%s;%s;%d;%d;%d;%d;%s;%d;%d",
                    pc.getId(),
                    pc.getName(),
                    pc.getPrice(),
                    pc.getAmount(),
                    pc.getRam(), // RAM değeri
                    pc.getStorage(), // Storage değeri
                    pc.getCpu(),
                    pc.getWarranty(),
                    pc.getEkranboyutu());
            lines.add(line);
        }
        Files.write(path, lines);
    }

    // Veriyi dosyadan yükleme metodu
    public static List<PC> loadPCData(String fileName) throws IOException {
        Path path = Paths.get(fileName);  // Dosya adı parametre olarak alınıyor
        List<PC> pcList = new ArrayList<>();

        // Dosya yoksa boş bir liste döndür
        if (!Files.exists(path)) {
            return pcList;
        }

        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            String[] parts = line.split(";");
            if (parts.length == 9) { // Her veri doğru şekilde ayrılmışsa
                try {
                    // RAM ve Storage değerlerini doğrulama
                    int ram = Integer.parseInt(parts[4]);
                    int storage = Integer.parseInt(parts[5]);

                    // Geçerli RAM ve Storage değerlerini kontrol et
                    if (!isValidRam(ram) || !isValidStorage(storage)) {
                        System.out.println("Invalid RAM or Storage value: " + line);
                        continue;  // Geçersizse bu satırı geç
                    }

                    // PC nesnesi oluşturuluyor
                    PC pc = new PC(
                            parts[0], // id
                            parts[1], // name
                            Integer.parseInt(parts[2]), // price
                            Integer.parseInt(parts[3]), // amount
                            ram, // ram
                            storage, // storage
                            parts[6], // cpu
                            Integer.parseInt(parts[7]), // warranty
                            Integer.parseInt(parts[8]) // ekranboyutu
                    );
                    pcList.add(pc);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid data format: " + line);
                }
            }
        }
        return pcList;
    }

    // RAM için geçerli değerleri kontrol et
    private static boolean isValidRam(int ram) {
        return ram == 8 || ram == 16 || ram == 32 || ram == 64; // 8'in üsleri
    }

    // Storage için geçerli değerleri kontrol et
    private static boolean isValidStorage(int storage) {
        return storage == 128 || storage == 256 || storage == 512 || storage == 1024; // 8'in üsleri
    }
}
