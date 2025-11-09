package com.example.centromedicohibernate.controllers;

import com.example.centromedicohibernate.DAO.CitaDaoImpl;
import com.example.centromedicohibernate.DAO.EspecialidadDaoImpl;
import com.example.centromedicohibernate.models.Cita;
import com.example.centromedicohibernate.models.Especialidad;
import com.example.centromedicohibernate.models.Paciente;
import com.example.centromedicohibernate.utils.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
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
    private CitaDaoImpl citaDao;
    private EspecialidadDaoImpl especialidadesDAO;
    
    // Cita seleccionada actualmente
    private Cita citaSeleccionada;
    
    // Formato de hora
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    private static SessionFactory factory = HibernateUtil.getSessionFactory();
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            citaDao = new CitaDaoImpl();
            especialidadesDAO = new EspecialidadDaoImpl();
            
            // Cargar especialidades PRIMERO desde la base de datos
            cargarEspecialidades();

            // Configuración de columnas
            numCitaColumn.setCellValueFactory(cellData -> 
                javafx.beans.binding.Bindings.createObjectBinding(() -> cellData.getValue().getId())
            );
            
            fechaColumn.setCellValueFactory(cellData -> {
                Timestamp ts = cellData.getValue().getFecha();
                String fecha = ts != null ? ts.toLocalDateTime().format(dateFormatter) : "";
                return javafx.beans.binding.Bindings.createStringBinding(() -> fecha);
            });
            
            especialidadColumn.setCellValueFactory(cellData -> {
                Especialidad esp = cellData.getValue().getEspecialidad();
                String nombre = esp != null ? esp.getNombreEspecialidad() : "Desconocida";
                return javafx.beans.binding.Bindings.createStringBinding(() -> nombre);
            });

            fechaCitaDatePicker.setValue(LocalDate.now());
            horaCitaField.setText("09:00");
            
            // ELIMINAR ESTE LISTENER - causa el problema
            // dniField.textProperty().addListener((obs, oldValue, newValue) -> {
            //     if (newValue != null && !newValue.trim().isEmpty()) {
            //         buscarPacientePorDNI(newValue.trim());
            //     }
            // });
            
            // Listener para selección en tabla
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
            verCitasBtn.setOnAction(e -> buscarPaciente()); // USAR buscarPaciente() en lugar de cargarCitasPaciente()
            
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
     * Busca un paciente por DNI y carga sus datos y citas
     */
    @FXML
    private void buscarPaciente() {
        String dni = dniField.getText().trim();
        
        if (dni.isEmpty()) {
            mostrarError("Por favor, ingrese un DNI");
            return;
        }
        
        Session session = null;
        try {
            // Crear nueva sesión
            session = factory.openSession();
            session.beginTransaction();
            
            // Buscar paciente por DNI usando HQL
            paciente = session.createQuery(
                "FROM Paciente WHERE dni = :dni", 
                Paciente.class
            )
            .setParameter("dni", dni)
            .uniqueResult();
            
            if (paciente != null) {
                // FORZAR LA CARGA DE LAS CITAS dentro de la transacción
                paciente.getCitas().size();
                
                session.getTransaction().commit();
                
                // Cargar datos del paciente en los campos
                nombreField.setText(paciente.getNombre());
                direccionField.setText(paciente.getDireccion() != null ? paciente.getDireccion() : "");
                telefonoField.setText(Integer.toString(paciente.getTelefono()));
                
                // Cargar citas del paciente
                cargarCitasPaciente();
                
                mostrarInfo("Paciente encontrado: " + paciente.getNombre());
            } else {
                session.getTransaction().commit();
                limpiarCamposPaciente();
                mostrarError("No se encontró ningún paciente con el DNI: " + dni);
            }
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            mostrarError("Error al buscar paciente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Limpia los campos de información del paciente
     */
    private void limpiarCamposPaciente() {
        nombreField.clear();
        direccionField.clear();
        telefonoField.clear();
        citasList.clear();
        paciente = null;
    }
    
    /**
     * Carga las citas del paciente actual
     */
    private void cargarCitasPaciente() {
        if (paciente == null) {
            mostrarError("Debe buscar un paciente primero");
            return;
        }
        
        try {
            // Obtener citas desde la relación
            List<Cita> citas = paciente.getCitas();
            citasList.clear();
            
            if (citas != null && !citas.isEmpty()) {
                citasList.addAll(citas);
            }
            
            citasTableView.setItems(citasList);
            citasTableView.refresh(); // Forzar actualización visual
            
            if (citasList.isEmpty()) {
                mostrarInfo("El paciente no tiene citas registradas");
            }
        } catch (Exception e) {
            mostrarError("Error al cargar citas: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Crea una nueva cita para el paciente actual
     */
    @FXML
    private void crearNuevaCita() {
        if (paciente == null) {
            mostrarError("Debe buscar un paciente primero");
            return;
        }
        
        LocalDate fecha = fechaCitaDatePicker.getValue();
        if (fecha == null) {
            mostrarError("Debe seleccionar una fecha");
            return;
        }
        
        String horaTexto = horaCitaField.getText().trim();
        if (horaTexto.isEmpty()) {
            mostrarError("Debe ingresar una hora");
            return;
        }
        
        String especialidadNombre = especialidadCombo.getValue();
        if (especialidadNombre == null || especialidadNombre.isEmpty()) {
            mostrarError("Debe seleccionar una especialidad");
            return;
        }
        
        Session session = null;
        try {
            LocalTime hora = LocalTime.parse(horaTexto, DateTimeFormatter.ofPattern("HH:mm"));
            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);
            Timestamp timestamp = Timestamp.valueOf(fechaHora);
            
            Especialidad especialidadSeleccionada = null;
            for (Especialidad esp : especialidades) {
                if (esp.getNombreEspecialidad().equals(especialidadNombre)) {
                    especialidadSeleccionada = esp;
                    break;
                }
            }
            
            if (especialidadSeleccionada == null) {
                mostrarError("Error al obtener la especialidad seleccionada");
                return;
            }
            
            // Crear nueva sesión
            session = factory.openSession();
            
            Cita nuevaCita = new Cita();
            nuevaCita.setFecha(timestamp);
            nuevaCita.setPaciente(paciente);
            nuevaCita.setEspecialidad(especialidadSeleccionada);
            
            citaDao.saveCita(nuevaCita, session);
            
            fechaCitaDatePicker.setValue(LocalDate.now());
            horaCitaField.setText("09:00");
            
            mostrarInfo("Cita creada correctamente");
            
            // Recargar paciente con citas actualizadas
            buscarPaciente();
            
        } catch (DateTimeParseException e) {
            mostrarError("Formato de hora incorrecto. Use HH:mm (ejemplo: 09:30)");
        } catch (Exception e) {
            mostrarError("Error al crear la cita: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
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
        
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Está seguro de que desea eliminar esta cita?");
        
        if (confirmacion.showAndWait().get() != ButtonType.OK) {
            return;
        }
        
        Session session = null;
        try {
            session = factory.openSession();
            
            // Guardar el DNI antes de borrar
            String dniPaciente = dniField.getText().trim();
            
            citaDao.deleteCitaById(citaSeleccionada.getId(), session);
            
            mostrarInfo("Cita eliminada correctamente.");
            
            limpiarFormulario();
            
            // Recargar paciente con citas actualizadas
            dniField.setText(dniPaciente);
            buscarPaciente();
            
        } catch (Exception e) {
            mostrarError("Error al eliminar la cita: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
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
        
        if (fechaCitaDatePicker.getValue() == null || 
            horaCitaField.getText().isEmpty() || 
            especialidadCombo.getValue() == null) {
            mostrarError("Por favor, complete todos los campos obligatorios.");
            return;
        }
        
        Session session = null;
        try {
            LocalDate fecha = fechaCitaDatePicker.getValue();
            LocalTime hora;
            try {
                hora = LocalTime.parse(horaCitaField.getText(), timeFormatter);
            } catch (DateTimeParseException e) {
                mostrarError("Formato de hora incorrecto. Use el formato HH:mm");
                return;
            }
            
            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);
            
            String nombreEspecialidad = especialidadCombo.getValue();
            Especialidad especialidadSeleccionada = null;
            for (Especialidad esp : especialidades) {
                if (esp.getNombreEspecialidad().equals(nombreEspecialidad)) {
                    especialidadSeleccionada = esp;
                    break;
                }
            }
            
            if (especialidadSeleccionada == null) {
                mostrarError("No se pudo encontrar la especialidad");
                return;
            }
            
            // Guardar el DNI antes de modificar
            String dniPaciente = dniField.getText().trim();
            
            // Crear nueva sesión
            session = factory.openSession();
            
            // Actualizar la cita
            citaSeleccionada.setFecha(Timestamp.valueOf(fechaHora));
            citaSeleccionada.setEspecialidad(especialidadSeleccionada);
            
            citaDao.updateCita(citaSeleccionada, session);
            
            mostrarInfo("Cita modificada correctamente.");
            
            limpiarFormulario();
            
            // Recargar paciente con citas actualizadas
            dniField.setText(dniPaciente);
            buscarPaciente();
            
        } catch (Exception e) {
            mostrarError("Error al modificar la cita: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    /**
     * Carga las especialidades disponibles desde la base de datos
     */
    private void cargarEspecialidades() {
        Session session = null;
        try {
            session = factory.openSession();
            especialidades = especialidadesDAO.getAllEspecialidades(session);
            
            if (especialidades == null) {
                especialidades = new ArrayList<>();
            }
            
            if (especialidades.isEmpty()) {
                mostrarError("No hay especialidades registradas en la base de datos. Por favor, contacte con el administrador.");
                return;
            }
            
            List<String> nombresEspecialidades = new ArrayList<>();
            for (Especialidad esp : especialidades) {
                nombresEspecialidades.add(esp.getNombreEspecialidad());
            }
            
            especialidadCombo.setItems(FXCollections.observableArrayList(nombresEspecialidades));
            
            if (!nombresEspecialidades.isEmpty()) {
                especialidadCombo.getSelectionModel().selectFirst();
            }
            
        } catch (Exception e) {
            mostrarError("Error al cargar especialidades: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
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
        
        // Limpiar selección en la tabla
        citasTableView.getSelectionModel().clearSelection();
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

    /**
     * Carga los datos de una cita en el formulario para edición
     */
    private void cargarCitaEnFormulario(Cita cita) {
        if (cita == null) {
            return;
        }
        
        try {
            // Cargar número de cita
            numeroCitaField.setText(String.valueOf(cita.getId()));
            
            // Cargar fecha y hora
            if (cita.getFecha() != null) {
                LocalDateTime fechaHora = cita.getFecha().toLocalDateTime();
                fechaCitaDatePicker.setValue(fechaHora.toLocalDate());
                horaCitaField.setText(fechaHora.format(timeFormatter));
            }
            
            // Cargar especialidad
            if (cita.getEspecialidad() != null) {
                especialidadCombo.setValue(cita.getEspecialidad().getNombreEspecialidad());
            }
            
        } catch (Exception e) {
            mostrarError("Error al cargar los datos de la cita: " + e.getMessage());
            e.printStackTrace();
        }
    }
}