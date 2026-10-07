module org.sovon9.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.sovon9.demo to javafx.fxml;
    exports org.sovon9.demo;
}