package org.example.peliculas_hibernate.DAO;


import org.example.peliculas_hibernate.Model.Pelicula;
import org.hibernate.Session;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PeliculasDAOImp implements PeliculasDAO{
	public List<Pelicula> peliculas;
	public PeliculasDAOImp(){
		this.peliculas = new ArrayList<>();
	}

	@Override
	public void SavePelicula(Session session, Pelicula pelicula) {

	}

	@Override
	public Pelicula GetPeliculabyId(Session session, int id) {
		return null;
	}

	@Override
	public void UpdatePelicula(Session session, Pelicula pelicula) {

	}

	@Override
	public void DeletePeliculaById(Session session, Pelicula pelicula) {

	}

	@Override
	public List<Pelicula> GetAllPeliculas(Session session) {
		return List.of();
	}
}
