package com.example.veriy;

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
import java.util.List;

public class PhoneController {
    @FXML
    private Button backButton;
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
    private TableColumn<Phone, Integer> numberOfCamerasColumn;
    @FXML
    private TableColumn<Phone, Boolean> fiveGSupportColumn;

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField amountField;
    @FXML
    private ComboBox<Integer> ramField;
    @FXML
    private ComboBox<Integer> storageField;
    @FXML
    private ComboBox<String> cpuField;
    @FXML
    private TextField warrantyField;
    @FXML
    private TextField numberOfCamerasField;
    @FXML
    private ComboBox<Boolean> fiveGSupportField;


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
        numberOfCamerasColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getNumberofCameras()).asObject());
        fiveGSupportColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleBooleanProperty(data.getValue().isFiveGsupport()).asObject());

        productTable.setItems(phoneList);

        loadProducts();
    }

    @FXML
    private void goToScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/veriy/Teknoloji.fxml"));
        Scene mainScene = new Scene(loader.load());
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }
    @FXML
    private void addPhone() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            int price = Integer.parseInt(priceField.getText());
            int amount = Integer.parseInt(amountField.getText());
            int ram = Integer.parseInt(ramField.getValue().toString());
            int storage = Integer.parseInt(storageField.getValue().toString());
            String cpu = cpuField.getValue();
            int warranty = Integer.parseInt(warrantyField.getText());
            int numberOfCameras = Integer.parseInt(numberOfCamerasField.getText());
            boolean fiveGSupport = "Yes".equalsIgnoreCase(fiveGSupportField.getValue().toString()); // String -> Boolean


            Phone phone = new Phone(id, name, price, amount, ram, storage, cpu, warranty,numberOfCameras, fiveGSupport);
            phoneList.add(phone);

            saveProducts();

            clearFields(false);
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid data.");
        }
    }

    /*@FXML
    private void deletePhone() {
        Phone selectedPhone = productTable.getSelectionModel().getSelectedItem();
        if (selectedPhone != null) {
            phoneList.remove(selectedPhone);
            saveProducts();
        } else {
            showAlert("Selection Error", "No product selected.");
        }
    }*/
    @FXML
    private void deletePhone() {
        Phone selectedPhone = productTable.getSelectionModel().getSelectedItem();
        if (selectedPhone != null) {
            // Onay penceresi oluşturuluyor
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Confirmation");
            confirmationAlert.setHeaderText("Are you sure you want to delete this product?");
            confirmationAlert.setContentText("Product: " + selectedPhone.getName());

            // Kullanıcı yanıtını kontrol ediyoruz
            var result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                phoneList.remove(selectedPhone);
                saveProducts();
                showAlert("Success", "Product deleted successfully.");
            }
        } else {
            showAlert("Selection Error", "No product selected.");
        }
    }


    @FXML
    private void updateProduct() {
    }


    private void saveProducts() {
        try {
            FileManagerPhone.savePhoneData(phoneList, dataFile);
        } catch (IOException e) {
            showAlert("Save Error", "Failed to save TopWear data.");
        }
    }

    private void loadProducts() {
        try {
            List<Phone> loadedProductss = FileManagerPhone.loadProducts(dataFile);
            phoneList.clear();
            phoneList.addAll(loadedProductss);
        } catch (IOException e) {
            System.out.println("No existing data found, starting fresh.");
        }
    }

    private void clearFields(boolean clearComboBoxes) {
        idField.clear();
        nameField.clear();
        priceField.clear();
        amountField.clear();
        warrantyField.clear();
        numberOfCamerasField.clear();
        if (clearComboBoxes) {
            ramField.setValue(null); // Clear RAM ComboBox
            storageField.setValue(null); // Clear Storage ComboBox
            cpuField.setValue(null); // Clear CPU ComboBox
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
