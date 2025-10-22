package org.example.peliculas_hibernate.Model;




import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Pelicula")
public class Pelicula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;


	public String titulo;

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
