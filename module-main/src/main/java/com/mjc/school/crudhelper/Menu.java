package com.mjc.school.crudhelper;

import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.impl.NewsController;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    private final CrudMenuHelper crudMenuHelper;

    private final NewsController newsController;

    private final AuthorController authorController;

    public Menu (CrudMenuHelper crudMenuHelper, NewsController newsController, AuthorController authorController) {
        this.crudMenuHelper = crudMenuHelper;
        this.newsController = newsController;
        this.authorController = authorController;
    }

    public void start() {
        Scanner keyboardInput = new Scanner(System.in);

        while (true) {
            try {
                while (true) {
                    String key;
                    crudMenuHelper.printMainMenu();
                    switch (key = keyboardInput.nextLine()) {
                        case "1" -> {
                            crudMenuHelper.getNews(newsController);
                            continue;
                        }
                        case "2" -> {
                            crudMenuHelper.getNewsById(newsController, keyboardInput);
                            continue;
                        }
                        case "3" -> {
                            crudMenuHelper.createNews(newsController, keyboardInput);
                            continue;
                        }
                        case "4" -> {
                            crudMenuHelper.updateNews(newsController, keyboardInput);
                            continue;
                        }
                        case "5" -> {
                            crudMenuHelper.deleteNews(newsController, keyboardInput);
                            continue;
                        }
                        case "6" -> {
                            crudMenuHelper.getAuthors(authorController);
                            continue;
                        }
                        case "7" -> {
                            crudMenuHelper.getAuthorsById(authorController, keyboardInput);
                            continue;
                        }
                        case "8" -> {
                            crudMenuHelper.createAuthors(authorController, keyboardInput);
                            continue;
                        }
                        case "9" -> {
                            crudMenuHelper.updateAuthors(authorController, keyboardInput);
                            continue;
                        }
                        case "10" -> {
                            crudMenuHelper.deleteAuthors(authorController, keyboardInput);
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