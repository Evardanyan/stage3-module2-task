package com.mjc.school.service.aspect;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.validator.Validator;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TagDtoValidator {
    private final Validator validator;

    public TagDtoValidator(Validator validator) {
        this.validator = validator;
    }

    @Before("@annotation(com.mjc.school.service.annotation.ValidateTagDto) && args(dtoRequest)")
    public void validateNewsRequest(TagDtoRequest dtoRequest) {
        validator.validateTagDto(dtoRequest);
    }
}
