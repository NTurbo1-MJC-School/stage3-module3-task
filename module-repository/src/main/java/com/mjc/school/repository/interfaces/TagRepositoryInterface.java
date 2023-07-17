package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.TagEntity;

import java.util.List;

public interface TagRepositoryInterface extends BaseRepository<TagEntity, Long> {

    abstract public List<TagEntity> readByNewsId(Long newsId);
}
