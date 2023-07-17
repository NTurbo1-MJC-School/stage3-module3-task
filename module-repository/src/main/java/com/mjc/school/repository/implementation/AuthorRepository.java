package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.interfaces.AuthorRepositoryInterface;
import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.repository.model.implementation.NewsEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements AuthorRepositoryInterface {
    @PersistenceContext
    private EntityManager entityManager;
    @Qualifier("newsRepository")
    private BaseRepository<NewsEntity, Long> newsRepository;

    public AuthorRepository() {}

    @Override
    public List<AuthorEntity> readAll() {
        return entityManager
                .createQuery("select author from AuthorEntity author").getResultList();
    }

    @Override
    public Optional<AuthorEntity> readById(Long id) {
        AuthorEntity authorEntity = entityManager.find(AuthorEntity.class, id);
        return authorEntity != null ? Optional.of(authorEntity) : Optional.empty();
    }

    @Override
    public Optional<AuthorEntity> readByNewsId(Long newsId) {
        return readById(
                newsRepository.readById(newsId).get().getAuthor().getId()
        );
    }

    @Override
    public AuthorEntity create(AuthorEntity entity) {
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
    public AuthorEntity update(AuthorEntity entity) {
        return this.create(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Optional<AuthorEntity> optionalAuthorEntity = this.readById(id);

            if (!optionalAuthorEntity.isEmpty()) {
                entityManager.remove(optionalAuthorEntity.get());
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
    public boolean existById(Long id) {
        return !this.readById(id).isEmpty();
    }
}
