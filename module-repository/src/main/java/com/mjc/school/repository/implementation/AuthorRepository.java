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
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorEntity, Long> {
    private final DataSource dataSource;
    private final BaseRepository<NewsEntity, Long> newsRepository;

    @Autowired
    public AuthorRepository(@Qualifier("newsRepository") BaseRepository newsRepository) {
        this.dataSource = DataSource.getInstance();
        this.newsRepository = newsRepository;
    }

    @Override
    public List<AuthorEntity> readAll() {
        return dataSource.getEntityManager()
                .createQuery("select author from AuthorEntity author").getResultList();
    }

    @Override
    public Optional<AuthorEntity> readById(Long id) {
        AuthorEntity authorEntity = dataSource.getEntityManager().find(AuthorEntity.class, id);
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
    public AuthorEntity update(AuthorEntity entity) {
        return this.create(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        EntityManager entityManager = dataSource.getEntityManager();
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
