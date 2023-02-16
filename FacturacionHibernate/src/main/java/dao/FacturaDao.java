package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.TypedQuery;
import modelos.Factura;
import modelos.Item;

public class FacturaDao {
	protected SessionFactory sessionFactory;
	protected Session session;

	// code to load Hibernate Session factory
	public void setup() {

		if (sessionFactory == null) {
			SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
			

		if (session == null || !session.isOpen()) {
			session = sessionFactory.openSession();
		}
	}}

	public void exit() {
		if (session != null && session.isOpen()) {
			session.close();
			session = null;
		}

		if (sessionFactory != null) {
			sessionFactory.close();
			sessionFactory = null;
		}
	}

	public void create(Factura factura) {
		setup();
		
			session.beginTransaction();
			session.persist(factura);
			session.getTransaction().commit();
		
		exit();

	}

	public Factura read(String numeroFactura) {
		Factura factura;
		setup();
		
		
			factura = session.get(Factura.class, numeroFactura);
			exit();
		
		return factura;

	}

	public void update(Factura factura) {
		setup();
	
			session.beginTransaction();
			session.merge(factura);
			session.getTransaction().commit();
		
		exit();

	}

	public String facturaCliente(String dni) {
		String numerosFactura = "";
		setup();
		
			session.beginTransaction();

			TypedQuery<Factura> query = session.createQuery("FROM Factura f WHERE f.client.dni = :dni", Factura.class);
			query.setParameter("dni", dni);

			List<Factura> listaFacturas = query.getResultList();

			for (Factura factura : listaFacturas) {
			
				numerosFactura += String.format("%16s", factura.getNumeroFactura()) + "\n";
			}
			session.getTransaction().commit();
		
		exit();

		return numerosFactura;
	}

}
