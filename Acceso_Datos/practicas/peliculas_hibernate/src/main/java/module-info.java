module org.example.peliculas_hibernate {
	requires javafx.controls;
	requires javafx.fxml;

	requires org.controlsfx.controls;
	requires com.dlsc.formsfx;
	requires javafx.base;
	requires org.hibernate.orm.core;
	requires java.persistence;

	opens org.example.peliculas_hibernate to javafx.fxml;
	exports org.example.peliculas_hibernate;
	exports org.example.peliculas_hibernate.Controller;
	opens org.example.peliculas_hibernate.Controller to javafx.fxml;
}