package com.example.veriy;

import Models.PC;
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
            if (id == null || id.isEmpty()) {
                showAlert("Input Error", "Id cannot be empty.");
                return;
            }
            for (TopWear existingTop : topWearList) {
                if (existingTop.getId().equals(id)) {
                    showAlert("Duplicate ID Error", "A TopWear with this ID already exists.");
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
                amount = Integer.parseInt(stockAmountField.getText());
                if (amount <= 0 || amount > 1000) {
                    showAlert("Input Error", "Amount must be between 1 and 1,000.");
                    stockAmountField.clear();
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Amount cannot be empty or String Value.");
                stockAmountField.clear();
                return;
            }

            String size = sizeField.getValue();
            if (size == null || size.isEmpty()) {
                showAlert("Input Error", "Please select a valid Size size.");
                return;
            }
            String color = colorField.getValue();
            if (color == null || color.isEmpty()) {
                showAlert("Input Error", "Please select a valid Color size.");
                return;
            }
            String cloth = clothField.getValue();
            if (cloth == null || cloth.isEmpty()) {
                showAlert("Input Error", "Please select a valid Cloth size.");
                return;
            }
            String gender = genderField.getValue();
            if (gender == null || gender.isEmpty()) {
                showAlert("Input Error", "Please select a valid Gender size.");
                return;
            }
            String sleeveType = sleeveTypeField.getValue();
            if (sleeveType == null || sleeveType.isEmpty()) {
                showAlert("Input Error", "Please select a valid SleeveType size.");
                return;
            }
            String neckType = neckTypeField.getValue();
            if (neckType == null || neckType.isEmpty()) {
                showAlert("Input Error", "Please select a valid NeckType size.");
                return;
            }

            TopWear topWear = new TopWear(id, name, price, amount, size, color, cloth, gender, sleeveType, neckType);
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
