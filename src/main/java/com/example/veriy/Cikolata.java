package com.example.veriy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.IOException;

public class Cikolata extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AnaEkran.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Ana Ekran");
        primaryStage.setWidth(600);
        primaryStage.setHeight(800);
        primaryStage.show();
        primaryStage.setFullScreen(true);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
