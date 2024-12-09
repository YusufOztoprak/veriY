<?xml version="1.0" encoding="UTF-8"?>
        <?import javafx.scene.control.*?>
        <?import javafx.scene.layout.*?>

<StackPane xmlns:fx="http://javafx.com/fxml/1" fx:controller="com.example.veriy.PCController">
    <VBox spacing="10" alignment="TOP_CENTER" prefWidth="600" prefHeight="400">
        <!-- Tabloyu oluşturuyoruz -->
        <TableView fx:id="productTable" prefWidth="550">
            <columns>
                <!-- Tablo sütunları -->
                <TableColumn fx:id="idColumn" text="ID" prefWidth="100" />
                <TableColumn fx:id="nameColumn" text="Name" prefWidth="150" />
                <TableColumn fx:id="priceColumn" text="Price" prefWidth="100" />
                <TableColumn fx:id="amountColumn" text="Amount" prefWidth="100" />
                <TableColumn fx:id="screenSizeColumn" text="Screen Size" prefWidth="100" />
            </columns>
        </TableView>

        <!-- Ürün ekleme için input alanları -->
        <HBox spacing="10" alignment="CENTER">
            <TextField fx:id="idField" promptText="Enter ID" />
            <TextField fx:id="nameField" promptText="Enter Name" />
            <TextField fx:id="priceField" promptText="Enter Price" />
            <TextField fx:id="amountField" promptText="Enter Amount" />
            <TextField fx:id="screenSizeField" promptText="Enter Screen Size" />
        </HBox>

        <!-- Ürün ekleme ve silme için butonlar -->
        <HBox spacing="10" alignment="CENTER">
            <Button text="Add Product" onAction="#handleAddProduct" />
            <Button text="Delete Selected" onAction="#handleDeleteProduct" />
        </HBox>
    </VBox>
</StackPane>

