package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorEntity, Long> {
    @PersistenceContext
    private EntityManager entityManager;
    private final BaseRepository<NewsEntity, Long> newsRepository;

    @Autowired
    public AuthorRepository(@Qualifier("newsRepository") BaseRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

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
            e.printStackTrace();
        }
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
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean existById(Long id) {
        return !this.readById(id).isEmpty();
    }
}
