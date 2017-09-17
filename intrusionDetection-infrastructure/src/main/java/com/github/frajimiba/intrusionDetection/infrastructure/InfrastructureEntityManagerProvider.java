package com.github.frajimiba.intrusionDetection.infrastructure;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.axonframework.common.jpa.EntityManagerProvider;

public class InfrastructureEntityManagerProvider implements EntityManagerProvider {

    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext(unitName="infrastructure")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}