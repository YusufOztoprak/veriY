package com.example.veriy;

import Models.BottomWear;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

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
    private TextField sizeField;

    @FXML
    private TextField colorField;

    @FXML
    private TextField clothField;

    @FXML
    private TextField genderField;

    @FXML
    private CheckBox hasPocketsCheckBox;

    @FXML
    private Button BackButton;

    private ObservableList<BottomWear> bottomWearList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // TableView sütunlarını veri modeline bağla
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        clothColumn.setCellValueFactory(new PropertyValueFactory<>("cloth"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        hasPocketsColumn.setCellValueFactory(new PropertyValueFactory<>("hasPockets"));

        // TableView'e veri bağla
        bottomWearTable.setItems(bottomWearList);
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
            BottomWear newBottomWear = new BottomWear(
                    idField.getText(),
                    nameField.getText(),
                    Integer.parseInt(priceField.getText()),
                    Integer.parseInt(stockAmountField.getText()),
                    sizeField.getText(),
                    colorField.getText(),
                    clothField.getText(),
                    genderField.getText(),
                    hasPocketsCheckBox.isSelected()
            );
            bottomWearList.add(newBottomWear);
            clearFields();
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz giriş: Lütfen tüm alanları doğru doldurun.");
        }
    }

    @FXML
    private void handleDeleteBottomWear() {
        BottomWear selectedItem = bottomWearTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            bottomWearList.remove(selectedItem);
        } else {
            System.out.println("Silinecek bir ürün seçilmedi.");
        }
    }

    @FXML
    private void updateProduct() {
        BottomWear selectedItem = bottomWearTable.getSelectionModel().getSelectedItem();
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
        }
    }

    private void clearFields() {
        idField.clear();
        nameField.clear();
        priceField.clear();
        stockAmountField.clear();
        sizeField.clear();
        colorField.clear();
        clothField.clear();
        genderField.clear();
        hasPocketsCheckBox.setSelected(false);
    }
}
