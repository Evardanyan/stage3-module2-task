package com.mjc.school.utils;

import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.impl.NewsController;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    private final MenuHelper menuHelper;

    private final NewsController newsController;

    private final AuthorController authorController;

    public Menu (MenuHelper menuHelper, NewsController newsController, AuthorController authorController) {
        this.menuHelper = menuHelper;
        this.newsController = newsController;
        this.authorController = authorController;
    }

    public void start() {
        Scanner keyboardInput = new Scanner(System.in);

        while (true) {
            try {
                while (true) {
                    String key;
                    menuHelper.printMainMenu();
                    switch (key = keyboardInput.nextLine()) {
                        case "1" -> {
                            menuHelper.getNews(newsController);
                            continue;
                        }
                        case "2" -> {
                            menuHelper.getNewsById(newsController, keyboardInput);
                            continue;
                        }
                        case "3" -> {
                            menuHelper.createNews(newsController, keyboardInput);
                            continue;
                        }
                        case "4" -> {
                            menuHelper.updateNews(newsController, keyboardInput);
                            continue;
                        }
                        case "5" -> {
                            menuHelper.deleteNews(newsController, keyboardInput);
                            continue;
                        }
                        case "6" -> {
                            menuHelper.getAuthors(authorController);
                            continue;
                        }
                        case "7" -> {
                            menuHelper.getAuthorsById(authorController, keyboardInput);
                            continue;
                        }
                        case "8" -> {
                            menuHelper.createAuthors(authorController, keyboardInput);
                            continue;
                        }
                        case "9" -> {
                            menuHelper.updateAuthors(authorController, keyboardInput);
                            continue;
                        }
                        case "10" -> {
                            menuHelper.deleteAuthors(authorController, keyboardInput);
                            continue;
                        }
                        case "0" -> {
                            System.exit(0);
                            continue;
                        }
                    }
                    System.out.println("Command not found.");
                }
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
                continue;
            }
        }
    }
}