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

    private ObservableList<Product> productList = FXCollections.observableArrayList();
    private InventoryFileHandler fileHandler = new InventoryFileHandler(); // Dosya yöneticisi

    // Tabloyu ve dosyayı başlangıçta ayarla
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Dosyadan verileri yükle ve tabloya ekle
        productList.addAll(fileHandler.readFromFile());
        productTable.setItems(productList);
    }

    @FXML
    private void addProduct() {
        try {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            Product newProduct = new Product(generateId(), name, price, quantity);
            productList.add(newProduct);

            fileHandler.writeToFile(productList); // Listeyi dosyaya kaydet
            clearFields();
        } catch (NumberFormatException e) {
            System.out.println("Lütfen geçerli bir sayı girin.");
        }
    }

    @FXML
    private void deleteProduct() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            productList.remove(selectedProduct);
            fileHandler.writeToFile(productList); // Listeyi dosyaya kaydet
        } else {
            System.out.println("Lütfen silmek için bir ürün seçin.");
        }
    }

    @FXML
    private void updateProduct() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            try {
                selectedProduct.setName(nameField.getText());
                selectedProduct.setPrice(Double.parseDouble(priceField.getText()));
                selectedProduct.setQuantity(Integer.parseInt(quantityField.getText()));

                productTable.refresh(); // Tabloyu yenile
                fileHandler.writeToFile(productList); // Listeyi dosyaya kaydet
                clearFields();
            } catch (NumberFormatException e) {
                System.out.println("Lütfen geçerli bir sayı girin.");
            }
        } else {
            System.out.println("Lütfen güncellemek için bir ürün seçin.");
        }
    }

    private void clearFields() {
        nameField.clear();
        priceField.clear();
        quantityField.clear();
    }

    private int generateId() {
        return productList.isEmpty() ? 1 : productList.get(productList.size() - 1).getId() + 1;
    }
}
