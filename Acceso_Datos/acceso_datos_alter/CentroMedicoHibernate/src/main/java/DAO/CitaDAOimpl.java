package DAO;


import model.Cita;
import org.hibernate.Session;

import java.util.List;

public class CitaDAOimpl implements CitaDAO{
    @Override
    public List<Cita> obtenerCitas(Session session, String emailPaciente){
        String hql = "from Cita c where c.paciente.emailID = :emailPaciente";
        List<Cita> lista = session.createQuery(hql, Cita.class).setParameter("emailPaciente", emailPaciente).list();
        return lista;
    }

    @Override
    public void crearCita(Session session, Cita c){

        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
    }

    @Override
    public void modificarCita(Session session, Cita c){

        session.beginTransaction();
        session.update(c);
        session.getTransaction().commit();
    }

    @Override
    public void eliminarCita(Session session, Cita c) {

        session.beginTransaction();
        session.delete(c);
        session.getTransaction().commit();
    }
}
