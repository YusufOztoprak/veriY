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

    private ObservableList<Parfume> parfumeList = FXCollections.observableArrayList();
    private final String dataFile = "Parfume.txt";

    @FXML
    public void initialize() {
        // TableView sütunlarını Parfume nesnesinin özelliklerine bağlama
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPrice()).asObject());
        amountColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAmount()).asObject());
        volumeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getVolume()).asObject());
        genderTargetColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGenderTarget()));
        expirationDateColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getExpiration_date()).asObject());
        userInstructionsColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getUserInstructions()));

        // ComboBox'ları doldurma
        genderTargetComboBox.setItems(FXCollections.observableArrayList("Unisex", "Men", "Women"));
        volumeField.setItems(FXCollections.observableArrayList("50", "100", "150", "200"));

        // Parfüm verilerini yükleme
        loadProducts();
    }


    // Add a new parfume to the list and save it to file
    @FXML
    private void handleAddProduct() {
        try {
            String id = idField.getText();
            if (id == null || id.isEmpty() || !id.matches("\\d+")) {  // Ensure ID is not empty and contains only numbers
                showAlert("Input Error", "Id must be a non-empty number.");
                return;
            }

            String name = nameField.getText();
            if (name == null || name.isEmpty() || !name.matches("[A-Za-z ]+")) {  // Ensure name contains only letters
                showAlert("Input Error", "Name must only contain letters.");
                return;
            }

            // Removed brand field

            int price;
            try {
                price = Integer.parseInt(priceField.getText());
                if (price <= 0) {
                    showAlert("Input Error", "Price must be a positive number.");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Please enter a valid price.");
                return;
            }

            int amount;
            try {
                amount = Integer.parseInt(amountField.getText());
                if (amount <= 0) {
                    showAlert("Input Error", "Amount must be a positive number.");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Please enter a valid amount.");
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
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Please enter a valid expiration date.");
                return;
            }

            String userInstructions = userInstructionsField.getText();
            if (userInstructions == null || userInstructions.isEmpty()) {
                showAlert("Input Error", "Please enter user instructions.");
                return;
            }

            // Yeni Parfume nesnesi oluştur
            Parfume parfume = new Parfume(id, name, price, amount, expirationDate, userInstructions, volume, genderTarget);

            // LinkedList'e sıralı ekle
            LinkedList<Parfume> parfumeLinkedList = new LinkedList<>(parfumeList);
            FileManagerParfume.addParfumeInSortedOrder(parfumeLinkedList, parfume);

            // ObservableList'i güncelle
            parfumeList = FXCollections.observableArrayList(parfumeLinkedList);
            productTable.setItems(parfumeList);

            // Veriyi dosyaya kaydet
            saveProducts();
            clearFields(); // Alanları temizle
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

    private void loadProducts() {
        try {
            // LinkedList ile sıralı verileri yükle
            LinkedList<Parfume> sortedParfumeList = FileManagerParfume.loadParfumeData(dataFile);

            // LinkedList'i ObservableList'e dönüştür
            parfumeList = FXCollections.observableArrayList(sortedParfumeList);

            // TableView'e listeyi ata
            productTable.setItems(parfumeList);

        } catch (IOException e) {
            showAlert("Load Error", "Failed to load parfume data: " + e.getMessage());
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
