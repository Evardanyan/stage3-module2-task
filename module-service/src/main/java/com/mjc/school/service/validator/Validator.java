package com.mjc.school.service.validator;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exception.ServiceErrorCodeMessage;
import com.mjc.school.service.exception.ValidatorException;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    private static Validator newsValidator;
    private static final String NEWS_ID = "News id";
    private static final String NEWS_CONTENT = "News content";
    private static final String AUTHOR_ID = "Author id";
    private static final String NEWS_TITLE = "News title";
    private static final Integer NEWS_CONTENT_MIN_LENGTH = 5;;
    private static final Integer NEWS_CONTENT_MAX_LENGTH = 255;
    private static final Integer NEWS_TITLE_MIN_LENGTH = 5;
    private static final Integer NEWS_TITLE_MAX_LENGTH = 30;
    private static final Integer AUTHOR_NAME_LENGTH_MIN = 3;
    private static final Integer AUTHOR_NAME_LENGTH_MAX = 15;
    private static final Integer MAX_AUTHOR_ID = 20;


    public void validateNewsId(Long newsId) {
        this.validateNumber(newsId, NEWS_ID);
        if (newsId > (long)MAX_AUTHOR_ID.intValue()) {
            throw new ValidatorException(String.format(ServiceErrorCodeMessage.AUTHOR_ID_DOES_NOT_EXIST.getCodeMsg(), newsId));
        }
    }

    public void validateAuthorId(Long authorId) {
        this.validateNumber(authorId, AUTHOR_ID);
        if (authorId > (long)MAX_AUTHOR_ID.intValue()) {
            throw new ValidatorException(String.format(ServiceErrorCodeMessage.AUTHOR_ID_DOES_NOT_EXIST.getCodeMsg(), authorId));
        }
    }

    public void validateNewsDto(NewsDtoRequest dtoRequest) {
        this.validateString(dtoRequest.title(), NEWS_TITLE, NEWS_TITLE_MIN_LENGTH, NEWS_TITLE_MAX_LENGTH);
        this.validateString(dtoRequest.content(), NEWS_CONTENT, NEWS_CONTENT_MIN_LENGTH, NEWS_CONTENT_MAX_LENGTH);
        this.validateAuthorId(dtoRequest.authorId());
    }

    public void validateAuthorDto(AuthorDtoRequest dtoRequest) {
        this.validateAuthorName(dtoRequest.name());
    }

    public void validateAuthorName(String name) {
        if (name == null) {
            throw new ValidatorException("Name cannot be null.");
        } else if (name.trim().length() < AUTHOR_NAME_LENGTH_MIN || name.trim().length() > AUTHOR_NAME_LENGTH_MAX) {
            throw new ValidatorException("Name length should be between 3 and 15 characters.");
        }
    }

    private void validateNumber(Long id, String parameter) {
        if (id == null || id < 1L) {
            throw new ValidatorException(String.format(ServiceErrorCodeMessage.VALIDATE_NEGATIVE_OR_NULL_NUMBER.getCodeMsg(), parameter, parameter, id));
        }
    }

    private void validateString(String value, String parameter, int minLength, int maxLength) {
        if (value == null) {
            throw new ValidatorException(String.format(ServiceErrorCodeMessage.VALIDATE_NULL_STRING.getCodeMsg(), parameter, parameter));
        }
        if (value.trim().length() < minLength || value.trim().length() > maxLength) {
            throw new ValidatorException(String.format(ServiceErrorCodeMessage.VALIDATE_STRING_LENGTH.getCodeMsg(), parameter, minLength, maxLength, parameter, value));
        }
    }

//    static {
//        NEWS_CONTENT_MIN_LENGTH = 5;
//        NEWS_CONTENT_MAX_LENGTH = 255;
//        AUTHOR_NAME_LENGTH_MIN = 3;
//        AUTHOR_NAME_LENGTH_MAX = 15;
//        NEWS_TITLE_MIN_LENGTH = 5;
//        NEWS_TITLE_MAX_LENGTH = 30;
//        MAX_AUTHOR_ID = 20;
//    }
}
