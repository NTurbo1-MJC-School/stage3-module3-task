package com.mjc.school.controller.command.implementation.getById;

import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.interfaces.NewsControllerInterface;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Constant;
import com.mjc.school.controller.helper.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

@Component
public class GetNewsByIdCommand implements Command {

    private NewsControllerInterface newsController;
    private Scanner keyboard;

    @Autowired
    public GetNewsByIdCommand(@Qualifier("newsController") NewsControllerInterface newsController) {
        this.newsController = newsController;
        this.keyboard = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println(Operations.GET_NEWS_BY_ID.getOperation());
        System.out.println(Constant.NEWS_ID_ENTER);
        try {
            Long id = CommandHelper.getKeyboardNumber(Constant.NEWS_ID, keyboard);
            System.out.println(CommandHelper
                    .findCommandHandlerFor(Operations.GET_NEWS_BY_ID.getOperationNumber(), newsController)
                    .invoke(newsController, id));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println(e.getCause().getMessage());
        }
    }
}
