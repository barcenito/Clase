package com.example.practicamongodb.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.example.practicamongodb.DAO.CitasDAO;
import com.example.practicamongodb.DAO.EspecialidadesDAO;
import com.example.practicamongodb.Models.Cita;
import com.example.practicamongodb.Models.Especialidad;
import com.example.practicamongodb.Models.Paciente;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CitasController implements Initializable {
    // Campos para la información de citas
    @FXML private TextField numeroCitaField;
    @FXML private DatePicker fechaCitaDatePicker;
    @FXML private TextField horaCitaField;
    @FXML private Button seleccionarFechaBtn;

    // Campos para la información del paciente
    @FXML private TextField dniField;
    @FXML private TextField nombreField;
    @FXML private TextField direccionField;
    @FXML private TextField telefonoField;
    @FXML private Button verCitasBtn;

    // Selector de especialidad
    @FXML private ComboBox<String> especialidadCombo;

    // Tabla de citas y sus columnas
    @FXML private TableView<Cita> citasTableView;
    @FXML private TableColumn<Cita, Integer> numCitaColumn;
    @FXML private TableColumn<Cita, String> fechaColumn;
    @FXML private TableColumn<Cita, String> especialidadColumn;

    // Botones de acción
    @FXML private Button nuevaCitaBtn;
    @FXML private Button borrarCitaBtn;
    @FXML private Button modificarCitaBtn;

    // Variables de modelo
    private Paciente paciente;
    private ObservableList<Cita> citasList = FXCollections.observableArrayList();
    private List<Especialidad> especialidades = new ArrayList<>();

    // DAOs para acceso a datos
    private CitasDAO citasDAO;
    private EspecialidadesDAO especialidadesDAO;

    // Cita seleccionada actualmente
    private Cita citaSeleccionada;

    // Formato de hora
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Inicializar DAOs
            citasDAO = new CitasDAO();
            citasDAO.conectar();

            especialidadesDAO = new EspecialidadesDAO();
            especialidadesDAO.connect();

            // Configurar columnas de la tabla
            numCitaColumn.setCellValueFactory(cellData -> {
                Integer id = cellData.getValue().getId();
                return javafx.beans.binding.Bindings.createObjectBinding(() -> id);
            });

            // Configurar la columna de fecha
            fechaColumn.setCellValueFactory(cellData -> {
                String fechaStr = cellData.getValue().getFecha();
                if (fechaStr != null) {
                    return javafx.beans.binding.Bindings.createStringBinding(() -> fechaStr);
                } else {
                    return javafx.beans.binding.Bindings.createStringBinding(() -> "");
                }
            });

            // Configurar la columna de especialidad
            especialidadColumn.setCellValueFactory(cellData -> {
                int idEsp = cellData.getValue().getIdEspecialidad();
                String nombreEsp = "Desconocida";

                for (Especialidad esp : especialidades) {
                    if (esp.getId() == idEsp) {
                        nombreEsp = esp.getNombreEspecialidad();
                        break;
                    }
                }

                final String nombreFinal = nombreEsp;
                return javafx.beans.binding.Bindings.createStringBinding(() -> nombreFinal);
            });

            // Establecer valores por defecto
            fechaCitaDatePicker.setValue(LocalDate.now());
            horaCitaField.setText("09:00");

            // Cargar especialidades o crear si no hay
            cargarEspecialidades();

            // Configurar selección en la tabla
            citasTableView.getSelectionModel().selectedItemProperty().addListener(
                    (obs, oldSelection, newSelection) -> {
                        if (newSelection != null) {
                            citaSeleccionada = newSelection;
                            cargarCitaEnFormulario(newSelection);
                        }
                    });

            // Configurar eventos de botones
            nuevaCitaBtn.setOnAction(e -> crearNuevaCita());
            borrarCitaBtn.setOnAction(e -> borrarCita());
            modificarCitaBtn.setOnAction(e -> modificarCita());
            verCitasBtn.setOnAction(e -> cargarCitasPaciente());

        } catch (Exception e) {
            mostrarError("Error al inicializar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Establece el paciente y carga sus datos en el formulario
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;

        // Cargar datos del paciente en el formulario
        dniField.setText(paciente.getDni());
        nombreField.setText(paciente.getNombre());
        direccionField.setText(paciente.getDireccion());
        telefonoField.setText(String.valueOf(paciente.getTelefono()));

        // Cargar citas del paciente
        cargarCitasPaciente();
    }

    /**
     * Método para manejar el botón de selección de fecha
     */
    @FXML
    private void seleccionarFecha() {
        fechaCitaDatePicker.show();
    }

    /**
     * Carga las especialidades disponibles o crea unas por defecto si no hay
     */
    private void cargarEspecialidades() {
        try {
            especialidades = especialidadesDAO.getEspecialidades();

            // Si no hay especialidades, crear unas por defecto
            if (especialidades.isEmpty()) {
                crearEspecialidadesDefecto();
                especialidades = especialidadesDAO.getEspecialidades();
            }

            List<String> nombresEspecialidades = new ArrayList<>();
            for (Especialidad esp : especialidades) {
                nombresEspecialidades.add(esp.getNombreEspecialidad());
            }

            especialidadCombo.setItems(FXCollections.observableArrayList(nombresEspecialidades));

            // Seleccionar la primera especialidad por defecto
            if (!nombresEspecialidades.isEmpty()) {
                especialidadCombo.getSelectionModel().selectFirst();
            }

        } catch (Exception e) {
            mostrarError("Error al cargar especialidades: " + e.getMessage());
        }
    }

    /**
     * Crea especialidades por defecto en la base de datos
     */
    private void crearEspecialidadesDefecto() {
        try {
            String[] nombresEspecialidades = {"Pediatría", "Cirugía", "Traumatología", "Psiquiatría"};

            for (String nombre : nombresEspecialidades) {
                Especialidad especialidad = new Especialidad();
                especialidad.setNombreEspecialidad(nombre);
                especialidadesDAO.insertEspecialidad(especialidad);
            }

            mostrarInfo("Se han creado especialidades predeterminadas");
        } catch (Exception e) {
            mostrarError("Error al crear especialidades por defecto: " + e.getMessage());
        }
    }

    /**
     * Carga una cita seleccionada en el formulario
     */
    private void cargarCitaEnFormulario(Cita cita) {
        numeroCitaField.setText(String.valueOf(cita.getId()));

        // Procesar la fecha y hora desde el string
        try {
            // Formato esperado: "yyyy-MM-dd HH:mm:ss"
            String fechaStr = cita.getFecha();
            if (fechaStr != null && !fechaStr.isEmpty()) {
                LocalDateTime fechaHora = LocalDateTime.parse(
                        fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                fechaCitaDatePicker.setValue(fechaHora.toLocalDate());
                horaCitaField.setText(fechaHora.toLocalTime().format(timeFormatter));
            }
        } catch (DateTimeParseException e) {
            mostrarError("Error al procesar la fecha: " + e.getMessage());
        }

        // Seleccionar la especialidad correspondiente
        for (int i = 0; i < especialidades.size(); i++) {
            if (especialidades.get(i).getId() == cita.getIdEspecialidad()) {
                especialidadCombo.getSelectionModel().select(i);
                break;
            }
        }
    }

    /**
     * Crea una nueva cita con los datos del formulario
     */
    private void crearNuevaCita() {
        try {
            // Validar campos obligatorios
            if (fechaCitaDatePicker.getValue() == null ||
                    horaCitaField.getText().isEmpty() ||
                    especialidadCombo.getValue() == null) {
                mostrarError("Por favor, complete todos los campos obligatorios.");
                return;
            }

            if (paciente == null) {
                mostrarError("No hay paciente seleccionado.");
                return;
            }

            // Crear la nueva cita
            Cita nuevaCita = new Cita();
            nuevaCita.setIdPaciente(paciente.getId());

            // Combinar fecha y hora
            LocalDate fecha = fechaCitaDatePicker.getValue();
            LocalTime hora;
            try {
                hora = LocalTime.parse(horaCitaField.getText(), timeFormatter);
            } catch (DateTimeParseException e) {
                mostrarError("Formato de hora incorrecto. Use el formato HH:mm");
                return;
            }

            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);
            // Convertir a String en el formato que espera MongoDB
            String fechaString = fechaHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            nuevaCita.setFecha(fechaString);

            // Obtener el ID de la especialidad seleccionada
            String nombreEspecialidad = especialidadCombo.getValue();
            boolean especialidadEncontrada = false;
            for (Especialidad esp : especialidades) {
                if (esp.getNombreEspecialidad().equals(nombreEspecialidad)) {
                    nuevaCita.setIdEspecialidad(esp.getId());
                    especialidadEncontrada = true;
                    break;
                }
            }

            if (!especialidadEncontrada) {
                mostrarError("La especialidad seleccionada no es válida.");
                return;
            }

            // Guardar la cita en la base de datos
            citasDAO.guardarCita(nuevaCita);

            // Recargar la lista de citas
            cargarCitasPaciente();

            // Limpiar el formulario
            limpiarFormulario();

            mostrarInfo("Cita creada correctamente.");

        } catch (Exception e) {
            mostrarError("Error al crear la cita: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Borra la cita seleccionada
     */
    private void borrarCita() {
        if (citaSeleccionada == null) {
            mostrarError("Por favor, seleccione una cita para borrar.");
            return;
        }

        try {
            citasDAO.eliminarCita(citaSeleccionada);
            cargarCitasPaciente();
            limpiarFormulario();
            mostrarInfo("Cita eliminada correctamente.");
        } catch (Exception e) {
            mostrarError("Error al eliminar la cita: " + e.getMessage());
        }
    }

    /**
     * Modifica la cita seleccionada con los datos del formulario
     */
    private void modificarCita() {
        if (citaSeleccionada == null) {
            mostrarError("Por favor, seleccione una cita para modificar.");
            return;
        }

        try {
            // Validar campos obligatorios
            if (fechaCitaDatePicker.getValue() == null ||
                    horaCitaField.getText().isEmpty() ||
                    especialidadCombo.getValue() == null) {
                mostrarError("Por favor, complete todos los campos obligatorios.");
                return;
            }

            // Combinar fecha y hora
            LocalDate fecha = fechaCitaDatePicker.getValue();
            LocalTime hora;
            try {
                hora = LocalTime.parse(horaCitaField.getText(), timeFormatter);
            } catch (DateTimeParseException e) {
                mostrarError("Formato de hora incorrecto. Use el formato HH:mm");
                return;
            }

            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);
            // Convertir a String en el formato que espera MongoDB
            String fechaString = fechaHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            citaSeleccionada.setFecha(fechaString);

            // Obtener el ID de la especialidad seleccionada
            String nombreEspecialidad = especialidadCombo.getValue();
            for (Especialidad esp : especialidades) {
                if (esp.getNombreEspecialidad().equals(nombreEspecialidad)) {
                    citaSeleccionada.setIdEspecialidad(esp.getId());
                    break;
                }
            }

            // Guardar los cambios en la base de datos
            citasDAO.modificarCita(citaSeleccionada);

            // Recargar la lista de citas
            cargarCitasPaciente();

            mostrarInfo("Cita modificada correctamente.");

        } catch (Exception e) {
            mostrarError("Error al modificar la cita: " + e.getMessage());
        }
    }

    /**
     * Carga las citas del paciente actual en la tabla
     */
    private void cargarCitasPaciente() {
        if (paciente == null) {
            mostrarError("No hay ningún paciente seleccionado");
            return;
        }

        try {
            // Obtener las citas del paciente actual
            List<Cita> citas = citasDAO.obtenerCitasPorPaciente(paciente.getId());

            // Actualizar la lista observable
            citasList.clear();
            citasList.addAll(citas);

            // Asignar la lista a la tabla
            citasTableView.setItems(citasList);

        } catch (Exception e) {
            mostrarError("Error al cargar las citas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Limpia los campos del formulario de citas
     */
    private void limpiarFormulario() {
        numeroCitaField.clear();
        fechaCitaDatePicker.setValue(LocalDate.now());
        horaCitaField.setText("09:00");
        citaSeleccionada = null;
    }

    /**
     * Muestra un mensaje de error
     */
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra un mensaje informativo
     */
    private void mostrarInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}