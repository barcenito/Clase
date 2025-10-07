package org.example.centromedico.models;

public class Paciente {
	protected int id;
	protected String dni;
	protected String passwd;
	protected String nombre;
	protected String direccion;
	protected int telefono;

	public Paciente() {
	}

	public Paciente(int id, String dni, String passwd, String nombre, String direccion, int telefono) {
		this.id = id;
		this.dni = dni;
		this.passwd = passwd;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
}
