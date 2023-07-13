package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.implementation.AuthorRepository;
import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.utils.ModelMapper;
import com.mjc.school.service.validator.AuthorValidator;
import com.mjc.school.service.validator.NewsValidator;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mjc.school.service.exceptions.ServiceErrorCode.AUTHOR_ID_DOES_NOT_EXIST;

@Service
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {

    private final AuthorRepository authorRepository;
    private final BaseRepository<NewsEntity, Long> newsRepository;
    private final AuthorValidator authorValidator;
    private final NewsValidator newsValidator;
    private ModelMapper mapper = Mappers.getMapper(ModelMapper.class);

    @Autowired
    public AuthorService(AuthorRepository authorRepository,
                         @Qualifier("newsRepository") BaseRepository newsRepository,
                         AuthorValidator authorValidator,
                         NewsValidator newsValidator) {
        this.authorRepository = authorRepository;
        this.newsRepository = newsRepository;
        this.authorValidator = authorValidator;
        this.newsValidator = newsValidator;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return mapper.authorEntityListToDtoList(authorRepository.readAll());
    }

    @Override
    public AuthorDtoResponse readById(Long authorId) {
        authorValidator.validateAuthorId(authorId);
        if (authorRepository.existById(authorId)) {
            AuthorEntity authorEntity = authorRepository.readById(authorId).get();
            return mapper.authorEntityToDto(authorEntity);
        } else {
            throw new NotFoundException(
                    String.format(String.valueOf(AUTHOR_ID_DOES_NOT_EXIST.getMessage()), authorId));
        }
    }

    public AuthorDtoResponse readByNewsId(Long newsId) {
        newsValidator.validateNewsId(newsId);
        return mapper.authorEntityToDto(
                authorRepository.readByNewsId(newsId).get()
        );
    }

    @Override
    public AuthorDtoResponse create(AuthorDtoRequest dtoRequest) {
        authorValidator.validateAuthorDto(dtoRequest);
        AuthorEntity entity = mapper.dtoToAuthorEntity(dtoRequest);
        AuthorEntity authorEntity = authorRepository.create(entity);
        return mapper.authorEntityToDto(authorEntity);
    }

    @Override
    public AuthorDtoResponse update(AuthorDtoRequest dtoRequest) {
        authorValidator.validateAuthorId(dtoRequest.id());
        authorValidator.validateAuthorDto(dtoRequest);
        if (authorRepository.existById(dtoRequest.id())) {
            AuthorEntity entity = mapper.dtoToAuthorEntity(dtoRequest);
            AuthorEntity authorEntity = authorRepository.update(entity);
            return mapper.authorEntityToDto(authorEntity);
        } else {
            throw new NotFoundException(
                    String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), dtoRequest.id()));
        }
    }

    @Override
    public boolean deleteById(Long authorId) {
        authorValidator.validateAuthorId(authorId);
        if (authorRepository.existById(authorId)) {
            return authorRepository.deleteById(authorId);
        } else {
            throw new NotFoundException(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), authorId));
        }
    }
}
