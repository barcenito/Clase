package com.juan.CRUD_Hibernate_cfg;



import com.juan.CRUD_Hibernate_cfg.DAO.DAOUsuario;
import com.juan.CRUD_Hibernate_cfg.Modelo.Profesor;
import com.juan.CRUD_Hibernate_cfg.Modelo.Usuario;
import com.juan.CRUD_Hibernate_cfg.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import  com.juan.CRUD_Hibernate_cfg.DAO.DAOProfesor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class App 
{
	static SessionFactory factory = null;
    public static void main( String[] args )
    {
    	//SessionFactoryes una instancia que creará objetos de tipo Sessiono(fabrica sessiones).
    	
    	SessionFactory factory = HibernateUtil.getSessionFactory();
    	
    	//Ahora que ya tenemos el objeto SessionFactory podemos obtener la Session 
    	//para trabajar con Hibernate.
    	
    	//Una sesión se utiliza para obtener una conexión física con una base de datos. 
    	//El objeto Session es liviano y está diseñado para ser instanciado
    	//cada vez que se necesita una interacción con la base de datos. 
    	//Los objetos persistentes se guardan y recuperan a través de un objeto de sesión.

    	//Los objetos de la sesión no deben mantenerse abiertos durante mucho tiempo 
    	//porque generalmente no son seguros para subprocesos y deben crearse y destruirse 
    	//según sea necesario. La función principal de la sesión 
    	//es ofrecer, crear, leer y eliminar operaciones para instancias 
    	//de clases de entidades asignadas.
    	
		Session session = HibernateUtil.getSession();


        Usuario user1 = new Usuario(1, LocalDate.of(2025,8,5), "Gonzales Sanchez", "Gonzalo");
        Usuario user2 = new Usuario(2, LocalDate.of(2025,2,7), "Maroto Arenaz", "Daniel");
        Usuario user3 = new Usuario(3, LocalDate.of(2025,3,6), "Vega Llorente", "Marta");

        DAOUsuario.insertarUsuario(session, user1);
        DAOUsuario.insertarUsuario(session, user2);
        DAOUsuario.insertarUsuario(session, user3);

        Usuario usuario_aux= DAOUsuario.buscarUsuario(session,3);

        usuario_aux.setNombre("Martín");
        DAOUsuario.modificarUsuario(session,usuario_aux);

        DAOUsuario.borrarUsuario(session, user2);

        DAOUsuario.listarUsuarios(session);

         Profesor profesor1=new Profesor(102, "Carlos", "González", "Oltra");
         Profesor profesor2=new Profesor(103, "Ana", "Sanchez", "Velasco");
         Profesor profesor3=new Profesor(104, "Luis", "Colinas", "Alonso");

            
        //INSERTAR

        DAOProfesor.insertarProfesor(session,profesor1);
        DAOProfesor.insertarProfesor(session,profesor2);
        DAOProfesor.insertarProfesor(session,profesor3);


         
         //buscar profesor

     Profesor profesor_aux=DAOProfesor.buscarProfesor(session,102);


         //MODIFICAR

         profesor_aux.setNombre("Alfredo");
         DAOProfesor.modificarProfesor(session,profesor_aux);


          //BORRAR

        DAOProfesor.borrarProfesor(session,profesor2);


         //consulta
        DAOProfesor.listarProfesores(session);



         session.close();



         factory.close();
    }
}
