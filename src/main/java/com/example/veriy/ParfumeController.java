package com.example.veriy;

import Models.PC;
import Models.Parfume;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParfumeController {
    @FXML
    private Button BackButton;

    @FXML
    private TableView<Parfume> parfumeTable;
    @FXML
    private TableColumn<Parfume, String> idColumn;
    @FXML
    private TableColumn<Parfume, String> nameColumn;
    @FXML
    private TableColumn<Parfume, Integer> priceColumn;
    @FXML
    private TableColumn<Parfume, Integer> amountColumn;
    @FXML
    private TableColumn<Parfume, Double> volumeColumn;
    @FXML
    private TableColumn<Parfume, String> genderTargetColumn;

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField amountField;
    @FXML
    private TextField volumeField;
    private final String dataFile = "Parfume.txt";
    @FXML
    private ComboBox<String> genderTargetBox;

    private ObservableList<Parfume> parfumeList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // TableView sütunlarını Parfume sınıfındaki özelliklerle eşleştir
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPrice()).asObject());
        amountColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAmount()).asObject());
        volumeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getVolume()).asObject());
        genderTargetColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGenderTarget()));

        // TableView'e liste bağla
        parfumeTable.setItems(parfumeList);

        loadProducts();
    }
    @FXML
    private void goToScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/veriy/KisiselBakim.fxml"));
        Scene mainScene = new Scene(loader.load());
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }

    @FXML
    private void addParfume() {
        // method implementation
        try {
            String id = idField.getText();
            if (id == null || id.isEmpty()) {
                showAlert("Input Error", "Gender target cannot be empty.");
                return;
            }
            String name = nameField.getText();
            int price = Integer.parseInt(priceField.getText());
            int amount = Integer.parseInt(amountField.getText());
            double volume = Double.parseDouble(volumeField.getText());
            String genderTarget = genderTargetBox.getValue();
            if (genderTarget == null || genderTarget.isEmpty()) {
                showAlert("Input Error", "Gender target cannot be empty.");
                return;
            }



            Parfume newParfume = new Parfume(id, name, price, amount, 0, "N/A", "N/A", volume, genderTarget);
            parfumeList.add(newParfume);
            saveProducts();

            // Alanları temizle
            idField.clear();
            nameField.clear();
            priceField.clear();
            amountField.clear();
            volumeField.clear();
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid data.");
        }
    }

    @FXML
    private void handleDeleteParfume() {
        Parfume selectedParfume = parfumeTable.getSelectionModel().getSelectedItem();
        if (selectedParfume != null) {
            parfumeList.remove(selectedParfume);
            saveProducts();
        } else {
            showAlert("Selection Error", "No parfume selected.");
        }
    }

    private void saveProducts() {
        try {
            List<Parfume> data = new ArrayList<>(parfumeList);
            FileManagerParfume.saveParfumeData(data, dataFile); // saveData yerine savePCData kullanılmalı
        } catch (IOException e) {
            showAlert("Save Error", "Failed to save products to file.");
        }
    }

    private void loadProducts() {
        try {
            List<Parfume> loadedProducts = FileManagerParfume.loadParfumeData(dataFile); // loadData yerine loadPCData kullanılmalı
            parfumeList.clear();
            parfumeList.addAll(loadedProducts);
        } catch (IOException e) {
            System.out.println("No existing data found, starting fresh.");
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
