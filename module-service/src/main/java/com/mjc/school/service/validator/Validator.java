package com.mjc.school.service.validator;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.exception.ServiceErrorCodeMessage;
import com.mjc.school.service.exception.ValidatorException;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    private final String NEWS_ID = "News id";
    private final String NEWS_CONTENT = "News content";
    private final String AUTHOR_ID = "Author id";
    private final String TAG_ID = "Tag id";
    private final String NEWS_TITLE = "News title";
    private final Integer NEWS_CONTENT_MIN_LENGTH = 5;;
    private final Integer NEWS_CONTENT_MAX_LENGTH = 255;
    private final Integer NEWS_TITLE_MIN_LENGTH = 5;
    private final Integer NEWS_TITLE_MAX_LENGTH = 30;
    private final Integer AUTHOR_NAME_LENGTH_MIN = 3;
    private final Integer AUTHOR_NAME_LENGTH_MAX = 15;

    private final Integer TAG_NAME_LENGTH_MIN = 3;
    private final Integer TAG_NAME_LENGTH_MAX = 15;


    public void validateNewsId(Long newsId) {
        this.validateNumber(newsId, NEWS_ID);
    }

    public void validateAuthorId(Long authorId) {
        this.validateNumber(authorId, AUTHOR_ID);
    }

    public void validateTagId(Long tagId) {
        this.validateNumber(tagId, AUTHOR_ID);
    }

    public void validateNewsDto(NewsDtoRequest dtoRequest) {
        this.validateString(dtoRequest.title(), NEWS_TITLE, NEWS_TITLE_MIN_LENGTH, NEWS_TITLE_MAX_LENGTH);
        this.validateString(dtoRequest.content(), NEWS_CONTENT, NEWS_CONTENT_MIN_LENGTH, NEWS_CONTENT_MAX_LENGTH);
        this.validateAuthorId(dtoRequest.authorId());
    }

    public void validateAuthorDto(AuthorDtoRequest dtoRequest) {
        this.validateAuthorName(dtoRequest.name());
    }

    public void validateTagDto(TagDtoRequest dtoRequest) {
        this.validateTagName(dtoRequest.name());
    }

    public void validateAuthorName(String name) {
        if (name == null) {
            throw new ValidatorException("Name cannot be null.");
        } else if (name.trim().length() < AUTHOR_NAME_LENGTH_MIN || name.trim().length() > AUTHOR_NAME_LENGTH_MAX) {
            throw new ValidatorException("Name length should be between 3 and 15 characters.");
        }
    }

    public void validateTagName(String name) {
        if (name == null) {
            throw new ValidatorException("Name cannot be null.");
        } else if (name.trim().length() < TAG_NAME_LENGTH_MIN || name.trim().length() > TAG_NAME_LENGTH_MAX) {
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
}
