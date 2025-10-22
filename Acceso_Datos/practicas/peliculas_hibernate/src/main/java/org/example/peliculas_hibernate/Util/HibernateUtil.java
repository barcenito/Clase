package org.example.peliculas_hibernate.Util;

import org.example.peliculas_hibernate.Model.Pelicula;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
	static SessionFactory factory = null;
	static {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(Pelicula.class);
		factory = cfg.buildSessionFactory();
	}
	public static SessionFactory getSessionFactory(){return factory;}
	public static Session getSession(){return factory.openSession();}
}
