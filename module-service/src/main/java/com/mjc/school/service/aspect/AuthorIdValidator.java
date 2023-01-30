package com.mjc.school.service.aspect;

import com.mjc.school.service.validator.Validator;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorIdValidator {

    private final Validator validator;

    public AuthorIdValidator(Validator validator) {
        this.validator = validator;
    }


    @Before("@annotation(com.mjc.school.service.annotation.ValidateAuthorId) && args(id)")
    public void validateId(Long id) {
        validator.validateAuthorId(id);
    }

}
