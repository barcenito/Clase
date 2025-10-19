package com.example.practicamongodb.DAO;

import com.example.practicamongodb.Models.Cita;
import com.example.practicamongodb.Models.ConexionMongoDB;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CitasDAO {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> citasCollection;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public CitasDAO() {
    }

    public void conectar() throws IOException {
        this.mongoClient = ConexionMongoDB.conectar();
        if (this.mongoClient != null) {
            this.database = mongoClient.getDatabase("CentroMedico");
            this.citasCollection = database.getCollection("citas");
        } else {
            throw new IOException("No se pudo conectar a MongoDB");
        }
    }

    public void desconectar() {
        if (this.mongoClient != null) {
            ConexionMongoDB.desconectar(this.mongoClient);
        }
    }

    public void guardarCita(Cita cita) {
        try {
            // Generar un nuevo ID si no tiene uno asignado
            if (cita.getId() <= 0) {
                Document maxIdDoc = citasCollection.find().sort(new Document("_id", -1)).first();
                int nextId = 1;
                if (maxIdDoc != null) {
                    nextId = maxIdDoc.getInteger("_id", 0) + 1;
                }
                cita.setId(nextId);
            }

            // Convertir la fecha string a Date para MongoDB
            Date fechaDate = null;
            try {
                if (cita.getFecha() != null && !cita.getFecha().isEmpty()) {
                    fechaDate = dateFormat.parse(cita.getFecha());
                }
            } catch (ParseException e) {
                // Si hay error de parsing, usar fecha actual
                fechaDate = new Date();
            }

            Document citaDoc = new Document()
                    .append("_id", cita.getId())
                    .append("fecha", cita.getFecha()) // Guardar como string para consistencia
                    .append("idPaciente", cita.getIdPaciente())
                    .append("idEspecialidad", cita.getIdEspecialidad());

            citasCollection.insertOne(citaDoc);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar cita: " + e.getMessage(), e);
        }
    }

    public void eliminarCita(Cita cita) {
        citasCollection.deleteOne(Filters.eq("_id", cita.getId()));
    }

    public void modificarCita(Cita cita) {
        Bson filter = Filters.eq("_id", cita.getId());
        Bson updates = Updates.combine(
                Updates.set("fecha", cita.getFecha()),
                Updates.set("idPaciente", cita.getIdPaciente()),
                Updates.set("idEspecialidad", cita.getIdEspecialidad())
        );

        citasCollection.updateOne(filter, updates);
    }

    public List<Cita> obtenerCitas() {
        List<Cita> citas = new ArrayList<>();
        MongoCursor<Document> cursor = citasCollection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Cita cita = documentToCita(doc);
                citas.add(cita);
            }
        } finally {
            cursor.close();
        }

        return citas;
    }

    public List<Cita> obtenerCitasPorPaciente(int idPaciente) {
        List<Cita> citas = new ArrayList<>();
        Bson filter = Filters.eq("idPaciente", idPaciente);
        MongoCursor<Document> cursor = citasCollection.find(filter).iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Cita cita = documentToCita(doc);
                citas.add(cita);
            }
        } finally {
            cursor.close();
        }

        return citas;
    }

    private Cita documentToCita(Document doc) {
        Cita cita = new Cita();
        cita.setId(doc.getInteger("_id"));

        // Obtener la fecha como string directamente
        String fechaStr = doc.getString("fecha");
        cita.setFecha(fechaStr);

        cita.setIdPaciente(doc.getInteger("idPaciente"));
        cita.setIdEspecialidad(doc.getInteger("idEspecialidad"));

        return cita;
    }
}