package com.example.veriy;

import Models.TopWear;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class TopWearController {

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
    private Button saveButton;

    private TopWear topWear;

    @FXML
    public void initialize() {
        // Bu metot, FXML yüklenirken otomatik olarak çağrılır.
        // Gerekirse başlangıç işlemleri burada yapılır.
    }

    @FXML
    private void handleSave() {
        // Kullanıcı tarafından girilen bilgileri al
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

        // Yeni bir TopWear nesnesi oluştur
        topWear = new TopWear(id, name, price, stockAmount, size, color, cloth, gender, sleeveType, neckType);

        // Kaydedilen bilgiyi konsola yazdır (veya başka bir işlem yap)
        System.out.println("Ürün kaydedildi: ");
        topWear.get_info();
    }

    public void setTopWear(TopWear topWear) {
        this.topWear = topWear;
        idField.setText(topWear.getId());
        nameField.setText(topWear.getName());
        priceField.setText(String.valueOf(topWear.getPrice()));
        stockAmountField.setText(String.valueOf(topWear.getAmount()));
        sizeField.setText(topWear.getSize());
        colorField.setText(topWear.getColor());
        clothField.setText(topWear.getCloth());
        genderField.setText(topWear.getGender());
        sleeveTypeField.setText(topWear.getSleeveType());
        neckTypeField.setText(topWear.getNeckType());
    }
}
