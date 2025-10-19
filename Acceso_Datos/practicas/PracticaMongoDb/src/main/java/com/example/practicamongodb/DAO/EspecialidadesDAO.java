package com.example.practicamongodb.DAO;

import com.example.practicamongodb.Models.ConexionMongoDB;
import com.example.practicamongodb.Models.Especialidad;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadesDAO {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> especialidadesCollection;

    public EspecialidadesDAO() {
    }

    public void connect() throws IOException {
        this.mongoClient = ConexionMongoDB.conectar();
        if (this.mongoClient != null) {
            this.database = mongoClient.getDatabase("CentroMedico");
            this.especialidadesCollection = database.getCollection("especialidades");
        } else {
            throw new IOException("No se pudo conectar a MongoDB");
        }
    }

    public void disconnect() {
        if (this.mongoClient != null) {
            ConexionMongoDB.desconectar(this.mongoClient);
        }
    }

    public List<Especialidad> getEspecialidades() {
        List<Especialidad> especialidades = new ArrayList<>();
        MongoCursor<Document> cursor = especialidadesCollection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Especialidad especialidad = documentToEspecialidad(doc);
                especialidades.add(especialidad);
            }
        } finally {
            cursor.close();
        }

        return especialidades;
    }

    public Especialidad getEspecialidad(int idEspecialidad) {
        Document doc = especialidadesCollection.find(Filters.eq("_id", idEspecialidad)).first();
        if (doc != null) {
            return documentToEspecialidad(doc);
        }
        return null;
    }

    public void updateEspecialidad(Especialidad especialidad) {
        Bson filter = Filters.eq("_id", especialidad.getId());
        Bson update = Updates.set("nombreEspecialidad", especialidad.getNombreEspecialidad());
        especialidadesCollection.updateOne(filter, update);
    }

    public void insertEspecialidad(Especialidad especialidad) {
        // Generar un nuevo ID si no tiene uno asignado
        Document maxIdDoc = especialidadesCollection.find().sort(new Document("_id", -1)).first();
        int nextId = 1;
        if (maxIdDoc != null) {
            nextId = maxIdDoc.getInteger("_id", 0) + 1;
        }

        Document especialidadDoc = new Document()
                .append("_id", nextId)
                .append("nombreEspecialidad", especialidad.getNombreEspecialidad());

        InsertOneResult result = especialidadesCollection.insertOne(especialidadDoc);
        if (result.wasAcknowledged()) {
            especialidad.setId(nextId);
        }
    }

    public void deleteEspecialidad(int idEspecialidad) {
        especialidadesCollection.deleteOne(Filters.eq("_id", idEspecialidad));
    }

    private Especialidad documentToEspecialidad(Document doc) {
        Especialidad especialidad = new Especialidad();
        especialidad.setId(doc.getInteger("_id"));
        especialidad.setNombreEspecialidad(doc.getString("nombreEspecialidad"));
        return especialidad;
    }
}