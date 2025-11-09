package com.example.centromedicohibernate.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="especialidad")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected  int id;
    @Column(name="nombreEspecialidad")
    protected  String nombreEspecialidad;

    @OneToMany(mappedBy = "especialidad", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Cita> citas;

	public Especialidad() {
	}

	public Especialidad(int id, String nombreEspecialidad) {
		this.id = id;
		this.nombreEspecialidad = nombreEspecialidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreEspecialidad() {
		return nombreEspecialidad;
	}

	public void setNombreEspecialidad(String nombreEspecialidad) {
		this.nombreEspecialidad = nombreEspecialidad;
	}

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    @Override
    public String toString() {
        return "Especialidad{" +
                "id=" + id +
                ", nombreEspecialidad='" + nombreEspecialidad + '\'' +
                '}';
    }
}
