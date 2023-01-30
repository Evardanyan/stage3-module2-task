package com.mjc.school.controller.utils;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exception.ServiceErrorCodeMessage;
import com.mjc.school.service.exception.ValidatorException;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ControllerHandler {

    private final NewsController newsController;
    private final AuthorController authorController;

    private final String  AUTHOR_ID = "Author Id";
    private final String  NEWS_ID = "News Id";
    private final String  ENTER_AUTHOR_ID = "Enter Author Id";
    private final String  ENTER_NEWS_ID = "Enter News Id";

    private Scanner keyboard = new Scanner(System.in);

    private ControllerHandler(NewsController newsController, AuthorController authorController) {
        this.newsController = newsController;
        this.authorController = authorController;
    }

    @CommandHandler(operation = "1")
    public void getNews() {
        System.out.println(Operation.GET_ALL_NEWS.getOperation());
        newsController.readAll().forEach(System.out::println);
    }

    @CommandHandler(operation = "2")
    public void getNewsById() {
        System.out.println(Operation.GET_NEWS_BY_ID.getOperation());
        System.out.println(ENTER_NEWS_ID);
        System.out.println(newsController.readById(Long.valueOf(this.getKeyboardNumber(NEWS_ID, keyboard))));
    }

    @CommandHandler(operation = "3")
    public void createNews() {
        NewsDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(Operation.CREATE_NEWS.getOperation());
                System.out.println("Enter news title:");
                String title = keyboard.nextLine();
                System.out.println("Enter news content:");
                String content = keyboard.nextLine();
                System.out.println(ENTER_AUTHOR_ID);
                Long authorId = this.getKeyboardNumber(AUTHOR_ID, keyboard);
                dtoRequest = new NewsDtoRequest(null, title, content, authorId);
                isValid = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(newsController.create(dtoRequest));
    }

    @CommandHandler(operation = "4")
    public void updateNews() {
        NewsDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(Operation.UPDATE_NEWS.getOperation());
                System.out.println(ENTER_NEWS_ID);
                Long newsId = this.getKeyboardNumber(NEWS_ID, keyboard);
                System.out.println("Enter news title:");
                String title = keyboard.nextLine();
                System.out.println("Enter news content:");
                String content = keyboard.nextLine();
                System.out.println(ENTER_AUTHOR_ID);
                Long authorId = this.getKeyboardNumber(AUTHOR_ID, keyboard);
                dtoRequest = new NewsDtoRequest(newsId, title, content, authorId);
                isValid = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(newsController.update(dtoRequest));
    }

    @CommandHandler(operation = "5")
    public void deleteNews() {
        System.out.println(Operation.REMOVE_NEWS_BY_ID.getOperation());
        System.out.println(ENTER_NEWS_ID);
        System.out.println(newsController.deleteById(Long.valueOf(this.getKeyboardNumber(NEWS_ID, keyboard))));
    }


    @CommandHandler(operation = "6")
    public void getAuthors() {
        System.out.println(Operation.GET_ALL_AUTHOR.getOperation());
        authorController.readAll().forEach(System.out::println);
    }


    @CommandHandler(operation = "7")
    public void getAuthorsById() {
        System.out.println(Operation.GET_AUTHOR_BY_ID.getOperation());
        System.out.println(ENTER_AUTHOR_ID);
        System.out.println(authorController.readById(Long.valueOf(this.getKeyboardNumber(AUTHOR_ID, keyboard))));
    }

    @CommandHandler(operation = "8")
    public void createAuthors() {
        AuthorDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(Operation.CREATE_AUTHOR.getOperation());
                System.out.println(ENTER_AUTHOR_ID);
                Long authorId = this.getKeyboardNumber(AUTHOR_ID, keyboard);
                System.out.println("Enter author name:");
                String authorName = keyboard.nextLine();
                dtoRequest = new AuthorDtoRequest(authorId, authorName);
                isValid = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(authorController.create(dtoRequest));
    }

    @CommandHandler(operation = "9")
    public void updateAuthors() {
        AuthorDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(Operation.UPDATE_NEWS.getOperation());
                System.out.println(ENTER_AUTHOR_ID);
                Long authorId = this.getKeyboardNumber(AUTHOR_ID, keyboard);
                System.out.println("Enter author name:");
                String authorName = keyboard.nextLine();
                dtoRequest = new AuthorDtoRequest(authorId, authorName);
                isValid = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(authorController.update(dtoRequest));
    }

    @CommandHandler(operation = "10")
    public void deleteAuthors() {
        System.out.println(Operation.REMOVE_AUTHOR_BY_ID.getOperation());
        System.out.println(ENTER_AUTHOR_ID);
        System.out.println(authorController.deleteById(Long.valueOf(this.getKeyboardNumber(AUTHOR_ID, keyboard))));
    }

    @CommandHandler(operation = "0")
    public void appExit() {
        System.out.println("Have Good day! Bye!");
        System.exit(0);
    }

    private long getKeyboardNumber(String params, Scanner keyboard) {
        long enter;
        try {
            enter = keyboard.nextLong();
            keyboard.nextLine();
        } catch (Exception ex) {
            keyboard.nextLine();
            throw new ValidatorException(String.format(ServiceErrorCodeMessage.VALIDATE_INT_VALUE.getCodeMsg(), params));
        }
        return enter;
    }
}
