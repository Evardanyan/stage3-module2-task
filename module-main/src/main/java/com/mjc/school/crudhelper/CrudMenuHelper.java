package com.mjc.school.crudhelper;

import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exception.ServiceErrorCodeMessage;
import com.mjc.school.service.exception.ValidatorException;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CrudMenuHelper {
    public void printMainMenu() {
        System.out.println("Enter the number of operation:");
        for (Operations operation : Operations.values()) {
            System.out.println(operation.getOperationWithNumber());
        }
    }

    public void getNews(NewsController newsController) {
        System.out.println(Operations.GET_ALL_NEWS.getOperation());
        newsController.readAll().forEach(System.out::println);
    }

    public void getNewsById(NewsController newsController, Scanner keyboard) {
        System.out.println(Operations.GET_NEWS_BY_ID.getOperation());
        System.out.println("Enter news id:");
        System.out.println((Object)newsController.readById(Long.valueOf(this.getKeyboardNumber("News Id", keyboard))));
    }

    public void createNews(NewsController newsController, Scanner keyboard) {
        NewsDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(Operations.CREATE_NEWS.getOperation());
                System.out.println("Enter news title:");
                String title = keyboard.nextLine();
                System.out.println("Enter news content:");
                String content = keyboard.nextLine();
                System.out.println("Enter author id:");
                Long authorId = this.getKeyboardNumber("Author Id", keyboard);
                dtoRequest = new NewsDtoRequest(null, title, content, authorId);
                isValid = true;
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println((Object)newsController.create(dtoRequest));
    }

    public void updateNews(NewsController newsController, Scanner keyboard) {
        NewsDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(Operations.UPDATE_NEWS.getOperation());
                System.out.println("Enter news id:");
                Long newsId = this.getKeyboardNumber("News Id", keyboard);
                System.out.println("Enter news title:");
                String title = keyboard.nextLine();
                System.out.println("Enter news content:");
                String content = keyboard.nextLine();
                System.out.println("Enter author id:");
                Long authorId = this.getKeyboardNumber("Author Id", keyboard);
                dtoRequest = new NewsDtoRequest(newsId, title, content, authorId);
                isValid = true;
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println((Object)newsController.update(dtoRequest));
    }

    public void deleteNews(NewsController newsController, Scanner keyboard) {
        System.out.println(Operations.REMOVE_NEWS_BY_ID.getOperation());
        System.out.println("Enter news id:");
        System.out.println(newsController.deleteById(Long.valueOf(this.getKeyboardNumber("News Id", keyboard))));
    }




    public void getAuthors(AuthorController authorController) {
        System.out.println(Operations.GET_ALL_AUTHOR.getOperation());
        authorController.readAll().forEach(System.out::println);
    }

    public void getAuthorsById(AuthorController authorController, Scanner keyboard) {
        System.out.println(Operations.GET_AUTHOR_BY_ID.getOperation());
        System.out.println("Enter news id:");
        System.out.println((Object)authorController.readById(Long.valueOf(this.getKeyboardNumber("Author Id", keyboard))));
    }

    public void createAuthors(AuthorController authorController, Scanner keyboard) {
        AuthorDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(Operations.CREATE_AUTHOR.getOperation());
                System.out.println("Enter author id:");
                Long authorId = this.getKeyboardNumber("Author Id", keyboard);
                System.out.println("Enter author name:");
                String authorName = keyboard.nextLine();
                dtoRequest = new AuthorDtoRequest(authorId, authorName);
                isValid = true;
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println((Object)authorController.create(dtoRequest));
    }

    public void updateAuthors(AuthorController authorController, Scanner keyboard) {
        AuthorDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(Operations.UPDATE_NEWS.getOperation());
                System.out.println("Enter author id:");
                Long authorId = this.getKeyboardNumber("Author Id", keyboard);
                System.out.println("Enter author name:");
                String authorName = keyboard.nextLine();
                dtoRequest = new AuthorDtoRequest(authorId, authorName);
                isValid = true;
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println((Object)authorController.update(dtoRequest));
    }

    public void deleteAuthors(AuthorController authorController, Scanner keyboard) {
        System.out.println(Operations.REMOVE_AUTHOR_BY_ID.getOperation());
        System.out.println("Enter author id:");
        System.out.println(authorController.deleteById(Long.valueOf(this.getKeyboardNumber("Author Id", keyboard))));
    }

    private long getKeyboardNumber(String params, Scanner keyboard) {
        long enter;
        try {
            enter = keyboard.nextLong();
            keyboard.nextLine();
        }
        catch (Exception ex) {
            keyboard.nextLine();
            throw new ValidatorException(String.format(ServiceErrorCodeMessage.VALIDATE_INT_VALUE.getCodeMsg(), params));
        }
        return enter;
    }
}
