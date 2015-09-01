package br.com.ciandt.desafio.repositoy.impl;

import java.io.Serializable;
import java.util.List;

import br.com.ciandt.desafio.infra.HibernateUtil;
import br.com.ciandt.desafio.modal.Rota;
import br.com.ciandt.desafio.repositoy.RotaRepository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class RotaRepositoyImpl implements RotaRepository {

	@Override
	@SuppressWarnings("unchecked")
	public Rota buscarPorCodigoEMenorDistancia(String pCodigo) {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(Rota.class);
			criteria.add(Restrictions.eq("codigo", pCodigo));
			criteria.addOrder(Order.asc("distancia"));
			List<Rota> listaRotas = criteria.list();

			session.getTransaction().commit();
			if (!listaRotas.isEmpty()) {
				return listaRotas.get(0);
			}

		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Rota> buscarPorDestino(String pDestino) {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(Rota.class);
			criteria.add(Restrictions.eq("destino", pDestino));
			List<Rota> listaRotas = criteria.list();

			session.getTransaction().commit();
			return listaRotas;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Rota> buscarPorOrigem(String pOrigem) {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(Rota.class);
			criteria.add(Restrictions.eq("origem", pOrigem));
			List<Rota> listaRotas = criteria.list();

			session.getTransaction().commit();
			return listaRotas;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Serializable incluir(Serializable pIdRota) {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();

			Serializable id = session.save(pIdRota);

			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Rota buscarPorSteps(String pSteps) {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(Rota.class);
			criteria.add(Restrictions.eq("steps", pSteps));
			Rota rota = (Rota) criteria.uniqueResult();

			session.getTransaction().commit();
			return rota;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
    public List<Rota> buscarTodas() {
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(Rota.class);
			List<Rota> rotas = criteria.list();

			session.getTransaction().commit();
			return rotas;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return null;
    }

}
