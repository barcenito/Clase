package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "cita")
public class Cita implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "NCita")
    private int NCita;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "nombreEsp")
    private String nombreEsp;

    @ManyToOne
    @JoinColumn(name = "fk_emailID_Paciente", referencedColumnName = "emailID")
    private Paciente paciente;//un paciente puede tener muchas listas

    public Cita() {
    }

    public Cita(int NCita, LocalDate fecha, String nombreEsp, Paciente paciente) {
        this.NCita = NCita;
        this.fecha = fecha;
        this.nombreEsp = nombreEsp;
        this.paciente = paciente;
    }

    public int getNCita() {
        return NCita;
    }

    public void setNCita(int NCita) {
        this.NCita = NCita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEspecialidad() {
        return nombreEsp;
    }

    public void setEspecialidad(String nombreEsp) {
        this.nombreEsp = nombreEsp;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

}
