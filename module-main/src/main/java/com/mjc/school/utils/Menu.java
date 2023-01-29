package com.mjc.school.utils;

import com.mjc.school.controllertest.utils.Operation;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    private final MenuCommandHandler menuCommandHandler;

    private Menu(MenuCommandHandler menuCommandHandler) {
        this.menuCommandHandler = menuCommandHandler;
    }

    public void start() {
        Scanner keyboardInput = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String key;
            printMainMenu();
            key = keyboardInput.nextLine();
            if (checkInput(key)) {
                if (key.equals("0")) {
                    System.out.println("Have Good day! Bye!");
                    break;
                }
                menuCommandHandler.executeMenu(key);
            } else {
                System.out.println("Please input only number from 1-10");
            }
        }
    }

    public void printMainMenu() {
        System.out.println("Enter the number of operation:");
        for (Operation operation : Operation.values()) {
            System.out.println(operation.getOperationWithNumber());
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
