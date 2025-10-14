package com.example.peliculas.models;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Pelicula {
	public int id;
	public String titulo;

	// añadimos la anotacion de JsonFormat para que cuando el objectreader bsuque este campo nos lo devuelva con el
	// formato que queremos (no lo devuelve con el formato deseado...) (se puede hacer con numeros usando pattern = "#0.00", o sacando booleans como strings pattern = "yes/no")
	//podriamos establecer localDate como string y luego convertirla a Date si es necesario como alternativa
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	public LocalDate fecha;

	public String genero;
	public String director;

	public Pelicula() {
	}

	public Pelicula(int id, String titulo, LocalDate fecha, String genero, String director) {
		this.id  = id;
		this.titulo = titulo;
		this.fecha = fecha;
		this.genero = genero;
		this.director = director;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	@Override
	public String toString() {
		return "Pelicula{" +
				"titulo='" + titulo + '\'' +
				", fecha=" + fecha +
				", genero='" + genero + '\'' +
				", director='" + director + '\'' +
				'}';
	}
}
