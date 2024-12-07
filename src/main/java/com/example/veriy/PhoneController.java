package com.example.veriy;

import Models.Phone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PhoneController {

    @FXML
    private TableView<Phone> phoneTable;
    @FXML
    private TableColumn<Phone, String> idColumn;
    @FXML
    private TableColumn<Phone, String> nameColumn;
    @FXML
    private TableColumn<Phone, Integer> priceColumn;
    @FXML
    private TableColumn<Phone, Integer> amountColumn;
    @FXML
    private TableColumn<Phone, String> fiveGColumn;
    @FXML
    private TableColumn<Phone, Integer> cameraColumn;
    @FXML
    private TableColumn<Phone, String> fastChargingColumn;

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField amountField;
    @FXML
    private TextField cameraField;

    @FXML
    private CheckBox fiveGCheckbox;
    @FXML
    private CheckBox fastChargingCheckbox;

    private ObservableList<Phone> phoneList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPrice()).asObject());
        amountColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAmount()).asObject());
        fiveGColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().isFiveGsupport() ? "Yes" : "No"));
        cameraColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getNumberofCameras()).asObject());
        fastChargingColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().isFastCharging() ? "Yes" : "No"));

        phoneTable.setItems(phoneList);
    }

    @FXML
    private void addPhone() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            int price = Integer.parseInt(priceField.getText());
            int amount = Integer.parseInt(amountField.getText());
            int cameras = Integer.parseInt(cameraField.getText());
            boolean fiveGSupport = fiveGCheckbox.isSelected();
            boolean fastCharging = fastChargingCheckbox.isSelected();

            Phone phone = new Phone(id, name, price, amount, 0, 0, "N/A", 0, "N/A", fiveGSupport, cameras, fastCharging);
            phoneList.add(phone);

            idField.clear();
            nameField.clear();
            priceField.clear();
            amountField.clear();
            cameraField.clear();
            fiveGCheckbox.setSelected(false);
            fastChargingCheckbox.setSelected(false);
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid data.");
        }
    }

    @FXML
    private void deletePhone() {
        Phone selectedPhone = phoneTable.getSelectionModel().getSelectedItem();
        if (selectedPhone != null) {
            phoneList.remove(selectedPhone);
        } else {
            showAlert("Selection Error", "No phone selected.");
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
