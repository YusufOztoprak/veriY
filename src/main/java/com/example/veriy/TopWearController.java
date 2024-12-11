package com.example.veriy;

import Models.TopWear;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class TopWearController {
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
    private TextField sizeField;
    @FXML
    private TextField colorField;
    @FXML
    private TextField clothField;
    @FXML
    private TextField genderField;
    @FXML
    private TextField sleeveTypeField;
    @FXML
    private TextField neckTypeField;

    @FXML
    private Button backButton;

    // ObservableList for storing TopWear items
    private ObservableList<TopWear> topWearList = FXCollections.observableArrayList();

    // Method to handle adding a new TopWear item
    @FXML
    public void handleAddTopWear() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            int price = Integer.parseInt(priceField.getText());
            int stockAmount = Integer.parseInt(stockAmountField.getText());
            String size = sizeField.getText();
            String color = colorField.getText();
            String cloth = clothField.getText();
            String gender = genderField.getText();
            String sleeveType = sleeveTypeField.getText();
            String neckType = neckTypeField.getText();

            // Create a new TopWear object and add it to the list
            TopWear newTopWear = new TopWear(id, name, price, stockAmount, size, color, cloth, gender, sleeveType, neckType);
            topWearList.add(newTopWear);

            // Update the TableView
            topWearTable.setItems(topWearList);

            // Clear the input fields after adding
            clearFields();
        } catch (NumberFormatException e) {
            showErrorMessage("Please enter valid numbers for Price and Stock Amount.");
        }
    }

    // Method to handle updating an existing TopWear item
    @FXML
    public void updateProduct() {
        // Get the selected TopWear from the table
        TopWear selectedTopWear = topWearTable.getSelectionModel().getSelectedItem();
        if (selectedTopWear != null) {
            try {
                // Update the selected TopWear object with values from the fields
                selectedTopWear.setId(idField.getText());
                selectedTopWear.setName(nameField.getText());
                selectedTopWear.setPrice(Integer.parseInt(priceField.getText()));
                selectedTopWear.setAmount(Integer.parseInt(stockAmountField.getText()));
                selectedTopWear.setSize(sizeField.getText());
                selectedTopWear.setColor(colorField.getText());
                selectedTopWear.setCloth(clothField.getText());
                selectedTopWear.setGender(genderField.getText());
                selectedTopWear.setSleeveType(sleeveTypeField.getText());
                selectedTopWear.setNeckType(neckTypeField.getText());

                // Refresh the TableView
                topWearTable.refresh();

                // Clear fields after update
                clearFields();
            } catch (NumberFormatException e) {
                showErrorMessage("Please enter valid numbers for Price and Stock Amount.");
            }
        } else {
            showErrorMessage("No product selected.");
        }
    }

    // Method to handle deleting a TopWear item
    @FXML
    public void handleDeleteTopWear() {
        TopWear selectedTopWear = topWearTable.getSelectionModel().getSelectedItem();
        if (selectedTopWear != null) {
            topWearList.remove(selectedTopWear);
            topWearTable.setItems(topWearList);
        } else {
            showErrorMessage("No product selected.");
        }
    }

    // Method to handle navigating back to the previous scene
    @FXML
    public void goToScene() {
        // Handle scene change logic here (e.g., using SceneManager or Parent/Stage switching)
        System.out.println("Navigating back to Giyim");
    }

    // Method to clear input fields
    private void clearFields() {
        idField.clear();
        nameField.clear();
        priceField.clear();
        stockAmountField.clear();
        sizeField.clear();
        colorField.clear();
        clothField.clear();
        genderField.clear();
        sleeveTypeField.clear();
        neckTypeField.clear();
    }

    // Method to show error messages
    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Initialize method to set up the TableView columns and load data
    @FXML
    public void initialize() {
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

        // Set TableView items to the ObservableList
        topWearTable.setItems(topWearList);
    }
}

