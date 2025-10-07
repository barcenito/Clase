package org.example.centromedico.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.centromedico.DAO.PacientesDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.example.centromedico.models.Paciente;
import org.example.centromedico.util.HashUtil;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
	public TextField userField;
	public PasswordField passwordField;
	public Button loginButton;
	public Label errorOutput;

	private PacientesDAO pacientesDAO;

	public LoginController() {
		pacientesDAO = new PacientesDAO();
		try{
			pacientesDAO.connect();
		} catch (SQLException sqle) {
			this.errorOutput.setText("Error al conectar con la base de datos");
		} catch (ClassNotFoundException cnfe) {
			this.errorOutput.setText("Error al iniciar la aplicación");
		} catch (IOException ioe) {
			this.errorOutput.setText("Error al cargar la configuración");
		}
		System.out.println("CONEXION REALIZADA");
	}
	public void onLogin(){

		String user = userField.getText();
		String passwd_inp = HashUtil.sha256(passwordField.getText());
		try {
			int _id = Integer.parseInt(user);
			Paciente userob = pacientesDAO.getPaciente(_id);

			if(userob != null && userob.getId() == _id && userob.getPasswd().equals(passwd_inp)){

				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/centromedico/citas.fxml"));
					Scene scene = new Scene(loader.load());

					CitasController citasController = loader.getController();
					citasController.setPaciente(userob);

					Stage stage = new Stage();
					stage.setTitle("Formulario Paciente");
					stage.setScene(scene);
					stage.show();
					//cerramos la ventana de login
					Stage currentStage = (Stage) loginButton.getScene().getWindow();
					currentStage.close();
				} catch (IOException e) {
					errorOutput.setText("Error al cargar la pantalla de citas");
					e.printStackTrace();
				}
			} else {
				errorOutput.setText("Credenciales incorrectas");
			}
		} catch(NumberFormatException ex){
			errorOutput.setText("El ID debe ser un número");
		} catch(SQLException ex) {
			errorOutput.setText("Error al consultar la base de datos");
			ex.printStackTrace();
		}
	}
}
