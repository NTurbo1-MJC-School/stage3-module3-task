package com.mjc.school.service.validator;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.implementation.AuthorEntity;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;
import static com.mjc.school.service.exceptions.ServiceErrorCode.VALIDATE_STRING_LENGTH;

@Component
public class AuthorValidator extends Validator {

    private final BaseRepository<AuthorEntity, Long> authorRepository;

    public AuthorValidator(@Qualifier("authorRepository") BaseRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void validateAuthorDto(AuthorDtoRequest dtoRequest) {
        validateString(dtoRequest.name(), AUTHOR_NAME, AUTHOR_NAME_MIN_LENGTH, AUTHOR_NAME_MAX_LENGTH);

    }

    public void validateAuthorId(Long authorId) {
        validateNumber(authorId, AUTHOR_ID);
        if (!authorRepository.existById(authorId)) {
            throw new ValidatorException(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), authorId));
        }
    }
}
