package com.mjc.school.controller.command.implementation.getAll;

import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.interfaces.NewsControllerInterface;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Component
public class GetAllNewsCommand implements Command {

    private NewsControllerInterface newsController;

    @Autowired
    public GetAllNewsCommand(@Qualifier("newsController") NewsControllerInterface newsController) {
        this.newsController = newsController;
    }
    @Override
    public void execute() {
        System.out.println(Operations.GET_ALL_NEWS.getOperation());
        try {
            if (CommandHelper
                    .findCommandHandlerFor(Operations.GET_ALL_NEWS.getOperationNumber(), newsController)
                    .invoke(newsController) instanceof List<?> allNews) {
                allNews.forEach(System.out::println);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println(e.getCause().getMessage());
        }
    }
}
