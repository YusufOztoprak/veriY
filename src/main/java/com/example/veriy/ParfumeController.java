package com.example.veriy;

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
import java.util.LinkedList;
import java.util.List;

public class ParfumeController {

    @FXML
    private Button backButton;
    @FXML
    private TableView<Parfume> productTable;
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
    private TableColumn<Parfume, Integer> expirationDateColumn;
    @FXML
    private TableColumn<Parfume, String> userInstructionsColumn;

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField amountField;
    @FXML
    private ComboBox<String> volumeField;
    @FXML
    private ComboBox<String> genderTargetComboBox;
    @FXML
    private TextField expirationDateField;
    @FXML
    private TextField userInstructionsField;

    private final LinkedList<Parfume> parfumeList = new LinkedList<>();
    private final String dataFile = "Parfume.txt";

    @FXML
    public void initialize() {
        // Set TableView columns with Parfume object properties
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPrice()).asObject());
        amountColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAmount()).asObject());
        volumeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getVolume()).asObject());
        genderTargetColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGenderTarget()));
        expirationDateColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getExpiration_date()).asObject());
        userInstructionsColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getUserInstructions()));

        updateTableView();

        // Populate ComboBoxes with predefined options
        genderTargetComboBox.setItems(FXCollections.observableArrayList("Unisex", "Men", "Women"));
        volumeField.setItems(FXCollections.observableArrayList("50", "100", "150", "200"));

        // Load parfume data from file
        loadProducts();
    }

    private void updateTableView() {
        ObservableList<Parfume> observableList = FXCollections.observableArrayList(parfumeList);
        productTable.setItems(observableList);
        productTable.refresh();
    }
    // Add a new parfume to the list and save it to file
    @FXML
    private void handleAddProduct() {
        try {
            String id = idField.getText();
            if (id == null || id.isEmpty()) {
                showAlert("Input Error", "Id cannot be empty.");
                return;
            }
            for (Parfume existingParfume : parfumeList) {
                if (existingParfume.getId().equals(id)) {
                    showAlert("Duplicate ID Error", "A Parfume with this ID already exists.");
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
                showAlert("Input Error", "Price cannot be empty or String Value.");
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
                showAlert("Input Error", "Amount cannot be empty or String Value.");
                amountField.clear();
                return;
            }


            // Get volume from ComboBox (converted to double)
            String volumeStr = volumeField.getValue();
            if (volumeStr == null || volumeStr.isEmpty()) {
                showAlert("Input Error", "Please select a valid volume.");
                return;
            }
            double volume = Double.parseDouble(volumeStr);

            String genderTarget = genderTargetComboBox.getValue();
            if (genderTarget == null || genderTarget.isEmpty()) {
                showAlert("Input Error", "Please select a valid gender target.");
                return;
            }

            int expirationDate;
            try {
                expirationDate = Integer.parseInt(expirationDateField.getText());
                if (expirationDate <= 0) {
                    showAlert("Input Error", "Expiration date must be a positive number.");
                    expirationDateField.clear();
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Input Error", " expiration date cannot be empty or String Value.");
                expirationDateField.clear();
                return;
            }

            String userInstructions = userInstructionsField.getText();
            if (userInstructions == null || userInstructions.isEmpty()) {
                showAlert("Input Error", "Please enter user instructions.");
                return;
            }

            // Create new Parfume object and add to list
            Parfume parfume = new Parfume(id, name, price, amount, expirationDate, userInstructions, volume, genderTarget);
            FileManagerParfume.addParfumeInSortedOrder(parfumeList,parfume);
            updateTableView();

            // Save updated parfume list to file
            saveProducts();
            clearFields(); // Clear the form fields
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid data.");
        }
    }

    // Delete the selected parfume from the list
    @FXML
    private void handleDeleteProduct() {
        Parfume selectedParfume = productTable.getSelectionModel().getSelectedItem();
        if (selectedParfume != null) {
            parfumeList.remove(selectedParfume);
            updateTableView();
            saveProducts(); // Save updated list to file
        } else {
            showAlert("Selection Error", "No parfume selected.");
        }
    }

    // Save parfumes to the file
    private void saveProducts() {
        try {
            FileManagerParfume.saveParfumeData(new ArrayList<>(parfumeList), dataFile);
        } catch (IOException e) {
            showAlert("Save Error", "Failed to save parfumes.");
        }
    }

    // Load parfumes from file
    private void loadProducts() {
        try {
            List<Parfume> loadedParfumes = FileManagerParfume.loadParfumeData(dataFile);
            parfumeList.clear();
            parfumeList.addAll(loadedParfumes);
            updateTableView();
        } catch (IOException e) {
            System.out.println("No existing data found, starting fresh.");
        }
    }

    // Show alert with given title and message
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Clear the input fields
    private void clearFields() {
        idField.clear();
        nameField.clear();
        priceField.clear();
        amountField.clear();
        volumeField.getSelectionModel().clearSelection();
        genderTargetComboBox.getSelectionModel().clearSelection();
        expirationDateField.clear();
        userInstructionsField.clear();
    }

    // Navigate back to the main scene
    @FXML
    private void goToMainScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/veriy/KisiselBakim.fxml"));
        Scene mainScene = new Scene(loader.load());
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }
}
