package com.example.veriy;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class KisiselBakimController {

    @FXML
    private Button ParfumeButton;
    @FXML
    private Button ShampooButton;
    @FXML
    private Button  BackButton;

    @FXML
    private void openParfumeScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/veriy/Parfume.fxml"));
        Scene parfumeScene = new Scene(loader.load());
        Stage stage = (Stage) ParfumeButton.getScene().getWindow();
        stage.setScene(parfumeScene);
        stage.show();
    }

    @FXML
    private void openShampooScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/veriy/Shampoo.fxml"));
        Scene shampooScene = new Scene(loader.load());
        Stage stage = (Stage) ShampooButton.getScene().getWindow();
        stage.setScene(shampooScene);
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

}