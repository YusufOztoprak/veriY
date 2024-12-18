package com.example.veriy;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.awt.*;
import java.io.IOException;

public class GirisEkranı {
    @FXML
    private TextField kullaniciAdiField;

    @FXML
    private PasswordField sifreField;

    @FXML
    private Label mesajLabel;
    @FXML


    public void initialize() {
        // Kullanıcı adı için 20 karakter sınırı
        addCharacterLimit(kullaniciAdiField, 20);

        // Şifre için 15 karakter sınırı
        addCharacterLimit(sifreField, 15);
    }

    @FXML
    private void handleGiris(ActionEvent event) {
        String kullaniciAdi = kullaniciAdiField.getText();
        String sifre = sifreField.getText();

        if (KullaniciVeritabani.dogrula(kullaniciAdi, sifre)) {
            mesajLabel.setText("Giriş başarılı, ana ekrana yönlendiriliyorsunuz.");
            // Ana ekran sahnesine geçiş
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AnaEkran.fxml"));
                //MainController mainController = loader.getController();
                //mainController.getName(kullaniciAdiField.getText());
                Scene anaEkran = new Scene(loader.load());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(anaEkran);
                stage.setTitle("Ana Ekran");
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                mesajLabel.setText("Ana ekrana geçiş yapılamadı: " + e.getMessage());
            }
        } else if (kullaniciAdiField.getText().isEmpty()|| sifreField.getText().isEmpty()) {
            mesajLabel.setText("kullanıcı adı veya şifre boş geçilemez");
        } else if (KullaniciVeritabani.kullanicilar.containsKey(kullaniciAdi)) {
            mesajLabel.setText("Şifre hatalı!");
            sifreField.clear();
        } else {
            mesajLabel.setText("Kullanıcı adı bulunamadı!");
            kullaniciAdiField.clear();
            sifreField.clear();
        }
    }

    @FXML
    private void handleKayitOl(ActionEvent event) {
        // Kayıt ol ekranına geçiş
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KayitOlEkranı.fxml"));
            Scene kayitOlEkrani = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(kayitOlEkrani);
            stage.setTitle("Kayıt Ol");
            stage.show();
        } catch (IOException e) {
            mesajLabel.setText("Kayıt ekranına geçiş yapılamadı: " + e.getMessage());
        }
    }
    private void addCharacterLimit(TextField textField, int maxLength) {
        // TextField'in metin değişimini dinleyen bir listener ekliyoruz
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Yeni metin belirtilen uzunluğu aşıyorsa
            if (newValue != null && newValue.length() > maxLength) {
                // Eski değeri geri yükler (sınıra uymayan değişikliği reddeder)
                textField.setText(oldValue);
            }
        });
    }

}
