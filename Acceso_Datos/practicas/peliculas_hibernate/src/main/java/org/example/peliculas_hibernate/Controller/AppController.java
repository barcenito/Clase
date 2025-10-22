package org.example.peliculas_hibernate.Controller;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.example.peliculas_hibernate.DAO.PeliculasDAOImp;
import org.example.peliculas_hibernate.Model.Pelicula;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class AppController {

	@FXML
	public TextField tfTitulo;
	public TextField tfFecha;
	public TextField tfGenero;
	public TextField tfDirector;
	public Button btImportar;
	public ListView lvPeliculas;
	public PeliculasDAOImp pelisDAO;

	public List<Pelicula> pelis = new ArrayList<>();
	public Pelicula pSeleccionada = new Pelicula();

	public AppController(){
		this.pelisDAO = new PeliculasDAOImp();
	}
	@FXML
	public void importarPeliculas(Event event) {
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