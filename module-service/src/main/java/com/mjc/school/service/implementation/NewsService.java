package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.repository.model.implementation.TagEntity;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.utils.ModelMapper;
import com.mjc.school.service.validator.NewsValidator;
import com.mjc.school.service.validator.TagValidator;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.mjc.school.service.exceptions.ServiceErrorCode.NEWS_ID_DOES_NOT_EXIST;

@Service
public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {

  private final BaseRepository<NewsEntity, Long> newsRepository;
  private final BaseRepository<AuthorEntity, Long> authorRepository;
  private final BaseRepository<TagEntity, Long> tagRepository;
  private final NewsValidator newsValidator;
  private final TagValidator tagValidator;
  private ModelMapper mapper = Mappers.getMapper(ModelMapper.class);

  @Autowired
  public NewsService(@Qualifier("newsRepository") BaseRepository newsRepository,
                     @Qualifier("authorRepository") BaseRepository authorRepository,
                     @Qualifier("tagRepository") BaseRepository tagRepository,
                     NewsValidator newsValidator,
                     TagValidator tagValidator) {
    this.newsRepository = newsRepository;
    this.authorRepository = authorRepository;
    this.tagRepository = tagRepository;
    this.newsValidator = newsValidator;
    this.tagValidator = tagValidator;
  }

  @Override
  public List<NewsDtoResponse> readAll() {
    return mapper.newsEntityListToDtoList(newsRepository.readAll());
  }

  @Override
  public NewsDtoResponse readById(Long newsId) {
    newsValidator.validateNewsId(newsId);
    if (newsRepository.existById(newsId)) {
      NewsEntity newsEntity = newsRepository.readById(newsId).get();
      return mapper.newsEntityToDto(newsEntity);
    } else {
      throw new NotFoundException(
          String.format(String.valueOf(NEWS_ID_DOES_NOT_EXIST.getMessage()), newsId));
    }
  }

  @Override
  public NewsDtoResponse create(NewsDtoRequest dtoRequest) {
    newsValidator.validateNewsDto(dtoRequest);
    NewsEntity entity = mapper.dtoToAuthorEntity(dtoRequest);

    entity.setAuthor(authorRepository.readById(dtoRequest.authorId()).get());

    try {
      List<TagEntity> tags = new ArrayList<>();
      for (Long tagId : dtoRequest.tagIds()) {
        tagValidator.validateTagId(tagId);
        tags.add(tagRepository.readById(tagId).get());
      }

      entity.setTags(tags);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("Tag ids will be empty.");
      entity.setTags(new ArrayList<>());
    }
    LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    entity.setCreateDate(date);
    entity.setLastUpdatedDate(date);
    entity.setAuthor(authorRepository.readById(dtoRequest.authorId()).get());
    NewsEntity newsEntity = newsRepository.create(entity);
    return mapper.newsEntityToDto(newsEntity);
  }

  @Override
  public NewsDtoResponse update(NewsDtoRequest dtoRequest) {
    newsValidator.validateNewsId(dtoRequest.id());
    newsValidator.validateNewsDto(dtoRequest);
    if (newsRepository.existById(dtoRequest.id())) {
      NewsEntity entity = mapper.dtoToAuthorEntity(dtoRequest);
      entity.setAuthor(authorRepository.readById(dtoRequest.authorId()).get());

      try {
        List<TagEntity> tags = new ArrayList<>();
        for (Long tagId : dtoRequest.tagIds()) {
          tagValidator.validateTagId(tagId);
          tags.add(tagRepository.readById(tagId).get());
        }

        entity.setTags(tags);
      } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println("Tag ids will be empty.");
        entity.setTags(new ArrayList<>());
      }

      LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
      entity.setCreateDate(date);
      entity.setLastUpdatedDate(date);
      entity.setAuthor(authorRepository.readById(dtoRequest.authorId()).get());

      NewsEntity newsEntity = newsRepository.update(entity);
      return mapper.newsEntityToDto(newsEntity);
    } else {
      throw new NotFoundException(
          String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), dtoRequest.id()));
    }
  }

  @Override
  public boolean deleteById(Long newsId) {
    newsValidator.validateNewsId(newsId);
    if (newsRepository.existById(newsId)) {
      return newsRepository.deleteById(newsId);
    } else {
      throw new NotFoundException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), newsId));
    }
  }
}
