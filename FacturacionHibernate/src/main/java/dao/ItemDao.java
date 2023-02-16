package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.TypedQuery;
import modelos.Item;

public class ItemDao {

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

	public void create(Item item) {
		setup();
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.persist(item);
			session.getTransaction().commit();
		}
		exit();

	}

	public Item read(String nombreProducto) {
		Item item;
		setup();
		
		try (Session session = sessionFactory.openSession()) {
			item = session.get(Item.class, nombreProducto);
		}
		exit();

		return item;

	}

	public void update(Item item) {
		setup();
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.merge(item);
			session.getTransaction().commit();
		}
		exit();

	}

	public List<Item> itemsList() {

		setup();
		List<Item> listaItems;
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			TypedQuery<Item> query = session.createQuery("FROM Item", Item.class);
			listaItems = query.getResultList();
			session.getTransaction().commit();
		}
		exit();

		return listaItems;
	}
}
