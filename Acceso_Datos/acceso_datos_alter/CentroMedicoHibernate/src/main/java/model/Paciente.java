package model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "paciente")
public class Paciente {
    @Id
    @Column(name = "emailID")
    private String emailID;

    @Column(name = "dni")
    private String dni;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    public Paciente(String emailID, String dni, String nombre, String direccion, String telefono) {
        this.emailID = emailID;
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Paciente() {
    }

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Cita> citas;//una cita solo puede tener un paciente

    public String getEmailID() {
        return emailID;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}
