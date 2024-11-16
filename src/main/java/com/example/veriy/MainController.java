package com.example.veriy;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class MainController {

    @FXML
    private Button technologyButton;
    @FXML
    private Button personalCareButton;
    @FXML
    private Button clothingButton;

    @FXML
    private void openTechnologyScene() throws IOException {
        // Teknoloji kategorisi ekranını yükle
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TechnologyView.fxml"));
        Scene technologyScene = new Scene(loader.load());
        Stage stage = (Stage) technologyButton.getScene().getWindow();
        stage.setScene(technologyScene);
        stage.show();
    }

    @FXML
    private void openPersonalCareScene() throws IOException {
        // Kişisel bakım kategorisi ekranını yükle
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PersonalCareView.fxml"));
        Scene personalCareScene = new Scene(loader.load());
        Stage stage = (Stage) personalCareButton.getScene().getWindow();
        stage.setScene(personalCareScene);
        stage.show();
    }

    @FXML
    private void openClothingScene() throws IOException {
        // Giyim kategorisi ekranını yükle
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClothingView.fxml"));
        Scene clothingScene = new Scene(loader.load());
        Stage stage = (Stage) clothingButton.getScene().getWindow();
        stage.setScene(clothingScene);
        stage.show();
    }
}
