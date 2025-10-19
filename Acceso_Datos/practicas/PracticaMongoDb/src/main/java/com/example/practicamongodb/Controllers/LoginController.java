package com.example.practicamongodb.Controllers;

import com.example.practicamongodb.DAO.PacientesDAO;
import com.example.practicamongodb.Util.HashUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import com.example.practicamongodb.Models.Paciente;

import java.io.IOException;

public class LoginController {
    public TextField userField;
    public PasswordField passwordField;
    public Button loginButton;
    public Label errorOutput;

    private PacientesDAO pacientesDAO;

    public LoginController() {
        pacientesDAO = new PacientesDAO();
        try {
            pacientesDAO.connect();
        } catch (IOException ioe) {
            mostrarError("Error al cargar la configuración o conectar con la base de datos");
            System.out.println(ioe);
            System.exit(0);
        }
    }

    public void onLogin() {
        String user = userField.getText();
        String passwd_inp = HashUtil.sha256(passwordField.getText());

        try {
            int _id = Integer.parseInt(user);
            Paciente userob = pacientesDAO.getPaciente(_id);

            if(userob != null && userob.getId() == _id && userob.getPasswd().equals(passwd_inp)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/practicamongodb/citas.fxml"));
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
        } catch(NumberFormatException ex) {
            errorOutput.setText("El ID debe ser un número");
        } catch(Exception ex) {
            errorOutput.setText("Error al consultar la base de datos");
            ex.printStackTrace();
        }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}