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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CitasDAO {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> citasCollection;

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
        Document citaDoc = new Document()
                .append("_id", cita.getId())
                .append("fecha", new Date(cita.getFecha()))
                .append("idPaciente", cita.getIdPaciente())
                .append("idEspecialidad", cita.getIdEspecialidad());

        citasCollection.insertOne(citaDoc);
    }

    public void eliminarCita(Cita cita) {
        citasCollection.deleteOne(Filters.eq("_id", cita.getId()));
    }

    public void modificarCita(Cita cita) {
        Bson filter = Filters.eq("_id", cita.getId());
        Bson updates = Updates.combine(
                Updates.set("fecha", new Date(cita.getFecha())),
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

        // Convertir Date de MongoDB a Timestamp de Java
        String fechaMongo = doc.getString("fecha");
        if (fechaMongo != null) {
            cita.setFecha(fechaMongo);
        }

        cita.setIdPaciente(doc.getInteger("idPaciente"));
        cita.setIdEspecialidad(doc.getInteger("idEspecialidad"));

        return cita;
    }
}