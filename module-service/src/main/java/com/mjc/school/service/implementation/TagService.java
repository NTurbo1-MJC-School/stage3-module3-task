package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.implementation.TagRepository;
import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.repository.model.implementation.TagEntity;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.utils.ModelMapper;
import com.mjc.school.service.validator.NewsValidator;
import com.mjc.school.service.validator.TagValidator;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.mjc.school.service.exceptions.ServiceErrorCode.TAG_ID_DOES_NOT_EXIST;

@Service
public class TagService implements BaseService<TagDtoRequest, TagDtoResponse, Long> {

    private final TagRepository tagRepository;
    private final BaseRepository<NewsEntity, Long> newsRepository;
    private final TagValidator tagValidator;
    private final NewsValidator newsValidator;
    private ModelMapper mapper = Mappers.getMapper(ModelMapper.class);

    @Autowired
    public TagService(TagRepository tagRepository,
                      @Qualifier("newsRepository") BaseRepository newsRepository,
                      TagValidator tagValidator,
                      NewsValidator newsValidator) {
        this.tagRepository = tagRepository;
        this.newsRepository = newsRepository;
        this.tagValidator = tagValidator;
        this.newsValidator = newsValidator;
    }

    @Override
    public List<TagDtoResponse> readAll() {
        return mapper.tagEntityListToDtoList(tagRepository.readAll());
    }

    @Override
    public TagDtoResponse readById(Long tagId) {
        if (tagRepository.existById(tagId)) {
            TagEntity tagEntity = tagRepository.readById(tagId).get();
            return mapper.tagEntityToDto(tagEntity);
        } else {
            throw new NotFoundException(
                    String.format(String.valueOf(TAG_ID_DOES_NOT_EXIST.getMessage()), tagId));
        }
    }

    public List<TagDtoResponse> readByNewsId(Long newsId) {
        return mapper.tagEntityListToDtoList(
                tagRepository.readByNewsId(newsId)
        );
    }

    @Override
    public TagDtoResponse create(TagDtoRequest dtoRequest) {
        tagValidator.validateTagDto(dtoRequest);
        TagEntity entity = mapper.dtoToTagEntity(dtoRequest);

        try {
            List<NewsEntity> newsEntities = new ArrayList<>();

            for (Long newsId : dtoRequest.newsIds()) {
                newsValidator.validateNewsId(newsId);
                newsEntities.add(newsRepository.readById(newsId).get());
            }

            entity.setNewsEntities(newsEntities);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("News id will be empty.");
            entity.setNewsEntities(new ArrayList<>());
        }

        TagEntity tagEntity = tagRepository.create(entity);
        return mapper.tagEntityToDto(tagEntity);
    }

    @Override
    public TagDtoResponse update(TagDtoRequest dtoRequest) {
        tagValidator.validateTagId(dtoRequest.id());
        tagValidator.validateTagDto(dtoRequest);

        if (tagRepository.existById(dtoRequest.id())) {
            TagEntity entity = mapper.dtoToTagEntity(dtoRequest);
            try {
                List<NewsEntity> newsEntities = new ArrayList<>();

                for (Long newsId : dtoRequest.newsIds()) {
                    newsValidator.validateNewsId(newsId);
                    newsEntities.add(newsRepository.readById(newsId).get());
                }

                entity.setNewsEntities(newsEntities);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("News id will be empty.");
                entity.setNewsEntities(new ArrayList<>());
            }

            TagEntity tagEntity = tagRepository.update(entity);
            return mapper.tagEntityToDto(tagEntity);
        } else {
            throw new NotFoundException(
                    String.format(TAG_ID_DOES_NOT_EXIST.getMessage(), dtoRequest.id()));
        }
    }

    @Override
    public boolean deleteById(Long tagId) {
        if (tagRepository.existById(tagId)) {
            return tagRepository.deleteById(tagId);
        } else {
            throw new NotFoundException(String.format(TAG_ID_DOES_NOT_EXIST.getMessage(), tagId));
        }
    }
}
