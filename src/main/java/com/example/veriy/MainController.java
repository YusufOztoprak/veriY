package com.example.veriy;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class MainController {
    @FXML
    private Label nameLabel;
    @FXML
    private Button technologyButton;
    @FXML
    private Button personalCareButton;
    @FXML
    private Button clothingButton;

    @FXML Button ExitButton;

    @FXML
    private void openTechnologyScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Teknoloji.fxml"));
        Scene technologyScene = new Scene(loader.load());
        Stage stage = (Stage) technologyButton.getScene().getWindow();
        stage.setScene(technologyScene);
        stage.show();
    }

    public void getName(String name){
        nameLabel.setText(name);
    }
    @FXML
    private void openPersonalCareScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("KisiselBakim.fxml"));
        Scene personalCareScene = new Scene(loader.load());
        Stage stage = (Stage) personalCareButton.getScene().getWindow();
        stage.setScene(personalCareScene);
        stage.show();
    }

    @FXML
    private void openClothingScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Giyim.fxml"));
        Scene clothingScene = new Scene(loader.load());
        Stage stage = (Stage) clothingButton.getScene().getWindow();
        stage.setScene(clothingScene);
        stage.show();
    }

    @FXML
    private void exit(){
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setHeaderText("Are you sure you want to exit?");

        // Kullanıcı yanıtını kontrol ediyoruz
        var result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
