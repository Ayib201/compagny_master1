package com.groupeisi.com.company.dao.sale;

import com.groupeisi.com.company.config.HibernateUtil;
import com.groupeisi.com.company.dao.RepositoryImpl;
import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.Sales;
import org.hibernate.Session;


public class SaleDao extends RepositoryImpl<Sales> implements ISaleDao {
	private final Session session = HibernateUtil.getSessionFactory().openSession();
}
