package com.mjc.school.utils;

import com.mjc.school.utils.interfaces.Command;
import org.springframework.stereotype.Component;

@Component
public class MenuStartCommand implements Command {

    Menu menu;
    public MenuStartCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        menu.start();
    }
}
