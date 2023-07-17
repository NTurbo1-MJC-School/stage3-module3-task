package com.mjc.school.controller.command.implementation.update;

import com.mjc.school.controller.interfaces.NewsControllerInterface;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Constant;
import com.mjc.school.controller.helper.Operations;
import com.mjc.school.service.dto.NewsDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Scanner;

@Component
public class UpdateNewsCommand implements Command {

    private NewsControllerInterface newsController;
    private Scanner keyboard;

    @Autowired
    public UpdateNewsCommand(@Qualifier("newsController") NewsControllerInterface newsController) {
        this.newsController = newsController;
        this.keyboard = new Scanner(System.in);
    }

    @Override
    public void execute() {
        NewsDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(Operations.UPDATE_NEWS.getOperation());
                System.out.println(Constant.NEWS_ID_ENTER);
                Long newsId = CommandHelper.getKeyboardNumber(Constant.NEWS_ID, keyboard);
                System.out.println(Constant.NEWS_TITLE_ENTER);
                String title = keyboard.nextLine();
                System.out.println(Constant.NEWS_CONTENT_ENTER);
                String content = keyboard.nextLine();
                System.out.println(Constant.AUTHOR_ID_ENTER);
                Long authorId = CommandHelper.getKeyboardNumber(Constant.AUTHOR_ID, keyboard);
                System.out.println(Constant.TAG_ID_ENTER);
                List<Long> tagIds = CommandHelper.getKeyboardListOfNumbers(Constant.TAG_ID, keyboard);
                dtoRequest = new NewsDtoRequest(newsId, title, content, authorId, tagIds);
                isValid = true;

                System.out.println(CommandHelper
                        .findCommandHandlerFor(Operations.UPDATE_NEWS.getOperationNumber(), newsController)
                        .invoke(newsController, dtoRequest));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                System.out.println(e.getCause().getMessage());
            }
        }
    }
}
