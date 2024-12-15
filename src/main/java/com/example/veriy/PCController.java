package com.example.veriy;

import Models.PC;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
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
    private ComboBox<Integer> ramField;
    @FXML
    private ComboBox<Integer> storageField;
    @FXML
    private ComboBox<String> cpuField;
    @FXML
    private TextField warrantyField;

    private final LinkedList<PC> pcList = new LinkedList<>();
    private final String dataFile = "PC.txt";

    public void initialize() {
        // Set TableView columns with PC object properties
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        priceColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPrice()).asObject());
        amountColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getAmount()).asObject());
        screenSizeColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getEkranboyutu()).asObject());
        ramColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getRam()).asObject());
        storageColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getStorage()).asObject());
        cpuColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCpu()));
        warrantyColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getWarranty()).asObject());

        // Populate TableView with initial data
        updateTableView();

        // Populate ComboBoxes with predefined values
        ramField.setItems(FXCollections.observableArrayList(8, 16, 32, 64));
        storageField.setItems(FXCollections.observableArrayList(128, 256, 512, 1024));

        // Load products from file
        loadProducts();
    }

    @FXML
    private void handleAddProduct(){
        try {
            String id = idField.getText();
            if (id == null || id.isEmpty()) {
                showAlert("Input Error", "Id cannot be empty.");
                return;
            }
            for (PC existingPc : pcList) {
                if (existingPc.getId().equals(id)) {
                    showAlert("Duplicate ID Error", "A PC with this ID already exists.");
                    idField.clear();
                    return;
                }
            }
            if (id.length()> 20){
                showAlert("Input Error", "Id cannot be longer than 20 chracters.");
                idField.clear();
                return;
            }

            String name = nameField.getText();
            if (name == null || name.isEmpty() || !name.matches("[A-Za-z ]+") ) {
                showAlert("Input Error", "Name cannot be empty or integer value..");
                nameField.clear();
                return;
            }if (name.length() > 20){
                showAlert("Input Error", "Name cannot be longer than 20 chracters.");
                nameField.clear();
                return;
            }

            int price;
            try {
                price = Integer.parseInt(priceField.getText());
                if (price <= 0 || price > 100000000) {
                    showAlert("Input Error", "Price must be between 1 and 100,000,000.");
                    priceField.clear();
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Price must be a valid number.");
                priceField.clear();
                return;
            }

            int amount;
            try {
                amount = Integer.parseInt(amountField.getText());
                if (amount <= 0 || amount > 1000) {
                    showAlert("Input Error", "Amount must be between 1 and 1,000.");
                    amountField.clear();
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Amount must be a valid number.");
                amountField.clear();
                return;
            }

            int screenSize;
            try {
                screenSize = Integer.parseInt(screenSizeField.getText());
                if (screenSize <= 0 || screenSize > 20) {
                    showAlert("Input Error", "Screen size must be between 1 and 20 inches.");
                    screenSizeField.clear();
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Screen size must be a valid number.");
                screenSizeField.clear();
                return;
            }

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

            int warranty;
            try {
                warranty = Integer.parseInt(warrantyField.getText());
                if (warranty <= 0) {
                    showAlert("Input Error", "Warranty must be greater than 0.");
                    warrantyField.clear();
                    return;
                }
                if (warranty > 100){
                    showAlert("Input Error", "warranty cannot be greater than 100.");
                    warrantyField.clear();
                }
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Warranty must be a valid number.");
                warrantyField.clear();
                return;
            }

            PC pc = new PC(id, name, price, amount, ram, storage, cpu, warranty, screenSize);
            FileManagerPC.addPCInSortedOrder(pcList, pc);
            updateTableView();
            saveProducts();
            clearFields(true);
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    private void updateTableView() {
        ObservableList<PC> observableList = FXCollections.observableArrayList(pcList);
        productTable.setItems(observableList);
        productTable.refresh();
    }

    @FXML
    private void handleDeleteProduct() {
        PC selectedPc = productTable.getSelectionModel().getSelectedItem();
        if (selectedPc != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Confirmation");
            confirmationAlert.setHeaderText("Are you sure you want to delete this product?");
            confirmationAlert.setContentText("Product: " + selectedPc.getName());

            var result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                pcList.remove(selectedPc);
                updateTableView();
                saveProducts();
                showAlert("Success", "Product deleted successfully.");
            }
        } else {
            showAlert("Selection Error", "No product selected.");
        }
    }

    private void saveProducts() {
        try {
            FileManagerPC.savePCData(new ArrayList<>(pcList), dataFile);
        } catch (IOException e) {
            showAlert("Save Error", "Failed to save products to file.");
        }
    }

    private void loadProducts() {
        try {
            List<PC> loadedProducts = FileManagerPC.loadPCData(dataFile);
            pcList.clear();
            pcList.addAll(loadedProducts);
            updateTableView();
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

    @FXML
    private void goToMainScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/veriy/Teknoloji.fxml"));
        Scene mainScene = new Scene(loader.load());
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }
}
