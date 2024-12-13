package com.example.veriy;

import Models.Phone;
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

public class TopWearController {
    @FXML
    private Button BackButton;
    @FXML
    private TableView<TopWear> topWearTable;
    @FXML
    private TableColumn<TopWear, String> idColumn;
    @FXML
    private TableColumn<TopWear, String> nameColumn;
    @FXML
    private TableColumn<TopWear, Integer> priceColumn;
    @FXML
    private TableColumn<TopWear, Integer> stockAmountColumn;
    @FXML
    private TableColumn<TopWear, String> sizeColumn;
    @FXML
    private TableColumn<TopWear, String> colorColumn;
    @FXML
    private TableColumn<TopWear, String> clothColumn;
    @FXML
    private TableColumn<TopWear, String> genderColumn;
    @FXML
    private TableColumn<TopWear, String> sleeveTypeColumn;
    @FXML
    private TableColumn<TopWear, String> neckTypeColumn;

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField stockAmountField;
    @FXML
    private ComboBox<String> sizeField;
    @FXML
    private ComboBox<String> colorField;
    @FXML
    private ComboBox<String> clothField;
    @FXML
    private ComboBox<String> genderField;
    @FXML
    private ComboBox<String> sleeveTypeField;
    @FXML
    private ComboBox<String> neckTypeField;

    private ObservableList<TopWear> topWearList = FXCollections.observableArrayList();
    private final String dataFile = "TopWear.txt";

    @FXML
    public void initialize() {
        // Table column mappings
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPrice()).asObject());
        stockAmountColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAmount()).asObject());
        sizeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getSize()));
        colorColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getColor()));
        clothColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCloth()));
        genderColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGender()));
        sleeveTypeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getSleeveType()));
        neckTypeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNeckType()));

        topWearTable.setItems(topWearList);

        // Verileri dosyadan yükle
        loadTopWears();
    }
    @FXML
    private void updateProduct(){
        TopWear selectedPhone = topWearTable.getSelectionModel().getSelectedItem();
        if (selectedPhone != null){
            handleAddTopWear();
        }else {
            showAlert("Selection Error", "No product selected.");
        }


    }

    @FXML
    private void beforeScene(){

    }


    @FXML
    private void handleAddTopWear() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            int price = Integer.parseInt(priceField.getText());
            int stockAmount = Integer.parseInt(stockAmountField.getText());
            String size = sizeField.getValue();
            String color = colorField.getValue();
            String cloth = clothField.getValue();
            String gender = genderField.getValue();
            String sleeveType = sleeveTypeField.getValue();
            String neckType = neckTypeField.getValue();

            TopWear topWear = new TopWear(id, name, price, stockAmount, size, color, cloth, gender, sleeveType, neckType);
            topWearList.add(topWear);

            saveTopWears();

            // Formu temizle
            clearForm();
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid data.");
        }
    }

    @FXML
    private void handleDeleteTopWear() {
        TopWear selectedTopWear = topWearTable.getSelectionModel().getSelectedItem();
        if (selectedTopWear != null) {
            // Onay penceresi oluşturuluyor
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Confirmation");
            confirmationAlert.setHeaderText("Are you sure you want to delete this product?");
            confirmationAlert.setContentText("Product: " + selectedTopWear.getName());

            // Kullanıcı yanıtını kontrol ediyoruz
            var result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                topWearList.remove(selectedTopWear);
                saveTopWears();
                showAlert("Success", "Product deleted successfully.");
            }
        } else {
            showAlert("Selection Error", "No product selected.");
        }
    }



    private void saveTopWears() {
        try {
            FileManagerTopWear.saveTopWearData(topWearList, dataFile);
        } catch (IOException e) {
            showAlert("Save Error", "Failed to save TopWear data.");
        }
    }

    private void loadTopWears() {
        try {
            List<TopWear> loadedTopWears = FileManagerTopWear.loadTopWearData(dataFile);
            topWearList.clear();
            topWearList.addAll(loadedTopWears);
        } catch (IOException e) {
            System.out.println("No existing data found, starting fresh.");
        }
    }

    private void clearForm() {
        idField.clear();
        nameField.clear();
        priceField.clear();
        stockAmountField.clear();
        sizeField.setValue(null); // Seçimi temizle
        colorField.setValue(null);
        clothField.setValue(null);
        genderField.setValue(null);
        sleeveTypeField.setValue(null);
        neckTypeField.setValue(null);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goToScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/veriy/Giyim.fxml"));
        Scene mainScene = new Scene(loader.load());
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }
}

