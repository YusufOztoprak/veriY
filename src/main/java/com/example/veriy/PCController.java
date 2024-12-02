package com.example.veriy;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PCController {

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TextField productNameField;
    @FXML
    private TextField productPriceField;

    private ObservableList<Product> pcList = FXCollections.observableArrayList();
    private int idCounter = 1;

    @FXML
    private void initialize() {
        // Table column'larını ayarlıyoruz
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Tabloyu güncelle
        productTable.setItems(pcList);
    }

    // Ürün ekleme
    @FXML
    private void addProduct() {
        String name = productNameField.getText();
        String priceText = productPriceField.getText();

        if (name.isEmpty() || priceText.isEmpty()) {
            return; // Boş alan varsa hiçbir şey yapma
        }

        double price = Double.parseDouble(priceText);
        Product newProduct = new Product(idCounter++, name, price);
        pcList.add(newProduct);

        // Alanları temizle
        productNameField.clear();
        productPriceField.clear();
    }

    // Ürün silme
    @FXML
    private void deleteProduct() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            pcList.remove(selectedProduct);
        }
    }
}
