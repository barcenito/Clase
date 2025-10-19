package com.example.practicamongodb.Models;

import java.sql.Timestamp;

public class Cita {
	protected int id;
	protected Timestamp fecha;
	protected int idPaciente;
	protected int idEspecialidad;

	public Cita() {
	}

	public Cita(int id, Timestamp fecha, int idPaciente, int idEspecialidad) {
		this.id = id;
		this.fecha = fecha;
		this.idPaciente = idPaciente;
		this.idEspecialidad = idEspecialidad;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public int getId() {
		return id;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public int getIdEspecialidad() {
		return idEspecialidad;
	}
}
