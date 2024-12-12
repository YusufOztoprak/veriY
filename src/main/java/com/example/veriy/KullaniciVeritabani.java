package com.example.veriy;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class KullaniciVeritabani {
    private static final String DOSYA_ADI = "kayit.txt";
    public static final Map<String, String> kullanicilar = new HashMap<>();

    static {
        dosyadanOku();
    }

    private static void dosyadanOku() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DOSYA_ADI))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                String[] parcala = satir.split(":");
                if (parcala.length == 2) {
                    kullanicilar.put(parcala[0], parcala[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Kayıt dosyası okunamadı: " + e.getMessage());
        }
    }

    private static void dosyayaYaz(String kullaniciAdi, String sifre) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_ADI, true))) {
            writer.write(kullaniciAdi + ":" + sifre);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Kayıt dosyasına yazılamadı: " + e.getMessage());
        }
    }

    public static boolean dogrula(String kullaniciAdi, String sifre) {
        return kullanicilar.containsKey(kullaniciAdi) && kullanicilar.get(kullaniciAdi).equals(sifre);
    }

    public static boolean ekle(String kullaniciAdi, String sifre) {
        if (!kullanicilar.containsKey(kullaniciAdi)) {
            kullanicilar.put(kullaniciAdi, sifre);
            dosyayaYaz(kullaniciAdi, sifre);
            return true;
        }
        return false;
    }
}

