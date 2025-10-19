module com.example.practicamongodb {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;

    // Otros módulos que puedas necesitar
    requires com.google.gson;
    requires java.sql;


    opens com.example.practicamongodb to javafx.fxml;
    exports com.example.practicamongodb;
    exports com.example.practicamongodb.Controllers;
    opens com.example.practicamongodb.Controllers to javafx.fxml;
}