package com.example.peliculas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// para que funcione sin el modules_info
//--module-path "C:\Users\<TU_USUARIO>\.m2\repository\org\openjfx\javafx-controls\21.0.6" --add-modules javafx.controls,javafx.fxml
public class Main extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("peliculas.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 320, 240);
		stage.setTitle("Hello!");
		stage.setScene(scene);
		stage.show();
	}
}
