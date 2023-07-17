package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsEntity, Long> {
  @PersistenceContext
  private EntityManager entityManager;

  public NewsRepository() {}

  @Override
  public List<NewsEntity> readAll() {
    return entityManager
            .createQuery("select news from NewsEntity news").getResultList();
  }

  @Override
  public Optional<NewsEntity> readById(Long newsId) {
    NewsEntity newsEntity = entityManager.find(NewsEntity.class, newsId);
    return newsEntity != null ? Optional.of(newsEntity) : Optional.empty();
  }

  @Override
  public NewsEntity create(NewsEntity entity) {

    try {
      entityManager.getTransaction().begin();
      if (entity.getId() == null) {
        entityManager.persist(entity);
      } else {
        entityManager.merge(entity);
      }

      entityManager.getTransaction().commit();
      return entity;
    } catch (Exception e) {
      entityManager.getTransaction().rollback();
      e.printStackTrace();
    }

    entityManager.close();
    return null;
  }

  @Override
  public NewsEntity update(NewsEntity entity) {
    return this.create(entity);
  }

  @Override
  public boolean deleteById(Long newsId) {
    EntityTransaction transaction = entityManager.getTransaction();

    try {
      transaction.begin();
      Optional<NewsEntity> optionalNewsEntity = this.readById(newsId);

      if (!optionalNewsEntity.isEmpty()) {
        entityManager.remove(optionalNewsEntity.get());
      }

      transaction.commit();

      return true;
    } catch (Exception e) {
      transaction.rollback();
      e.printStackTrace();
    }

    entityManager.close();
    return false;
  }

  @Override
  public boolean existById(Long newsId) {
    return !this.readById(newsId).isEmpty();
  }
}
