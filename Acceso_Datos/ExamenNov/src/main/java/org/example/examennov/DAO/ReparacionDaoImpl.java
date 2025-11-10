package org.example.examennov.DAO;

import org.example.examennov.Model.Coche;
import org.example.examennov.Model.Reparacion;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ReparacionDaoImpl implements ReparacionDao{
	@Override
	public List<Reparacion> obtenerReparacionesPorCoche(int idCoche, Session session) {
		Transaction transaction = null;
        List<Reparacion> reparaciones = null;
        try{
            transaction = session.beginTransaction();
            reparaciones = session.createQuery("from Reparacion c where c.idcoche = :idCoche", Reparacion.class).setParameter("idCoche",idCoche).getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
			return new ArrayList<>();
        }
        return reparaciones;
	}

	@Override
	public void crearReparacion(Session session, Reparacion reparacion) {

	}

	@Override
	public void borrarReparacion(Session session, Reparacion reparacion) {

	}

	@Override
	public void modificarReparacion(Session session, Reparacion reparacion) {

	}
}
