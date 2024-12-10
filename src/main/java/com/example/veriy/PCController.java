package com.example.veriy;

import Models.PC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PCController {

    @FXML
    private TableView<PC> productTable;
    @FXML
    private TableColumn<PC, String> idColumn;
    @FXML
    private TableColumn<PC, String> nameColumn;
    @FXML
    private TableColumn<PC, Integer> priceColumn;
    @FXML
    private TableColumn<PC, Integer> amountColumn;
    @FXML
    private TableColumn<PC, Integer> screenSizeColumn; // `String` yerine `Integer` kullanılmalı
    @FXML
    private TableColumn<PC, Integer> ramColumn;
    @FXML
    private TableColumn<PC, Integer> storageColumn;
    @FXML
    private TableColumn<PC, String> cpuColumn;
    @FXML
    private TableColumn<PC, Integer> warrantyColumn;

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField amountField;
    @FXML
    private TextField screenSizeField;
    @FXML
    private TextField ramField;
    @FXML
    private TextField storageField;
    @FXML
    private TextField cpuField;
    @FXML
    private TextField warrantyField;

    private ObservableList<PC> pcList = FXCollections.observableArrayList();
    private final String dataFile = "PC.txt";

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPrice()).asObject());
        amountColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAmount()).asObject());
        screenSizeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getEkranboyutu()).asObject());
        ramColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getRam()).asObject());
        storageColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getStorage()).asObject());
        cpuColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCpu()));
        warrantyColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getWarranty()).asObject());

        productTable.setItems(pcList);

        // Verileri dosyadan yükle
        loadProducts();
    }

    @FXML
    private void handleAddProduct() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            int price = Integer.parseInt(priceField.getText());
            int amount = Integer.parseInt(amountField.getText());
            int ekranboyutu = Integer.parseInt(screenSizeField.getText());
            int ram = Integer.parseInt(ramField.getText());
            int storage = Integer.parseInt(storageField.getText());
            String cpu = cpuField.getText();
            int warranty = Integer.parseInt(warrantyField.getText());

            PC pc = new PC(id, name, price, amount, ram, storage, cpu, warranty, ekranboyutu);
            pcList.add(pc);

            saveProducts();

            // Alanları temizle
            idField.clear();
            nameField.clear();
            priceField.clear();
            amountField.clear();
            screenSizeField.clear();
            ramField.clear();
            storageField.clear();
            cpuField.clear();
            warrantyField.clear();
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid data.");
        }
    }

    @FXML
    private void handleDeleteProduct() {
        PC selectedPC = productTable.getSelectionModel().getSelectedItem();
        if (selectedPC != null) {
            pcList.remove(selectedPC);
            saveProducts();
        } else {
            showAlert("Selection Error", "No product selected.");
        }
    }

    private void saveProducts() {
        try {
            List<PC> data = new ArrayList<>(pcList);
            FileManagerPC.savePCData(data, dataFile); // saveData yerine savePCData kullanılmalı
        } catch (IOException e) {
            showAlert("Save Error", "Failed to save products to file.");
        }
    }

    private void loadProducts() {
        try {
            List<PC> loadedProducts = FileManagerPC.loadPCData(dataFile); // loadData yerine loadPCData kullanılmalı
            pcList.clear();
            pcList.addAll(loadedProducts);
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
