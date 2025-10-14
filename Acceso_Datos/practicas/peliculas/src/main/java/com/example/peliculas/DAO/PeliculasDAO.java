package com.example.peliculas.DAO;


import com.example.peliculas.models.Pelicula;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PeliculasDAO {
	public ObjectMapper mapper;
	public List<Pelicula> peliculas;
	public PeliculasDAO(){
		this.mapper = new ObjectMapper();
		this.peliculas = new ArrayList<>();
		this.mapper.registerModule(new JavaTimeModule());
	}


	public List<Pelicula> getPeliculas(String filePath){
		File file = new File(filePath);
		try {
			this.peliculas = mapper.readValue(file, new TypeReference<List<Pelicula>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.peliculas;
	}

}
