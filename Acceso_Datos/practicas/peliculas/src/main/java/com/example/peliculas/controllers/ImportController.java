package com.example.peliculas.controllers;

import com.example.peliculas.DAO.PeliculasDAO;
import com.example.peliculas.models.Pelicula;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.swing.text.DateFormatter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ImportController {

	@FXML
	public TextField tfTitulo;
	public TextField tfFecha;
	public TextField tfGenero;
	public TextField tfDirector;
	public Button btImportar;
	public ListView lvPeliculas;
	public PeliculasDAO pelisDAO;

	public List<Pelicula> pelis = new ArrayList<>();
	public Pelicula pSeleccionada = new Pelicula();

	public ImportController(){
		this.pelisDAO = new PeliculasDAO();
	}
	@FXML
	public void importarPeliculas(Event event) {
		this.pelis =  pelisDAO.getPeliculas("src/main/resources/database/peliculas.json");
		List<String> pNames = new ArrayList<>();
		pelis.forEach(peli-> pNames.add(peli.getTitulo()));
		lvPeliculas.setItems(FXCollections.observableList(pNames));
		pelis.forEach(System.out::println);

	}
	public void seleccionarPelicula(Event event){
		String seleccion = (String) lvPeliculas.getSelectionModel().getSelectedItem();
		for(Pelicula p:pelis) {
			if (p.getTitulo().equals(seleccion)) {
				this.pSeleccionada = p;
				break;
			}
		}
		mostrarSeleccion();
	}
	public void mostrarSeleccion(){
		tfTitulo.setText(this.pSeleccionada.getTitulo());
		tfFecha.setText(this.pSeleccionada.getFecha().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		tfDirector.setText(this.pSeleccionada.getDirector());
		tfGenero.setText(this.pSeleccionada.getGenero());
	}
}
