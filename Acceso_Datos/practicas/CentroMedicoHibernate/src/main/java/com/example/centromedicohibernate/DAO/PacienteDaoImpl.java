package com.example.centromedicohibernate.DAO;

import com.example.centromedicohibernate.models.Paciente;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PacienteDaoImpl implements PacienteDao{
    public PacienteDaoImpl() {
    }

    @Override
    public void savePaciente(Paciente paciente, Session session) {
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(paciente);
            transaction.commit();
        }
        catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }

    }

    @Override
    public Paciente getPacienteById(int id, Session session) {
        Transaction transaction = null;
        Paciente paciente = null;
        try{
            transaction = session.beginTransaction();
            paciente = session.get(Paciente.class, id);
            transaction.commit();
        }
        catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return paciente;
    }

    @Override
    public List<Paciente> getAllPacientes(Session session) {
        List <Paciente> pacientes = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            pacientes = session.createQuery("from Paciente", Paciente.class).list();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            return new ArrayList<>();
        }
        return pacientes;
    }

    @Override
    public void updatePaciente(Paciente paciente, Session session) {
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(paciente);
            transaction.commit();
        }
        catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void deletePacienteById(int id, Session session) {
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.delete(id);
            transaction.commit();
        }
        catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
        }

    }
}
