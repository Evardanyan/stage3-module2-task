package com.mjc.school.utils;

import com.mjc.school.controllertest.annotation.CommandHandler;
import com.mjc.school.controllertest.utils.MenuHelper;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class MenuCommandHandler {

    private final MenuHelper menuHelper;

    public MenuCommandHandler(MenuHelper menuHelper) {
        this.menuHelper = menuHelper;
    }

    void executeMenu(String operation) {
        Method[] menuMethods = MenuHelper.class.getDeclaredMethods();
        List<Method> method = Arrays.stream(menuMethods)
                .filter(x -> x.isAnnotationPresent(CommandHandler.class))
                .filter(x -> x.getAnnotation(CommandHandler.class).operation().equals(operation)).collect(Collectors.toList());
        Method result = method.get(0);
        try {
            result.invoke(menuHelper);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getCause().getMessage());

        }

    }

    public void start() {
        Scanner keyboardInput = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String key;
            menuHelper.printMainMenu();
            key = keyboardInput.nextLine();
            if (checkInput(key)) {
                if (key.equals("0")) {
                    System.out.println("Have Good day! Bye!");
                    break;
                }
                executeMenu(key);
            } else {
                System.out.println("Please input only number from 1-10");
            }
        }
    }

    boolean checkInput(String input) {
        if (input.equals("10")) {
            return true;
        } else if ((!Character.isDigit(input.charAt(0)) || input.length() > 1)) {
            return false;
        }
        return true;
    }
}
