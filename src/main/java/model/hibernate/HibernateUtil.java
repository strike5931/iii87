package model.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static SessionFactory buildSessionFactory() {
		final StandardServiceRegistry serviceRegistry =
				new StandardServiceRegistryBuilder().configure().build();
		try {
			return new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static void closeSessionFactory() {
		if(sessionFactory!=null) {
			sessionFactory.close();
		}
	}
}
