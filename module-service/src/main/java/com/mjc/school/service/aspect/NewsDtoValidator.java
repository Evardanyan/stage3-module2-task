package com.mjc.school.service.aspect;

import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.validator.Validator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NewsDtoValidator {

    private final Validator validator;

    public NewsDtoValidator(Validator validator) {
        this.validator = validator;
    }

    @Before("@annotation(com.mjc.school.service.annotation.ValidateNewsDto) && args(dtoRequest)")
    public void validateNewsRequest(NewsDtoRequest dtoRequest) {
        validator.validateNewsDto(dtoRequest);

    }
}
