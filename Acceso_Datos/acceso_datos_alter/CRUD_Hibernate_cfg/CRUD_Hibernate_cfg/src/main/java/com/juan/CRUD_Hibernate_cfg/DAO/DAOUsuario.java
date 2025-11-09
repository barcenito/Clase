package com.juan.CRUD_Hibernate_cfg.DAO;

import com.juan.CRUD_Hibernate_cfg.Modelo.Profesor;
import com.juan.CRUD_Hibernate_cfg.Modelo.Usuario;
import org.hibernate.Session;

import java.util.List;

public class DAOUsuario {

    public static void insertarUsuario(Session session, Usuario u){

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

    public static  void listarUsuarios(Session session)
    {
        List<Usuario> lista = session.createQuery("from Usuario").getResultList();

        for (Usuario l:lista) {
            // formato clasico
            System.out.println(l.toString());

        }

        //list.forEach(System.out::println);//version 1.8 de java
    }

    public static  Usuario buscarUsuario (Session session, int id){

        Usuario u;

        u=(Usuario)session.get(Usuario.class, id);
        System.out.println(u.getId());
        System.out.println(u.getNombre());
        System.out.println(u.getApellidos());
        System.out.println(u.getFechaNacimiento());

        return u;
    }
}
