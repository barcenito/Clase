package com.example.centromedicohibernate.DAO;

import com.example.centromedicohibernate.models.Paciente;
import org.hibernate.Session;

import java.util.List;

public interface PacienteDao {

    void  savePaciente(Paciente paciente, Session session);

    Paciente getPacienteById(int id, Session session);

    List<Paciente> getAllPacientes(Session session);

    void updatePaciente(Paciente paciente,Session session);

    void deletePacienteById(int id,Session session);
}
