package br.com.ciandt.desafio.modal;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import br.com.ciandt.desafio.infra.HibernateUtil;

public class RotaTest {

	@Test
	@SuppressWarnings("unchecked")
	public void test_persistencia() {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();

			Rota rota = new Rota("A", "D", new BigDecimal(100), null);
			session.save(rota);

			List<Rota> list = session.createCriteria(Rota.class).list();
			Assert.assertNotNull(list);
			Assert.assertFalse(list.isEmpty());

			for (Rota rotaBusca : list) {
				System.out.println(rotaBusca);
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}

	@Test
	public void test_busca() {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();

			Rota rotaRecuperada = session.get(Rota.class,
			        Rota.criarCodigo("A", "D"));
			System.out.println(rotaRecuperada);

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}

}
