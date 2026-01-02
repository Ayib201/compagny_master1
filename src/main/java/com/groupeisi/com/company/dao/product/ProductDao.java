package com.groupeisi.com.company.dao.product;

import com.groupeisi.com.company.config.HibernateUtil;
import com.groupeisi.com.company.dao.RepositoryImpl;
import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.Optional;


public class ProductDao extends RepositoryImpl<Product> implements IProductDao {
	private final Session session = HibernateUtil.getSessionFactory().openSession();
	public Product getProduct(String id) {
		return session.get(Product.class, id);
	}
}
