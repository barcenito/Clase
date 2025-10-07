package org.example.centromedico.DAO;

import org.example.centromedico.models.Cita;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class CitasDAO {
	private Connection conexion;
	public CitasDAO(){
	}
	public void conectar() throws ClassNotFoundException, SQLException, IOException {
		Properties configuration = new Properties();
		configuration.load(R.getProperties("database.properties"));
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

	public void guardarCita(Cita cita){
		String sql = "INSERT INTO citas (id, )"
	}
}
