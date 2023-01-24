package com.mjc.school.service.aspect;

import com.mjc.school.service.validator.Validator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorIdValidator {

    @Autowired
    private Validator validator;

//    @Before("@annotation(com.mjc.school.service.annotation.ValidateAuthorId)")
    @Around("@annotation(com.mjc.school.service.annotation.ValidateAuthorId)")
    public void beforeCRUDOperationAuthorService(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Object[] args = joinPoint.getArgs();
    try {
        validator.validateAuthorId(((Long) (args[0])));
    } catch (RuntimeException ex) {
//        System.out.println(ex.getMessage());
        ex.getMessage();
    }

    }


}
