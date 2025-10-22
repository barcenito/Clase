package org.example.peliculas_hibernate.DAO;

import org.example.peliculas_hibernate.Model.Pelicula;
import org.hibernate.Session;

import java.util.List;

public interface PeliculasDAO {
	void SavePelicula(Session session, Pelicula pelicula);
	Pelicula GetPeliculabyId(Session session, int id);
	void UpdatePelicula(Session session, Pelicula pelicula);
	void DeletePeliculaById(Session session, Pelicula pelicula);
	List<Pelicula> GetAllPeliculas(Session session);
}
