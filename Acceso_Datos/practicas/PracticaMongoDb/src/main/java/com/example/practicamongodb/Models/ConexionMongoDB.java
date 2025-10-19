package com.example.practicamongodb.Models;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConexionMongoDB {
    /**
     * Método para conectar con MongoDB usando la API de 4.11 ya que la 3.12 no funciona con sdk 25
     * @return Una instancia de MongoClient
     */
    public static MongoClient conectar() {
        try {
            // Cargar propiedades de conexión
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File("src/main/resources/configuration/database.properties")));
            String host = properties.getProperty("host");
            String port = properties.getProperty("port");
            String name = properties.getProperty("name");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            // Crear string de conexión para MongoDB
            String connectionString = String.format("mongodb://%s:%s@%s:%s/?authSource=admin",
                    username, password, host, port);

            // Configurar y crear el cliente MongoDB
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .build();

            MongoClient mongoClient = MongoClients.create(settings);

            // Verificar la conexión accediendo a la información del servidor
            mongoClient.getDatabase("admin").runCommand(new org.bson.Document("ping", 1));

            System.out.println("Conectada correctamente a la BD MongoDB");
            return mongoClient;
        }
        catch (MongoException me) {
            System.out.println("Error de MongoDB: " + me.getMessage());
            me.printStackTrace();
            return null;
        }
        catch (IOException e) {
            System.out.println("Error al cargar el archivo de propiedades: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        catch (Exception e) {
            System.out.println("Error inesperado en la conexión: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método para desconectar de MongoDB
     * @param client El cliente MongoDB a cerrar
     */
    public static void desconectar(MongoClient client) {
        if (client != null) {
            try {
                client.close();
                System.out.println("Conexión a MongoDB cerrada correctamente");
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
