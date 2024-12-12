package com.example.veriy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class KayitOlEkranı {

    @FXML
    private TextField yeniKullaniciAdiField;

    @FXML
    private PasswordField yeniSifreField;

    @FXML
    private Label mesajLabel;

    @FXML
    private void handleKayitOl() {
        String kullaniciAdi = yeniKullaniciAdiField.getText();
        String sifre = yeniSifreField.getText();

        if (kullaniciAdi.isEmpty() || sifre.isEmpty()) {
            mesajLabel.setText("Kullanıcı adı ve şifre boş olamaz!");
            return;
        }

        if (KullaniciVeritabani.ekle(kullaniciAdi, sifre)) {
            mesajLabel.setText("Kayıt başarılı!");
        } else {
            mesajLabel.setText("Kullanıcı adı zaten mevcut!");
        }
    }
}

