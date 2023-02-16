package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import modelos.Factura;
import modelos.Pedido;

public class PedidoDao {

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

	public void create(Pedido pedido) {
		setup();
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.persist(pedido);
			session.getTransaction().commit();
		}
		exit();

	}

	public Pedido read(String nombreProducto) {
		Pedido pedido;
		setup();
		
		try (Session session = sessionFactory.openSession()) {
			pedido = session.get(Pedido.class, nombreProducto);
		}
		exit();

		return pedido;

	}

	public void update(Factura factura) {
		setup();
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.merge(factura);
			session.getTransaction().commit();
		}
		exit();

	}
}
