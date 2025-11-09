package Controller;

import DAO.CitaDAO;
import DAO.CitaDAOimpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Cita;
import model.Especialidad;
import model.Paciente;
import org.hibernate.Session;
import util.DBConnection;
import util.HibernateUtil;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FormularioController {

       /*TextField	txtDni
TextField	txtNombre
TextField	txtDireccion
TextField	txtTelefono
DatePicker	dpFechCita
ComboBox	cmbEspecialidad
TableView tableCitas
TableColumn	colNumero
TableColumn	colFecha
TableColumn	colEspecialidad
Button	btnVer
Button	btnNueva
Button	btnBorrar
Button	btnModificar*/

    @FXML private TextField txtDni, txtNombre, txtDireccion, txtTelefono;
    @FXML private DatePicker dpFechCita;
    @FXML private ComboBox<Especialidad> cmbEspecialidad;
    @FXML private TableView<Cita> tableCitas;
    @FXML private TableColumn<Cita, Integer> colNumero;
    @FXML private TableColumn<Cita, LocalDate> colFecha;
    @FXML private TableColumn<Cita, String> colEspecialidad;
    @FXML private Button btnVer, btnNueva, btnBorrar, btnModificar;

    private final CitaDAO citaDAO= new CitaDAOimpl();
    private Paciente pac;


    @FXML
    public void initialize() {

        colNumero.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getNCita()).asObject());
        colFecha.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getFecha()));
        colEspecialidad.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEspecialidad()));


        cargaEspecialidad();
    }

    public void setPaciente(Paciente p) {
        this.pac = p;
        txtDni.setText(p.getDni());
        txtNombre.setText(p.getNombre());
        txtDireccion.setText(p.getDireccion());
        txtTelefono.setText(p.getTelefono());
    }
    public static final ObjectMapper  JSON_MAPPER = new ObjectMapper();

    private void cargaEspecialidad() {
        cmbEspecialidad.getItems().clear();
        ArrayList<Especialidad> especialidad;
        try  {
            especialidad = JSON_MAPPER.readValue(new File("src/main/resources/JSON/especialidades.json"),
                    JSON_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, Especialidad.class));
            ObservableList<Especialidad> datos = FXCollections.observableArrayList(especialidad);
            cmbEspecialidad.setItems(datos);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //método para llamar al metodo real, y asignarlo al boton
    @FXML
    private void cargaCitas() {
        cargaCitasGlobal();
    }

    private void cargaCitasGlobal() {
        tableCitas.getItems().clear();

        try (Session session = HibernateUtil.getSession()){
            List<Cita> lista= citaDAO.obtenerCitas(session, pac.getEmailID());

            tableCitas.setItems(FXCollections.observableList(lista));
        }
    }

   @FXML
    private void nuevaCita(ActionEvent event) {
        Especialidad esp = cmbEspecialidad.getValue();
        LocalDate fecha = dpFechCita.getValue();
        if (esp == null || fecha == null) {
            System.out.println("Debe seleccionar fecha y especialidad.");
            return;
        }

        if (pac == null){
            System.out.println("Paciente nulo");
            return;
        }

        Cita nueva = new Cita();
        nueva.setFecha(fecha);
        nueva.setEspecialidad(esp.getNombre());
        nueva.setPaciente(pac);

        try(Session session = HibernateUtil.getSession()){
            citaDAO.crearCita(session, nueva);
        }

        cargaCitasGlobal();
    }

    @FXML
    public void borrarCita(ActionEvent event) {
        Cita c = tableCitas.getSelectionModel().getSelectedItem();
        if (c == null) {
            System.out.println("Seleccione una cita para borrar");
            return;
        }

        try (Session session = HibernateUtil.getSession()){
            citaDAO.eliminarCita(session, c);
        }

        cargaCitasGlobal();
    }

    @FXML
    public void modificarCita(ActionEvent event) {
        Cita c = tableCitas.getSelectionModel().getSelectedItem();
        if (c == null) {
            System.out.println("Seleccione una cita para modificar");
            return;
        }

        LocalDate nuevaFecha = dpFechCita.getValue();
        Especialidad esp = cmbEspecialidad.getValue();
        if (nuevaFecha == null || esp == null) {
            System.out.println("Seleccione fecha y especialidad para modificar");
            return;
        }

        c.setFecha(nuevaFecha);
        c.setEspecialidad(String.valueOf(esp));
        try(Session session = HibernateUtil.getSession()){
            citaDAO.modificarCita(session, c);
        }
        cargaCitasGlobal();

    }

}
