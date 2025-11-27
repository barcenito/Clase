package org.example.examennov.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.examennov.DAO.CocheDao;
import org.example.examennov.DAO.CocheDaoImpl;
import org.example.examennov.Model.Coche;
import org.example.examennov.Model.Reparacion;
import org.example.examennov.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador principal de la interfaz de citas.
 * Se encarga de coordinar las acciones entre la vista (FXML),
 * los modelos (Paciente, Cita, Especialidad)
 * y los DAOs (acceso a datos con JDBC y Hibernate).
 */
public class ReparacionesController {

    // --- Elementos del formulario (inyectados desde el FXML) ---
    @FXML private TextField txtMatricula, txtMarca, txtKm, txtDescripcion, txtPrecio, txtNumero;
    @FXML private DatePicker dpFecha;
    @FXML private TableView<Reparacion> tableReparaciones;
    @FXML private TableColumn<Reparacion, Integer> colNumero;
    @FXML private TableColumn<Reparacion, LocalDate> colFecha;
	@FXML private TableColumn<Reparacion, String> colDescripcion;
	@FXML private TableColumn<Reparacion, Integer> colPrecio;
	@FXML private TableColumn<Reparacion, Boolean> pagado;

	@FXML private Button btnLimpiar, btnBorrar, btnNueva, btnModificar, btnCrear, btnDatosV, btnVer;
	@FXML private RadioButton radioYesBtn, radioNoBtn;

	private static SessionFactory factory = HibernateUtil.getSessionFactory();

    // --- DAOs y variables auxiliares ---
//    PacienteDAO pacienteDAO = new PacienteDAOimpl();      // Acceso a pacientes con JDBC
	CocheDaoImpl cocheDAO = new CocheDaoImpl();
//    CitasDAO citasDAO = new CitasDAOimpl();               // Acceso a citas (JDBC y Hibernate)
    private Coche SelectedCar;                                 // Paciente actualmente cargado
//    CitasMongoDAO citasMongoDAO = new CitasMongoDAOimpl();// Acceso a MongoDB

    // Mapper para leer el JSON de especialidades
//    public static final ObjectMapper JSON_MAPPER = new ObjectMapper();


    @FXML
    public void initialize() {
        // Configuramos las columnas de la tabla
        colNumero.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getIdReparacion()).asObject());
        colFecha.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getFechareparacion()));
        colDescripcion.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDescripcion().toString()));
		colPrecio.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getPrecio()).asObject());

        // Cargamos las especialidades desde el JSON

        // Evento: al pulsar ENTER en el DNI se busca el paciente
        txtMatricula.setOnAction(e -> {
            try {
				buscarCochePorMatricula();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Evento: al seleccionar una cita, se cargan sus datos en los campos
        tableReparaciones.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cargarDatosCita(newSelection);
            }
        });
    }

    /**
     * Busca un paciente en MySQL por su DNI usando JDBC.
     * Si lo encuentra, carga sus datos en los campos de texto y los bloquea.
     */
    private void buscarCochePorMatricula() throws SQLException {
        Session session = null;
		String matricula = txtMatricula.getText();
		try {
			session = factory.openSession();
			Coche coche = cocheDAO.buscarPorMatricula(matricula, session);

			if (coche != null) {
				SelectedCar = coche;
				txtMatricula.setText(coche.getMatricula());
				txtMarca.setText(coche.getMarca());
				txtKm.setText(Integer.toString(coche.getKm()));

				// Deshabilitamos los campos (no se pueden editar)
				txtMarca.setDisable(true);
				txtMatricula.setDisable(true);
				txtKm.setDisable(true);
			} else {
				mostrarError("Coche no encontrado con matricula: " + matricula);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
    }

    /**
     * Muestra todas las citas del paciente seleccionado (vía JDBC).
     * Además muestra avisos si no hay citas o si tiene una para hoy.
     */
//    @FXML
//    private void verCitasPaciente() throws SQLException {
//        int idPaciente = pac.getIdPaciente();
//        List<Cita> citas = citasDAO.obtenerCitasPorPaciente(idPaciente);
//
//        ObservableList<Cita> citasFX = FXCollections.observableArrayList(citas);
//        tableCitas.setItems(citasFX);
//
//        boolean tieneCitaHoy = citas.stream().anyMatch(c -> c.getFechaCita().equals(LocalDate.now()));
//        if (tieneCitaHoy) {
//            mostrarInfo("¡El paciente tiene una cita para hoy!");
//        }
//        if (citas.isEmpty()) {
//            mostrarWarning("El paciente no tiene citas.");
//        }
//    }

    /**
     * Carga en los campos los datos de la cita seleccionada en la tabla.
     */
    private void cargarDatosCita(Reparacion reparacion) {
        txtNumero.setText(String.valueOf(reparacion.getIdReparacion()));
        dpFecha.setValue(reparacion.getFechareparacion());
		txtDescripcion.setText(reparacion.getDescripcion());
		txtPrecio.setText(String.valueOf(reparacion.getPrecio()));
		if(reparacion.isPagado()){
			radioYesBtn.selectedProperty();
		}else{
			radioNoBtn.selectedProperty();
		}




    }

    /**
     * Limpia los campos relacionados con las citas.
     */
//    @FXML
//    private void limpiarCamposCita() {
//        txtNumero.clear();
//        dpFecha.setValue(null);
//        cmbEspecialidad.getSelectionModel().select(null);
//        tableCitas.getSelectionModel().clearSelection();
//    }

    /**
     * Crea una nueva cita usando Hibernate y actualiza la tabla.
     * También podría insertarse en MongoDB.
     */
//    @FXML
//    private void crearNuevCita() {
//        if (pac == null) {
//            mostrarError("Primero busca y selecciona el paciente.");
//            return;
//        }
//        LocalDate fecha = dpFecha.getValue();
//        Especialidad especialidadCombo = cmbEspecialidad.getValue();
//        if (fecha == null || especialidadCombo == null) {
//            mostrarError("Completa los campos de fecha y especialidad.");
//            return;
//        }
//
//        // Creamos una sesión de Hibernate para insertar la cita
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Especialidad especialidadHibernate = session.get(Especialidad.class, especialidadCombo.getIdEspecialidad());
//            if (especialidadHibernate == null) {
//                mostrarError("La especialidad seleccionada no existe en la base de datos.");
//                return;
//            }
//            session.beginTransaction();
//
//            // Creamos el objeto cita y lo persistimos
//            Cita nuevaCita = new Cita();
//            nuevaCita.setFechaCita(fecha);
//            nuevaCita.setEspecialidad(especialidadHibernate);
//            nuevaCita.setPaciente(pac);
//
//            citasDAO.crearCita(session, nuevaCita);
//            session.getTransaction().commit();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            mostrarError("Error guardando la cita con Hibernate.");
//            return;
//        }
//
//        actualizarCitasTableView();
//        limpiarCamposCita();
//        mostrarInfo("¡Nueva cita guardada!");
//    }

    /**
     * Borra una cita seleccionada utilizando Hibernate.
     */
//    @FXML
//    private void borrarCitaSeleccionada() {
//        Cita cita = tableCitas.getSelectionModel().getSelectedItem();
//        if (cita == null) {
//            mostrarError("Selecciona una cita para borrar.");
//            return;
//        }
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            citasDAO.borrarCita(session, cita);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            mostrarError("Error al borrar la cita.");
//            return;
//        }
//        actualizarCitasTableView();
//    }

    /**
     * Modifica la cita seleccionada con los nuevos datos.
     * Si no se reasigna el paciente, la cita desaparecerá (FK nula en MySQL).
     */
//    @FXML
//    private void modificarCitaSeleccionada() {
//        Cita citaSeleccionada = tableCitas.getSelectionModel().getSelectedItem();
//        if (citaSeleccionada == null) {
//            mostrarError("Selecciona una cita para modificar.");
//            return;
//        }
//        LocalDate fechaModificada = dpFecha.getValue();
//        Especialidad espModificada = cmbEspecialidad.getValue();
//        if (fechaModificada == null || espModificada == null) {
//            mostrarError("Elige bien fecha y especialidad.");
//            return;
//        }
//
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Especialidad espHibernate = session.get(Especialidad.class, espModificada.getIdEspecialidad());
//            if (espHibernate == null) {
//                mostrarError("La especialidad seleccionada no existe en la base de datos.");
//                return;
//            }
//
//            session.beginTransaction();
//            citaSeleccionada.setFechaCita(fechaModificada);
//            citaSeleccionada.setEspecialidad(espHibernate);
//            citaSeleccionada.setPaciente(pac); // 🔥 Importante para mantener la FK correcta
//            citasDAO.modificarCita(session, citaSeleccionada);
//            session.getTransaction().commit();
//
//            mostrarInfo("Cita actualizada");
//            actualizarCitasTableView();
//            limpiarCamposCita();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            mostrarError("Error al modificar la cita.");
//        }
//    }

    /**
     * Actualiza los datos mostrados en la tabla con los últimos datos de MySQL.
     */
//    private void actualizarCitasTableView() {
//        try {
//            List<Cita> citas = citasDAO.obtenerCitasPorPaciente(pac.getIdPaciente());
//            tableCitas.setItems(FXCollections.observableArrayList(citas));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    /**
     * Carga las especialidades desde el archivo JSON.
     */
//    private void cargaEspecialidad() {
//        cmbEspecialidad.getItems().clear();
//        ArrayList<Especialidad> especialidad;
//        try  {
//            especialidad = JSON_MAPPER.readValue(
//                    new File("src/main/resources/JSON/especialidades.json"),
//                    JSON_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, Especialidad.class)
//            );
//            ObservableList<Especialidad> datos = FXCollections.observableArrayList(especialidad);
//            cmbEspecialidad.setItems(datos);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    // --- Métodos auxiliares para mostrar alertas ---
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarWarning(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    //metodo para abrir la interfaz de crear paciente
//    @FXML
//    private void abrirInterfazPaciente(){
//		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/pacientes.fxml"));
//			Scene scene = new Scene(loader.load());
//
//			Stage stage = new Stage();
//			stage.setTitle("Crear Paciente");
//			stage.setScene(scene);
//			stage.show();
//
//			Stage currenStage = (Stage) btnCrear.getScene().getWindow();
//			currenStage.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.out.println("Error al cargar la interfaz");
//		}
//    }
}
