module org.example.centromedico {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
	requires mysql.connector.java;

	opens org.example.centromedico to javafx.fxml;
    exports org.example.centromedico;
    exports org.example.centromedico.controllers;
    opens org.example.centromedico.controllers to javafx.fxml;
}