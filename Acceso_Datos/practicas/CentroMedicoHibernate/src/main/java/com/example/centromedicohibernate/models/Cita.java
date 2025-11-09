package com.example.centromedicohibernate.models;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
    @Column(name="fecha")
	protected Timestamp fecha;
    @Column(name="idPaciente", insertable = false, updatable = false)
	protected int idPaciente;
    @Column(name="idEspecialidad", insertable = false, updatable = false)
	protected int idEspecialidad;
    @ManyToOne
    @JoinColumn(name="idPaciente", referencedColumnName = "id")
    protected Paciente paciente;

    @ManyToOne
    @JoinColumn(name="idEspecialidad", referencedColumnName = "id" )
    protected Especialidad especialidad;

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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", idPaciente=" + idPaciente +
                ", idEspecialidad=" + idEspecialidad +
                '}';
    }
}
