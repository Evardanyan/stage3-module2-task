package com.mjc.school.service.aspect;

import com.mjc.school.service.validator.Validator;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NewsIdValidator {

    private final Validator validator;

    public NewsIdValidator(Validator validator) {
        this.validator = validator;
    }


    @Before("@annotation(com.mjc.school.service.annotation.ValidateNewsId) && args(id)")
    public void validateId(Long id) {
        validator.validateNewsId(id);


    }




}
