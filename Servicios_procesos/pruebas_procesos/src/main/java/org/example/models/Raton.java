package org.example.models;

public class Raton implements Runnable{
	protected double time;
	protected String nombre;
	public Raton(){}

	public Raton(String nombre) {
		this.nombre = nombre;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void run() {
		System.out.println("el raton esta comiendo");
	}
}
