package org.example.examennov.DAO;

import org.example.examennov.Connection.DBConnection;
import org.example.examennov.Model.Coche;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CocheDaoImpl implements CocheDao{
//	@Override
//	public Coche buscarPorMatricula(String matricula) throws SQLException{
//		Coche coche = null;
//		String sql="Select * from coches where matricula=?";
//
//		try (Connection conn = DBConnection.conectar();
//			 PreparedStatement ps = conn.prepareStatement(sql)) {
//
//			ps.setString(1, matricula);
//			ResultSet rs = ps.executeQuery();
//
//			while (rs.next()) {
//				coche = new Coche();
//				coche.setIdCoche(rs.getInt("idcoche"));
//				coche.setMatricula(rs.getString("matricula"));
//				coche.setMarca(rs.getString("marca"));
//				coche.setKm(rs.getInt("km"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return coche;
//	}
	@Override
	public Coche buscarPorMatricula(String matricula, Session session) {
		List<Coche> coches = null;
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			coches = session.createQuery("from Coche", Coche.class).list();
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null){
				transaction.rollback();
			}
		}
		Coche car = null;
		for(Coche c:coches){
			if(c.getMatricula().equals(matricula)){
				car = c;
			}
		}
		return car;
	}

	@Override
	public List<Coche> obtenerCoches(Session session) {
		List<Coche> cars = null;
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			cars = session.createQuery("from Coche", Coche.class).list();
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null){
				transaction.rollback();
			}
			return new ArrayList<>();
		}
		return cars;
	}
}
