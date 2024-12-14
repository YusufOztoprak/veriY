package com.example.veriy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.plaf.basic.BasicButtonUI;
import java.io.IOException;

public class KayitOlEkranı {

    @FXML
    private TextField yeniKullaniciAdiField;

    @FXML
    private PasswordField yeniSifreField;

    @FXML
    private Label mesajLabel;
    @FXML
    private Button BackButton;

    @FXML
    private void handleKayitOl() {
        String kullaniciAdi = yeniKullaniciAdiField.getText();
        if (kullaniciAdi.length() > 20){
            mesajLabel.setText("kullanıcı adı uzunluğu en fazla 20 karakter içermelidir...");
            yeniKullaniciAdiField.clear();
            return;
        }
        String sifre = yeniSifreField.getText();
        if (sifre.length() > 10){
            mesajLabel.setText("kullanıcı şifresi en fazla 10 karakter uzunluğunda olmalıdır...");
            yeniSifreField.clear();
            return;
        }

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

    @FXML
    private void goToMainScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/veriy/GirisEkranı.fxml"));
        Scene mainScene = new Scene(loader.load());
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }
}

