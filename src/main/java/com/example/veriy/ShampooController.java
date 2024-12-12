package com.example.veriy;

import Models.Shampoo;
import Models.TopWear;
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
    private TextField hairTypeField;
    @FXML
    private TextField volumeField;

    private ObservableList<Shampoo> shampooList = FXCollections.observableArrayList();
    private final String dataFile = "Shampoo.txt";

    @FXML
    public void initialize() {
        // TableView sütunlarını Shampoo sınıfındaki özelliklerle eşleştir
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPrice()).asObject());
        amountColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAmount()).asObject());
        hairTypeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getHairType()));
        volumeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getVolume()).asObject());

        // TableView'e liste bağla
        shampooTable.setItems(shampooList);

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
            String id = idField.getText();
            String name = nameField.getText();
            int price = Integer.parseInt(priceField.getText());
            int amount = Integer.parseInt(amountField.getText());
            String hairType = hairTypeField.getText();
            double volume = Double.parseDouble(volumeField.getText());

            Shampoo newShampoo = new Shampoo(id, name, price, amount, 0, "N/A", "N/A", hairType, volume);
            shampooList.add(newShampoo);
            saveShampoo();

            clearForm();
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid data.");
        }
    }

    @FXML
    private void handleDeleteShampoo() {
        Shampoo selectedShampoo = shampooTable.getSelectionModel().getSelectedItem();
        if (selectedShampoo != null) {
            shampooList.remove(selectedShampoo);
        } else {
            showAlert("Selection Error", "No shampoo selected.");
        }
    }
    private void saveShampoo() {
        try {
            FileManagerShampoo.saveShampooData(shampooList, dataFile);
        } catch (IOException e) {
            showAlert("Save Error", "Failed to save TopWear data.");
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
        hairTypeField.clear();
        volumeField.clear();

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
