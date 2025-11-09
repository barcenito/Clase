package com.example.centromedicohibernate.DAO;

import com.example.centromedicohibernate.models.Cita;
import org.hibernate.Session;

import java.util.List;

public interface CitaDao {

    void  saveCita(Cita cita, Session session);

    Cita getCitaById(int id, Session session);

    List<Cita> getAllCitas(Session session);

    void updateCita(Cita cita,Session session);

    void deleteCitaById(int id,Session session);
}
