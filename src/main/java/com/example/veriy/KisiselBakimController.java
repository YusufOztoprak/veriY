package com.example.veriy;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class KisiselBakimController {

    @FXML
    private Button ShampooButton;
    @FXML
    private Button ParfumeButton;

    @FXML
    private void openShampooScene() throws IOException {
        // Teknoloji kategorisi ekranını yükle
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Shampoo.fxml"));
        Scene ShampooScene = new Scene(loader.load());
        Stage stage = (Stage) ShampooButton.getScene().getWindow();
        stage.setScene(ShampooScene);
        stage.show();
    }

    @FXML
    private void openParfumeScene() throws IOException {
        // Kişisel bakım kategorisi ekranını yükle
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Parfume.fxml"));
        Scene ParfumeScene = new Scene(loader.load());
        Stage stage = (Stage) ParfumeButton.getScene().getWindow();
        stage.setScene(ParfumeScene);
        stage.show();
    }
}
