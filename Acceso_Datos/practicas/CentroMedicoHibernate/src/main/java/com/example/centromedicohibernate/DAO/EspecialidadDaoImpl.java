package com.example.centromedicohibernate.DAO;

import com.example.centromedicohibernate.models.Especialidad;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EspecialidadDaoImpl implements EspecialidadDao{
    public EspecialidadDaoImpl() {
    }

    @Override
    public void saveEspecialidad(Especialidad especialidad, Session session) {
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(especialidad);
            transaction.commit();
        }
        catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }

    }

    @Override
    public Especialidad getEspecialidadById(int id, Session session) {
        Transaction transaction = null;
        Especialidad especialidad = null;
        try{
            transaction = session.beginTransaction();
            especialidad = session.get(Especialidad.class, id);
            transaction.commit();
        }
        catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
        return especialidad;
    }

    @Override
    public List<Especialidad> getAllEspecialidades(Session session) {
        List <Especialidad> especialidades = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            especialidades = session.createQuery("FROM Especialidad", Especialidad.class).list();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            return new ArrayList<>();
        }
        return especialidades;
    }

    @Override
    public void updateEspecialidad(Especialidad especialidad, Session session) {
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(especialidad);
            transaction.commit();
        }
        catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public void deleteEspecialidadById(int id, Session session) {
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
