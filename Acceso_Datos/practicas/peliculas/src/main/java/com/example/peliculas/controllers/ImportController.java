package com.example.peliculas.controllers;

import com.example.peliculas.DAO.PeliculasDAO;
import com.example.peliculas.models.Pelicula;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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


	public ImportController(){
		this.pelisDAO = new PeliculasDAO();
	}
	@FXML
	protected void importarPeliculas() {
		List<Pelicula> pelis = new ArrayList<>();
		pelis =  pelisDAO.getPeliculas("database/peliculas.json");
		pelis.forEach(System.out::println);

	}
	protected void seleccionarPelicula(){
		return;
	}
}
