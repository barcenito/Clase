package org.example.centromedico.util;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class R {
	public static InputStream getImage(String name) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream("images" + File.separator + name);
	}

	public static InputStream getProperties(String name) {
    InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("src/main/resources/configuration/database.properties");
    if (stream == null) {
        System.err.println("¡ALERTA! No se pudo encontrar el archivo: configuration/" + name);
        // Intento alternativo
        stream = R.class.getClassLoader().getResourceAsStream("configuration/" + name);
        if (stream == null) {
            System.err.println("¡ALERTA! Segundo intento fallido para encontrar: configuration/" + name);
        }
    }
    return stream;
}

	public static URL getUI(String name) {
		return Thread.currentThread().getContextClassLoader().getResource("ui" + File.separator + name);
	}
}
