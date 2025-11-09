module com.example.centromedicohibernate {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.persistence;
    requires jdk.jfr;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens com.example.centromedicohibernate to javafx.fxml;
    opens com.example.centromedicohibernate.controllers to javafx.fxml;
    opens com.example.centromedicohibernate.models to javafx.fxml, org.hibernate.orm.core;
    opens com.example.centromedicohibernate.utils to javafx.fxml;
    opens com.example.centromedicohibernate.DAO to javafx.fxml;
    exports com.example.centromedicohibernate;
}