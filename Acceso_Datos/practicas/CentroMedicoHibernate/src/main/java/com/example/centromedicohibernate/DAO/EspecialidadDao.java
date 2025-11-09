package com.example.centromedicohibernate.DAO;

import com.example.centromedicohibernate.models.Especialidad;
import org.hibernate.Session;

import java.util.List;

public interface EspecialidadDao {

    void  saveEspecialidad(Especialidad especialidad, Session session);

    Especialidad getEspecialidadById(int id, Session session);

    List<Especialidad> getAllEspecialidades(Session session);

    void updateEspecialidad(Especialidad especialidad,Session session);

    void deleteEspecialidadById(int id,Session session);
}
