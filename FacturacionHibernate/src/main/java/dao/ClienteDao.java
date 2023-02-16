package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import modelos.Cliente;


	public class ClienteDao {

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
	    
		public void create(Cliente cliente) {
			setup();
	        try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.persist(cliente);
			session.getTransaction().commit();
	        }
			exit();
			
		}

		public Cliente read(String dni) {
			Cliente cliente;
			setup();
			
	        try (Session session = sessionFactory.openSession()) {
			cliente = session.get(Cliente.class, dni);
	        }
			exit();

			return cliente;

		}
		
		public void update(Cliente cliente) {
			setup();
			try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.merge(cliente);
			session.getTransaction().commit();
			}
			exit();
			
		}

	}

