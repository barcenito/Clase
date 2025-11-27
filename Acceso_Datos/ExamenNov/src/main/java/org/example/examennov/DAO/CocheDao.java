package org.example.examennov.DAO;

import org.example.examennov.Model.Coche;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface CocheDao {
//	Coche buscarPorMatricula(String matricula) throws SQLException;
	Coche buscarPorMatricula(String matricula, Session session);
	List<Coche> obtenerCoches(Session session);
}
