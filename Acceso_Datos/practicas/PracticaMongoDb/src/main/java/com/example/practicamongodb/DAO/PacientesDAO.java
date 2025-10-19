package com.example.practicamongodb.DAO;

import com.example.practicamongodb.Models.ConexionMongoDB;
import com.example.practicamongodb.Models.Paciente;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PacientesDAO {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> pacientesCollection;

    public PacientesDAO() {
    }

    public void connect() throws IOException {
        this.mongoClient = ConexionMongoDB.conectar();
        if (this.mongoClient != null) {
            this.database = mongoClient.getDatabase("CentroMedico");
            this.pacientesCollection = database.getCollection("pacientes");
        } else {
            throw new IOException("No se pudo conectar a MongoDB");
        }
    }

    public void disconnect() {
        if (this.mongoClient != null) {
            ConexionMongoDB.desconectar(this.mongoClient);
        }
    }

    public List<Paciente> getPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        MongoCursor<Document> cursor = pacientesCollection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Paciente paciente = documentToPaciente(doc);
                pacientes.add(paciente);
            }
        } finally {
            cursor.close();
        }

        return pacientes;
    }

    public Paciente getPaciente(int idPaciente) {
        Document doc = pacientesCollection.find(Filters.eq("_id", idPaciente)).first();
        if (doc != null) {
            return documentToPaciente(doc);
        }
        return null;
    }

    public void updatePaciente(Paciente paciente) {
        Bson filter = Filters.eq("_id", paciente.getId());
        Bson updates = Updates.combine(
                Updates.set("dni", paciente.getDni()),
                Updates.set("passwd", paciente.getPasswd()),
                Updates.set("nombre", paciente.getNombre()),
                Updates.set("direccion", paciente.getDireccion()),
                Updates.set("telefono", paciente.getTelefono())
        );

        pacientesCollection.updateOne(filter, updates);
    }

    private Paciente documentToPaciente(Document doc) {
        Paciente paciente = new Paciente();
        paciente.setId(doc.getInteger("_id"));
        paciente.setDni(doc.getString("dni"));
        paciente.setPasswd(doc.getString("passwd"));
        paciente.setNombre(doc.getString("nombre"));
        paciente.setDireccion(doc.getString("direccion"));
        paciente.setTelefono(doc.getInteger("telefono"));
        return paciente;
    }

    // Método adicional para insertar un nuevo paciente
    public void insertPaciente(Paciente paciente) {
        // Generar un nuevo ID si no tiene uno asignado
        Document maxIdDoc = pacientesCollection.find().sort(new Document("_id", -1)).first();
        int nextId = 1;
        if (maxIdDoc != null) {
            nextId = maxIdDoc.getInteger("_id", 0) + 1;
        }

        Document pacienteDoc = new Document()
                .append("_id", nextId)
                .append("dni", paciente.getDni())
                .append("passwd", paciente.getPasswd())
                .append("nombre", paciente.getNombre())
                .append("direccion", paciente.getDireccion())
                .append("telefono", paciente.getTelefono());

        pacientesCollection.insertOne(pacienteDoc);
        paciente.setId(nextId);
    }
}