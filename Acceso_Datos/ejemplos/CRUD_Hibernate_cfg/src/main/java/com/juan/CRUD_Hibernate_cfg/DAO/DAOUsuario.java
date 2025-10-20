package com.juan.CRUD_Hibernate_cfg.DAO;

import com.juan.CRUD_Hibernate_cfg.Modelo.Usuario;
import org.hibernate.Session;

import java.util.List;


public class DAOUsuario {
	public static void insertarusuario(Session session, Usuario u){
		//transaction solo se usa en operaciones de escritura
		session.beginTransaction();
		session.save(u);
		session.getTransaction().commit();
	}
	public static void modificarUsuario(Session session, Usuario u){
		session.beginTransaction();
		session.update(u);
		session.getTransaction().commit();
	}
	public static void borrarUsuario(Session session, Usuario u){
		session.beginTransaction();
		session.delete(u);
		session.getTransaction().commit();
	}
	public static void ListarUsuarios(Session session){
		List<Usuario> Ulist = session.createQuery("from Usuario").getResultList();
		for(Usuario us:Ulist){
			System.out.println(us.toString());
		}
	}
	public static Usuario buscarUsuario(Session session, int id){
		Usuario us;
		us = (Usuario) session.get(Usuario.class, id);
		System.out.println(us.toString());
		return us;
	}

}
