package com.mjc.school.service.validator;

import com.mjc.school.service.exceptions.ValidatorException;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;

public abstract class Validator {
    static final String NEWS_ID = "News id";
    static final String NEWS_CONTENT = "News content";
    static final String AUTHOR_ID = "Author id";
    static final String NEWS_TITLE = "News title";
    static final Integer NEWS_CONTENT_MIN_LENGTH = 5;
    static final Integer NEWS_CONTENT_MAX_LENGTH = 255;
    static final Integer NEWS_TITLE_MIN_LENGTH = 5;
    static final Integer NEWS_TITLE_MAX_LENGTH = 30;
    static final String AUTHOR_NAME = "Author name";
    static final Integer AUTHOR_NAME_MIN_LENGTH = 5;
    static final Integer AUTHOR_NAME_MAX_LENGTH = 15;
    static final Integer MAX_AUTHOR_ID = 20;
    static final String TAG_ID = "Tag id";
    static final String TAG_NAME = "Tag name";
    static final Integer TAG_NAME_MIN_LENGTH = 3;
    static final Integer TAG_NAME_MAX_LENGTH = 15;
    public Validator() {}

    public void validateNumber(Long id, String parameter) {
        if (id == null || id < 1) {
            throw new ValidatorException(
                    String.format(VALIDATE_NEGATIVE_OR_NULL_NUMBER.getMessage(), parameter, parameter, id));
        }
    }

    public void validateString(String value, String parameter, int minLength, int maxLength) {
        if (value == null) {
            throw new ValidatorException(
                    String.format(VALIDATE_NULL_STRING.getMessage(), parameter, parameter));
        }
        if (value.trim().length() < minLength || value.trim().length() > maxLength) {
            throw new ValidatorException(
                    String.format(
                            VALIDATE_STRING_LENGTH.getMessage(),
                            parameter,
                            minLength,
                            maxLength,
                            parameter,
                            value));
        }
    }
}
