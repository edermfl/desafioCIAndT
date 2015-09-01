package br.com.ciandt.desafio.infra;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static final SessionFactory sessionFactory;
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	
	static {
		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Session getSession() {
		Session session = (Session) threadLocal.get();
		session = sessionFactory.openSession();
		threadLocal.set(session);
		return session;
	}
	
	public static void close(){
		getSession().close();
		sessionFactory.close();
	}
}
