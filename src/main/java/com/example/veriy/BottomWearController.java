package com.example.veriy;

import Models.BottomWear;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class BottomWearController {
    @FXML
    private Button BackButton;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField amountField;

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
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private BottomWear bottomWear;

    /**
     * Başlangıçta bir BottomWear nesnesi bağlamak için kullanılır.
     * @param bottomWear BottomWear nesnesi
     */
    public void setBottomWear(BottomWear bottomWear) {
        this.bottomWear = bottomWear;

        // Mevcut verileri arayüze yansıt
        idField.setText(bottomWear.getId());
        nameField.setText(bottomWear.getName());
        priceField.setText(String.valueOf(bottomWear.getPrice()));
        amountField.setText(String.valueOf(bottomWear.getAmount()));
        sizeField.setText(bottomWear.getSize());
        colorField.setText(bottomWear.getColor());
        clothField.setText(bottomWear.getCloth());
        genderField.setText(bottomWear.getGender());
        hasPocketsCheckBox.setSelected(bottomWear.hasPockets());
    }
    @FXML
    private void goToScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/veriy/Giyim.fxml"));
        Scene mainScene = new Scene(loader.load());
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }

    /**
     * Kaydet butonuna tıklandığında çağrılır.
     */
    @FXML
    private void saveBottomWear() {
        if (bottomWear == null) {
            bottomWear = new BottomWear(
                    idField.getText(),
                    nameField.getText(),
                    Integer.parseInt(priceField.getText()),
                    Integer.parseInt(amountField.getText()),
                    sizeField.getText(),
                    colorField.getText(),
                    clothField.getText(),
                    genderField.getText(),
                    hasPocketsCheckBox.isSelected()
            );
        } else {
            bottomWear.setId(idField.getText());
            bottomWear.setName(nameField.getText());
            bottomWear.setPrice(Integer.parseInt(priceField.getText()));
            bottomWear.setAmount(Integer.parseInt(amountField.getText()));
            bottomWear.setSize(sizeField.getText());
            bottomWear.setColor(colorField.getText());
            bottomWear.setCloth(clothField.getText());
            bottomWear.setGender(genderField.getText());
            bottomWear.setHasPockets(hasPocketsCheckBox.isSelected());
        }

        System.out.println("BottomWear bilgileri kaydedildi:");
        bottomWear.get_info();
    }

    /**
     * İptal butonuna tıklandığında çağrılır.
     */
    @FXML
    private void cancelAction() {
        System.out.println("İşlem iptal edildi.");
        // Mevcut pencereyi kapatma veya diğer bir işlem
    }
}
