package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsEntity, Long> {

  private final DataSource dataSource;

  public NewsRepository() {
    this.dataSource = DataSource.getInstance();
  }

  @Override
  public List<NewsEntity> readAll() {
    return dataSource.getEntityManager()
            .createQuery("select news from NewsEntity news").getResultList();
  }

  @Override
  public Optional<NewsEntity> readById(Long newsId) {
    NewsEntity newsEntity = dataSource.getEntityManager().find(NewsEntity.class, newsId);
    return newsEntity != null ? Optional.of(newsEntity) : Optional.empty();
  }

  @Override
  public NewsEntity create(NewsEntity entity) {

    try {
      dataSource.getEntityManager().getTransaction().begin();
      if (entity.getId() == null) {
        dataSource.getEntityManager().persist(entity);
      } else {
        dataSource.getEntityManager().merge(entity);
      }

      dataSource.getEntityManager().getTransaction().commit();
      return entity;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public NewsEntity update(NewsEntity entity) {
    return this.create(entity);
  }

  @Override
  public boolean deleteById(Long newsId) {
    EntityManager entityManager = dataSource.getEntityManager();
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
      e.printStackTrace();
    }

    return false;
  }

  @Override
  public boolean existById(Long newsId) {
    return !this.readById(newsId).isEmpty();
  }
}
