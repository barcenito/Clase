package com.juan.CRUD_Hibernate_cfg.Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Usuario")
public class Usuario   {

	@Id
	@Column(name="Id")
	private int id;

	@Column(name="nombre")
	private String nombre;

	@Column(name="fechaNacimiento")
	private LocalDate born;

	@Column(name="apellidos")
	private String ape;

	public Usuario(){
	}

	public Usuario(int id, String nombre, LocalDate born, String ape) {
		this.id = id;
		this.nombre = nombre;
		this.born = born;
		this.ape = ape;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getBorn() {
		return born;
	}

	public void setBorn(LocalDate born) {
		this.born = born;
	}

	public String getApe() {
		return ape;
	}

	public void setApe(String ape) {
		this.ape = ape;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", born=" + born + ", ape=" + ape + "]";
	}


}

