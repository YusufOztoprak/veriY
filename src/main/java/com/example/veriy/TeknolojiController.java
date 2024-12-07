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
    private void openTelefonScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/veriy/Phone.fxml"));
        Scene telefonScene = new Scene(loader.load());
        Stage stage = (Stage) TelefonButton.getScene().getWindow();
        stage.setScene(telefonScene);
        stage.show();
    }

    @FXML
    private void openPCScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/veriy/Pc.fxml"));
        Scene pcScene = new Scene(loader.load());
        Stage stage = (Stage) PCButton.getScene().getWindow();
        stage.setScene(pcScene);
        stage.show();
    }
}
