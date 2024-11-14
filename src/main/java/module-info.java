module com.example.veriy {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.veriy to javafx.fxml;
    exports com.example.veriy;
}