package com.mjc.school.service.validator;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.repository.model.implementation.NewsEntity;
import com.mjc.school.repository.model.implementation.TagEntity;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;

@Component
public class NewsValidator extends Validator{
  private final BaseRepository<NewsEntity, Long> newsRepository;
  private final AuthorValidator authorValidator;

  @Autowired
  public NewsValidator(@Qualifier("newsRepository") BaseRepository newsRepository,
                       AuthorValidator authorValidator) {
    super();
    this.newsRepository = newsRepository;
    this.authorValidator = authorValidator;
  }

  public void validateNewsDto(NewsDtoRequest dtoRequest) {
    validateString(dtoRequest.title(), NEWS_TITLE, NEWS_TITLE_MIN_LENGTH, NEWS_TITLE_MAX_LENGTH);
    validateString(
            dtoRequest.content(), NEWS_CONTENT, NEWS_CONTENT_MIN_LENGTH, NEWS_CONTENT_MAX_LENGTH);
    authorValidator.validateAuthorId(dtoRequest.authorId());
  }

  public void validateNewsId(Long newsId) {

    validateNumber(newsId, NEWS_ID);

    if (!newsRepository.existById(newsId)) {
      throw new ValidatorException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), newsId));
    }
  }
}
