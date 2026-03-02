package com.groupeisi.com.company.dao.product;

import com.groupeisi.com.company.config.HibernateUtil;
import com.groupeisi.com.company.dao.RepositoryImpl;
import com.groupeisi.com.company.entities.Product;
import org.hibernate.Session;


public class ProductDao extends RepositoryImpl<Product, String> implements IProductDao {
	private final Session session = HibernateUtil.getSessionFactory().openSession();
}
