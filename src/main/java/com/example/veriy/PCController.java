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
            if ((id == null || id.isEmpty())) {
                showAlert("Input Error", "Id cannot be empty.");
                return;
            }
            if ( (5 <id.length())) {
                showAlert("Input Error", "string lenght cannot be longher than 5 ");
                return;
            }
            String name = nameField.getText();
            if ((name == null || name.isEmpty())) {
                showAlert("Input Error", "Name cannot be empty.");
                return;
            }
            if ( (5 <name.length())) {
                showAlert("Input Error", "Name lenght cannot be longher than 5 ");
                return;
            }
            int price = Integer.parseInt(priceField.getText());
            if ((priceField == null || priceField.getText().isEmpty())) {
                showAlert("Input Error", "Price cannot be empty.");
                return;
            }
            if ( (5 <priceField.getText().length())) {
                showAlert("Input Error", "string lenght cannot be longher than 5 ");
                return;
            }
            int amount = Integer.parseInt(amountField.getText());
            if ((amountField == null || amountField.getText().isEmpty())) {
                showAlert("Input Error", "Id cannot be empty.");
                return;
            }
            if ( (5 <amountField.getText().length())) {
                showAlert("Input Error", "string lenght cannot be longher than 5 ");
                return;
            }
            int ekranboyutu = Integer.parseInt(screenSizeField.getText());
            if ((screenSizeField == null || screenSizeField.getText().isEmpty())) {
                showAlert("Input Error", "Id cannot be empty.");
                return;
            }
            if ( (5 <screenSizeField.getText().length())) {
                showAlert("Input Error", "string lenght cannot be longher than 5 ");
                return;
            }
            int ram = Integer.parseInt(ramField.getText());
            if ((ramField == null || ramField.getText().isEmpty())) {
                showAlert("Input Error", "Id cannot be empty.");
                return;
            }
            if ( (5 <ramField.getText().length())) {
                showAlert("Input Error", "string lenght cannot be longher than 5 ");
                return;
            }
            int storage = Integer.parseInt(storageField.getText());
            if ((storageField == null || screenSizeField.getText().isEmpty())) {
                showAlert("Input Error", "Id cannot be empty.");
                return;
            }
            if ( (5 <screenSizeField.getText().length())) {
                showAlert("Input Error", "string lenght cannot be longher than 5 ");
                return;
            }
            String cpu = cpuField.getText();
            if ((cpu == null || cpu.isEmpty())) {
                showAlert("Input Error", "Id cannot be empty.");
                return;
            }
            if ( (5 <cpu.length())) {
                showAlert("Input Error", "string lenght cannot be longher than 5 ");
                return;
            }
            int warranty = Integer.parseInt(warrantyField.getText());
            if ((warrantyField == null || warrantyField.getText().isEmpty())) {
                showAlert("Input Error", "Id cannot be empty.");
                return;
            }
            if ( (5 <warrantyField.getText().length())) {
                showAlert("Input Error", "string lenght cannot be longher than 5 ");
                return;
            }

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

    @FXML
    private void goToMainScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/veriy/Teknoloji.fxml"));
        Scene mainScene = new Scene(loader.load());
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }


}
