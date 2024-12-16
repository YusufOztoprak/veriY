package com.example.veriy;

import Models.Parfume;
import Models.Shampoo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ShampooController {

    @FXML
    private Button BackButton;

    @FXML
    private TableView<Shampoo> shampooTable;
    @FXML
    private TableColumn<Shampoo, String> idColumn;
    @FXML
    private TableColumn<Shampoo, String> nameColumn;
    @FXML
    private TableColumn<Shampoo, Integer> priceColumn;
    @FXML
    private TableColumn<Shampoo, Integer> amountColumn;
    @FXML
    private TableColumn<Shampoo, Integer> expirationColumn;  // Updated column for expiration
    @FXML
    private TableColumn<Shampoo, String> hairTypeColumn;
    @FXML
    private TableColumn<Shampoo, Double> volumeColumn;

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField amountField;
    @FXML
    private TextField expirationField;  // Added expiration field
    @FXML
    private ComboBox<String> hairTypeComboBox;
    @FXML
    private ComboBox<String> volumeComboBox;

    private ObservableList<Shampoo> shampooList = FXCollections.observableArrayList();
    private final String dataFile = "Shampoo.txt";

    @FXML
    public void initialize() {
        // TableView sütunlarını Shampoo sınıfındaki özelliklerle eşleştir
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPrice()).asObject());
        amountColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAmount()).asObject());
        expirationColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getExpiration_date()).asObject());  // Display expiration date
        hairTypeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getHairType()));
        volumeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getVolume()).asObject());

        // TableView'e liste bağla
        shampooTable.setItems(shampooList);

        // ComboBox'lara seçenekler ekle
        hairTypeComboBox.setItems(FXCollections.observableArrayList("Dry", "Oily", "Normal", "Curly"));
        volumeComboBox.setItems(FXCollections.observableArrayList("100", "250", "500", "1000"));

        // Verileri yükle
        loadShampoo();
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
    private void handleAddShampoo() {
        try {
            // Formdan veriyi al
            String id = idField.getText();
            if (id == null || id.isEmpty()) {
                showAlert("Input Error", "Id cannot be empty.");
                return;
            }
            for (Shampoo existingShampoo : shampooList) {
                if (existingShampoo.getId().equals(id)) {
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
            int expirationDate = parseIntegerField(expirationField);
            String hairType = hairTypeComboBox.getValue();
            if (hairType == null || hairType.isEmpty()) {
                showAlert("Input Error", "Please select a valid CPU.");
                return;
            }
            String volumeStr = volumeComboBox.getValue();
            if (volumeStr == null || volumeStr.isEmpty()) {
                showAlert("Input Error", "Please select a valid CPU.");
                return;
            }
            String expirationStr = expirationField.getText().trim();
            double volume = Double.parseDouble(volumeStr);

            // Yeni Shampoo nesnesi oluştur
            Shampoo newShampoo = new Shampoo(id, name, price, amount, expirationDate, "N/A", hairType, volume);

            // Veriyi sıralı bir şekilde LinkedList'e ekle
            List<Shampoo> shampooLinkedList = FileManagerShampoo.loadShampooData(dataFile);
            FileManagerShampoo.addShampooInSortedOrder(shampooLinkedList, newShampoo);

            // TableView'e sıralı listeyi aktar
            shampooList = FXCollections.observableArrayList(shampooLinkedList);
            shampooTable.setItems(shampooList);

            // Veriyi dosyaya kaydet
            saveShampoo();

            // Formu temizle
            clearForm();

        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid data.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete the selected parfume from the lis
    @FXML
    private void handleDeleteShampoo() {
        Shampoo selectedShampo = shampooTable.getSelectionModel().getSelectedItem();
        if (selectedShampo != null) {
            // Onay penceresi oluşturuluyor
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Confirmation");
            confirmationAlert.setHeaderText("Are you sure you want to delete this product?");
            confirmationAlert.setContentText("Product: " + selectedShampo.getName());

            // Kullanıcı yanıtını kontrol ediyoruz
            var result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                shampooList.remove(selectedShampo);
                saveShampoo();
                showAlert("Success", "Product deleted successfully.");
            }
        } else {
            showAlert("Selection Error", "No product selected.");
        }
    }


    private void saveShampoo() {
        try {
            FileManagerShampoo.saveShampooData(shampooList, dataFile);
        } catch (IOException e) {
            showAlert("Save Error", "Failed to save Shampoo data.");
        }
    }

    private void loadShampoo() {
        try {
            List<Shampoo> loadedShampoo = FileManagerShampoo.loadShampooData(dataFile);
            shampooList.clear();
            shampooList.addAll(loadedShampoo);
        } catch (IOException e) {
            System.out.println("No existing data found, starting fresh.");
        }
    }

    private void clearForm() {
        idField.clear();
        nameField.clear();
        priceField.clear();
        amountField.clear();
        expirationField.clear();  // Clear expiration field
        hairTypeComboBox.getSelectionModel().clearSelection();
        volumeComboBox.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Helper method to parse integers with error handling
    private int parseIntegerField(TextField field) {
        try {
            return Integer.parseInt(field.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter a valid number.");
            return -1; // Indicates invalid input
        }
    }
}
