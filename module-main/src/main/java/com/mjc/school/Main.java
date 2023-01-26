package com.mjc.school;

import com.mjc.school.utils.MenuStartCommand;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        MenuStartCommand menu = applicationContext.getBean(MenuStartCommand.class);
        menu.execute();

    }
}
