/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConnectionDB {

    public static MongoClient conectar() {



        try {
            Properties configuration = new Properties();
            configuration.load(R.getProperties("database.properties"));
            String user = configuration.getProperty("user");
            String password = configuration.getProperty("password");
            String host = configuration.getProperty("host");
            String port = configuration.getProperty("port");


            final MongoClient conexion = new MongoClient(new MongoClientURI("mongodb://"+user+":"+password+"@"+host+":"+port+"/?authSource=admin"));

            System.out.println("Conectada correctamente a la BD");
            return conexion;
        } catch (Exception e) {
            System.out.println("Conexion Fallida");
            System.out.println(e);
            return null;
        }
    }

    public static void desconectar(MongoClient con) {
        con.close();
    }

}
