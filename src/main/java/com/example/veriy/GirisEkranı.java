package com.example.veriy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.IOException;

public class GirisEkranı {

    @FXML
    private TextField kullaniciAdiField;

    @FXML
    private PasswordField sifreField;

    @FXML
    private Label mesajLabel;

    @FXML
    private void handleGiris(ActionEvent event) {
        String kullaniciAdi = kullaniciAdiField.getText();
        String sifre = sifreField.getText();

        if (KullaniciVeritabani.dogrula(kullaniciAdi, sifre)) {
            mesajLabel.setText("Giriş başarılı, ana ekrana yönlendiriliyorsunuz.");
            // Ana ekran sahnesine geçiş
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AnaEkran.fxml"));
                Scene anaEkran = new Scene(loader.load());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(anaEkran);
                stage.setTitle("Ana Ekran");
                stage.show();
            } catch (IOException e) {
                mesajLabel.setText("Ana ekrana geçiş yapılamadı: " + e.getMessage());
            }
        } else if (KullaniciVeritabani.kullanicilar.containsKey(kullaniciAdi)) {
            mesajLabel.setText("Şifre hatalı!");
        } else {
            mesajLabel.setText("Kullanıcı adı bulunamadı!");
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
}
