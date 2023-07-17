package com.mjc.school.controller.command.implementation.create;

import com.mjc.school.controller.interfaces.TagControllerInterface;
import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.helper.CommandHelper;
import com.mjc.school.controller.helper.Constant;
import com.mjc.school.controller.helper.Operations;
import com.mjc.school.service.dto.TagDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Scanner;

@Component
public class CreateTagCommand implements Command {

    private TagControllerInterface tagController;
    private Scanner keyboard;

    @Autowired
    public CreateTagCommand(@Qualifier("tagController") TagControllerInterface tagController) {
        this.tagController = tagController;
        this.keyboard = new Scanner(System.in);
    }
    @Override
    public void execute() {
        TagDtoRequest dtoRequest = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(Operations.CREATE_TAG.getOperation());
                System.out.println(Constant.TAG_NAME_ENTER);
                String name = keyboard.nextLine();
                System.out.println(Constant.NEWS_ID_ENTER);
                List<Long> newsIds = CommandHelper.getKeyboardListOfNumbers(Constant.NEWS_ID, keyboard);
                dtoRequest = new TagDtoRequest(null, name, newsIds);
                isValid = true;

                System.out.println(CommandHelper
                        .findCommandHandlerFor(Operations.CREATE_TAG.getOperationNumber(), tagController)
                        .invoke(tagController, dtoRequest));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                System.out.println(e.getCause().getMessage());
            }
        }
    }
}
