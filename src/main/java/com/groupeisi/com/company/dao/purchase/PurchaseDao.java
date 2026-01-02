package com.groupeisi.com.company.dao.purchase;

import com.groupeisi.com.company.config.HibernateUtil;
import com.groupeisi.com.company.dao.RepositoryImpl;
import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.Purchases;
import org.hibernate.Session;


public class PurchaseDao extends RepositoryImpl<Purchases> implements IPurchaseDao {
	private final Session session = HibernateUtil.getSessionFactory().openSession();
}
