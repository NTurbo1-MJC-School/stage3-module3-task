package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.repository.model.implementation.TagEntity;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepository implements BaseRepository<TagEntity, Long> {

    private final DataSource dataSource;
    private final BaseRepository<NewsEntity, Long> newsRepository;

    public TagRepository(@Qualifier("newsRepository") BaseRepository newsRepository) {
        this.dataSource = DataSource.getInstance();
        this.newsRepository = newsRepository;
    }

    @Override
    public List<TagEntity> readAll() {
        return dataSource
                .getEntityManager()
                .createQuery("select tag from TagEntity tag")
                .getResultList();
    }

    @Override
    public Optional<TagEntity> readById(Long id) {
        TagEntity tagEntity = dataSource.getEntityManager().find(TagEntity.class, id);
        return tagEntity != null ? Optional.of(tagEntity) : Optional.empty();
    }

    public List<TagEntity> readByNewsId(Long newsId) {
        return newsRepository.readById(newsId).get().getTags();
    }

    @Override
    public TagEntity create(TagEntity entity) {
        EntityManager entityManager = dataSource.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            if (entity.getId() == null) {
                entityManager.persist(entity);
            } else {
                entityManager.merge(entity);
            }

            transaction.commit();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TagEntity update(TagEntity entity) {
        return this.create(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        EntityManager entityManager = dataSource.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Optional<TagEntity> optionalTagEntity = this.readById(id);

            if (!optionalTagEntity.isEmpty()) {
                entityManager.remove(optionalTagEntity.get());
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
        return this.readById(id).isEmpty();
    }
}
