package com.example.veriy;

import Models.PC;
import Models.Phone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class PhoneController implements Initializable {
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


    private LinkedList<Phone> phoneList = new LinkedList<>();
    private final String dataFile = "Phone.txt";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        updateTableView();

        loadProducts();
    }

    private void updateTableView() {
        ObservableList<Phone> observableList = FXCollections.observableArrayList(phoneList);
        productTable.setItems(observableList);
        productTable.refresh();
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
            if (id == null || id.isEmpty()) {
                showAlert("Input Error", "Id cannot be empty.");
                return;
            }
            if (id.length() > 20){
                showAlert("Input Error", "Id cannot be longer than 20 chracters. ");
                idField.clear();
                return;
            }

            // Check if the phone ID already exists
            for (Phone existingPhone : phoneList) {
                if (existingPhone.getId().equals(id)) {
                    showAlert("Duplicate ID Error", "A phone with this ID already exists.");
                    return; // Exit the method if a duplicate is found
                }
            }

            String name = nameField.getText();
            if (name == null || name.isEmpty()) {
                showAlert("Input Error", "Name cannot be empty.");
                return;
            }

            if (name.length() > 20) {
                showAlert("Input Error", "Name must be less than 20 characters.");
                nameField.clear();
                return;
            }
            if (!name.matches("[A-Za-z ]+")) {
                showAlert("Input Error", "Name must only contain letters.");
                nameField.clear();
                return;
            }

            int price = 0;
            try {
                price = Integer.parseInt(priceField.getText());
                if (price <= 0) {
                    showAlert("Input Error", "Price cannot be less than 0.");
                    priceField.clear();
                    return;
                }
                if (price > 100000000) {
                    showAlert("Input Error", "Price must be less than 100000000.");
                    priceField.clear();
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Price cannot be empty or string value.");
                amountField.clear();
                return;
            }

            int amount = 0;
            try {
                amount = Integer.parseInt(amountField.getText());
                if (amount <= 0) {
                    showAlert("Input Error", "Amount cannot be less than 0.");
                    amountField.clear();
                    return;
                }
                if (amount > 1000) {
                    showAlert("Input Error", "Amount must be less than 1000.");
                    amountField.clear();
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Amount cannot be empty or string value.");
                amountField.clear();
                return;
            }

            // RAM field check (ComboBox)
            Integer ramValue = ramField.getValue();
            if (ramValue == null) {
                showAlert("Input Error", "Please select a valid RAM size.");
                return;
            }

            // Storage field check (ComboBox)
            Integer storageValue = storageField.getValue();
            if (storageValue == null) {
                showAlert("Input Error", "Please select a valid storage size.");
                return;
            }

            String cpu = cpuField.getValue();
            if (cpu == null || cpu.isEmpty()) {
                showAlert("Input Error", "Please select a valid CPU.");
                return;
            }

            int warranty = 0;
            try {
                warranty = Integer.parseInt(warrantyField.getText());
                if (warranty <= 0) {
                    showAlert("Input Error", "Warranty cannot be less than 0.");
                    warrantyField.clear();
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Input error", "Warranty cannot be empty.");
                warrantyField.clear();
                return;
            }

            int numberOfCameras = 0;
            try {
                numberOfCameras = Integer.parseInt(numberOfCamerasField.getText());
                if (numberOfCameras <= 0) {
                    showAlert("Input Error", "Number of cameras cannot be less than 0.");
                    numberOfCamerasField.clear();
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Input error", "Number of cameras cannot be empty or string value.");
                numberOfCamerasField.clear();
                return;
            }

            boolean fiveGSupport = "Yes".equalsIgnoreCase(fiveGSupportField.getValue().toString());

            Phone phone = new Phone(id, name, price, amount, ramValue, storageValue, cpu, warranty, numberOfCameras, fiveGSupport);

            FileManagerPhone.addPhoneInSortedOrder(phoneList, phone); // Sıralı ekleme
            updateTableView();
            saveProducts();
            clearFields(false);

        } catch (Exception e) {
            showAlert("Error", "Unexpected error occurred.");
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
                updateTableView();
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
            updateTableView();
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
