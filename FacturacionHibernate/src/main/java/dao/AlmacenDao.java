package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import modelos.Almacen;

public class AlmacenDao {
    protected SessionFactory sessionFactory;

    // code to load Hibernate Session factory
    public void setup() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
    }

    public void exit() {
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory = null;
        }
    }

    public void create(Almacen almacen) {
        setup();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(almacen);
            session.getTransaction().commit();
        }
        exit();
    }

    public Almacen read(int id) {
    	 Almacen almacen;
        setup();
       
        try (Session session = sessionFactory.openSession()) {
            almacen = session.find(Almacen.class, id);
        }
        exit();
        return almacen;
    }

    public void update(Almacen almacen) {
        setup();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(almacen);
            session.getTransaction().commit();
        }
        exit();
    }
}
