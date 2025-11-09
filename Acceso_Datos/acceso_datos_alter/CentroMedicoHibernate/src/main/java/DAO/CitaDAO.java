package DAO;

import model.Cita;
import org.hibernate.Session;

import java.util.List;

public interface CitaDAO {

    List<Cita> obtenerCitas(Session session, String emailPaciente);

    void crearCita(Session session, Cita c);

    void modificarCita(Session session, Cita c);

    void eliminarCita(Session session, Cita c);
}
