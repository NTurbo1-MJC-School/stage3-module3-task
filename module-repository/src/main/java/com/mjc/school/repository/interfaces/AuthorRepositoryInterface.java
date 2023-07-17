package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.AuthorEntity;

import java.util.Optional;

public interface AuthorRepositoryInterface extends BaseRepository<AuthorEntity, Long> {

    Optional<AuthorEntity> readByNewsId(Long newsId);
}
