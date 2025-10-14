module com.example.peliculas {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.datatype.jsr310;
	requires org.controlsfx.controls;
	requires java.desktop;

	opens com.example.peliculas to javafx.fxml;
	opens com.example.peliculas.DAO to javafx.fxml;
	opens com.example.peliculas.models to javafx.fml;
	opens com.example.peliculas.controllers to javafx.fml;
	exports com.example.peliculas;
	exports com.example.peliculas.controllers;
	exports com.example.peliculas.DAO;
	exports com.example.peliculas.models;
}