package com.groupeisi.com.company.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public abstract class BaseService {
    protected EntityManager em ;
    protected EntityTransaction et;
    public BaseService() {
        this.em = JpaUtils.getEntityManager();
        this.et = em.getTransaction();
    }
    protected BaseService(EntityManager entityManager) {
        this.em = entityManager;
        this.et = entityManager.getTransaction();
    }
}
