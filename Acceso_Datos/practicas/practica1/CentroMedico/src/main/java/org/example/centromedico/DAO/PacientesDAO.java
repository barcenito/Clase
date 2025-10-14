package org.example.centromedico.DAO;

import org.example.centromedico.models.Paciente;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.example.centromedico.util.R;

public class PacientesDAO {
	private Connection connection;

	public PacientesDAO() {
	}

	public void connect() throws ClassNotFoundException, SQLException, IOException {
		Properties configuration = new Properties();
		System.out.println(System.getProperty("user.dir"));
		InputStream fileInput = R.getProperties("\\database.properties");
		if (fileInput == null) {
            throw new IOException("No se pudo encontrar el archivo database.properties en resources/configuration/");
        }
		configuration.load(fileInput);
		String host = configuration.getProperty("host");
		String port = configuration.getProperty("port");
		String name = configuration.getProperty("name");
		String username = configuration.getProperty("username");
		String password = configuration.getProperty("password");

		Class.forName("com.mysql.cj.jdbc.Driver");
		this.connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
				username, password);
	}

	public List<Paciente> getPacientes() throws SQLException {
		List<Paciente> pacientes = new ArrayList<>();
		String sql = "SELECT * FROM paciente";
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
	public Paciente getPaciente(int idPaciente) throws SQLException{
		Paciente nuevoPaciente = null;
		String sql = "SELECT * FROM paciente where id=?";
		PreparedStatement smt = this.connection.prepareStatement(sql);
		smt.setInt(1,idPaciente);
		ResultSet result = smt.executeQuery();
		if (result.next()) {
			nuevoPaciente = new Paciente();
			nuevoPaciente.setId(result.getInt("id"));
			nuevoPaciente.setDni(result.getString("dni"));
			nuevoPaciente.setPasswd(result.getString("passwd"));
			nuevoPaciente.setNombre(result.getString("nombre"));
			nuevoPaciente.setDireccion(result.getString("direccion"));
			nuevoPaciente.setTelefono(result.getInt("telefono"));
    }
		return nuevoPaciente;
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
		smt.executeUpdate();
	}
}
