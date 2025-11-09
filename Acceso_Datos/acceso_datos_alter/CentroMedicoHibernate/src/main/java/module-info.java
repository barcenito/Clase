module org.example.centromedicohibernate {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.naming;
    requires java.persistence;
    requires com.fasterxml.jackson.databind;

    exports Controller;
    opens Controller to javafx.fxml;

    exports model;
    opens model to javafx.fxml, org.hibernate.orm.core, com.fasterxml.jackson.databind;

    opens org.example.centromedicohibernate to javafx.fxml;
    exports org.example.centromedicohibernate;
}
