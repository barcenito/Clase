package org.example.centromedico.DAO;

import org.example.centromedico.models.Paciente;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import org.example.centromedico.util.R;

public class UsersDAO {
	private Connection connection;

	public UsersDAO() {
	}


	public void connect() throws ClassNotFoundException, SQLException, IOException {
		Properties configuration = new Properties();
		configuration.load(R.getProperties("database.properties"));
		String host = configuration.getProperty("host");
		String port = configuration.getProperty("port");
		String name = configuration.getProperty("name");
		String username = configuration.getProperty("username");
		String password = configuration.getProperty("password");

		Class.forName("com.mysql.cj.jdbc.Driver");
		this.connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
				username, password);
	}

	public ArrayList<Paciente> getPacientes() throws SQLException {
		ArrayList<Paciente> pacientes = new ArrayList<>();
		String sql = "FROM pacientes SELECT *";
		PreparedStatement stm = this.connection.prepareStatement(sql);
		ResultSet resultado = stm.executeQuery();
		while(resultado.next()){
			Paciente paciente = new Paciente();
			paciente.setId(resultado.getInt("id"));
			paciente.setDni(resultado.getString("dni"));
			paciente.setPasswd(resultado.getString("passwd"));
			paciente.setNombre(resultado.getString("nombre"));
			paciente.setTelefono(resultado.getInt("telefono"));
			pacientes.add(paciente);
		}
		return pacientes;
	}
	public Paciente getPaciente(int idPaciente){
		Paciente nuevoPaciente = new Paciente();
		String sql = "SELECT paciente where id=?";
		PreparedStatement smt = this.connection.prepareStatement(sql);
		ResultSet result = smt.executeQuery();

	}

	public void updatePaciente(Paciente paciente) throws SQLException {
		String sql = "UPDATE paciente SET dni = ?, passwd = ? , nombre = ?, direccion = ?, telefono = ?  where id = ? ";
		PreparedStatement smt = this.connection.prepareStatement(sql);
		smt.setString(1,paciente.getDni());
		smt.setString(2,paciente.getPasswd());
		smt.setString(3,paciente.getNombre());
		smt.setString(4,paciente.getDireccion());
		smt.setInt(5,paciente.getTelefono());
		smt.setInt(6,paciente.getId());
		smt.executeQuery();
	}
	public void create(){

	}
	public void delete(){

	}
}
