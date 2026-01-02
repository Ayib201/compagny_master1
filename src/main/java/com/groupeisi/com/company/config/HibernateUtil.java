package com.groupeisi.com.company.config;

import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.Purchases;
import com.groupeisi.com.company.entities.Sales;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Properties;

import com.groupeisi.com.company.entities.UserEntity;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static final Logger LOG = LoggerFactory.getLogger(HibernateUtil.class);

	private HibernateUtil() {}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				// Charge le fichier .env automatiquement
				Dotenv dotenv = Dotenv.load();

				Configuration configuration = new Configuration();
				Properties settings = new Properties();

				settings.put(AvailableSettings.DRIVER, "org.postgresql.Driver");
				settings.put(AvailableSettings.URL, dotenv.get("DB_URL"));
				settings.put(AvailableSettings.USER, dotenv.get("DB_USER"));
				settings.put(AvailableSettings.PASS, dotenv.get("DB_PASSWORD"));

				settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
				settings.put(AvailableSettings.HBM2DDL_AUTO, "update");
				settings.put(AvailableSettings.SHOW_SQL, "true");
				settings.put(AvailableSettings.FORMAT_SQL, "true");
				settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				configuration.setProperties(settings);
				configuration.addAnnotatedClass(UserEntity.class);
				configuration.addAnnotatedClass(Product.class);
				configuration.addAnnotatedClass(Sales.class);
				configuration.addAnnotatedClass(Purchases.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties())
						.build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);

				return sessionFactory;

			} catch (Exception e) {
				LOG.error("Impossible d'initialiser Hibernate", e);
				throw new ExceptionInInitializerError(e);
			}
		}
		return sessionFactory;
	}
}
