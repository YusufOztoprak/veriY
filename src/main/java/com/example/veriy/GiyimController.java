package com.example.veriy;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class GiyimController {

    @FXML
    private Button TopButton;
    @FXML
    private Button BottomButton;

    @FXML
    private Button BackButton;

    @FXML
    private void openTopScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/veriy/TopWear.fxml"));
        Scene TopScene = new Scene(loader.load());
        Stage stage = (Stage) TopButton.getScene().getWindow();
        stage.setScene(TopScene);
        stage.show();
    }
    @FXML
    private void goToScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/veriy/AnaEkran.fxml"));
        Scene mainScene = new Scene(loader.load());
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }

    @FXML
    private void openBottomScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/veriy/BottomWear.fxml"));
        Scene BottomScene = new Scene(loader.load());
        Stage stage = (Stage) BottomButton.getScene().getWindow();
        stage.setScene(BottomScene);
        stage.show();
    }
}
