package com.example.veriy;

import Models.Parfume;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ParfumeController {

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
    private TableColumn<Parfume, Double> alcoholContentColumn;

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
    @FXML
    private TextField genderTargetField;
    @FXML
    private TextField alcoholContentField;

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
        alcoholContentColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getAlcoholContent()).asObject());

        // TableView'e liste bağla
        parfumeTable.setItems(parfumeList);
    }

    @FXML
    private void addParfume() {
        // method implementation
        try {
            String id = idField.getText();
            String name = nameField.getText();
            int price = Integer.parseInt(priceField.getText());
            int amount = Integer.parseInt(amountField.getText());
            double volume = Double.parseDouble(volumeField.getText());
            String genderTarget = genderTargetField.getText();
            double alcoholContent = Double.parseDouble(alcoholContentField.getText());

            Parfume newParfume = new Parfume(id, name, price, amount, 0, "N/A", "N/A", volume, genderTarget, alcoholContent);
            parfumeList.add(newParfume);

            // Alanları temizle
            idField.clear();
            nameField.clear();
            priceField.clear();
            amountField.clear();
            volumeField.clear();
            genderTargetField.clear();
            alcoholContentField.clear();
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid data.");
        }
    }

    @FXML
    private void handleDeleteParfume() {
        Parfume selectedParfume = parfumeTable.getSelectionModel().getSelectedItem();
        if (selectedParfume != null) {
            parfumeList.remove(selectedParfume);
        } else {
            showAlert("Selection Error", "No parfume selected.");
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
