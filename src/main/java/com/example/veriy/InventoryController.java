package com.example.veriy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class InventoryController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField quantityField;

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TableColumn<Product, Integer> quantityColumn;

    // Ürünleri saklamak için ObservableList
    private ObservableList<Product> productList = FXCollections.observableArrayList();

    // Başlangıçta tabloyu ve sütunları ayarlayın
    public void initialize() {
        // Sütunları Product sınıfının özelliklerine bağlayın
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // productTable'ı productList ile bağlayın
        productTable.setItems(productList);
    }

    // Ürün ekleme işlemi
    @FXML
    private void addProduct() {
        try {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            // Yeni ürün oluştur ve listeye ekle
            Product newProduct = new Product(generateId(), name, price, quantity);
            productList.add(newProduct);

            // Alanları temizle
            nameField.clear();
            priceField.clear();
            quantityField.clear();
        } catch (NumberFormatException e) {
            System.out.println("Lütfen geçerli bir sayı girin.");
        }
    }

    // Ürün silme işlemi
    @FXML
    private void deleteProduct() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            productList.remove(selectedProduct); // ObservableList'ten kaldır, tablo otomatik güncellenecektir
        } else {
            System.out.println("Lütfen silmek için bir ürün seçin.");
        }
    }

    // Ürün güncelleme işlemi
    @FXML
    private void updateProduct() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            try {
                selectedProduct.setName(nameField.getText());
                selectedProduct.setPrice(Double.parseDouble(priceField.getText()));
                selectedProduct.setQuantity(Integer.parseInt(quantityField.getText()));

                productTable.refresh(); // Tabloyu manuel olarak yenile
            } catch (NumberFormatException e) {
                System.out.println("Lütfen geçerli bir sayı girin.");
            }
        } else {
            System.out.println("Lütfen güncellemek için bir ürün seçin.");
        }
    }

    // Benzersiz ID üretmek için basit bir yöntem
    private int generateId() {
        return productList.size() + 1;
    }
}
