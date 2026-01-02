package com.groupeisi.com.company.dao;

import java.util.List;

import com.groupeisi.com.company.config.HibernateUtil;
import com.groupeisi.com.company.entities.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RepositoryImpl<T> implements Repository<T> {

	private final Session session = HibernateUtil.getSessionFactory().openSession();
	private Transaction transaction = null;

	@Override
	public boolean save(T t) {
		try {
			transaction = session.beginTransaction();
			session.persist(t);
			session.flush();
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(long id, Class<T> clazz) {
		try {
			transaction = session.beginTransaction();
			T entity = session.get(clazz, id);
			if (entity != null) {
				session.remove(entity);
				transaction.commit();
				return true;
			} else {
				if (transaction != null) transaction.rollback();
				return false;
			}
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(T t) {
		try {
			transaction = session.beginTransaction();
			session.merge(t);
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<T> list(Class<T> clazz) {
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> root = cq.from(clazz);
		cq.select(root);
		return session.createQuery(cq).getResultList();
	}

	@Override
	public T get(long id, Class<T> clazz) {
		return session.get(clazz, id);
	}

	@Override
	public List<T> listpaginate(Class<T> clazz, int page, int pageSize) {
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> root = cq.from(clazz);
		cq.select(root);
		return session.createQuery(cq)
				.setFirstResult((page - 1) * pageSize)
				.setMaxResults(pageSize)
				.getResultList();
	}
}
