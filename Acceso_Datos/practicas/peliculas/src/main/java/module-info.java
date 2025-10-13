module com.example.peliculas {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.datatype.jsr310;
	requires org.controlsfx.controls;

	opens com.example.peliculas to javafx.fxml;
	opens com.example.peliculas.DAO to javafx.fxml;
	exports com.example.peliculas;
}