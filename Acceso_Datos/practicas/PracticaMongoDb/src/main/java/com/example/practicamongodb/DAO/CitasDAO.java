package com.example.practicamongodb.DAO;




import com.mongodb.internal.connection.Connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CitasDAO {
	private Connection conexion;
	public CitasDAO(){
	}
	public void conectar() throws ClassNotFoundException, SQLException, IOException {
		Properties configuration = new Properties();
		FileInputStream fileInput = new FileInputStream("src/main/resources/configuration/database.properties");
		configuration.load(fileInput);
		String host = configuration.getProperty("host");
		String port = configuration.getProperty("port");
		String name = configuration.getProperty("name");
		String username = configuration.getProperty("username");
		String password = configuration.getProperty("password");
		Class.forName("com.mysql.cj.jdbc.Driver");
		this.conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC", username, password);
	}

	public void desconectar() throws SQLException {
		this.conexion.close();
	}

	public void guardarCita(Cita cita) throws SQLException {
		String sql = "INSERT INTO Cita (id, fecha, idPaciente, idEspecialidad) VALUES (?, ? ,? ,?)";
		PreparedStatement stm = conexion.prepareStatement(sql);
		stm.setInt(1,cita.getId());
		stm.setTimestamp(2,cita.getFecha());
		stm.setInt(3,cita.getIdPaciente());
		stm.setInt(4,cita.getIdEspecialidad());
		stm.executeUpdate();
	}
	public void eliminarCita(Cita cita) throws SQLException {
		String sql = "DELETE FROM Cita WHERE id = ?";

		PreparedStatement sentencia = conexion.prepareStatement(sql);
		sentencia.setInt(1, cita.getId());
		sentencia.executeUpdate();
	}
	public void modificarCita(Cita cita) throws SQLException{
		String sql = "UPDATE Cita SET fecha = ?, idPaciente = ?, idEspecialidad = ? WHERE id = ?";
		PreparedStatement smt = conexion.prepareStatement(sql);
		smt.setTimestamp(1, cita.getFecha());
		smt.setInt(2, cita.getIdPaciente());
		smt.setInt(3, cita.getIdEspecialidad());
		smt.setInt(4, cita.getId());  
		smt.executeUpdate();
	}

	public List<Cita> obtenerCitas() throws SQLException{
		List<Cita> citas = new ArrayList<>();
		String sql = "SELECT * FROM Cita";
		PreparedStatement smt = conexion.prepareStatement(sql);
		ResultSet results = smt.executeQuery();
		while(results.next()){
			Cita cita = new Cita();
			cita.setId(results.getInt("id"));
			cita.setFecha(results.getTimestamp("fecha"));
			cita.setIdPaciente(results.getInt("idPaciente"));
			cita.setIdEspecialidad(results.getInt("idEspecialidad"));
			citas.add(cita);
		}
		return citas;
	}
	
	public List<Cita> obtenerCitasPorPaciente(int idPaciente) throws SQLException {
    List<Cita> citas = new ArrayList<>();
    String sql = "SELECT * FROM Cita WHERE idPaciente = ?";
    
    PreparedStatement smt = conexion.prepareStatement(sql);
    smt.setInt(1, idPaciente);
    
    ResultSet results = smt.executeQuery();
    
    while (results.next()) {
        Cita cita = new Cita();
        cita.setId(results.getInt("id"));
        cita.setFecha(results.getTimestamp("fecha"));
        cita.setIdPaciente(results.getInt("idPaciente"));
        cita.setIdEspecialidad(results.getInt("idEspecialidad"));
        citas.add(cita);
    }
    
    return citas;
}



}
