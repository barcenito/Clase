package org.example.examennov.DAO;

import org.example.examennov.Model.Reparacion;
import org.hibernate.Session;

import java.util.List;

public interface ReparacionDao {
	List<Reparacion> obtenerReparacionesPorCoche(int idCoche, Session session);

	void crearReparacion(Session session, Reparacion reparacion);

	void borrarReparacion(Session session, Reparacion reparacion);

	void modificarReparacion(Session session, Reparacion reparacion);
}
