package com.mjc.school.controller.command.implementation.getByNewsId;

import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Constant;
import com.mjc.school.controller.helper.Operations;
import com.mjc.school.controller.interfaces.TagControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

@Component
public class GetTagsByNewsIdCommand implements Command {
    private final TagControllerInterface tagController;
    private Scanner keyboard;

    @Autowired
    public GetTagsByNewsIdCommand(@Qualifier("tagController") TagControllerInterface tagController) {
        this.tagController = tagController;
        this.keyboard = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println(Operations.GET_TAG_BY_NEWS_ID.getOperation());
        System.out.println(Constant.NEWS_ID_ENTER);
        try {
            Long id = CommandHelper.getKeyboardNumber(Constant.NEWS_ID, keyboard);
            System.out.println(CommandHelper
                    .findCommandHandlerFor(Operations.GET_TAG_BY_NEWS_ID.getOperationNumber(), tagController)
                    .invoke(tagController, id));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println(e.getCause().getMessage());
        }
    }
}
