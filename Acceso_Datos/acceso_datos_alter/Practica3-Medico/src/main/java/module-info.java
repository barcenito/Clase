module org.example.practica3medico {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.practica3medico to javafx.fxml;
    exports org.example.practica3medico;
}