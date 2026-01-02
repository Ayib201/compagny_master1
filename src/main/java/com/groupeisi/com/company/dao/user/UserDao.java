package com.groupeisi.com.company.dao.user;

import java.util.Optional;


import com.groupeisi.com.company.config.HibernateUtil;
import com.groupeisi.com.company.dao.RepositoryImpl;
import com.groupeisi.com.company.entities.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;


public class UserDao extends RepositoryImpl<UserEntity> implements IUserDao {
	
	private final Session session = HibernateUtil.getSessionFactory().openSession();

	@Override
	public Optional<UserEntity> login(String email, String password) {

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cr = cb.createQuery(UserEntity.class);
		Root<UserEntity> user = cr.from(UserEntity.class);
		//Predicate pour la clause where
		Predicate predicateEmail = cb.equal(user.get("email"), email);
		Predicate predicatePwd = cb.equal(user.get("password"), password);
		Predicate finalPredicate = cb.and(predicateEmail, predicatePwd);
		
		cr.select(user);
		cr.where(finalPredicate);
		
		return Optional.ofNullable(session.createQuery(cr).getSingleResult());
	}

}
