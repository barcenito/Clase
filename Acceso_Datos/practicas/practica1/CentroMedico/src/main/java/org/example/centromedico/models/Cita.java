package org.example.centromedico.models;

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
