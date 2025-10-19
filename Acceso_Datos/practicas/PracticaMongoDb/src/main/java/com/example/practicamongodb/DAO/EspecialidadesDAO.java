package com.example.practicamongodb.DAO;

import org.example.centromedico.models.Especialidad;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EspecialidadesDAO {
    private Connection connection;

    public EspecialidadesDAO() {
    }

    public void connect() throws ClassNotFoundException, SQLException, IOException {
        Properties configuration = new Properties();
        FileInputStream fileInput = new FileInputStream("src/main/resources/configuration/database.properties");
        if(fileInput == null){
            System.out.println("qe mierdas pasa con esta puta mierda citas");
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

    public List<Especialidad> getEspecialidades() throws SQLException {
        List<Especialidad> especialidades = new ArrayList<>();
        String sql = "SELECT * FROM especialidad";
        PreparedStatement stm = this.connection.prepareStatement(sql);
        ResultSet resultado = stm.executeQuery();
        while (resultado.next()) {
            Especialidad especialidad = new Especialidad();
            especialidad.setId(resultado.getInt("id"));
            especialidad.setNombreEspecialidad(resultado.getString("nombreEspecialidad"));
            especialidades.add(especialidad);
        }
        return especialidades;
    }

    public Especialidad getEspecialidad(int idEspecialidad) throws SQLException {
        Especialidad nuevaEspecialidad = null;
        String sql = "SELECT * FROM especialidad WHERE id=?";
        PreparedStatement smt = this.connection.prepareStatement(sql);
        smt.setInt(1, idEspecialidad);
        ResultSet result = smt.executeQuery();
        if (result.next()) {
            nuevaEspecialidad = new Especialidad();
            nuevaEspecialidad.setId(result.getInt("id"));
            nuevaEspecialidad.setNombreEspecialidad(result.getString("nombreEspecialidad"));
        }
        return nuevaEspecialidad;
    }

    public void updateEspecialidad(Especialidad especialidad) throws SQLException {
        String sql = "UPDATE especialidad SET nombreEspecialidad = ? WHERE id = ?";
        PreparedStatement smt = this.connection.prepareStatement(sql);
        smt.setString(1, especialidad.getNombreEspecialidad());
        smt.setInt(2, especialidad.getId());
        smt.executeUpdate();
    }

    public void insertEspecialidad(Especialidad especialidad) throws SQLException {
        String sql = "INSERT INTO especialidad (nombreEspecialidad) VALUES (?)";
        PreparedStatement smt = this.connection.prepareStatement(sql);
        smt.setString(1, especialidad.getNombreEspecialidad());
        smt.executeUpdate();
    }

    public void deleteEspecialidad(int idEspecialidad) throws SQLException {
        String sql = "DELETE FROM especialidad WHERE id = ?";
        PreparedStatement smt = this.connection.prepareStatement(sql);
        smt.setInt(1, idEspecialidad);
        smt.executeUpdate();
    }
}