package com.mjc.school.utils;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.utils.ControllerHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuCommandHandler {

    private final ControllerHandler controllerHandler;

    private MenuCommandHandler(ControllerHandler controllerHandler) {
        this.controllerHandler = controllerHandler;
    }

    void executeMenu(String operation) {
        Method[] menuMethods = ControllerHandler.class.getDeclaredMethods();
        List<Method> method = Arrays.stream(menuMethods)
                .filter(x -> x.isAnnotationPresent(CommandHandler.class))
                .filter(x -> x.getAnnotation(CommandHandler.class).operation().equals(operation)).collect(Collectors.toList());
        Method result = method.get(0);
        try {
            result.invoke(controllerHandler);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getCause().getMessage());
        }
    }
}
