package com.example.veriy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ana Ekran");

        Label label = new Label("Kategorinizi seçiniz:");
        Button teknolojiButton = new Button("Teknoloji");
        Button kisiselBakimButton = new Button("Kişisel Bakım");
        Button giyimButton = new Button("Giyim");

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, teknolojiButton, kisiselBakimButton, giyimButton);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}