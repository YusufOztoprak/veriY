package com.example.veriy;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class TeknolojiController {

    @FXML
    private Button TelefonButton;
    @FXML
    private Button PCButton;
    @FXML
    private Button TabletButton;

    @FXML
    private void openTelefonScene() throws IOException {
        // Teknoloji kategorisi ekranını yükle
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Telefon.fxml"));
        Scene technologyScene = new Scene(loader.load());
        Stage stage = (Stage) TelefonButton.getScene().getWindow();
        stage.setScene(technologyScene);
        stage.show();
    }

    @FXML
    private void openPCScene() throws IOException {
        // Kişisel bakım kategorisi ekranını yükle
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PC.fxml"));
        Scene personalCareScene = new Scene(loader.load());
        Stage stage = (Stage) PCButton.getScene().getWindow();
        stage.setScene(personalCareScene);
        stage.show();
    }

    @FXML
    private void openTableScene() throws IOException {
        // Giyim kategorisi ekranını yükle
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Table.fxml"));
        Scene clothingScene = new Scene(loader.load());
        Stage stage = (Stage) TabletButton.getScene().getWindow();
        stage.setScene(clothingScene);
        stage.show();
    }
}
