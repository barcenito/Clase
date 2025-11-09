package com.example.centromedicohibernate.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
    @Column(name="dni")
	protected String dni;
    @Column(name="passwd")
	protected String passwd;
    @Column(name="nombre")
	protected String nombre;
    @Column(name="direccion")
	protected String direccion;
    @Column(name="telefono")
	protected int telefono;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cita> citas;

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
    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }


    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", passwd='" + passwd + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}
