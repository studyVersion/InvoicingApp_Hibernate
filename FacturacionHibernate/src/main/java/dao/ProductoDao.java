package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import modelos.Producto;

public class ProductoDao {

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

	public void create(Producto producto) {
		setup();
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.persist(producto);
			session.getTransaction().commit();
		}
		exit();

	}

	public Producto read(String nombreProducto) {
		Producto producto;
		setup();
		
		try (Session session = sessionFactory.openSession()) {
			producto = session.get(Producto.class, nombreProducto);
		}
		exit();

		return producto;

	}

	public void update(Producto producto) {
		setup();
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.merge(producto);
			session.getTransaction().commit();
		}
		exit();

	}

}
