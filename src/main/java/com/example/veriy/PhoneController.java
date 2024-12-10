package com.example.veriy;

import Models.Phone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhoneController {

    @FXML
    private TableView<Phone> productTable;
    @FXML
    private TableColumn<Phone, String> idColumn;
    @FXML
    private TableColumn<Phone, String> nameColumn;
    @FXML
    private TableColumn<Phone, Integer> priceColumn;
    @FXML
    private TableColumn<Phone, Integer> amountColumn;
    @FXML
    private TableColumn<Phone, Integer> ramColumn;
    @FXML
    private TableColumn<Phone, Integer> storageColumn;
    @FXML
    private TableColumn<Phone, String> cpuColumn;
    @FXML
    private TableColumn<Phone, Integer> warrantyColumn;
    @FXML
    private TableColumn<Phone, Boolean> fiveGSupportColumn;
    @FXML
    private TableColumn<Phone, Integer> numberOfCamerasColumn;
    @FXML
    private TableColumn<Phone, Boolean> fastChargingColumn;

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField amountField;
    @FXML
    private TextField ramField;
    @FXML
    private TextField storageField;
    @FXML
    private TextField cpuField;
    @FXML
    private TextField warrantyField;
    @FXML
    private TextField fiveGSupportField;
    @FXML
    private TextField numberOfCamerasField;
    @FXML
    private TextField fastChargingField;

    private ObservableList<Phone> phoneList = FXCollections.observableArrayList();
    private final String dataFile = "Phone.txt";

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPrice()).asObject());
        amountColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAmount()).asObject());
        ramColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getRam()).asObject());
        storageColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getStorage()).asObject());
        cpuColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCpu()));
        warrantyColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getWarranty()).asObject());
        fiveGSupportColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleBooleanProperty(data.getValue().isFiveGsupport()).asObject());
        numberOfCamerasColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getNumberofCameras()).asObject());
        fastChargingColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleBooleanProperty(data.getValue().isFastCharging()).asObject());

        productTable.setItems(phoneList);

        loadProducts();
    }

    @FXML
    private void beforeScene(){

    }

    @FXML
    private void addPhone() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            int price = Integer.parseInt(priceField.getText());
            int amount = Integer.parseInt(amountField.getText());
            int ram = Integer.parseInt(ramField.getText());
            int storage = Integer.parseInt(storageField.getText());
            String cpu = cpuField.getText();
            int warranty = Integer.parseInt(warrantyField.getText());
            boolean fiveGSupport = Boolean.parseBoolean(fiveGSupportField.getText());
            int numberOfCameras = Integer.parseInt(numberOfCamerasField.getText());
            boolean fastCharging = Boolean.parseBoolean(fastChargingField.getText());

            Phone phone = new Phone(id, name, price, amount, ram, storage, cpu, warranty, "Brand", fiveGSupport, numberOfCameras, fastCharging);
            phoneList.add(phone);

            saveProducts();

            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid data.");
        }
    }

    @FXML
    private void deletePhone() {
        Phone selectedPhone = productTable.getSelectionModel().getSelectedItem();
        if (selectedPhone != null) {
            phoneList.remove(selectedPhone);
            saveProducts();
        } else {
            showAlert("Selection Error", "No product selected.");
        }
    }

    private void saveProducts() {
        try {
            List<Phone> data = new ArrayList<>(phoneList);
            FileManagerPhone.saveData(data, dataFile);
        } catch (IOException e) {
            showAlert("Save Error", "Failed to save products to file.");
        }
    }

    private void loadProducts() {
        try {
            List<Phone> loadedProducts = FileManagerPhone.loadData(dataFile);
            phoneList.clear();
            phoneList.addAll(loadedProducts);
        } catch (IOException e) {
            System.out.println("No existing data found, starting fresh.");
        }
    }

    private void clearFields() {
        idField.clear();
        nameField.clear();
        priceField.clear();
        amountField.clear();
        ramField.clear();
        storageField.clear();
        cpuField.clear();
        warrantyField.clear();
        fiveGSupportField.clear();
        numberOfCamerasField.clear();
        fastChargingField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
