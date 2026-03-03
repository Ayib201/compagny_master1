package com.groupeisi.com.company.config;

import com.groupeisi.com.company.entities.Product;
import com.groupeisi.com.company.entities.Purchases;
import com.groupeisi.com.company.entities.Sales;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Properties;

import com.groupeisi.com.company.entities.UserEntity;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static final Logger LOG =
			LoggerFactory.getLogger(HibernateUtil.class);

	private HibernateUtil() {}

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {

			try {
				Dotenv dotenv = Dotenv.load();

				Configuration configuration = new Configuration();
				Properties settings = new Properties();

				// ✅ DRIVER supprimé (deprecated)

				settings.put(JdbcSettings.JAKARTA_JDBC_URL, dotenv.get("DB_URL"));
				settings.put(JdbcSettings.JAKARTA_JDBC_USER, dotenv.get("DB_USER"));
				settings.put(JdbcSettings.JAKARTA_JDBC_PASSWORD, dotenv.get("DB_PASSWORD"));

				settings.put(JdbcSettings.DIALECT,
						"org.hibernate.dialect.PostgreSQLDialect");

				settings.put(SchemaToolingSettings.HBM2DDL_AUTO, "update");
				settings.put(JdbcSettings.SHOW_SQL, "true");
				settings.put(JdbcSettings.FORMAT_SQL, "true");
				settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				configuration.setProperties(settings);

				configuration.addAnnotatedClass(UserEntity.class);
				configuration.addAnnotatedClass(Product.class);
				configuration.addAnnotatedClass(Sales.class);
				configuration.addAnnotatedClass(Purchases.class);

				ServiceRegistry serviceRegistry =
						new StandardServiceRegistryBuilder()
								.applySettings(configuration.getProperties())
								.build();

				sessionFactory =
						configuration.buildSessionFactory(serviceRegistry);

			} catch (Exception e) {
				LOG.error("Erreur lors de l'initialisation de Hibernate", e);
			}
		}

		return sessionFactory;
	}
}