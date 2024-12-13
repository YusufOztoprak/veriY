package com.example.veriy;


import Models.PC;
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

public class PCController {

    @FXML
    private Button backButton;
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
    private TableColumn<PC, Integer> screenSizeColumn;
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
    private ComboBox<Integer> ramField; // ComboBox for RAM
    @FXML
    private ComboBox<Integer> storageField; // ComboBox for Storage
    @FXML
    private ComboBox<String> cpuField;
    @FXML
    private TextField warrantyField;

    private ObservableList<PC> pcList = FXCollections.observableArrayList();
    private final String dataFile = "PC.txt";

    @FXML
    public void initialize() {
        // Set TableView columns with PC object properties
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

        // Load products from file
        loadProducts();

        // Populate ComboBoxes with predefined values
        ramField.setItems(FXCollections.observableArrayList(8, 16, 32, 64));
        storageField.setItems(FXCollections.observableArrayList(128, 256, 512, 1024));

        // Ensure the data is saved after the table is initialized
        saveProducts();
    }

    // Add a new product to the list and save it to file
    @FXML
    private void handleAddProduct() {
        try {
            String id = idField.getText();
            if (id == null || id.isEmpty()) {
                showAlert("Input Error", "Id cannot be empty.");
                return;
            }

            String name = nameField.getText();
            if (name == null || name.isEmpty()) {
                showAlert("Input Error", "Name cannot be empty.");
                return;
            }

            int price = Integer.parseInt(priceField.getText());
            if (price <= 0) {
                showAlert("Input Error", "Please enter a valid price.");
                return;
            }

            int amount = Integer.parseInt(amountField.getText());
            if (amount <= 0) {
                showAlert("Input Error", "Please enter a valid amount.");
                return;
            }

            int screenSize = Integer.parseInt(screenSizeField.getText());
            if (screenSize <= 0) {
                showAlert("Input Error", "Please enter a valid screen size.");
                return;
            }

            // Get selected values from ComboBoxes
            Integer ram = ramField.getValue();
            if (ram == null || ram <= 0) {
                showAlert("Input Error", "Please select a valid RAM size.");
                return;
            }

            Integer storage = storageField.getValue();
            if (storage == null || storage <= 0) {
                showAlert("Input Error", "Please select a valid storage size.");
                return;
            }

            String cpu = cpuField.getValue();
            if (cpu == null || cpu.isEmpty()) {
                showAlert("Input Error", "Please select a valid CPU.");
                return;
            }

            int warranty = Integer.parseInt(warrantyField.getText());
            if (warranty <= 0) {
                showAlert("Input Error", "Please enter a valid warranty.");
                return;
            }

            // Create new PC object and add to list
            PC pc = new PC(id, name, price, amount, ram, storage, cpu, warranty, screenSize);
            pcList.add(pc);

            // Save updated product list to file
            saveProducts();
            clearFields(false); // Clear the form, without resetting ComboBox values
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid data.");
        }
    }

    // Delete selected product from the list and save to file
    @FXML
    private void handleDeleteProduct() {
        PC selectedPC = productTable.getSelectionModel().getSelectedItem();
        if (selectedPC != null) {
            pcList.remove(selectedPC);
            saveProducts(); // Save after deletion
        } else {
            showAlert("Selection Error", "No product selected.");
        }
    }

    // Save products to the PC.txt file
    private void saveProducts() {
        try {
            FileManagerPC.savePCData(new ArrayList<>(pcList), dataFile);
        } catch (IOException e) {
            showAlert("Save Error", "Failed to save products to file.");
        }
    }

    // Load products from the PC.txt file
    private void loadProducts() {
        try {
            List<PC> loadedProducts = FileManagerPC.loadPCData(dataFile);
            pcList.clear();
            pcList.addAll(loadedProducts);
        } catch (IOException e) {
            System.out.println("No existing data found, starting fresh.");
        }
    }

    // Show alert message
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Clear the form fields
    private void clearFields(boolean clearComboBoxes) {
        idField.clear();
        nameField.clear();
        priceField.clear();
        amountField.clear();
        screenSizeField.clear();
        if (clearComboBoxes) {
            ramField.setValue(null);
            storageField.setValue(null);
            cpuField.setValue(null);
        }
        warrantyField.clear();
    }

    // Navigate back to the main scene
    @FXML
    private void goToMainScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/veriy/Teknoloji.fxml"));
        Scene mainScene = new Scene(loader.load());
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }
}
