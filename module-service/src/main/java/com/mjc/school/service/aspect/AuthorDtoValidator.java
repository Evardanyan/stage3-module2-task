package com.mjc.school.service.aspect;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.validator.Validator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorDtoValidator {
    @Autowired
    private Validator validator;

    @Before("@annotation(com.mjc.school.service.annotation.ValidateAuthorDto)")
    public void beforeCRUDOperation(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Object[] args = joinPoint.getArgs();
        try {

            validator.validateAuthorDto(((AuthorDtoRequest) (args[0])));
        } catch (RuntimeException ex) {
            ex.getMessage();
        }

    }
}
