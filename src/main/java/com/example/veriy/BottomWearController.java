package com.example.veriy;

import Models.BottomWear;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

public class BottomWearController {
    @FXML
    private TableView<BottomWear> bottomWearTable;

    @FXML
    private TableColumn<BottomWear, String> idColumn;

    @FXML
    private TableColumn<BottomWear, String> nameColumn;

    @FXML
    private TableColumn<BottomWear, Integer> priceColumn;

    @FXML
    private TableColumn<BottomWear, Integer> stockAmountColumn;

    @FXML
    private TableColumn<BottomWear, String> sizeColumn;

    @FXML
    private TableColumn<BottomWear, String> colorColumn;

    @FXML
    private TableColumn<BottomWear, String> clothColumn;

    @FXML
    private TableColumn<BottomWear, String> genderColumn;

    @FXML
    private TableColumn<BottomWear, Boolean> hasPocketsColumn;

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
    private CheckBox hasPocketsCheckBox;

    private final String dataFile = "BottomWear.txt";

    @FXML
    private Button BackButton;

    private final LinkedList<BottomWear> bottomWearList = new LinkedList<>();
    @FXML
    public void initialize() {
        // TableView sütunlarını veri modeline bağla
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addCharacterLimit(nameField,20);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        addCharacterLimit(priceField,9);
        stockAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        addCharacterLimit(stockAmountField,9);
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        clothColumn.setCellValueFactory(new PropertyValueFactory<>("cloth"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        hasPocketsColumn.setCellValueFactory(cellData ->
                new SimpleBooleanProperty(cellData.getValue().hasPockets()));
        hasPocketsColumn.setCellFactory(tc -> new CheckBoxTableCell<>());

        // TableView'e veri bağla
        updateTableView();

        loadBottomWears();
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

    @FXML
    private void goToScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/veriy/Giyim.fxml"));
        Scene mainScene = new Scene(loader.load());
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }

    @FXML
    private void handleAddBottomWear() {
        try {
            String id = idField.getText();
            if (id == null || id.isEmpty()) {
                showAlert("Input Error", "Id cannot be empty.");
                return;
            }
            for (BottomWear existingBottom : bottomWearList) {
                if (existingBottom.getId().equals(id)) {
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
                showAlert("Input Error", "Price must be a valid number.");
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
                showAlert("Input Error", "Amount must be a valid number.");
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
            boolean hasPockets = hasPocketsCheckBox.isSelected();



            BottomWear bottomWear = new BottomWear(id, name, price, amount, size, color, cloth, gender, hasPockets);
            FileManagerBottomWear.addBottomWearInSortedOrder(bottomWearList,bottomWear);
            updateTableView();
            saveBottomWears();
            clearFields();
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz giriş: Lütfen tüm alanları doğru doldurun.");
        }
    }

    @FXML
    private void handleDeleteBottomWear() {
        BottomWear selectedBottomWear = bottomWearTable.getSelectionModel().getSelectedItem();
        if (selectedBottomWear != null) {
            // Onay penceresi oluşturuluyor
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Confirmation");
            confirmationAlert.setHeaderText("Are you sure you want to delete this product?");
            confirmationAlert.setContentText("Product: " + selectedBottomWear.getName());

            // Kullanıcı yanıtını kontrol ediyoruz
            var result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                bottomWearList.remove(selectedBottomWear);
                updateTableView();
                saveBottomWears();
                showAlert("Success", "Product deleted successfully.");
            }
        } else {
            showAlert("Selection Error", "No product selected.");
        }
    }


    @FXML
    private void updateProduct() {
        /*BottomWear selectedItem = bottomWearTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            try {
                selectedItem.setId(idField.getText());
                selectedItem.setName(nameField.getText());
                selectedItem.setPrice(Integer.parseInt(priceField.getText()));
                selectedItem.setAmount(Integer.parseInt(stockAmountField.getText()));
                selectedItem.setSize(sizeField.getText());
                selectedItem.setColor(colorField.getText());
                selectedItem.setCloth(clothField.getText());
                selectedItem.setGender(genderField.getText());
                selectedItem.setHasPockets(hasPocketsCheckBox.isSelected());

                bottomWearTable.refresh();
                clearFields();
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz giriş: Lütfen tüm alanları doğru doldurun.");
            }
        } else {
            System.out.println("Güncellenecek bir ürün seçilmedi.");
        }*/
    }

    private void clearFields() {
        idField.clear();
        nameField.clear();
        priceField.clear();
        stockAmountField.clear();
        hasPocketsCheckBox.setSelected(false);
    }
    private void saveBottomWears() {
        try {
            FileManagerBottomWear.saveBottomWearData(bottomWearList, dataFile);
        } catch (IOException e) {
            showAlert("Save Error", "Failed to save TopWear data.");
        }
    }

    private void loadBottomWears() {
        try {
            List<BottomWear> loadedBottomWears = FileManagerBottomWear.loadBottomWearData(dataFile);
            bottomWearList.clear();
            bottomWearList.addAll(loadedBottomWears);
            updateTableView();
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
    private void updateTableView() {
        ObservableList<BottomWear> observableList = FXCollections.observableArrayList(bottomWearList);
        bottomWearTable.setItems(observableList);
        bottomWearTable.refresh();
    }

}
