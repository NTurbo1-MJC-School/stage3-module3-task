package com.mjc.school.repository.utils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DataSource {
  private final EntityManager entityManager;

  private DataSource() {
    entityManager = Persistence
            .createEntityManagerFactory("pu")
            .createEntityManager();
  }

  public static DataSource getInstance() {
    return LazyDataSource.INSTANCE;
  }

  public EntityManager getEntityManager() {
    return entityManager;
  }

  private static class LazyDataSource {
    static final DataSource INSTANCE = new DataSource();
  }
}
