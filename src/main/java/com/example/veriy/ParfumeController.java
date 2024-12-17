package com.example.veriy;

import Models.Parfume;
import Models.Phone;
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
        addCharacterLimit(nameField,20);
        priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPrice()).asObject());
        addCharacterLimit(priceField,9);
        amountColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAmount()).asObject());
        addCharacterLimit(amountField,9);
        volumeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getVolume()).asObject());
        genderTargetColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGenderTarget()));
        expirationDateColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getExpiration_date()).asObject());
        addCharacterLimit(expirationDateField,9);
        userInstructionsColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getUserInstructions()));

        updateTableView();

        // Populate ComboBoxes with predefined options
        genderTargetComboBox.setItems(FXCollections.observableArrayList("Unisex", "Men", "Women"));
        volumeField.setItems(FXCollections.observableArrayList("50", "100", "150", "200"));

        // Load parfume data from file
        loadProducts();
    }
    private void addCharacterLimit(TextField textField, int maxLength) {
        // TextField'in metin değişimini dinleyen bir listener ekliyoruz
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Yeni metin belirtilen uzunluğu aşıyorsa
            if (newValue != null && newValue.length() > maxLength) {
                // Eski değeri geri yükler (sınıra uymayan değişikliği reddeder)
                textField.setText(oldValue);
            }
        });
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
            if (!id.matches("^[A-Za-z0-9-]+$")) {  // Sadece harf, rakam ve tire kontrolü
                showAlert("Input Error", "Id can only contain letters, numbers, and the '-' character.");
                idField.clear();
                return;
            }


            /// Sayısal kısmı ayıklama ve kontrol etme
            String numericPart = id.replaceAll("[^0-9]", ""); // Harfleri temizleyip sadece sayıları alır
            if (!numericPart.isEmpty()) { // Eğer sayısal kısım varsa kontrol et
                try {
                    int numericValue = Integer.parseInt(numericPart);
                    if (numericValue > 1000000) { // Sayısal değerin sınırı
                        showAlert("Input Error", "The numeric part of the ID cannot be greater than 1,000,000.");
                        idField.clear();
                        return;
                    }
                } catch (NumberFormatException e) {
                    showAlert("Input Error", "Numeric part of the ID is invalid.");
                    idField.clear();
                    return;
                }
            }

            String name = nameField.getText();
            if (name == null || name.isEmpty() || !name.matches("[A-Za-z ]+") ) {
                showAlert("Input Error", "Name cannot be empty or integer value..");
                nameField.clear();
                return;
            }if (name.length() > 15){
                showAlert("Input Error", "Name cannot be longer than 15 chracters.");
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
                if (expirationDate <= 2024) {
                    showAlert("Input Error", "Expiration date must be a greater than today.");
                    expirationDateField.clear();
                    return;
                }
                if (expirationDate > 2100){
                    showAlert("Input Error ","Expiration date cannot be greater than 2100");
                    expirationDateField.clear();
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Input Error", " expiration date cannot be empty or String Value.");
                expirationDateField.clear();
                return;
            }

            String userInstructions = userInstructionsField.getText();

            // Boş olma durumu kontrolü
            if (userInstructions == null || userInstructions.isEmpty()) {
                showAlert("Input Error", "Please enter user instructions.");
                expirationDateField.clear();
                return;
            }

            // Uzunluk sınırı kontrolü (örneğin 500 karakterden fazla olamaz)
            if (userInstructions.length() > 500) {
                showAlert("Input Error", "User instructions cannot exceed 500 characters.");
                expirationDateField.clear();
                return;
            }

            // Özel karakter kontrolü (istenmeyen karakterler var mı?)
            if (userInstructions.matches(".*[<>\"'&].*")) {
                showAlert("Input Error", "User instructions cannot contain special characters like <, >, \", ', or &.");
                expirationDateField.clear();
                return;
            }

            // Sadece rakam içermesi durumu (örneğin sadece rakam içeriyorsa hatalı olabilir)
            if (userInstructions.matches("[0-9]+")) {
                showAlert("Input Error", "User instructions cannot be numeric.");
                expirationDateField.clear();
                return;
            }

            // Boşluk ile başlama ya da bitme kontrolü
            if (userInstructions.startsWith(" ") || userInstructions.endsWith(" ")) {
                showAlert("Input Error", "User instructions cannot start or end with a space.");
                expirationDateField.clear();
                return;
            }

            // Yalnızca büyük harfleri içeriyorsa (isteğe bağlı)
            if (userInstructions.equals(userInstructions.toUpperCase())) {
                showAlert("Input Error", "User instructions cannot be in all uppercase letters.");
                expirationDateField.clear();
                return;
            }

            // Eğer belirli bir dilde karakterler varsa (örneğin sadece İngilizce karakterler istiyorsanız)
            if (!userInstructions.matches("[a-zA-Z0-9 ]*")) {
                showAlert("Input Error", "User instructions can only contain alphanumeric characters and spaces.");
                expirationDateField.clear();
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
            // Onay penceresi oluşturuluyor
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Confirmation");
            confirmationAlert.setHeaderText("Are you sure you want to delete this product?");
            confirmationAlert.setContentText("Product: " + selectedParfume.getName());

            // Kullanıcı yanıtını kontrol ediyoruz
            var result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                parfumeList.remove(selectedParfume);
                updateTableView();
                saveProducts();
                showAlert("Success", "Product deleted successfully.");

            }
        } else {
            showAlert("Selection Error", "No product selected.");
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
