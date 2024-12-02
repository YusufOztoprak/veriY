package com.example.veriy;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShampooController {
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
    private TableColumn<Shampoo, Integer> expirationColumn;

    @FXML
    private TableColumn<Shampoo, String> brandColumn;

    @FXML
    private TableColumn<Shampoo, String> hairTypeColumn;

    @FXML
    private TableColumn<Shampoo, Double> volumeColumn;

    private ShampooRepo shampooRepo = new ShampooRepo();

    @FXML
    public void initialize() {
        // Bind TableColumns to Shampoo properties
        idColumn.setCellValueFactory(new PropertyValueFactory<>("product_ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        expirationColumn.setCellValueFactory(new PropertyValueFactory<>("expiration_date"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        hairTypeColumn.setCellValueFactory(new PropertyValueFactory<>("hairType"));
        volumeColumn.setCellValueFactory(new PropertyValueFactory<>("volume"));

        // Populate TableView with shampoo data
        shampooTable.setItems(shampooRepo.getShampooList());

        // Add sample data
        shampooRepo.addShampoo(new Shampoo(001, "Head & Shoulders", 50, 100, 2025, "P&G", "Apply to wet hair.", "All Hair Types", 400.0));
        shampooRepo.addShampoo(new Shampoo(002, "Pantene", 40, 200, 2024, "P&G", "Use daily.", "Dry Hair", 300.0));
    }
}
